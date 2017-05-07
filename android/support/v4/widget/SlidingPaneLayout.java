// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.Bitmap;
import android.util.Log;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Paint;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.Rect;
import android.view.View;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import android.view.ViewGroup;

public class SlidingPaneLayout extends ViewGroup
{
    private static final int DEFAULT_FADE_COLOR = -858993460;
    private static final int DEFAULT_OVERHANG_SIZE = 32;
    static final SlidingPaneLayout$SlidingPanelLayoutImpl IMPL;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "SlidingPaneLayout";
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private SlidingPaneLayout$PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    private final ArrayList<SlidingPaneLayout$DisableLayerRunnable> mPostedRunnables;
    private boolean mPreservedOpenState;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    private float mSlideOffset;
    private int mSlideRange;
    private View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 17) {
            IMPL = new SlidingPaneLayout$SlidingPanelLayoutImplJBMR1();
            return;
        }
        if (sdk_INT >= 16) {
            IMPL = new SlidingPaneLayout$SlidingPanelLayoutImplJB();
            return;
        }
        IMPL = new SlidingPaneLayout$SlidingPanelLayoutImplBase();
    }
    
    public SlidingPaneLayout(final Context context) {
        this(context, null);
    }
    
    public SlidingPaneLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public SlidingPaneLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mSliderFadeColor = -858993460;
        this.mFirstLayout = true;
        this.mTmpRect = new Rect();
        this.mPostedRunnables = new ArrayList<SlidingPaneLayout$DisableLayerRunnable>();
        final float density = context.getResources().getDisplayMetrics().density;
        this.mOverhangSize = (int)(32.0f * density + 0.5f);
        ViewConfiguration.get(context);
        this.setWillNotDraw(false);
        ViewCompat.setAccessibilityDelegate((View)this, new SlidingPaneLayout$AccessibilityDelegate(this));
        ViewCompat.setImportantForAccessibility((View)this, 1);
        (this.mDragHelper = ViewDragHelper.create(this, 0.5f, new SlidingPaneLayout$DragHelperCallback(this, null))).setMinVelocity(density * 400.0f);
    }
    
    private boolean closePane(final View view, final int n) {
        boolean b = false;
        if (this.mFirstLayout || this.smoothSlideTo(0.0f, n)) {
            this.mPreservedOpenState = false;
            b = true;
        }
        return b;
    }
    
    private void dimChildView(final View view, final float n, final int n2) {
        final SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams = (SlidingPaneLayout$LayoutParams)view.getLayoutParams();
        if (n > 0.0f && n2 != 0) {
            final int n3 = (int)(((0xFF000000 & n2) >>> 24) * n);
            if (slidingPaneLayout$LayoutParams.dimPaint == null) {
                slidingPaneLayout$LayoutParams.dimPaint = new Paint();
            }
            slidingPaneLayout$LayoutParams.dimPaint.setColorFilter((ColorFilter)new PorterDuffColorFilter(n3 << 24 | (0xFFFFFF & n2), PorterDuff$Mode.SRC_OVER));
            if (ViewCompat.getLayerType(view) != 2) {
                ViewCompat.setLayerType(view, 2, slidingPaneLayout$LayoutParams.dimPaint);
            }
            this.invalidateChildRegion(view);
        }
        else if (ViewCompat.getLayerType(view) != 0) {
            if (slidingPaneLayout$LayoutParams.dimPaint != null) {
                slidingPaneLayout$LayoutParams.dimPaint.setColorFilter((ColorFilter)null);
            }
            final SlidingPaneLayout$DisableLayerRunnable slidingPaneLayout$DisableLayerRunnable = new SlidingPaneLayout$DisableLayerRunnable(this, view);
            this.mPostedRunnables.add(slidingPaneLayout$DisableLayerRunnable);
            ViewCompat.postOnAnimation((View)this, slidingPaneLayout$DisableLayerRunnable);
        }
    }
    
    private void invalidateChildRegion(final View view) {
        SlidingPaneLayout.IMPL.invalidateChildRegion(this, view);
    }
    
    private boolean isLayoutRtlSupport() {
        return ViewCompat.getLayoutDirection((View)this) == 1;
    }
    
    private void onPanelDragged(int n) {
        if (this.mSlideableView == null) {
            this.mSlideOffset = 0.0f;
            return;
        }
        final boolean layoutRtlSupport = this.isLayoutRtlSupport();
        final SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams = (SlidingPaneLayout$LayoutParams)this.mSlideableView.getLayoutParams();
        final int width = this.mSlideableView.getWidth();
        int n2 = n;
        if (layoutRtlSupport) {
            n2 = this.getWidth() - n - width;
        }
        if (layoutRtlSupport) {
            n = this.getPaddingRight();
        }
        else {
            n = this.getPaddingLeft();
        }
        int n3;
        if (layoutRtlSupport) {
            n3 = slidingPaneLayout$LayoutParams.rightMargin;
        }
        else {
            n3 = slidingPaneLayout$LayoutParams.leftMargin;
        }
        this.mSlideOffset = (n2 - (n3 + n)) / this.mSlideRange;
        if (this.mParallaxBy != 0) {
            this.parallaxOtherViews(this.mSlideOffset);
        }
        if (slidingPaneLayout$LayoutParams.dimWhenOffset) {
            this.dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
        }
        this.dispatchOnPanelSlide(this.mSlideableView);
    }
    
    private boolean openPane(final View view, final int n) {
        return (this.mFirstLayout || this.smoothSlideTo(1.0f, n)) && (this.mPreservedOpenState = true);
    }
    
    private void parallaxOtherViews(final float mParallaxOffset) {
        final boolean layoutRtlSupport = this.isLayoutRtlSupport();
        final SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams = (SlidingPaneLayout$LayoutParams)this.mSlideableView.getLayoutParams();
        while (true) {
            Label_0094: {
                if (!slidingPaneLayout$LayoutParams.dimWhenOffset) {
                    break Label_0094;
                }
                int n;
                if (layoutRtlSupport) {
                    n = slidingPaneLayout$LayoutParams.rightMargin;
                }
                else {
                    n = slidingPaneLayout$LayoutParams.leftMargin;
                }
                if (n > 0) {
                    break Label_0094;
                }
                final boolean b = true;
                for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                    final View child = this.getChildAt(i);
                    if (child != this.mSlideableView) {
                        final int n2 = (int)((1.0f - this.mParallaxOffset) * this.mParallaxBy);
                        this.mParallaxOffset = mParallaxOffset;
                        int n3 = n2 - (int)((1.0f - mParallaxOffset) * this.mParallaxBy);
                        if (layoutRtlSupport) {
                            n3 = -n3;
                        }
                        child.offsetLeftAndRight(n3);
                        if (b) {
                            float n4;
                            if (layoutRtlSupport) {
                                n4 = this.mParallaxOffset - 1.0f;
                            }
                            else {
                                n4 = 1.0f - this.mParallaxOffset;
                            }
                            this.dimChildView(child, n4, this.mCoveredFadeColor);
                        }
                    }
                }
                return;
            }
            final boolean b = false;
            continue;
        }
    }
    
    private static boolean viewIsOpaque(final View view) {
        if (!ViewCompat.isOpaque(view)) {
            if (Build$VERSION.SDK_INT >= 18) {
                return false;
            }
            final Drawable background = view.getBackground();
            if (background == null) {
                return false;
            }
            if (background.getOpacity() != -1) {
                return false;
            }
        }
        return true;
    }
    
    protected boolean canScroll(final View view, final boolean b, int n, final int n2, final int n3) {
        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup)view;
            final int scrollX = view.getScrollX();
            final int scrollY = view.getScrollY();
            for (int i = viewGroup.getChildCount() - 1; i >= 0; --i) {
                final View child = viewGroup.getChildAt(i);
                if (n2 + scrollX >= child.getLeft() && n2 + scrollX < child.getRight() && n3 + scrollY >= child.getTop() && n3 + scrollY < child.getBottom() && this.canScroll(child, true, n, n2 + scrollX - child.getLeft(), n3 + scrollY - child.getTop())) {
                    return true;
                }
            }
        }
        Label_0144: {
            break Label_0144;
        }
        if (b) {
            if (!this.isLayoutRtlSupport()) {
                n = -n;
            }
            if (ViewCompat.canScrollHorizontally(view, n)) {
                return true;
            }
        }
        return false;
    }
    
    @Deprecated
    public boolean canSlide() {
        return this.mCanSlide;
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof SlidingPaneLayout$LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams);
    }
    
    public boolean closePane() {
        return this.closePane(this.mSlideableView, 0);
    }
    
    public void computeScroll() {
        if (this.mDragHelper.continueSettling(true)) {
            if (this.mCanSlide) {
                ViewCompat.postInvalidateOnAnimation((View)this);
                return;
            }
            this.mDragHelper.abort();
        }
    }
    
    void dispatchOnPanelClosed(final View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelClosed(view);
        }
        this.sendAccessibilityEvent(32);
    }
    
    void dispatchOnPanelOpened(final View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelOpened(view);
        }
        this.sendAccessibilityEvent(32);
    }
    
    void dispatchOnPanelSlide(final View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelSlide(view, this.mSlideOffset);
        }
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        Drawable drawable;
        if (this.isLayoutRtlSupport()) {
            drawable = this.mShadowDrawableRight;
        }
        else {
            drawable = this.mShadowDrawableLeft;
        }
        View child;
        if (this.getChildCount() > 1) {
            child = this.getChildAt(1);
        }
        else {
            child = null;
        }
        if (child == null || drawable == null) {
            return;
        }
        final int top = child.getTop();
        final int bottom = child.getBottom();
        final int intrinsicWidth = drawable.getIntrinsicWidth();
        int right;
        int left;
        if (this.isLayoutRtlSupport()) {
            right = child.getRight();
            left = right + intrinsicWidth;
        }
        else {
            left = child.getLeft();
            right = left - intrinsicWidth;
        }
        drawable.setBounds(right, top, left, bottom);
        drawable.draw(canvas);
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        final SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams = (SlidingPaneLayout$LayoutParams)view.getLayoutParams();
        final int save = canvas.save(2);
        if (this.mCanSlide && !slidingPaneLayout$LayoutParams.slideable && this.mSlideableView != null) {
            canvas.getClipBounds(this.mTmpRect);
            if (this.isLayoutRtlSupport()) {
                this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
            }
            else {
                this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
            }
            canvas.clipRect(this.mTmpRect);
        }
        boolean b;
        if (Build$VERSION.SDK_INT >= 11) {
            b = super.drawChild(canvas, view, n);
        }
        else if (slidingPaneLayout$LayoutParams.dimWhenOffset && this.mSlideOffset > 0.0f) {
            if (!view.isDrawingCacheEnabled()) {
                view.setDrawingCacheEnabled(true);
            }
            final Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                canvas.drawBitmap(drawingCache, (float)view.getLeft(), (float)view.getTop(), slidingPaneLayout$LayoutParams.dimPaint);
                b = false;
            }
            else {
                Log.e("SlidingPaneLayout", "drawChild: child view " + view + " returned null drawing cache");
                b = super.drawChild(canvas, view, n);
            }
        }
        else {
            if (view.isDrawingCacheEnabled()) {
                view.setDrawingCacheEnabled(false);
            }
            b = super.drawChild(canvas, view, n);
        }
        canvas.restoreToCount(save);
        return b;
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return (ViewGroup$LayoutParams)new SlidingPaneLayout$LayoutParams();
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return (ViewGroup$LayoutParams)new SlidingPaneLayout$LayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return (ViewGroup$LayoutParams)new SlidingPaneLayout$LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return (ViewGroup$LayoutParams)new SlidingPaneLayout$LayoutParams(viewGroup$LayoutParams);
    }
    
    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }
    
    public int getParallaxDistance() {
        return this.mParallaxBy;
    }
    
    public int getSliderFadeColor() {
        return this.mSliderFadeColor;
    }
    
    boolean isDimmed(final View view) {
        if (view == null) {
            return false;
        }
        final SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams = (SlidingPaneLayout$LayoutParams)view.getLayoutParams();
        return this.mCanSlide && slidingPaneLayout$LayoutParams.dimWhenOffset && this.mSlideOffset > 0.0f;
    }
    
    public boolean isOpen() {
        return !this.mCanSlide || this.mSlideOffset == 1.0f;
    }
    
    public boolean isSlideable() {
        return this.mCanSlide;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
        for (int size = this.mPostedRunnables.size(), i = 0; i < size; ++i) {
            this.mPostedRunnables.get(i).run();
        }
        this.mPostedRunnables.clear();
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final boolean b = false;
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (!this.mCanSlide && actionMasked == 0 && this.getChildCount() > 1) {
            final View child = this.getChildAt(1);
            if (child != null) {
                this.mPreservedOpenState = !this.mDragHelper.isViewUnder(child, (int)motionEvent.getX(), (int)motionEvent.getY());
            }
        }
        boolean onInterceptTouchEvent;
        if (!this.mCanSlide || (this.mIsUnableToDrag && actionMasked != 0)) {
            this.mDragHelper.cancel();
            onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        }
        else {
            if (actionMasked == 3 || actionMasked == 1) {
                this.mDragHelper.cancel();
                return false;
            }
            int n = 0;
            Label_0167: {
                switch (actionMasked) {
                    case 0: {
                        this.mIsUnableToDrag = false;
                        final float x = motionEvent.getX();
                        final float y = motionEvent.getY();
                        this.mInitialMotionX = x;
                        this.mInitialMotionY = y;
                        if (this.mDragHelper.isViewUnder(this.mSlideableView, (int)x, (int)y) && this.isDimmed(this.mSlideableView)) {
                            n = 1;
                            break Label_0167;
                        }
                        break;
                    }
                    case 2: {
                        final float x2 = motionEvent.getX();
                        final float y2 = motionEvent.getY();
                        final float abs = Math.abs(x2 - this.mInitialMotionX);
                        final float abs2 = Math.abs(y2 - this.mInitialMotionY);
                        if (abs > this.mDragHelper.getTouchSlop() && abs2 > abs) {
                            this.mDragHelper.cancel();
                            this.mIsUnableToDrag = true;
                            return false;
                        }
                        break;
                    }
                }
                n = 0;
            }
            if (!this.mDragHelper.shouldInterceptTouchEvent(motionEvent)) {
                onInterceptTouchEvent = b;
                if (n == 0) {
                    return onInterceptTouchEvent;
                }
            }
            return true;
        }
        return onInterceptTouchEvent;
    }
    
    protected void onLayout(final boolean b, int i, int n, int n2, int n3) {
        final boolean layoutRtlSupport = this.isLayoutRtlSupport();
        if (layoutRtlSupport) {
            this.mDragHelper.setEdgeTrackingEnabled(2);
        }
        else {
            this.mDragHelper.setEdgeTrackingEnabled(1);
        }
        final int n4 = n2 - i;
        if (layoutRtlSupport) {
            i = this.getPaddingRight();
        }
        else {
            i = this.getPaddingLeft();
        }
        if (layoutRtlSupport) {
            n3 = this.getPaddingLeft();
        }
        else {
            n3 = this.getPaddingRight();
        }
        final int paddingTop = this.getPaddingTop();
        final int childCount = this.getChildCount();
        if (this.mFirstLayout) {
            float mSlideOffset;
            if (this.mCanSlide && this.mPreservedOpenState) {
                mSlideOffset = 1.0f;
            }
            else {
                mSlideOffset = 0.0f;
            }
            this.mSlideOffset = mSlideOffset;
        }
        int j;
        View child;
        SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams;
        int measuredWidth;
        int mSlideRange;
        int n5;
        int n6;
        for (j = 0, n = i; j < childCount; ++j, n2 = i, i = n, n = n2) {
            child = this.getChildAt(j);
            if (child.getVisibility() == 8) {
                n2 = i;
                i = n;
                n = n2;
            }
            else {
                slidingPaneLayout$LayoutParams = (SlidingPaneLayout$LayoutParams)child.getLayoutParams();
                measuredWidth = child.getMeasuredWidth();
                if (slidingPaneLayout$LayoutParams.slideable) {
                    n2 = slidingPaneLayout$LayoutParams.leftMargin;
                    mSlideRange = Math.min(i, n4 - n3 - this.mOverhangSize) - n - (n2 + slidingPaneLayout$LayoutParams.rightMargin);
                    this.mSlideRange = mSlideRange;
                    if (layoutRtlSupport) {
                        n2 = slidingPaneLayout$LayoutParams.rightMargin;
                    }
                    else {
                        n2 = slidingPaneLayout$LayoutParams.leftMargin;
                    }
                    slidingPaneLayout$LayoutParams.dimWhenOffset = (n + n2 + mSlideRange + measuredWidth / 2 > n4 - n3);
                    n5 = (int)(mSlideRange * this.mSlideOffset);
                    n += n2 + n5;
                    this.mSlideOffset = n5 / this.mSlideRange;
                    n2 = 0;
                }
                else if (this.mCanSlide && this.mParallaxBy != 0) {
                    n2 = (int)((1.0f - this.mSlideOffset) * this.mParallaxBy);
                    n = i;
                }
                else {
                    n2 = 0;
                    n = i;
                }
                if (layoutRtlSupport) {
                    n6 = n4 - n + n2;
                    n2 = n6 - measuredWidth;
                }
                else {
                    n2 = n - n2;
                    n6 = n2 + measuredWidth;
                }
                child.layout(n2, paddingTop, n6, child.getMeasuredHeight() + paddingTop);
                n2 = child.getWidth() + i;
                i = n;
                n = n2;
            }
        }
        if (this.mFirstLayout) {
            if (this.mCanSlide) {
                if (this.mParallaxBy != 0) {
                    this.parallaxOtherViews(this.mSlideOffset);
                }
                if (((SlidingPaneLayout$LayoutParams)this.mSlideableView.getLayoutParams()).dimWhenOffset) {
                    this.dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
                }
            }
            else {
                for (i = 0; i < childCount; ++i) {
                    this.dimChildView(this.getChildAt(i), 0.0f, this.mSliderFadeColor);
                }
            }
            this.updateObscuredViewsVisibility(this.mSlideableView);
        }
        this.mFirstLayout = false;
    }
    
    protected void onMeasure(int size, int n) {
        final int mode = View$MeasureSpec.getMode(size);
        final int size2 = View$MeasureSpec.getSize(size);
        final int mode2 = View$MeasureSpec.getMode(n);
        size = View$MeasureSpec.getSize(n);
        while (true) {
            Label_1105: {
                int n2;
                if (mode != 1073741824) {
                    if (!this.isInEditMode()) {
                        throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
                    }
                    if (mode == Integer.MIN_VALUE) {
                        n = size2;
                        n2 = mode2;
                    }
                    else {
                        if (mode != 0) {
                            break Label_1105;
                        }
                        n2 = mode2;
                        n = 300;
                    }
                }
                else {
                    if (mode2 != 0) {
                        break Label_1105;
                    }
                    if (!this.isInEditMode()) {
                        throw new IllegalStateException("Height must not be UNSPECIFIED");
                    }
                    if (mode2 != 0) {
                        break Label_1105;
                    }
                    final int n3 = Integer.MIN_VALUE;
                    n = size2;
                    size = 300;
                    n2 = n3;
                }
                int n4 = 0;
                switch (n2) {
                    default: {
                        size = 0;
                        n4 = -1;
                        break;
                    }
                    case 1073741824: {
                        size = (n4 = size - this.getPaddingTop() - this.getPaddingBottom());
                        break;
                    }
                    case Integer.MIN_VALUE: {
                        final int paddingTop = this.getPaddingTop();
                        final int paddingBottom = this.getPaddingBottom();
                        final int n5 = 0;
                        n4 = size - paddingTop - paddingBottom;
                        size = n5;
                        break;
                    }
                }
                boolean mCanSlide = false;
                final int n6 = n - this.getPaddingLeft() - this.getPaddingRight();
                final int childCount = this.getChildCount();
                if (childCount > 2) {
                    Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
                }
                this.mSlideableView = null;
                int i = 0;
                int n7 = n6;
                float n8 = 0.0f;
                while (i < childCount) {
                    final View child = this.getChildAt(i);
                    final SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams = (SlidingPaneLayout$LayoutParams)child.getLayoutParams();
                    int n10 = 0;
                    Label_0187: {
                        if (child.getVisibility() == 8) {
                            slidingPaneLayout$LayoutParams.dimWhenOffset = false;
                            final int n9 = n7;
                            n10 = size;
                            size = n9;
                        }
                        else {
                            float n11 = n8;
                            if (slidingPaneLayout$LayoutParams.weight > 0.0f) {
                                n8 = (n11 = n8 + slidingPaneLayout$LayoutParams.weight);
                                if (slidingPaneLayout$LayoutParams.width == 0) {
                                    final int n12 = size;
                                    size = n7;
                                    n10 = n12;
                                    break Label_0187;
                                }
                            }
                            final int n13 = slidingPaneLayout$LayoutParams.leftMargin + slidingPaneLayout$LayoutParams.rightMargin;
                            int n14;
                            if (slidingPaneLayout$LayoutParams.width == -2) {
                                n14 = View$MeasureSpec.makeMeasureSpec(n6 - n13, Integer.MIN_VALUE);
                            }
                            else if (slidingPaneLayout$LayoutParams.width == -1) {
                                n14 = View$MeasureSpec.makeMeasureSpec(n6 - n13, 1073741824);
                            }
                            else {
                                n14 = View$MeasureSpec.makeMeasureSpec(slidingPaneLayout$LayoutParams.width, 1073741824);
                            }
                            int n15;
                            if (slidingPaneLayout$LayoutParams.height == -2) {
                                n15 = View$MeasureSpec.makeMeasureSpec(n4, Integer.MIN_VALUE);
                            }
                            else if (slidingPaneLayout$LayoutParams.height == -1) {
                                n15 = View$MeasureSpec.makeMeasureSpec(n4, 1073741824);
                            }
                            else {
                                n15 = View$MeasureSpec.makeMeasureSpec(slidingPaneLayout$LayoutParams.height, 1073741824);
                            }
                            child.measure(n14, n15);
                            final int measuredWidth = child.getMeasuredWidth();
                            final int measuredHeight = child.getMeasuredHeight();
                            int min = size;
                            if (n2 == Integer.MIN_VALUE && measuredHeight > (min = size)) {
                                min = Math.min(measuredHeight, n4);
                            }
                            size = n7 - measuredWidth;
                            final boolean slideable = size < 0;
                            slidingPaneLayout$LayoutParams.slideable = slideable;
                            if (slidingPaneLayout$LayoutParams.slideable) {
                                this.mSlideableView = child;
                            }
                            n10 = min;
                            mCanSlide |= slideable;
                            n8 = n11;
                        }
                    }
                    ++i;
                    final int n16 = n10;
                    n7 = size;
                    size = n16;
                }
                if (mCanSlide || n8 > 0.0f) {
                    final int n17 = n6 - this.mOverhangSize;
                    for (int j = 0; j < childCount; ++j) {
                        final View child2 = this.getChildAt(j);
                        if (child2.getVisibility() != 8) {
                            final SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams2 = (SlidingPaneLayout$LayoutParams)child2.getLayoutParams();
                            if (child2.getVisibility() != 8) {
                                boolean b;
                                if (slidingPaneLayout$LayoutParams2.width == 0 && slidingPaneLayout$LayoutParams2.weight > 0.0f) {
                                    b = true;
                                }
                                else {
                                    b = false;
                                }
                                int measuredWidth2;
                                if (b) {
                                    measuredWidth2 = 0;
                                }
                                else {
                                    measuredWidth2 = child2.getMeasuredWidth();
                                }
                                if (mCanSlide && child2 != this.mSlideableView) {
                                    if (slidingPaneLayout$LayoutParams2.width < 0 && (measuredWidth2 > n17 || slidingPaneLayout$LayoutParams2.weight > 0.0f)) {
                                        int n18;
                                        if (b) {
                                            if (slidingPaneLayout$LayoutParams2.height == -2) {
                                                n18 = View$MeasureSpec.makeMeasureSpec(n4, Integer.MIN_VALUE);
                                            }
                                            else if (slidingPaneLayout$LayoutParams2.height == -1) {
                                                n18 = View$MeasureSpec.makeMeasureSpec(n4, 1073741824);
                                            }
                                            else {
                                                n18 = View$MeasureSpec.makeMeasureSpec(slidingPaneLayout$LayoutParams2.height, 1073741824);
                                            }
                                        }
                                        else {
                                            n18 = View$MeasureSpec.makeMeasureSpec(child2.getMeasuredHeight(), 1073741824);
                                        }
                                        child2.measure(View$MeasureSpec.makeMeasureSpec(n17, 1073741824), n18);
                                    }
                                }
                                else if (slidingPaneLayout$LayoutParams2.weight > 0.0f) {
                                    int n19;
                                    if (slidingPaneLayout$LayoutParams2.width == 0) {
                                        if (slidingPaneLayout$LayoutParams2.height == -2) {
                                            n19 = View$MeasureSpec.makeMeasureSpec(n4, Integer.MIN_VALUE);
                                        }
                                        else if (slidingPaneLayout$LayoutParams2.height == -1) {
                                            n19 = View$MeasureSpec.makeMeasureSpec(n4, 1073741824);
                                        }
                                        else {
                                            n19 = View$MeasureSpec.makeMeasureSpec(slidingPaneLayout$LayoutParams2.height, 1073741824);
                                        }
                                    }
                                    else {
                                        n19 = View$MeasureSpec.makeMeasureSpec(child2.getMeasuredHeight(), 1073741824);
                                    }
                                    if (mCanSlide) {
                                        final int n20 = n6 - (slidingPaneLayout$LayoutParams2.rightMargin + slidingPaneLayout$LayoutParams2.leftMargin);
                                        final int measureSpec = View$MeasureSpec.makeMeasureSpec(n20, 1073741824);
                                        if (measuredWidth2 != n20) {
                                            child2.measure(measureSpec, n19);
                                        }
                                    }
                                    else {
                                        child2.measure(View$MeasureSpec.makeMeasureSpec((int)(slidingPaneLayout$LayoutParams2.weight * Math.max(0, n7) / n8) + measuredWidth2, 1073741824), n19);
                                    }
                                }
                            }
                        }
                    }
                }
                this.setMeasuredDimension(n, this.getPaddingTop() + size + this.getPaddingBottom());
                this.mCanSlide = mCanSlide;
                if (this.mDragHelper.getViewDragState() != 0 && !mCanSlide) {
                    this.mDragHelper.abort();
                }
                return;
            }
            n = size2;
            int n2 = mode2;
            continue;
        }
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        final SlidingPaneLayout$SavedState slidingPaneLayout$SavedState = (SlidingPaneLayout$SavedState)parcelable;
        super.onRestoreInstanceState(slidingPaneLayout$SavedState.getSuperState());
        if (slidingPaneLayout$SavedState.isOpen) {
            this.openPane();
        }
        else {
            this.closePane();
        }
        this.mPreservedOpenState = slidingPaneLayout$SavedState.isOpen;
    }
    
    protected Parcelable onSaveInstanceState() {
        final SlidingPaneLayout$SavedState slidingPaneLayout$SavedState = new SlidingPaneLayout$SavedState(super.onSaveInstanceState());
        boolean isOpen;
        if (this.isSlideable()) {
            isOpen = this.isOpen();
        }
        else {
            isOpen = this.mPreservedOpenState;
        }
        slidingPaneLayout$SavedState.isOpen = isOpen;
        return (Parcelable)slidingPaneLayout$SavedState;
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n != n3) {
            this.mFirstLayout = true;
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        boolean onTouchEvent = false;
        if (!this.mCanSlide) {
            onTouchEvent = super.onTouchEvent(motionEvent);
        }
        else {
            this.mDragHelper.processTouchEvent(motionEvent);
            final int action = motionEvent.getAction();
            final boolean b = true;
            switch (action & 0xFF) {
                default: {
                    return true;
                }
                case 0: {
                    final float x = motionEvent.getX();
                    final float y = motionEvent.getY();
                    this.mInitialMotionX = x;
                    this.mInitialMotionY = y;
                    return true;
                }
                case 1: {
                    onTouchEvent = b;
                    if (!this.isDimmed(this.mSlideableView)) {
                        break;
                    }
                    final float x2 = motionEvent.getX();
                    final float y2 = motionEvent.getY();
                    final float n = x2 - this.mInitialMotionX;
                    final float n2 = y2 - this.mInitialMotionY;
                    final int touchSlop = this.mDragHelper.getTouchSlop();
                    onTouchEvent = b;
                    if (n * n + n2 * n2 >= touchSlop * touchSlop) {
                        break;
                    }
                    onTouchEvent = b;
                    if (this.mDragHelper.isViewUnder(this.mSlideableView, (int)x2, (int)y2)) {
                        this.closePane(this.mSlideableView, 0);
                        return true;
                    }
                    break;
                }
            }
        }
        return onTouchEvent;
    }
    
    public boolean openPane() {
        return this.openPane(this.mSlideableView, 0);
    }
    
    public void requestChildFocus(final View view, final View view2) {
        super.requestChildFocus(view, view2);
        if (!this.isInTouchMode() && !this.mCanSlide) {
            this.mPreservedOpenState = (view == this.mSlideableView);
        }
    }
    
    void setAllChildrenVisible() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() == 4) {
                child.setVisibility(0);
            }
        }
    }
    
    public void setCoveredFadeColor(final int mCoveredFadeColor) {
        this.mCoveredFadeColor = mCoveredFadeColor;
    }
    
    public void setPanelSlideListener(final SlidingPaneLayout$PanelSlideListener mPanelSlideListener) {
        this.mPanelSlideListener = mPanelSlideListener;
    }
    
    public void setParallaxDistance(final int mParallaxBy) {
        this.mParallaxBy = mParallaxBy;
        this.requestLayout();
    }
    
    @Deprecated
    public void setShadowDrawable(final Drawable shadowDrawableLeft) {
        this.setShadowDrawableLeft(shadowDrawableLeft);
    }
    
    public void setShadowDrawableLeft(final Drawable mShadowDrawableLeft) {
        this.mShadowDrawableLeft = mShadowDrawableLeft;
    }
    
    public void setShadowDrawableRight(final Drawable mShadowDrawableRight) {
        this.mShadowDrawableRight = mShadowDrawableRight;
    }
    
    @Deprecated
    public void setShadowResource(final int n) {
        this.setShadowDrawable(this.getResources().getDrawable(n));
    }
    
    public void setShadowResourceLeft(final int n) {
        this.setShadowDrawableLeft(this.getResources().getDrawable(n));
    }
    
    public void setShadowResourceRight(final int n) {
        this.setShadowDrawableRight(this.getResources().getDrawable(n));
    }
    
    public void setSliderFadeColor(final int mSliderFadeColor) {
        this.mSliderFadeColor = mSliderFadeColor;
    }
    
    @Deprecated
    public void smoothSlideClosed() {
        this.closePane();
    }
    
    @Deprecated
    public void smoothSlideOpen() {
        this.openPane();
    }
    
    boolean smoothSlideTo(final float n, int n2) {
        if (!this.mCanSlide) {
            return false;
        }
        final boolean layoutRtlSupport = this.isLayoutRtlSupport();
        final SlidingPaneLayout$LayoutParams slidingPaneLayout$LayoutParams = (SlidingPaneLayout$LayoutParams)this.mSlideableView.getLayoutParams();
        if (layoutRtlSupport) {
            n2 = this.getPaddingRight();
            n2 = (int)(this.getWidth() - (slidingPaneLayout$LayoutParams.rightMargin + n2 + this.mSlideRange * n + this.mSlideableView.getWidth()));
        }
        else {
            n2 = this.getPaddingLeft();
            n2 = (int)(slidingPaneLayout$LayoutParams.leftMargin + n2 + this.mSlideRange * n);
        }
        if (this.mDragHelper.smoothSlideViewTo(this.mSlideableView, n2, this.mSlideableView.getTop())) {
            this.setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation((View)this);
            return true;
        }
        return false;
    }
    
    void updateObscuredViewsVisibility(final View view) {
        final boolean layoutRtlSupport = this.isLayoutRtlSupport();
        int paddingLeft;
        if (layoutRtlSupport) {
            paddingLeft = this.getWidth() - this.getPaddingRight();
        }
        else {
            paddingLeft = this.getPaddingLeft();
        }
        int paddingLeft2;
        if (layoutRtlSupport) {
            paddingLeft2 = this.getPaddingLeft();
        }
        else {
            paddingLeft2 = this.getWidth() - this.getPaddingRight();
        }
        final int paddingTop = this.getPaddingTop();
        final int height = this.getHeight();
        final int paddingBottom = this.getPaddingBottom();
        int left;
        int right;
        int top;
        int bottom;
        if (view != null && viewIsOpaque(view)) {
            left = view.getLeft();
            right = view.getRight();
            top = view.getTop();
            bottom = view.getBottom();
        }
        else {
            bottom = 0;
            top = 0;
            right = 0;
            left = 0;
        }
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child == view) {
                break;
            }
            int n;
            if (layoutRtlSupport) {
                n = paddingLeft2;
            }
            else {
                n = paddingLeft;
            }
            final int max = Math.max(n, child.getLeft());
            final int max2 = Math.max(paddingTop, child.getTop());
            int n2;
            if (layoutRtlSupport) {
                n2 = paddingLeft;
            }
            else {
                n2 = paddingLeft2;
            }
            final int min = Math.min(n2, child.getRight());
            final int min2 = Math.min(height - paddingBottom, child.getBottom());
            int visibility;
            if (max >= left && max2 >= top && min <= right && min2 <= bottom) {
                visibility = 4;
            }
            else {
                visibility = 0;
            }
            child.setVisibility(visibility);
        }
    }
}
