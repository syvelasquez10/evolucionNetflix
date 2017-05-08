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
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.ui.lomo.CwDiscoveryView;
import android.view.View;
import android.support.v7.widget.RecyclerView$ViewHolder;

class CWExtendedDiscoveryFrag$CWViewHolder extends RecyclerView$ViewHolder
{
    final /* synthetic */ CWExtendedDiscoveryFrag this$0;
    
    public CWExtendedDiscoveryFrag$CWViewHolder(final CWExtendedDiscoveryFrag this$0, final View view) {
        this.this$0 = this$0;
        super(view);
    }
    
    public void updateView(final int n) {
        ((CwDiscoveryView)this.itemView).update(this.this$0.collectionData.get(n), this.this$0.trackable, n, true, false, new CWExtendedDiscoveryFrag$CWViewHolder$1(this));
    }
}
