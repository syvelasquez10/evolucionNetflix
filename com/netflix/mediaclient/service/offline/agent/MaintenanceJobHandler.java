// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import com.netflix.mediaclient.service.offline.download.OfflinePlayableListener;
import com.netflix.mediaclient.service.offline.download.OfflinePlayableImpl;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.offline.registry.OfflineRegistry;
import com.netflix.mediaclient.service.offline.license.OfflineLicenseManager;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable;
import java.util.List;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable$PlayableMaintenanceCallBack;
import com.netflix.mediaclient.service.offline.download.OfflinePlayable$PlayableDeleteCompleteCallBack;

class MaintenanceJobHandler implements OfflinePlayable$PlayableDeleteCompleteCallBack, OfflinePlayable$PlayableMaintenanceCallBack
{
    private static final String TAG = "nf_MaintenanceJob";
    private final List<OfflinePlayable> mActivePlayables;
    private final MaintenanceJobHandler$MaintenanceJobHandlerCallback mCallback;
    private final List<OfflinePlayablePersistentData> mDeletedPlaybles;
    private final OfflineLicenseManager mOfflineLicenseManager;
    private final OfflineRegistry mOfflineRegistry;
    private int mPendingDeleteCount;
    private int mPendingMaintenanceCount;
    private final AtomicBoolean mTerminated;
    
    MaintenanceJobHandler(final MaintenanceJobHandler$MaintenanceJobHandlerCallback mCallback, final OfflineLicenseManager mOfflineLicenseManager, final List<OfflinePlayable> mActivePlayables, final OfflineRegistry mOfflineRegistry) {
        this.mPendingMaintenanceCount = 0;
        this.mPendingDeleteCount = 0;
        this.mTerminated = new AtomicBoolean(false);
        this.mOfflineRegistry = mOfflineRegistry;
        this.mOfflineLicenseManager = mOfflineLicenseManager;
        this.mCallback = mCallback;
        this.mDeletedPlaybles = this.mOfflineRegistry.getAllDeletedPlayable();
        this.mActivePlayables = mActivePlayables;
    }
    
    private void processPendingDelete() {
        Log.i("nf_MaintenanceJob", "processPendingDelete");
        final ArrayList<OfflinePlayablePersistentData> list = new ArrayList<OfflinePlayablePersistentData>();
        for (final OfflinePlayablePersistentData offlinePlayablePersistentData : this.mDeletedPlaybles) {
            if (!offlinePlayablePersistentData.getDownloadState().equals(DownloadState.DeleteComplete)) {
                list.add(offlinePlayablePersistentData);
            }
            else {
                this.mOfflineRegistry.removeFromDeletedList(offlinePlayablePersistentData);
            }
        }
        if (list.size() > 0) {
            Log.i("nf_MaintenanceJob", "processPendingDelete not calling onAllMaintenanceJobDone");
            this.mPendingDeleteCount = list.size();
            final Iterator<Object> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                OfflinePlayableImpl.deleteLicense(this.mOfflineLicenseManager, iterator2.next(), this, null);
            }
        }
        else {
            this.mCallback.onAllMaintenanceJobDone();
        }
        this.syncActiveLicensesToServer();
    }
    
    private void syncActiveLicensesToServer() {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.mActivePlayables.size() > 0) {
            final Iterator<OfflinePlayable> iterator = this.mActivePlayables.iterator();
            while (iterator.hasNext()) {
                final String mLinkDeactivate = iterator.next().getOfflineViewablePersistentData().mLicenseData.mLinkDeactivate;
                if (StringUtils.isNotEmpty(mLinkDeactivate)) {
                    list.add(mLinkDeactivate);
                }
            }
        }
        this.mOfflineLicenseManager.trySyncActiveLicensesToServer(list);
    }
    
    @Override
    public void onDeleteCompleted(final OfflinePlayablePersistentData offlinePlayablePersistentData) {
        Log.i("nf_MaintenanceJob", "onDeleteCompleted");
        this.mOfflineRegistry.removeFromDeletedList(offlinePlayablePersistentData);
        --this.mPendingDeleteCount;
        if (this.mPendingDeleteCount == 0 && !this.mTerminated.get()) {
            this.mCallback.onAllMaintenanceJobDone();
        }
    }
    
    @Override
    public void onMaintenanceJobDone(final OfflinePlayable offlinePlayable) {
        Log.i("nf_MaintenanceJob", "onMaintenanceJobDone");
        --this.mPendingMaintenanceCount;
        if (this.mPendingMaintenanceCount == 0 && !this.mTerminated.get()) {
            this.processPendingDelete();
        }
    }
    
    void startMaintenanceJob() {
        Log.i("nf_MaintenanceJob", "startMaintenanceJob");
        if (this.mActivePlayables.size() > 0) {
            this.mPendingMaintenanceCount = this.mActivePlayables.size();
            final Iterator<OfflinePlayable> iterator = this.mActivePlayables.iterator();
            while (iterator.hasNext()) {
                iterator.next().doMaintenanceWork(this);
            }
        }
        else {
            this.processPendingDelete();
        }
    }
    
    void terminate() {
        this.mTerminated.set(true);
    }
}
