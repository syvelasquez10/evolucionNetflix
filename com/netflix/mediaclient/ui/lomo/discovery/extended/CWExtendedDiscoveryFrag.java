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
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;

public class CWExtendedDiscoveryFrag extends BaseExtendedDiscoveryFrag
{
    private static final int CW_COLLECTION_ID = -1;
    private static final int CW_VIDEOS_PER_PAGE = 9;
    private static final int NUM_DISCOVERY_ROWS = 1;
    private static final String TAG = "CWExtendedDiscoveryFrag";
    private List<Video> collectionData;
    
    @Override
    protected void findViews(final View view) {
        super.findViews(view);
        this.titleView.setText(2131231003);
    }
    
    @Override
    protected void setupLayoutManagerAndAdapter() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager((Context)this.getActivity()));
        this.adapter = new CWExtendedDiscoveryFrag$CollectionAdapter(this, null);
        this.recyclerView.addItemDecoration(new CWExtendedDiscoveryFrag$DiscoveryItemDecoration(this));
    }
    
    @Override
    public void updatePage(final ServiceManager serviceManager, final long n, final String s, final PlayContext playContext, final PaginatedDiscoveryAdapter$BlurredStoryArtProvider paginatedDiscoveryAdapter$BlurredStoryArtProvider) {
        super.updatePage(serviceManager, n, s, playContext, paginatedDiscoveryAdapter$BlurredStoryArtProvider);
        serviceManager.getBrowse().fetchTask(new CachedModelProxy$FetchTurboCollectionVideosTask(n, 0, 9), new CWExtendedDiscoveryFrag$1(this, "CWExtendedDiscoveryFrag", serviceManager));
    }
    
    public void updatePage(final ServiceManager serviceManager, final PlayContext playContext, final PaginatedDiscoveryAdapter$BlurredStoryArtProvider paginatedDiscoveryAdapter$BlurredStoryArtProvider) {
        this.updatePage(serviceManager, -1L, "", playContext, paginatedDiscoveryAdapter$BlurredStoryArtProvider);
    }
}
