// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.msl.util.Base64;
import java.util.HashMap;
import com.netflix.mediaclient.Log;
import android.os.Handler;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$OfflineRefreshInvoke;

class RefreshOfflineLicenseRequest extends OfflineLicenseRequest
{
    IBladeRunnerClient$OfflineRefreshInvoke mInvokedFrom;
    
    public RefreshOfflineLicenseRequest(final IBladeRunnerClient$OfflineRefreshInvoke mInvokedFrom, final String s, final byte[] array, final String s2, final OfflineLicenseManagerCallback offlineLicenseManagerCallback, final OfflineLicenseRequest$OfflineLicenseRequestCallback offlineLicenseRequest$OfflineLicenseRequestCallback, final BladeRunnerClient bladeRunnerClient, final MediaDrm mediaDrm, final Handler handler, final byte[] mKeySetId) {
        super(s, array, s2, offlineLicenseManagerCallback, offlineLicenseRequest$OfflineLicenseRequestCallback, bladeRunnerClient, mediaDrm, handler);
        this.mKeySetId = mKeySetId;
        this.mInvokedFrom = mInvokedFrom;
    }
    
    @Override
    protected void sendLicenseRequest() {
        try {
            Log.i("nf_offlineLicenseMgr", "RefreshOfflineLicenseRequest sendLicenseRequest playableId=" + this.mPlayableId);
            this.mBladeRunnerClient.refreshOfflineLicense(this.mInvokedFrom, this.mLiceneseLink, Base64.encode(this.mMediaDrm.getKeyRequest(this.mSessionId, this.mDrmHeader, "", 2, (HashMap)this.mOptionalParams).getData()), new RefreshOfflineLicenseRequest$1(this));
        }
        catch (Exception ex) {
            Log.e("nf_offlineLicenseMgr", "RefreshOfflineLicenseRequest Exception " + ex);
            this.doLicenseResponseCallback(null, null, CommonStatus.DRM_FAILURE_CDM);
        }
    }
    
    @Override
    public void sendRequest() {
        if (this.tryCreateDrmSession(this.mKeySetId)) {
            this.sendLicenseRequest();
        }
    }
}
