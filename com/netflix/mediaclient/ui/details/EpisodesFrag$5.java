// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.Log;

class EpisodesFrag$5 implements Runnable
{
    final /* synthetic */ EpisodesFrag this$0;
    final /* synthetic */ int val$position;
    
    EpisodesFrag$5(final EpisodesFrag this$0, final int val$position) {
        this.this$0 = this$0;
        this.val$position = val$position;
    }
    
    @Override
    public void run() {
        if (Log.isLoggable()) {
            Log.v("EpisodesFrag", "Setting item checked, position: " + this.val$position);
        }
        this.this$0.episodesAdapter.setItemChecked(this.val$position, this.this$0.recyclerView);
    }
}
