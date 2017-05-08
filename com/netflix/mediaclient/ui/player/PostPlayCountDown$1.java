// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

class PostPlayCountDown$1 implements Runnable
{
    final /* synthetic */ PostPlayCountDown this$0;
    
    PostPlayCountDown$1(final PostPlayCountDown this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.refreshTimerText();
    }
}
