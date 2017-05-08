// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.view;

import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import android.view.View$MeasureSpec;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import android.view.MotionEvent;
import android.view.View$OnLayoutChangeListener;
import android.view.animation.Animation;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import android.view.View;
import android.graphics.Rect;
import android.view.ViewGroup$LayoutParams;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.touch.ReactHitSlopView;
import android.view.ViewGroup;

public class ReactViewGroup extends ViewGroup implements ReactHitSlopView, ReactInterceptingViewGroup, ReactClippingViewGroup, ReactPointerEventsView
{
    private static final ViewGroup$LayoutParams sDefaultLayoutParam;
    private static final Rect sHelperRect;
    private View[] mAllChildren;
    private int mAllChildrenCount;
    private ReactViewGroup$ChildrenLayoutChangeListener mChildrenLayoutChangeListener;
    private Rect mClippingRect;
    private Rect mHitSlopRect;
    private boolean mNeedsOffscreenAlphaCompositing;
    private OnInterceptTouchEventListener mOnInterceptTouchEventListener;
    private PointerEvents mPointerEvents;
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private boolean mRemoveClippedSubviews;
    
    static {
        sDefaultLayoutParam = new ViewGroup$LayoutParams(0, 0);
        sHelperRect = new Rect();
    }
    
    public ReactViewGroup(final Context context) {
        super(context);
        this.mRemoveClippedSubviews = false;
        this.mAllChildren = null;
        this.mPointerEvents = PointerEvents.AUTO;
        this.mNeedsOffscreenAlphaCompositing = false;
    }
    
