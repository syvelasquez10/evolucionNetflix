// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.os.Parcelable;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import android.content.res.Resources;
import android.support.v4.view.ViewConfigurationCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Paint;
import com.viewpagerindicator.android.osp.ViewPager;
import android.view.View;

public class UnderlinePageIndicator extends View implements PageIndicator
{
    private static final int FADE_FRAME_MS = 30;
    private static final int INVALID_POINTER = -1;
    private int mActivePointerId;
    private int mCurrentPage;
    private int mFadeBy;
    private int mFadeDelay;
    private int mFadeLength;
    private final Runnable mFadeRunnable;
    private boolean mFades;
    private boolean mIsDragging;
    private float mLastMotionX;
    private OnPageChangeListener mListener;
    private final Paint mPaint;
    private float mPositionOffset;
    private int mScrollState;
    private int mTouchSlop;
    private ViewPager mViewPager;
    
    public UnderlinePageIndicator(final Context context) {
        this(context, null);
    }
    
    public UnderlinePageIndicator(final Context context, final AttributeSet set) {
        this(context, set, R.attr.vpiUnderlinePageIndicatorStyle);
    }
    
    public UnderlinePageIndicator(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mPaint = new Paint(1);
        this.mLastMotionX = -1.0f;
        this.mActivePointerId = -1;
        this.mFadeRunnable = new Runnable() {
            @Override
            public void run() {
                if (UnderlinePageIndicator.this.mFades) {
                    final int max = Math.max(UnderlinePageIndicator.this.mPaint.getAlpha() - UnderlinePageIndicator.this.mFadeBy, 0);
                    UnderlinePageIndicator.this.mPaint.setAlpha(max);
                    UnderlinePageIndicator.this.invalidate();
                    if (max > 0) {
                        UnderlinePageIndicator.this.postDelayed((Runnable)this, 30L);
                    }
                }
            }
        };
        if (this.isInEditMode()) {
            return;
        }
        final Resources resources = this.getResources();
        final boolean boolean1 = resources.getBoolean(R.bool.default_underline_indicator_fades);
        final int integer = resources.getInteger(R.integer.default_underline_indicator_fade_delay);
        final int integer2 = resources.getInteger(R.integer.default_underline_indicator_fade_length);
        final int color = resources.getColor(R.color.default_underline_indicator_selected_color);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.UnderlinePageIndicator, n, 0);
        this.setFades(obtainStyledAttributes.getBoolean(2, boolean1));
        this.setSelectedColor(obtainStyledAttributes.getColor(1, color));
        this.setFadeDelay(obtainStyledAttributes.getInteger(3, integer));
        this.setFadeLength(obtainStyledAttributes.getInteger(4, integer2));
        final Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable != null) {
            this.setBackgroundDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
    }
    
    public int getFadeDelay() {
        return this.mFadeDelay;
    }
    
    public int getFadeLength() {
        return this.mFadeLength;
    }
    
    public boolean getFades() {
        return this.mFades;
    }
    
    public int getSelectedColor() {
        return this.mPaint.getColor();
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
                final int paddingLeft = this.getPaddingLeft();
                final float n = (this.getWidth() - paddingLeft - this.getPaddingRight()) / (1.0f * count);
                final float n2 = paddingLeft + (this.mCurrentPage + this.mPositionOffset) * n;
                canvas.drawRect(n2, (float)this.getPaddingTop(), n2 + n, (float)(this.getHeight() - this.getPaddingBottom()), this.mPaint);
            }
        }
    }
    
    public void onPageScrollStateChanged(final int mScrollState) {
        this.mScrollState = mScrollState;
        if (this.mListener != null) {
            this.mListener.onPageScrollStateChanged(mScrollState);
        }
    }
    
    public void onPageScrolled(final int mCurrentPage, final float mPositionOffset, final int n) {
        this.mCurrentPage = mCurrentPage;
        this.mPositionOffset = mPositionOffset;
        if (this.mFades) {
            if (n > 0) {
                this.removeCallbacks(this.mFadeRunnable);
                this.mPaint.setAlpha(255);
            }
            else if (this.mScrollState != 1) {
                this.postDelayed(this.mFadeRunnable, (long)this.mFadeDelay);
            }
        }
        this.invalidate();
        if (this.mListener != null) {
            this.mListener.onPageScrolled(mCurrentPage, mPositionOffset, n);
        }
    }
    
    public void onPageSelected(final int mCurrentPage) {
        if (this.mScrollState == 0) {
            this.mCurrentPage = mCurrentPage;
            this.mPositionOffset = 0.0f;
            this.invalidate();
            this.mFadeRunnable.run();
        }
        if (this.mListener != null) {
            this.mListener.onPageSelected(mCurrentPage);
        }
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mCurrentPage = savedState.currentPage;
        this.requestLayout();
    }
    
    public Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.currentPage = this.mCurrentPage;
        return (Parcelable)savedState;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        if (this.mViewPager == null || this.mViewPager.getAdapter().getCount() == 0) {
            return false;
        }
        final int n = motionEvent.getAction() & 0xFF;
        switch (n) {
            case 0: {
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mLastMotionX = motionEvent.getX();
                break;
            }
            case 2: {
                final float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId));
                final float n2 = x - this.mLastMotionX;
                if (!this.mIsDragging && Math.abs(n2) > this.mTouchSlop) {
                    this.mIsDragging = true;
                }
                if (!this.mIsDragging) {
                    break;
                }
                this.mLastMotionX = x;
                if (this.mViewPager.isFakeDragging() || this.mViewPager.beginFakeDrag()) {
                    this.mViewPager.fakeDragBy(n2);
                    break;
                }
                break;
            }
            case 1:
            case 3: {
                if (!this.mIsDragging) {
                    final int count = this.mViewPager.getAdapter().getCount();
                    final int width = this.getWidth();
                    final float n3 = width / 2.0f;
                    final float n4 = width / 6.0f;
                    if (this.mCurrentPage > 0 && motionEvent.getX() < n3 - n4) {
                        if (n != 3) {
                            this.mViewPager.setCurrentItem(this.mCurrentPage - 1);
                        }
                        return true;
                    }
                    if (this.mCurrentPage < count - 1 && motionEvent.getX() > n3 + n4) {
                        if (n != 3) {
                            this.mViewPager.setCurrentItem(this.mCurrentPage + 1);
                        }
                        return true;
                    }
                }
                this.mIsDragging = false;
                this.mActivePointerId = -1;
                if (this.mViewPager.isFakeDragging()) {
                    this.mViewPager.endFakeDrag();
                    break;
                }
                break;
            }
            case 5: {
                final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.mLastMotionX = MotionEventCompat.getX(motionEvent, actionIndex);
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                break;
            }
            case 6: {
                final int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.mActivePointerId) {
                    int n5;
                    if (actionIndex2 == 0) {
                        n5 = 1;
                    }
                    else {
                        n5 = 0;
                    }
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, n5);
                }
                this.mLastMotionX = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId));
                break;
            }
        }
        return true;
    }
    
    public void setCurrentItem(final int n) {
        if (this.mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.mViewPager.setCurrentItem(n);
        this.mCurrentPage = n;
        this.invalidate();
    }
    
    public void setFadeDelay(final int mFadeDelay) {
        this.mFadeDelay = mFadeDelay;
    }
    
    public void setFadeLength(final int mFadeLength) {
        this.mFadeLength = mFadeLength;
        this.mFadeBy = 255 / (this.mFadeLength / 30);
    }
    
    public void setFades(final boolean mFades) {
        if (mFades != this.mFades) {
            this.mFades = mFades;
            if (!mFades) {
                this.removeCallbacks(this.mFadeRunnable);
                this.mPaint.setAlpha(255);
                this.invalidate();
                return;
            }
            this.post(this.mFadeRunnable);
        }
    }
    
    public void setOnPageChangeListener(final OnPageChangeListener mListener) {
        this.mListener = mListener;
    }
    
    public void setSelectedColor(final int color) {
        this.mPaint.setColor(color);
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
        (this.mViewPager = mViewPager).setOnPageChangeListener((ViewPager.OnPageChangeListener)this);
        this.invalidate();
        this.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (UnderlinePageIndicator.this.mFades) {
                    UnderlinePageIndicator.this.post(UnderlinePageIndicator.this.mFadeRunnable);
                }
            }
        });
    }
    
    public void setViewPager(final ViewPager viewPager, final int currentItem) {
        this.setViewPager(viewPager);
        this.setCurrentItem(currentItem);
    }
    
    static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        int currentPage;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        private SavedState(final Parcel parcel) {
            super(parcel);
            this.currentPage = parcel.readInt();
        }
        
        public SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeInt(this.currentPage);
        }
    }
}
