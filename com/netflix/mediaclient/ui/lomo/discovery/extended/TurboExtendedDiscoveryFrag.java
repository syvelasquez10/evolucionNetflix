// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery.extended;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.falkor.CachedModelProxy$FetchTurboCollectionVideosTask;
import com.netflix.mediaclient.ui.lomo.discovery.PaginatedDiscoveryAdapter$BlurredStoryArtProvider;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class TurboExtendedDiscoveryFrag extends BaseExtendedDiscoveryFrag
{
    private static final String TAG = "TurboExtendedDiscoveryFrag";
    private static final int TOTAL_TURBO_ROWS = 2;
    private static final int TURBO_TITLES_PER_PAGE = 18;
    
    private void updateTitle() {
        if (this.titleView != null && this.title != null) {
            this.titleView.setText((CharSequence)this.title);
        }
    }
    
    @Override
    protected void findViews(final View view) {
        super.findViews(view);
        this.updateTitle();
    }
    
    @Override
    protected void setupLayoutManagerAndAdapter() {
        this.recyclerView.setLayoutManager(new GridLayoutManager((Context)this.getActivity(), 2));
        this.adapter = new TurboExtendedDiscoveryFrag$CollectionAdapter(this, null);
        this.recyclerView.addItemDecoration(new TurboExtendedDiscoveryFrag$TurboItemDecoration(this));
    }
    
    @Override
    public void updatePage(final ServiceManager serviceManager, final long n, final String s, final PlayContext playContext, final PaginatedDiscoveryAdapter$BlurredStoryArtProvider paginatedDiscoveryAdapter$BlurredStoryArtProvider) {
        super.updatePage(serviceManager, n, s, playContext, paginatedDiscoveryAdapter$BlurredStoryArtProvider);
        serviceManager.getBrowse().fetchTask(new CachedModelProxy$FetchTurboCollectionVideosTask(n, 0, 18), new TurboExtendedDiscoveryFrag$1(this, "TurboExtendedDiscoveryFrag"));
        this.updateTitle();
    }
}
