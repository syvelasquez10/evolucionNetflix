// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator.android.osp;

import android.database.DataSetObserver;
import android.view.View$MeasureSpec;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.view.ViewConfiguration;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.graphics.Canvas;
import android.view.accessibility.AccessibilityEvent;
import android.view.KeyEvent;
import java.util.List;
import java.util.Collections;
import android.os.SystemClock;
import android.view.SoundEffectConstants;
import android.view.FocusFinder;
import android.util.Log;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.view.VelocityTracker;
import android.graphics.Rect;
import java.lang.reflect.Method;
import android.widget.Scroller;
import android.os.Parcelable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.View;
import java.util.ArrayList;
import android.support.v4.view.PagerAdapter;
import android.view.animation.Interpolator;
import java.util.Comparator;
import android.view.ViewGroup;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;

public class ViewPager$LayoutParams extends ViewGroup$LayoutParams
{
    int childIndex;
    public int gravity;
    public boolean isDecor;
    boolean needsMeasure;
    int position;
    float widthFactor;
    
    public ViewPager$LayoutParams() {
        super(-1, -1);
        this.widthFactor = 0.0f;
    }
    
    public ViewPager$LayoutParams(final Context context, final AttributeSet set) {
        super(context, set);
        this.widthFactor = 0.0f;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, ViewPager.LAYOUT_ATTRS);
        this.gravity = obtainStyledAttributes.getInteger(0, 48);
        obtainStyledAttributes.recycle();
    }
}
