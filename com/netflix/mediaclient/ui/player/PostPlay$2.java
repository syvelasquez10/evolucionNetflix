// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class PostPlay$2 implements View$OnClickListener
{
    final /* synthetic */ PostPlay this$0;
    
    PostPlay$2(final PostPlay this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        Log.d("nf_postplay", "Interrupter mode, continue");
        this.this$0.moveFromInterruptedToPlaying();
    }
}
