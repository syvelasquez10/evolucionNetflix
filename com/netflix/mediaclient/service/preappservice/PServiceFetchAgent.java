// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.Intent;

public class PServiceFetchAgent extends PServiceAgent
{
    private static final String TAG = "nf_preapp_fetchagent";
    private PDiskData mDiskData;
    private boolean mLoadFromDiskInProgress;
    
    private boolean isLoadInProgress() {
        return this.mLoadFromDiskInProgress;
    }
    
    private void loadDefaultData() {
    }
    
    private void notifyOthers(final Intent intent) {
    }
    
    private void refreshDataAndNotify(final Intent intent) {
        final PServiceFetchAgent$1 pServiceFetchAgent$1 = new PServiceFetchAgent$1(this, intent);
        this.mLoadFromDiskInProgress = true;
        new BackgroundTask().execute(new PServiceFetchAgent$2(this, pServiceFetchAgent$1));
    }
    
    void completeInit() {
        this.mLoadFromDiskInProgress = false;
        this.loadDefaultData();
        this.initCompleted(CommonStatus.OK);
    }
    
    @Override
    protected void doInit() {
        this.mDiskData = null;
        this.mLoadFromDiskInProgress = false;
        this.completeInit();
    }
    
    public void handleCommand(final Intent intent) {
        if (intent == null) {
            Log.w("nf_preapp_fetchagent", "Intent is null");
        }
        else {
            if (!"com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_ALL_UPDATED".equals(intent.getAction()) && !"com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_CW_UPDATED".equals(intent.getAction()) && !"com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_IQ_UPDATED".equals(intent.getAction())) {
                Log.e("nf_preapp_fetchagent", "Uknown command!");
                return;
            }
            if (Log.isLoggable("nf_preapp_fetchagent", 3)) {
                Log.d("nf_preapp_fetchagent", String.format("received intent: %s", intent.getAction()));
            }
            if (!this.isLoadInProgress()) {
                this.refreshDataAndNotify(intent);
            }
        }
    }
}
