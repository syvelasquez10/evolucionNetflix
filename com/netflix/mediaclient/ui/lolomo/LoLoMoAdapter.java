// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.ui.lomo.PaginatedCwAdapter;
import com.netflix.mediaclient.ui.lomo.PaginatedLoMoAdapter;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.model.LoMo;

public class LoLoMoAdapter extends BasePaginatedLoLoMoAdapter<LoMo>
{
    public static final String HOME_LOLOMO_GENRE_ID = "lolomo";
    private static final String TAG = "LoLoMoAdapter";
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
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory((Context)this.getActivity());
        this.requestId = System.nanoTime();
        serviceManager.getBrowse().prefetchLoLoMo(0, 19, 0, PaginatedLoMoAdapter.computeNumVideosToFetchPerBatch(this.activity, screenSizeCategory) - 1, 0, PaginatedCwAdapter.computeNumVideosToFetchPerBatch(this.activity, screenSizeCategory) - 1, this.activity.isForKids(), false, new LoggingManagerCallback("LoLoMoAdapter") {
            final /* synthetic */ long val$requestIdClone = LoLoMoAdapter.this.requestId;
            
            @Override
            public void onLoLoMoPrefetched(final Status status) {
                super.onLoLoMoPrefetched(status);
                ThreadUtils.assertOnMain();
                if (this.val$requestIdClone != LoLoMoAdapter.this.requestId) {
                    Log.d("LoLoMoAdapter", "Request IDs do not match - skipping prefetch callback");
                    return;
                }
                LoLoMoAdapter.this.handlePrefetchComplete();
            }
        });
    }
}
