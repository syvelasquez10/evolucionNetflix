// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.ui.details.DummyEpisodeDetails;
import java.util.Collection;
import android.support.v7.widget.RecyclerView;
import android.view.ViewParent;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.widget.Checkable;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import android.support.v7.widget.RecyclerView$Adapter;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.android.fragment.LoadingView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.content.Context;
import android.view.View;
import android.view.View$OnClickListener;
import android.support.v7.widget.RecyclerView$ViewHolder;

class RecyclerViewHeaderAdapter$VideoViewHolder extends RecyclerView$ViewHolder implements View$OnClickListener
{
    private View loadingView;
    final /* synthetic */ RecyclerViewHeaderAdapter this$0;
    
    public RecyclerViewHeaderAdapter$VideoViewHolder(final RecyclerViewHeaderAdapter this$0, final View view, final Context context) {
        this.this$0 = this$0;
        super((View)new FrameLayout(context));
        if (view != null) {
            ((ViewGroup)this.itemView).addView(view);
            this.addLoadingViewToRoot((View)new LoadingView(context));
            view.setOnClickListener((View$OnClickListener)this);
        }
    }
    
    private void addLoadingViewToRoot(final View view) {
        view.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        view.setVisibility(8);
        ((ViewGroup)this.itemView).addView(view);
    }
    
    public void onClick(final View singleChoiceModeState) {
        if (this.this$0.itemClickListener != null) {
            this.this$0.itemClickListener.onItemClick(singleChoiceModeState, this.getPosition());
        }
        this.this$0.setSingleChoiceModeState(singleChoiceModeState);
    }
    
    public void setLoadingViewVisibility(final int visibility) {
        if (this.loadingView != null) {
            this.loadingView.setVisibility(visibility);
        }
    }
}
