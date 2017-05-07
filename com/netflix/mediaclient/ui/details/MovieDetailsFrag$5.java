// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

class MovieDetailsFrag$5 implements Runnable
{
    final /* synthetic */ MovieDetailsFrag this$0;
    final /* synthetic */ int val$scrollY;
    
    MovieDetailsFrag$5(final MovieDetailsFrag this$0, final int val$scrollY) {
        this.this$0 = this$0;
        this.val$scrollY = val$scrollY;
    }
    
    @Override
    public void run() {
        this.this$0.primaryView.scrollTo(0, this.val$scrollY);
    }
}
