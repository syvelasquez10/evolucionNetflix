// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.android.osp.AsyncTaskCompat;
import com.netflix.mediaclient.util.CryptoUtils;
import android.media.MediaDrm$ProvisionRequest;
import android.os.Build;
import com.netflix.mediaclient.nccp.NccpRequestTask;
import com.netflix.mediaclient.nccp.transaction.CdmProvisionNccpTransaction;
import com.netflix.mediaclient.nccp.response.CdmProvisionNccpResponse;
import com.netflix.mediaclient.nccp.NccpResponse;
import com.netflix.mediaclient.nccp.NccpTransaction;
import com.netflix.mediaclient.nccp.NccpResponseHandler;
import java.util.Arrays;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.MediaDrm$CryptoSession;
import android.util.Base64;
import java.util.HashMap;
import android.media.MediaDrm$KeyRequest;
import com.netflix.mediaclient.Log;
import android.media.NotProvisionedException;
import android.media.DeniedByServerException;
import android.media.UnsupportedSchemeException;
import com.netflix.mediaclient.util.PreferenceUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent;
import android.media.MediaDrm;
import java.util.UUID;
import android.annotation.TargetApi;
import android.media.MediaDrm$OnEventListener;

@TargetApi(18)
public class WidevineDrmManager implements MediaDrm$OnEventListener, DrmManager
{
    public static final String PROPERTY_SYSTEM_ID = "systemId";
    public static final String TAG = "nf_drm";
    private static final UUID WIDEVINE_SCHEME;
    private static final byte[] init;
    private MediaDrm drm;
    private DrmReadyCallback mCallback;
    private ServiceAgent.ConfigurationAgentInterface mConfiguration;
    private Context mContext;
    private DelayedRegistrationCallback mDelayedRegistration;
    private ErrorLogging mErrorLogging;
    private ServiceAgent.UserAgentInterface mUser;
    private CryptoSession nccpCryptoFactoryCryptoSession;
    private AtomicBoolean nccpCryptoFactoryReady;
    
    static {
        init = new byte[] { 10, 122, 0, 108, 56, 43 };
        WIDEVINE_SCHEME = new UUID(-1301668207276963122L, -6645017420763422227L);
    }
    
    WidevineDrmManager(final Context mContext, final ServiceAgent.ConfigurationAgentInterface mConfiguration, final ServiceAgent.UserAgentInterface mUser, final ErrorLogging mErrorLogging, final DrmReadyCallback mCallback) throws UnsupportedSchemeException {
        this.nccpCryptoFactoryReady = new AtomicBoolean(false);
        this.nccpCryptoFactoryCryptoSession = new CryptoSession();
        if (mCallback == null) {
            throw new IllegalArgumentException();
        }
        this.mCallback = mCallback;
        this.mConfiguration = mConfiguration;
        this.mUser = mUser;
        this.mErrorLogging = mErrorLogging;
        this.mContext = mContext;
        (this.drm = new MediaDrm(WidevineDrmManager.WIDEVINE_SCHEME)).setOnEventListener((MediaDrm$OnEventListener)this);
        this.showProperties();
        if (this.isWidevinePluginChanged()) {
            this.reset();
        }
        PreferenceUtils.putStringPref(this.mContext, "nf_drm_system_id", this.getDeviceType());
    }
    
    private void afterWidewineProvisioning() {
        if (this.restoreKeySetFromNccpCryptoFactoryDrmSession()) {
            Log.d("nf_drm", "NCCP Cryto Factory based on Widewine CDM is ready");
            this.onReady();
            return;
        }
        Log.d("nf_drm", "Execute CDMProvisoningRequest to NCCP, key set not found, crypto factory not ready!");
        try {
            this.startNccpProvisioning();
        }
        catch (NotProvisionedException ex) {
            Log.e("nf_drm", "Not provisioned. It should not happen! Redo provisioning!", (Throwable)ex);
            this.startWidewineProvisioning();
        }
    }
    
    private void cdmProvisioningCompleted() {
        synchronized (this.nccpCryptoFactoryReady) {
            this.nccpCryptoFactoryReady.set(true);
            if (this.mDelayedRegistration == null) {
                Log.d("nf_drm", "No delayed registration");
                return;
            }
            Log.d("nf_drm", "Delayed registration found. Execute it");
            Log.d("nf_drm", "Send registation through bridge...");
            this.mDelayedRegistration.execute();
            this.mDelayedRegistration = null;
            Log.d("nf_drm", "Send registation through bridge done.");
        }
    }
    
