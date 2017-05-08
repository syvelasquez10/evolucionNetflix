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
import android.support.v7.widget.LinearLayoutManager;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.lomo.CwDiscoveryView;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView$ViewHolder;
import android.support.v7.widget.RecyclerView$Adapter;

class CWExtendedDiscoveryFrag$CollectionAdapter extends RecyclerView$Adapter<RecyclerView$ViewHolder>
{
    final /* synthetic */ CWExtendedDiscoveryFrag this$0;
    
    private CWExtendedDiscoveryFrag$CollectionAdapter(final CWExtendedDiscoveryFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int getItemCount() {
        if (this.this$0.collectionData == null) {
            return 0;
        }
        return this.this$0.collectionData.size();
    }
    
    @Override
    public void onBindViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        ((CWExtendedDiscoveryFrag$CWViewHolder)recyclerView$ViewHolder).updateView(n);
    }
    
    @Override
    public RecyclerView$ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int dimensionPixelOffset) {
        dimensionPixelOffset = this.this$0.getActivity().getResources().getDimensionPixelOffset(2131362123);
        final float n = (DeviceUtils.getScreenWidthInPixels((Context)this.this$0.getActivity()) - dimensionPixelOffset * 2) / 1;
        final CwDiscoveryView cwDiscoveryView = new CwDiscoveryView((Context)this.this$0.getActivity());
        cwDiscoveryView.setLayoutParams(new ViewGroup$LayoutParams((int)n, (int)(0.5625f * n)));
        return new CWExtendedDiscoveryFrag$CWViewHolder(this.this$0, (View)cwDiscoveryView);
    }
}
