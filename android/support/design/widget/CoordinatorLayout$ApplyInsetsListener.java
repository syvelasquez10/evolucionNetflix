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
import android.support.design.R$style;
import android.support.design.R$styleable;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.view.ViewGroup$OnHierarchyChangeListener;
import android.support.v4.view.NestedScrollingParentHelper;
import java.util.List;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Comparator;
import android.view.ViewGroup;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import android.support.v4.view.OnApplyWindowInsetsListener;

final class CoordinatorLayout$ApplyInsetsListener implements OnApplyWindowInsetsListener
{
    final /* synthetic */ CoordinatorLayout this$0;
    
    CoordinatorLayout$ApplyInsetsListener(final CoordinatorLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        this.this$0.setWindowInsets(windowInsetsCompat);
        return windowInsetsCompat.consumeSystemWindowInsets();
    }
}
