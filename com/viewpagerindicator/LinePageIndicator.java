// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import android.annotation.SuppressLint;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.os.Parcelable;
import android.graphics.Canvas;
import android.util.FloatMath;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import android.content.res.Resources;
import android.support.v4.view.ViewConfigurationCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import com.viewpagerindicator.android.osp.ViewPager;
import android.graphics.Paint;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import android.view.View;

public class LinePageIndicator extends View implements PageIndicator
{
    private static final int INVALID_POINTER = -1;
    private int mActivePointerId;
    private boolean mCentered;
    private int mCurrentPage;
    private float mGapWidth;
    private boolean mIsDragging;
    private float mLastMotionX;
    private float mLineWidth;
    private ViewPager$OnPageChangeListener mListener;
    private final Paint mPaintSelected;
    private final Paint mPaintUnselected;
    private int mTouchSlop;
    private ViewPager mViewPager;
    
    public LinePageIndicator(final Context context) {
        this(context, null);
    }
    
    public LinePageIndicator(final Context context, final AttributeSet set) {
        this(context, set, R$attr.vpiLinePageIndicatorStyle);
    }
    
    public LinePageIndicator(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mPaintUnselected = new Paint(1);
        this.mPaintSelected = new Paint(1);
        this.mLastMotionX = -1.0f;
        this.mActivePointerId = -1;
        if (this.isInEditMode()) {
            return;
        }
        final Resources resources = this.getResources();
        final int color = resources.getColor(R$color.default_line_indicator_selected_color);
        final int color2 = resources.getColor(R$color.default_line_indicator_unselected_color);
        final float dimension = resources.getDimension(R$dimen.default_line_indicator_line_width);
        final float dimension2 = resources.getDimension(R$dimen.default_line_indicator_gap_width);
        final float dimension3 = resources.getDimension(R$dimen.default_line_indicator_stroke_width);
        final boolean boolean1 = resources.getBoolean(R$bool.default_line_indicator_centered);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.LinePageIndicator, n, 0);
        this.mCentered = obtainStyledAttributes.getBoolean(1, boolean1);
        this.mLineWidth = obtainStyledAttributes.getDimension(5, dimension);
        this.mGapWidth = obtainStyledAttributes.getDimension(6, dimension2);
        this.setStrokeWidth(obtainStyledAttributes.getDimension(3, dimension3));
        this.mPaintUnselected.setColor(obtainStyledAttributes.getColor(4, color2));
        this.mPaintSelected.setColor(obtainStyledAttributes.getColor(2, color));
        final Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable != null) {
            this.setBackgroundDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }
    
    private int measureHeight(int size) {
        final int mode = View$MeasureSpec.getMode(size);
        size = View$MeasureSpec.getSize(size);
        float min;
        if (mode == 1073741824) {
            min = size;
        }
        else {
            min = this.mPaintSelected.getStrokeWidth() + this.getPaddingTop() + this.getPaddingBottom();
            if (mode == Integer.MIN_VALUE) {
                min = Math.min(min, size);
            }
        }
        return (int)FloatMath.ceil(min);
    }
    
    private int measureWidth(int size) {
        final int mode = View$MeasureSpec.getMode(size);
        size = View$MeasureSpec.getSize(size);
        float min;
        if (mode == 1073741824 || this.mViewPager == null) {
            min = size;
        }
        else {
            final int count = this.mViewPager.getAdapter().getCount();
            min = (count - 1) * this.mGapWidth + (this.getPaddingLeft() + this.getPaddingRight() + count * this.mLineWidth);
            if (mode == Integer.MIN_VALUE) {
                min = Math.min(min, size);
            }
        }
        return (int)FloatMath.ceil(min);
    }
    
    public float getGapWidth() {
        return this.mGapWidth;
    }
    
    public float getLineWidth() {
        return this.mLineWidth;
    }
    
    public int getSelectedColor() {
        return this.mPaintSelected.getColor();
    }
    
    public float getStrokeWidth() {
        return this.mPaintSelected.getStrokeWidth();
    }
    
    public int getUnselectedColor() {
        return this.mPaintUnselected.getColor();
    }
    
    public boolean isCentered() {
        return this.mCentered;
    }
    
    public void notifyDataSetChanged() {
        this.invalidate();
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.mViewPager != null) {
            final int count = this.mViewPager.getAdapter().getCount();
            if (count != 0) {
                if (this.mCurrentPage >= count) {
                    this.setCurrentItem(count - 1);
                    return;
                }
                final float n = this.mLineWidth + this.mGapWidth;
                final float n2 = count;
                final float mGapWidth = this.mGapWidth;
                final float n3 = this.getPaddingTop();
                float n4 = this.getPaddingLeft();
                final float n5 = this.getPaddingRight();
                final float n6 = n3 + (this.getHeight() - n3 - this.getPaddingBottom()) / 2.0f;
                if (this.mCentered) {
                    n4 += (this.getWidth() - n4 - n5) / 2.0f - (n2 * n - mGapWidth) / 2.0f;
                }
                for (int i = 0; i < count; ++i) {
                    final float n7 = n4 + i * n;
                    final float mLineWidth = this.mLineWidth;
                    Paint paint;
                    if (i == this.mCurrentPage) {
                        paint = this.mPaintSelected;
                    }
                    else {
                        paint = this.mPaintUnselected;
                    }
                    canvas.drawLine(n7, n6, n7 + mLineWidth, n6, paint);
                }
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.setMeasuredDimension(this.measureWidth(n), this.measureHeight(n2));
    }
    
    public void onPageScrollStateChanged(final int n) {
        if (this.mListener != null) {
            this.mListener.onPageScrollStateChanged(n);
        }
    }
    
    public void onPageScrolled(final int n, final float n2, final int n3) {
        if (this.mListener != null) {
            this.mListener.onPageScrolled(n, n2, n3);
        }
    }
    
    public void onPageSelected(final int mCurrentPage) {
        this.mCurrentPage = mCurrentPage;
        this.invalidate();
        if (this.mListener != null) {
            this.mListener.onPageSelected(mCurrentPage);
        }
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final LinePageIndicator$SavedState linePageIndicator$SavedState = (LinePageIndicator$SavedState)parcelable;
        super.onRestoreInstanceState(linePageIndicator$SavedState.getSuperState());
        this.mCurrentPage = linePageIndicator$SavedState.currentPage;
        this.requestLayout();
    }
    
    public Parcelable onSaveInstanceState() {
        final LinePageIndicator$SavedState linePageIndicator$SavedState = new LinePageIndicator$SavedState(super.onSaveInstanceState());
        linePageIndicator$SavedState.currentPage = this.mCurrentPage;
        return (Parcelable)linePageIndicator$SavedState;
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        int n = 0;
        if (!super.onTouchEvent(motionEvent)) {
            if (this.mViewPager == null || this.mViewPager.getAdapter().getCount() == 0) {
                return false;
            }
            final int n2 = motionEvent.getAction() & 0xFF;
            switch (n2) {
                default: {
                    return true;
                }
                case 0: {
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    this.mLastMotionX = motionEvent.getX();
                    return true;
                }
                case 2: {
                    final float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId));
                    final float n3 = x - this.mLastMotionX;
                    if (!this.mIsDragging && Math.abs(n3) > this.mTouchSlop) {
                        this.mIsDragging = true;
                    }
                    if (!this.mIsDragging) {
                        break;
                    }
                    this.mLastMotionX = x;
                    if (this.mViewPager.isFakeDragging() || this.mViewPager.beginFakeDrag()) {
                        this.mViewPager.fakeDragBy(n3);
                        return true;
                    }
                    break;
                }
                case 1:
                case 3: {
                    if (!this.mIsDragging) {
                        final int count = this.mViewPager.getAdapter().getCount();
                        final int width = this.getWidth();
                        final float n4 = width / 2.0f;
                        final float n5 = width / 6.0f;
                        if (this.mCurrentPage > 0 && motionEvent.getX() < n4 - n5) {
                            if (n2 != 3) {
                                this.mViewPager.setCurrentItem(this.mCurrentPage - 1);
                                return true;
                            }
                            break;
                        }
                        else if (this.mCurrentPage < count - 1 && motionEvent.getX() > n5 + n4) {
                            if (n2 != 3) {
                                this.mViewPager.setCurrentItem(this.mCurrentPage + 1);
                                return true;
                            }
                            break;
                        }
                    }
                    this.mIsDragging = false;
                    this.mActivePointerId = -1;
                    if (this.mViewPager.isFakeDragging()) {
                        this.mViewPager.endFakeDrag();
                        return true;
                    }
                    break;
                }
                case 5: {
                    final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                    this.mLastMotionX = MotionEventCompat.getX(motionEvent, actionIndex);
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    return true;
                }
                case 6: {
                    final int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                    if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.mActivePointerId) {
                        if (actionIndex2 == 0) {
                            n = 1;
                        }
                        this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, n);
                    }
                    this.mLastMotionX = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId));
                    return true;
                }
            }
        }
        return true;
    }
    
    public void setCentered(final boolean mCentered) {
        this.mCentered = mCentered;
        this.invalidate();
    }
    
    public void setCurrentItem(final int n) {
        if (this.mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.mViewPager.setCurrentItem(n);
        this.mCurrentPage = n;
        this.invalidate();
    }
    
    public void setGapWidth(final float mGapWidth) {
        this.mGapWidth = mGapWidth;
        this.invalidate();
    }
    
    public void setLineWidth(final float mLineWidth) {
        this.mLineWidth = mLineWidth;
        this.invalidate();
    }
    
    public void setOnPageChangeListener(final ViewPager$OnPageChangeListener mListener) {
        this.mListener = mListener;
    }
    
    public void setSelectedColor(final int color) {
        this.mPaintSelected.setColor(color);
        this.invalidate();
    }
    
    public void setStrokeWidth(final float n) {
        this.mPaintSelected.setStrokeWidth(n);
        this.mPaintUnselected.setStrokeWidth(n);
        this.invalidate();
    }
    
    public void setUnselectedColor(final int color) {
        this.mPaintUnselected.setColor(color);
        this.invalidate();
    }
    
    public void setViewPager(final ViewPager mViewPager) {
        if (this.mViewPager == mViewPager) {
            return;
        }
        if (this.mViewPager != null) {
            this.mViewPager.setOnPageChangeListener(null);
        }
        if (mViewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        (this.mViewPager = mViewPager).setOnPageChangeListener(this);
        this.invalidate();
    }
    
    public void setViewPager(final ViewPager viewPager, final int currentItem) {
        this.setViewPager(viewPager);
        this.setCurrentItem(currentItem);
    }
}