    private void closeSession(final byte[] array) {
        // monitorenter(this)
        Label_0043: {
            if (array == null) {
                break Label_0043;
            }
            try {
                Log.d("nf_drm", "Closing session!");
                try {
                    this.drm.closeSession(array);
                    return;
                }
                catch (Throwable t) {
                    Log.w("nf_drm", "Failed to close orphan pending session!", t);
                }
            }
            finally {
            }
            // monitorexit(this)
        }
        Log.w("nf_drm", "Session ID is null. Ignore.");
    }
    
    private void closeSessionAndRemoveKeys(final byte[] array) {
        // monitorenter(this)
        Label_0058: {
            if (array == null) {
                break Label_0058;
            }
            try {
                if (this.drm != null) {
                    Log.d("nf_drm", "closeSessionAndRemoveKeys!");
                    this.drm.removeKeys(array);
                    try {
                        this.drm.closeSession(array);
                        return;
                    }
                    catch (Throwable t) {
                        Log.w("nf_drm", "closeSessionAndRemoveKeys failed !", t);
                    }
                }
            }
            finally {
            }
            // monitorexit(this)
        }
        Log.w("nf_drm", "closeSessionAndRemoveKeys sessionId is null. Ignore.");
    }
    
    private MediaDrm$KeyRequest createKeyRequest() throws NotProvisionedException {
        synchronized (this) {
            Log.d("nf_drm", "get NCCP session key request");
            this.closeSessionAndRemoveKeys(this.nccpCryptoFactoryCryptoSession.pendingSessionId);
            Log.d("nf_drm", "Create a new crypto session");
            this.nccpCryptoFactoryCryptoSession.pendingSessionId = this.drm.openSession();
            return this.drm.getKeyRequest(this.nccpCryptoFactoryCryptoSession.pendingSessionId, WidevineDrmManager.init, "application/xml", 2, new HashMap());
        }
    }
    
    private boolean createNccpCryptoFactoryDrmSession() {
        while (true) {
            Label_0147: {
                try {
                    this.nccpCryptoFactoryCryptoSession.sessionId = this.drm.openSession();
                    if (Log.isLoggable("nf_drm", 3)) {
                        if (this.nccpCryptoFactoryCryptoSession.sessionId == null) {
                            break Label_0147;
                        }
                        Log.d("nf_drm", "Device is provisioned. NCCP crypto factory session ID: " + new String(this.nccpCryptoFactoryCryptoSession.sessionId));
                    }
                    return true;
                }
                catch (NotProvisionedException ex) {
                    Log.e("nf_drm", "Device is not provisioned, start provisioning workflow!", (Throwable)ex);
                    this.startWidewineProvisioning();
                    return false;
                }
                catch (Throwable t) {
                    Log.e("nf_drm", "Fatal error, can not recover!", t);
                    this.mCallback.drmError(-100);
                    this.mErrorLogging.logHandledException("Failed to created NCCP crypto factory DRM session " + t.getMessage());
                    return false;
                }
            }
            Log.d("nf_drm", "Device is provisioned. NCCP crypto factory session ID: null");
            return true;
        }
    }
    
    private void dumpKeyReqyest(final byte[] array) {
        if (array != null) {
            if (Log.isLoggable("nf_drm", 3)) {
                Log.d("nf_drm", "key request created: " + Base64.encodeToString(array, 2));
            }
            return;
        }
        Log.w("nf_drm", "key request returned null");
    }
    
    private MediaDrm$CryptoSession findMediaDrmCryptoSession() {
        final byte[] sessionId = this.nccpCryptoFactoryCryptoSession.sessionId;
        if (sessionId == null) {
            return null;
        }
        return this.drm.getCryptoSession(sessionId, getCipherAlgorithm(), getMacAlgorithm());
    }
    
    private static String getCipherAlgorithm() {
        return "AES/CBC/NoPadding";
    }
    
    private static String getMacAlgorithm() {
        return "HmacSHA256";
    }
    
    public static String getMediaDrmSecurityLevels() {
        try {
            final MediaDrm mediaDrm = new MediaDrm(WidevineDrmManager.WIDEVINE_SCHEME);
            final String propertyString = mediaDrm.getPropertyString("securityLevel");
            Log.d("nf_drm", "Widevine securityLevel [" + propertyString + "]");
            if (mediaDrm != null) {
                mediaDrm.release();
            }
            return propertyString;
        }
        catch (UnsupportedSchemeException ex) {
            return null;
        }
    }
    
