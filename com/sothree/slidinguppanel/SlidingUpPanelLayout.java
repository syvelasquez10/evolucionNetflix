// 
// Decompiled by Procyon v0.5.30
// 

package com.sothree.slidinguppanel;

import android.util.Log;
import android.view.View$MeasureSpec;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.view.ViewConfiguration;
import android.support.v4.widget.ViewDragHelper$Callback;
import com.netflix.mediaclient.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.support.v4.widget.ViewDragHelper;
import android.graphics.Paint;
import android.view.ViewGroup;

public class SlidingUpPanelLayout extends ViewGroup
{
    private static final int[] DEFAULT_ATTRS;
    private static final String TAG;
    private float mAnchorPoint;
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private final Paint mCoveredFadePaint;
    private final ViewDragHelper mDragHelper;
    private View mDragView;
    private int mDragViewResId;
    private boolean mFirstLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsSlidingEnabled;
    private boolean mIsSlidingUp;
    private boolean mIsUnableToDrag;
    private boolean mIsUsingDragViewTouchEvents;
    private int mMinFlingVelocity;
    private int mPanelHeight;
    private SlidingUpPanelLayout$PanelSlideListener mPanelSlideListener;
    private final int mScrollTouchSlop;
    private Drawable mShadowDrawable;
    private int mShadowHeight;
    private float mSlideOffset;
    private int mSlideRange;
    private SlidingUpPanelLayout$SlideState mSlideState;
    private View mSlideableView;
    private final Rect mTmpRect;
    
    static {
        TAG = SlidingUpPanelLayout.class.getSimpleName();
        DEFAULT_ATTRS = new int[] { 16842927 };
    }
    
    public SlidingUpPanelLayout(final Context context) {
        this(context, null);
    }
    
    public SlidingUpPanelLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public SlidingUpPanelLayout(final Context context, final AttributeSet set, int int1) {
        super(context, set, int1);
        this.mMinFlingVelocity = 400;
        this.mCoveredFadeColor = -1728053248;
        this.mCoveredFadePaint = new Paint();
        this.mPanelHeight = -1;
        this.mShadowHeight = -1;
        this.mDragViewResId = -1;
        this.mSlideState = SlidingUpPanelLayout$SlideState.COLLAPSED;
        this.mAnchorPoint = 0.0f;
        this.mFirstLayout = true;
        this.mTmpRect = new Rect();
        if (set != null) {
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, SlidingUpPanelLayout.DEFAULT_ATTRS);
            if (obtainStyledAttributes != null) {
                int1 = obtainStyledAttributes.getInt(0, 0);
                if (int1 != 48 && int1 != 80) {
                    throw new IllegalArgumentException("layout_gravity must be set to either top or bottom");
                }
                this.mIsSlidingUp = (int1 == 80);
            }
            obtainStyledAttributes.recycle();
            final TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(set, R$styleable.SlidingUpPanelLayout);
            if (obtainStyledAttributes2 != null) {
                this.mPanelHeight = obtainStyledAttributes2.getDimensionPixelSize(0, -1);
                this.mShadowHeight = obtainStyledAttributes2.getDimensionPixelSize(1, -1);
                this.mMinFlingVelocity = obtainStyledAttributes2.getInt(3, 400);
                this.mCoveredFadeColor = obtainStyledAttributes2.getColor(2, -1728053248);
                this.mDragViewResId = obtainStyledAttributes2.getResourceId(4, -1);
            }
            obtainStyledAttributes2.recycle();
        }
        final float density = context.getResources().getDisplayMetrics().density;
        if (this.mPanelHeight == -1) {
            this.mPanelHeight = (int)(68.0f * density + 0.5f);
        }
        if (this.mShadowHeight == -1) {
            this.mShadowHeight = (int)(4.0f * density + 0.5f);
        }
        this.setWillNotDraw(false);
        (this.mDragHelper = ViewDragHelper.create(this, 0.5f, new SlidingUpPanelLayout$DragHelperCallback(this, null))).setMinVelocity(density * this.mMinFlingVelocity);
        this.mCanSlide = true;
        this.mIsSlidingEnabled = true;
        this.mScrollTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
    
    private boolean collapsePane(final View view, final int n) {
        return this.mFirstLayout || this.smoothSlideTo(1.0f, n);
    }
    
    private boolean expandPane(final View view, final int n, final float n2) {
        return this.mFirstLayout || this.smoothSlideTo(n2, n);
    }
    
