// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.ui.lomo.PaginatedCwAdapter;
import com.netflix.mediaclient.ui.lomo.PaginatedLoMoAdapter;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.LoMo;

public class LoLoMoAdapter extends BasePaginatedLoLoMoAdapter<LoMo>
{
    public static final String LOLOMO_GENRE_ID = "lolomo";
    private long requestId;
    
    public LoLoMoAdapter(final LoLoMoFrag loLoMoFrag) {
        super(loLoMoFrag, "lolomo");
    }
    
    private void handlePrefetchComplete() {
        super.refreshData();
    }
    
    @Override
    protected boolean isGenreList() {
        return false;
    }
    
    @Override
    protected void makeFetchRequest(final String s, final int n, final int n2, final ManagerCallback managerCallback) {
        this.getServiceManager().fetchLoMos(s, n, n2, managerCallback);
    }
    
    @Override
    public void refreshData() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null) {
            Log.w("BasePaginatedLoLoMoAdapter", "Service man is null");
            return;
        }
        Log.v("BasePaginatedLoLoMoAdapter", "Prefetching lolomo...");
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory((Context)this.getActivity());
        this.requestId = System.nanoTime();
        serviceManager.prefetchLoLoMo(0, 19, 0, PaginatedLoMoAdapter.computeNumVideosToFetchPerBatch(this.activity, screenSizeCategory) - 1, 0, PaginatedCwAdapter.computeNumVideosToFetchPerBatch(this.activity, screenSizeCategory) - 1, this.activity.isForKids(), false, new LoggingManagerCallback("BasePaginatedLoLoMoAdapter") {
            final /* synthetic */ long val$requestIdClone = LoLoMoAdapter.this.requestId;
            
            @Override
            public void onLoLoMoPrefetched(final int n) {
                super.onLoLoMoPrefetched(n);
                if (this.val$requestIdClone != LoLoMoAdapter.this.requestId) {
                    Log.d("BasePaginatedLoLoMoAdapter", "Request IDs do not match - skipping prefetch callback");
                    return;
                }
                LoLoMoAdapter.this.handlePrefetchComplete();
            }
        });
    }
}
