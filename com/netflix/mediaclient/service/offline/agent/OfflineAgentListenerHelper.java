// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.offline.StopReason;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.android.app.Status;
import android.os.Handler;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixPowerManager$PartialWakeLockReason;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.service.NetflixPowerManager;

public class OfflineAgentListenerHelper
{
    private static final String TAG = "nf_offlineAgent";
    private NetflixPowerManager mNetflixPowerManager;
    private final List<OfflineAgentListener> mOfflineAgentListeners;
    
    public OfflineAgentListenerHelper() {
        this.mOfflineAgentListeners = new ArrayList<OfflineAgentListener>();
    }
    
    private void releasePartialWakeLock() {
        if (this.mNetflixPowerManager != null) {
            this.mNetflixPowerManager.releasePartialWakeLockFor(NetflixPowerManager$PartialWakeLockReason.DownloadGoingOn);
        }
    }
    
    private void removeDeadListeners() {
        final Iterator<OfflineAgentListener> iterator = this.mOfflineAgentListeners.iterator();
        while (iterator.hasNext()) {
            final OfflineAgentListener offlineAgentListener = iterator.next();
            if (offlineAgentListener != null && offlineAgentListener.isListenerDestroyed()) {
                Log.i("nf_offlineAgent", "...removing deadListener... " + offlineAgentListener.getClass().getName());
                iterator.remove();
            }
        }
    }
    
    private void takePartialWakeLock() {
        if (this.mNetflixPowerManager != null) {
            this.mNetflixPowerManager.acquirePartialWakeLockFor(NetflixPowerManager$PartialWakeLockReason.DownloadGoingOn);
        }
    }
    
    public void addOfflineAgentListener(final Handler handler, final OfflineAgentListener offlineAgentListener) {
        if (handler != null && offlineAgentListener != null) {
            if (Log.isLoggable()) {
                Log.i("nf_offlineAgent", "addOfflineAgentListener before listener=" + offlineAgentListener.getClass().getName() + " count=" + this.mOfflineAgentListeners.size());
            }
            handler.post((Runnable)new OfflineAgentListenerHelper$13(this, offlineAgentListener));
        }
    }
    
    public void agentDestroying() {
        this.releasePartialWakeLock();
        this.mNetflixPowerManager = null;
    }
    
    public void onAllPlayablesDeleted(final Handler handler, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onAllPlayablesDeleted status=" + status);
        }
        handler.post((Runnable)new OfflineAgentListenerHelper$8(this, status));
    }
    
    public void onCreateRequestResponse(final Handler handler, final String s, final Status status) {
        handler.post((Runnable)new OfflineAgentListenerHelper$1(this, s, status));
    }
    
    public void onDownloadCompleted(final Handler handler, final OfflinePlayableViewData offlinePlayableViewData) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onDownloadCompleted playableId=" + offlinePlayableViewData.getPlayableId());
        }
        handler.post((Runnable)new OfflineAgentListenerHelper$3(this, offlinePlayableViewData));
    }
    
    public void onDownloadResumedByUser(final Handler handler, final OfflinePlayableViewData offlinePlayableViewData) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onDownloadResumedByUser playableId=" + offlinePlayableViewData.getPlayableId());
        }
        handler.post((Runnable)new OfflineAgentListenerHelper$5(this, offlinePlayableViewData));
    }
    
    public void onDownloadStopped(final Handler handler, final OfflinePlayableViewData offlinePlayableViewData, final StopReason stopReason) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onDownloadStopped playableId=" + offlinePlayableViewData.getPlayableId());
        }
        handler.post((Runnable)new OfflineAgentListenerHelper$4(this, offlinePlayableViewData, stopReason));
    }
    
    public void onError(final Handler handler, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "sendError status=" + status);
        }
        handler.post((Runnable)new OfflineAgentListenerHelper$9(this, status));
    }
    
    public void onLicenseRefreshDone(final Handler handler, final OfflinePlayableViewData offlinePlayableViewData, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "sendLicenseRefreshDone status=" + status);
        }
        handler.post((Runnable)new OfflineAgentListenerHelper$10(this, offlinePlayableViewData, status));
    }
    
    public void onOfflinePlayableDeleted(final Handler handler, final String s, final Status status, final OfflineAgentInterface offlineAgentInterface, final OfflineAgent$DeleteAndTryAgainRequest offlineAgent$DeleteAndTryAgainRequest) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onOfflinePlayableDeleted playableId=" + s + " Status=" + status);
        }
        handler.post((Runnable)new OfflineAgentListenerHelper$6(this, s, status, offlineAgent$DeleteAndTryAgainRequest, handler, offlineAgentInterface));
    }
    
    public void onOfflinePlayableProgress(final Handler handler, final OfflinePlayableViewData offlinePlayableViewData, final int n) {
        handler.post((Runnable)new OfflineAgentListenerHelper$2(this, offlinePlayableViewData, n));
    }
    
    public void onOfflinePlayablesDeleted(final Handler handler, final List<String> list, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "onOfflinePlayablesDeleted playableIdList size=" + list.size() + " Status=" + status);
        }
        handler.post((Runnable)new OfflineAgentListenerHelper$7(this, list, status));
    }
    
    public void onPlayWindowRenewDone(final Handler handler, final OfflinePlayableViewData offlinePlayableViewData, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "sendPlayWindowRenewDone status=" + status);
        }
        handler.post((Runnable)new OfflineAgentListenerHelper$11(this, offlinePlayableViewData, status));
    }
    
    public void removeOfflineAgentListener(final Handler handler, final OfflineAgentListener offlineAgentListener) {
        if (handler != null && offlineAgentListener != null) {
            Log.i("nf_offlineAgent", "removeOfflineAgentListener before listener=" + offlineAgentListener.getClass().getName() + " count=" + this.mOfflineAgentListeners.size());
            handler.post((Runnable)new OfflineAgentListenerHelper$14(this, offlineAgentListener));
        }
    }
    
    public void sendStorageAddedOrRemoved(final Handler handler, final boolean b) {
        if (Log.isLoggable()) {
            Log.i("nf_offlineAgent", "sendStorageAddedOrRemoved offlineFeatureAvailable=%b", b);
        }
        handler.post((Runnable)new OfflineAgentListenerHelper$12(this, b));
    }
    
    public void setNetflixPowerManager(final NetflixPowerManager mNetflixPowerManager) {
        this.mNetflixPowerManager = mNetflixPowerManager;
    }
}
