// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.os.SystemClock;
import android.os.Looper;
import android.view.animation.Interpolator;
import android.os.Handler;

class ValueAnimatorCompatImplEclairMr1$1 implements Runnable
{
    final /* synthetic */ ValueAnimatorCompatImplEclairMr1 this$0;
    
    ValueAnimatorCompatImplEclairMr1$1(final ValueAnimatorCompatImplEclairMr1 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.update();
    }
}
