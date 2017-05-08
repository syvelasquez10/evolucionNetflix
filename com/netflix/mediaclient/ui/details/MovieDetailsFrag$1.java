// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

class MovieDetailsFrag$1 implements Runnable
{
    final /* synthetic */ MovieDetailsFrag this$0;
    
    MovieDetailsFrag$1(final MovieDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.recyclerView.scrollToPosition(0);
    }
}
