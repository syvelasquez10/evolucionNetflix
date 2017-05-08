// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.scroll;

import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import android.graphics.drawable.ColorDrawable;
import android.view.View$MeasureSpec;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.graphics.Canvas;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import android.widget.OverScroller;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.graphics.Rect;
import java.lang.reflect.Field;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import android.view.ViewGroup$OnHierarchyChangeListener;
import android.view.View$OnLayoutChangeListener;
import android.widget.ScrollView;

public class ReactScrollView extends ScrollView implements View$OnLayoutChangeListener, ViewGroup$OnHierarchyChangeListener, ReactClippingViewGroup
{
    private static Field sScrollerField;
    private static boolean sTriedToGetScrollerField;
    private Rect mClippingRect;
    private View mContentView;
    private boolean mDoneFlinging;
    private boolean mDragging;
    private Drawable mEndBackground;
    private int mEndFillColor;
    private boolean mFlinging;
    private FpsListener mFpsListener;
    private final OnScrollDispatchHelper mOnScrollDispatchHelper;
    private boolean mRemoveClippedSubviews;
    private boolean mScrollEnabled;
    private String mScrollPerfTag;
    private final OverScroller mScroller;
    private boolean mSendMomentumEvents;
    
    static {
        ReactScrollView.sTriedToGetScrollerField = false;
    }
    
    public ReactScrollView(final ReactContext p0, final FpsListener p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   android/widget/ScrollView.<init>:(Landroid/content/Context;)V
        //     5: aload_0        
        //     6: new             Lcom/facebook/react/views/scroll/OnScrollDispatchHelper;
        //     9: dup            
        //    10: invokespecial   com/facebook/react/views/scroll/OnScrollDispatchHelper.<init>:()V
        //    13: putfield        com/facebook/react/views/scroll/ReactScrollView.mOnScrollDispatchHelper:Lcom/facebook/react/views/scroll/OnScrollDispatchHelper;
        //    16: aload_0        
        //    17: iconst_1       
        //    18: putfield        com/facebook/react/views/scroll/ReactScrollView.mScrollEnabled:Z
        //    21: aload_0        
        //    22: aconst_null    
        //    23: putfield        com/facebook/react/views/scroll/ReactScrollView.mFpsListener:Lcom/facebook/react/views/scroll/FpsListener;
        //    26: aload_0        
        //    27: iconst_0       
        //    28: putfield        com/facebook/react/views/scroll/ReactScrollView.mEndFillColor:I
        //    31: aload_0        
        //    32: aload_2        
        //    33: putfield        com/facebook/react/views/scroll/ReactScrollView.mFpsListener:Lcom/facebook/react/views/scroll/FpsListener;
        //    36: getstatic       com/facebook/react/views/scroll/ReactScrollView.sTriedToGetScrollerField:Z
        //    39: ifne            63
        //    42: iconst_1       
        //    43: putstatic       com/facebook/react/views/scroll/ReactScrollView.sTriedToGetScrollerField:Z
        //    46: ldc             Landroid/widget/ScrollView;.class
        //    48: ldc             "mScroller"
        //    50: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    53: putstatic       com/facebook/react/views/scroll/ReactScrollView.sScrollerField:Ljava/lang/reflect/Field;
        //    56: getstatic       com/facebook/react/views/scroll/ReactScrollView.sScrollerField:Ljava/lang/reflect/Field;
        //    59: iconst_1       
        //    60: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    63: getstatic       com/facebook/react/views/scroll/ReactScrollView.sScrollerField:Ljava/lang/reflect/Field;
        //    66: ifnull          138
        //    69: getstatic       com/facebook/react/views/scroll/ReactScrollView.sScrollerField:Ljava/lang/reflect/Field;
        //    72: aload_0        
        //    73: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    76: astore_1       
        //    77: aload_1        
        //    78: instanceof      Landroid/widget/OverScroller;
        //    81: ifeq            110
        //    84: aload_0        
        //    85: aload_1        
        //    86: checkcast       Landroid/widget/OverScroller;
        //    89: putfield        com/facebook/react/views/scroll/ReactScrollView.mScroller:Landroid/widget/OverScroller;
        //    92: aload_0        
        //    93: aload_0        
        //    94: invokevirtual   com/facebook/react/views/scroll/ReactScrollView.setOnHierarchyChangeListener:(Landroid/view/ViewGroup$OnHierarchyChangeListener;)V
        //    97: return         
        //    98: astore_1       
        //    99: ldc             "React"
        //   101: ldc             "Failed to get mScroller field for ScrollView! This app will exhibit the bounce-back scrolling bug :("
        //   103: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   106: pop            
        //   107: goto            63
        //   110: ldc             "React"
        //   112: ldc             "Failed to cast mScroller field in ScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :("
        //   114: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   117: pop            
        //   118: aload_0        
        //   119: aconst_null    
        //   120: putfield        com/facebook/react/views/scroll/ReactScrollView.mScroller:Landroid/widget/OverScroller;
        //   123: goto            92
        //   126: astore_1       
        //   127: new             Ljava/lang/RuntimeException;
        //   130: dup            
        //   131: ldc             "Failed to get mScroller from ScrollView!"
        //   133: aload_1        
        //   134: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   137: athrow         
        //   138: aload_0        
        //   139: aconst_null    
        //   140: putfield        com/facebook/react/views/scroll/ReactScrollView.mScroller:Landroid/widget/OverScroller;
        //   143: goto            92
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  46     63     98     110    Ljava/lang/NoSuchFieldException;
        //  69     92     126    138    Ljava/lang/IllegalAccessException;
        //  110    123    126    138    Ljava/lang/IllegalAccessException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    private void disableFpsListener() {
        if (this.isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.disable(this.mScrollPerfTag);
        }
    }
    
