// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import com.netflix.msl.util.Base64;
import android.media.DeniedByServerException;
import com.netflix.mediaclient.util.MediaDrmUtils;
import android.media.ResourceBusyException;
import android.media.NotProvisionedException;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import com.netflix.mediaclient.Log;
import android.os.Handler;
import java.util.HashMap;
import android.media.MediaDrm;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;

public class OfflineLicenseRequest
{
    static final String TAG = "nf_offlineLicenseMgr";
    private boolean mAborted;
    protected final BladeRunnerClient mBladeRunnerClient;
    protected byte[] mDrmHeader;
    final String mDxId;
    protected byte[] mKeySetId;
    protected String mLiceneseLink;
    protected MediaDrm mMediaDrm;
    final OfflineLicenseManagerCallback mOfflineLicenseManagerCallback;
    final OfflineLicenseRequest$OfflineLicenseRequestCallback mOfflineLicenseRequestCallback;
    protected final HashMap<String, String> mOptionalParams;
    final String mOxId;
    final String mPlayableId;
    protected byte[] mSessionId;
    protected final Handler mWorkHandler;
    
    public OfflineLicenseRequest(final String mPlayableId, final byte[] mDrmHeader, final String mLiceneseLink, final OfflineLicenseManagerCallback mOfflineLicenseManagerCallback, final OfflineLicenseRequest$OfflineLicenseRequestCallback mOfflineLicenseRequestCallback, final BladeRunnerClient mBladeRunnerClient, final MediaDrm mMediaDrm, final Handler mWorkHandler, final String mOxId, final String mDxId) {
        this.mOptionalParams = new HashMap<String, String>();
        this.mPlayableId = mPlayableId;
        this.mOfflineLicenseManagerCallback = mOfflineLicenseManagerCallback;
        this.mOfflineLicenseRequestCallback = mOfflineLicenseRequestCallback;
        this.mDrmHeader = mDrmHeader;
        this.mLiceneseLink = mLiceneseLink;
        this.mBladeRunnerClient = mBladeRunnerClient;
        this.mMediaDrm = mMediaDrm;
        this.mWorkHandler = mWorkHandler;
        this.mOxId = mOxId;
        this.mDxId = mDxId;
    }
    
    private void closeMediaDrmSession() {
        if (this.mSessionId == null) {
            return;
        }
        while (true) {
            try {
                Log.i("nf_offlineLicenseMgr", "closing mediaDrm session for mPlayableId=" + this.mPlayableId);
                this.mMediaDrm.closeSession(this.mSessionId);
                this.mSessionId = null;
            }
            catch (Exception ex) {
                Log.e("nf_offlineLicenseMgr", "error closing mediaDrm Session " + ex);
                continue;
            }
            break;
        }
    }
    
    private boolean isAborted() {
        return this.mAborted;
    }
    
    private void sendLicenseActivateIfNeeded(final OfflineLicenseResponse offlineLicenseResponse, final Status status) {
        if (status.isError() || offlineLicenseResponse.mLinkActivate == null) {
            Log.d("nf_offlineLicenseMgr", "skip sending activate on error %s", status);
        }
        else if (this instanceof RefreshOfflineLicenseRequest || this instanceof OfflineLicenseRequest) {
            this.mBladeRunnerClient.activateOfflineLicense(offlineLicenseResponse.mLinkActivate);
        }
    }
    
    private boolean tryCreateDrmSession() {
        try {
            this.mSessionId = this.mMediaDrm.openSession();
            if (this.mSessionId == null || this.mSessionId.length == 0) {
                this.doLicenseResponseCallback(null, null, CommonStatus.DRM_FAILURE_CDM);
                Log.e("nf_offlineLicenseMgr", "tryCreateDrmSession DrmSession invalid");
                return false;
            }
        }
        catch (NotProvisionedException ex) {
            Log.e("nf_offlineLicenseMgr", "createDrmSession failed " + ex);
            this.doLicenseResponseCallback(null, null, CommonStatus.DRM_FAILURE_CDM_NOT_PROVISIONED);
            return false;
        }
        catch (ResourceBusyException ex2) {
            Log.e("nf_offlineLicenseMgr", "createDrmSession failed " + ex2);
            this.doLicenseResponseCallback(null, null, CommonStatus.DRM_FAILURE_CDM_RESOURCE_BUSY);
            return false;
        }
        return true;
    }
    
    public void abortRequestAndCloseMediaSession() {
        this.mAborted = true;
        this.closeMediaDrmSession();
    }
    
    protected void doLicenseResponseCallback(final OfflineLicenseResponse offlineLicenseResponse, final byte[] keySetId, final Status status) {
        Log.d("nf_offlineLicenseMgr", "doLicenseResponseCallback " + status);
        this.closeMediaDrmSession();
        if (!this.isAborted()) {
            if (offlineLicenseResponse != null) {
                offlineLicenseResponse.setKeySetId(keySetId);
                this.sendLicenseActivateIfNeeded(offlineLicenseResponse, status);
            }
            this.mOfflineLicenseManagerCallback.onOfflineLicenseRequestDone(this.mPlayableId, offlineLicenseResponse, status);
            this.mOfflineLicenseRequestCallback.onLicenseRequestDone(this, status);
        }
    }
    
