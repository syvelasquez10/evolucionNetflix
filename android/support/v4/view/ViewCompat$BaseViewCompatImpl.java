// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityEvent;
import android.graphics.drawable.Drawable;
import android.view.ViewParent;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.util.Log;
import android.view.View;
import java.util.WeakHashMap;
import java.lang.reflect.Method;

class ViewCompat$BaseViewCompatImpl implements ViewCompat$ViewCompatImpl
{
    private Method mDispatchFinishTemporaryDetach;
    private Method mDispatchStartTemporaryDetach;
    private boolean mTempDetachBound;
    WeakHashMap<View, ViewPropertyAnimatorCompat> mViewPropertyAnimatorCompatMap;
    
    ViewCompat$BaseViewCompatImpl() {
        this.mViewPropertyAnimatorCompatMap = null;
    }
    
    private void bindTempDetach() {
        while (true) {
            try {
                this.mDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", (Class<?>[])new Class[0]);
                this.mDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", (Class<?>[])new Class[0]);
                this.mTempDetachBound = true;
            }
            catch (NoSuchMethodException ex) {
                Log.e("ViewCompat", "Couldn't find method", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    @Override
    public ViewPropertyAnimatorCompat animate(final View view) {
        return new ViewPropertyAnimatorCompat(view);
    }
    
    @Override
    public boolean canScrollHorizontally(final View view, final int n) {
        return false;
    }
    
    @Override
    public boolean canScrollVertically(final View view, final int n) {
        return false;
    }
    
    @Override
    public void dispatchFinishTemporaryDetach(final View view) {
        if (!this.mTempDetachBound) {
            this.bindTempDetach();
        }
        if (this.mDispatchFinishTemporaryDetach != null) {
            try {
                this.mDispatchFinishTemporaryDetach.invoke(view, new Object[0]);
                return;
            }
            catch (Exception ex) {
                Log.d("ViewCompat", "Error calling dispatchFinishTemporaryDetach", (Throwable)ex);
                return;
            }
        }
        view.onFinishTemporaryDetach();
    }
    
    @Override
    public void dispatchStartTemporaryDetach(final View view) {
        if (!this.mTempDetachBound) {
            this.bindTempDetach();
        }
        if (this.mDispatchStartTemporaryDetach != null) {
            try {
                this.mDispatchStartTemporaryDetach.invoke(view, new Object[0]);
                return;
            }
            catch (Exception ex) {
                Log.d("ViewCompat", "Error calling dispatchStartTemporaryDetach", (Throwable)ex);
                return;
            }
        }
        view.onStartTemporaryDetach();
    }
    
    @Override
    public int getAccessibilityLiveRegion(final View view) {
        return 0;
    }
    
    @Override
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final View view) {
        return null;
    }
    
    @Override
    public float getAlpha(final View view) {
        return 1.0f;
    }
    
    @Override
    public float getElevation(final View view) {
        return 0.0f;
    }
    
    @Override
    public boolean getFitsSystemWindows(final View view) {
        return false;
    }
    
    long getFrameTime() {
        return 10L;
    }
    
    @Override
    public int getImportantForAccessibility(final View view) {
        return 0;
    }
    
    @Override
    public int getLabelFor(final View view) {
        return 0;
    }
    
    @Override
    public int getLayerType(final View view) {
        return 0;
    }
    
    @Override
    public int getLayoutDirection(final View view) {
        return 0;
    }
    
    @Override
    public int getMeasuredHeightAndState(final View view) {
        return view.getMeasuredHeight();
    }
    
    @Override
    public int getMeasuredState(final View view) {
        return 0;
    }
    
    @Override
    public int getMeasuredWidthAndState(final View view) {
        return view.getMeasuredWidth();
    }
    
    @Override
    public int getMinimumHeight(final View view) {
        return 0;
    }
    
    @Override
    public int getMinimumWidth(final View view) {
        return 0;
    }
    
    @Override
    public int getOverScrollMode(final View view) {
        return 2;
    }
    
    @Override
    public int getPaddingEnd(final View view) {
        return view.getPaddingRight();
    }
    
    @Override
    public int getPaddingStart(final View view) {
        return view.getPaddingLeft();
    }
    
    @Override
    public ViewParent getParentForAccessibility(final View view) {
        return view.getParent();
    }
    
    @Override
    public float getPivotX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getPivotY(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getRotation(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getRotationX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getRotationY(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getScaleX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getScaleY(final View view) {
        return 0.0f;
    }
    
    @Override
    public String getTransitionName(final View view) {
        return null;
    }
    
    @Override
    public float getTranslationX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getTranslationY(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getTranslationZ(final View view) {
        return 0.0f;
    }
    
    @Override
    public int getWindowSystemUiVisibility(final View view) {
        return 0;
    }
    
    @Override
    public float getX(final View view) {
        return 0.0f;
    }
    
    @Override
    public float getY(final View view) {
        return 0.0f;
    }
    
    @Override
    public boolean hasAccessibilityDelegate(final View view) {
        return false;
    }
    
    @Override
    public boolean hasTransientState(final View view) {
        return false;
    }
    
    @Override
    public boolean isOpaque(final View view) {
        final boolean b = false;
        final Drawable background = view.getBackground();
        boolean b2 = b;
        if (background != null) {
            b2 = b;
            if (background.getOpacity() == -1) {
                b2 = true;
            }
        }
        return b2;
    }
    
    @Override
    public void jumpDrawablesToCurrentState(final View view) {
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }
    
    @Override
    public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        return false;
    }
    
    @Override
    public void postInvalidateOnAnimation(final View view) {
        view.invalidate();
    }
    
    @Override
    public void postInvalidateOnAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        view.invalidate(n, n2, n3, n4);
    }
    
    @Override
    public void postOnAnimation(final View view, final Runnable runnable) {
        view.postDelayed(runnable, this.getFrameTime());
    }
    
    @Override
    public void postOnAnimationDelayed(final View view, final Runnable runnable, final long n) {
        view.postDelayed(runnable, this.getFrameTime() + n);
    }
    
    @Override
    public void requestApplyInsets(final View view) {
    }
    
    @Override
    public int resolveSizeAndState(final int n, final int n2, final int n3) {
        return View.resolveSize(n, n2);
    }
    
    @Override
    public void setAccessibilityDelegate(final View view, final AccessibilityDelegateCompat accessibilityDelegateCompat) {
    }
    
    @Override
    public void setAccessibilityLiveRegion(final View view, final int n) {
    }
    
    @Override
    public void setAlpha(final View view, final float n) {
    }
    
    @Override
    public void setChildrenDrawingOrderEnabled(final ViewGroup viewGroup, final boolean b) {
    }
    
    @Override
    public void setElevation(final View view, final float n) {
    }
    
    @Override
    public void setHasTransientState(final View view, final boolean b) {
    }
    
    @Override
    public void setImportantForAccessibility(final View view, final int n) {
    }
    
    @Override
    public void setLabelFor(final View view, final int n) {
    }
    
    @Override
    public void setLayerPaint(final View view, final Paint paint) {
    }
    
    @Override
    public void setLayerType(final View view, final int n, final Paint paint) {
    }
    
    @Override
    public void setLayoutDirection(final View view, final int n) {
    }
    
    @Override
    public void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
    }
    
    @Override
    public void setOverScrollMode(final View view, final int n) {
    }
    
    @Override
    public void setPaddingRelative(final View view, final int n, final int n2, final int n3, final int n4) {
        view.setPadding(n, n2, n3, n4);
    }
    
    @Override
    public void setPivotX(final View view, final float n) {
    }
    
    @Override
    public void setPivotY(final View view, final float n) {
    }
    
    @Override
    public void setRotation(final View view, final float n) {
    }
    
    @Override
    public void setRotationX(final View view, final float n) {
    }
    
    @Override
    public void setRotationY(final View view, final float n) {
    }
    
    @Override
    public void setScaleX(final View view, final float n) {
    }
    
    @Override
    public void setScaleY(final View view, final float n) {
    }
    
    @Override
    public void setTransitionName(final View view, final String s) {
    }
    
    @Override
    public void setTranslationX(final View view, final float n) {
    }
    
    @Override
    public void setTranslationY(final View view, final float n) {
    }
    
    @Override
    public void setTranslationZ(final View view, final float n) {
    }
    
    @Override
    public void setX(final View view, final float n) {
    }
    
    @Override
    public void setY(final View view, final float n) {
    }
}
