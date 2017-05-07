// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;

class PostPlay$7 implements Runnable
{
    final /* synthetic */ PostPlay this$0;
    
    PostPlay$7(final PostPlay this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("nf_postplay", "After 60 minutes of waiting for user input, stop player ui");
        if (!this.this$0.mContext.destroyed()) {
            this.this$0.mContext.finish();
        }
    }
}
