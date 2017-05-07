// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;

public class LoLoMoAdapter extends BaseLoLoMoAdapter<LoMo>
{
    public static final String HOME_LOLOMO_GENRE_ID = "lolomo";
    protected static final String TAG = "LoLoMoAdapter";
    private long requestId;
    
    public LoLoMoAdapter(final LoLoMoFrag loLoMoFrag) {
        super(loLoMoFrag, "lolomo");
    }
    
    private void handlePrefetchComplete() {
        LogUtils.logCurrentThreadName("LoLoMoAdapter", "handlePrefetchComplete()");
        super.refreshData();
    }
    
    @Override
    protected boolean isGenreList() {
        return false;
    }
    
    @Override
    protected void makeFetchRequest(final String s, final int n, final int n2, final ManagerCallback managerCallback) {
        this.getServiceManager().getBrowse().fetchLoMos(n, n2, managerCallback);
    }
    
    @Override
    public void refreshData() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null) {
            Log.w("LoLoMoAdapter", "Service man is null");
            return;
        }
        Log.v("LoLoMoAdapter", "Prefetching lolomo...");
        this.requestId = System.nanoTime();
        serviceManager.getBrowse().prefetchLoLoMo(0, 19, 0, LomoConfig.computeNumVideosToFetchPerBatch(this.activity, LoMoType.STANDARD) - 1, 0, LomoConfig.computeNumVideosToFetchPerBatch(this.activity, LoMoType.CONTINUE_WATCHING) - 1, BrowseExperience.shouldLoadExtraCharacterLeaves(), BrowseExperience.shouldLoadKubrickLeavesInLolomo(), false, new LoLoMoAdapter$1(this, "LoLoMoAdapter", this.requestId));
    }
}
