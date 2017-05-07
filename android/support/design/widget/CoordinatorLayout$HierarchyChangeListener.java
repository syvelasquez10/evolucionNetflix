// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.content.ContextCompat;
import android.graphics.drawable.ColorDrawable;
import android.util.SparseArray;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import java.io.Serializable;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import java.util.HashMap;
import android.text.TextUtils;
import android.support.v4.view.GravityCompat;
import java.util.Collections;
import android.util.Log;
import android.support.v4.view.ViewCompat;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.design.R$style;
import android.support.design.R$styleable;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.WindowInsetsCompat;
import java.util.List;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Comparator;
import android.view.ViewGroup;
import android.view.View;
import android.view.ViewGroup$OnHierarchyChangeListener;

final class CoordinatorLayout$HierarchyChangeListener implements ViewGroup$OnHierarchyChangeListener
{
    final /* synthetic */ CoordinatorLayout this$0;
    
    CoordinatorLayout$HierarchyChangeListener(final CoordinatorLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void onChildViewAdded(final View view, final View view2) {
        if (this.this$0.mOnHierarchyChangeListener != null) {
            this.this$0.mOnHierarchyChangeListener.onChildViewAdded(view, view2);
        }
    }
    
    public void onChildViewRemoved(final View view, final View view2) {
        this.this$0.dispatchDependentViewRemoved(view2);
        if (this.this$0.mOnHierarchyChangeListener != null) {
            this.this$0.mOnHierarchyChangeListener.onChildViewRemoved(view, view2);
        }
    }
}
