// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import android.media.MediaDrm$ProvisionRequest;
import com.netflix.mediaclient.util.CryptoUtils;
import java.util.Arrays;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.media.UnsupportedSchemeException;
import android.media.MediaDrm$CryptoSession;
import android.util.Base64;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.media.NotProvisionedException;
import com.netflix.mediaclient.StatusCode;
import java.util.HashMap;
import android.media.MediaDrm$KeyRequest;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.Log;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import android.content.Context;
import android.media.MediaDrm;
import java.util.UUID;
import android.annotation.TargetApi;
import android.media.MediaDrm$OnEventListener;

@TargetApi(18)
public class WidevineDrmManager implements MediaDrm$OnEventListener, DrmManager
{
    public static final String KB_HELP_URL_FOR_CRYPTO_FAILURES = "https://help.netflix.com/en/node/14384";
    public static final String PROPERTY_SYSTEM_ID = "systemId";
    public static final String TAG;
    private static final UUID WIDEVINE_SCHEME;
    private static final byte[] init;
    private MediaDrm drm;
    private boolean isWidevineL3;
    private DrmManager$DrmReadyCallback mCallback;
    private Context mContext;
    private String mCurrentAccountId;
    private boolean mDrmSystemChanged;
    private ErrorLogging mErrorLogging;
    private AccountKeyMap mKeyIdsMap;
    private ServiceAgent$UserAgentInterface mUser;
    private AtomicBoolean mWidevineProvisioned;
    private WidevineDrmManager$CryptoSession nccpCryptoFactoryCryptoSession;
    
    static {
        TAG = WidevineDrmManager.class.getSimpleName();
        init = new byte[] { 10, 122, 0, 108, 56, 43 };
        WIDEVINE_SCHEME = new UUID(-1301668207276963122L, -6645017420763422227L);
    }
    
