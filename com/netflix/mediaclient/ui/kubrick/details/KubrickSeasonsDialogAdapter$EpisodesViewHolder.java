// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.graphics.Typeface;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import android.view.View;
import android.view.View$OnClickListener;
import android.support.v7.widget.RecyclerView$ViewHolder;

public class KubrickSeasonsDialogAdapter$EpisodesViewHolder extends RecyclerView$ViewHolder implements View$OnClickListener
{
    View selectIndicator;
    TextView text;
    final /* synthetic */ KubrickSeasonsDialogAdapter this$0;
    
    public KubrickSeasonsDialogAdapter$EpisodesViewHolder(final KubrickSeasonsDialogAdapter this$0, final View view) {
        this.this$0 = this$0;
        super(view);
        this.text = (TextView)view.findViewById(2131624208);
        this.selectIndicator = view.findViewById(2131624207);
        if (view != null) {
            view.setOnClickListener((View$OnClickListener)this);
        }
    }
    
    public void onClick(final View view) {
        if (Log.isLoggable()) {
            Log.v("sdf", "VideoViewHolder - onClick");
        }
        this.this$0.setItemClicked(this.getPosition());
    }
}
