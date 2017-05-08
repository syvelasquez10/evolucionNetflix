// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import android.media.MediaDrm$ProvisionRequest;
import com.netflix.mediaclient.util.CryptoUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import java.util.Arrays;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import android.media.MediaDrm$CryptoSession;
import android.util.Base64;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.StatusCode;
import android.media.NotProvisionedException;
import java.util.HashMap;
import android.media.MediaDrm$KeyRequest;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.MediaDrmUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.media.MediaDrm;
import android.annotation.TargetApi;
import android.media.MediaDrm$OnEventListener;

@TargetApi(18)
abstract class WidevineDrmManager implements MediaDrm$OnEventListener, DrmManager
{
    public static final String KB_HELP_URL_FOR_CRYPTO_FAILURES = "https://help.netflix.com/en/node/14384";
    public static final String TAG;
    private static final byte[] init;
    protected MediaDrm drm;
    protected DrmManager$DrmReadyCallback mCallback;
    protected ServiceAgent$ConfigurationAgentInterface mConfiguration;
    protected Context mContext;
    protected String mCurrentAccountId;
    protected boolean mDrmSystemChanged;
    protected IErrorHandler mErrorHandler;
    protected ErrorLogging mErrorLogging;
    protected AccountKeyMap mKeyIdsMap;
    protected ServiceAgent$UserAgentInterface mUser;
    protected AtomicBoolean mWidevineProvisioned;
    protected WidevineDrmManager$CryptoSession nccpCryptoFactoryCryptoSession;
    
    static {
        TAG = WidevineDrmManager.class.getSimpleName();
        init = new byte[] { 10, 122, 0, 108, 56, 43 };
    }
    
    WidevineDrmManager(final Context mContext, final ServiceAgent$UserAgentInterface mUser, final ServiceAgent$ConfigurationAgentInterface mConfiguration, final ErrorLogging mErrorLogging, final IErrorHandler mErrorHandler, final DrmManager$DrmReadyCallback mCallback) {
        this.mWidevineProvisioned = new AtomicBoolean(false);
        this.nccpCryptoFactoryCryptoSession = new WidevineDrmManager$CryptoSession(null);
        if (mCallback == null) {
            throw new IllegalArgumentException();
        }
        this.mCallback = mCallback;
        this.mUser = mUser;
        this.mConfiguration = mConfiguration;
        this.mErrorLogging = mErrorLogging;
        this.mErrorHandler = mErrorHandler;
        this.mContext = mContext;
        this.drm = new MediaDrm(MediaDrmUtils.WIDEVINE_SCHEME);
        this.setSecurityLevel();
        this.verifySystemId();
        this.drm.setOnEventListener((MediaDrm$OnEventListener)this);
        this.mKeyIdsMap = new AccountKeyMap(this.mContext);
        this.showProperties();
        if (this.isWidevinePluginChanged()) {
            Log.d(WidevineDrmManager.TAG, "Widevine plugin is changed, reset...");
            this.reset();
            this.mDrmSystemChanged = true;
        }
        PreferenceUtils.putStringPref(this.mContext, "nf_drm_system_id", this.getDeviceType());
        PreferenceUtils.putStringPref(this.mContext, "nf_drm_crypto_provider", this.getCryptoProvider().name());
    }
    
    private void afterWidewineProvisioning() {
        this.mCallback.drmReady();
    }
    
    private void closeCryptoSessions(final byte[] array) {
        if (array == null || this.drm == null) {
            return;
        }
        Log.d(WidevineDrmManager.TAG, "closeCryptoSessions");
        try {
            this.drm.closeSession(array);
        }
        catch (Throwable t) {
            Log.w(WidevineDrmManager.TAG, "closeCryptoSessions failed !", t);
        }
    }
    
    private void closeSessionAndRemoveKeys(final byte[] array) {
        synchronized (this) {
            this.removeSessionKeys(array);
            this.closeCryptoSessions(array);
        }
    }
    
