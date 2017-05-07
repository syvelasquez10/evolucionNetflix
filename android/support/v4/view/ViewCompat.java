// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import java.lang.reflect.Field;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.WeakHashMap;
import java.lang.reflect.Method;
import android.support.annotation.IntDef;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewParent;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.os.Build$VERSION;

public class ViewCompat
{
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    private static final long FAKE_FRAME_TIME = 10L;
    static final ViewCompatImpl IMPL;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    public static final int LAYER_TYPE_HARDWARE = 2;
    public static final int LAYER_TYPE_NONE = 0;
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
    public static final int MEASURED_SIZE_MASK = 16777215;
    public static final int MEASURED_STATE_MASK = -16777216;
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;
    public static final int OVER_SCROLL_ALWAYS = 0;
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    public static final int OVER_SCROLL_NEVER = 2;
    private static final String TAG = "ViewCompat";
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = (ViewCompatImpl)new Api21ViewCompatImpl();
            return;
        }
        if (sdk_INT >= 19) {
            IMPL = (ViewCompatImpl)new KitKatViewCompatImpl();
            return;
        }
        if (sdk_INT >= 17) {
            IMPL = (ViewCompatImpl)new JbMr1ViewCompatImpl();
            return;
        }
        if (sdk_INT >= 16) {
            IMPL = (ViewCompatImpl)new JBViewCompatImpl();
            return;
        }
        if (sdk_INT >= 14) {
            IMPL = (ViewCompatImpl)new ICSViewCompatImpl();
            return;
        }
        if (sdk_INT >= 11) {
            IMPL = (ViewCompatImpl)new HCViewCompatImpl();
            return;
        }
        if (sdk_INT >= 9) {
            IMPL = (ViewCompatImpl)new GBViewCompatImpl();
            return;
        }
        if (sdk_INT >= 7) {
            IMPL = (ViewCompatImpl)new EclairMr1ViewCompatImpl();
            return;
        }
        IMPL = (ViewCompatImpl)new BaseViewCompatImpl();
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
    
    public static void dispatchFinishTemporaryDetach(final View view) {
        ViewCompat.IMPL.dispatchFinishTemporaryDetach(view);
    }
    
    public static void dispatchStartTemporaryDetach(final View view) {
        ViewCompat.IMPL.dispatchStartTemporaryDetach(view);
    }
    
    public static int getAccessibilityLiveRegion(final View view) {
        return ViewCompat.IMPL.getAccessibilityLiveRegion(view);
    }
    
    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final View view) {
        return ViewCompat.IMPL.getAccessibilityNodeProvider(view);
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
    
    public static int getLabelFor(final View view) {
        return ViewCompat.IMPL.getLabelFor(view);
    }
    
    public static int getLayerType(final View view) {
        return ViewCompat.IMPL.getLayerType(view);
    }
    
    public static int getLayoutDirection(final View view) {
        return ViewCompat.IMPL.getLayoutDirection(view);
    }
    
    public static int getMeasuredHeightAndState(final View view) {
        return ViewCompat.IMPL.getMeasuredHeightAndState(view);
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
    
    public static float getPivotX(final View view) {
        return ViewCompat.IMPL.getPivotX(view);
    }
    
    public static float getPivotY(final View view) {
        return ViewCompat.IMPL.getPivotY(view);
    }
    
    public static float getRotation(final View view) {
        return ViewCompat.IMPL.getRotation(view);
    }
    
    public static float getRotationX(final View view) {
        return ViewCompat.IMPL.getRotationX(view);
    }
    
    public static float getRotationY(final View view) {
        return ViewCompat.IMPL.getRotationY(view);
    }
    
    public static float getScaleX(final View view) {
        return ViewCompat.IMPL.getScaleX(view);
    }
    
    public static float getScaleY(final View view) {
        return ViewCompat.IMPL.getScaleY(view);
    }
    
    public static String getTransitionName(final View view) {
        return ViewCompat.IMPL.getTransitionName(view);
    }
    
    public static float getTranslationX(final View view) {
        return ViewCompat.IMPL.getTranslationX(view);
    }
    
    public static float getTranslationY(final View view) {
        return ViewCompat.IMPL.getTranslationY(view);
    }
    
    public static float getTranslationZ(final View view) {
        return ViewCompat.IMPL.getTranslationZ(view);
    }
    
    public static int getWindowSystemUiVisibility(final View view) {
        return ViewCompat.IMPL.getWindowSystemUiVisibility(view);
    }
    
    public static float getX(final View view) {
        return ViewCompat.IMPL.getX(view);
    }
    
    public static float getY(final View view) {
        return ViewCompat.IMPL.getY(view);
    }
    
    public static boolean hasAccessibilityDelegate(final View view) {
        return ViewCompat.IMPL.hasAccessibilityDelegate(view);
    }
    
    public static boolean hasTransientState(final View view) {
        return ViewCompat.IMPL.hasTransientState(view);
    }
    
    public static boolean isOpaque(final View view) {
        return ViewCompat.IMPL.isOpaque(view);
    }
    
    public static void jumpDrawablesToCurrentState(final View view) {
        ViewCompat.IMPL.jumpDrawablesToCurrentState(view);
    }
    
    public static void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        ViewCompat.IMPL.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }
    
    public static void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewCompat.IMPL.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
    }
    
    public static void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        ViewCompat.IMPL.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }
    
    public static boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        return ViewCompat.IMPL.performAccessibilityAction(view, n, bundle);
    }
    
    public static void postInvalidateOnAnimation(final View view) {
        ViewCompat.IMPL.postInvalidateOnAnimation(view);
    }
    
    public static void postInvalidateOnAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        ViewCompat.IMPL.postInvalidateOnAnimation(view, n, n2, n3, n4);
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
    
    public static void setAccessibilityLiveRegion(final View view, final int n) {
        ViewCompat.IMPL.setAccessibilityLiveRegion(view, n);
    }
    
    public static void setAlpha(final View view, final float n) {
        ViewCompat.IMPL.setAlpha(view, n);
    }
    
    public static void setChildrenDrawingOrderEnabled(final ViewGroup viewGroup, final boolean b) {
        ViewCompat.IMPL.setChildrenDrawingOrderEnabled(viewGroup, b);
    }
    
    public static void setElevation(final View view, final float n) {
        ViewCompat.IMPL.setElevation(view, n);
    }
    
    public static void setHasTransientState(final View view, final boolean b) {
        ViewCompat.IMPL.setHasTransientState(view, b);
    }
    
    public static void setImportantForAccessibility(final View view, final int n) {
        ViewCompat.IMPL.setImportantForAccessibility(view, n);
    }
    
    public static void setLabelFor(final View view, final int n) {
        ViewCompat.IMPL.setLabelFor(view, n);
    }
    
    public static void setLayerPaint(final View view, final Paint paint) {
        ViewCompat.IMPL.setLayerPaint(view, paint);
    }
    
    public static void setLayerType(final View view, final int n, final Paint paint) {
        ViewCompat.IMPL.setLayerType(view, n, paint);
    }
    
    public static void setLayoutDirection(final View view, final int n) {
        ViewCompat.IMPL.setLayoutDirection(view, n);
    }
    
    public static void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        ViewCompat.IMPL.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
    }
    
    public static void setOverScrollMode(final View view, final int n) {
        ViewCompat.IMPL.setOverScrollMode(view, n);
    }
    
    public static void setPaddingRelative(final View view, final int n, final int n2, final int n3, final int n4) {
        ViewCompat.IMPL.setPaddingRelative(view, n, n2, n3, n4);
    }
    
    public static void setPivotX(final View view, final float n) {
        ViewCompat.IMPL.setPivotX(view, n);
    }
    
    public static void setPivotY(final View view, final float n) {
        ViewCompat.IMPL.setPivotX(view, n);
    }
    
    public static void setRotation(final View view, final float n) {
        ViewCompat.IMPL.setRotation(view, n);
    }
    
    public static void setRotationX(final View view, final float n) {
        ViewCompat.IMPL.setRotationX(view, n);
    }
    
    public static void setRotationY(final View view, final float n) {
        ViewCompat.IMPL.setRotationY(view, n);
    }
    
    public static void setScaleX(final View view, final float n) {
        ViewCompat.IMPL.setScaleX(view, n);
    }
    
    public static void setScaleY(final View view, final float n) {
        ViewCompat.IMPL.setScaleY(view, n);
    }
    
    public static void setTransitionName(final View view, final String s) {
        ViewCompat.IMPL.setTransitionName(view, s);
    }
    
    public static void setTranslationX(final View view, final float n) {
        ViewCompat.IMPL.setTranslationX(view, n);
    }
    
    public static void setTranslationY(final View view, final float n) {
        ViewCompat.IMPL.setTranslationY(view, n);
    }
    
    public static void setTranslationZ(final View view, final float n) {
        ViewCompat.IMPL.setTranslationZ(view, n);
    }
    
    public static void setX(final View view, final float n) {
        ViewCompat.IMPL.setX(view, n);
    }
    
    public static void setY(final View view, final float n) {
        ViewCompat.IMPL.setY(view, n);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ 0L, 1L, 2L })
    private @interface AccessibilityLiveRegion {
    }
    
    static class Api21ViewCompatImpl extends KitKatViewCompatImpl
    {
        @Override
        public float getElevation(final View view) {
            return ViewCompatApi21.getElevation(view);
        }
        
        @Override
        public String getTransitionName(final View view) {
            return ViewCompatApi21.getTransitionName(view);
        }
        
        @Override
        public float getTranslationZ(final View view) {
            return ViewCompatApi21.getTranslationZ(view);
        }
        
        @Override
        public void requestApplyInsets(final View view) {
            ViewCompatApi21.requestApplyInsets(view);
        }
        
        @Override
        public void setElevation(final View view, final float n) {
            ViewCompatApi21.setElevation(view, n);
        }
        
        @Override
        public void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            ViewCompatApi21.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
        }
        
        @Override
        public void setTransitionName(final View view, final String s) {
            ViewCompatApi21.setTransitionName(view, s);
        }
        
        @Override
        public void setTranslationZ(final View view, final float n) {
            ViewCompatApi21.setTranslationZ(view, n);
        }
    }
    
    static class BaseViewCompatImpl implements ViewCompatImpl
    {
        private Method mDispatchFinishTemporaryDetach;
        private Method mDispatchStartTemporaryDetach;
        private boolean mTempDetachBound;
        WeakHashMap<View, ViewPropertyAnimatorCompat> mViewPropertyAnimatorCompatMap;
        
        BaseViewCompatImpl() {
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
    
    static class EclairMr1ViewCompatImpl extends BaseViewCompatImpl
    {
        @Override
        public boolean isOpaque(final View view) {
            return ViewCompatEclairMr1.isOpaque(view);
        }
        
        @Override
        public void setChildrenDrawingOrderEnabled(final ViewGroup viewGroup, final boolean b) {
            ViewCompatEclairMr1.setChildrenDrawingOrderEnabled(viewGroup, b);
        }
    }
    
    static class GBViewCompatImpl extends EclairMr1ViewCompatImpl
    {
        @Override
        public int getOverScrollMode(final View view) {
            return ViewCompatGingerbread.getOverScrollMode(view);
        }
        
        @Override
        public void setOverScrollMode(final View view, final int n) {
            ViewCompatGingerbread.setOverScrollMode(view, n);
        }
    }
    
    static class HCViewCompatImpl extends GBViewCompatImpl
    {
        @Override
        public float getAlpha(final View view) {
            return ViewCompatHC.getAlpha(view);
        }
        
        @Override
        long getFrameTime() {
            return ViewCompatHC.getFrameTime();
        }
        
        @Override
        public int getLayerType(final View view) {
            return ViewCompatHC.getLayerType(view);
        }
        
        @Override
        public int getMeasuredHeightAndState(final View view) {
            return ViewCompatHC.getMeasuredHeightAndState(view);
        }
        
        @Override
        public int getMeasuredState(final View view) {
            return ViewCompatHC.getMeasuredState(view);
        }
        
        @Override
        public int getMeasuredWidthAndState(final View view) {
            return ViewCompatHC.getMeasuredWidthAndState(view);
        }
        
        @Override
        public float getPivotX(final View view) {
            return ViewCompatHC.getPivotX(view);
        }
        
        @Override
        public float getPivotY(final View view) {
            return ViewCompatHC.getPivotY(view);
        }
        
        @Override
        public float getRotation(final View view) {
            return ViewCompatHC.getRotation(view);
        }
        
        @Override
        public float getRotationX(final View view) {
            return ViewCompatHC.getRotationX(view);
        }
        
        @Override
        public float getRotationY(final View view) {
            return ViewCompatHC.getRotationY(view);
        }
        
        @Override
        public float getScaleX(final View view) {
            return ViewCompatHC.getScaleX(view);
        }
        
        @Override
        public float getScaleY(final View view) {
            return ViewCompatHC.getScaleY(view);
        }
        
        @Override
        public float getTranslationX(final View view) {
            return ViewCompatHC.getTranslationX(view);
        }
        
        @Override
        public float getTranslationY(final View view) {
            return ViewCompatHC.getTranslationY(view);
        }
        
        @Override
        public float getX(final View view) {
            return ViewCompatHC.getX(view);
        }
        
        @Override
        public float getY(final View view) {
            return ViewCompatHC.getY(view);
        }
        
        @Override
        public void jumpDrawablesToCurrentState(final View view) {
            ViewCompatHC.jumpDrawablesToCurrentState(view);
        }
        
        @Override
        public int resolveSizeAndState(final int n, final int n2, final int n3) {
            return ViewCompatHC.resolveSizeAndState(n, n2, n3);
        }
        
        @Override
        public void setAlpha(final View view, final float n) {
            ViewCompatHC.setAlpha(view, n);
        }
        
        @Override
        public void setLayerPaint(final View view, final Paint paint) {
            this.setLayerType(view, this.getLayerType(view), paint);
            view.invalidate();
        }
        
        @Override
        public void setLayerType(final View view, final int n, final Paint paint) {
            ViewCompatHC.setLayerType(view, n, paint);
        }
        
        @Override
        public void setPivotX(final View view, final float n) {
            ViewCompatHC.setPivotX(view, n);
        }
        
        @Override
        public void setPivotY(final View view, final float n) {
            ViewCompatHC.setPivotY(view, n);
        }
        
        @Override
        public void setRotation(final View view, final float n) {
            ViewCompatHC.setRotation(view, n);
        }
        
        @Override
        public void setRotationX(final View view, final float n) {
            ViewCompatHC.setRotationX(view, n);
        }
        
        @Override
        public void setRotationY(final View view, final float n) {
            ViewCompatHC.setRotationY(view, n);
        }
        
        @Override
        public void setScaleX(final View view, final float n) {
            ViewCompatHC.setScaleX(view, n);
        }
        
        @Override
        public void setScaleY(final View view, final float n) {
            ViewCompatHC.setScaleY(view, n);
        }
        
        @Override
        public void setTranslationX(final View view, final float n) {
            ViewCompatHC.setTranslationX(view, n);
        }
        
        @Override
        public void setTranslationY(final View view, final float n) {
            ViewCompatHC.setTranslationY(view, n);
        }
        
        @Override
        public void setX(final View view, final float n) {
            ViewCompatHC.setX(view, n);
        }
        
        @Override
        public void setY(final View view, final float n) {
            ViewCompatHC.setY(view, n);
        }
    }
    
    static class ICSViewCompatImpl extends HCViewCompatImpl
    {
        static boolean accessibilityDelegateCheckFailed;
        static Field mAccessibilityDelegateField;
        
        static {
            ICSViewCompatImpl.accessibilityDelegateCheckFailed = false;
        }
        
        @Override
        public ViewPropertyAnimatorCompat animate(final View view) {
            if (this.mViewPropertyAnimatorCompatMap == null) {
                this.mViewPropertyAnimatorCompatMap = new WeakHashMap<View, ViewPropertyAnimatorCompat>();
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
            if ((viewPropertyAnimatorCompat = this.mViewPropertyAnimatorCompatMap.get(view)) == null) {
                viewPropertyAnimatorCompat = new ViewPropertyAnimatorCompat(view);
                this.mViewPropertyAnimatorCompatMap.put(view, viewPropertyAnimatorCompat);
            }
            return viewPropertyAnimatorCompat;
        }
        
        @Override
        public boolean canScrollHorizontally(final View view, final int n) {
            return ViewCompatICS.canScrollHorizontally(view, n);
        }
        
        @Override
        public boolean canScrollVertically(final View view, final int n) {
            return ViewCompatICS.canScrollVertically(view, n);
        }
        
        @Override
        public boolean hasAccessibilityDelegate(final View p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: iconst_1       
            //     1: istore_2       
            //     2: getstatic       android/support/v4/view/ViewCompat$ICSViewCompatImpl.accessibilityDelegateCheckFailed:Z
            //     5: ifeq            10
            //     8: iconst_0       
            //     9: ireturn        
            //    10: getstatic       android/support/v4/view/ViewCompat$ICSViewCompatImpl.mAccessibilityDelegateField:Ljava/lang/reflect/Field;
            //    13: ifnonnull       33
            //    16: ldc             Landroid/view/View;.class
            //    18: ldc             "mAccessibilityDelegate"
            //    20: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
            //    23: putstatic       android/support/v4/view/ViewCompat$ICSViewCompatImpl.mAccessibilityDelegateField:Ljava/lang/reflect/Field;
            //    26: getstatic       android/support/v4/view/ViewCompat$ICSViewCompatImpl.mAccessibilityDelegateField:Ljava/lang/reflect/Field;
            //    29: iconst_1       
            //    30: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
            //    33: getstatic       android/support/v4/view/ViewCompat$ICSViewCompatImpl.mAccessibilityDelegateField:Ljava/lang/reflect/Field;
            //    36: aload_1        
            //    37: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
            //    40: astore_1       
            //    41: aload_1        
            //    42: ifnull          54
            //    45: iload_2        
            //    46: ireturn        
            //    47: astore_1       
            //    48: iconst_1       
            //    49: putstatic       android/support/v4/view/ViewCompat$ICSViewCompatImpl.accessibilityDelegateCheckFailed:Z
            //    52: iconst_0       
            //    53: ireturn        
            //    54: iconst_0       
            //    55: istore_2       
            //    56: goto            45
            //    59: astore_1       
            //    60: iconst_1       
            //    61: putstatic       android/support/v4/view/ViewCompat$ICSViewCompatImpl.accessibilityDelegateCheckFailed:Z
            //    64: iconst_0       
            //    65: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  16     33     47     54     Ljava/lang/Throwable;
            //  33     41     59     66     Ljava/lang/Throwable;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        @Override
        public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
            ViewCompatICS.onInitializeAccessibilityEvent(view, accessibilityEvent);
        }
        
        @Override
        public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewCompatICS.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.getInfo());
        }
        
        @Override
        public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
            ViewCompatICS.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }
        
        @Override
        public void setAccessibilityDelegate(final View view, final AccessibilityDelegateCompat accessibilityDelegateCompat) {
            ViewCompatICS.setAccessibilityDelegate(view, accessibilityDelegateCompat.getBridge());
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ 0L, 1L, 2L, 4L })
    private @interface ImportantForAccessibility {
    }
    
    static class JBViewCompatImpl extends ICSViewCompatImpl
    {
        @Override
        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final View view) {
            final Object accessibilityNodeProvider = ViewCompatJB.getAccessibilityNodeProvider(view);
            if (accessibilityNodeProvider != null) {
                return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
            }
            return null;
        }
        
        @Override
        public boolean getFitsSystemWindows(final View view) {
            return ViewCompatJB.getFitsSystemWindows(view);
        }
        
        @Override
        public int getImportantForAccessibility(final View view) {
            return ViewCompatJB.getImportantForAccessibility(view);
        }
        
        @Override
        public int getMinimumHeight(final View view) {
            return ViewCompatJB.getMinimumHeight(view);
        }
        
        @Override
        public int getMinimumWidth(final View view) {
            return ViewCompatJB.getMinimumWidth(view);
        }
        
        @Override
        public ViewParent getParentForAccessibility(final View view) {
            return ViewCompatJB.getParentForAccessibility(view);
        }
        
        @Override
        public boolean hasTransientState(final View view) {
            return ViewCompatJB.hasTransientState(view);
        }
        
        @Override
        public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
            return ViewCompatJB.performAccessibilityAction(view, n, bundle);
        }
        
        @Override
        public void postInvalidateOnAnimation(final View view) {
            ViewCompatJB.postInvalidateOnAnimation(view);
        }
        
        @Override
        public void postInvalidateOnAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
            ViewCompatJB.postInvalidateOnAnimation(view, n, n2, n3, n4);
        }
        
        @Override
        public void postOnAnimation(final View view, final Runnable runnable) {
            ViewCompatJB.postOnAnimation(view, runnable);
        }
        
        @Override
        public void postOnAnimationDelayed(final View view, final Runnable runnable, final long n) {
            ViewCompatJB.postOnAnimationDelayed(view, runnable, n);
        }
        
        @Override
        public void requestApplyInsets(final View view) {
            ViewCompatJB.requestApplyInsets(view);
        }
        
        @Override
        public void setHasTransientState(final View view, final boolean b) {
            ViewCompatJB.setHasTransientState(view, b);
        }
        
        @Override
        public void setImportantForAccessibility(final View view, final int n) {
            int n2 = n;
            if (n == 4) {
                n2 = 2;
            }
            ViewCompatJB.setImportantForAccessibility(view, n2);
        }
    }
    
    static class JbMr1ViewCompatImpl extends JBViewCompatImpl
    {
        @Override
        public int getLabelFor(final View view) {
            return ViewCompatJellybeanMr1.getLabelFor(view);
        }
        
        @Override
        public int getLayoutDirection(final View view) {
            return ViewCompatJellybeanMr1.getLayoutDirection(view);
        }
        
        @Override
        public int getPaddingEnd(final View view) {
            return ViewCompatJellybeanMr1.getPaddingEnd(view);
        }
        
        @Override
        public int getPaddingStart(final View view) {
            return ViewCompatJellybeanMr1.getPaddingStart(view);
        }
        
        @Override
        public int getWindowSystemUiVisibility(final View view) {
            return ViewCompatJellybeanMr1.getWindowSystemUiVisibility(view);
        }
        
        @Override
        public void setLabelFor(final View view, final int n) {
            ViewCompatJellybeanMr1.setLabelFor(view, n);
        }
        
        @Override
        public void setLayerPaint(final View view, final Paint paint) {
            ViewCompatJellybeanMr1.setLayerPaint(view, paint);
        }
        
        @Override
        public void setLayoutDirection(final View view, final int n) {
            ViewCompatJellybeanMr1.setLayoutDirection(view, n);
        }
        
        @Override
        public void setPaddingRelative(final View view, final int n, final int n2, final int n3, final int n4) {
            ViewCompatJellybeanMr1.setPaddingRelative(view, n, n2, n3, n4);
        }
    }
    
    static class KitKatViewCompatImpl extends JbMr1ViewCompatImpl
    {
        @Override
        public int getAccessibilityLiveRegion(final View view) {
            return ViewCompatKitKat.getAccessibilityLiveRegion(view);
        }
        
        @Override
        public void setAccessibilityLiveRegion(final View view, final int n) {
            ViewCompatKitKat.setAccessibilityLiveRegion(view, n);
        }
        
        @Override
        public void setImportantForAccessibility(final View view, final int n) {
            ViewCompatJB.setImportantForAccessibility(view, n);
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ 0L, 1L, 2L })
    private @interface LayerType {
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ 0L, 1L, 2L, 3L })
    private @interface LayoutDirectionMode {
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ 0L, 1L, 1L })
    private @interface OverScroll {
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ 0L, 1L })
    private @interface ResolvedLayoutDirectionMode {
    }
    
    interface ViewCompatImpl
    {
        ViewPropertyAnimatorCompat animate(final View p0);
        
        boolean canScrollHorizontally(final View p0, final int p1);
        
        boolean canScrollVertically(final View p0, final int p1);
        
        void dispatchFinishTemporaryDetach(final View p0);
        
        void dispatchStartTemporaryDetach(final View p0);
        
        int getAccessibilityLiveRegion(final View p0);
        
        AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final View p0);
        
        float getAlpha(final View p0);
        
        float getElevation(final View p0);
        
        boolean getFitsSystemWindows(final View p0);
        
        int getImportantForAccessibility(final View p0);
        
        int getLabelFor(final View p0);
        
        int getLayerType(final View p0);
        
        int getLayoutDirection(final View p0);
        
        int getMeasuredHeightAndState(final View p0);
        
        int getMeasuredState(final View p0);
        
        int getMeasuredWidthAndState(final View p0);
        
        int getMinimumHeight(final View p0);
        
        int getMinimumWidth(final View p0);
        
        int getOverScrollMode(final View p0);
        
        int getPaddingEnd(final View p0);
        
        int getPaddingStart(final View p0);
        
        ViewParent getParentForAccessibility(final View p0);
        
        float getPivotX(final View p0);
        
        float getPivotY(final View p0);
        
        float getRotation(final View p0);
        
        float getRotationX(final View p0);
        
        float getRotationY(final View p0);
        
        float getScaleX(final View p0);
        
        float getScaleY(final View p0);
        
        String getTransitionName(final View p0);
        
        float getTranslationX(final View p0);
        
        float getTranslationY(final View p0);
        
        float getTranslationZ(final View p0);
        
        int getWindowSystemUiVisibility(final View p0);
        
        float getX(final View p0);
        
        float getY(final View p0);
        
        boolean hasAccessibilityDelegate(final View p0);
        
        boolean hasTransientState(final View p0);
        
        boolean isOpaque(final View p0);
        
        void jumpDrawablesToCurrentState(final View p0);
        
        void onInitializeAccessibilityEvent(final View p0, final AccessibilityEvent p1);
        
        void onInitializeAccessibilityNodeInfo(final View p0, final AccessibilityNodeInfoCompat p1);
        
        void onPopulateAccessibilityEvent(final View p0, final AccessibilityEvent p1);
        
        boolean performAccessibilityAction(final View p0, final int p1, final Bundle p2);
        
        void postInvalidateOnAnimation(final View p0);
        
        void postInvalidateOnAnimation(final View p0, final int p1, final int p2, final int p3, final int p4);
        
        void postOnAnimation(final View p0, final Runnable p1);
        
        void postOnAnimationDelayed(final View p0, final Runnable p1, final long p2);
        
        void requestApplyInsets(final View p0);
        
        int resolveSizeAndState(final int p0, final int p1, final int p2);
        
        void setAccessibilityDelegate(final View p0, final AccessibilityDelegateCompat p1);
        
        void setAccessibilityLiveRegion(final View p0, final int p1);
        
        void setAlpha(final View p0, final float p1);
        
        void setChildrenDrawingOrderEnabled(final ViewGroup p0, final boolean p1);
        
        void setElevation(final View p0, final float p1);
        
        void setHasTransientState(final View p0, final boolean p1);
        
        void setImportantForAccessibility(final View p0, final int p1);
        
        void setLabelFor(final View p0, final int p1);
        
        void setLayerPaint(final View p0, final Paint p1);
        
        void setLayerType(final View p0, final int p1, final Paint p2);
        
        void setLayoutDirection(final View p0, final int p1);
        
        void setOnApplyWindowInsetsListener(final View p0, final OnApplyWindowInsetsListener p1);
        
        void setOverScrollMode(final View p0, final int p1);
        
        void setPaddingRelative(final View p0, final int p1, final int p2, final int p3, final int p4);
        
        void setPivotX(final View p0, final float p1);
        
        void setPivotY(final View p0, final float p1);
        
        void setRotation(final View p0, final float p1);
        
        void setRotationX(final View p0, final float p1);
        
        void setRotationY(final View p0, final float p1);
        
        void setScaleX(final View p0, final float p1);
        
        void setScaleY(final View p0, final float p1);
        
        void setTransitionName(final View p0, final String p1);
        
        void setTranslationX(final View p0, final float p1);
        
        void setTranslationY(final View p0, final float p1);
        
        void setTranslationZ(final View p0, final float p1);
        
        void setX(final View p0, final float p1);
        
        void setY(final View p0, final float p1);
    }
}