    private int getSlidingTop() {
        if (this.mSlideableView != null) {
            return this.getMeasuredHeight() - this.getPaddingBottom() - this.mSlideableView.getMeasuredHeight();
        }
        return this.getMeasuredHeight() - this.getPaddingBottom();
    }
    
    private static boolean hasOpaqueBackground(final View view) {
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
    
    private boolean isDragViewUnder(int n, int n2) {
        boolean b = true;
        View view;
        if (this.mDragView != null) {
            view = this.mDragView;
        }
        else {
            view = this.mSlideableView;
        }
        if (view == null) {
            return false;
        }
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        final int[] array2 = new int[2];
        this.getLocationOnScreen(array2);
        n += array2[0];
        n2 += array2[1];
        if (n < array[0] || n >= array[0] + view.getWidth() || n2 < array[1]) {
            return false;
        }
        n = array[1];
        if (n2 >= view.getHeight() + n) {
            return false;
        }
        return b;
        b = false;
        return b;
    }
    
    private void onPanelDragged(final int n) {
        final int slidingTop = this.getSlidingTop();
        float mSlideOffset;
        if (this.mIsSlidingUp) {
            mSlideOffset = (n - slidingTop) / this.mSlideRange;
        }
        else {
            mSlideOffset = (slidingTop - n) / this.mSlideRange;
        }
        this.mSlideOffset = mSlideOffset;
        if (this.mSlideOffset < 0.0f) {
            this.mSlideOffset = 0.0f;
        }
        else if (this.mSlideOffset > 1.0f) {
            this.mSlideOffset = 1.0f;
        }
        this.dispatchOnPanelSlide(this.mSlideableView);
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof SlidingUpPanelLayout$LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams);
    }
    
    public boolean collapsePane() {
        return this.collapsePane(this.mSlideableView, 0);
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
    
    void dispatchOnPanelAnchored(final View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelAnchored(view);
        }
        this.sendAccessibilityEvent(32);
    }
    
