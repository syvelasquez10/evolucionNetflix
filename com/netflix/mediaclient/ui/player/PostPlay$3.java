// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class PostPlay$3 implements View$OnClickListener
{
    final /* synthetic */ PostPlay this$0;
    
    PostPlay$3(final PostPlay this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        Log.d("nf_postplay", "Interrupter mode, stop");
        if (this.this$0.mPlayerFragment != null) {
            this.this$0.mPlayerFragment.finish();
        }
    }
}