    protected void handleLicenseResponse(final OfflineLicenseResponse offlineLicenseResponse, final Status status) {
        if (this.isAborted()) {
            Log.i("nf_offlineLicenseMgr", "handleLicenseResponse request was aborted.");
            return;
        }
        Status status2 = status;
    Label_0109:
        while (true) {
            if (!status.isSucces()) {
                break Label_0109;
            }
            if (offlineLicenseResponse == null) {
                goto Label_0165;
            }
            while (true) {
                try {
                    if (offlineLicenseResponse.getLicenseData() != null && offlineLicenseResponse.getLicenseData().length > 0) {
                        final byte[] provideKeyResponse = this.mMediaDrm.provideKeyResponse(this.mSessionId, offlineLicenseResponse.getLicenseData());
                        if (this.mKeySetId == null || this.mKeySetId.length == 0) {
                            this.mKeySetId = provideKeyResponse;
                        }
                        if (this.mKeySetId == null || this.mKeySetId.length == 0) {
                            status2 = CommonStatus.DRM_FAILURE_CDM_KEY_SET_EMPTY;
                            Log.e("nf_offlineLicenseMgr", "handleLicenseResponse provideKeyResponse returned null");
                        }
                        else {
                            MediaDrmUtils.dumpKeyStatus("nf_offlineLicenseMgr", this.mMediaDrm, this.mSessionId);
                            Log.logByteArrayRaw("nf_offlineLicenseMgr", "handleLicenseResponse keySetId", this.mKeySetId);
                            status2 = status;
                        }
                        this.doLicenseResponseCallback(offlineLicenseResponse, this.mKeySetId, status2);
                        return;
                    }
                    goto Label_0165;
                }
                catch (NotProvisionedException ex2) {
                    status2 = CommonStatus.DRM_FAILURE_CDM_NOT_PROVISIONED;
                    Log.e("nf_offlineLicenseMgr", "handleLicenseResponse provideKeyResponse NotProvisionedException");
                    continue Label_0109;
                }
                catch (DeniedByServerException ex3) {
                    status2 = CommonStatus.DRM_FAILURE_CDM_SERVER_DENIED;
                    Log.e("nf_offlineLicenseMgr", "handleLicenseResponse provideKeyResponse DeniedByServerException");
                    continue Label_0109;
                }
                catch (Exception ex) {
                    status2 = CommonStatus.DRM_FAILURE_CDM_EXCEPTION;
                    Log.e("nf_offlineLicenseMgr", "handleLicenseResponse provideKeyResponse Exception" + ex);
                    continue Label_0109;
                }
                continue Label_0109;
            }
            break;
        }
    }
    
    public boolean isDrmSessionOpen() {
        return this.mSessionId != null && this.mSessionId.length > 0;
    }
    
    protected void sendLicenseRequest() {
        final NetflixImmutableStatus ok = CommonStatus.OK;
        try {
            Log.i("nf_offlineLicenseMgr", "sendLicenseRequest playableId=" + this.mPlayableId);
            this.mBladeRunnerClient.fetchOfflineLicense(this.mLiceneseLink, Base64.encode(this.mMediaDrm.getKeyRequest(this.mSessionId, this.mDrmHeader, "", 2, (HashMap)this.mOptionalParams).getData()), new OfflineLicenseRequest$1(this));
        }
        catch (NotProvisionedException ex2) {
            final NetflixImmutableStatus netflixImmutableStatus = CommonStatus.DRM_FAILURE_CDM_NOT_PROVISIONED;
            Log.e("nf_offlineLicenseMgr", "deactivate getKeyRequest NotProvisionedException");
        }
        catch (Exception ex) {
            final NetflixImmutableStatus netflixImmutableStatus = CommonStatus.DRM_FAILURE_CDM;
            Log.e("nf_offlineLicenseMgr", "sendLicenseRequest Exception" + ex);
            goto Label_0096;
        }
    }
    
    public void sendRequest() {
        if (this.tryCreateDrmSession()) {
            this.sendLicenseRequest();
        }
    }
    
    protected boolean tryCreateDrmSession(final byte[] array) {
        boolean b = false;
        Log.logByteArrayRaw("nf_offlineLicenseMgr", "tryCreateDrmSession using keySetId", array);
        if (!this.tryCreateDrmSession()) {
            return b;
        }
        try {
            this.mMediaDrm.restoreKeys(this.mSessionId, array);
            MediaDrmUtils.dumpKeyStatus("nf_offlineLicenseMgr", this.mMediaDrm, this.mSessionId);
            b = true;
            return b;
        }
        catch (Exception ex) {
            Log.e("nf_offlineLicenseMgr", "restorekeys failed " + ex);
            this.doLicenseResponseCallback(null, null, CommonStatus.DRM_FAILURE_CDM);
            return false;
        }
    }
}
