// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemSelectedListener;

class EpisodesFrag$3 implements AdapterView$OnItemSelectedListener
{
    final /* synthetic */ EpisodesFrag this$0;
    
    EpisodesFrag$3(final EpisodesFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Season spinner selected position: " + n);
        }
        final SeasonDetails seasonDetails = (SeasonDetails)this.this$0.spinner.getItemAtPosition(n);
        if (seasonDetails == null && Log.isLoggable()) {
            Log.w("EpisodesFrag", "null season details retrieved for position: " + n);
        }
        this.this$0.leWrapper.showLoadingView(true);
        ((EpisodesAdapter)this.this$0.episodesAdapter).updateSeasonDetails(seasonDetails);
        this.this$0.selectedEpisodeIndex = -1;
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
        Log.v("EpisodesFrag", "Season spinner - Nothing selected");
    }
}