    private MediaDrm$KeyRequest createKeyRequest() {
        // monitorenter(this)
        try {
            Log.d(WidevineDrmManager.TAG, "get NCCP session key request");
            this.closeCryptoSessions(this.nccpCryptoFactoryCryptoSession.pendingSessionId);
            try {
                Log.d(WidevineDrmManager.TAG, "Create a new crypto session");
                this.nccpCryptoFactoryCryptoSession.pendingSessionId = this.drm.openSession();
                final WidevineDrmManager widevineDrmManager = this;
                final MediaDrm mediaDrm = widevineDrmManager.drm;
                final WidevineDrmManager widevineDrmManager2 = this;
                final WidevineDrmManager$CryptoSession widevineDrmManager$CryptoSession = widevineDrmManager2.nccpCryptoFactoryCryptoSession;
                final byte[] array = widevineDrmManager$CryptoSession.pendingSessionId;
                final byte[] array2 = WidevineDrmManager.init;
                final String s = "application/xml";
                final int n = 2;
                final HashMap hashMap = new HashMap();
                final MediaDrm$KeyRequest keyRequest = mediaDrm.getKeyRequest(array, array2, s, n, (HashMap)hashMap);
                return keyRequest;
            }
            catch (NotProvisionedException ex) {
                this.mErrorLogging.logHandledException("NotProvisionedException::createKeyRequest::openSession:: Failed to open new session " + ex.getMessage());
                throw ex;
            }
            catch (Throwable t) {
                this.mErrorLogging.logHandledException("createKeyRequest::openSession Failed to open new session " + t.getMessage());
                throw t;
            }
        }
        finally {}
        try {
            final WidevineDrmManager widevineDrmManager = this;
            final MediaDrm mediaDrm = widevineDrmManager.drm;
            final WidevineDrmManager widevineDrmManager2 = this;
            final WidevineDrmManager$CryptoSession widevineDrmManager$CryptoSession = widevineDrmManager2.nccpCryptoFactoryCryptoSession;
            final byte[] array = widevineDrmManager$CryptoSession.pendingSessionId;
            final byte[] array2 = WidevineDrmManager.init;
            final String s = "application/xml";
            final int n = 2;
            final HashMap hashMap = new HashMap();
            final MediaDrm$KeyRequest keyRequest2;
            final MediaDrm$KeyRequest keyRequest = keyRequest2 = mediaDrm.getKeyRequest(array, array2, s, n, (HashMap)hashMap);
            return keyRequest2;
        }
        catch (NotProvisionedException ex2) {
            this.mErrorLogging.logHandledException("NotProvisionedException::createKeyRequest::getKeyRequest:: Failed to get key request " + ex2.getMessage());
            throw ex2;
        }
        catch (Throwable t2) {
            this.mErrorLogging.logHandledException("createKeyRequest::getKeyRequest:: Failed to get key request " + t2.getMessage());
            throw t2;
        }
    }
    
    private String createMediaDrmErrorMessage(final StatusCode statusCode, final Throwable t) {
        final StringBuilder sb = new StringBuilder("MediaDrm failure: ");
        sb.append(statusCode.name()).append(". Exception: ");
        if (t == null) {
            sb.append(" init failure: security level changed");
        }
        else {
            sb.append(android.util.Log.getStackTraceString(t));
        }
        return sb.toString();
    }
    
    private boolean createNccpCryptoFactoryDrmSession() {
        while (true) {
            Label_0148: {
                try {
                    this.nccpCryptoFactoryCryptoSession.sessionId = this.drm.openSession();
                    if (Log.isLoggable()) {
                        if (this.nccpCryptoFactoryCryptoSession.sessionId == null) {
                            break Label_0148;
                        }
                        Log.d(WidevineDrmManager.TAG, "Device is provisioned. NCCP crypto factory session ID: " + new String(this.nccpCryptoFactoryCryptoSession.sessionId));
                    }
                    return true;
                }
                catch (NotProvisionedException ex) {
                    Log.e(WidevineDrmManager.TAG, "Device is not provisioned, start provisioning workflow!", (Throwable)ex);
                    this.startWidewineProvisioning();
                    return false;
                }
                catch (Throwable t) {
                    Log.e(WidevineDrmManager.TAG, "Fatal error, can not recover!", t);
                    this.mCallback.drmError(CommonStatus.DRM_FAILURE_CDM);
                    this.mErrorLogging.logHandledException("openSession:: Failed to created NCCP crypto factory DRM session " + t.getMessage());
                    return false;
                }
            }
            Log.d(WidevineDrmManager.TAG, "Device is provisioned. NCCP crypto factory session ID: null");
            return true;
        }
    }
    
