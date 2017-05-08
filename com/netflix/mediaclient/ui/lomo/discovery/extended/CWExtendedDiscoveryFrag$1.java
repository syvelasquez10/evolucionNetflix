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
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class CWExtendedDiscoveryFrag$1 extends LoggingManagerCallback
{
    final /* synthetic */ CWExtendedDiscoveryFrag this$0;
    
    CWExtendedDiscoveryFrag$1(final CWExtendedDiscoveryFrag this$0, final String s) {
        this.this$0 = this$0;
        super(s);
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        super.onVideosFetched(list, status);
        this.this$0.collectionData = list;
        if (this.this$0.adapter != null) {
            this.this$0.adapter.notifyDataSetChanged();
            this.this$0.leWrapper.hide(true);
        }
    }
}
