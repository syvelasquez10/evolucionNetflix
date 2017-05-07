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
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewCompat;
import android.content.res.TypedArray;
import android.view.ViewConfiguration;
import com.netflix.mediaclient.R;
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
    private static final int DEFAULT_FADE_COLOR = -1728053248;
    private static final int DEFAULT_MIN_FLING_VELOCITY = 400;
    private static final int DEFAULT_PANEL_HEIGHT = 68;
    private static final int DEFAULT_SHADOW_HEIGHT = 4;
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
    private PanelSlideListener mPanelSlideListener;
    private final int mScrollTouchSlop;
    private Drawable mShadowDrawable;
    private int mShadowHeight;
    private float mSlideOffset;
    private int mSlideRange;
    private SlideState mSlideState;
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
        this.mSlideState = SlideState.COLLAPSED;
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
            final TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(set, R.styleable.SlidingUpPanelLayout);
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
        (this.mDragHelper = ViewDragHelper.create(this, 0.5f, (ViewDragHelper.Callback)new DragHelperCallback())).setMinVelocity(this.mMinFlingVelocity * density);
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
        if (n < array[0] || n >= array[0] + view.getWidth() || n2 < array[1] || n2 >= array[1] + view.getHeight()) {
            b = false;
        }
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
    
    protected boolean canScroll(final View view, final boolean b, final int n, final int n2, final int n3) {
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
        return b && ViewCompat.canScrollHorizontally(view, -n);
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams);
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
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final int save = canvas.save(2);
        boolean b2;
        final boolean b = b2 = false;
        if (this.mCanSlide) {
            b2 = b;
            if (!layoutParams.slideable) {
                b2 = b;
                if (this.mSlideableView != null) {
                    canvas.getClipBounds(this.mTmpRect);
                    if (this.mIsSlidingUp) {
                        this.mTmpRect.bottom = Math.min(this.mTmpRect.bottom, this.mSlideableView.getTop());
                    }
                    else {
                        this.mTmpRect.top = Math.max(this.mTmpRect.top, this.mSlideableView.getBottom());
                    }
                    canvas.clipRect(this.mTmpRect);
                    b2 = b;
                    if (this.mSlideOffset < 1.0f) {
                        b2 = true;
                    }
                }
            }
        }
        final boolean drawChild = super.drawChild(canvas, view, n);
        canvas.restoreToCount(save);
        if (b2) {
            this.mCoveredFadePaint.setColor((int)(((this.mCoveredFadeColor & 0xFF000000) >>> 24) * (1.0f - this.mSlideOffset)) << 24 | (this.mCoveredFadeColor & 0xFFFFFF));
            canvas.drawRect(this.mTmpRect, this.mCoveredFadePaint);
        }
        return drawChild;
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
        return (ViewGroup$LayoutParams)new LayoutParams();
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return (ViewGroup$LayoutParams)new LayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return (ViewGroup$LayoutParams)new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return (ViewGroup$LayoutParams)new LayoutParams(viewGroup$LayoutParams);
    }
    
    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }
    
    public int getPanelHeight() {
        return this.mPanelHeight;
    }
    
    public void hidePane() {
        if (this.mSlideableView == null) {
            return;
        }
        this.mSlideableView.setVisibility(8);
        this.requestLayout();
    }
    
    public boolean isAnchored() {
        return this.mSlideState == SlideState.ANCHORED;
    }
    
    public boolean isExpanded() {
        return this.mSlideState == SlideState.EXPANDED;
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
            final boolean b = false;
            int dragViewUnder;
            final int n = dragViewUnder = 0;
            while (true) {
                switch (actionMasked) {
                    default: {
                        dragViewUnder = n;
                    }
                    case 1: {
                        if (this.mDragHelper.shouldInterceptTouchEvent(motionEvent) || dragViewUnder != 0) {
                            return true;
                        }
                        break;
                    }
                    case 0: {
                        this.mIsUnableToDrag = false;
                        this.mInitialMotionX = x;
                        this.mInitialMotionY = y;
                        dragViewUnder = n;
                        if (!this.isDragViewUnder((int)x, (int)y)) {
                            continue;
                        }
                        dragViewUnder = n;
                        if (!this.mIsUsingDragViewTouchEvents) {
                            dragViewUnder = 1;
                        }
                        continue;
                    }
                    case 2: {
                        final float abs = Math.abs(x - this.mInitialMotionX);
                        final float abs2 = Math.abs(y - this.mInitialMotionY);
                        final int touchSlop = this.mDragHelper.getTouchSlop();
                        dragViewUnder = (b ? 1 : 0);
                        if (this.mIsUsingDragViewTouchEvents) {
                            if (abs > this.mScrollTouchSlop && abs2 < this.mScrollTouchSlop) {
                                return super.onInterceptTouchEvent(motionEvent);
                            }
                            dragViewUnder = (b ? 1 : 0);
                            if (abs2 > this.mScrollTouchSlop) {
                                dragViewUnder = (this.isDragViewUnder((int)x, (int)y) ? 1 : 0);
                            }
                        }
                        if ((abs2 > touchSlop && abs > abs2) || !this.isDragViewUnder((int)x, (int)y)) {
                            this.mDragHelper.cancel();
                            this.mIsUnableToDrag = true;
                            return false;
                        }
                        continue;
                    }
                }
                break;
            }
        }
        return onInterceptTouchEvent;
    }
    
    protected void onLayout(final boolean b, int n, int i, int paddingTop, int paddingLeft) {
        paddingLeft = this.getPaddingLeft();
        paddingTop = this.getPaddingTop();
        final int slidingTop = this.getSlidingTop();
        final int childCount = this.getChildCount();
        if (this.mFirstLayout) {
            switch (this.mSlideState) {
                default: {
                    this.mSlideOffset = 1.0f;
                    break;
                }
                case EXPANDED: {
                    float mSlideOffset;
                    if (this.mCanSlide) {
                        mSlideOffset = 0.0f;
                    }
                    else {
                        mSlideOffset = 1.0f;
                    }
                    this.mSlideOffset = mSlideOffset;
                    break;
                }
                case ANCHORED: {
                    float mAnchorPoint;
                    if (this.mCanSlide) {
                        mAnchorPoint = this.mAnchorPoint;
                    }
                    else {
                        mAnchorPoint = 1.0f;
                    }
                    this.mSlideOffset = mAnchorPoint;
                    break;
                }
            }
        }
        View child;
        LayoutParams layoutParams;
        int measuredHeight;
        for (i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                layoutParams = (LayoutParams)child.getLayoutParams();
                measuredHeight = child.getMeasuredHeight();
                if (layoutParams.slideable) {
                    this.mSlideRange = measuredHeight - this.mPanelHeight;
                }
                if (this.mIsSlidingUp) {
                    if (layoutParams.slideable) {
                        n = slidingTop + (int)(this.mSlideRange * this.mSlideOffset);
                    }
                    else {
                        n = paddingTop;
                    }
                }
                else if (layoutParams.slideable) {
                    n = slidingTop - (int)(this.mSlideRange * this.mSlideOffset);
                }
                else {
                    n = paddingTop + this.mPanelHeight;
                }
                child.layout(paddingLeft, n, paddingLeft + child.getMeasuredWidth(), n + measuredHeight);
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
        final int paddingTop = this.getPaddingTop();
        final int paddingBottom = this.getPaddingBottom();
        int mPanelHeight = this.mPanelHeight;
        final int childCount = this.getChildCount();
        if (childCount > 2) {
            Log.e(SlidingUpPanelLayout.TAG, "onMeasure: More than two child views are not supported.");
        }
        else if (this.getChildAt(1).getVisibility() == 8) {
            mPanelHeight = 0;
        }
        this.mSlideableView = null;
        this.mCanSlide = false;
        for (int i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            n2 = size2 - paddingTop - paddingBottom;
            if (child.getVisibility() == 8) {
                layoutParams.dimWhenOffset = false;
            }
            else {
                if (i == 1) {
                    layoutParams.slideable = true;
                    layoutParams.dimWhenOffset = true;
                    this.mSlideableView = child;
                    this.mCanSlide = true;
                }
                else {
                    n2 -= mPanelHeight;
                }
                if (layoutParams.width == -2) {
                    n = View$MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
                }
                else if (layoutParams.width == -1) {
                    n = View$MeasureSpec.makeMeasureSpec(size, 1073741824);
                }
                else {
                    n = View$MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
                }
                if (layoutParams.height == -2) {
                    n2 = View$MeasureSpec.makeMeasureSpec(n2, Integer.MIN_VALUE);
                }
                else if (layoutParams.height == -1) {
                    n2 = View$MeasureSpec.makeMeasureSpec(n2, 1073741824);
                }
                else {
                    n2 = View$MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
                }
                child.measure(n, n2);
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
        for (int i = 0; i < this.getChildCount(); ++i) {
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
    
    public void setPanelSlideListener(final PanelSlideListener mPanelSlideListener) {
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
        if (this.mCanSlide) {
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
        }
        return false;
    }
    
    void updateObscuredViewVisibility() {
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
        int visibility;
        if (max >= left && max2 >= top && min <= right && min2 <= bottom) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        child.setVisibility(visibility);
    }
    
    private class DragHelperCallback extends Callback
    {
        @Override
        public int clampViewPositionVertical(final View view, final int n, int paddingTop) {
            int access$1000;
            if (SlidingUpPanelLayout.this.mIsSlidingUp) {
                access$1000 = SlidingUpPanelLayout.this.getSlidingTop();
                paddingTop = access$1000 + SlidingUpPanelLayout.this.mSlideRange;
            }
            else {
                paddingTop = SlidingUpPanelLayout.this.getPaddingTop();
                access$1000 = paddingTop - SlidingUpPanelLayout.this.mSlideRange;
            }
            return Math.min(Math.max(n, access$1000), paddingTop);
        }
        
        @Override
        public int getViewVerticalDragRange(final View view) {
            return SlidingUpPanelLayout.this.mSlideRange;
        }
        
        @Override
        public void onViewCaptured(final View view, final int n) {
            SlidingUpPanelLayout.this.setAllChildrenVisible();
        }
        
        @Override
        public void onViewDragStateChanged(int n) {
            n = (int)(SlidingUpPanelLayout.this.mAnchorPoint * SlidingUpPanelLayout.this.mSlideRange);
            if (SlidingUpPanelLayout.this.mDragHelper.getViewDragState() == 0) {
                if (SlidingUpPanelLayout.this.mSlideOffset == 0.0f) {
                    if (SlidingUpPanelLayout.this.mSlideState != SlideState.EXPANDED) {
                        SlidingUpPanelLayout.this.updateObscuredViewVisibility();
                        SlidingUpPanelLayout.this.dispatchOnPanelExpanded(SlidingUpPanelLayout.this.mSlideableView);
                        SlidingUpPanelLayout.this.mSlideState = SlideState.EXPANDED;
                    }
                }
                else if (SlidingUpPanelLayout.this.mSlideOffset == n / SlidingUpPanelLayout.this.mSlideRange) {
                    if (SlidingUpPanelLayout.this.mSlideState != SlideState.ANCHORED) {
                        SlidingUpPanelLayout.this.updateObscuredViewVisibility();
                        SlidingUpPanelLayout.this.dispatchOnPanelAnchored(SlidingUpPanelLayout.this.mSlideableView);
                        SlidingUpPanelLayout.this.mSlideState = SlideState.ANCHORED;
                    }
                }
                else if (SlidingUpPanelLayout.this.mSlideState != SlideState.COLLAPSED) {
                    SlidingUpPanelLayout.this.dispatchOnPanelCollapsed(SlidingUpPanelLayout.this.mSlideableView);
                    SlidingUpPanelLayout.this.mSlideState = SlideState.COLLAPSED;
                }
            }
        }
        
        @Override
        public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
            SlidingUpPanelLayout.this.onPanelDragged(n2);
            SlidingUpPanelLayout.this.invalidate();
        }
        
        @Override
        public void onViewReleased(final View view, float n, final float n2) {
            int access$1000;
            if (SlidingUpPanelLayout.this.mIsSlidingUp) {
                access$1000 = SlidingUpPanelLayout.this.getSlidingTop();
            }
            else {
                access$1000 = SlidingUpPanelLayout.this.getSlidingTop() - SlidingUpPanelLayout.this.mSlideRange;
            }
            int n3 = 0;
            Label_0109: {
                if (SlidingUpPanelLayout.this.mAnchorPoint != 0.0f) {
                    if (SlidingUpPanelLayout.this.mIsSlidingUp) {
                        n = (int)(SlidingUpPanelLayout.this.mAnchorPoint * SlidingUpPanelLayout.this.mSlideRange) / SlidingUpPanelLayout.this.mSlideRange;
                    }
                    else {
                        n = (SlidingUpPanelLayout.this.mPanelHeight - (SlidingUpPanelLayout.this.mPanelHeight - (int)(SlidingUpPanelLayout.this.mAnchorPoint * SlidingUpPanelLayout.this.mSlideRange))) / SlidingUpPanelLayout.this.mSlideRange;
                    }
                    if (n2 > 0.0f || (n2 == 0.0f && SlidingUpPanelLayout.this.mSlideOffset >= (1.0f + n) / 2.0f)) {
                        n3 = access$1000 + SlidingUpPanelLayout.this.mSlideRange;
                    }
                    else {
                        n3 = access$1000;
                        if (n2 == 0.0f) {
                            n3 = access$1000;
                            if (SlidingUpPanelLayout.this.mSlideOffset < (1.0f + n) / 2.0f) {
                                n3 = access$1000;
                                if (SlidingUpPanelLayout.this.mSlideOffset >= n / 2.0f) {
                                    n3 = (int)(access$1000 + SlidingUpPanelLayout.this.mSlideRange * SlidingUpPanelLayout.this.mAnchorPoint);
                                }
                            }
                        }
                    }
                }
                else {
                    if (n2 <= 0.0f) {
                        n3 = access$1000;
                        if (n2 != 0.0f) {
                            break Label_0109;
                        }
                        n3 = access$1000;
                        if (SlidingUpPanelLayout.this.mSlideOffset <= 0.5f) {
                            break Label_0109;
                        }
                    }
                    n3 = access$1000 + SlidingUpPanelLayout.this.mSlideRange;
                }
            }
            SlidingUpPanelLayout.this.mDragHelper.settleCapturedViewAt(view.getLeft(), n3);
            SlidingUpPanelLayout.this.invalidate();
        }
        
        @Override
        public boolean tryCaptureView(final View view, final int n) {
            return !SlidingUpPanelLayout.this.mIsUnableToDrag && ((LayoutParams)view.getLayoutParams()).slideable;
        }
    }
    
    public static class LayoutParams extends ViewGroup$MarginLayoutParams
    {
        private static final int[] ATTRS;
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        
        static {
            ATTRS = new int[] { 16843137 };
        }
        
        public LayoutParams() {
            super(-1, -1);
        }
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            context.obtainStyledAttributes(set, LayoutParams.ATTRS).recycle();
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((ViewGroup$MarginLayoutParams)layoutParams);
        }
    }
    
    public interface PanelSlideListener
    {
        void onPanelAnchored(final View p0);
        
        void onPanelCollapsed(final View p0);
        
        void onPanelExpanded(final View p0);
        
        void onPanelSlide(final View p0, final float p1);
    }
    
    public static class SimplePanelSlideListener implements PanelSlideListener
    {
        @Override
        public void onPanelAnchored(final View view) {
        }
        
        @Override
        public void onPanelCollapsed(final View view) {
        }
        
        @Override
        public void onPanelExpanded(final View view) {
        }
        
        @Override
        public void onPanelSlide(final View view, final float n) {
        }
    }
    
    private enum SlideState
    {
        ANCHORED, 
        COLLAPSED, 
        EXPANDED;
    }
}
