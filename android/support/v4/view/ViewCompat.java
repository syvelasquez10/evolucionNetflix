// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.view.ViewParent;
import android.view.View;
import android.os.Build$VERSION;

public class ViewCompat
{
    static final ViewCompat$ViewCompatImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = new ViewCompat$LollipopViewCompatImpl();
            return;
        }
        if (sdk_INT >= 19) {
            IMPL = new ViewCompat$KitKatViewCompatImpl();
            return;
        }
        if (sdk_INT >= 17) {
            IMPL = new ViewCompat$JbMr1ViewCompatImpl();
            return;
        }
        if (sdk_INT >= 16) {
            IMPL = new ViewCompat$JBViewCompatImpl();
            return;
        }
        if (sdk_INT >= 14) {
            IMPL = new ViewCompat$ICSViewCompatImpl();
            return;
        }
        if (sdk_INT >= 11) {
            IMPL = new ViewCompat$HCViewCompatImpl();
            return;
        }
        if (sdk_INT >= 9) {
            IMPL = new ViewCompat$GBViewCompatImpl();
            return;
        }
        if (sdk_INT >= 7) {
            IMPL = new ViewCompat$EclairMr1ViewCompatImpl();
            return;
        }
        IMPL = new ViewCompat$BaseViewCompatImpl();
    }
    
    public static ViewPropertyAnimatorCompat animate(final View view) {
        return ViewCompat.IMPL.animate(view);
    }
    
    public static boolean canScrollHorizontally(final View view, final int n) {
        return ViewCompat.IMPL.canScrollHorizontally(view, n);
    }
    
    public static boolean canScrollVertically(final View view, final int n) {
        return ViewCompat.IMPL.canScrollVertically(view, n);
    }
    
    public static int combineMeasuredStates(final int n, final int n2) {
        return ViewCompat.IMPL.combineMeasuredStates(n, n2);
    }
    
    public static WindowInsetsCompat dispatchApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return ViewCompat.IMPL.dispatchApplyWindowInsets(view, windowInsetsCompat);
    }
    
    public static float getAlpha(final View view) {
        return ViewCompat.IMPL.getAlpha(view);
    }
    
    public static float getElevation(final View view) {
        return ViewCompat.IMPL.getElevation(view);
    }
    
    public static boolean getFitsSystemWindows(final View view) {
        return ViewCompat.IMPL.getFitsSystemWindows(view);
    }
    
    public static int getImportantForAccessibility(final View view) {
        return ViewCompat.IMPL.getImportantForAccessibility(view);
    }
    
    public static int getLayerType(final View view) {
        return ViewCompat.IMPL.getLayerType(view);
    }
    
    public static int getLayoutDirection(final View view) {
        return ViewCompat.IMPL.getLayoutDirection(view);
    }
    
    public static int getMeasuredState(final View view) {
        return ViewCompat.IMPL.getMeasuredState(view);
    }
    
    public static int getMeasuredWidthAndState(final View view) {
        return ViewCompat.IMPL.getMeasuredWidthAndState(view);
    }
    
    public static int getMinimumHeight(final View view) {
        return ViewCompat.IMPL.getMinimumHeight(view);
    }
    
    public static int getMinimumWidth(final View view) {
        return ViewCompat.IMPL.getMinimumWidth(view);
    }
    
    public static int getOverScrollMode(final View view) {
        return ViewCompat.IMPL.getOverScrollMode(view);
    }
    
    public static int getPaddingEnd(final View view) {
        return ViewCompat.IMPL.getPaddingEnd(view);
    }
    
    public static int getPaddingStart(final View view) {
        return ViewCompat.IMPL.getPaddingStart(view);
    }
    
    public static ViewParent getParentForAccessibility(final View view) {
        return ViewCompat.IMPL.getParentForAccessibility(view);
    }
    
    public static float getTranslationX(final View view) {
        return ViewCompat.IMPL.getTranslationX(view);
    }
    
    public static float getTranslationY(final View view) {
        return ViewCompat.IMPL.getTranslationY(view);
    }
    
    public static int getWindowSystemUiVisibility(final View view) {
        return ViewCompat.IMPL.getWindowSystemUiVisibility(view);
    }
    
    public static float getZ(final View view) {
        return ViewCompat.IMPL.getZ(view);
    }
    
    public static boolean hasAccessibilityDelegate(final View view) {
        return ViewCompat.IMPL.hasAccessibilityDelegate(view);
    }
    
    public static boolean hasOverlappingRendering(final View view) {
        return ViewCompat.IMPL.hasOverlappingRendering(view);
    }
    
    public static boolean hasTransientState(final View view) {
        return ViewCompat.IMPL.hasTransientState(view);
    }
    
    public static boolean isAttachedToWindow(final View view) {
        return ViewCompat.IMPL.isAttachedToWindow(view);
    }
    
    public static boolean isLaidOut(final View view) {
        return ViewCompat.IMPL.isLaidOut(view);
    }
    
    public static boolean isPaddingRelative(final View view) {
        return ViewCompat.IMPL.isPaddingRelative(view);
    }
    
    public static void jumpDrawablesToCurrentState(final View view) {
        ViewCompat.IMPL.jumpDrawablesToCurrentState(view);
    }
    
    public static WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return ViewCompat.IMPL.onApplyWindowInsets(view, windowInsetsCompat);
    }
    
    public static void postInvalidateOnAnimation(final View view) {
        ViewCompat.IMPL.postInvalidateOnAnimation(view);
    }
    
    public static void postOnAnimation(final View view, final Runnable runnable) {
        ViewCompat.IMPL.postOnAnimation(view, runnable);
    }
    
    public static void postOnAnimationDelayed(final View view, final Runnable runnable, final long n) {
        ViewCompat.IMPL.postOnAnimationDelayed(view, runnable, n);
    }
    
    public static void requestApplyInsets(final View view) {
        ViewCompat.IMPL.requestApplyInsets(view);
    }
    
    public static int resolveSizeAndState(final int n, final int n2, final int n3) {
        return ViewCompat.IMPL.resolveSizeAndState(n, n2, n3);
    }
    
    public static void setAccessibilityDelegate(final View view, final AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.IMPL.setAccessibilityDelegate(view, accessibilityDelegateCompat);
    }
    
    public static void setActivated(final View view, final boolean b) {
        ViewCompat.IMPL.setActivated(view, b);
    }
    
    public static void setAlpha(final View view, final float n) {
        ViewCompat.IMPL.setAlpha(view, n);
    }
    
    public static void setBackgroundTintList(final View view, final ColorStateList list) {
        ViewCompat.IMPL.setBackgroundTintList(view, list);
    }
    
    public static void setBackgroundTintMode(final View view, final PorterDuff$Mode porterDuff$Mode) {
        ViewCompat.IMPL.setBackgroundTintMode(view, porterDuff$Mode);
    }
    
    public static void setElevation(final View view, final float n) {
        ViewCompat.IMPL.setElevation(view, n);
    }
    
    public static void setImportantForAccessibility(final View view, final int n) {
        ViewCompat.IMPL.setImportantForAccessibility(view, n);
    }
    
    public static void setLayerType(final View view, final int n, final Paint paint) {
        ViewCompat.IMPL.setLayerType(view, n, paint);
    }
    
    public static void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        ViewCompat.IMPL.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
    }
    
    public static void setPaddingRelative(final View view, final int n, final int n2, final int n3, final int n4) {
        ViewCompat.IMPL.setPaddingRelative(view, n, n2, n3, n4);
    }
    
    public static void setSaveFromParentEnabled(final View view, final boolean b) {
        ViewCompat.IMPL.setSaveFromParentEnabled(view, b);
    }
    
    public static void setTranslationX(final View view, final float n) {
        ViewCompat.IMPL.setTranslationX(view, n);
    }
    
    public static void setTranslationY(final View view, final float n) {
        ViewCompat.IMPL.setTranslationY(view, n);
    }
}
