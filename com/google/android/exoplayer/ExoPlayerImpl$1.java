// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.os.Message;
import android.os.Handler;

class ExoPlayerImpl$1 extends Handler
{
    final /* synthetic */ ExoPlayerImpl this$0;
    
    ExoPlayerImpl$1(final ExoPlayerImpl this$0) {
        this.this$0 = this$0;
    }
    
    public void handleMessage(final Message message) {
        this.this$0.handleEvent(message);
    }
}
