// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import java.util.Locale;
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

public class BarkerSeasonsDialogAdapter extends RecyclerView$Adapter<BarkerSeasonsDialogAdapter$EpisodesViewHolder>
{
    private static final String TAG = "sdf";
    private final Context context;
    private int curSelectedPosition;
    private BarkerSeasonsDialogAdapter$OnSeasonSelectedListener listener;
    private final List<String> seasons;
    
    public BarkerSeasonsDialogAdapter(final Context context, final BarkerSeasonsDialogAdapter$OnSeasonSelectedListener listener) {
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
    public void onBindViewHolder(final BarkerSeasonsDialogAdapter$EpisodesViewHolder barkerSeasonsDialogAdapter$EpisodesViewHolder, int visibility) {
        final int n = 0;
        barkerSeasonsDialogAdapter$EpisodesViewHolder.text.setText((CharSequence)this.seasons.get(visibility));
        final TextView text = barkerSeasonsDialogAdapter$EpisodesViewHolder.text;
        int n2;
        if (this.curSelectedPosition == visibility) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        text.setTypeface((Typeface)null, n2);
        final View selectIndicator = barkerSeasonsDialogAdapter$EpisodesViewHolder.selectIndicator;
        if (this.curSelectedPosition == visibility) {
            visibility = n;
        }
        else {
            visibility = 4;
        }
        selectIndicator.setVisibility(visibility);
    }
    
    @Override
    public BarkerSeasonsDialogAdapter$EpisodesViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        return new BarkerSeasonsDialogAdapter$EpisodesViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(2130903169, viewGroup, false));
    }
    
    public int updateSeasonData(final List<SeasonDetails> list, final int n) {
        this.seasons.clear();
        for (int i = 0; i < list.size(); ++i) {
            final SeasonDetails seasonDetails = list.get(i);
            this.seasons.add(seasonDetails.getTitle().toUpperCase(Locale.getDefault()));
            if (n == seasonDetails.getSeasonNumber()) {
                this.curSelectedPosition = i;
            }
        }
        this.notifyDataSetChanged();
        return this.curSelectedPosition;
    }
}
