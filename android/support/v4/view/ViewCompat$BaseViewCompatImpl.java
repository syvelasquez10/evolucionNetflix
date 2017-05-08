// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewParent;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.util.Log;
import android.view.View;
import java.util.WeakHashMap;
import java.lang.reflect.Method;

class ViewCompat$BaseViewCompatImpl implements ViewCompat$ViewCompatImpl
{
    private static Method sChildrenDrawingOrderMethod;
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
    
    private boolean canScrollingViewScrollHorizontally(final ScrollingView scrollingView, final int n) {
        boolean b = true;
        final int computeHorizontalScrollOffset = scrollingView.computeHorizontalScrollOffset();
        final int n2 = scrollingView.computeHorizontalScrollRange() - scrollingView.computeHorizontalScrollExtent();
        if (n2 == 0) {
            b = false;
        }
        else if (n < 0) {
            if (computeHorizontalScrollOffset <= 0) {
                return false;
            }
        }
        else if (computeHorizontalScrollOffset >= n2 - 1) {
            return false;
        }
        return b;
    }
    
    private boolean canScrollingViewScrollVertically(final ScrollingView scrollingView, final int n) {
        boolean b = true;
        final int computeVerticalScrollOffset = scrollingView.computeVerticalScrollOffset();
        final int n2 = scrollingView.computeVerticalScrollRange() - scrollingView.computeVerticalScrollExtent();
        if (n2 == 0) {
            b = false;
        }
        else if (n < 0) {
            if (computeVerticalScrollOffset <= 0) {
                return false;
            }
        }
        else if (computeVerticalScrollOffset >= n2 - 1) {
            return false;
        }
        return b;
    }
    
    @Override
    public ViewPropertyAnimatorCompat animate(final View view) {
        return new ViewPropertyAnimatorCompat(view);
    }
    
    @Override
    public boolean canScrollHorizontally(final View view, final int n) {
        return view instanceof ScrollingView && this.canScrollingViewScrollHorizontally((ScrollingView)view, n);
    }
    
    @Override
    public boolean canScrollVertically(final View view, final int n) {
        return view instanceof ScrollingView && this.canScrollingViewScrollVertically((ScrollingView)view, n);
    }
    
    @Override
    public int combineMeasuredStates(final int n, final int n2) {
        return n | n2;
    }
    
