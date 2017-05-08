// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.crypto;

import android.media.MediaDrm$ProvisionRequest;
import android.annotation.SuppressLint;
import android.media.MediaDrm;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.media.NotProvisionedException;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.util.MediaDrmUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.UUID;
import android.annotation.TargetApi;

@TargetApi(18)
public abstract class MSLWidevineCryptoManager extends BaseCryptoManager
{
    public static final String PROPERTY_SYSTEM_ID = "systemId";
    protected static final String TAG = "nf_msl";
    public static final UUID WIDEVINE_SCHEME;
    private AtomicBoolean mWidevineProvisioned;
    
    static {
        WIDEVINE_SCHEME = MediaDrmUtils.WIDEVINE_SCHEME;
    }
    
    MSLWidevineCryptoManager(final Context context, final CryptoProvider cryptoProvider, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final CryptoManager$DrmReadyCallback cryptoManager$DrmReadyCallback) {
        super(context, cryptoProvider, serviceAgent$ConfigurationAgentInterface, cryptoManager$DrmReadyCallback);
        this.mWidevineProvisioned = new AtomicBoolean(false);
    }
    
    private void handleCryptoFallback() {
        if (this.getCryptoProvider() == CryptoProvider.WIDEVINE_L1) {
            Log.d("nf_msl", "MediaDrm failed for Widevine L1, fail back to legacy crypto scheme %b", this.mConfiguration.shouldForceLegacyCrypto());
            PreferenceUtils.putBooleanPref(this.mContext, "disable_widevine", true);
            return;
        }
        if (this.getCryptoProvider() == CryptoProvider.WIDEVINE_L3) {
            Log.d("nf_msl", "MediaDrm failed for Widevine L3, fail back to legacy crypto scheme ");
            PreferenceUtils.putBooleanPref(this.mContext, "nf_disable_widevine_l3", true);
            return;
        }
        Log.e("nf_msl", "Crypto provider was not supported for this error " + this.getCryptoProvider());
    }
    
    private boolean isDeviceProvisioned() {
        try {
            this.mDrm.closeSession(this.mDrm.openSession());
            Log.d("nf_msl", "Ready!");
            this.mCallback.drmReady();
            return true;
        }
        catch (NotProvisionedException ex) {
            Log.e("nf_msl", (Throwable)ex, "Device is not provisioned, start provisioning workflow!", new Object[0]);
            this.startWidewineProvisioning();
            return false;
        }
        catch (Throwable t) {
            Log.e("nf_msl", t, "Fatal error, can not recover!", new Object[0]);
            this.mCallback.drmError(CommonStatus.DRM_FAILURE_CDM);
            return false;
        }
    }
    
    @SuppressLint({ "NewApi" })
    public static boolean isWidewineSupported() {
        return AndroidUtils.getAndroidVersion() >= 18 && MediaDrm.isCryptoSchemeSupported(MSLWidevineCryptoManager.WIDEVINE_SCHEME);
    }
    
    private void startWidewineProvisioning() {
        Object o = this.mWidevineProvisioned;
        synchronized (o) {
            this.mWidevineProvisioned.set(false);
            // monitorexit(o)
            o = this.mDrm.getProvisionRequest();
            new WidevineCDMProvisionRequestTask(((MediaDrm$ProvisionRequest)o).getData(), new MSLWidevineCryptoManager$1(this, ((MediaDrm$ProvisionRequest)o).getDefaultUrl())).execute((Object[])new String[] { ((MediaDrm$ProvisionRequest)o).getDefaultUrl() });
        }
    }
    
    @Override
    public void destroy() {
        synchronized (this) {
            super.destroy();
            this.mWidevineProvisioned.set(false);
        }
    }
    
    public CryptoProvider getCryptoProvider() {
        return this.mCryptoProvider;
    }
    
    @Override
    protected String getLogTag() {
        return "nf_msl";
    }
    
    @Override
    protected UUID getSchemeUUID() {
        return MSLWidevineCryptoManager.WIDEVINE_SCHEME;
    }
    
    @Override
    protected void init() {
        Log.d("nf_msl", "MSLWidevineCryptoManager::init:");
        if (this.isDeviceProvisioned()) {
            Log.d("nf_msl", "MSLWidevineCryptoManager::init: Widevine is provisioned");
        }
    }
    
    @Override
    protected void load() {
    }
}
