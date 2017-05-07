// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.util.MdxUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.MdxUtils$SetVideoRatingCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.mdx.DialogMessageReceiver;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.mdx.DialogMessageReceiver$Callback;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.VideoView;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;

class KubrickShowDetailsFrag$4 implements RecyclerViewHeaderAdapter$IViewCreator
{
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    KubrickShowDetailsFrag$4(final KubrickShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    private int getImageHeight() {
        return (int)((DeviceUtils.getScreenWidthInPixels((Context)this.this$0.getActivity()) - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131361997) * (this.this$0.numColumns + 1.0f)) / this.this$0.numColumns * 0.5625f);
    }
    
    @Override
    public View createItemView() {
        final VideoView videoView = new VideoView(this.this$0.recyclerView.getContext());
        videoView.setAdjustViewBounds(true);
        videoView.setScaleType(ImageView$ScaleType.FIT_XY);
        videoView.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.getImageHeight()));
        videoView.setIsHorizontal(true);
        return (View)videoView;
    }
}