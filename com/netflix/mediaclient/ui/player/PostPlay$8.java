// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;

class PostPlay$8 implements Runnable
{
    final /* synthetic */ PostPlay this$0;
    
    PostPlay$8(final PostPlay this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mPlayerFragment == null) {
            Log.e("nf_postplay", "onInterrupterDismiss() - called with null PlayerFragment!");
        }
        else {
            Log.d("nf_postplay", "After 60 minutes of waiting for user input, stop player ui");
            if (!this.this$0.mPlayerFragment.isActivityValid()) {
                this.this$0.mPlayerFragment.finish();
            }
        }
    }
}
