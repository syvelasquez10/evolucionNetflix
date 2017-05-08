// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

class PostPlayFrag$5 implements Runnable
{
    final /* synthetic */ PostPlayFrag this$0;
    
    PostPlayFrag$5(final PostPlayFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.refreshTimerText();
    }
}
