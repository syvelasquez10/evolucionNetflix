// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery.extended;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.falkor.CachedModelProxy$FetchTurboCollectionVideosTask;
import com.netflix.mediaclient.ui.lomo.discovery.PaginatedDiscoveryAdapter$BlurredStoryArtProvider;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.view.View;
import android.view.View$OnClickListener;

class TurboExtendedDiscoveryFrag$TurboViewHolder$1 implements View$OnClickListener
{
    final /* synthetic */ TurboExtendedDiscoveryFrag$TurboViewHolder this$1;
    final /* synthetic */ int val$position;
    
    TurboExtendedDiscoveryFrag$TurboViewHolder$1(final TurboExtendedDiscoveryFrag$TurboViewHolder this$1, final int val$position) {
        this.this$1 = this$1;
        this.val$position = val$position;
    }
    
    public void onClick(final View view) {
        DetailsActivityLauncher.show(this.this$1.this$0.getNetflixActivity(), this.this$1.this$0.collectionData.get(this.val$position), new PlayContextImp(this.this$1.this$0.trackable, this.val$position), "TurboExtendedDiscoveryFrag");
    }
}
