// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import android.widget.ListAdapter;
import android.widget.Adapter;
import android.os.Handler;
import android.widget.AbsListView;
import android.os.Parcelable;
import android.widget.AdapterView;
import java.util.ArrayList;
import android.graphics.Canvas;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.view.View$MeasureSpec;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.View;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AbsListView$OnScrollListener;
import android.widget.GridView;

public class StickyGridHeadersGridView extends GridView implements AbsListView$OnScrollListener, AdapterView$OnItemClickListener, AdapterView$OnItemSelectedListener, AdapterView$OnItemLongClickListener
{
    private static final String ERROR_PLATFORM;
    private static final int MATCHED_STICKIED_HEADER = -2;
    private static final int NO_MATCHED_HEADER = -1;
    static final String TAG;
    protected static final int TOUCH_MODE_DONE_WAITING = 2;
    protected static final int TOUCH_MODE_DOWN = 0;
    protected static final int TOUCH_MODE_FINISHED_LONG_PRESS = -2;
    protected static final int TOUCH_MODE_REST = -1;
    protected static final int TOUCH_MODE_TAP = 1;
    protected StickyGridHeadersBaseAdapterWrapper mAdapter;
    private boolean mAreHeadersSticky;
    private boolean mClipToPaddingHasBeenSet;
    private final Rect mClippingRect;
    private boolean mClippingToPadding;
    private int mColumnWidth;
    private long mCurrentHeaderId;
    protected boolean mDataChanged;
    private final DataSetObserver mDataSetObserver;
    private int mHeaderBottomPosition;
    boolean mHeaderChildBeingPressed;
    private boolean mHeadersIgnorePadding;
    private int mHorizontalSpacing;
    private boolean mMaskStickyHeaderRegion;
    protected int mMotionHeaderPosition;
    private float mMotionY;
    private int mNumColumns;
    private boolean mNumColumnsSet;
    private int mNumMeasuredColumns;
    private OnHeaderClickListener mOnHeaderClickListener;
    private OnHeaderLongClickListener mOnHeaderLongClickListener;
    private AdapterView$OnItemClickListener mOnItemClickListener;
    private AdapterView$OnItemLongClickListener mOnItemLongClickListener;
    private AdapterView$OnItemSelectedListener mOnItemSelectedListener;
    public CheckForHeaderLongPress mPendingCheckForLongPress;
    public CheckForHeaderTap mPendingCheckForTap;
    private PerformHeaderClick mPerformHeaderClick;
    private AbsListView$OnScrollListener mScrollListener;
    private int mScrollState;
    private View mStickiedHeader;
    protected int mTouchMode;
    private Runnable mTouchModeReset;
    private final int mTouchSlop;
    private int mVerticalSpacing;
    
    static {
        ERROR_PLATFORM = "Error supporting platform " + Build$VERSION.SDK_INT + ".";
        TAG = StickyGridHeadersGridView.class.getSimpleName();
    }
    
    public StickyGridHeadersGridView(final Context context) {
        this(context, null);
    }
    
    public StickyGridHeadersGridView(final Context context, final AttributeSet set) {
        this(context, set, 16842865);
    }
    
    public StickyGridHeadersGridView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mAreHeadersSticky = true;
        this.mClippingRect = new Rect();
        this.mCurrentHeaderId = -1L;
        this.mDataSetObserver = new DataSetObserver() {
            public void onChanged() {
                StickyGridHeadersGridView.this.reset();
            }
            
            public void onInvalidated() {
                StickyGridHeadersGridView.this.reset();
            }
        };
        this.mMaskStickyHeaderRegion = true;
        this.mNumMeasuredColumns = 1;
        this.mScrollState = 0;
        this.mHeaderChildBeingPressed = false;
        super.setOnScrollListener((AbsListView$OnScrollListener)this);
        this.setVerticalFadingEdgeEnabled(false);
        if (!this.mNumColumnsSet) {
            this.mNumColumns = -1;
        }
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
    
    static /* synthetic */ int access$500(final StickyGridHeadersGridView stickyGridHeadersGridView) {
        return stickyGridHeadersGridView.getWindowAttachCount();
    }
    
    static /* synthetic */ int access$600(final StickyGridHeadersGridView stickyGridHeadersGridView) {
        return stickyGridHeadersGridView.getWindowAttachCount();
    }
    
