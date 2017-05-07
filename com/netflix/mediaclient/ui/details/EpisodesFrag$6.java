// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

class EpisodesFrag$6 implements Runnable
{
    final /* synthetic */ EpisodesFrag this$0;
    
    EpisodesFrag$6(final EpisodesFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.setSpinnerSelection();
    }
}