    private void dumpKeyReqyest(final byte[] array) {
        if (array != null) {
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "key request created: " + Base64.encodeToString(array, 2));
            }
            return;
        }
        Log.w(WidevineDrmManager.TAG, "key request returned null");
    }
    
    private MediaDrm$CryptoSession findMediaDrmCryptoSession() {
        final byte[] sessionId = this.nccpCryptoFactoryCryptoSession.sessionId;
        if (sessionId == null) {
            return null;
        }
        try {
            return this.drm.getCryptoSession(sessionId, getCipherAlgorithm(), getMacAlgorithm());
        }
        catch (Exception ex) {
            Log.e(WidevineDrmManager.TAG, "Failed findMediaDrmCryptoSession ", ex);
            return null;
        }
    }
    
    private static String getCipherAlgorithm() {
        return "AES/CBC/NoPadding";
    }
    
    private static String getMacAlgorithm() {
        return "HmacSHA256";
    }
    
    private void handleCryptoFallback() {
        if (this.getCryptoProvider() == CryptoProvider.WIDEVINE_L1) {
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "MediaDrm failed for Widevine L1, fail back to legacy crypto scheme " + this.mConfiguration.shouldForceLegacyCrypto());
            }
            PreferenceUtils.putBooleanPref(this.mContext, "disable_widevine", true);
            return;
        }
        if (this.getCryptoProvider() == CryptoProvider.WIDEVINE_L3) {
            Log.d(WidevineDrmManager.TAG, "MediaDrm failed for Widevine L3, fail back to legacy crypto scheme ");
            PreferenceUtils.putBooleanPref(this.mContext, "disable_widevine_l3", true);
            return;
        }
        Log.e(WidevineDrmManager.TAG, "Crypto provider was not supported for this error " + this.getCryptoProvider());
    }
    
    private boolean isValidKeyIds(final AccountKeyMap$KeyIds accountKeyMap$KeyIds, final String s, final String s2) {
        return accountKeyMap$KeyIds != null && StringUtils.isNotEmpty(accountKeyMap$KeyIds.getKceKeyId()) && StringUtils.isNotEmpty(accountKeyMap$KeyIds.getKchKeyId()) && StringUtils.isNotEmpty(accountKeyMap$KeyIds.getKeySetId()) && ((StringUtils.isEmpty(s2) && StringUtils.isEmpty(s)) || (accountKeyMap$KeyIds.getKchKeyId().equals(s2) && accountKeyMap$KeyIds.getKceKeyId().equals(s)));
    }
    
    private boolean isWidevinePluginChanged() {
        final String stringPref = PreferenceUtils.getStringPref(this.mContext, "nf_drm_system_id", null);
        final String deviceType = this.getDeviceType();
        if (stringPref == null) {
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "System ID was not saved, user is not logged in, no need to report that plugin is changed: " + stringPref);
            }
            return false;
        }
        CryptoProvider cryptoProvider;
        if ((cryptoProvider = CryptoProvider.fromName(PreferenceUtils.getStringPref(this.mContext, "nf_drm_crypto_provider", null))) == null) {
            if (DrmManagerRegistry.isLegacyDrmSystem(stringPref)) {
                Log.d(WidevineDrmManager.TAG, "Previous crypto provider was legacy...");
                cryptoProvider = CryptoProvider.LEGACY;
            }
            else {
                Log.d(WidevineDrmManager.TAG, "Previous crypto provider was Widevine L1...");
                cryptoProvider = CryptoProvider.WIDEVINE_L1;
            }
        }
        if (Log.isLoggable()) {
            Log.d(WidevineDrmManager.TAG, "System ID was " + stringPref + " and now is " + deviceType);
            Log.d(WidevineDrmManager.TAG, "Crypto provider was  " + cryptoProvider + " and now is " + this.getCryptoProvider());
        }
        if (!stringPref.equals(deviceType)) {
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "System ID changed from " + stringPref + " to " + deviceType + ", report plugin changed");
            }
            return true;
        }
        if (Log.isLoggable()) {
            Log.d(WidevineDrmManager.TAG, "System ID did not changed: " + stringPref + ", check security level");
        }
        if (this.getCryptoProvider() == cryptoProvider) {
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "Same crypto provider " + cryptoProvider + ". No change!");
            }
            return false;
        }
        if (Log.isLoggable()) {
            Log.d(WidevineDrmManager.TAG, "Crypto provider is changed from " + cryptoProvider + " to " + this.getCryptoProvider());
        }
        return true;
    }
    
    private void mediaDrmFailure(final StatusCode statusCode, final Throwable t) {
        while (true) {
            // monitorenter(this)
            final WidevineDrmManager$2 widevineDrmManager$2 = null;
            while (true) {
                Label_0117: {
                    try {
                        this.mErrorLogging.logHandledException(this.createMediaDrmErrorMessage(statusCode, t));
                        Runnable runnable;
                        if (StatusCode.DRM_FAILURE_MEDIADRM_WIDEVINE_PLUGIN_CHANGED == statusCode) {
                            Log.d(WidevineDrmManager.TAG, "MediaDrm failed, unregister device and logout user");
                            runnable = new WidevineDrmManager$2(this);
                        }
                        else {
                            if (StatusCode.DRM_FAILURE_MEDIADRM_PROVIDE_KEY_RESPONSE != statusCode && StatusCode.DRM_FAILURE_MEDIADRM_KEYS_RESTORE_FAILED != statusCode) {
                                break Label_0117;
                            }
                            Log.d(WidevineDrmManager.TAG, "MediaDrm provide key update failed or restore keys failed. Unregister device, logout user, and kill app process after error is displayed.");
                            runnable = new WidevineDrmManager$3(this);
                        }
                        this.mErrorHandler.addError(new WidevineErrorDescriptor(this.mContext, statusCode, runnable, 2131231063));
                        return;
                    }
                    finally {
                    }
                    // monitorexit(this)
                }
                this.handleCryptoFallback();
                Runnable runnable = widevineDrmManager$2;
                continue;
            }
        }
    }
    
    private void removeSessionKeys(final byte[] array) {
        if (array == null || this.drm == null) {
            return;
        }
        Log.d(WidevineDrmManager.TAG, "removeSessionKeys");
        try {
            this.drm.removeKeys(array);
        }
        catch (Exception ex) {
            Log.e(WidevineDrmManager.TAG, "removeSessionKeys ", ex);
        }
    }
    
    private void reset() {
        PreferenceUtils.removePref(this.mContext, "nf_drm_system_id");
        PreferenceUtils.removePref(this.mContext, "nf_drm_crypto_provider");
        this.closeSessionAndRemoveKeys(this.nccpCryptoFactoryCryptoSession.pendingSessionId);
        this.closeSessionAndRemoveKeys(this.nccpCryptoFactoryCryptoSession.sessionId);
        this.nccpCryptoFactoryCryptoSession.reset();
        this.mKeyIdsMap.clearMap();
    }
    
    private boolean restoreKeysToSession(final AccountKeyMap$KeyIds accountKeyMap$KeyIds) {
        try {
            this.closeCryptoSessions(this.nccpCryptoFactoryCryptoSession.sessionId);
            this.nccpCryptoFactoryCryptoSession.sessionId = this.drm.openSession();
            this.drm.restoreKeys(this.nccpCryptoFactoryCryptoSession.sessionId, accountKeyMap$KeyIds.getKeySetId().getBytes());
            this.nccpCryptoFactoryCryptoSession.kceKeyId = accountKeyMap$KeyIds.getKceKeyId().getBytes();
            this.nccpCryptoFactoryCryptoSession.kchKeyId = accountKeyMap$KeyIds.getKchKeyId().getBytes();
            Log.d(WidevineDrmManager.TAG, "restoreKeysToSession succeeded.");
            return true;
        }
        catch (Throwable t) {
            Log.e(WidevineDrmManager.TAG, "Failed to restore keys to DRM session");
            this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_KEYS_RESTORE_FAILED, t);
            this.mErrorLogging.logHandledException("Failed to restore keys to DRM session " + t.getMessage());
            return false;
        }
    }
    
    private void showProperties() {
        if (Log.isLoggable()) {
            Log.d(WidevineDrmManager.TAG, "vendor: " + this.drm.getPropertyString("vendor"));
            Log.d(WidevineDrmManager.TAG, "version: " + this.drm.getPropertyString("version"));
            Log.d(WidevineDrmManager.TAG, "description: " + this.drm.getPropertyString("description"));
            Log.d(WidevineDrmManager.TAG, "deviceId: " + Arrays.toString(this.drm.getPropertyByteArray("deviceUniqueId")));
            Log.d(WidevineDrmManager.TAG, "algorithms: " + this.drm.getPropertyString("algorithms"));
            Log.d(WidevineDrmManager.TAG, "security level: " + this.drm.getPropertyString("securityLevel"));
            Log.d(WidevineDrmManager.TAG, "system ID: " + this.drm.getPropertyString("systemId"));
            Log.i(WidevineDrmManager.TAG, "provisioningId: " + Arrays.toString(this.drm.getPropertyByteArray("provisioningUniqueId")));
        }
    }
    
    private void startWidewineProvisioning() {
        Object o = this.mWidevineProvisioned;
        synchronized (o) {
            this.mWidevineProvisioned.set(false);
            // monitorexit(o)
            o = this.drm.getProvisionRequest();
            new WidevineCDMProvisionRequestTask(((MediaDrm$ProvisionRequest)o).getData(), new WidevineDrmManager$1(this, ((MediaDrm$ProvisionRequest)o).getDefaultUrl())).execute((Object[])new String[] { ((MediaDrm$ProvisionRequest)o).getDefaultUrl() });
        }
    }
    
    private void updateKeyResponseForNccpSession(byte[] provideKeyResponse, final byte[] kceKeyId, final byte[] kchKeyId) {
        Log.d(WidevineDrmManager.TAG, "Provide key response...");
        provideKeyResponse = this.drm.provideKeyResponse(this.nccpCryptoFactoryCryptoSession.sessionId, provideKeyResponse);
        Log.d(WidevineDrmManager.TAG, "Save keys...");
        if (provideKeyResponse == null) {
            Log.e(WidevineDrmManager.TAG, "Something is wrong, this should not happen! KeySetId is null!");
            throw new NotProvisionedException("Something is wrong, this should not happen! KeySetId is null!");
        }
        this.nccpCryptoFactoryCryptoSession.kceKeyId = kceKeyId;
        this.nccpCryptoFactoryCryptoSession.kchKeyId = kchKeyId;
        this.mKeyIdsMap.addCurrentKeyIds(new String(provideKeyResponse), new String(kceKeyId), new String(kchKeyId));
        this.mDrmSystemChanged = false;
    }
    
    private void verifySystemId() {
        final String deviceType = this.getDeviceType();
        if (StringUtils.isEmpty(deviceType)) {
            final IllegalStateException ex = new IllegalStateException("Empty system ID!");
            ErrorLoggingManager.logHandledException(ex);
            throw ex;
        }
        final int length = deviceType.trim().length();
        if (length > 5) {
            final IllegalStateException ex2 = new IllegalStateException("System ID is invalid. Its length is " + length);
            ErrorLoggingManager.logHandledException(ex2);
            throw ex2;
        }
        Log.d(WidevineDrmManager.TAG, "Valid System ID.");
    }
    
    void clearKeys(final String s) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "clearKeys " + s);
            }
            this.mKeyIdsMap.removeCurrentKeyIds(s);
        }
    }
    
    byte[] decrypt(final byte[] array, final byte[] array2) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        byte[] unpadPerPKCS5Padding;
        if (mediaDrmCryptoSession == null) {
            Log.w(WidevineDrmManager.TAG, "decrypt - session NOT found!");
            unpadPerPKCS5Padding = new byte[0];
        }
        else {
            if (this.nccpCryptoFactoryCryptoSession.kceKeyId == null) {
                Log.w(WidevineDrmManager.TAG, "decrypt - kce is null!");
                return new byte[0];
            }
            try {
                final byte[] array3 = unpadPerPKCS5Padding = CryptoUtils.unpadPerPKCS5Padding(mediaDrmCryptoSession.decrypt(this.nccpCryptoFactoryCryptoSession.kceKeyId, array, array2), 16);
                if (Log.isLoggable()) {
                    Log.d(WidevineDrmManager.TAG, "decrypt input size " + array.length + ", iv size " + array2.length + ", output size " + array3.length);
                    return array3;
                }
            }
            catch (Throwable t) {
                Log.e(WidevineDrmManager.TAG, "Failed to decrypt ", t);
                this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_DECRYPT, t);
                return new byte[0];
            }
        }
        return unpadPerPKCS5Padding;
    }
    
    public void destroy() {
        synchronized (this) {
            this.mWidevineProvisioned.set(false);
            this.closeSessionAndRemoveKeys(this.nccpCryptoFactoryCryptoSession.pendingSessionId);
            this.closeCryptoSessions(this.nccpCryptoFactoryCryptoSession.sessionId);
            if (this.drm != null) {
                this.drm.release();
                this.drm = null;
            }
        }
    }
    
    byte[] encrypt(final byte[] array, final byte[] array2) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        byte[] encrypt;
        if (mediaDrmCryptoSession == null) {
            Log.w(WidevineDrmManager.TAG, "encrypt - session NOT found!");
            encrypt = new byte[0];
        }
        else {
            if (this.nccpCryptoFactoryCryptoSession.kceKeyId == null) {
                Log.w(WidevineDrmManager.TAG, "encrypt - kce is null!");
                return new byte[0];
            }
            try {
                final byte[] array3 = encrypt = mediaDrmCryptoSession.encrypt(this.nccpCryptoFactoryCryptoSession.kceKeyId, CryptoUtils.padPerPKCS5Padding(array, 16), array2);
                if (Log.isLoggable()) {
                    Log.d(WidevineDrmManager.TAG, "encrypt input size " + array.length + ", iv size " + array2.length + ", output size " + array3.length);
                    return array3;
                }
            }
            catch (Throwable t) {
                Log.e(WidevineDrmManager.TAG, "Failed to encrypt ", t);
                this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_ENCRYPT, t);
                return new byte[0];
            }
        }
        return encrypt;
    }
    
    public byte[] getDeviceId() {
        byte[] array;
        if (this.drm == null) {
            Log.e(WidevineDrmManager.TAG, "Session MediaDrm is null! It should NOT happen!");
            array = null;
        }
        else {
            final byte[] propertyByteArray = this.drm.getPropertyByteArray("deviceUniqueId");
            if (propertyByteArray == null) {
                Log.e(WidevineDrmManager.TAG, "MediaDrm device ID is null! It should NOT happen!");
                return propertyByteArray;
            }
            array = propertyByteArray;
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "MediaDrm device ID is: " + new String(propertyByteArray));
                return propertyByteArray;
            }
        }
        return array;
    }
    
    public String getDeviceType() {
        String propertyString;
        if (this.drm == null) {
            Log.e(WidevineDrmManager.TAG, "Session MediaDrm is null! It should NOT happen!");
            propertyString = null;
        }
        else {
            final String s = propertyString = this.drm.getPropertyString("systemId");
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "MediaDrm system ID is: " + s);
                return s;
            }
        }
        return propertyString;
    }
    
    byte[] getNccpSessionKeyRequest() {
        synchronized (this) {
            Log.d(WidevineDrmManager.TAG, "get NCCP session key request");
            try {
                final byte[] data = this.createKeyRequest().getData();
                this.dumpKeyReqyest(data);
                return data;
            }
            catch (Throwable t) {
                Log.e(WidevineDrmManager.TAG, "Failed to get key request", t);
                this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_GET_KEY_REQUEST, t);
                final byte[] data = new byte[0];
            }
        }
    }
    
    public void init() {
        if (this.isWidevinePluginChanged()) {
            PreferenceUtils.putStringPref(this.mContext, "nf_drm_system_id", this.getDeviceType());
            PreferenceUtils.putStringPref(this.mContext, "nf_drm_crypto_provider", this.getCryptoProvider().name());
            this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_WIDEVINE_PLUGIN_CHANGED, null);
        }
        else if (this.createNccpCryptoFactoryDrmSession()) {
            Log.d(WidevineDrmManager.TAG, "NCCP Crypto Factory session is created");
            this.afterWidewineProvisioning();
        }
    }
    
    public void onEvent(final MediaDrm mediaDrm, final byte[] array, final int n, final int n2, final byte[] array2) {
        if (n == 1) {
            Log.d(WidevineDrmManager.TAG, "Provisioning is required");
        }
        else {
            if (n == 2) {
                Log.d(WidevineDrmManager.TAG, "MediaDrm event: Key required");
                return;
            }
            if (n == 3) {
                Log.d(WidevineDrmManager.TAG, "MediaDrm event: Key expired");
                return;
            }
            if (n == 4 && Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "MediaDrm event: Vendor defined: " + n);
            }
        }
    }
    
    boolean restoreKeys(final String mCurrentAccountId, final String s, final String s2) {
        boolean b = true;
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "restoreKeys for " + mCurrentAccountId + ",kceKid: " + s + ",kchKid: " + s2);
            }
            if (mCurrentAccountId.equals(this.mCurrentAccountId)) {
                Log.d(WidevineDrmManager.TAG, "restoreKeys already loaded");
            }
            else {
                final AccountKeyMap$KeyIds restoreKeyIdsForAccount = this.mKeyIdsMap.restoreKeyIdsForAccount(mCurrentAccountId);
                this.mCurrentAccountId = mCurrentAccountId;
                if (!this.mDrmSystemChanged) {
                    b = (this.isValidKeyIds(restoreKeyIdsForAccount, s, s2) && this.restoreKeysToSession(restoreKeyIdsForAccount));
                }
            }
            return b;
        }
    }
    
    protected abstract void setSecurityLevel();
    
    byte[] sign(final byte[] array) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        byte[] sign;
        if (mediaDrmCryptoSession == null) {
            Log.w(WidevineDrmManager.TAG, "sign - session NOT found!");
            sign = new byte[0];
        }
        else {
            if (this.nccpCryptoFactoryCryptoSession.kchKeyId == null) {
                Log.w(WidevineDrmManager.TAG, "sign - kch is null!");
                return new byte[0];
            }
            try {
                final byte[] array2 = sign = mediaDrmCryptoSession.sign(this.nccpCryptoFactoryCryptoSession.kchKeyId, array);
                if (Log.isLoggable()) {
                    Log.d(WidevineDrmManager.TAG, "sign input size " + array.length + ", output size " + array2.length);
                    return array2;
                }
            }
            catch (Throwable t) {
                Log.e(WidevineDrmManager.TAG, "Failed to sign message ", t);
                this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_SIGN, t);
                return new byte[0];
            }
        }
        return sign;
    }
    
    boolean updateNccpSessionKeyResponse(final byte[] array, final byte[] array2, final byte[] array3, final String s) {
    Label_0042_Outer:
        while (true) {
            boolean b = false;
            while (true) {
                while (true) {
                    final byte[] array4;
                    Label_0189: {
                        synchronized (this) {
                            if (Log.isLoggable()) {
                                Log.d(WidevineDrmManager.TAG, "Update key response for account " + s);
                            }
                            break Label_0189;
                            try {
                                final byte[] pendingSessionId = this.nccpCryptoFactoryCryptoSession.pendingSessionId;
                                if (pendingSessionId != null) {
                                    if (Log.isLoggable()) {
                                        Log.d(WidevineDrmManager.TAG, "Update key response for pending session id " + new String(pendingSessionId));
                                    }
                                    final byte[] activatePendingSessionId = this.nccpCryptoFactoryCryptoSession.activatePendingSessionId();
                                    if (s.equals(this.mCurrentAccountId)) {
                                        this.closeSessionAndRemoveKeys(activatePendingSessionId);
                                    }
                                    else {
                                        this.closeCryptoSessions(activatePendingSessionId);
                                    }
                                }
                                this.updateKeyResponseForNccpSession(array4, array2, array3);
                                b = true;
                            }
                            catch (Throwable t) {
                                Log.e(WidevineDrmManager.TAG, "We failed to update key response...", t);
                                this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_PROVIDE_KEY_RESPONSE, t);
                            }
                            return b;
                            Log.e(WidevineDrmManager.TAG, "Update key response has invlaid input");
                            return b;
                        }
                    }
                    if (array4 != null && array2 != null && array3 != null) {
                        continue Label_0042_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    boolean verify(final byte[] array, final byte[] array2) {
        boolean verify = false;
        Log.logByteArray(WidevineDrmManager.TAG, "Verify message", array);
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        if (mediaDrmCryptoSession == null) {
            Log.w(WidevineDrmManager.TAG, "verify - session NOT found!");
        }
        else {
            if (this.nccpCryptoFactoryCryptoSession.kchKeyId == null) {
                Log.w(WidevineDrmManager.TAG, "verify - kch is null!");
                return false;
            }
            try {
                final boolean b = verify = mediaDrmCryptoSession.verify(this.nccpCryptoFactoryCryptoSession.kchKeyId, array, array2);
                if (Log.isLoggable()) {
                    Log.d(WidevineDrmManager.TAG, "Messaage is verified: " + b);
                    return b;
                }
            }
            catch (Throwable t) {
                Log.e(WidevineDrmManager.TAG, "Failed to verify message ", t);
                this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_VERIFY, t);
                return false;
            }
        }
        return verify;
    }
}
