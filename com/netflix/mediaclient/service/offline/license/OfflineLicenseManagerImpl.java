// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.license;

import java.util.List;
import com.netflix.mediaclient.service.player.bladerunnerclient.IBladeRunnerClient$OfflineRefreshInvoke;
import com.netflix.mediaclient.service.pdslogging.PdsLoggingUtils;
import com.netflix.mediaclient.service.offline.log.OfflineErrorLogblob;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.MediaDrmUtils;
import java.util.LinkedList;
import android.os.Looper;
import android.os.Handler;
import android.media.MediaDrm;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import java.util.Queue;
import android.content.Context;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerClient;
import android.media.MediaDrm$OnEventListener;

public class OfflineLicenseManagerImpl implements MediaDrm$OnEventListener, OfflineLicenseManager, OfflineLicenseRequest$OfflineLicenseRequestCallback
{
    private static final int MAX_MEDIA_DRM_SESSION = 1;
    private static final long MIN_LICENSE_SYNC_INTERVAL_MS = 3600000L;
    private static final String TAG = "nf_offlineLicenseMgr";
    private final BladeRunnerClient mBladeRunnerClient;
    private final Context mContext;
    private final Queue<DeactivateOfflineLicenseRequest> mDeactivateOfflineLicenseRequestQueue;
    private final IClientLogging mLoggingAgent;
    private final MediaDrm mMediaDrm;
    private final Queue<OfflineLicenseRequest> mNewLicenseRequestQueue;
    private long mRecentSyncTimeMs;
    private final Queue<RefreshOfflineLicenseRequest> mRefreshLicenseRequestQueue;
    private final Handler mWorkHandler;
    
    public OfflineLicenseManagerImpl(final Context mContext, final Looper looper, final BladeRunnerClient mBladeRunnerClient, final IClientLogging mLoggingAgent) {
        this.mNewLicenseRequestQueue = new LinkedList<OfflineLicenseRequest>();
        this.mRefreshLicenseRequestQueue = new LinkedList<RefreshOfflineLicenseRequest>();
        this.mDeactivateOfflineLicenseRequestQueue = new LinkedList<DeactivateOfflineLicenseRequest>();
        this.mRecentSyncTimeMs = 0L;
        this.mContext = mContext;
        this.mWorkHandler = new Handler(looper);
        this.mBladeRunnerClient = mBladeRunnerClient;
        this.mLoggingAgent = mLoggingAgent;
        this.mMediaDrm = MediaDrmUtils.getNewMediaDrmInstance((MediaDrm$OnEventListener)this);
    }
    
    private void trySendingNextRequest() {
        Log.i("nf_offlineLicenseMgr", "trySendingNextRequest");
        final OfflineLicenseRequest offlineLicenseRequest = this.mNewLicenseRequestQueue.peek();
        if (offlineLicenseRequest != null) {
            offlineLicenseRequest.sendRequest();
        }
        else {
            final RefreshOfflineLicenseRequest refreshOfflineLicenseRequest = this.mRefreshLicenseRequestQueue.peek();
            if (refreshOfflineLicenseRequest != null) {
                refreshOfflineLicenseRequest.sendRequest();
                return;
            }
            final DeactivateOfflineLicenseRequest deactivateOfflineLicenseRequest = this.mDeactivateOfflineLicenseRequestQueue.peek();
            if (deactivateOfflineLicenseRequest != null) {
                deactivateOfflineLicenseRequest.sendRequest();
            }
        }
    }
    
