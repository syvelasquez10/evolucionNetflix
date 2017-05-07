// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import java.io.Serializable;
import java.util.Collection;
import android.support.v7.widget.RecyclerView;
import android.view.ViewParent;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import com.netflix.mediaclient.ui.details.IEpisodeView;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.widget.Checkable;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import android.support.v7.widget.RecyclerView$Adapter;
import android.view.ViewGroup;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.view.View;
import android.view.View$OnClickListener;
import android.support.v7.widget.RecyclerView$ViewHolder;

class RecyclerViewHeaderAdapter$VideoViewHolder extends RecyclerView$ViewHolder implements View$OnClickListener
{
    final /* synthetic */ RecyclerViewHeaderAdapter this$0;
    
    public RecyclerViewHeaderAdapter$VideoViewHolder(final RecyclerViewHeaderAdapter this$0, final View view, final Context context) {
        this.this$0 = this$0;
        super(createFrameView(context));
        if (Log.isLoggable()) {
            Log.v("RecyclerViewHeaderAdapter", "Creating new VideoViewHolder - child: " + view);
        }
        if (view != null) {
            ((ViewGroup)this.itemView).addView(view);
            view.setOnClickListener((View$OnClickListener)this);
        }
    }
    
    public void onClick(final View view) {
        if (Log.isLoggable()) {
            Log.v("RecyclerViewHeaderAdapter", "VideoViewHolder - onClick");
        }
        this.this$0.setSingleChoiceModeState(view, this.getPosition());
    }
}
