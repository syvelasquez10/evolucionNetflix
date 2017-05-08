// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

class PostPlay$5 implements Runnable
{
    final /* synthetic */ PostPlay this$0;
    final /* synthetic */ PostPlayCallToAction val$autoPlayCta;
    
    PostPlay$5(final PostPlay this$0, final PostPlayCallToAction val$autoPlayCta) {
        this.this$0 = this$0;
        this.val$autoPlayCta = val$autoPlayCta;
    }
    
    @Override
    public void run() {
        this.val$autoPlayCta.playAction(true);
    }
}
