// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;

public class GenreLoLoMoAdapter extends BasePaginatedLoLoMoAdapter<Genre>
{
    private static final String TAG = "GenreLoLoMoAdapter";
    
    public GenreLoLoMoAdapter(final LoLoMoFrag loLoMoFrag, final String s) {
        super(loLoMoFrag, s);
    }
    
    private void handlePrefetchComplete() {
        super.refreshData();
    }
    
    @Override
    protected boolean isGenreList() {
        return true;
    }
    
    @Override
    protected void makeFetchRequest(final String s, final int n, final int n2, final ManagerCallback managerCallback) {
        this.getServiceManager().getBrowse().fetchGenres(s, n, n2, managerCallback);
    }
    
    @Override
    public void refreshData() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null) {
            Log.w("GenreLoLoMoAdapter", "Service man is null");
            return;
        }
        Log.v("GenreLoLoMoAdapter", "Prefetching genre lolomo...");
        serviceManager.getBrowse().prefetchGenreLoLoMo(this.getGenreId(), 0, 19, 0, LomoConfig.computeNumVideosToFetchPerBatch(this.activity, LoMoType.STANDARD) - 1, this.activity.isKubrick(), false, new GenreLoLoMoAdapter$1(this, "GenreLoLoMoAdapter"));
    }
}
