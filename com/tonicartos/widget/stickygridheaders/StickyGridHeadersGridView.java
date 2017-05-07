// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

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
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.view.View$MeasureSpec;
import android.view.MotionEvent$PointerCoords;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.View;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AbsListView$OnScrollListener;
import android.widget.GridView;

public class StickyGridHeadersGridView extends GridView implements AbsListView$OnScrollListener, AdapterView$OnItemClickListener, AdapterView$OnItemLongClickListener, AdapterView$OnItemSelectedListener
{
    private static final String ERROR_PLATFORM;
    static final String TAG;
    protected StickyGridHeadersBaseAdapterWrapper mAdapter;
    private boolean mAreHeadersSticky;
    private boolean mClipToPaddingHasBeenSet;
    private final Rect mClippingRect;
    private boolean mClippingToPadding;
    private int mColumnWidth;
    private long mCurrentHeaderId;
    protected boolean mDataChanged;
    private DataSetObserver mDataSetObserver;
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
    private StickyGridHeadersGridView$OnHeaderClickListener mOnHeaderClickListener;
    private StickyGridHeadersGridView$OnHeaderLongClickListener mOnHeaderLongClickListener;
    private AdapterView$OnItemClickListener mOnItemClickListener;
    private AdapterView$OnItemLongClickListener mOnItemLongClickListener;
    private AdapterView$OnItemSelectedListener mOnItemSelectedListener;
    public StickyGridHeadersGridView$CheckForHeaderLongPress mPendingCheckForLongPress;
    public StickyGridHeadersGridView$CheckForHeaderTap mPendingCheckForTap;
    private StickyGridHeadersGridView$PerformHeaderClick mPerformHeaderClick;
    private AbsListView$OnScrollListener mScrollListener;
    private int mScrollState;
    private View mStickiedHeader;
    protected int mTouchMode;
    private Runnable mTouchModeReset;
    private int mTouchSlop;
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
        this.mDataSetObserver = new StickyGridHeadersGridView$1(this);
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
    
    private static MotionEvent$PointerCoords[] getPointerCoords(final MotionEvent motionEvent) {
        final int pointerCount = motionEvent.getPointerCount();
        final MotionEvent$PointerCoords[] array = new MotionEvent$PointerCoords[pointerCount];
        for (int i = 0; i < pointerCount; ++i) {
            motionEvent.getPointerCoords(i, array[i] = new MotionEvent$PointerCoords());
        }
        return array;
    }
    
