// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import android.media.NotProvisionedException;
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
    
    public RefreshOfflineLicenseRequest(final IBladeRunnerClient$OfflineRefreshInvoke mInvokedFrom, final String s, final byte[] array, final String s2, final OfflineLicenseManagerCallback offlineLicenseManagerCallback, final OfflineLicenseRequest$OfflineLicenseRequestCallback offlineLicenseRequest$OfflineLicenseRequestCallback, final BladeRunnerClient bladeRunnerClient, final MediaDrm mediaDrm, final Handler handler, final byte[] mKeySetId, final String s3, final String s4) {
        super(s, array, s2, offlineLicenseManagerCallback, offlineLicenseRequest$OfflineLicenseRequestCallback, bladeRunnerClient, mediaDrm, handler, s3, s4);
        this.mKeySetId = mKeySetId;
        this.mInvokedFrom = mInvokedFrom;
    }
    
    @Override
    protected void sendLicenseRequest() {
        try {
            Log.i("nf_offlineLicenseMgr", "RefreshOfflineLicenseRequest sendLicenseRequest playableId=" + this.mPlayableId);
            this.mBladeRunnerClient.refreshOfflineLicense(this.mInvokedFrom, this.mLiceneseLink, Base64.encode(this.mMediaDrm.getKeyRequest(this.mSessionId, this.mDrmHeader, "", 2, (HashMap)this.mOptionalParams).getData()), new RefreshOfflineLicenseRequest$1(this));
        }
        catch (NotProvisionedException ex2) {
            final NetflixImmutableStatus netflixImmutableStatus = CommonStatus.DRM_FAILURE_CDM_NOT_PROVISIONED;
            Log.e("nf_offlineLicenseMgr", "RefreshOfflineLicenseRequest getKeyRequest NotProvisionedException");
        }
        catch (Exception ex) {
            final NetflixImmutableStatus netflixImmutableStatus = CommonStatus.DRM_FAILURE_CDM;
            Log.e("nf_offlineLicenseMgr", "RefreshOfflineLicenseRequest Exception " + ex);
            goto Label_0095;
        }
    }
    
    @Override
    public void sendRequest() {
        if (this.tryCreateDrmSession(this.mKeySetId)) {
            this.sendLicenseRequest();
        }
    }
}
