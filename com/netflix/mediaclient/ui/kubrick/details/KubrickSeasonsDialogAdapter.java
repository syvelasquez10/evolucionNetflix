// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView$ViewHolder;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView$Adapter;

public class KubrickSeasonsDialogAdapter extends RecyclerView$Adapter<KubrickSeasonsDialogAdapter$EpisodesViewHolder>
{
    private static final String TAG = "sdf";
    private final Context context;
    private int curSelectedPosition;
    private KubrickSeasonsDialogAdapter$OnSeasonSelectedListener listener;
    private final List<String> seasons;
    
    public KubrickSeasonsDialogAdapter(final Context context, final KubrickSeasonsDialogAdapter$OnSeasonSelectedListener listener) {
        this.seasons = new ArrayList<String>();
        this.context = context;
        this.listener = listener;
    }
    
    private void setItemClicked(final int curSelectedPosition) {
        this.curSelectedPosition = curSelectedPosition;
        this.listener.onSeasonSelected(curSelectedPosition);
        this.notifyDataSetChanged();
    }
    
    @Override
    public int getItemCount() {
        return this.seasons.size();
    }
    
    @Override
    public void onBindViewHolder(final KubrickSeasonsDialogAdapter$EpisodesViewHolder kubrickSeasonsDialogAdapter$EpisodesViewHolder, int visibility) {
        final int n = 0;
        kubrickSeasonsDialogAdapter$EpisodesViewHolder.text.setText((CharSequence)this.seasons.get(visibility));
        int n2;
        if (this.curSelectedPosition == visibility) {
            n2 = 2131558518;
        }
        else {
            n2 = 2131558536;
        }
        kubrickSeasonsDialogAdapter$EpisodesViewHolder.text.setTextColor(this.context.getResources().getColor(n2));
        final TextView text = kubrickSeasonsDialogAdapter$EpisodesViewHolder.text;
        int n3;
        if (this.curSelectedPosition == visibility) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        text.setTypeface((Typeface)null, n3);
        final View selectIndicator = kubrickSeasonsDialogAdapter$EpisodesViewHolder.selectIndicator;
        if (this.curSelectedPosition == visibility) {
            visibility = n;
        }
        else {
            visibility = 4;
        }
        selectIndicator.setVisibility(visibility);
    }
    
    @Override
    public KubrickSeasonsDialogAdapter$EpisodesViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return new KubrickSeasonsDialogAdapter$EpisodesViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(2130903110, viewGroup, false));
    }
    
    public int updateSeasonData(final List<SeasonDetails> list, final int n) {
        this.seasons.clear();
        for (int i = 0; i < list.size(); ++i) {
            final SeasonDetails seasonDetails = list.get(i);
            this.seasons.add(seasonDetails.getSeasonNumberTitle(this.context).toUpperCase());
            if (n == seasonDetails.getSeasonNumber()) {
                this.curSelectedPosition = i;
            }
        }
        this.notifyDataSetChanged();
        return this.curSelectedPosition;
    }
}
