// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewConfigurationCompat;
import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.os.TraceCompat;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.SparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View$MeasureSpec;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.recyclerview.R$styleable;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.Rect;
import android.support.v4.view.NestedScrollingChildHelper;
import java.util.List;
import java.util.ArrayList;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.NestedScrollingChild;
import android.view.ViewGroup;

class RecyclerView$2 implements Runnable
{
    final /* synthetic */ RecyclerView this$0;
    
    RecyclerView$2(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mItemAnimator != null) {
            this.this$0.mItemAnimator.runPendingAnimations();
        }
        this.this$0.mPostedAnimatorRunner = false;
    }
}
