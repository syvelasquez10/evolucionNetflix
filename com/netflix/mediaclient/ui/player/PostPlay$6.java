// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

class PostPlay$6 implements Runnable
{
    final /* synthetic */ PostPlay this$0;
    
    PostPlay$6(final PostPlay this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.onTick(this.this$0.autoplayTimer.getTime());
    }
}