    public void abortAllRequestsForPlayable(final String s) {
        Log.i("nf_offlineLicenseMgr", "abortAllRequestsForPlayable playableId=" + s);
        final Iterator<OfflineLicenseRequest> iterator = (Iterator<OfflineLicenseRequest>)this.mNewLicenseRequestQueue.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final OfflineLicenseRequest offlineLicenseRequest = iterator.next();
            if (offlineLicenseRequest.mPlayableId.equals(s)) {
                offlineLicenseRequest.abortRequestAndCloseMediaSession();
                iterator.remove();
            }
            else {
                if (!offlineLicenseRequest.isDrmSessionOpen()) {
                    continue;
                }
                ++n;
            }
        }
        final Iterator<RefreshOfflineLicenseRequest> iterator2 = (Iterator<RefreshOfflineLicenseRequest>)this.mRefreshLicenseRequestQueue.iterator();
        while (iterator2.hasNext()) {
            final RefreshOfflineLicenseRequest refreshOfflineLicenseRequest = iterator2.next();
            if (refreshOfflineLicenseRequest.mPlayableId.equals(s)) {
                refreshOfflineLicenseRequest.abortRequestAndCloseMediaSession();
                iterator2.remove();
            }
            else {
                if (!refreshOfflineLicenseRequest.isDrmSessionOpen()) {
                    continue;
                }
                ++n;
            }
        }
        final Iterator<DeactivateOfflineLicenseRequest> iterator3 = (Iterator<DeactivateOfflineLicenseRequest>)this.mDeactivateOfflineLicenseRequestQueue.iterator();
        while (iterator3.hasNext()) {
            final DeactivateOfflineLicenseRequest deactivateOfflineLicenseRequest = iterator3.next();
            if (deactivateOfflineLicenseRequest.mPlayableId.equals(s)) {
                deactivateOfflineLicenseRequest.abortRequestAndCloseMediaSession();
                iterator3.remove();
            }
            else {
                if (!deactivateOfflineLicenseRequest.isDrmSessionOpen()) {
                    continue;
                }
                ++n;
            }
        }
        if (n < 1) {
            Log.i("nf_offlineLicenseMgr", "abortAllRequestsForPlayable drmSessionsOpen=" + n);
            this.trySendingNextRequest();
        }
    }
    
    public void deleteLicense(final String s, final byte[] array, final boolean b, final String s2, final String s3, final String s4, final OfflineLicenseManagerCallback offlineLicenseManagerCallback) {
        Log.i("nf_offlineLicenseMgr", "deleteLicense playableId=" + s);
        new DeactivateOfflineLicenseRequest(s, array, b, offlineLicenseManagerCallback, this, this.mBladeRunnerClient, this.mMediaDrm, s2, this.mWorkHandler, s3, s4).sendRequest();
    }
    
    public void destroy() {
        if (this.mMediaDrm != null) {
            this.mMediaDrm.release();
        }
    }
    
    public void downloadCompleteAndActivateLicense(final String s, final String s2, final OfflineLicenseManager$DownloadCompleteAndActivateCallback offlineLicenseManager$DownloadCompleteAndActivateCallback) {
        this.mBladeRunnerClient.downloadComplete(s2, new OfflineLicenseManagerImpl$1(this, offlineLicenseManager$DownloadCompleteAndActivateCallback, s));
    }
    
    public void onEvent(final MediaDrm mediaDrm, final byte[] array, final int n, final int n2, final byte[] array2) {
        String s = "EVENT_UNKNOWN";
        if (n == 3) {
            s = "EVENT_KEY_EXPIRED";
        }
        else if (n == 2) {
            s = "EVENT_KEY_REQUIRED";
        }
        else if (n == 5) {
            s = "EVENT_SESSION_RECLAIMED";
        }
        else if (n == 4) {
            s = "EVENT_VENDOR_DEFINED";
        }
        else if (n == 1) {
            s = "EVENT_PROVISION_REQUIRED";
        }
        Log.logByteArrayRaw("nf_offlineLicenseMgr", "onEvent [" + n + "] " + s, array);
    }
    
    public void onLicenseRequestDone(final OfflineLicenseRequest offlineLicenseRequest, final Status status) {
        Log.i("nf_offlineLicenseMgr", "onLicenseRequestDone");
        if (offlineLicenseRequest instanceof RefreshOfflineLicenseRequest) {
            final Iterator<RefreshOfflineLicenseRequest> iterator = (Iterator<RefreshOfflineLicenseRequest>)this.mRefreshLicenseRequestQueue.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().mPlayableId.equals(offlineLicenseRequest.mPlayableId)) {
                    Log.i("nf_offlineLicenseMgr", "onLicenseRequestDone removing from mRefreshLicenseRequestQueue");
                    iterator.remove();
                    break;
                }
            }
        }
        else if (offlineLicenseRequest instanceof DeactivateOfflineLicenseRequest) {
            final Iterator<DeactivateOfflineLicenseRequest> iterator2 = (Iterator<DeactivateOfflineLicenseRequest>)this.mDeactivateOfflineLicenseRequestQueue.iterator();
            while (iterator2.hasNext()) {
                if (iterator2.next().mPlayableId.equals(offlineLicenseRequest.mPlayableId)) {
                    Log.i("nf_offlineLicenseMgr", "onLicenseRequestDone removing from mDeactivateOfflineLicenseRequestQueue");
                    iterator2.remove();
                    break;
                }
            }
        }
        else {
            final Iterator<OfflineLicenseRequest> iterator3 = (Iterator<OfflineLicenseRequest>)this.mNewLicenseRequestQueue.iterator();
            while (iterator3.hasNext()) {
                if (iterator3.next().mPlayableId.equals(offlineLicenseRequest.mPlayableId)) {
                    Log.i("nf_offlineLicenseMgr", "onLicenseRequestDone removing from mNewLicenseRequestQueue");
                    iterator3.remove();
                    break;
                }
            }
        }
        if (status.isError()) {
            OfflineErrorLogblob.sendBladerunnerError(this.mLoggingAgent.getLogblobLogging(), offlineLicenseRequest.mPlayableId, offlineLicenseRequest.mOxId, offlineLicenseRequest.mDxId, status);
            PdsLoggingUtils.downloadStoppedOnLicenseError(this.mContext, offlineLicenseRequest.mPlayableId, status);
        }
        this.trySendingNextRequest();
    }
    
    public void refreshLicense(final IBladeRunnerClient$OfflineRefreshInvoke bladeRunnerClient$OfflineRefreshInvoke, final String s, final byte[] array, final byte[] array2, final String s2, final String s3, final String s4, final OfflineLicenseManagerCallback offlineLicenseManagerCallback) {
        Log.i("nf_offlineLicenseMgr", "refreshLicense playableId=" + s);
        final RefreshOfflineLicenseRequest refreshOfflineLicenseRequest = new RefreshOfflineLicenseRequest(bladeRunnerClient$OfflineRefreshInvoke, s, array, s2, offlineLicenseManagerCallback, this, this.mBladeRunnerClient, this.mMediaDrm, this.mWorkHandler, array2, s3, s4);
        this.mRefreshLicenseRequestQueue.add(refreshOfflineLicenseRequest);
        if (this.mRefreshLicenseRequestQueue.size() + this.mNewLicenseRequestQueue.size() + this.mDeactivateOfflineLicenseRequestQueue.size() <= 1) {
            refreshOfflineLicenseRequest.sendRequest();
            return;
        }
        Log.i("nf_offlineLicenseMgr", "refreshLicense serializing the request");
    }
    
    public void requestNewLicense(final String s, final byte[] array, final String s2, final String s3, final String s4, final OfflineLicenseManagerCallback offlineLicenseManagerCallback) {
        Log.i("nf_offlineLicenseMgr", "requestNewLicense playableId=" + s);
        final OfflineLicenseRequest offlineLicenseRequest = new OfflineLicenseRequest(s, array, s2, offlineLicenseManagerCallback, this, this.mBladeRunnerClient, this.mMediaDrm, this.mWorkHandler, s3, s4);
        this.mNewLicenseRequestQueue.add(offlineLicenseRequest);
        if (this.mRefreshLicenseRequestQueue.size() + this.mNewLicenseRequestQueue.size() + this.mDeactivateOfflineLicenseRequestQueue.size() <= 1) {
            offlineLicenseRequest.sendRequest();
            return;
        }
        Log.i("nf_offlineLicenseMgr", "requestNewLicense serializing the request");
    }
    
    public void trySyncActiveLicensesToServer(final List<String> list) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis <= this.mRecentSyncTimeMs + 3600000L) {
            if (Log.isLoggable()) {
                Log.i("nf_offlineLicenseMgr", "trySyncActiveLicensesToServer last ssync " + this.mRecentSyncTimeMs);
            }
            return;
        }
        this.mRecentSyncTimeMs = currentTimeMillis;
        this.mBladeRunnerClient.syncActiveLicensesToServer(list, new OfflineLicenseManagerImpl$2(this));
    }
}
