// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import android.os.Bundle;
import com.netflix.mediaclient.util.TrailerUtils;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.content.Context;
import android.view.ViewGroup;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableObject;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.GridLayoutManager;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;

class EpisodesFrag_Ab7994$1 extends GridLayoutManager$SpanSizeLookup
{
    final /* synthetic */ EpisodesFrag_Ab7994 this$0;
    
    EpisodesFrag_Ab7994$1(final EpisodesFrag_Ab7994 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int getSpanSize(final int n) {
        if (n == 0 || ((RecyclerViewHeaderAdapter)this.this$0.recyclerView.getAdapter()).isPositionFooter(n)) {
            return this.this$0.numColumns;
        }
        return 1;
    }
}
