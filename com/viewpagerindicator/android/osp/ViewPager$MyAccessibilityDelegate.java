// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator.android.osp;

import android.database.DataSetObserver;
import android.view.View$MeasureSpec;
import android.view.ViewConfiguration;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.graphics.Canvas;
import android.view.KeyEvent;
import java.util.List;
import java.util.Collections;
import android.os.SystemClock;
import android.view.SoundEffectConstants;
import android.view.FocusFinder;
import android.view.ViewGroup$LayoutParams;
import android.util.Log;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.view.VelocityTracker;
import android.graphics.Rect;
import java.lang.reflect.Method;
import android.widget.Scroller;
import android.os.Parcelable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.EdgeEffectCompat;
import java.util.ArrayList;
import android.support.v4.view.PagerAdapter;
import android.view.animation.Interpolator;
import java.util.Comparator;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.support.v4.view.AccessibilityDelegateCompat;

class ViewPager$MyAccessibilityDelegate extends AccessibilityDelegateCompat
{
    final /* synthetic */ ViewPager this$0;
    
    ViewPager$MyAccessibilityDelegate(final ViewPager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)ViewPager.class.getName());
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        boolean scrollable = true;
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
        if (this.this$0.mAdapter == null || this.this$0.mAdapter.getCount() <= 1) {
            scrollable = false;
        }
        accessibilityNodeInfoCompat.setScrollable(scrollable);
        if (this.this$0.mAdapter != null && this.this$0.mCurItem >= 0 && this.this$0.mCurItem < this.this$0.mAdapter.getCount() - 1) {
            accessibilityNodeInfoCompat.addAction(4096);
        }
        if (this.this$0.mAdapter != null && this.this$0.mCurItem > 0 && this.this$0.mCurItem < this.this$0.mAdapter.getCount()) {
            accessibilityNodeInfoCompat.addAction(8192);
        }
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        if (super.performAccessibilityAction(view, n, bundle)) {
            return true;
        }
        switch (n) {
            default: {
                return false;
            }
            case 4096: {
                if (this.this$0.mAdapter != null && this.this$0.mCurItem >= 0 && this.this$0.mCurItem < this.this$0.mAdapter.getCount() - 1) {
                    this.this$0.setCurrentItem(this.this$0.mCurItem + 1);
                    return true;
                }
                return false;
            }
            case 8192: {
                if (this.this$0.mAdapter != null && this.this$0.mCurItem > 0 && this.this$0.mCurItem < this.this$0.mAdapter.getCount()) {
                    this.this$0.setCurrentItem(this.this$0.mCurItem - 1);
                    return true;
                }
                return false;
            }
        }
    }
}