    @Override
    public WindowInsetsCompat dispatchApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return windowInsetsCompat;
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
    public boolean dispatchNestedFling(final View view, final float n, final float n2, final boolean b) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).dispatchNestedFling(n, n2, b);
    }
    
    @Override
    public boolean dispatchNestedPreFling(final View view, final float n, final float n2) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).dispatchNestedPreFling(n, n2);
    }
    
    @Override
    public boolean dispatchNestedPreScroll(final View view, final int n, final int n2, final int[] array, final int[] array2) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).dispatchNestedPreScroll(n, n2, array, array2);
    }
    
    @Override
    public boolean dispatchNestedScroll(final View view, final int n, final int n2, final int n3, final int n4, final int[] array) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).dispatchNestedScroll(n, n2, n3, n4, array);
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
    public ColorStateList getBackgroundTintList(final View view) {
        return ViewCompatBase.getBackgroundTintList(view);
    }
    
    @Override
    public PorterDuff$Mode getBackgroundTintMode(final View view) {
        return ViewCompatBase.getBackgroundTintMode(view);
    }
    
    @Override
    public Rect getClipBounds(final View view) {
        return null;
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
    public Matrix getMatrix(final View view) {
        return null;
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
        return ViewCompatBase.getMinimumHeight(view);
    }
    
    @Override
    public int getMinimumWidth(final View view) {
        return ViewCompatBase.getMinimumWidth(view);
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
    public int getScrollIndicators(final View view) {
        return 0;
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
        return view.getLeft();
    }
    
    @Override
    public float getY(final View view) {
        return view.getTop();
    }
    
    @Override
    public float getZ(final View view) {
        return this.getTranslationZ(view) + this.getElevation(view);
    }
    
    @Override
    public boolean hasAccessibilityDelegate(final View view) {
        return false;
    }
    
    @Override
    public boolean hasNestedScrollingParent(final View view) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).hasNestedScrollingParent();
    }
    
    @Override
    public boolean hasOnClickListeners(final View view) {
        return false;
    }
    
    @Override
    public boolean hasOverlappingRendering(final View view) {
        return true;
    }
    
    @Override
    public boolean hasTransientState(final View view) {
        return false;
    }
    
    @Override
    public boolean isAttachedToWindow(final View view) {
        return ViewCompatBase.isAttachedToWindow(view);
    }
    
    @Override
    public boolean isImportantForAccessibility(final View view) {
        return true;
    }
    
    @Override
    public boolean isInLayout(final View view) {
        return false;
    }
    
    @Override
    public boolean isLaidOut(final View view) {
        return ViewCompatBase.isLaidOut(view);
    }
    
    @Override
    public boolean isLayoutDirectionResolved(final View view) {
        return false;
    }
    
    @Override
    public boolean isNestedScrollingEnabled(final View view) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).isNestedScrollingEnabled();
    }
    
    @Override
    public boolean isPaddingRelative(final View view) {
        return false;
    }
    
    @Override
    public void jumpDrawablesToCurrentState(final View view) {
    }
    
    @Override
    public void offsetLeftAndRight(final View view, final int n) {
        ViewCompatBase.offsetLeftAndRight(view, n);
    }
    
    @Override
    public void offsetTopAndBottom(final View view, final int n) {
        ViewCompatBase.offsetTopAndBottom(view, n);
    }
    
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return windowInsetsCompat;
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
    public void setActivated(final View view, final boolean b) {
    }
    
    @Override
    public void setAlpha(final View view, final float n) {
    }
    
    @Override
    public void setBackgroundTintList(final View view, final ColorStateList list) {
        ViewCompatBase.setBackgroundTintList(view, list);
    }
    
    @Override
    public void setBackgroundTintMode(final View view, final PorterDuff$Mode porterDuff$Mode) {
        ViewCompatBase.setBackgroundTintMode(view, porterDuff$Mode);
    }
    
    @Override
    public void setChildrenDrawingOrderEnabled(final ViewGroup p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/view/ViewCompat$BaseViewCompatImpl.sChildrenDrawingOrderMethod:Ljava/lang/reflect/Method;
        //     3: ifnonnull       35
        //     6: ldc_w           Landroid/view/ViewGroup;.class
        //     9: ldc_w           "setChildrenDrawingOrderEnabled"
        //    12: iconst_1       
        //    13: anewarray       Ljava/lang/Class;
        //    16: dup            
        //    17: iconst_0       
        //    18: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    21: aastore        
        //    22: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    25: putstatic       android/support/v4/view/ViewCompat$BaseViewCompatImpl.sChildrenDrawingOrderMethod:Ljava/lang/reflect/Method;
        //    28: getstatic       android/support/v4/view/ViewCompat$BaseViewCompatImpl.sChildrenDrawingOrderMethod:Ljava/lang/reflect/Method;
        //    31: iconst_1       
        //    32: invokevirtual   java/lang/reflect/Method.setAccessible:(Z)V
        //    35: getstatic       android/support/v4/view/ViewCompat$BaseViewCompatImpl.sChildrenDrawingOrderMethod:Ljava/lang/reflect/Method;
        //    38: aload_1        
        //    39: iconst_1       
        //    40: anewarray       Ljava/lang/Object;
        //    43: dup            
        //    44: iconst_0       
        //    45: iload_2        
        //    46: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    49: aastore        
        //    50: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    53: pop            
        //    54: return         
        //    55: astore_3       
        //    56: ldc             "ViewCompat"
        //    58: ldc_w           "Unable to find childrenDrawingOrderEnabled"
        //    61: aload_3        
        //    62: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    65: pop            
        //    66: goto            28
        //    69: astore_1       
        //    70: ldc             "ViewCompat"
        //    72: ldc_w           "Unable to invoke childrenDrawingOrderEnabled"
        //    75: aload_1        
        //    76: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    79: pop            
        //    80: return         
        //    81: astore_1       
        //    82: ldc             "ViewCompat"
        //    84: ldc_w           "Unable to invoke childrenDrawingOrderEnabled"
        //    87: aload_1        
        //    88: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    91: pop            
        //    92: return         
        //    93: astore_1       
        //    94: ldc             "ViewCompat"
        //    96: ldc_w           "Unable to invoke childrenDrawingOrderEnabled"
        //    99: aload_1        
        //   100: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   103: pop            
        //   104: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  6      28     55     69     Ljava/lang/NoSuchMethodException;
        //  35     54     69     81     Ljava/lang/IllegalAccessException;
        //  35     54     81     93     Ljava/lang/IllegalArgumentException;
        //  35     54     93     105    Ljava/lang/reflect/InvocationTargetException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0035:
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
    public void setClipBounds(final View view, final Rect rect) {
    }
    
    @Override
    public void setElevation(final View view, final float n) {
    }
    
    @Override
    public void setFitsSystemWindows(final View view, final boolean b) {
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
    public void setNestedScrollingEnabled(final View view, final boolean nestedScrollingEnabled) {
        if (view instanceof NestedScrollingChild) {
            ((NestedScrollingChild)view).setNestedScrollingEnabled(nestedScrollingEnabled);
        }
    }
    
    @Override
    public void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
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
    public void setPointerIcon(final View view, final PointerIconCompat pointerIconCompat) {
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
    public void setSaveFromParentEnabled(final View view, final boolean b) {
    }
    
    @Override
    public void setScaleX(final View view, final float n) {
    }
    
    @Override
    public void setScaleY(final View view, final float n) {
    }
    
    @Override
    public void setScrollIndicators(final View view, final int n) {
    }
    
    @Override
    public void setScrollIndicators(final View view, final int n, final int n2) {
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
    
    @Override
    public void setZ(final View view, final float n) {
    }
    
    @Override
    public boolean startNestedScroll(final View view, final int n) {
        return view instanceof NestedScrollingChild && ((NestedScrollingChild)view).startNestedScroll(n);
    }
    
    @Override
    public void stopNestedScroll(final View view) {
        if (view instanceof NestedScrollingChild) {
            ((NestedScrollingChild)view).stopNestedScroll();
        }
    }
}
