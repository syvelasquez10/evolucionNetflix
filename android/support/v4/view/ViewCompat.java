// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.drawable.Drawable;
import android.graphics.Paint;
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
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
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
        IMPL = (ViewCompatImpl)new BaseViewCompatImpl();
    }
    
    public static boolean canScrollHorizontally(final View view, final int n) {
        return ViewCompat.IMPL.canScrollHorizontally(view, n);
    }
    
    public static boolean canScrollVertically(final View view, final int n) {
        return ViewCompat.IMPL.canScrollVertically(view, n);
    }
    
    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final View view) {
        return ViewCompat.IMPL.getAccessibilityNodeProvider(view);
    }
    
    public static float getAlpha(final View view) {
        return ViewCompat.IMPL.getAlpha(view);
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
    
    public static int getOverScrollMode(final View view) {
        return ViewCompat.IMPL.getOverScrollMode(view);
    }
    
    public static ViewParent getParentForAccessibility(final View view) {
        return ViewCompat.IMPL.getParentForAccessibility(view);
    }
    
    public static boolean hasTransientState(final View view) {
        return ViewCompat.IMPL.hasTransientState(view);
    }
    
    public static boolean isOpaque(final View view) {
        return ViewCompat.IMPL.isOpaque(view);
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
    
    public static int resolveSizeAndState(final int n, final int n2, final int n3) {
        return ViewCompat.IMPL.resolveSizeAndState(n, n2, n3);
    }
    
    public static void setAccessibilityDelegate(final View view, final AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.IMPL.setAccessibilityDelegate(view, accessibilityDelegateCompat);
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
    
    public static void setOverScrollMode(final View view, final int n) {
        ViewCompat.IMPL.setOverScrollMode(view, n);
    }
    
    public int getAccessibilityLiveRegion(final View view) {
        return ViewCompat.IMPL.getAccessibilityLiveRegion(view);
    }
    
    public void setAccessibilityLiveRegion(final View view, final int n) {
        ViewCompat.IMPL.setAccessibilityLiveRegion(view, n);
    }
    
    static class BaseViewCompatImpl implements ViewCompatImpl
    {
        @Override
        public boolean canScrollHorizontally(final View view, final int n) {
            return false;
        }
        
        @Override
        public boolean canScrollVertically(final View view, final int n) {
            return false;
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
        public int getOverScrollMode(final View view) {
            return 2;
        }
        
        @Override
        public ViewParent getParentForAccessibility(final View view) {
            return view.getParent();
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
            view.postInvalidateDelayed(this.getFrameTime());
        }
        
        @Override
        public void postInvalidateOnAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
            view.postInvalidateDelayed(this.getFrameTime(), n, n2, n3, n4);
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
        public void setOverScrollMode(final View view, final int n) {
        }
    }
    
    static class EclairMr1ViewCompatImpl extends BaseViewCompatImpl
    {
        @Override
        public boolean isOpaque(final View view) {
            return ViewCompatEclairMr1.isOpaque(view);
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
        public int resolveSizeAndState(final int n, final int n2, final int n3) {
            return ViewCompatHC.resolveSizeAndState(n, n2, n3);
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
    }
    
    static class ICSViewCompatImpl extends HCViewCompatImpl
    {
        @Override
        public boolean canScrollHorizontally(final View view, final int n) {
            return ViewCompatICS.canScrollHorizontally(view, n);
        }
        
        @Override
        public boolean canScrollVertically(final View view, final int n) {
            return ViewCompatICS.canScrollVertically(view, n);
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
        public int getImportantForAccessibility(final View view) {
            return ViewCompatJB.getImportantForAccessibility(view);
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
        public void setHasTransientState(final View view, final boolean b) {
            ViewCompatJB.setHasTransientState(view, b);
        }
        
        @Override
        public void setImportantForAccessibility(final View view, final int n) {
            ViewCompatJB.setImportantForAccessibility(view, n);
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
    }
    
    interface ViewCompatImpl
    {
        boolean canScrollHorizontally(final View p0, final int p1);
        
        boolean canScrollVertically(final View p0, final int p1);
        
        int getAccessibilityLiveRegion(final View p0);
        
        AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final View p0);
        
        float getAlpha(final View p0);
        
        int getImportantForAccessibility(final View p0);
        
        int getLabelFor(final View p0);
        
        int getLayerType(final View p0);
        
        int getLayoutDirection(final View p0);
        
        int getMeasuredHeightAndState(final View p0);
        
        int getMeasuredState(final View p0);
        
        int getMeasuredWidthAndState(final View p0);
        
        int getOverScrollMode(final View p0);
        
        ViewParent getParentForAccessibility(final View p0);
        
        boolean hasTransientState(final View p0);
        
        boolean isOpaque(final View p0);
        
        void onInitializeAccessibilityEvent(final View p0, final AccessibilityEvent p1);
        
        void onInitializeAccessibilityNodeInfo(final View p0, final AccessibilityNodeInfoCompat p1);
        
        void onPopulateAccessibilityEvent(final View p0, final AccessibilityEvent p1);
        
        boolean performAccessibilityAction(final View p0, final int p1, final Bundle p2);
        
        void postInvalidateOnAnimation(final View p0);
        
        void postInvalidateOnAnimation(final View p0, final int p1, final int p2, final int p3, final int p4);
        
        void postOnAnimation(final View p0, final Runnable p1);
        
        void postOnAnimationDelayed(final View p0, final Runnable p1, final long p2);
        
        int resolveSizeAndState(final int p0, final int p1, final int p2);
        
        void setAccessibilityDelegate(final View p0, final AccessibilityDelegateCompat p1);
        
        void setAccessibilityLiveRegion(final View p0, final int p1);
        
        void setHasTransientState(final View p0, final boolean p1);
        
        void setImportantForAccessibility(final View p0, final int p1);
        
        void setLabelFor(final View p0, final int p1);
        
        void setLayerPaint(final View p0, final Paint p1);
        
        void setLayerType(final View p0, final int p1, final Paint p2);
        
        void setLayoutDirection(final View p0, final int p1);
        
        void setOverScrollMode(final View p0, final int p1);
    }
}