    private void enableFpsListener() {
        if (this.isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.enable(this.mScrollPerfTag);
        }
    }
    
    private int getMaxScrollY() {
        return Math.max(0, this.mContentView.getHeight() - (this.getHeight() - this.getPaddingBottom() - this.getPaddingTop()));
    }
    
    private boolean isScrollPerfLoggingEnabled() {
        return this.mFpsListener != null && this.mScrollPerfTag != null && !this.mScrollPerfTag.isEmpty();
    }
    
    public void draw(final Canvas canvas) {
        if (this.mEndFillColor != 0) {
            final View child = this.getChildAt(0);
            if (this.mEndBackground != null && child != null && child.getBottom() < this.getHeight()) {
                this.mEndBackground.setBounds(0, child.getBottom(), this.getWidth(), this.getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        super.draw(canvas);
    }
    
    public void fling(final int n) {
        if (this.mScroller != null) {
            this.mScroller.fling(this.getScrollX(), this.getScrollY(), 0, n, 0, 0, 0, Integer.MAX_VALUE, 0, (this.getHeight() - this.getPaddingBottom() - this.getPaddingTop()) / 2);
            this.postInvalidateOnAnimation();
        }
        else {
            super.fling(n);
        }
        if (this.mSendMomentumEvents || this.isScrollPerfLoggingEnabled()) {
            this.mFlinging = true;
            this.enableFpsListener();
            ReactScrollViewHelper.emitScrollMomentumBeginEvent((ViewGroup)this);
            this.postOnAnimationDelayed((Runnable)new ReactScrollView$1(this), 20L);
        }
    }
    
    public void getClippingRect(final Rect rect) {
        rect.set((Rect)Assertions.assertNotNull(this.mClippingRect));
    }
    
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            this.updateClippingRect();
        }
    }
    
    public void onChildViewAdded(final View view, final View mContentView) {
        (this.mContentView = mContentView).addOnLayoutChangeListener((View$OnLayoutChangeListener)this);
    }
    
    public void onChildViewRemoved(final View view, final View view2) {
        this.mContentView.removeOnLayoutChangeListener((View$OnLayoutChangeListener)this);
        this.mContentView = null;
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (this.mScrollEnabled && super.onInterceptTouchEvent(motionEvent)) {
            NativeGestureUtil.notifyNativeGestureStarted((View)this, motionEvent);
            ReactScrollViewHelper.emitScrollBeginDragEvent((ViewGroup)this);
            this.mDragging = true;
            this.enableFpsListener();
            return true;
        }
        return false;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.scrollTo(this.getScrollX(), this.getScrollY());
    }
    
    public void onLayoutChange(final View view, int scrollY, int maxScrollY, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (this.mContentView != null) {
            scrollY = this.getScrollY();
            maxScrollY = this.getMaxScrollY();
            if (scrollY > maxScrollY) {
                this.scrollTo(this.getScrollX(), maxScrollY);
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(n, n2);
        this.setMeasuredDimension(View$MeasureSpec.getSize(n), View$MeasureSpec.getSize(n2));
    }
    
    protected void onOverScrolled(final int n, final int n2, final boolean b, final boolean b2) {
        int n3 = n2;
        if (this.mScroller != null) {
            n3 = n2;
            if (!this.mScroller.isFinished()) {
                n3 = n2;
                if (this.mScroller.getCurrY() != this.mScroller.getFinalY()) {
                    final int maxScrollY = this.getMaxScrollY();
                    if ((n3 = n2) >= maxScrollY) {
                        this.mScroller.abortAnimation();
                        n3 = maxScrollY;
                    }
                }
            }
        }
        super.onOverScrolled(n, n3, b, b2);
    }
    
    protected void onScrollChanged(final int n, final int n2, final int n3, final int n4) {
        super.onScrollChanged(n, n2, n3, n4);
        if (this.mOnScrollDispatchHelper.onScrollChanged(n, n2)) {
            if (this.mRemoveClippedSubviews) {
                this.updateClippingRect();
            }
            if (this.mFlinging) {
                this.mDoneFlinging = false;
            }
            ReactScrollViewHelper.emitScrollEvent((ViewGroup)this);
        }
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.mRemoveClippedSubviews) {
            this.updateClippingRect();
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        if ((motionEvent.getAction() & 0xFF) == 0x1 && this.mDragging) {
            ReactScrollViewHelper.emitScrollEndDragEvent((ViewGroup)this);
            this.mDragging = false;
            this.disableFpsListener();
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void setEndFillColor(final int mEndFillColor) {
        if (mEndFillColor != this.mEndFillColor) {
            this.mEndFillColor = mEndFillColor;
            this.mEndBackground = (Drawable)new ColorDrawable(this.mEndFillColor);
        }
    }
    
    public void setRemoveClippedSubviews(final boolean mRemoveClippedSubviews) {
        if (mRemoveClippedSubviews && this.mClippingRect == null) {
            this.mClippingRect = new Rect();
        }
        this.mRemoveClippedSubviews = mRemoveClippedSubviews;
        this.updateClippingRect();
    }
    
    public void setScrollEnabled(final boolean mScrollEnabled) {
        this.mScrollEnabled = mScrollEnabled;
    }
    
    public void setScrollPerfTag(final String mScrollPerfTag) {
        this.mScrollPerfTag = mScrollPerfTag;
    }
    
    public void setSendMomentumEvents(final boolean mSendMomentumEvents) {
        this.mSendMomentumEvents = mSendMomentumEvents;
    }
    
    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Assertions.assertNotNull(this.mClippingRect);
            ReactClippingViewGroupHelper.calculateClippingRect((View)this, this.mClippingRect);
            final View child = this.getChildAt(0);
            if (child instanceof ReactClippingViewGroup) {
                ((ReactClippingViewGroup)child).updateClippingRect();
            }
        }
    }
}
