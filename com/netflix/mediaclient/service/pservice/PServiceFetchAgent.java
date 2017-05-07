// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.content.Intent;

public class PServiceFetchAgent extends PServiceAgent implements PServiceAgent$PServiceFetchAgentInterface, PServiceAgent$PServicePartnerFetchInterface
{
    public static final String PARTNER_EXP_DEFAULT = "default";
    private static final String TAG = "nf_preapp_fetchagent";
    public static final String WIDGET_EXP_DEFAULT = "default";
    private PDiskData mDiskData;
    private boolean mLoadFromDiskInProgress;
    
    private boolean isLoadInProgress() {
        return this.mLoadFromDiskInProgress;
    }
    
    private void loadDefaultData() {
    }
    
    private void refreshData() {
        this.refreshDataAndNotify(new Intent("com.netflix.mediaclient.intent.action.ALL_UPDATED_FROM_PREAPP_AGENT"));
    }
    
    private void setLoadFromDiskInProgress(final boolean mLoadFromDiskInProgress) {
        this.mLoadFromDiskInProgress = mLoadFromDiskInProgress;
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
        if (this.mDiskData == null) {
            Log.w("nf_preapp_fetchagent", "mDiskData is null - ignoring request");
        }
        else {
            final int videoCountOfListForPartnerExp = PServiceABTest.getVideoCountOfListForPartnerExp(pDiskData$ListName, this.mDiskData);
            if (videoCountOfListForPartnerExp > 0) {
                final ArrayList<PVideo> list2 = new ArrayList<PVideo>();
                final List<PVideo> listByName = this.mDiskData.getListByName(pDiskData$ListName);
                int n = 0;
                while (true) {
                    list = list2;
                    if (n >= videoCountOfListForPartnerExp) {
                        break;
                    }
                    list2.add(listByName.get(n));
                    ++n;
                }
            }
        }
        return list;
    }
    
    @Override
    public PDiskData getDiskData() {
        if (this.mDiskData == null) {
            Log.w("nf_preapp_fetchagent", "mDiskData is null");
        }
        return this.mDiskData;
    }
    
    public void refreshDataAndNotify(final Intent intent) {
        if (this.isLoadInProgress()) {
            return;
        }
        final PServiceFetchAgent$1 pServiceFetchAgent$1 = new PServiceFetchAgent$1(this, intent);
        this.setLoadFromDiskInProgress(true);
        new BackgroundTask().execute(new PServiceFetchAgent$2(this, pServiceFetchAgent$1));
    }
}