    private void handleNccpProvisioningFailure(final boolean b, final boolean b2) {
        if (b2) {
            Log.e("nf_drm", "Clear session and key ids");
            this.reset();
        }
        if (Log.isLoggable("nf_drm", 6)) {
            Log.e("nf_drm", "Report failure, fatal " + b);
        }
        this.mCallback.drmError(-100);
        this.mErrorLogging.logHandledException("Failed NCCP provisioning!");
    }
    
    private boolean isWidevinePluginChanged() {
        final String stringPref = PreferenceUtils.getStringPref(this.mContext, "nf_drm_system_id", null);
        final String deviceType = this.getDeviceType();
        if (stringPref == null) {
            if (Log.isLoggable("nf_drm", 3)) {
                Log.d("nf_drm", "System ID was not saved, user is not logged in, no need to report that plugin is changed: " + stringPref);
            }
        }
        else {
            if (!stringPref.equals(deviceType)) {
                if (Log.isLoggable("nf_drm", 3)) {
                    Log.d("nf_drm", "System ID changed from " + stringPref + " to " + deviceType);
                }
                return true;
            }
            if (Log.isLoggable("nf_drm", 3)) {
                Log.d("nf_drm", "System ID did not changed: " + stringPref);
                return false;
            }
        }
        return false;
    }
    
    @SuppressLint({ "NewApi" })
    public static boolean isWidewineSupported() {
        return AndroidUtils.getAndroidVersion() >= 18 && MediaDrm.isCryptoSchemeSupported(WidevineDrmManager.WIDEVINE_SCHEME);
    }
    
