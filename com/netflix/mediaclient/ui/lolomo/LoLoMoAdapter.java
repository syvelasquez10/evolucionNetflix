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

public class LoLoMoAdapter extends BaseLoLoMoAdapter<LoMo>
{
    public static final String LOLOMO_GENRE_ID = "lolomo";
    
    public LoLoMoAdapter(final LoLoMoFrag loLoMoFrag) {
        super(loLoMoFrag, "lolomo");
    }
    
    private void handlePrefetchComplete() {
        super.refresh();
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
    public void refresh() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null) {
            Log.w("LoLoMoAdapter", "Service man is null");
            return;
        }
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory((Context)this.getActivity());
        serviceManager.prefetchLoLoMo(0, 19, 0, PaginatedLoMoAdapter.computeNumVideosToFetchPerBatch(screenSizeCategory) - 1, 0, PaginatedCwAdapter.computeNumVideosToFetchPerBatch(screenSizeCategory) - 1, false, new LoggingManagerCallback("LoLoMoAdapter") {
            @Override
            public void onLoLoMoPrefetched(final int n) {
                super.onLoLoMoPrefetched(n);
                LoLoMoAdapter.this.handlePrefetchComplete();
            }
        });
    }
}
