// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import java.util.Map;

public class PServiceFetchAgent extends PServiceAgent implements PServiceAgent$PServicePartnerFetchInterface
{
    public static final String PARTNER_EXP_DEFAULT = "default";
    private static final String TAG = "nf_preapp_fetchagent";
    public static final String WIDGET_EXP_DEFAULT = "default";
    private PDiskData mDiskData;
    private boolean mLoadFromDiskInProgress;
    Map<PDiskData$ListName, Integer> mPartnerDefaultExp;
    private boolean mRemoteDiskWriteInProgress;
    
    public PServiceFetchAgent() {
        this.mPartnerDefaultExp = new PServiceFetchAgent$1(this);
    }
    
    private int getVideoCount(final PDiskData$ListName pDiskData$ListName) {
        final String preAppPartnerExperience = this.mDiskData.preAppPartnerExperience;
        switch (preAppPartnerExperience.hashCode()) {
            case 1544803905: {
                if (preAppPartnerExperience.equals("default")) {
                    break;
                }
                break;
            }
        }
        return this.mPartnerDefaultExp.get(pDiskData$ListName);
    }
    
    private boolean isLoadInProgress() {
        return this.mLoadFromDiskInProgress;
    }
    
    private void loadDefaultData() {
    }
    
    private void lockDiskAccess() {
        this.mRemoteDiskWriteInProgress = true;
    }
    
    private void notifyOthers(final Intent intent) {
    }
    
    private void refreshData() {
        this.refreshDataAndNotify(null);
    }
    
    private void refreshDataAndNotify(final Intent intent) {
        final PServiceFetchAgent$2 pServiceFetchAgent$2 = new PServiceFetchAgent$2(this, intent);
        if (!this.canReadDataFromDisk()) {
            Log.w("nf_preapp_fetchagent", "Skipping reading from disk because remote write in progress");
            return;
        }
        this.setLoadFromDiskInProgress(true);
        new BackgroundTask().execute(new PServiceFetchAgent$3(this, pServiceFetchAgent$2));
    }
    
    private void setLoadFromDiskInProgress(final boolean mLoadFromDiskInProgress) {
        this.mLoadFromDiskInProgress = mLoadFromDiskInProgress;
    }
    
    private void unlockDiskAccess() {
        this.mRemoteDiskWriteInProgress = false;
    }
    
    public boolean canReadDataFromDisk() {
        return !this.mRemoteDiskWriteInProgress;
    }
    
    void completeInit() {
        this.loadDefaultData();
        this.refreshData();
        this.initCompleted(CommonStatus.OK);
    }
    
    @Override
    protected void doInit() {
        this.completeInit();
    }
    
    @Override
    public List<PVideo> fetchVideosForPartner(final PDiskData$ListName pDiskData$ListName) {
        List<PVideo> list = null;
        if (this.mDiskData != null) {
            final int videoCount = this.getVideoCount(pDiskData$ListName);
            if (videoCount > 0) {
                final ArrayList<PVideo> list2 = new ArrayList<PVideo>();
                final List<PVideo> listByName = this.mDiskData.getListByName(pDiskData$ListName);
                int n = 0;
                while (true) {
                    list = list2;
                    if (n >= videoCount) {
                        break;
                    }
                    list2.add(listByName.get(n));
                    ++n;
                }
            }
        }
        return list;
    }
    
    public void handleCommand(final Intent intent) {
        if (intent == null) {
            Log.w("nf_preapp_fetchagent", "Intent is null");
        }
        else {
            if ("com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_ACTIVE_DISK_WRITE".equals(intent.getAction())) {
                this.lockDiskAccess();
                return;
            }
            if (!"com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_ALL_UPDATED".equals(intent.getAction()) && !"com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_CW_UPDATED".equals(intent.getAction()) && !"com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_IQ_UPDATED".equals(intent.getAction())) {
                Log.e("nf_preapp_fetchagent", "Uknown command!");
                return;
            }
            if (Log.isLoggable("nf_preapp_fetchagent", 3)) {
                Log.d("nf_preapp_fetchagent", String.format("received intent: %s", intent.getAction()));
            }
            this.unlockDiskAccess();
            if (!this.isLoadInProgress()) {
                this.refreshDataAndNotify(intent);
            }
        }
    }
}
