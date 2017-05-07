// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.animation.Animator;
import java.nio.ByteBuffer;
import android.animation.Animator$AnimatorListener;

class CurrentTimeTablet$1 implements Animator$AnimatorListener
{
    final /* synthetic */ CurrentTimeTablet this$0;
    final /* synthetic */ ByteBuffer val$bb;
    
    CurrentTimeTablet$1(final CurrentTimeTablet this$0, final ByteBuffer val$bb) {
        this.this$0 = this$0;
        this.val$bb = val$bb;
    }
    
    public void onAnimationCancel(final Animator animator) {
        Log.i("CurrentTimeTablet", "startStartAnimation cancel");
    }
    
    public void onAnimationEnd(final Animator animator) {
        Log.i("CurrentTimeTablet", "startStartAnimation end");
        if (this.this$0.mBifDownloaded.get()) {
            Log.w("CurrentTimeTablet", "Bifs are downloaded, setup currentTimeExp layout params");
            this.this$0.context.getScreen().startBif(this.val$bb);
            return;
        }
        Log.w("CurrentTimeTablet", "Bifs are not downloaded, do not setup currentTimeExp layout params");
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
    }
}
