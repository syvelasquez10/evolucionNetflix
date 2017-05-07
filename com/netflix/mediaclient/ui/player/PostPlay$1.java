// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class PostPlay$1 implements View$OnTouchListener
{
    final /* synthetic */ PostPlay this$0;
    
    PostPlay$1(final PostPlay this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        Log.d("nf_postplay", "Hijacking tap, do nothing");
        return true;
    }
}
