// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.widget.LinearLayout$LayoutParams;
import android.view.View$MeasureSpec;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.content.Context;
import android.graphics.Paint;
import android.widget.LinearLayout;

class TabLayout$SlidingTabStrip extends LinearLayout
{
    private ValueAnimatorCompat mIndicatorAnimator;
    private int mIndicatorLeft;
    private int mIndicatorRight;
    private int mSelectedIndicatorHeight;
    private final Paint mSelectedIndicatorPaint;
    int mSelectedPosition;
    float mSelectionOffset;
    final /* synthetic */ TabLayout this$0;
    
    TabLayout$SlidingTabStrip(final TabLayout this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.mSelectedPosition = -1;
        this.mIndicatorLeft = -1;
        this.mIndicatorRight = -1;
        this.setWillNotDraw(false);
        this.mSelectedIndicatorPaint = new Paint();
    }
    
    private void updateIndicatorPosition() {
        final View child = this.getChildAt(this.mSelectedPosition);
        int right;
        int n2;
        if (child != null && child.getWidth() > 0) {
            final int left = child.getLeft();
            final int n = right = child.getRight();
            n2 = left;
            if (this.mSelectionOffset > 0.0f) {
                right = n;
                n2 = left;
                if (this.mSelectedPosition < this.getChildCount() - 1) {
                    final View child2 = this.getChildAt(this.mSelectedPosition + 1);
                    n2 = (int)(left * (1.0f - this.mSelectionOffset) + this.mSelectionOffset * child2.getLeft());
                    right = (int)(n * (1.0f - this.mSelectionOffset) + child2.getRight() * this.mSelectionOffset);
                }
            }
        }
        else {
            right = -1;
            n2 = -1;
        }
        this.setIndicatorPosition(n2, right);
    }
    
    void animateIndicatorToPosition(final int n, final int n2) {
        if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
            this.mIndicatorAnimator.cancel();
        }
        final boolean b = ViewCompat.getLayoutDirection((View)this) == 1;
        final View child = this.getChildAt(n);
        if (child == null) {
            this.updateIndicatorPosition();
        }
        else {
            final int left = child.getLeft();
            final int right = child.getRight();
            int mIndicatorLeft;
            int mIndicatorRight;
            if (Math.abs(n - this.mSelectedPosition) <= 1) {
                mIndicatorLeft = this.mIndicatorLeft;
                mIndicatorRight = this.mIndicatorRight;
            }
            else {
                final int dpToPx = this.this$0.dpToPx(24);
                if (n < this.mSelectedPosition) {
                    if (b) {
                        mIndicatorRight = (mIndicatorLeft = left - dpToPx);
                    }
                    else {
                        mIndicatorRight = (mIndicatorLeft = right + dpToPx);
                    }
                }
                else if (b) {
                    mIndicatorRight = (mIndicatorLeft = right + dpToPx);
                }
                else {
                    mIndicatorRight = (mIndicatorLeft = left - dpToPx);
                }
            }
            if (mIndicatorLeft != left || mIndicatorRight != right) {
                final ValueAnimatorCompat animator = ViewUtils.createAnimator();
                (this.mIndicatorAnimator = animator).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                animator.setDuration(n2);
                animator.setFloatValues(0.0f, 1.0f);
                animator.addUpdateListener(new TabLayout$SlidingTabStrip$1(this, mIndicatorLeft, left, mIndicatorRight, right));
                animator.addListener(new TabLayout$SlidingTabStrip$2(this, n));
                animator.start();
            }
        }
    }
    
    boolean childrenNeedLayout() {
        final boolean b = false;
        final int childCount = this.getChildCount();
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= childCount) {
                break;
            }
            if (this.getChildAt(n).getWidth() <= 0) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (this.mIndicatorLeft >= 0 && this.mIndicatorRight > this.mIndicatorLeft) {
            canvas.drawRect((float)this.mIndicatorLeft, (float)(this.getHeight() - this.mSelectedIndicatorHeight), (float)this.mIndicatorRight, (float)this.getHeight(), this.mSelectedIndicatorPaint);
        }
    }
    
    float getIndicatorPosition() {
        return this.mSelectedPosition + this.mSelectionOffset;
    }
    
    protected void onLayout(final boolean b, int mSelectedPosition, final int n, final int n2, final int n3) {
        super.onLayout(b, mSelectedPosition, n, n2, n3);
        if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
            this.mIndicatorAnimator.cancel();
            final long duration = this.mIndicatorAnimator.getDuration();
            mSelectedPosition = this.mSelectedPosition;
            this.animateIndicatorToPosition(mSelectedPosition, Math.round(duration * (1.0f - this.mIndicatorAnimator.getAnimatedFraction())));
            return;
        }
        this.updateIndicatorPosition();
    }
    
    protected void onMeasure(final int n, final int n2) {
        final boolean b = false;
        super.onMeasure(n, n2);
        if (View$MeasureSpec.getMode(n) == 1073741824 && this.this$0.mMode == 1 && this.this$0.mTabGravity == 1) {
            final int childCount = this.getChildCount();
            int i = 0;
            int width = 0;
            while (i < childCount) {
                final View child = this.getChildAt(i);
                int max;
                if (child.getVisibility() == 0) {
                    max = Math.max(width, child.getMeasuredWidth());
                }
                else {
                    max = width;
                }
                ++i;
                width = max;
            }
            if (width > 0) {
                int n5;
                if (width * childCount <= this.getMeasuredWidth() - this.this$0.dpToPx(16) * 2) {
                    int n3 = 0;
                    int n4 = b ? 1 : 0;
                    while (true) {
                        n5 = n4;
                        if (n3 >= childCount) {
                            break;
                        }
                        final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)this.getChildAt(n3).getLayoutParams();
                        if (linearLayout$LayoutParams.width != width || linearLayout$LayoutParams.weight != 0.0f) {
                            linearLayout$LayoutParams.width = width;
                            linearLayout$LayoutParams.weight = 0.0f;
                            n4 = 1;
                        }
                        ++n3;
                    }
                }
                else {
                    this.this$0.mTabGravity = 0;
                    this.this$0.updateTabViews(false);
                    n5 = 1;
                }
                if (n5 != 0) {
                    super.onMeasure(n, n2);
                }
            }
        }
    }
    
    void setIndicatorPosition(final int mIndicatorLeft, final int mIndicatorRight) {
        if (mIndicatorLeft != this.mIndicatorLeft || mIndicatorRight != this.mIndicatorRight) {
            this.mIndicatorLeft = mIndicatorLeft;
            this.mIndicatorRight = mIndicatorRight;
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    void setIndicatorPositionFromTabPosition(final int mSelectedPosition, final float mSelectionOffset) {
        if (this.mIndicatorAnimator != null && this.mIndicatorAnimator.isRunning()) {
            this.mIndicatorAnimator.cancel();
        }
        this.mSelectedPosition = mSelectedPosition;
        this.mSelectionOffset = mSelectionOffset;
        this.updateIndicatorPosition();
    }
    
    void setSelectedIndicatorColor(final int color) {
        if (this.mSelectedIndicatorPaint.getColor() != color) {
            this.mSelectedIndicatorPaint.setColor(color);
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    void setSelectedIndicatorHeight(final int mSelectedIndicatorHeight) {
        if (this.mSelectedIndicatorHeight != mSelectedIndicatorHeight) {
            this.mSelectedIndicatorHeight = mSelectedIndicatorHeight;
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
}