    private void addInArray(final View view, int n) {
        View[] mAllChildren = Assertions.assertNotNull(this.mAllChildren);
        final int mAllChildrenCount = this.mAllChildrenCount;
        final int length = mAllChildren.length;
        if (n == mAllChildrenCount) {
            View[] mAllChildren2 = mAllChildren;
            if (length == mAllChildrenCount) {
                System.arraycopy(mAllChildren, 0, this.mAllChildren = new View[length + 12], 0, length);
                mAllChildren2 = this.mAllChildren;
            }
            n = this.mAllChildrenCount++;
            mAllChildren2[n] = view;
            return;
        }
        if (n < mAllChildrenCount) {
            if (length == mAllChildrenCount) {
                System.arraycopy(mAllChildren, 0, this.mAllChildren = new View[length + 12], 0, n);
                System.arraycopy(mAllChildren, n, this.mAllChildren, n + 1, mAllChildrenCount - n);
                mAllChildren = this.mAllChildren;
            }
            else {
                System.arraycopy(mAllChildren, n, mAllChildren, n + 1, mAllChildrenCount - n);
            }
            mAllChildren[n] = view;
            ++this.mAllChildrenCount;
            return;
        }
        throw new IndexOutOfBoundsException("index=" + n + " count=" + mAllChildrenCount);
    }
    
    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable();
            final Drawable background = this.getBackground();
            super.setBackground((Drawable)null);
            if (background == null) {
                super.setBackground((Drawable)this.mReactBackgroundDrawable);
            }
            else {
                super.setBackground((Drawable)new LayerDrawable(new Drawable[] { this.mReactBackgroundDrawable, background }));
            }
        }
        return this.mReactBackgroundDrawable;
    }
    
    private int indexOfChildInAllChildren(final View view) {
        final int mAllChildrenCount = this.mAllChildrenCount;
        final View[] array = Assertions.assertNotNull(this.mAllChildren);
        for (int i = 0; i < mAllChildrenCount; ++i) {
            if (array[i] == view) {
                return i;
            }
        }
        return -1;
    }
    
    private void removeFromArray(int n) {
        final View[] array = Assertions.assertNotNull(this.mAllChildren);
        final int mAllChildrenCount = this.mAllChildrenCount;
        if (n == mAllChildrenCount - 1) {
            n = this.mAllChildrenCount - 1;
            array[this.mAllChildrenCount = n] = null;
            return;
        }
        if (n >= 0 && n < mAllChildrenCount) {
            System.arraycopy(array, n + 1, array, n, mAllChildrenCount - n - 1);
            n = this.mAllChildrenCount - 1;
            array[this.mAllChildrenCount = n] = null;
            return;
        }
        throw new IndexOutOfBoundsException();
    }
    
    private void updateClippingToRect(final Rect rect) {
        int i = 0;
        Assertions.assertNotNull(this.mAllChildren);
        int n = 0;
        while (i < this.mAllChildrenCount) {
            this.updateSubviewClipStatus(rect, i, n);
            int n2 = n;
            if (this.mAllChildren[i].getParent() == null) {
                n2 = n + 1;
            }
            ++i;
            n = n2;
        }
    }
    
    private void updateSubviewClipStatus(final Rect rect, int n, final int n2) {
        final int n3 = 1;
        final View view = Assertions.assertNotNull(this.mAllChildren)[n];
        ReactViewGroup.sHelperRect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        final boolean intersects = rect.intersects(ReactViewGroup.sHelperRect.left, ReactViewGroup.sHelperRect.top, ReactViewGroup.sHelperRect.right, ReactViewGroup.sHelperRect.bottom);
        final Animation animation = view.getAnimation();
        boolean b;
        if (animation != null && !animation.hasEnded()) {
            b = true;
        }
        else {
            b = false;
        }
        if (!intersects && view.getParent() != null && !b) {
            super.removeViewsInLayout(n - n2, 1);
            n = n3;
        }
        else if (intersects && view.getParent() == null) {
            super.addViewInLayout(view, n - n2, ReactViewGroup.sDefaultLayoutParam, true);
            this.invalidate();
            n = n3;
        }
        else {
            n = n3;
            if (!intersects) {
                n = 0;
            }
        }
        if (n != 0 && view instanceof ReactClippingViewGroup) {
            final ReactClippingViewGroup reactClippingViewGroup = (ReactClippingViewGroup)view;
            if (reactClippingViewGroup.getRemoveClippedSubviews()) {
                reactClippingViewGroup.updateClippingRect();
            }
        }
    }
    
    private void updateSubviewClipStatus(final View view) {
        int i = 0;
        if (this.mRemoveClippedSubviews && this.getParent() != null) {
            Assertions.assertNotNull(this.mClippingRect);
            Assertions.assertNotNull(this.mAllChildren);
            ReactViewGroup.sHelperRect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (this.mClippingRect.intersects(ReactViewGroup.sHelperRect.left, ReactViewGroup.sHelperRect.top, ReactViewGroup.sHelperRect.right, ReactViewGroup.sHelperRect.bottom) != (view.getParent() != null)) {
                int n = 0;
                while (i < this.mAllChildrenCount) {
                    if (this.mAllChildren[i] == view) {
                        this.updateSubviewClipStatus(this.mClippingRect, i, n);
                        return;
                    }
                    int n2 = n;
                    if (this.mAllChildren[i].getParent() == null) {
                        n2 = n + 1;
                    }
                    ++i;
                    n = n2;
                }
            }
        }
    }
    
    void addViewWithSubviewClippingEnabled(final View view, final int n) {
        this.addViewWithSubviewClippingEnabled(view, n, ReactViewGroup.sDefaultLayoutParam);
    }
    
    void addViewWithSubviewClippingEnabled(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        int n2 = 0;
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        this.addInArray(view, n);
        int n3;
        for (int i = 0; i < n; ++i, n2 = n3) {
            n3 = n2;
            if (this.mAllChildren[i].getParent() == null) {
                n3 = n2 + 1;
            }
        }
        this.updateSubviewClipStatus(this.mClippingRect, n, n2);
        view.addOnLayoutChangeListener((View$OnLayoutChangeListener)this.mChildrenLayoutChangeListener);
    }
    
    protected void dispatchSetPressed(final boolean b) {
    }
    
    int getAllChildrenCount() {
        return this.mAllChildrenCount;
    }
    
    public int getBackgroundColor() {
        if (this.getBackground() != null) {
            return ((ReactViewBackgroundDrawable)this.getBackground()).getColor();
        }
        return 0;
    }
    
    View getChildAtWithSubviewClippingEnabled(final int n) {
        return Assertions.assertNotNull(this.mAllChildren)[n];
    }
    
    public void getClippingRect(final Rect rect) {
        rect.set(this.mClippingRect);
    }
    
    public Rect getHitSlopRect() {
        return this.mHitSlopRect;
    }
    
    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }
    
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }
    
    public boolean hasOverlappingRendering() {
        return this.mNeedsOffscreenAlphaCompositing;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            this.updateClippingRect();
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        return (this.mOnInterceptTouchEventListener != null && this.mOnInterceptTouchEventListener.onInterceptTouchEvent(this, motionEvent)) || this.mPointerEvents == PointerEvents.NONE || this.mPointerEvents == PointerEvents.BOX_ONLY || super.onInterceptTouchEvent(motionEvent);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
    }
    
    protected void onMeasure(final int n, final int n2) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(n, n2);
        this.setMeasuredDimension(View$MeasureSpec.getSize(n), View$MeasureSpec.getSize(n2));
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.mRemoveClippedSubviews) {
            this.updateClippingRect();
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.mPointerEvents != PointerEvents.NONE && this.mPointerEvents != PointerEvents.BOX_NONE;
    }
    
    void removeAllViewsWithSubviewClippingEnabled() {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mAllChildren);
        for (int i = 0; i < this.mAllChildrenCount; ++i) {
            this.mAllChildren[i].removeOnLayoutChangeListener((View$OnLayoutChangeListener)this.mChildrenLayoutChangeListener);
        }
        this.removeAllViewsInLayout();
        this.mAllChildrenCount = 0;
    }
    
    void removeViewWithSubviewClippingEnabled(final View view) {
        int n = 0;
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        view.removeOnLayoutChangeListener((View$OnLayoutChangeListener)this.mChildrenLayoutChangeListener);
        final int indexOfChildInAllChildren = this.indexOfChildInAllChildren(view);
        if (this.mAllChildren[indexOfChildInAllChildren].getParent() != null) {
            int n2;
            for (int i = 0; i < indexOfChildInAllChildren; ++i, n = n2) {
                n2 = n;
                if (this.mAllChildren[i].getParent() == null) {
                    n2 = n + 1;
                }
            }
            super.removeViewsInLayout(indexOfChildInAllChildren - n, 1);
        }
        this.removeFromArray(indexOfChildInAllChildren);
    }
    
    public void requestLayout() {
    }
    
    public void setBackground(final Drawable drawable) {
        throw new UnsupportedOperationException("This method is not supported for ReactViewGroup instances");
    }
    
    public void setBackgroundColor(final int color) {
        if (color == 0 && this.mReactBackgroundDrawable == null) {
            return;
        }
        this.getOrCreateReactViewBackground().setColor(color);
    }
    
    public void setBorderColor(final int n, final float n2, final float n3) {
        this.getOrCreateReactViewBackground().setBorderColor(n, n2, n3);
    }
    
    public void setBorderRadius(final float radius) {
        this.getOrCreateReactViewBackground().setRadius(radius);
    }
    
    public void setBorderRadius(final float n, final int n2) {
        this.getOrCreateReactViewBackground().setRadius(n, n2);
    }
    
    public void setBorderStyle(final String borderStyle) {
        this.getOrCreateReactViewBackground().setBorderStyle(borderStyle);
    }
    
    public void setBorderWidth(final int n, final float n2) {
        this.getOrCreateReactViewBackground().setBorderWidth(n, n2);
    }
    
    public void setHitSlopRect(final Rect mHitSlopRect) {
        this.mHitSlopRect = mHitSlopRect;
    }
    
    public void setNeedsOffscreenAlphaCompositing(final boolean mNeedsOffscreenAlphaCompositing) {
        this.mNeedsOffscreenAlphaCompositing = mNeedsOffscreenAlphaCompositing;
    }
    
    public void setOnInterceptTouchEventListener(final OnInterceptTouchEventListener mOnInterceptTouchEventListener) {
        this.mOnInterceptTouchEventListener = mOnInterceptTouchEventListener;
    }
    
    void setPointerEvents(final PointerEvents mPointerEvents) {
        this.mPointerEvents = mPointerEvents;
    }
    
    public void setRemoveClippedSubviews(final boolean mRemoveClippedSubviews) {
        int i = 0;
        if (mRemoveClippedSubviews == this.mRemoveClippedSubviews) {
            return;
        }
        this.mRemoveClippedSubviews = mRemoveClippedSubviews;
        if (mRemoveClippedSubviews) {
            ReactClippingViewGroupHelper.calculateClippingRect((View)this, this.mClippingRect = new Rect());
            this.mAllChildrenCount = this.getChildCount();
            this.mAllChildren = new View[Math.max(12, this.mAllChildrenCount)];
            this.mChildrenLayoutChangeListener = new ReactViewGroup$ChildrenLayoutChangeListener(this, null);
            while (i < this.mAllChildrenCount) {
                (this.mAllChildren[i] = this.getChildAt(i)).addOnLayoutChangeListener((View$OnLayoutChangeListener)this.mChildrenLayoutChangeListener);
                ++i;
            }
            this.updateClippingRect();
            return;
        }
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        Assertions.assertNotNull(this.mChildrenLayoutChangeListener);
        for (int j = 0; j < this.mAllChildrenCount; ++j) {
            this.mAllChildren[j].removeOnLayoutChangeListener((View$OnLayoutChangeListener)this.mChildrenLayoutChangeListener);
        }
        this.getDrawingRect(this.mClippingRect);
        this.updateClippingToRect(this.mClippingRect);
        this.mAllChildren = null;
        this.mClippingRect = null;
        this.mAllChildrenCount = 0;
        this.mChildrenLayoutChangeListener = null;
    }
    
    public void setTranslucentBackgroundDrawable(final Drawable background) {
        super.setBackground((Drawable)null);
        if (this.mReactBackgroundDrawable != null && background != null) {
            super.setBackground((Drawable)new LayerDrawable(new Drawable[] { this.mReactBackgroundDrawable, background }));
        }
        else if (background != null) {
            super.setBackground(background);
        }
    }
    
    public void updateClippingRect() {
        if (!this.mRemoveClippedSubviews) {
            return;
        }
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        ReactClippingViewGroupHelper.calculateClippingRect((View)this, this.mClippingRect);
        this.updateClippingToRect(this.mClippingRect);
    }
}
