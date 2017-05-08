// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import android.media.NotProvisionedException;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import com.netflix.mediaclient.android.app.Status;
import android.os.Handler;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;

public final class DeactivateOfflineLicenseRequest extends OfflineLicenseRequest
{
    private static final String TAG = "nf_offlineLicenseMgr";
    static final boolean USE_OFFLINE_SECURE_STOP = false;
    private final boolean mWasDownloadedCompletely;
    
    public DeactivateOfflineLicenseRequest(final String s, final byte[] mKeySetId, final boolean mWasDownloadedCompletely, final OfflineLicenseManagerCallback offlineLicenseManagerCallback, final OfflineLicenseRequest$OfflineLicenseRequestCallback offlineLicenseRequest$OfflineLicenseRequestCallback, final BladeRunnerClient bladeRunnerClient, final MediaDrm mediaDrm, final String s2, final Handler handler, final String s3, final String s4) {
        super(s, null, s2, offlineLicenseManagerCallback, offlineLicenseRequest$OfflineLicenseRequestCallback, bladeRunnerClient, mediaDrm, handler, s3, s4);
        this.mKeySetId = mKeySetId;
        this.mWasDownloadedCompletely = mWasDownloadedCompletely;
    }
    
    private void handleDeactivateResponse(final String s, final Status status) {
        if (status.isSucces()) {}
        this.doLicenseResponseCallback(null, null, status);
    }
    
    @Override
    public void sendRequest() {
        try {
            Log.i("nf_offlineLicenseMgr", "deactivate playableId=" + this.mPlayableId);
            if (this.mKeySetId == null || this.mKeySetId.length == 0) {
                this.doLicenseResponseCallback(null, null, CommonStatus.OK);
                return;
            }
            Log.logByteArrayRaw("nf_offlineLicenseMgr", "handleLicenseResponse keySetId", this.mKeySetId);
            this.mBladeRunnerClient.deactivateOfflineLicense(this.mLiceneseLink, "", this.mWasDownloadedCompletely, new DeactivateOfflineLicenseRequest$1(this));
        }
        catch (NotProvisionedException ex2) {
            final NetflixImmutableStatus netflixImmutableStatus = CommonStatus.DRM_FAILURE_CDM_NOT_PROVISIONED;
            Log.e("nf_offlineLicenseMgr", "deactivate getKeyRequest NotProvisionedException");
        }
        catch (Exception ex) {
            final NetflixImmutableStatus netflixImmutableStatus = CommonStatus.DRM_FAILURE_CDM;
            Log.e("nf_offlineLicenseMgr", "DeactivateOfflineLicenseRequest Exception " + ex);
            goto Label_0103;
        }
    }
}