    private void mediaDrmFailure(final boolean b) {
        // monitorenter(this)
        Label_0021: {
            if (b) {
                break Label_0021;
            }
            try {
                if (this.nccpCryptoFactoryReady.get()) {
                    this.nccpCryptoFactoryReady.set(false);
                    Log.d("nf_drm", "MediaDrm failed, unregister device and logout user");
                    new BackgroundTask().execute(new Runnable() {
                        @Override
                        public void run() {
                            WidevineDrmManager.this.mUser.logoutUser();
                            Log.d("nf_drm", "Redo CDM provisioning");
                            WidevineDrmManager.this.init();
                        }
                    });
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    private void onReady() {
        Log.d("nf_drm", "NCCP Crypto Factory based on Widewine CDM is ready");
        this.cdmProvisioningCompleted();
        this.mCallback.drmReady();
    }
    
    private void reset() {
        synchronized (this.nccpCryptoFactoryReady) {
            this.nccpCryptoFactoryReady.set(false);
            PreferenceUtils.removePref(this.mContext, "nf_drm_system_id");
            PreferenceUtils.removePref(this.mContext, "nf_drm_cdm_keyset_id");
            PreferenceUtils.removePref(this.mContext, "nf_drm_kce_key_id");
            PreferenceUtils.removePref(this.mContext, "nf_drm_kch_key_id");
            this.closeSessionAndRemoveKeys(this.nccpCryptoFactoryCryptoSession.pendingSessionId);
            this.closeSessionAndRemoveKeys(this.nccpCryptoFactoryCryptoSession.sessionId);
            this.nccpCryptoFactoryCryptoSession.reset();
        }
    }
    
    private boolean restoreKeySetFromNccpCryptoFactoryDrmSession() {
        final String stringPref = PreferenceUtils.getStringPref(this.mContext, "nf_drm_cdm_keyset_id", null);
        if (stringPref == null) {
            Log.d("nf_drm", "NCCP crypto factory session key set ID not found, unable to restore DRM session");
            return false;
        }
        if (Log.isLoggable("nf_drm", 3)) {
            Log.d("nf_drm", "NCCP crypto factory key set ID found: " + stringPref);
        }
        String stringPref2;
        try {
            this.drm.restoreKeys(this.nccpCryptoFactoryCryptoSession.sessionId, stringPref.getBytes());
            stringPref2 = PreferenceUtils.getStringPref(this.mContext, "nf_drm_kce_key_id", null);
            if (stringPref2 == null) {
                Log.d("nf_drm", "kceKeyId not found, unable to restore DRM session");
                return false;
            }
        }
        catch (Throwable t) {
            Log.e("nf_drm", "Failed to restore keys to DRM session");
            return false;
        }
        this.nccpCryptoFactoryCryptoSession.kceKeyId = stringPref2.getBytes();
        final String stringPref3 = PreferenceUtils.getStringPref(this.mContext, "nf_drm_kch_key_id", null);
        if (stringPref3 == null) {
            Log.d("nf_drm", "kchKeyId not found, unable to restore DRM session");
            return false;
        }
        this.nccpCryptoFactoryCryptoSession.kchKeyId = stringPref3.getBytes();
        Log.d("nf_drm", "NCCP crypto factory key set ID restored to session.");
        return true;
    }
    
    private void showProperties() {
        if (Log.isLoggable("nf_drm", 3)) {
            Log.d("nf_drm", "vendor: " + this.drm.getPropertyString("vendor"));
            Log.d("nf_drm", "version: " + this.drm.getPropertyString("version"));
            Log.d("nf_drm", "description: " + this.drm.getPropertyString("description"));
            Log.d("nf_drm", "deviceId: " + Arrays.toString(this.drm.getPropertyByteArray("deviceUniqueId")));
            Log.d("nf_drm", "algorithms: " + this.drm.getPropertyString("algorithms"));
            Log.d("nf_drm", "security level: " + this.drm.getPropertyString("securityLevel"));
            Log.d("nf_drm", "system ID: " + this.drm.getPropertyString("systemId"));
            Log.i("nf_drm", "provisioningId: " + Arrays.toString(this.drm.getPropertyByteArray("provisioningUniqueId")));
        }
    }
    
    private void startNccpProvisioning() throws NotProvisionedException {
        Log.d("nf_drm", "Start NCCP provisioning...");
        this.nccpCryptoFactoryReady.set(false);
        final NccpResponseHandler nccpResponseHandler = new NccpResponseHandler() {
            @Override
            public void handle(final NccpTransaction nccpTransaction, final NccpResponse nccpResponse) {
                if (Log.isLoggable("nf_drm", 3)) {
                    Log.d("nf_drm", "Received NCCP response for CDM provisioning " + nccpResponse);
                }
                if (nccpResponse instanceof CdmProvisionNccpResponse) {
                    final CdmProvisionNccpResponse cdmProvisionNccpResponse = (CdmProvisionNccpResponse)nccpResponse;
                    if (!cdmProvisionNccpResponse.isSuccess()) {
                        Log.e("nf_drm", "CDM provisioning failed with  " + cdmProvisionNccpResponse.getError());
                        WidevineDrmManager.this.mCallback.drmError(-100);
                        if (cdmProvisionNccpResponse.getError() != null) {
                            WidevineDrmManager.this.mErrorLogging.logHandledException("Failed NCCP provisioning! " + cdmProvisionNccpResponse.getError().toString());
                            return;
                        }
                        WidevineDrmManager.this.mErrorLogging.logHandledException("Failed NCCP provisioning! NCCP error is null!");
                        return;
                    }
                    else {
                        try {
                            Log.d("nf_drm", "Setting kpe/kph");
                            WidevineDrmManager.this.updateKeyResponseForNccpSession(cdmProvisionNccpResponse.getKeyResponse(), cdmProvisionNccpResponse.getKcekeyId(), cdmProvisionNccpResponse.getKchkeyId());
                            Log.d("nf_drm", "Report that DRM is ready.");
                            WidevineDrmManager.this.cdmProvisioningCompleted();
                            WidevineDrmManager.this.mCallback.drmReady();
                            Log.d("nf_drm", "Reported that DRM is ready.");
                            return;
                        }
                        catch (Exception ex) {
                            Log.e("nf_drm", "Failed to provide key response exception ", ex);
                            WidevineDrmManager.this.mCallback.drmError(-100);
                            WidevineDrmManager.this.mErrorLogging.logHandledException(new Exception("Failed to provide key response exception", ex));
                            return;
                        }
                        catch (Throwable t) {
                            Log.e("nf_drm", "Failed to provide key response error ", t);
                            WidevineDrmManager.this.mCallback.drmError(-100);
                            WidevineDrmManager.this.mErrorLogging.logHandledException(new Exception("Failed to provide key response ", t));
                            return;
                        }
                    }
                }
                Log.e("nf_drm", "We did NOT received success!");
                WidevineDrmManager.this.mCallback.drmError(-100);
                WidevineDrmManager.this.mErrorLogging.logHandledException("Failed NCCP provisioning! Error on response.");
            }
        };
        if (Log.isLoggable("nf_drm", 3)) {
            if (this.nccpCryptoFactoryCryptoSession.sessionId != null) {
                Log.d("nf_drm", "Start NCCP provisioning, NCCP crypto factory session ID: " + new String(this.nccpCryptoFactoryCryptoSession.sessionId));
            }
            else {
                Log.d("nf_drm", "Start NCCP provisioning, NCCP crypto factory session ID: null");
            }
        }
        final MediaDrm$KeyRequest keyRequest = this.drm.getKeyRequest(this.nccpCryptoFactoryCryptoSession.sessionId, WidevineDrmManager.init, "application/xml", 2, new HashMap());
        this.dumpKeyReqyest(keyRequest.getData());
        ((AsyncTaskCompat<Void, Object, Object>)new NccpRequestTask(new CdmProvisionNccpTransaction(this.mContext, this.mConfiguration.getEsnProvider(), nccpResponseHandler, keyRequest.getData()))).execute(new Void[0]);
    }
    
    private void startWidewineProvisioning() {
        final MediaDrm$ProvisionRequest provisionRequest = this.drm.getProvisionRequest();
        new WidevineCDMProvisionRequestTask(provisionRequest.getData(), new WidewineProvisiongCallback() {
            final /* synthetic */ String val$url = provisionRequest.getDefaultUrl();
            
            @Override
            public void done(final byte[] array) {
                if (array != null) {
                    if (Log.isLoggable("nf_drm", 3)) {
                        Log.d("nf_drm", "Got CDM provisiong " + new String(array));
                    }
                    try {
                        WidevineDrmManager.this.drm.provideProvisionResponse(array);
                        WidevineDrmManager.this.init();
                        return;
                    }
                    catch (DeniedByServerException ex) {
                        Log.d("nf_drm", "Server declined Widewine provisioning request. Server URL: " + this.val$url, (Throwable)ex);
                        WidevineDrmManager.this.mCallback.drmError(-101);
                        WidevineDrmManager.this.mErrorLogging.logHandledException(new Exception("Server declined Widewine provisioning request. Server URL: " + this.val$url + ". Build: " + Build.DISPLAY, (Throwable)ex));
                        return;
                    }
                    catch (Throwable t) {
                        Log.d("nf_drm", "Fatal error on seting Widewine provisioning response", t);
                        WidevineDrmManager.this.mErrorLogging.logHandledException(new Exception("Fatal error on seting Widewine provisioning response received from URL: " + this.val$url + ". Build: " + Build.DISPLAY, t));
                        if (WidevineDrmManager.this.mCallback != null) {
                            WidevineDrmManager.this.mCallback.drmError(-100);
                        }
                        return;
                    }
                }
                Log.e("nf_drm", "Failed to get provisiong certificate");
                WidevineDrmManager.this.mCallback.drmError(-100);
                WidevineDrmManager.this.mErrorLogging.logHandledException("Failed to get provisiong certificate. Response is null from URL " + this.val$url);
            }
        }).execute((Object[])new String[] { provisionRequest.getDefaultUrl() });
    }
    
    private void updateKeyResponseForNccpSession(byte[] provideKeyResponse, final byte[] kceKeyId, final byte[] kchKeyId) throws DeniedByServerException, NotProvisionedException {
        Log.d("nf_drm", "Provide key response...");
        provideKeyResponse = this.drm.provideKeyResponse(this.nccpCryptoFactoryCryptoSession.sessionId, provideKeyResponse);
        Log.d("nf_drm", "Save keys...");
        if (provideKeyResponse == null) {
            Log.e("nf_drm", "Something is wrong, this should not happen! KeySetId is null!");
            throw new NotProvisionedException("Something is wrong, this should not happen! KeySetId is null!");
        }
        PreferenceUtils.putStringPref(this.mContext, "nf_drm_cdm_keyset_id", new String(provideKeyResponse));
        this.nccpCryptoFactoryCryptoSession.kceKeyId = kceKeyId;
        if (this.nccpCryptoFactoryCryptoSession.kceKeyId == null) {
            Log.e("nf_drm", "Something is wrong, this should not happen! KCE Key Id is null!");
            throw new NotProvisionedException("Something is wrong, this should not happen! KCE Key Id is null!");
        }
        PreferenceUtils.putStringPref(this.mContext, "nf_drm_kce_key_id", new String(this.nccpCryptoFactoryCryptoSession.kceKeyId));
        this.nccpCryptoFactoryCryptoSession.kchKeyId = kchKeyId;
        if (this.nccpCryptoFactoryCryptoSession.kchKeyId == null) {
            Log.e("nf_drm", "Something is wrong, this should not happen! KCH key Id is null!");
            throw new NotProvisionedException("Something is wrong, this should not happen! KCH Key Id is null!");
        }
        PreferenceUtils.putStringPref(this.mContext, "nf_drm_kch_key_id", new String(this.nccpCryptoFactoryCryptoSession.kchKeyId));
    }
    
    public boolean canExecuteRegistration(final DelayedRegistrationCallback mDelayedRegistration) {
        synchronized (this.nccpCryptoFactoryReady) {
            if (this.nccpCryptoFactoryReady.get()) {
                Log.d("nf_drm", "CDM provisioning was completed. Kpe/kph are set, just pass message through bridge.");
                return true;
            }
            Log.d("nf_drm", "CDM provisioning is NOT completed. Kpe/kph are NOT set, save registration data and execute them AFTER CDM provisioning is completed!");
            if (this.mDelayedRegistration != null) {
                Log.w("nf_drm", "Previous registration is still in queue");
            }
            this.mDelayedRegistration = mDelayedRegistration;
            return false;
        }
    }
    
    byte[] decrypt(byte[] unpadPerPKCS5Padding, final byte[] array) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        if (mediaDrmCryptoSession == null) {
            Log.w("nf_drm", "Session NOT found!");
            return new byte[0];
        }
        try {
            Log.d("nf_drm", "KCE key id: " + new String(this.nccpCryptoFactoryCryptoSession.kceKeyId));
            unpadPerPKCS5Padding = CryptoUtils.unpadPerPKCS5Padding(mediaDrmCryptoSession.decrypt(this.nccpCryptoFactoryCryptoSession.kceKeyId, unpadPerPKCS5Padding, array), 16);
            return unpadPerPKCS5Padding;
        }
        catch (Throwable t) {
            Log.e("nf_drm", "Failed to decrypt ", t);
            this.mediaDrmFailure(false);
            return new byte[0];
        }
    }
    
    public void destroy() {
        synchronized (this) {
            this.nccpCryptoFactoryReady.set(false);
            this.closeSessionAndRemoveKeys(this.nccpCryptoFactoryCryptoSession.pendingSessionId);
            this.closeSession(this.nccpCryptoFactoryCryptoSession.sessionId);
            if (this.drm != null) {
                this.drm.release();
                this.drm = null;
            }
        }
    }
    
    byte[] encrypt(byte[] array, final byte[] array2) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        if (mediaDrmCryptoSession == null) {
            Log.w("nf_drm", "Session NOT found!");
            return new byte[0];
        }
        try {
            Log.d("nf_drm", "KCE key id: " + new String(this.nccpCryptoFactoryCryptoSession.kceKeyId));
            array = CryptoUtils.padPerPKCS5Padding(array, 16);
            array = mediaDrmCryptoSession.encrypt(this.nccpCryptoFactoryCryptoSession.kceKeyId, array, array2);
            return array;
        }
        catch (Throwable t) {
            Log.e("nf_drm", "Failed to encrypt ", t);
            this.mediaDrmFailure(false);
            return new byte[0];
        }
    }
    
    public byte[] getDeviceId() {
        byte[] array;
        if (this.drm == null) {
            Log.e("nf_drm", "Session MediaDrm is null! It should NOT happen!");
            array = null;
        }
        else {
            final byte[] propertyByteArray = this.drm.getPropertyByteArray("deviceUniqueId");
            if (propertyByteArray == null) {
                Log.e("nf_drm", "MediaDrm device ID is null! It should NOT happen!");
                return propertyByteArray;
            }
            array = propertyByteArray;
            if (Log.isLoggable("nf_drm", 3)) {
                Log.d("nf_drm", "MediaDrm device ID is: " + new String(propertyByteArray));
                return propertyByteArray;
            }
        }
        return array;
    }
    
    public String getDeviceType() {
        String propertyString;
        if (this.drm == null) {
            Log.e("nf_drm", "Session MediaDrm is null! It should NOT happen!");
            propertyString = null;
        }
        else {
            final String s = propertyString = this.drm.getPropertyString("systemId");
            if (Log.isLoggable("nf_drm", 3)) {
                Log.d("nf_drm", "MediaDrm system ID is: " + s);
                return s;
            }
        }
        return propertyString;
    }
    
    public int getDrmType() {
        return 1;
    }
    
    byte[] getNccpSessionKeyRequest() {
        synchronized (this) {
            Log.d("nf_drm", "get NCCP session key request");
            try {
                final byte[] data = this.createKeyRequest().getData();
                this.dumpKeyReqyest(data);
                return data;
            }
            catch (Throwable t) {
                Log.e("nf_drm", "Failed to get key request", t);
                this.mediaDrmFailure(false);
                final byte[] data = new byte[0];
            }
        }
    }
    
    public void init() {
        if (this.isWidevinePluginChanged()) {
            PreferenceUtils.putStringPref(this.mContext, "nf_drm_system_id", this.getDeviceType());
            this.mediaDrmFailure(true);
        }
        else if (this.createNccpCryptoFactoryDrmSession()) {
            Log.d("nf_drm", "NCCP Crypto Factory session is created");
            this.afterWidewineProvisioning();
        }
    }
    
    public boolean isNccpCryptoFactoryReady() {
        return this.nccpCryptoFactoryReady.get();
    }
    
    public void onEvent(final MediaDrm mediaDrm, final byte[] array, final int n, final int n2, final byte[] array2) {
        if (n == 1) {
            Log.d("nf_drm", "Provisioning is required");
        }
        else {
            if (n == 2) {
                Log.d("nf_drm", "MediaDrm event: Key required");
                return;
            }
            if (n == 3) {
                Log.d("nf_drm", "MediaDrm event: Key expired");
                return;
            }
            if (n == 4 && Log.isLoggable("nf_drm", 3)) {
                Log.d("nf_drm", "MediaDrm event: Vendor defined: " + n);
            }
        }
    }
    
    public void resetCryptoFactory() {
        synchronized (this) {
            Log.d("nf_drm", "Reset crypto factory. Initiate CDM provisioning to get kpe/kph.");
            try {
                this.reset();
                Log.d("nf_drm", "Create a new crypto session");
                this.nccpCryptoFactoryCryptoSession.pendingSessionId = this.drm.openSession();
                Log.d("nf_drm", "Start NCCP provisioning...");
                final NccpResponseHandler nccpResponseHandler = new NccpResponseHandler() {
                    @Override
                    public void handle(final NccpTransaction nccpTransaction, final NccpResponse nccpResponse) {
                        if (Log.isLoggable("nf_drm", 3)) {
                            Log.d("nf_drm", "Received NCCP response for CDM provisioning " + nccpResponse);
                        }
                        if (nccpResponse instanceof CdmProvisionNccpResponse) {
                            final CdmProvisionNccpResponse cdmProvisionNccpResponse = (CdmProvisionNccpResponse)nccpResponse;
                            if (!cdmProvisionNccpResponse.isSuccess()) {
                                Log.e("nf_drm", "CDM provisioning failed with  " + cdmProvisionNccpResponse.getError());
                                WidevineDrmManager.this.handleNccpProvisioningFailure(true, true);
                                return;
                            }
                            try {
                                Log.d("nf_drm", "Setting kpe/kph");
                                WidevineDrmManager.this.updateNccpSessionKeyResponse(cdmProvisionNccpResponse.getKeyResponse(), cdmProvisionNccpResponse.getKcekeyId(), cdmProvisionNccpResponse.getKchkeyId());
                                WidevineDrmManager.this.cdmProvisioningCompleted();
                                Log.d("nf_drm", "Report that DRM is ready.");
                                if (WidevineDrmManager.this.mCallback != null) {
                                    WidevineDrmManager.this.mCallback.drmReady();
                                }
                                Log.d("nf_drm", "Reported that DRM is ready.");
                                return;
                            }
                            catch (Throwable t) {
                                Log.e("nf_drm", "Failed to provide key response ", t);
                                WidevineDrmManager.this.handleNccpProvisioningFailure(true, true);
                                return;
                            }
                        }
                        Log.e("nf_drm", "We did NOT received success!");
                        WidevineDrmManager.this.handleNccpProvisioningFailure(false, true);
                    }
                };
                final MediaDrm$KeyRequest keyRequest = this.drm.getKeyRequest(this.nccpCryptoFactoryCryptoSession.pendingSessionId, WidevineDrmManager.init, "application/xml", 2, new HashMap());
                this.dumpKeyReqyest(keyRequest.getData());
                ((AsyncTaskCompat<Void, Object, Object>)new NccpRequestTask(new CdmProvisionNccpTransaction(this.mContext, this.mConfiguration.getEsnProvider(), nccpResponseHandler, keyRequest.getData()))).execute(new Void[0]);
            }
            catch (Throwable t) {
                Log.e("nf_drm", "Not provisioned. It should not happen! Redo Widevine provisioning!", t);
                this.mediaDrmFailure(false);
            }
        }
    }
    
    byte[] sign(byte[] sign) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        if (mediaDrmCryptoSession == null) {
            Log.w("nf_drm", "Session NOT found!");
            return new byte[0];
        }
        try {
            Log.d("nf_drm", "KCH key id: " + new String(this.nccpCryptoFactoryCryptoSession.kchKeyId));
            sign = mediaDrmCryptoSession.sign(this.nccpCryptoFactoryCryptoSession.kchKeyId, sign);
            return sign;
        }
        catch (Throwable t) {
            Log.e("nf_drm", "Failed to sign message ", t);
            this.mediaDrmFailure(false);
            return new byte[0];
        }
    }
    
    boolean updateNccpSessionKeyResponse(final byte[] array, final byte[] array2, final byte[] array3) {
        synchronized (this) {
            Log.d("nf_drm", "Update key response");
            if (Log.isLoggable("nf_drm", 3)) {
                Log.d("nf_drm", "Kce Key ID" + new String(array2));
                Log.d("nf_drm", "Kch Key ID" + new String(array3));
            }
            boolean b = false;
            try {
                final byte[] pendingSessionId = this.nccpCryptoFactoryCryptoSession.pendingSessionId;
                if (pendingSessionId != null) {
                    Log.d("nf_drm", "Update key response for pending session id " + new String(pendingSessionId));
                    this.closeSessionAndRemoveKeys(this.nccpCryptoFactoryCryptoSession.activatePendingSessionId());
                }
                this.updateKeyResponseForNccpSession(array, array2, array3);
                b = true;
                return b;
            }
            catch (Throwable t) {
                Log.e("nf_drm", "We failed to update key response..." + t.getMessage() + ": ", t);
                this.mediaDrmFailure(false);
            }
        }
    }
    
    boolean verify(final byte[] array, final byte[] array2) {
        Log.logByteArray("nf_drm", "Verify message", array);
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        boolean verify;
        if (mediaDrmCryptoSession == null) {
            Log.w("nf_drm", "Session NOT found!");
            verify = false;
        }
        else {
            Log.d("nf_drm", "KCH key id: " + new String(this.nccpCryptoFactoryCryptoSession.kchKeyId));
            try {
                final boolean b = verify = mediaDrmCryptoSession.verify(this.nccpCryptoFactoryCryptoSession.kchKeyId, array, array2);
                if (Log.isLoggable("nf_drm", 3)) {
                    Log.d("nf_drm", "Messaage is verified: " + b);
                    return b;
                }
            }
            catch (Throwable t) {
                Log.e("nf_drm", "Failed to verify message ", t);
                this.mediaDrmFailure(false);
                return false;
            }
        }
        return verify;
    }
    
    private static class CryptoSession
    {
        public byte[] kceKeyId;
        public byte[] kchKeyId;
        public byte[] pendingSessionId;
        public byte[] sessionId;
        
        public byte[] activatePendingSessionId() {
            byte[] sessionId = null;
            synchronized (this) {
                if (this.pendingSessionId == null) {
                    Log.e("nf_drm", "Pending session does NOT exist! Do nothing!");
                }
                else {
                    Log.d("nf_drm", "Pending session does exist! Move pending to current session id and return old!");
                    sessionId = this.sessionId;
                    this.sessionId = this.pendingSessionId;
                    this.pendingSessionId = null;
                }
                return sessionId;
            }
        }
        
        public void reset() {
            synchronized (this) {
                this.pendingSessionId = null;
                this.sessionId = null;
                this.kceKeyId = null;
                this.kchKeyId = null;
            }
        }
    }
    
    public interface NccpProvisiongCallback
    {
        void done(final byte[] p0);
        
        void error();
    }
    
    public interface WidewineProvisiongCallback
    {
        void done(final byte[] p0);
    }
}
