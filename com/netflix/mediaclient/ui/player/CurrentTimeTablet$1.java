// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import java.util.concurrent.atomic.AtomicBoolean;
import android.view.View;
import com.netflix.mediaclient.ui.common.CurrentTimeAnimation;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class CurrentTimeTablet$1 implements Animation$AnimationListener
{
    final /* synthetic */ CurrentTimeTablet this$0;
    final /* synthetic */ BottomPanel val$bp;
    
    CurrentTimeTablet$1(final CurrentTimeTablet this$0, final BottomPanel val$bp) {
        this.this$0 = this$0;
        this.val$bp = val$bp;
    }
    
    public void onAnimationEnd(final Animation animation) {
        while (true) {
            while (true) {
                Label_0217: {
                    synchronized (this.this$0.lock) {
                        this.this$0.currentTimeAnimationInProgress = false;
                        final int dipToPixels = AndroidUtils.dipToPixels((Context)this.this$0.context, 40);
                        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.this$0.currentTime.getLayoutParams();
                        layoutParams.setMargins(this.val$bp.getTimeX(this.this$0.currentTime), 0, 0, dipToPixels);
                        this.this$0.currentTime.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                        if (this.this$0.lock.animationCanceled) {
                            Log.d("screen", "Animation was canceled, do nothing!");
                        }
                        else {
                            Log.d("screen", "Animation was NOT canceled, expand!");
                            if (!this.this$0.mBifDownloaded.get()) {
                                break Label_0217;
                            }
                            Log.w("screen", "Bifs are downloaded, remove current time background");
                            this.this$0.currentTime.setBackgroundResource(0);
                            final RelativeLayout$LayoutParams layoutParams2 = (RelativeLayout$LayoutParams)this.this$0.currentTimeExp.getLayoutParams();
                            layoutParams2.setMargins(this.val$bp.getTimeX(this.this$0.currentTimeExp), 0, 0, dipToPixels);
                            this.this$0.currentTimeExp.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
                            this.this$0.currentTimeExp.setVisibility(0);
                        }
                        this.this$0.lock.animationCanceled = false;
                        return;
                    }
                }
                Log.w("screen", "Bifs are not downloaded, do not remove current time background");
                continue;
            }
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
        synchronized (this.this$0.lock) {
            this.this$0.currentTimeAnimationInProgress = true;
        }
    }
}
