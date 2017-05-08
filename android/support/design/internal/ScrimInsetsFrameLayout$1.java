// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v4.view.ViewCompat;
import android.graphics.Rect;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import android.support.v4.view.OnApplyWindowInsetsListener;

class ScrimInsetsFrameLayout$1 implements OnApplyWindowInsetsListener
{
    final /* synthetic */ ScrimInsetsFrameLayout this$0;
    
    ScrimInsetsFrameLayout$1(final ScrimInsetsFrameLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        if (this.this$0.mInsets == null) {
            this.this$0.mInsets = new Rect();
        }
        this.this$0.mInsets.set(windowInsetsCompat.getSystemWindowInsetLeft(), windowInsetsCompat.getSystemWindowInsetTop(), windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom());
        this.this$0.onInsetsChanged(windowInsetsCompat);
        this.this$0.setWillNotDraw(!windowInsetsCompat.hasSystemWindowInsets() || this.this$0.mInsetForeground == null);
        ViewCompat.postInvalidateOnAnimation((View)this.this$0);
        return windowInsetsCompat.consumeSystemWindowInsets();
    }
}