    void dispatchOnPanelCollapsed(final View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelCollapsed(view);
        }
        this.sendAccessibilityEvent(32);
    }
    
    void dispatchOnPanelExpanded(final View view) {
        if (this.mPanelSlideListener != null) {
            this.mPanelSlideListener.onPanelExpanded(view);
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
        if (this.mSlideableView != null) {
            final int right = this.mSlideableView.getRight();
            int bottom;
            int top;
            if (this.mIsSlidingUp) {
                bottom = this.mSlideableView.getTop() - this.mShadowHeight;
                top = this.mSlideableView.getTop();
            }
            else {
                bottom = this.mSlideableView.getBottom();
                top = this.mSlideableView.getBottom() + this.mShadowHeight;
            }
            final int left = this.mSlideableView.getLeft();
            if (this.mShadowDrawable != null) {
                this.mShadowDrawable.setBounds(left, bottom, right, top);
                this.mShadowDrawable.draw(canvas);
            }
        }
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        final SlidingUpPanelLayout$LayoutParams slidingUpPanelLayout$LayoutParams = (SlidingUpPanelLayout$LayoutParams)view.getLayoutParams();
        final int save = canvas.save(2);
        while (true) {
            Label_0208: {
                if (!this.mCanSlide || slidingUpPanelLayout$LayoutParams.slideable || this.mSlideableView == null) {
                    break Label_0208;
                }
                canvas.getClipBounds(this.mTmpRect);
                if (this.mIsSlidingUp) {
                    this.mTmpRect.bottom = Math.min(this.mTmpRect.bottom, this.mSlideableView.getTop());
                }
                else {
                    this.mTmpRect.top = Math.max(this.mTmpRect.top, this.mSlideableView.getBottom());
                }
                canvas.clipRect(this.mTmpRect);
                if (this.mSlideOffset >= 1.0f) {
                    break Label_0208;
                }
                final int n2 = 1;
                final boolean drawChild = super.drawChild(canvas, view, n);
                canvas.restoreToCount(save);
                if (n2 != 0) {
                    this.mCoveredFadePaint.setColor((int)(((this.mCoveredFadeColor & 0xFF000000) >>> 24) * (1.0f - this.mSlideOffset)) << 24 | (this.mCoveredFadeColor & 0xFFFFFF));
                    canvas.drawRect(this.mTmpRect, this.mCoveredFadePaint);
                }
                return drawChild;
            }
            final int n2 = 0;
            continue;
        }
    }
    
    public boolean expandPane() {
        return this.expandPane(0.0f);
    }
    
    public boolean expandPane(final float n) {
        if (!this.isPaneVisible()) {
            this.showPane();
        }
        return this.expandPane(this.mSlideableView, 0, n);
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return (ViewGroup$LayoutParams)new SlidingUpPanelLayout$LayoutParams();
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return (ViewGroup$LayoutParams)new SlidingUpPanelLayout$LayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return (ViewGroup$LayoutParams)new SlidingUpPanelLayout$LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return (ViewGroup$LayoutParams)new SlidingUpPanelLayout$LayoutParams(viewGroup$LayoutParams);
    }
    
    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }
    
    public int getPanelHeight() {
        return this.mPanelHeight;
    }
    
    public boolean isAnchored() {
        return this.mSlideState == SlidingUpPanelLayout$SlideState.ANCHORED;
    }
    
    public boolean isExpanded() {
        return this.mSlideState == SlidingUpPanelLayout$SlideState.EXPANDED;
    }
    
    public boolean isPaneVisible() {
        boolean b = true;
        if (this.getChildCount() < 2) {
            return false;
        }
        if (this.getChildAt(1).getVisibility() != 0) {
            b = false;
        }
        return b;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
    }
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.mDragViewResId != -1) {
            this.mDragView = this.findViewById(this.mDragViewResId);
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        boolean onInterceptTouchEvent = false;
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (!this.mCanSlide || !this.mIsSlidingEnabled || (this.mIsUnableToDrag && actionMasked != 0)) {
            this.mDragHelper.cancel();
            onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        }
        else {
            if (actionMasked == 3 || actionMasked == 1) {
                this.mDragHelper.cancel();
                return false;
            }
            final float x = motionEvent.getX();
            final float y = motionEvent.getY();
            boolean dragViewUnder = false;
            Label_0115: {
                switch (actionMasked) {
                    case 0: {
                        this.mIsUnableToDrag = false;
                        this.mInitialMotionX = x;
                        this.mInitialMotionY = y;
                        if (this.isDragViewUnder((int)x, (int)y) && !this.mIsUsingDragViewTouchEvents) {
                            dragViewUnder = true;
                            break Label_0115;
                        }
                        break;
                    }
                    case 2: {
                        final float abs = Math.abs(x - this.mInitialMotionX);
                        final float abs2 = Math.abs(y - this.mInitialMotionY);
                        final int touchSlop = this.mDragHelper.getTouchSlop();
                        while (true) {
                            Label_0301: {
                                if (!this.mIsUsingDragViewTouchEvents) {
                                    break Label_0301;
                                }
                                if (abs > this.mScrollTouchSlop && abs2 < this.mScrollTouchSlop) {
                                    return super.onInterceptTouchEvent(motionEvent);
                                }
                                if (abs2 <= this.mScrollTouchSlop) {
                                    break Label_0301;
                                }
                                dragViewUnder = this.isDragViewUnder((int)x, (int)y);
                                if ((abs2 > touchSlop && abs > abs2) || !this.isDragViewUnder((int)x, (int)y)) {
                                    this.mDragHelper.cancel();
                                    this.mIsUnableToDrag = true;
                                    return false;
                                }
                                break Label_0115;
                            }
                            dragViewUnder = false;
                            continue;
                        }
                    }
                }
                dragViewUnder = false;
            }
            if (this.mDragHelper.shouldInterceptTouchEvent(motionEvent) || dragViewUnder) {
                return true;
            }
        }
        return onInterceptTouchEvent;
    }
    
    protected void onLayout(final boolean b, int n, int i, int paddingTop, int paddingLeft) {
        float mAnchorPoint = 1.0f;
        paddingLeft = this.getPaddingLeft();
        paddingTop = this.getPaddingTop();
        final int slidingTop = this.getSlidingTop();
        final int childCount = this.getChildCount();
        if (this.mFirstLayout) {
            switch (SlidingUpPanelLayout$1.$SwitchMap$com$sothree$slidinguppanel$SlidingUpPanelLayout$SlideState[this.mSlideState.ordinal()]) {
                default: {
                    this.mSlideOffset = 1.0f;
                    break;
                }
                case 1: {
                    if (this.mCanSlide) {
                        mAnchorPoint = 0.0f;
                    }
                    this.mSlideOffset = mAnchorPoint;
                    break;
                }
                case 2: {
                    if (this.mCanSlide) {
                        mAnchorPoint = this.mAnchorPoint;
                    }
                    this.mSlideOffset = mAnchorPoint;
                    break;
                }
            }
        }
        View child;
        SlidingUpPanelLayout$LayoutParams slidingUpPanelLayout$LayoutParams;
        int measuredHeight;
        for (i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                slidingUpPanelLayout$LayoutParams = (SlidingUpPanelLayout$LayoutParams)child.getLayoutParams();
                measuredHeight = child.getMeasuredHeight();
                if (slidingUpPanelLayout$LayoutParams.slideable) {
                    this.mSlideRange = measuredHeight - this.mPanelHeight;
                }
                if (this.mIsSlidingUp) {
                    if (slidingUpPanelLayout$LayoutParams.slideable) {
                        n = (int)(this.mSlideRange * this.mSlideOffset) + slidingTop;
                    }
                    else {
                        n = paddingTop;
                    }
                }
                else if (slidingUpPanelLayout$LayoutParams.slideable) {
                    n = slidingTop - (int)(this.mSlideRange * this.mSlideOffset);
                }
                else {
                    n = this.mPanelHeight + paddingTop;
                }
                child.layout(paddingLeft, n, child.getMeasuredWidth() + paddingLeft, measuredHeight + n);
            }
        }
        if (this.mFirstLayout) {
            this.updateObscuredViewVisibility();
        }
        this.mFirstLayout = false;
    }
    
    protected void onMeasure(int n, int n2) {
        final int mode = View$MeasureSpec.getMode(n);
        final int size = View$MeasureSpec.getSize(n);
        n = View$MeasureSpec.getMode(n2);
        final int size2 = View$MeasureSpec.getSize(n2);
        if (mode != 1073741824) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
        }
        if (n != 1073741824) {
            throw new IllegalStateException("Height must have an exact value or MATCH_PARENT");
        }
        final int n3 = size2 - this.getPaddingTop() - this.getPaddingBottom();
        n = this.mPanelHeight;
        final int childCount = this.getChildCount();
        if (childCount > 2) {
            Log.e(SlidingUpPanelLayout.TAG, "onMeasure: More than two child views are not supported.");
        }
        else if (this.getChildAt(1).getVisibility() == 8) {
            n = 0;
        }
        this.mSlideableView = null;
        this.mCanSlide = false;
        for (int i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final SlidingUpPanelLayout$LayoutParams slidingUpPanelLayout$LayoutParams = (SlidingUpPanelLayout$LayoutParams)child.getLayoutParams();
            if (child.getVisibility() == 8) {
                slidingUpPanelLayout$LayoutParams.dimWhenOffset = false;
            }
            else {
                int n4;
                if (i == 1) {
                    slidingUpPanelLayout$LayoutParams.slideable = true;
                    slidingUpPanelLayout$LayoutParams.dimWhenOffset = true;
                    this.mSlideableView = child;
                    this.mCanSlide = true;
                    n4 = n3;
                }
                else {
                    n4 = n3 - n;
                }
                if (slidingUpPanelLayout$LayoutParams.width == -2) {
                    n2 = View$MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
                }
                else if (slidingUpPanelLayout$LayoutParams.width == -1) {
                    n2 = View$MeasureSpec.makeMeasureSpec(size, 1073741824);
                }
                else {
                    n2 = View$MeasureSpec.makeMeasureSpec(slidingUpPanelLayout$LayoutParams.width, 1073741824);
                }
                int n5;
                if (slidingUpPanelLayout$LayoutParams.height == -2) {
                    n5 = View$MeasureSpec.makeMeasureSpec(n4, Integer.MIN_VALUE);
                }
                else if (slidingUpPanelLayout$LayoutParams.height == -1) {
                    n5 = View$MeasureSpec.makeMeasureSpec(n4, 1073741824);
                }
                else {
                    n5 = View$MeasureSpec.makeMeasureSpec(slidingUpPanelLayout$LayoutParams.height, 1073741824);
                }
                child.measure(n2, n5);
            }
        }
        this.setMeasuredDimension(size, size2);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n2 != n4) {
            this.mFirstLayout = true;
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        boolean onTouchEvent = false;
        if (!this.mCanSlide || !this.mIsSlidingEnabled) {
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
                    final float x2 = motionEvent.getX();
                    final float y2 = motionEvent.getY();
                    final float n = x2 - this.mInitialMotionX;
                    final float n2 = y2 - this.mInitialMotionY;
                    final int touchSlop = this.mDragHelper.getTouchSlop();
                    View view;
                    if (this.mDragView != null) {
                        view = this.mDragView;
                    }
                    else {
                        view = this.mSlideableView;
                    }
                    onTouchEvent = b;
                    if (n * n + n2 * n2 >= touchSlop * touchSlop) {
                        break;
                    }
                    onTouchEvent = b;
                    if (!this.isDragViewUnder((int)x2, (int)y2)) {
                        break;
                    }
                    view.playSoundEffect(0);
                    if (!this.isExpanded() && !this.isAnchored()) {
                        this.expandPane(this.mAnchorPoint);
                        return true;
                    }
                    this.collapsePane();
                    return true;
                }
            }
        }
        return onTouchEvent;
    }
    
    void setAllChildrenVisible() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() == 4) {
                child.setVisibility(0);
            }
        }
    }
    
    public void setAnchorPoint(final float mAnchorPoint) {
        if (mAnchorPoint > 0.0f && mAnchorPoint < 1.0f) {
            this.mAnchorPoint = mAnchorPoint;
        }
    }
    
    public void setCoveredFadeColor(final int mCoveredFadeColor) {
        this.mCoveredFadeColor = mCoveredFadeColor;
        this.invalidate();
    }
    
    public void setDragView(final View mDragView) {
        this.mDragView = mDragView;
    }
    
    public void setEnableDragViewTouchEvents(final boolean mIsUsingDragViewTouchEvents) {
        this.mIsUsingDragViewTouchEvents = mIsUsingDragViewTouchEvents;
    }
    
    public void setPanelHeight(final int mPanelHeight) {
        this.mPanelHeight = mPanelHeight;
        this.requestLayout();
    }
    
    public void setPanelSlideListener(final SlidingUpPanelLayout$PanelSlideListener mPanelSlideListener) {
        this.mPanelSlideListener = mPanelSlideListener;
    }
    
    public void setShadowDrawable(final Drawable mShadowDrawable) {
        this.mShadowDrawable = mShadowDrawable;
    }
    
    public void setSlidingEnabled(final boolean mIsSlidingEnabled) {
        this.mIsSlidingEnabled = mIsSlidingEnabled;
    }
    
    public void showPane() {
        if (this.getChildCount() < 2) {
            return;
        }
        this.getChildAt(1).setVisibility(0);
        this.requestLayout();
    }
    
    boolean smoothSlideTo(final float n, int slidingTop) {
        if (!this.mCanSlide) {
            return false;
        }
        slidingTop = this.getSlidingTop();
        if (this.mIsSlidingUp) {
            slidingTop += (int)(this.mSlideRange * n);
        }
        else {
            slidingTop -= (int)(this.mSlideRange * n);
        }
        if (this.mDragHelper.smoothSlideViewTo(this.mSlideableView, this.mSlideableView.getLeft(), slidingTop)) {
            this.setAllChildrenVisible();
            ViewCompat.postInvalidateOnAnimation((View)this);
            return true;
        }
        return false;
    }
    
    void updateObscuredViewVisibility() {
        final int n = 0;
        if (this.getChildCount() == 0) {
            return;
        }
        final int paddingLeft = this.getPaddingLeft();
        final int width = this.getWidth();
        final int paddingRight = this.getPaddingRight();
        final int paddingTop = this.getPaddingTop();
        final int height = this.getHeight();
        final int paddingBottom = this.getPaddingBottom();
        int left;
        int right;
        int top;
        int bottom;
        if (this.mSlideableView != null && hasOpaqueBackground(this.mSlideableView)) {
            left = this.mSlideableView.getLeft();
            right = this.mSlideableView.getRight();
            top = this.mSlideableView.getTop();
            bottom = this.mSlideableView.getBottom();
        }
        else {
            bottom = 0;
            top = 0;
            right = 0;
            left = 0;
        }
        final View child = this.getChildAt(0);
        final int max = Math.max(paddingLeft, child.getLeft());
        final int max2 = Math.max(paddingTop, child.getTop());
        final int min = Math.min(width - paddingRight, child.getRight());
        final int min2 = Math.min(height - paddingBottom, child.getBottom());
        int visibility = n;
        if (max >= left) {
            visibility = n;
            if (max2 >= top) {
                visibility = n;
                if (min <= right) {
                    visibility = n;
                    if (min2 <= bottom) {
                        visibility = 4;
                    }
                }
            }
        }
        child.setVisibility(visibility);
    }
}