    WidevineDrmManager(final Context mContext, final ServiceAgent$UserAgentInterface mUser, final ErrorLogging mErrorLogging, final DrmManager$DrmReadyCallback mCallback, final boolean b) {
        this.mWidevineProvisioned = new AtomicBoolean(false);
        this.nccpCryptoFactoryCryptoSession = new WidevineDrmManager$CryptoSession(null);
        this.isWidevineL3 = false;
        if (mCallback == null) {
            throw new IllegalArgumentException();
        }
        this.mCallback = mCallback;
        this.mUser = mUser;
        this.mErrorLogging = mErrorLogging;
        this.mContext = mContext;
        this.drm = new MediaDrm(WidevineDrmManager.WIDEVINE_SCHEME);
        if (b) {
            Log.d(WidevineDrmManager.TAG, "Setting security level to L3");
            this.drm.setPropertyString("securityLevel", "L3");
            this.isWidevineL3 = true;
        }
        this.drm.setOnEventListener((MediaDrm$OnEventListener)this);
        this.mKeyIdsMap = new AccountKeyMap(this.mContext);
        this.showProperties();
        if (this.isWidevinePluginChanged()) {
            this.reset();
            this.mDrmSystemChanged = true;
        }
        PreferenceUtils.putStringPref(this.mContext, "nf_drm_system_id", this.getDeviceType());
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
        synchronized (this) {
            Log.d(WidevineDrmManager.TAG, "get NCCP session key request");
            this.closeCryptoSessions(this.nccpCryptoFactoryCryptoSession.pendingSessionId);
            Log.d(WidevineDrmManager.TAG, "Create a new crypto session");
            this.nccpCryptoFactoryCryptoSession.pendingSessionId = this.drm.openSession();
            return this.drm.getKeyRequest(this.nccpCryptoFactoryCryptoSession.pendingSessionId, WidevineDrmManager.init, "application/xml", 2, new HashMap());
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
                    this.mErrorLogging.logHandledException("Failed to created NCCP crypto factory DRM session " + t.getMessage());
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
    
    public static String getMediaDrmMaxSecurityLevel() {
        try {
            final MediaDrm mediaDrm = new MediaDrm(WidevineDrmManager.WIDEVINE_SCHEME);
            final String propertyString = mediaDrm.getPropertyString("securityLevel");
            Log.d(WidevineDrmManager.TAG, "Widevine default securityLevel [" + propertyString + "]");
            if (mediaDrm != null) {
                mediaDrm.release();
            }
            return propertyString;
        }
        catch (UnsupportedSchemeException ex) {
            return null;
        }
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
        }
        else {
            if (!stringPref.equals(deviceType)) {
                if (Log.isLoggable()) {
                    Log.d(WidevineDrmManager.TAG, "System ID changed from " + stringPref + " to " + deviceType);
                }
                return true;
            }
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "System ID did not changed: " + stringPref);
                return false;
            }
        }
        return false;
    }
    
    @SuppressLint({ "NewApi" })
    public static boolean isWidewineSupported() {
        return AndroidUtils.getAndroidVersion() >= 18 && MediaDrm.isCryptoSchemeSupported(WidevineDrmManager.WIDEVINE_SCHEME);
    }
    
    private void mediaDrmFailure(final StatusCode statusCode, final Throwable t) {
        while (true) {
            while (true) {
                Label_0151: {
                    synchronized (this) {
                        this.mErrorLogging.logHandledException(this.createMediaDrmErrorMessage(statusCode, t));
                        if (StatusCode.DRM_FAILURE_MEDIADRM_WIDEVINE_PLUGIN_CHANGED == statusCode) {
                            Log.d(WidevineDrmManager.TAG, "MediaDrm failed, unregister device and logout user");
                            new BackgroundTask().execute(new WidevineDrmManager$2(this));
                        }
                        else {
                            if (StatusCode.DRM_FAILURE_MEDIADRM_PROVIDE_KEY_RESPONSE != statusCode && StatusCode.DRM_FAILURE_MEDIADRM_KEYS_RESTORE_FAILED != statusCode) {
                                break Label_0151;
                            }
                            Log.d(WidevineDrmManager.TAG, "MediaDrm provide key update failed or restore keys failed. Unregister device, logout user, and kill app process after error is displayed.");
                            new BackgroundTask().execute(new WidevineDrmManager$3(this));
                            final Intent intent = new Intent("com.netflix.mediaclient.ui.error.ACTION_DISPLAY_ERROR");
                            intent.putExtra("status", statusCode.getValue());
                            intent.putExtra("message_id", 2131493376);
                            LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
                        }
                        return;
                    }
                }
                Log.d(WidevineDrmManager.TAG, "MediaDrm failed, fail back to legacy crypto scheme");
                PreferenceUtils.putBooleanPref(this.mContext, "disable_widevine", true);
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
    
    void clearKeys(final String s) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "clearKeys " + s);
            }
            this.mKeyIdsMap.removeCurrentKeyIds(s);
        }
    }
    
    byte[] decrypt(byte[] unpadPerPKCS5Padding, final byte[] array) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        if (mediaDrmCryptoSession == null) {
            Log.w(WidevineDrmManager.TAG, "decrypt - session NOT found!");
            return new byte[0];
        }
        if (this.nccpCryptoFactoryCryptoSession.kceKeyId == null) {
            Log.w(WidevineDrmManager.TAG, "decrypt - kce is null!");
            return new byte[0];
        }
        try {
            unpadPerPKCS5Padding = CryptoUtils.unpadPerPKCS5Padding(mediaDrmCryptoSession.decrypt(this.nccpCryptoFactoryCryptoSession.kceKeyId, unpadPerPKCS5Padding, array), 16);
            return unpadPerPKCS5Padding;
        }
        catch (Throwable t) {
            Log.e(WidevineDrmManager.TAG, "Failed to decrypt ", t);
            this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_DECRYPT, t);
            return new byte[0];
        }
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
    
    byte[] encrypt(byte[] array, final byte[] array2) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        if (mediaDrmCryptoSession == null) {
            Log.w(WidevineDrmManager.TAG, "encrypt - session NOT found!");
            return new byte[0];
        }
        if (this.nccpCryptoFactoryCryptoSession.kceKeyId == null) {
            Log.w(WidevineDrmManager.TAG, "encrypt - kce is null!");
            return new byte[0];
        }
        try {
            array = CryptoUtils.padPerPKCS5Padding(array, 16);
            array = mediaDrmCryptoSession.encrypt(this.nccpCryptoFactoryCryptoSession.kceKeyId, array, array2);
            return array;
        }
        catch (Throwable t) {
            Log.e(WidevineDrmManager.TAG, "Failed to encrypt ", t);
            this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_ENCRYPT, t);
            return new byte[0];
        }
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
        String s;
        if (this.drm == null) {
            Log.e(WidevineDrmManager.TAG, "Session MediaDrm is null! It should NOT happen!");
            s = null;
        }
        else {
            String s3;
            final String s2 = s3 = this.drm.getPropertyString("systemId");
            if (this.isWidevineL3) {
                s3 = s2 + "=L3";
            }
            s = s3;
            if (Log.isLoggable()) {
                Log.d(WidevineDrmManager.TAG, "MediaDrm system ID is: " + s3);
                return s3;
            }
        }
        return s;
    }
    
    public int getDrmType() {
        return 1;
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
    
    byte[] sign(byte[] sign) {
        final MediaDrm$CryptoSession mediaDrmCryptoSession = this.findMediaDrmCryptoSession();
        if (mediaDrmCryptoSession == null) {
            Log.w(WidevineDrmManager.TAG, "sign - session NOT found!");
            return new byte[0];
        }
        if (this.nccpCryptoFactoryCryptoSession.kchKeyId == null) {
            Log.w(WidevineDrmManager.TAG, "sign - kch is null!");
            return new byte[0];
        }
        try {
            sign = mediaDrmCryptoSession.sign(this.nccpCryptoFactoryCryptoSession.kchKeyId, sign);
            return sign;
        }
        catch (Throwable t) {
            Log.e(WidevineDrmManager.TAG, "Failed to sign message ", t);
            this.mediaDrmFailure(StatusCode.DRM_FAILURE_MEDIADRM_SIGN, t);
            return new byte[0];
        }
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
