// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import java.util.List;
import android.content.Intent;

public class PServiceFetchAgent extends PServiceAgent implements PServiceAgent$PServiceFetchAgentInterface, PServiceAgent$PServicePartnerFetchInterface
{
    public static final String PARTNER_EXP_DEFAULT = "default";
    private static final String TAG = "nf_preapp_fetchagent";
    public static final String WIDGET_EXP_DEFAULT = "default";
    private boolean initDone;
    private PDiskData mDiskData;
    private boolean mLoadFromDiskInProgress;
    
    private boolean isLoadInProgress() {
        return this.mLoadFromDiskInProgress;
    }
    
    private void refreshData() {
        this.refreshDataAndNotify(null);
    }
    
    private void setLoadFromDiskInProgress(final boolean mLoadFromDiskInProgress) {
        this.mLoadFromDiskInProgress = mLoadFromDiskInProgress;
    }
    
    void completeInit() {
        this.refreshData();
    }
    
    @Override
    protected void doInit() {
        this.completeInit();
    }
    
    @Override
    public List<PVideo> fetchVideosForPartner(final PDiskData$ListType pDiskData$ListType) {
        List<PVideo> list = null;
        if (this.mDiskData == null) {
            Log.w("nf_preapp_fetchagent", "mDiskData is null - ignoring request");
        }
        else {
            final int videoCountOfListForPartnerExp = PServiceABTest.getVideoCountOfListForPartnerExp(pDiskData$ListType, this.mDiskData);
            if (videoCountOfListForPartnerExp > 0) {
                final ArrayList<PVideo> list2 = new ArrayList<PVideo>();
                final List<PVideo> videoListByName = this.mDiskData.getVideoListByName(pDiskData$ListType);
                int n = 0;
                while (true) {
                    list = list2;
                    if (n >= videoCountOfListForPartnerExp) {
                        break;
                    }
                    list2.add(videoListByName.get(n));
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
