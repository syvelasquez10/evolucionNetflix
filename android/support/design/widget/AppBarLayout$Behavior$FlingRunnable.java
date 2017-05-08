// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.view.ViewConfiguration;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import java.util.List;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.widget.ScrollerCompat;
import java.lang.ref.WeakReference;
import android.view.View;
import android.support.v4.view.ViewCompat;

class AppBarLayout$Behavior$FlingRunnable implements Runnable
{
    private final AppBarLayout mLayout;
    private final CoordinatorLayout mParent;
    final /* synthetic */ AppBarLayout$Behavior this$0;
    
    AppBarLayout$Behavior$FlingRunnable(final AppBarLayout$Behavior this$0, final CoordinatorLayout mParent, final AppBarLayout mLayout) {
        this.this$0 = this$0;
        this.mParent = mParent;
        this.mLayout = mLayout;
    }
    
    @Override
    public void run() {
        if (this.mLayout != null && this.this$0.mScroller != null && this.this$0.mScroller.computeScrollOffset()) {
            this.this$0.setAppBarTopBottomOffset(this.mParent, this.mLayout, this.this$0.mScroller.getCurrY());
            ViewCompat.postOnAnimation((View)this.mLayout, this);
        }
    }
}