    private int findMotionHeader(final float n) {
        if (this.mStickiedHeader == null || n > this.mHeaderBottomPosition) {
            for (int n2 = 0, i = this.getFirstVisiblePosition(); i <= this.getLastVisiblePosition(); i += this.mNumMeasuredColumns, n2 += this.mNumMeasuredColumns) {
                if (this.getItemIdAtPosition(i) == -1L) {
                    final View child = this.getChildAt(n2);
                    final int bottom = child.getBottom();
                    final int top = child.getTop();
                    if (n <= bottom) {
                        final int n3 = n2;
                        if (n >= top) {
                            return n3;
                        }
                    }
                }
            }
            return -1;
        }
        return -2;
    }
    
    private int getHeaderHeight() {
        if (this.mStickiedHeader != null) {
            return this.mStickiedHeader.getMeasuredHeight();
        }
        return 0;
    }
    
    private long headerViewPositionToId(final int n) {
        if (n == -2) {
            return this.mCurrentHeaderId;
        }
        return this.mAdapter.getHeaderId(this.getFirstVisiblePosition() + n);
    }
    
    private void measureHeader() {
        if (this.mStickiedHeader == null) {
            return;
        }
        int n;
        if (this.mHeadersIgnorePadding) {
            n = View$MeasureSpec.makeMeasureSpec(this.getWidth(), 1073741824);
        }
        else {
            n = View$MeasureSpec.makeMeasureSpec(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight(), 1073741824);
        }
        final ViewGroup$LayoutParams layoutParams = this.mStickiedHeader.getLayoutParams();
        int n2;
        if (layoutParams != null && layoutParams.height > 0) {
            n2 = View$MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        }
        else {
            n2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        }
        this.mStickiedHeader.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
        this.mStickiedHeader.measure(n, n2);
        if (this.mHeadersIgnorePadding) {
            this.mStickiedHeader.layout(this.getLeft(), 0, this.getRight(), this.mStickiedHeader.getMeasuredHeight());
            return;
        }
        this.mStickiedHeader.layout(this.getLeft() + this.getPaddingLeft(), 0, this.getRight() - this.getPaddingRight(), this.mStickiedHeader.getMeasuredHeight());
    }
    
    private void reset() {
        this.mHeaderBottomPosition = 0;
        this.swapStickiedHeader(null);
        this.mCurrentHeaderId = Long.MIN_VALUE;
    }
    
    private void scrollChanged(int n) {
        if (this.mAdapter != null && this.mAdapter.getCount() != 0 && this.mAreHeadersSticky && this.getChildAt(0) != null) {
            final int n2 = n;
            int n3;
            if ((n3 = n - this.mNumMeasuredColumns) < 0) {
                n3 = n;
            }
            int n4;
            if ((n4 = n + this.mNumMeasuredColumns) >= this.mAdapter.getCount()) {
                n4 = n;
            }
            long mCurrentHeaderId;
            if (this.mVerticalSpacing == 0) {
                mCurrentHeaderId = this.mAdapter.getHeaderId(n);
                n3 = n2;
            }
            else if (this.mVerticalSpacing < 0) {
                this.mAdapter.getHeaderId(n);
                if (this.getChildAt(this.mNumMeasuredColumns).getTop() <= 0) {
                    mCurrentHeaderId = this.mAdapter.getHeaderId(n4);
                    n3 = n4;
                }
                else {
                    mCurrentHeaderId = this.mAdapter.getHeaderId(n);
                    n3 = n2;
                }
            }
            else {
                final int top = this.getChildAt(0).getTop();
                if (top > 0 && top < this.mVerticalSpacing) {
                    mCurrentHeaderId = this.mAdapter.getHeaderId(n3);
                }
                else {
                    mCurrentHeaderId = this.mAdapter.getHeaderId(n);
                    n3 = n2;
                }
            }
            if (this.mCurrentHeaderId != mCurrentHeaderId) {
                this.swapStickiedHeader(this.mAdapter.getHeaderView(n3, this.mStickiedHeader, (ViewGroup)this));
                this.measureHeader();
                this.mCurrentHeaderId = mCurrentHeaderId;
            }
            final int childCount = this.getChildCount();
            if (childCount != 0) {
                View view = null;
                int n5 = 99999;
                int n6;
                View view2;
                for (int i = 0; i < childCount; i += this.mNumMeasuredColumns, view = view2, n5 = n6) {
                    final View child = super.getChildAt(i);
                    int top2;
                    if (this.mClippingToPadding) {
                        top2 = child.getTop() - this.getPaddingTop();
                    }
                    else {
                        top2 = child.getTop();
                    }
                    if (top2 < 0) {
                        n6 = n5;
                        view2 = view;
                    }
                    else {
                        view2 = view;
                        n6 = n5;
                        if (this.mAdapter.getItemId(this.getPositionForView(child)) == -1L) {
                            view2 = view;
                            if (top2 < (n6 = n5)) {
                                view2 = child;
                                n6 = top2;
                            }
                        }
                    }
                }
                final int headerHeight = this.getHeaderHeight();
                if (view != null) {
                    if (n == 0 && super.getChildAt(0).getTop() > 0 && !this.mClippingToPadding) {
                        this.mHeaderBottomPosition = 0;
                        return;
                    }
                    if (this.mClippingToPadding) {
                        this.mHeaderBottomPosition = Math.min(view.getTop(), this.getPaddingTop() + headerHeight);
                        if (this.mHeaderBottomPosition < this.getPaddingTop()) {
                            n = this.getPaddingTop() + headerHeight;
                        }
                        else {
                            n = this.mHeaderBottomPosition;
                        }
                        this.mHeaderBottomPosition = n;
                        return;
                    }
                    this.mHeaderBottomPosition = Math.min(view.getTop(), headerHeight);
                    if (this.mHeaderBottomPosition < 0) {
                        n = headerHeight;
                    }
                    else {
                        n = this.mHeaderBottomPosition;
                    }
                    this.mHeaderBottomPosition = n;
                }
                else {
                    this.mHeaderBottomPosition = headerHeight;
                    if (this.mClippingToPadding) {
                        this.mHeaderBottomPosition += this.getPaddingTop();
                    }
                }
            }
        }
    }
    