    private static int[] getPointerIds(final MotionEvent motionEvent) {
        final int pointerCount = motionEvent.getPointerCount();
        final int[] array = new int[pointerCount];
        for (int i = 0; i < pointerCount; ++i) {
            array[i] = motionEvent.getPointerId(i);
        }
        return array;
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
            int n2;
            if ((n2 = n - this.mNumMeasuredColumns) < 0) {
                n2 = n;
            }
            int n3;
            if ((n3 = this.mNumMeasuredColumns + n) >= this.mAdapter.getCount()) {
                n3 = n;
            }
            long mCurrentHeaderId;
            if (this.mVerticalSpacing == 0) {
                mCurrentHeaderId = this.mAdapter.getHeaderId(n);
                n2 = n;
            }
            else if (this.mVerticalSpacing < 0) {
                this.mAdapter.getHeaderId(n);
                if (this.getChildAt(this.mNumMeasuredColumns).getTop() <= 0) {
                    mCurrentHeaderId = this.mAdapter.getHeaderId(n3);
                    n2 = n3;
                }
                else {
                    mCurrentHeaderId = this.mAdapter.getHeaderId(n);
                    n2 = n;
                }
            }
            else {
                final int top = this.getChildAt(0).getTop();
                if (top > 0 && top < this.mVerticalSpacing) {
                    mCurrentHeaderId = this.mAdapter.getHeaderId(n2);
                }
                else {
                    mCurrentHeaderId = this.mAdapter.getHeaderId(n);
                    n2 = n;
                }
            }
            if (this.mCurrentHeaderId != mCurrentHeaderId) {
                this.swapStickiedHeader(this.mAdapter.getHeaderView(n2, this.mStickiedHeader, (ViewGroup)this));
                this.measureHeader();
                this.mCurrentHeaderId = mCurrentHeaderId;
            }
            final int childCount = this.getChildCount();
            if (childCount != 0) {
                View view = null;
                int n4 = 99999;
                for (int i = 0; i < childCount; i += this.mNumMeasuredColumns) {
                    final View child = super.getChildAt(i);
                    int top2;
                    if (this.mClippingToPadding) {
                        top2 = child.getTop() - this.getPaddingTop();
                    }
                    else {
                        top2 = child.getTop();
                    }
                    if (top2 >= 0) {
                        if (this.mAdapter.getItemId(this.getPositionForView(child)) == -1L && top2 < n4) {
                            view = child;
                            n4 = top2;
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
                            n = headerHeight + this.getPaddingTop();
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
    
    private MotionEvent transformEvent(final MotionEvent motionEvent, int i) {
        if (i == -2) {
            return motionEvent;
        }
        final long downTime = motionEvent.getDownTime();
        final long eventTime = motionEvent.getEventTime();
        final int action = motionEvent.getAction();
        final int pointerCount = motionEvent.getPointerCount();
        final int[] pointerIds = getPointerIds(motionEvent);
        final MotionEvent$PointerCoords[] pointerCoords = getPointerCoords(motionEvent);
        final int metaState = motionEvent.getMetaState();
        final float xPrecision = motionEvent.getXPrecision();
        final float yPrecision = motionEvent.getYPrecision();
        final int deviceId = motionEvent.getDeviceId();
        final int edgeFlags = motionEvent.getEdgeFlags();
        final int source = motionEvent.getSource();
        final int flags = motionEvent.getFlags();
        final View child = this.getChildAt(i);
        MotionEvent$PointerCoords motionEvent$PointerCoords;
        for (i = 0; i < pointerCount; ++i) {
            motionEvent$PointerCoords = pointerCoords[i];
            motionEvent$PointerCoords.y -= child.getTop();
        }
        return MotionEvent.obtain(downTime, eventTime, action, pointerCount, pointerIds, pointerCoords, metaState, xPrecision, yPrecision, deviceId, edgeFlags, source, flags);
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
            throw new StickyGridHeadersGridView$RuntimePlatformSupportException(this, ex);
        }
        catch (ClassNotFoundException ex2) {
            throw new StickyGridHeadersGridView$RuntimePlatformSupportException(this, ex2);
        }
        catch (IllegalArgumentException ex3) {
            throw new StickyGridHeadersGridView$RuntimePlatformSupportException(this, ex3);
        }
        catch (IllegalAccessException ex4) {
            throw new StickyGridHeadersGridView$RuntimePlatformSupportException(this, ex4);
        }
        catch (InvocationTargetException ex5) {
            throw new StickyGridHeadersGridView$RuntimePlatformSupportException(this, ex5);
        }
        catch (NoSuchFieldException ex6) {
            throw new StickyGridHeadersGridView$RuntimePlatformSupportException(this, ex6);
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
            throw new StickyGridHeadersGridView$RuntimePlatformSupportException(this, ex);
        }
        catch (IllegalArgumentException ex2) {
            throw new StickyGridHeadersGridView$RuntimePlatformSupportException(this, ex2);
        }
        catch (IllegalAccessException ex3) {
            throw new StickyGridHeadersGridView$RuntimePlatformSupportException(this, ex3);
        }
        catch (InvocationTargetException ex4) {
            throw new StickyGridHeadersGridView$RuntimePlatformSupportException(this, ex4);
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
        int n3 = 0;
        View child;
        View view;
        boolean b2;
        int n4;
        int measureSpec;
        int width;
        int n5;
        int measureSpec2;
        Label_0645_Outer:Block_25_Outer:Label_0820_Outer:
        while (true) {
            Label_0618: {
                if (n3 >= list.size()) {
                    break Label_0618;
                }
                child = this.getChildAt((int)list.get(n3));
                try {
                    view = (View)child.getTag();
                    if (((StickyGridHeadersBaseAdapterWrapper$HeaderFillerView)child).getHeaderId() == this.mCurrentHeaderId && child.getTop() < 0 && this.mAreHeadersSticky) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    if (view.getVisibility() == 0 && !b2) {
                        if (this.mHeadersIgnorePadding) {
                            n4 = View$MeasureSpec.makeMeasureSpec(this.getWidth(), 1073741824);
                        }
                        else {
                            n4 = View$MeasureSpec.makeMeasureSpec(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight(), 1073741824);
                        }
                        measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
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
                    }
                    ++n3;
                    continue Label_0645_Outer;
                    // iftrue(Label_0735:, this.mStickiedHeader.getWidth() == width)
                    // iftrue(Label_0880:, this.mHeaderBottomPosition == headerHeight)
                    // iftrue(Label_1008:, !this.mHeadersIgnorePadding)
                    // iftrue(Label_0885:, b == false || !this.mMaskStickyHeaderRegion)
                    // iftrue(Label_0997:, !this.mClippingToPadding)
                    // iftrue(Label_0890:, !this.mHeadersIgnorePadding)
                    // iftrue(Label_0633:, b != false)
                    // iftrue(Label_0931:, !this.mHeadersIgnorePadding)
                    // iftrue(Label_0859:, this.mHeaderBottomPosition == headerHeight)
                    // iftrue(Label_0967:, !this.mHeadersIgnorePadding)
                    // iftrue(Label_0908:, !this.mHeadersIgnorePadding)
                Label_0820:
                    while (true) {
                        Block_26_Outer:Label_0761_Outer:Label_0633_Outer:
                        while (true) {
                            Block_24: {
                                while (true) {
                                    Block_22: {
                                        while (true) {
                                            while (true) {
                                                Label_0735: {
                                                Label_0880:
                                                    while (true) {
                                                        while (true) {
                                                            Label_0673: {
                                                                while (true) {
                                                                    while (true) {
                                                                    Block_31:
                                                                        while (true) {
                                                                            break Block_24;
                                                                            n5 = View$MeasureSpec.makeMeasureSpec(this.getWidth(), 1073741824);
                                                                            break Label_0673;
                                                                            Label_0997: {
                                                                                this.mClippingRect.top = 0;
                                                                            }
                                                                            while (true) {
                                                                                Label_0791: {
                                                                                    break Label_0791;
                                                                                    this.mClippingRect.top = this.getPaddingTop();
                                                                                    break Label_0791;
                                                                                    Label_0908:
                                                                                    n5 = View$MeasureSpec.makeMeasureSpec(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight(), 1073741824);
                                                                                    break Label_0673;
                                                                                    this.mStickiedHeader.layout(this.getLeft(), 0, this.getRight(), this.mStickiedHeader.getHeight());
                                                                                    break Label_0735;
                                                                                    Label_0890:
                                                                                    width = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
                                                                                    continue Block_25_Outer;
                                                                                    this.mStickiedHeader.draw(canvas);
                                                                                    break Block_31;
                                                                                }
                                                                                canvas.save();
                                                                                canvas.clipRect(this.mClippingRect);
                                                                                canvas.translate(0.0f, (float)n);
                                                                                break Label_0820;
                                                                                canvas.saveLayerAlpha(0.0f, 0.0f, (float)canvas.getWidth(), (float)canvas.getHeight(), this.mHeaderBottomPosition * 255 / headerHeight, 31);
                                                                                continue Label_0820_Outer;
                                                                            }
                                                                            break Block_22;
                                                                            width = this.getWidth();
                                                                            continue Block_25_Outer;
                                                                        }
                                                                        canvas.restore();
                                                                        break Label_0880;
                                                                        this.mClippingRect.bottom = n + headerHeight;
                                                                        continue Block_26_Outer;
                                                                    }
                                                                    Label_0931: {
                                                                        this.mStickiedHeader.layout(this.getLeft() + this.getPaddingLeft(), 0, this.getRight() - this.getPaddingRight(), this.mStickiedHeader.getHeight());
                                                                    }
                                                                    break Label_0735;
                                                                    continue Label_0761_Outer;
                                                                }
                                                                Label_0885: {
                                                                    return;
                                                                }
                                                            }
                                                            measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
                                                            this.mStickiedHeader.measure(View$MeasureSpec.makeMeasureSpec(0, 0), View$MeasureSpec.makeMeasureSpec(0, 0));
                                                            this.mStickiedHeader.measure(n5, measureSpec2);
                                                            continue Label_0820_Outer;
                                                        }
                                                        continue Label_0761_Outer;
                                                    }
                                                    canvas.restore();
                                                    return;
                                                    this.mClippingRect.left = 0;
                                                    this.mClippingRect.right = this.getWidth();
                                                    continue Label_0633_Outer;
                                                }
                                                continue;
                                            }
                                            Label_0967: {
                                                this.mClippingRect.left = this.getPaddingLeft();
                                            }
                                            this.mClippingRect.right = this.getWidth() - this.getPaddingRight();
                                            continue Label_0633_Outer;
                                        }
                                    }
                                    canvas.restore();
                                    continue;
                                }
                            }
                            continue Label_0820_Outer;
                        }
                        Label_1008: {
                            canvas.translate((float)this.getPaddingLeft(), (float)n);
                        }
                        continue Label_0820;
                    }
                }
                catch (Exception ex) {}
            }
        }
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
        int mNumMeasuredColumns = 1;
        if (this.mNumColumns == -1) {
            if (this.mColumnWidth > 0) {
                final int max = Math.max(View$MeasureSpec.getSize(n) - this.getPaddingLeft() - this.getPaddingRight(), 0);
                final int n3 = max / this.mColumnWidth;
                if (n3 > 0) {
                    for (mNumMeasuredColumns = n3; mNumMeasuredColumns != 1 && this.mColumnWidth * mNumMeasuredColumns + (mNumMeasuredColumns - 1) * this.mHorizontalSpacing > max; --mNumMeasuredColumns) {}
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
        final StickyGridHeadersGridView$SavedState stickyGridHeadersGridView$SavedState = (StickyGridHeadersGridView$SavedState)parcelable;
        super.onRestoreInstanceState(stickyGridHeadersGridView$SavedState.getSuperState());
        this.mAreHeadersSticky = stickyGridHeadersGridView$SavedState.areHeadersSticky;
        this.requestLayout();
    }
    
    public Parcelable onSaveInstanceState() {
        final StickyGridHeadersGridView$SavedState stickyGridHeadersGridView$SavedState = new StickyGridHeadersGridView$SavedState(super.onSaveInstanceState());
        stickyGridHeadersGridView$SavedState.areHeadersSticky = this.mAreHeadersSticky;
        return (Parcelable)stickyGridHeadersGridView$SavedState;
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
                header.postDelayed((Runnable)new StickyGridHeadersGridView$2(this, child), (long)ViewConfiguration.getPressedStateDuration());
                this.invalidate(0, child.getTop(), this.getWidth(), child.getHeight() + child.getTop());
            }
        }
        switch (action & 0xFF) {
            case 0: {
                if (this.mPendingCheckForTap == null) {
                    this.mPendingCheckForTap = new StickyGridHeadersGridView$CheckForHeaderTap(this);
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
                        this.invalidate(0, child2.getTop(), this.getWidth(), child2.getHeight() + child2.getTop());
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
                            this.mPerformHeaderClick = new StickyGridHeadersGridView$PerformHeaderClick(this, null);
                        }
                        final StickyGridHeadersGridView$PerformHeaderClick mPerformHeaderClick = this.mPerformHeaderClick;
                        mPerformHeaderClick.mClickMotionPosition = this.mMotionHeaderPosition;
                        mPerformHeaderClick.rememberWindowAttachCount();
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
                                this.postDelayed(this.mTouchModeReset = new StickyGridHeadersGridView$3(this, header4, mPerformHeaderClick), (long)ViewConfiguration.getPressedStateDuration());
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
        final boolean b = this.mOnHeaderLongClickListener != null && this.mOnHeaderLongClickListener.onHeaderLongClick((AdapterView<?>)this, view, n);
        if (b) {
            if (view != null) {
                view.sendAccessibilityEvent(2);
            }
            this.performHapticFeedback(0);
        }
        return b;
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
    
    public void setVerticalSpacing(final int n) {
        super.setVerticalSpacing(n);
        this.mVerticalSpacing = n;
    }
}
