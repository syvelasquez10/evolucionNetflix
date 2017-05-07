// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.Bitmap;
import android.util.Log;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff$Mode;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.view.View;

class SlidingPaneLayout$DisableLayerRunnable implements Runnable
{
    final View mChildView;
    final /* synthetic */ SlidingPaneLayout this$0;
    
    SlidingPaneLayout$DisableLayerRunnable(final SlidingPaneLayout this$0, final View mChildView) {
        this.this$0 = this$0;
        this.mChildView = mChildView;
    }
    
    @Override
    public void run() {
        if (this.mChildView.getParent() == this.this$0) {
            ViewCompat.setLayerType(this.mChildView, 0, null);
            this.this$0.invalidateChildRegion(this.mChildView);
        }
        this.this$0.mPostedRunnables.remove(this);
    }
}