    private void swapStickiedHeader(final View mStickiedHeader) {
        this.detachHeader(this.mStickiedHeader);
        this.attachHeader(mStickiedHeader);
        this.mStickiedHeader = mStickiedHeader;
    }
    
    @SuppressLint({ "Recycle" })
    private MotionEvent transformEvent(final MotionEvent motionEvent, final int n) {
        if (n == -2) {
            return motionEvent;
        }
        return MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getX(), motionEvent.getY(), motionEvent.getPressure(), motionEvent.getSize(), motionEvent.getMetaState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags());
    }
    
    public boolean areHeadersSticky() {
        return this.mAreHeadersSticky;
    }
    
    void attachHeader(final View view) {
        if (view == null) {
            return;
        }
        try {
            final Field declaredField = View.class.getDeclaredField("mAttachInfo");
            declaredField.setAccessible(true);
            final Method declaredMethod = View.class.getDeclaredMethod("dispatchAttachedToWindow", Class.forName("android.view.View$AttachInfo"), Integer.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(view, declaredField.get(this), 8);
        }
        catch (NoSuchMethodException ex) {
            throw new RuntimePlatformSupportException(ex);
        }
        catch (ClassNotFoundException ex2) {
            throw new RuntimePlatformSupportException(ex2);
        }
        catch (IllegalArgumentException ex3) {
            throw new RuntimePlatformSupportException(ex3);
        }
        catch (IllegalAccessException ex4) {
            throw new RuntimePlatformSupportException(ex4);
        }
        catch (InvocationTargetException ex5) {
            throw new RuntimePlatformSupportException(ex5);
        }
        catch (NoSuchFieldException ex6) {
            throw new RuntimePlatformSupportException(ex6);
        }
    }
    
    void detachHeader(final View view) {
        if (view == null) {
            return;
        }
        try {
            final Method declaredMethod = View.class.getDeclaredMethod("dispatchDetachedFromWindow", (Class<?>[])new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(view, new Object[0]);
        }
        catch (NoSuchMethodException ex) {
            throw new RuntimePlatformSupportException(ex);
        }
        catch (IllegalArgumentException ex2) {
            throw new RuntimePlatformSupportException(ex2);
        }
        catch (IllegalAccessException ex3) {
            throw new RuntimePlatformSupportException(ex3);
        }
        catch (InvocationTargetException ex4) {
            throw new RuntimePlatformSupportException(ex4);
        }
    }
    
    protected void dispatchDraw(final Canvas canvas) {
        if (Build$VERSION.SDK_INT < 8) {
            this.scrollChanged(this.getFirstVisiblePosition());
        }
        boolean b;
        if (this.mStickiedHeader != null && this.mAreHeadersSticky && this.mStickiedHeader.getVisibility() == 0) {
            b = true;
        }
        else {
            b = false;
        }
        final int headerHeight = this.getHeaderHeight();
        final int n = this.mHeaderBottomPosition - headerHeight;
        if (b && this.mMaskStickyHeaderRegion) {
            if (this.mHeadersIgnorePadding) {
                this.mClippingRect.left = 0;
                this.mClippingRect.right = this.getWidth();
            }
            else {
                this.mClippingRect.left = this.getPaddingLeft();
                this.mClippingRect.right = this.getWidth() - this.getPaddingRight();
            }
            this.mClippingRect.top = this.mHeaderBottomPosition;
            this.mClippingRect.bottom = this.getHeight();
            canvas.save();
            canvas.clipRect(this.mClippingRect);
        }
        super.dispatchDraw(canvas);
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for (int n2 = 0, i = this.getFirstVisiblePosition(); i <= this.getLastVisiblePosition(); i += this.mNumMeasuredColumns, n2 += this.mNumMeasuredColumns) {
            if (this.getItemIdAtPosition(i) == -1L) {
                list.add(n2);
            }
        }
        for (int j = 0; j < list.size(); ++j) {
        Label_0331_Outer:
            while (true) {
                final View child = this.getChildAt((int)list.get(j));
                while (true) {
                    View view = null;
                Label_0346:
                    while (true) {
                        try {
                            view = (View)child.getTag();
                            if (((StickyGridHeadersBaseAdapterWrapper.HeaderFillerView)child).getHeaderId() == this.mCurrentHeaderId && child.getTop() < 0 && this.mAreHeadersSticky) {
                                final int n3 = 1;
                                if (view.getVisibility() != 0 || n3 != 0) {
                                    break;
                                }
                                break Label_0346;
                            }
                        }
                        catch (Exception ex) {
                            return;
                        }
                        final int n3 = 0;
                        continue Label_0331_Outer;
                    }
                    int n4;
                    if (this.mHeadersIgnorePadding) {
                        n4 = View$MeasureSpec.makeMeasureSpec(this.getWidth(), 1073741824);
                    }
                    else {
                        n4 = View$MeasureSpec.makeMeasureSpec(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight(), 1073741824);
                    }
                    final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
                    view.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
                    view.measure(n4, measureSpec);
                    if (this.mHeadersIgnorePadding) {
                        view.layout(this.getLeft(), 0, this.getRight(), child.getHeight());
                    }
                    else {
                        view.layout(this.getLeft() + this.getPaddingLeft(), 0, this.getRight() - this.getPaddingRight(), child.getHeight());
                    }
                    if (this.mHeadersIgnorePadding) {
                        this.mClippingRect.left = 0;
                        this.mClippingRect.right = this.getWidth();
                    }
                    else {
                        this.mClippingRect.left = this.getPaddingLeft();
                        this.mClippingRect.right = this.getWidth() - this.getPaddingRight();
                    }
                    this.mClippingRect.bottom = child.getBottom();
                    this.mClippingRect.top = child.getTop();
                    canvas.save();
                    canvas.clipRect(this.mClippingRect);
                    if (this.mHeadersIgnorePadding) {
                        canvas.translate(0.0f, (float)child.getTop());
                    }
                    else {
                        canvas.translate((float)this.getPaddingLeft(), (float)child.getTop());
                    }
                    view.draw(canvas);
                    canvas.restore();
                    continue;
                }
            }
        }
        if (b && this.mMaskStickyHeaderRegion) {
            canvas.restore();
        }
        else if (!b) {
            return;
        }
        int width;
        if (this.mHeadersIgnorePadding) {
            width = this.getWidth();
        }
        else {
            width = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        }
        if (this.mStickiedHeader.getWidth() != width) {
            int n5;
            if (this.mHeadersIgnorePadding) {
                n5 = View$MeasureSpec.makeMeasureSpec(this.getWidth(), 1073741824);
            }
            else {
                n5 = View$MeasureSpec.makeMeasureSpec(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight(), 1073741824);
            }
            final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
            this.mStickiedHeader.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
            this.mStickiedHeader.measure(n5, measureSpec2);
            if (this.mHeadersIgnorePadding) {
                this.mStickiedHeader.layout(this.getLeft(), 0, this.getRight(), this.mStickiedHeader.getHeight());
            }
            else {
                this.mStickiedHeader.layout(this.getLeft() + this.getPaddingLeft(), 0, this.getRight() - this.getPaddingRight(), this.mStickiedHeader.getHeight());
            }
        }
        if (this.mHeadersIgnorePadding) {
            this.mClippingRect.left = 0;
            this.mClippingRect.right = this.getWidth();
        }
        else {
            this.mClippingRect.left = this.getPaddingLeft();
            this.mClippingRect.right = this.getWidth() - this.getPaddingRight();
        }
        this.mClippingRect.bottom = n + headerHeight;
        if (this.mClippingToPadding) {
            this.mClippingRect.top = this.getPaddingTop();
        }
        else {
            this.mClippingRect.top = 0;
        }
        canvas.save();
        canvas.clipRect(this.mClippingRect);
        if (this.mHeadersIgnorePadding) {
            canvas.translate(0.0f, (float)n);
        }
        else {
            canvas.translate((float)this.getPaddingLeft(), (float)n);
        }
        if (this.mHeaderBottomPosition != headerHeight) {
            canvas.saveLayerAlpha(0.0f, 0.0f, (float)canvas.getWidth(), (float)canvas.getHeight(), this.mHeaderBottomPosition * 255 / headerHeight, 31);
        }
        this.mStickiedHeader.draw(canvas);
        if (this.mHeaderBottomPosition != headerHeight) {
            canvas.restore();
        }
        canvas.restore();
    }
    
    public View getHeaderAt(final int n) {
        if (n == -2) {
            return this.mStickiedHeader;
        }
        try {
            return (View)this.getChildAt(n).getTag();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public View getStickiedHeader() {
        return this.mStickiedHeader;
    }
    
    public boolean getStickyHeaderIsTranscluent() {
        return !this.mMaskStickyHeaderRegion;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        this.mOnItemClickListener.onItemClick((AdapterView)adapterView, view, this.mAdapter.translatePosition(n).mPosition, n2);
    }
    
    public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        return this.mOnItemLongClickListener.onItemLongClick((AdapterView)adapterView, view, this.mAdapter.translatePosition(n).mPosition, n2);
    }
    
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        this.mOnItemSelectedListener.onItemSelected((AdapterView)adapterView, view, this.mAdapter.translatePosition(n).mPosition, n2);
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (this.mNumColumns == -1) {
            int mNumMeasuredColumns;
            if (this.mColumnWidth > 0) {
                final int max = Math.max(View$MeasureSpec.getSize(n) - this.getPaddingLeft() - this.getPaddingRight(), 0);
                int n3 = max / this.mColumnWidth;
                if (n3 > 0) {
                    while ((mNumMeasuredColumns = n3) != 1) {
                        mNumMeasuredColumns = n3;
                        if (this.mColumnWidth * n3 + (n3 - 1) * this.mHorizontalSpacing <= max) {
                            break;
                        }
                        --n3;
                    }
                }
                else {
                    mNumMeasuredColumns = 1;
                }
            }
            else {
                mNumMeasuredColumns = 2;
            }
            this.mNumMeasuredColumns = mNumMeasuredColumns;
        }
        else {
            this.mNumMeasuredColumns = this.mNumColumns;
        }
        if (this.mAdapter != null) {
            this.mAdapter.setNumColumns(this.mNumMeasuredColumns);
        }
        this.measureHeader();
        super.onMeasure(n, n2);
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
        this.mOnItemSelectedListener.onNothingSelected((AdapterView)adapterView);
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mAreHeadersSticky = savedState.areHeadersSticky;
        this.requestLayout();
    }
    
    public Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.areHeadersSticky = this.mAreHeadersSticky;
        return (Parcelable)savedState;
    }
    
    public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
        if (this.mScrollListener != null) {
            this.mScrollListener.onScroll(absListView, n, n2, n3);
        }
        if (Build$VERSION.SDK_INT >= 8) {
            this.scrollChanged(n);
        }
    }
    
    public void onScrollStateChanged(final AbsListView absListView, final int mScrollState) {
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrollStateChanged(absListView, mScrollState);
        }
        this.mScrollState = mScrollState;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        final boolean mHeaderChildBeingPressed = this.mHeaderChildBeingPressed;
        if (this.mHeaderChildBeingPressed) {
            final View header = this.getHeaderAt(this.mMotionHeaderPosition);
            View child;
            if (this.mMotionHeaderPosition == -2) {
                child = header;
            }
            else {
                child = this.getChildAt(this.mMotionHeaderPosition);
            }
            if (action == 1 || action == 3) {
                this.mHeaderChildBeingPressed = false;
            }
            if (header != null) {
                header.dispatchTouchEvent(this.transformEvent(motionEvent, this.mMotionHeaderPosition));
                header.invalidate();
                header.postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        StickyGridHeadersGridView.this.invalidate(0, child.getTop(), StickyGridHeadersGridView.this.getWidth(), child.getTop() + child.getHeight());
                    }
                }, (long)ViewConfiguration.getPressedStateDuration());
                this.invalidate(0, child.getTop(), this.getWidth(), child.getTop() + child.getHeight());
            }
        }
        switch (action & 0xFF) {
            case 0: {
                if (this.mPendingCheckForTap == null) {
                    this.mPendingCheckForTap = new CheckForHeaderTap();
                }
                this.postDelayed((Runnable)this.mPendingCheckForTap, (long)ViewConfiguration.getTapTimeout());
                final int n = (int)motionEvent.getY();
                this.mMotionY = n;
                this.mMotionHeaderPosition = this.findMotionHeader(n);
                if (this.mMotionHeaderPosition != -1 && this.mScrollState != 2) {
                    final View header2 = this.getHeaderAt(this.mMotionHeaderPosition);
                    if (header2 != null) {
                        if (header2.dispatchTouchEvent(this.transformEvent(motionEvent, this.mMotionHeaderPosition))) {
                            header2.setPressed(this.mHeaderChildBeingPressed = true);
                        }
                        header2.invalidate();
                        View child2 = header2;
                        if (this.mMotionHeaderPosition != -2) {
                            child2 = this.getChildAt(this.mMotionHeaderPosition);
                        }
                        this.invalidate(0, child2.getTop(), this.getWidth(), child2.getTop() + child2.getHeight());
                    }
                    this.mTouchMode = 0;
                    return true;
                }
                break;
            }
            case 2: {
                if (this.mMotionHeaderPosition != -1 && Math.abs(motionEvent.getY() - this.mMotionY) > this.mTouchSlop) {
                    this.mTouchMode = -1;
                    final View header3 = this.getHeaderAt(this.mMotionHeaderPosition);
                    if (header3 != null) {
                        header3.setPressed(false);
                        header3.invalidate();
                    }
                    final Handler handler = this.getHandler();
                    if (handler != null) {
                        handler.removeCallbacks((Runnable)this.mPendingCheckForLongPress);
                    }
                    this.mMotionHeaderPosition = -1;
                    break;
                }
                break;
            }
            case 1: {
                if (this.mTouchMode == -2) {
                    this.mTouchMode = -1;
                    return true;
                }
                if (this.mTouchMode != -1 && this.mMotionHeaderPosition != -1) {
                    final View header4 = this.getHeaderAt(this.mMotionHeaderPosition);
                    if (!mHeaderChildBeingPressed && header4 != null) {
                        if (this.mTouchMode != 0) {
                            header4.setPressed(false);
                        }
                        if (this.mPerformHeaderClick == null) {
                            this.mPerformHeaderClick = new PerformHeaderClick();
                        }
                        final PerformHeaderClick mPerformHeaderClick = this.mPerformHeaderClick;
                        mPerformHeaderClick.mClickMotionPosition = this.mMotionHeaderPosition;
                        ((WindowRunnable)mPerformHeaderClick).rememberWindowAttachCount();
                        if (this.mTouchMode == 0 || this.mTouchMode == 1) {
                            final Handler handler2 = this.getHandler();
                            if (handler2 != null) {
                                Runnable runnable;
                                if (this.mTouchMode == 0) {
                                    runnable = this.mPendingCheckForTap;
                                }
                                else {
                                    runnable = this.mPendingCheckForLongPress;
                                }
                                handler2.removeCallbacks(runnable);
                            }
                            if (!this.mDataChanged) {
                                this.mTouchMode = 1;
                                header4.setPressed(true);
                                this.setPressed(true);
                                if (this.mTouchModeReset != null) {
                                    this.removeCallbacks(this.mTouchModeReset);
                                }
                                this.postDelayed(this.mTouchModeReset = new Runnable() {
                                    @Override
                                    public void run() {
                                        StickyGridHeadersGridView.this.mMotionHeaderPosition = -1;
                                        StickyGridHeadersGridView.this.mTouchModeReset = null;
                                        StickyGridHeadersGridView.this.mTouchMode = -1;
                                        header4.setPressed(false);
                                        StickyGridHeadersGridView.this.setPressed(false);
                                        header4.invalidate();
                                        StickyGridHeadersGridView.this.invalidate(0, header4.getTop(), StickyGridHeadersGridView.this.getWidth(), header4.getHeight());
                                        if (!StickyGridHeadersGridView.this.mDataChanged) {
                                            mPerformHeaderClick.run();
                                        }
                                    }
                                }, (long)ViewConfiguration.getPressedStateDuration());
                            }
                            else {
                                this.mTouchMode = -1;
                            }
                        }
                        else if (!this.mDataChanged) {
                            mPerformHeaderClick.run();
                        }
                    }
                    this.mTouchMode = -1;
                    return true;
                }
                break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public boolean performHeaderClick(final View view, final long n) {
        if (this.mOnHeaderClickListener != null) {
            this.playSoundEffect(0);
            if (view != null) {
                view.sendAccessibilityEvent(1);
            }
            this.mOnHeaderClickListener.onHeaderClick((AdapterView<?>)this, view, n);
            return true;
        }
        return false;
    }
    
    public boolean performHeaderLongPress(final View view, final long n) {
        boolean onHeaderLongClick = false;
        if (this.mOnHeaderLongClickListener != null) {
            onHeaderLongClick = this.mOnHeaderLongClickListener.onHeaderLongClick((AdapterView<?>)this, view, n);
        }
        if (onHeaderLongClick) {
            if (view != null) {
                view.sendAccessibilityEvent(2);
            }
            this.performHapticFeedback(0);
        }
        return onHeaderLongClick;
    }
    
    public void setAdapter(final ListAdapter listAdapter) {
        if (this.mAdapter != null && this.mDataSetObserver != null) {
            this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
        }
        if (!this.mClipToPaddingHasBeenSet) {
            this.mClippingToPadding = true;
        }
        StickyGridHeadersBaseAdapter stickyGridHeadersBaseAdapter;
        if (listAdapter instanceof StickyGridHeadersBaseAdapter) {
            stickyGridHeadersBaseAdapter = (StickyGridHeadersBaseAdapter)listAdapter;
        }
        else if (listAdapter instanceof StickyGridHeadersSimpleAdapter) {
            stickyGridHeadersBaseAdapter = new StickyGridHeadersSimpleAdapterWrapper((StickyGridHeadersSimpleAdapter)listAdapter);
        }
        else {
            stickyGridHeadersBaseAdapter = new StickyGridHeadersListAdapterWrapper(listAdapter);
        }
        (this.mAdapter = new StickyGridHeadersBaseAdapterWrapper(this.getContext(), this, stickyGridHeadersBaseAdapter)).registerDataSetObserver(this.mDataSetObserver);
        this.reset();
        super.setAdapter((ListAdapter)this.mAdapter);
    }
    
    public void setAreHeadersSticky(final boolean mAreHeadersSticky) {
        if (mAreHeadersSticky != this.mAreHeadersSticky) {
            this.mAreHeadersSticky = mAreHeadersSticky;
            this.requestLayout();
        }
    }
    
    public void setClipToPadding(final boolean b) {
        super.setClipToPadding(b);
        this.mClippingToPadding = b;
        this.mClipToPaddingHasBeenSet = true;
    }
    
    public void setColumnWidth(final int n) {
        super.setColumnWidth(n);
        this.mColumnWidth = n;
    }
    
    public void setHeadersIgnorePadding(final boolean mHeadersIgnorePadding) {
        this.mHeadersIgnorePadding = mHeadersIgnorePadding;
    }
    
    public void setHorizontalSpacing(final int n) {
        super.setHorizontalSpacing(n);
        this.mHorizontalSpacing = n;
    }
    
    public void setNumColumns(final int numColumns) {
        super.setNumColumns(numColumns);
        this.mNumColumnsSet = true;
        this.mNumColumns = numColumns;
        if (numColumns != -1 && this.mAdapter != null) {
            this.mAdapter.setNumColumns(numColumns);
        }
    }
    
    public void setOnHeaderClickListener(final OnHeaderClickListener mOnHeaderClickListener) {
        this.mOnHeaderClickListener = mOnHeaderClickListener;
    }
    
    public void setOnHeaderLongClickListener(final OnHeaderLongClickListener mOnHeaderLongClickListener) {
        if (!this.isLongClickable()) {
            this.setLongClickable(true);
        }
        this.mOnHeaderLongClickListener = mOnHeaderLongClickListener;
    }
    
    public void setOnItemClickListener(final AdapterView$OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
        super.setOnItemClickListener((AdapterView$OnItemClickListener)this);
    }
    
    public void setOnItemLongClickListener(final AdapterView$OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
        super.setOnItemLongClickListener((AdapterView$OnItemLongClickListener)this);
    }
    
    public void setOnItemSelectedListener(final AdapterView$OnItemSelectedListener mOnItemSelectedListener) {
        this.mOnItemSelectedListener = mOnItemSelectedListener;
        super.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)this);
    }
    
    public void setOnScrollListener(final AbsListView$OnScrollListener mScrollListener) {
        this.mScrollListener = mScrollListener;
    }
    
    public void setStickyHeaderIsTranscluent(final boolean b) {
        this.mMaskStickyHeaderRegion = !b;
    }
    
    public void setVerticalSpacing(final int n) {
        super.setVerticalSpacing(n);
        this.mVerticalSpacing = n;
    }
    
    private class CheckForHeaderLongPress extends WindowRunnable implements Runnable
    {
        @Override
        public void run() {
            final View header = StickyGridHeadersGridView.this.getHeaderAt(StickyGridHeadersGridView.this.mMotionHeaderPosition);
            if (header != null) {
                final long access$400 = StickyGridHeadersGridView.this.headerViewPositionToId(StickyGridHeadersGridView.this.mMotionHeaderPosition);
                boolean performHeaderLongPress = false;
                if (((WindowRunnable)this).sameWindow()) {
                    performHeaderLongPress = performHeaderLongPress;
                    if (!StickyGridHeadersGridView.this.mDataChanged) {
                        performHeaderLongPress = StickyGridHeadersGridView.this.performHeaderLongPress(header, access$400);
                    }
                }
                if (!performHeaderLongPress) {
                    StickyGridHeadersGridView.this.mTouchMode = 2;
                    return;
                }
                StickyGridHeadersGridView.this.mTouchMode = -2;
                StickyGridHeadersGridView.this.setPressed(false);
                header.setPressed(false);
            }
        }
    }
    
    final class CheckForHeaderTap implements Runnable
    {
        @Override
        public void run() {
            if (StickyGridHeadersGridView.this.mTouchMode == 0) {
                StickyGridHeadersGridView.this.mTouchMode = 1;
                final View header = StickyGridHeadersGridView.this.getHeaderAt(StickyGridHeadersGridView.this.mMotionHeaderPosition);
                if (header != null && !StickyGridHeadersGridView.this.mHeaderChildBeingPressed) {
                    if (StickyGridHeadersGridView.this.mDataChanged) {
                        StickyGridHeadersGridView.this.mTouchMode = 2;
                        return;
                    }
                    header.setPressed(true);
                    StickyGridHeadersGridView.this.setPressed(true);
                    StickyGridHeadersGridView.this.refreshDrawableState();
                    final int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    if (!StickyGridHeadersGridView.this.isLongClickable()) {
                        StickyGridHeadersGridView.this.mTouchMode = 2;
                        return;
                    }
                    if (StickyGridHeadersGridView.this.mPendingCheckForLongPress == null) {
                        StickyGridHeadersGridView.this.mPendingCheckForLongPress = new CheckForHeaderLongPress();
                    }
                    ((WindowRunnable)StickyGridHeadersGridView.this.mPendingCheckForLongPress).rememberWindowAttachCount();
                    StickyGridHeadersGridView.this.postDelayed((Runnable)StickyGridHeadersGridView.this.mPendingCheckForLongPress, (long)longPressTimeout);
                }
            }
        }
    }
    
    public interface OnHeaderClickListener
    {
        void onHeaderClick(final AdapterView<?> p0, final View p1, final long p2);
    }
    
    public interface OnHeaderLongClickListener
    {
        boolean onHeaderLongClick(final AdapterView<?> p0, final View p1, final long p2);
    }
    
    private class PerformHeaderClick extends WindowRunnable implements Runnable
    {
        int mClickMotionPosition;
        
        @Override
        public void run() {
            if (!StickyGridHeadersGridView.this.mDataChanged && StickyGridHeadersGridView.this.mAdapter != null && StickyGridHeadersGridView.this.mAdapter.getCount() > 0 && this.mClickMotionPosition != -1 && this.mClickMotionPosition < StickyGridHeadersGridView.this.mAdapter.getCount() && ((WindowRunnable)this).sameWindow()) {
                final View header = StickyGridHeadersGridView.this.getHeaderAt(this.mClickMotionPosition);
                if (header != null) {
                    StickyGridHeadersGridView.this.performHeaderClick(header, StickyGridHeadersGridView.this.headerViewPositionToId(this.mClickMotionPosition));
                }
            }
        }
    }
    
    class RuntimePlatformSupportException extends RuntimeException
    {
        private static final long serialVersionUID = -6512098808936536538L;
        
        public RuntimePlatformSupportException(final Exception ex) {
            super(StickyGridHeadersGridView.ERROR_PLATFORM, ex);
        }
    }
    
    static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        boolean areHeadersSticky;
        
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
            this.areHeadersSticky = (parcel.readByte() != 0);
        }
        
        public SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public String toString() {
            return "StickyGridHeadersGridView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " areHeadersSticky=" + this.areHeadersSticky + "}";
        }
        
        public void writeToParcel(final Parcel parcel, int n) {
            super.writeToParcel(parcel, n);
            if (this.areHeadersSticky) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeByte((byte)n);
        }
    }
    
    private class WindowRunnable
    {
        private int mOriginalAttachCount;
        
        public void rememberWindowAttachCount() {
            this.mOriginalAttachCount = StickyGridHeadersGridView.access$500(StickyGridHeadersGridView.this);
        }
        
        public boolean sameWindow() {
            return StickyGridHeadersGridView.this.hasWindowFocus() && StickyGridHeadersGridView.access$600(StickyGridHeadersGridView.this) == this.mOriginalAttachCount;
        }
    }
}
