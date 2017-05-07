// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.os.Parcelable;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.Rect;
import java.util.ArrayList;
import android.view.View;
import java.util.List;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.ViewGroup;
import android.util.Log;
import android.view.animation.Interpolator;

public class RecyclerView$SmoothScroller$Action
{
    private boolean changed;
    private int consecutiveUpdates;
    private int mDuration;
    private int mDx;
    private int mDy;
    private Interpolator mInterpolator;
    
    public RecyclerView$SmoothScroller$Action(final int n, final int n2) {
        this(n, n2, Integer.MIN_VALUE, null);
    }
    
    public RecyclerView$SmoothScroller$Action(final int mDx, final int mDy, final int mDuration, final Interpolator mInterpolator) {
        this.changed = false;
        this.consecutiveUpdates = 0;
        this.mDx = mDx;
        this.mDy = mDy;
        this.mDuration = mDuration;
        this.mInterpolator = mInterpolator;
    }
    
    private void runIfNecessary(final RecyclerView recyclerView) {
        if (this.changed) {
            this.validate();
            if (this.mInterpolator == null) {
                if (this.mDuration == Integer.MIN_VALUE) {
                    recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
                }
                else {
                    recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration);
                }
            }
            else {
                recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
            }
            ++this.consecutiveUpdates;
            if (this.consecutiveUpdates > 10) {
                Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
            }
            this.changed = false;
            return;
        }
        this.consecutiveUpdates = 0;
    }
    
    private void validate() {
        if (this.mInterpolator != null && this.mDuration < 1) {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
        if (this.mDuration < 1) {
            throw new IllegalStateException("Scroll duration must be a positive number");
        }
    }
    
    public void update(final int mDx, final int mDy, final int mDuration, final Interpolator mInterpolator) {
        this.mDx = mDx;
        this.mDy = mDy;
        this.mDuration = mDuration;
        this.mInterpolator = mInterpolator;
        this.changed = true;
    }
}
