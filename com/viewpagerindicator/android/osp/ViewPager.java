// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator.android.osp;

import android.support.v4.os.ParcelableCompat;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.content.res.TypedArray;
import android.util.Log;
import android.database.DataSetObserver;
import android.view.View$MeasureSpec;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.view.ViewConfiguration;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.graphics.Canvas;
import android.view.accessibility.AccessibilityEvent;
import android.view.KeyEvent;
import java.util.List;
import java.util.Collections;
import android.os.SystemClock;
import android.view.SoundEffectConstants;
import android.view.FocusFinder;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.view.VelocityTracker;
import android.graphics.Rect;
import java.lang.reflect.Method;
import android.widget.Scroller;
import android.os.Parcelable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.View;
import java.util.ArrayList;
import android.support.v4.view.PagerAdapter;
import android.view.animation.Interpolator;
import java.util.Comparator;
import android.view.ViewGroup;

public class ViewPager extends ViewGroup
{
    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator<ItemInfo> COMPARATOR;
    protected static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int[] LAYOUT_ATTRS;
    private static final int MAX_SETTLE_DURATION_MS = 400;
    private static final int MIN_DISTANCE_FOR_FLING = 20;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final String TAG_TOUCH = "ViewPagerTouch";
    private static final boolean USE_CACHE = false;
    private static final Interpolator sInterpolator;
    private static final ViewPositionComparator sPositionComparator;
    private int mActivePointerId;
    private PagerAdapter mAdapter;
    private OnAdapterChangeListener mAdapterChangeListener;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    private int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsTrackingTouch;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffectCompat mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private OnPageChangeListener mOnPageChangeListener;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffectCompat mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private Method mSetChildrenDrawingOrderEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    
    static {
        LAYOUT_ATTRS = new int[] { 16842931 };
        COMPARATOR = new Comparator<ItemInfo>() {
            @Override
            public int compare(final ItemInfo itemInfo, final ItemInfo itemInfo2) {
                return itemInfo.position - itemInfo2.position;
            }
        };
        sInterpolator = (Interpolator)new Interpolator() {
            public float getInterpolation(float n) {
                --n;
                return n * n * n * n * n + 1.0f;
            }
        };
        sPositionComparator = new ViewPositionComparator();
    }
    
    public ViewPager(final Context context) {
        super(context);
        this.mItems = new ArrayList<ItemInfo>();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mEndScrollRunnable = new Runnable() {
            @Override
            public void run() {
                ViewPager.this.setScrollState(0);
                ViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        this.initViewPager();
    }
    
    public ViewPager(final Context context, final AttributeSet set) {
        super(context, set);
        this.mItems = new ArrayList<ItemInfo>();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mEndScrollRunnable = new Runnable() {
            @Override
            public void run() {
                ViewPager.this.setScrollState(0);
                ViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        this.initViewPager();
    }
    
    private void calculatePageOffsets(ItemInfo itemInfo, int i, ItemInfo itemInfo2) {
        final int count = this.mAdapter.getCount();
        final int width = this.getWidth();
        float n;
        if (width > 0) {
            n = this.mPageMargin / width;
        }
        else {
            n = 0.0f;
        }
        if (itemInfo2 != null) {
            final int position = itemInfo2.position;
            if (position < itemInfo.position) {
                int n2 = 0;
                float n3 = itemInfo2.offset + itemInfo2.widthFactor + n;
                int j;
                for (int n4 = position + 1; n4 <= itemInfo.position && n2 < this.mItems.size(); n4 = j + 1) {
                    itemInfo2 = this.mItems.get(n2);
                    float offset;
                    while (true) {
                        offset = n3;
                        j = n4;
                        if (n4 <= itemInfo2.position) {
                            break;
                        }
                        offset = n3;
                        j = n4;
                        if (n2 >= this.mItems.size() - 1) {
                            break;
                        }
                        ++n2;
                        itemInfo2 = this.mItems.get(n2);
                    }
                    while (j < itemInfo2.position) {
                        offset += this.mAdapter.getPageWidth(j) + n;
                        ++j;
                    }
                    itemInfo2.offset = offset;
                    n3 = offset + (itemInfo2.widthFactor + n);
                }
            }
            else if (position > itemInfo.position) {
                int n5 = this.mItems.size() - 1;
                float offset2 = itemInfo2.offset;
                int k;
                for (int n6 = position - 1; n6 >= itemInfo.position && n5 >= 0; n6 = k - 1) {
                    itemInfo2 = this.mItems.get(n5);
                    float n7;
                    while (true) {
                        n7 = offset2;
                        k = n6;
                        if (n6 >= itemInfo2.position) {
                            break;
                        }
                        n7 = offset2;
                        k = n6;
                        if (n5 <= 0) {
                            break;
                        }
                        --n5;
                        itemInfo2 = this.mItems.get(n5);
                    }
                    while (k > itemInfo2.position) {
                        n7 -= this.mAdapter.getPageWidth(k) + n;
                        --k;
                    }
                    offset2 = n7 - (itemInfo2.widthFactor + n);
                    itemInfo2.offset = offset2;
                }
            }
        }
        final int size = this.mItems.size();
        final float offset3 = itemInfo.offset;
        int l = itemInfo.position - 1;
        float offset4;
        if (itemInfo.position == 0) {
            offset4 = itemInfo.offset;
        }
        else {
            offset4 = -3.4028235E38f;
        }
        this.mFirstOffset = offset4;
        float mLastOffset;
        if (itemInfo.position == count - 1) {
            mLastOffset = itemInfo.offset + itemInfo.widthFactor - 1.0f;
        }
        else {
            mLastOffset = Float.MAX_VALUE;
        }
        this.mLastOffset = mLastOffset;
        int n8 = i - 1;
        float n9 = offset3;
        while (n8 >= 0) {
            for (itemInfo2 = this.mItems.get(n8); l > itemInfo2.position; --l) {
                n9 -= this.mAdapter.getPageWidth(l) + n;
            }
            n9 -= itemInfo2.widthFactor + n;
            itemInfo2.offset = n9;
            if (itemInfo2.position == 0) {
                this.mFirstOffset = n9;
            }
            --n8;
            --l;
        }
        float offset5 = itemInfo.offset + itemInfo.widthFactor + n;
        final int n10 = itemInfo.position + 1;
        final int n11 = i + 1;
        i = n10;
        for (int n12 = n11; n12 < size; ++n12, ++i) {
            for (itemInfo = this.mItems.get(n12); i < itemInfo.position; ++i) {
                offset5 += this.mAdapter.getPageWidth(i) + n;
            }
            if (itemInfo.position == count - 1) {
                this.mLastOffset = itemInfo.widthFactor + offset5 - 1.0f;
            }
            itemInfo.offset = offset5;
            offset5 += itemInfo.widthFactor + n;
        }
    }
    
    private void completeScroll(final boolean b) {
        boolean b2;
        if (this.mScrollState == 2) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (b2) {
            this.setScrollingCacheEnabled(false);
            this.mScroller.abortAnimation();
            final int scrollX = this.getScrollX();
            final int scrollY = this.getScrollY();
            final int currX = this.mScroller.getCurrX();
            final int currY = this.mScroller.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                this.scrollTo(currX, currY);
            }
        }
        this.mPopulatePending = false;
        final int n = 0;
        int n2 = b2 ? 1 : 0;
        for (int i = n; i < this.mItems.size(); ++i) {
            final ItemInfo itemInfo = this.mItems.get(i);
            if (itemInfo.scrolling) {
                n2 = 1;
                itemInfo.scrolling = false;
            }
        }
        if (n2 != 0) {
            if (!b) {
                this.mEndScrollRunnable.run();
                return;
            }
            ViewCompat.postOnAnimation((View)this, this.mEndScrollRunnable);
        }
    }
    
    private int determineTargetPage(int n, final float n2, int max, final int n3) {
        if (Math.abs(n3) > this.mFlingDistance && Math.abs(max) > this.mMinimumVelocity) {
            if (max <= 0) {
                ++n;
            }
        }
        else {
            float n4;
            if (n >= this.mCurItem) {
                n4 = 0.4f;
            }
            else {
                n4 = 0.6f;
            }
            n = (int)(n + n2 + n4);
        }
        max = n;
        if (this.mItems.size() > 0) {
            max = Math.max(this.mItems.get(0).position, Math.min(n, this.mItems.get(this.mItems.size() - 1).position));
        }
        return max;
    }
    
    private void enableLayers(final boolean b) {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            int n;
            if (b) {
                n = 2;
            }
            else {
                n = 0;
            }
            ViewCompat.setLayerType(this.getChildAt(i), n, null);
        }
    }
    
    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }
    
    private Rect getChildRectInPagerCoordinates(final Rect rect, final View view) {
        Rect rect2 = rect;
        if (rect == null) {
            rect2 = new Rect();
        }
        if (view == null) {
            rect2.set(0, 0, 0, 0);
        }
        else {
            rect2.left = view.getLeft();
            rect2.right = view.getRight();
            rect2.top = view.getTop();
            rect2.bottom = view.getBottom();
            ViewPager viewPager;
            for (ViewParent viewParent = view.getParent(); viewParent instanceof ViewGroup && viewParent != this; viewParent = viewPager.getParent()) {
                viewPager = (ViewPager)viewParent;
                rect2.left += viewPager.getLeft();
                rect2.right += viewPager.getRight();
                rect2.top += viewPager.getTop();
                rect2.bottom += viewPager.getBottom();
            }
        }
        return rect2;
    }
    
    private ItemInfo infoForCurrentScrollPosition() {
        float n = 0.0f;
        final int width = this.getWidth();
        float n2;
        if (width > 0) {
            n2 = this.getScrollX() / width;
        }
        else {
            n2 = 0.0f;
        }
        if (width > 0) {
            n = this.mPageMargin / width;
        }
        int position = -1;
        float offset = 0.0f;
        float widthFactor = 0.0f;
        int n3 = 1;
        ItemInfo itemInfo = null;
        int n4 = 0;
        ItemInfo itemInfo2;
        while (true) {
            itemInfo2 = itemInfo;
            if (n4 >= this.mItems.size()) {
                break;
            }
            final ItemInfo itemInfo3 = this.mItems.get(n4);
            int n5 = n4;
            ItemInfo mTempItem = itemInfo3;
            if (n3 == 0) {
                n5 = n4;
                mTempItem = itemInfo3;
                if (itemInfo3.position != position + 1) {
                    mTempItem = this.mTempItem;
                    mTempItem.offset = offset + widthFactor + n;
                    mTempItem.position = position + 1;
                    mTempItem.widthFactor = this.mAdapter.getPageWidth(mTempItem.position);
                    n5 = n4 - 1;
                }
            }
            offset = mTempItem.offset;
            final float widthFactor2 = mTempItem.widthFactor;
            if (n3 == 0) {
                itemInfo2 = itemInfo;
                if (n2 < offset) {
                    break;
                }
            }
            if (n2 < widthFactor2 + offset + n || n5 == this.mItems.size() - 1) {
                itemInfo2 = mTempItem;
                break;
            }
            n3 = 0;
            position = mTempItem.position;
            widthFactor = mTempItem.widthFactor;
            n4 = n5 + 1;
            itemInfo = mTempItem;
        }
        return itemInfo2;
    }
    
    private boolean isGutterDrag(final float n, final float n2) {
        return (n < this.mGutterSize && n2 > 0.0f) || (n > this.getWidth() - this.mGutterSize && n2 < 0.0f);
    }
    
    private boolean onInterceptTouchEventInternal(final MotionEvent motionEvent) {
        final int n = motionEvent.getAction() & 0xFF;
        if (n == 3 || n == 1) {
            this.mIsTrackingTouch = false;
            this.mIsBeingDragged = false;
            this.mIsUnableToDrag = false;
            this.mActivePointerId = -1;
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
            }
            return false;
        }
        if (n != 0) {
            if (this.mIsBeingDragged) {
                this.mIsTrackingTouch = false;
                return true;
            }
            if (this.mIsUnableToDrag) {
                return this.mIsTrackingTouch = false;
            }
        }
        switch (n) {
            case 2: {
                final int mActivePointerId = this.mActivePointerId;
                if (mActivePointerId == -1) {
                    break;
                }
                final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, mActivePointerId);
                final float x = MotionEventCompat.getX(motionEvent, pointerIndex);
                final float n2 = x - this.mLastMotionX;
                final float abs = Math.abs(n2);
                final float y = MotionEventCompat.getY(motionEvent, pointerIndex);
                final float abs2 = Math.abs(y - this.mInitialMotionY);
                if (n2 != 0.0f && !this.isGutterDrag(this.mLastMotionX, n2) && this.canScroll((View)this, false, (int)n2, (int)x, (int)y)) {
                    this.mIsTrackingTouch = false;
                    this.mLastMotionX = x;
                    this.mLastMotionY = y;
                    this.mIsUnableToDrag = true;
                    return false;
                }
                if (abs > this.mTouchSlop && abs > abs2) {
                    this.mIsBeingDragged = true;
                    this.setScrollState(1);
                    float mLastMotionX;
                    if (n2 > 0.0f) {
                        mLastMotionX = this.mInitialMotionX + this.mTouchSlop;
                    }
                    else {
                        mLastMotionX = this.mInitialMotionX - this.mTouchSlop;
                    }
                    this.mLastMotionX = mLastMotionX;
                    this.mLastMotionY = y;
                    this.setScrollingCacheEnabled(true);
                }
                else if (abs2 > this.mTouchSlop) {
                    this.mIsUnableToDrag = true;
                }
                if (this.mIsBeingDragged && this.performDrag(x)) {
                    ViewCompat.postInvalidateOnAnimation((View)this);
                    break;
                }
                break;
            }
            case 0: {
                final float x2 = motionEvent.getX();
                this.mInitialMotionX = x2;
                this.mLastMotionX = x2;
                final float y2 = motionEvent.getY();
                this.mInitialMotionY = y2;
                this.mLastMotionY = y2;
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsUnableToDrag = false;
                this.mIsTrackingTouch = true;
                this.mScroller.computeScrollOffset();
                if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = false;
                    this.populate();
                    this.mIsBeingDragged = true;
                    this.setScrollState(1);
                    break;
                }
                this.completeScroll(false);
                this.mIsBeingDragged = false;
                break;
            }
            case 6: {
                this.onSecondaryPointerUp(motionEvent);
                break;
            }
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        return this.mIsBeingDragged;
    }
    
    private void onSecondaryPointerUp(final MotionEvent motionEvent) {
        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            int n;
            if (actionIndex == 0) {
                n = 1;
            }
            else {
                n = 0;
            }
            this.mLastMotionX = MotionEventCompat.getX(motionEvent, n);
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, n);
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }
    
    private boolean pageScrolled(int n) {
        boolean b = false;
        if (this.mItems.size() == 0) {
            this.mCalledSuper = false;
            this.onPageScrolled(0, 0.0f, 0);
            if (!this.mCalledSuper) {
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            }
        }
        else {
            final ItemInfo infoForCurrentScrollPosition = this.infoForCurrentScrollPosition();
            final int width = this.getWidth();
            final int mPageMargin = this.mPageMargin;
            final float n2 = this.mPageMargin / width;
            final int position = infoForCurrentScrollPosition.position;
            final float n3 = (n / width - infoForCurrentScrollPosition.offset) / (infoForCurrentScrollPosition.widthFactor + n2);
            n = (int)((width + mPageMargin) * n3);
            this.mCalledSuper = false;
            this.onPageScrolled(position, n3, n);
            if (!this.mCalledSuper) {
                throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            }
            b = true;
        }
        return b;
    }
    
    private boolean performDrag(float mLastMotionX) {
        final boolean b = false;
        final boolean b2 = false;
        boolean b3 = false;
        final float mLastMotionX2 = this.mLastMotionX;
        this.mLastMotionX = mLastMotionX;
        final float n = this.getScrollX() + (mLastMotionX2 - mLastMotionX);
        final int width = this.getWidth();
        mLastMotionX = width * this.mFirstOffset;
        float n2 = width * this.mLastOffset;
        boolean b4 = true;
        boolean b5 = true;
        final ItemInfo itemInfo = this.mItems.get(0);
        final ItemInfo itemInfo2 = this.mItems.get(this.mItems.size() - 1);
        if (itemInfo.position != 0) {
            b4 = false;
            mLastMotionX = itemInfo.offset * width;
        }
        if (itemInfo2.position != this.mAdapter.getCount() - 1) {
            b5 = false;
            n2 = itemInfo2.offset * width;
        }
        if (n < mLastMotionX) {
            if (b4) {
                b3 = this.mLeftEdge.onPull(Math.abs(mLastMotionX - n) / width);
            }
        }
        else {
            b3 = b;
            mLastMotionX = n;
            if (n > n2) {
                b3 = b2;
                if (b5) {
                    b3 = this.mRightEdge.onPull(Math.abs(n - n2) / width);
                }
                mLastMotionX = n2;
            }
        }
        this.mLastMotionX += mLastMotionX - (int)mLastMotionX;
        this.scrollTo((int)mLastMotionX, this.getScrollY());
        this.pageScrolled((int)mLastMotionX);
        return b3;
    }
    
    private void recomputeScrollPosition(int n, int n2, int duration, int timePassed) {
        if (n2 > 0 && !this.mItems.isEmpty()) {
            n2 = (n + duration) * (this.getScrollX() / (n2 + timePassed));
            this.scrollTo(n2, this.getScrollY());
            if (!this.mScroller.isFinished()) {
                duration = this.mScroller.getDuration();
                timePassed = this.mScroller.timePassed();
                this.mScroller.startScroll(n2, 0, (int)(this.infoForPosition(this.mCurItem).offset * n), 0, duration - timePassed);
            }
        }
        else {
            final ItemInfo infoForPosition = this.infoForPosition(this.mCurItem);
            float min;
            if (infoForPosition != null) {
                min = Math.min(infoForPosition.offset, this.mLastOffset);
            }
            else {
                min = 0.0f;
            }
            n *= (int)min;
            if (n != this.getScrollX()) {
                this.completeScroll(false);
                this.scrollTo(n, this.getScrollY());
            }
        }
    }
    
    private void removeNonDecorViews() {
        int n;
        for (int i = 0; i < this.getChildCount(); i = n + 1) {
            n = i;
            if (!((LayoutParams)this.getChildAt(i).getLayoutParams()).isDecor) {
                this.removeViewAt(i);
                n = i - 1;
            }
        }
    }
    
    private void scrollToItem(final int n, final boolean b, final int n2, final boolean b2) {
        final ItemInfo infoForPosition = this.infoForPosition(n);
        int n3 = 0;
        if (infoForPosition != null) {
            n3 = (int)(this.getWidth() * Math.max(this.mFirstOffset, Math.min(infoForPosition.offset, this.mLastOffset)));
        }
        if (b) {
            this.smoothScrollTo(n3, 0, n2);
            if (b2 && this.mOnPageChangeListener != null) {
                this.mOnPageChangeListener.onPageSelected(n);
            }
            if (b2 && this.mInternalPageChangeListener != null) {
                this.mInternalPageChangeListener.onPageSelected(n);
            }
            return;
        }
        if (b2 && this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(n);
        }
        if (b2 && this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageSelected(n);
        }
        this.completeScroll(false);
        this.scrollTo(n3, 0);
    }
    
    private void setScrollState(final int mScrollState) {
        if (this.mScrollState != mScrollState) {
            this.mScrollState = mScrollState;
            if (this.mPageTransformer != null) {
                this.enableLayers(mScrollState != 0);
            }
            if (this.mOnPageChangeListener != null) {
                this.mOnPageChangeListener.onPageScrollStateChanged(mScrollState);
            }
        }
    }
    
    private void setScrollingCacheEnabled(final boolean mScrollingCacheEnabled) {
        if (this.mScrollingCacheEnabled != mScrollingCacheEnabled) {
            this.mScrollingCacheEnabled = mScrollingCacheEnabled;
        }
    }
    
    public void addFocusables(final ArrayList<View> list, final int n, final int n2) {
        final int size = list.size();
        final int descendantFocusability = this.getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i = 0; i < this.getChildCount(); ++i) {
                final View child = this.getChildAt(i);
                if (child.getVisibility() == 0) {
                    final ItemInfo infoForChild = this.infoForChild(child);
                    if (infoForChild != null && infoForChild.position == this.mCurItem) {
                        child.addFocusables((ArrayList)list, n, n2);
                    }
                }
            }
        }
        if ((descendantFocusability != 262144 || size == list.size()) && this.isFocusable() && ((n2 & 0x1) != 0x1 || !this.isInTouchMode() || this.isFocusableInTouchMode()) && list != null) {
            list.add((View)this);
        }
    }
    
    ItemInfo addNewItem(final int position, final int n) {
        final ItemInfo itemInfo = new ItemInfo();
        itemInfo.position = position;
        itemInfo.object = this.mAdapter.instantiateItem(this, position);
        itemInfo.widthFactor = this.mAdapter.getPageWidth(position);
        if (n < 0 || n >= this.mItems.size()) {
            this.mItems.add(itemInfo);
            return itemInfo;
        }
        this.mItems.add(n, itemInfo);
        return itemInfo;
    }
    
    public void addTouchables(final ArrayList<View> list) {
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() == 0) {
                final ItemInfo infoForChild = this.infoForChild(child);
                if (infoForChild != null && infoForChild.position == this.mCurItem) {
                    child.addTouchables((ArrayList)list);
                }
            }
        }
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        ViewGroup$LayoutParams generateLayoutParams = viewGroup$LayoutParams;
        if (!this.checkLayoutParams(viewGroup$LayoutParams)) {
            generateLayoutParams = this.generateLayoutParams(viewGroup$LayoutParams);
        }
        final LayoutParams layoutParams = (LayoutParams)generateLayoutParams;
        layoutParams.isDecor |= (view instanceof Decor);
        if (!this.mInLayout) {
            super.addView(view, n, generateLayoutParams);
            return;
        }
        if (layoutParams != null && layoutParams.isDecor) {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
        layoutParams.needsMeasure = true;
        this.addViewInLayout(view, n, generateLayoutParams);
    }
    
    public boolean arrowScroll(final int n) {
        View focus;
        if ((focus = this.findFocus()) == this) {
            focus = null;
        }
        boolean b = false;
        final View nextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup)this, focus, n);
        if (nextFocus != null && nextFocus != focus) {
            if (n == 17) {
                final int left = this.getChildRectInPagerCoordinates(this.mTempRect, nextFocus).left;
                final int left2 = this.getChildRectInPagerCoordinates(this.mTempRect, focus).left;
                if (focus != null && left >= left2) {
                    b = this.pageLeft();
                }
                else {
                    b = nextFocus.requestFocus();
                }
            }
            else if (n == 66) {
                final int left3 = this.getChildRectInPagerCoordinates(this.mTempRect, nextFocus).left;
                final int left4 = this.getChildRectInPagerCoordinates(this.mTempRect, focus).left;
                if (focus != null && left3 <= left4) {
                    b = this.pageRight();
                }
                else {
                    b = nextFocus.requestFocus();
                }
            }
        }
        else if (n == 17 || n == 1) {
            b = this.pageLeft();
        }
        else if (n == 66 || n == 2) {
            b = this.pageRight();
        }
        if (b) {
            this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(n));
        }
        return b;
    }
    
    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return false;
        }
        this.mFakeDragging = true;
        this.setScrollState(1);
        this.mLastMotionX = 0.0f;
        this.mInitialMotionX = 0.0f;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        else {
            this.mVelocityTracker.clear();
        }
        final long uptimeMillis = SystemClock.uptimeMillis();
        final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.mVelocityTracker.addMovement(obtain);
        obtain.recycle();
        this.mFakeDragBeginTime = uptimeMillis;
        return true;
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
    
    public void computeScroll() {
        if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            final int scrollX = this.getScrollX();
            final int scrollY = this.getScrollY();
            final int currX = this.mScroller.getCurrX();
            final int currY = this.mScroller.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                this.scrollTo(currX, currY);
                if (!this.pageScrolled(currX)) {
                    this.mScroller.abortAnimation();
                    this.scrollTo(0, currY);
                }
            }
            ViewCompat.postInvalidateOnAnimation((View)this);
            return;
        }
        this.completeScroll(true);
    }
    
    void dataSetChanged() {
        boolean b = this.mItems.size() < this.mOffscreenPageLimit * 2 + 1 && this.mItems.size() < this.mAdapter.getCount();
        int mCurItem = this.mCurItem;
        int n = 0;
        int max;
        int n2;
        int n3;
        for (int i = 0; i < this.mItems.size(); i = n3 + 1, n = n2, mCurItem = max) {
            final ItemInfo itemInfo = this.mItems.get(i);
            final int itemPosition = this.mAdapter.getItemPosition(itemInfo.object);
            if (itemPosition == -1) {
                max = mCurItem;
                n2 = n;
                n3 = i;
            }
            else if (itemPosition == -2) {
                this.mItems.remove(i);
                final int n4 = i - 1;
                int n5;
                if ((n5 = n) == 0) {
                    this.mAdapter.startUpdate(this);
                    n5 = 1;
                }
                this.mAdapter.destroyItem(this, itemInfo.position, itemInfo.object);
                b = true;
                n3 = n4;
                n2 = n5;
                max = mCurItem;
                if (this.mCurItem == itemInfo.position) {
                    max = Math.max(0, Math.min(this.mCurItem, this.mAdapter.getCount() - 1));
                    b = true;
                    n3 = n4;
                    n2 = n5;
                }
            }
            else {
                n3 = i;
                n2 = n;
                max = mCurItem;
                if (itemInfo.position != itemPosition) {
                    if (itemInfo.position == this.mCurItem) {
                        mCurItem = itemPosition;
                    }
                    itemInfo.position = itemPosition;
                    b = true;
                    n3 = i;
                    n2 = n;
                    max = mCurItem;
                }
            }
        }
        if (n != 0) {
            this.mAdapter.finishUpdate(this);
        }
        Collections.sort(this.mItems, ViewPager.COMPARATOR);
        if (b) {
            for (int childCount = this.getChildCount(), j = 0; j < childCount; ++j) {
                final LayoutParams layoutParams = (LayoutParams)this.getChildAt(j).getLayoutParams();
                if (!layoutParams.isDecor) {
                    layoutParams.widthFactor = 0.0f;
                }
            }
            this.setCurrentItemInternal(mCurItem, false, true);
            this.requestLayout();
        }
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || this.executeKeyEvent(keyEvent);
    }
    
    public boolean dispatchPopulateAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() == 0) {
                final ItemInfo infoForChild = this.infoForChild(child);
                if (infoForChild != null && infoForChild.position == this.mCurItem && child.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    float distanceInfluenceForSnapDuration(final float n) {
        return (float)Math.sin((float)((n - 0.5f) * 0.4712389167638204));
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        boolean b = false;
        boolean b2 = false;
        final int overScrollMode = ViewCompat.getOverScrollMode((View)this);
        if (overScrollMode == 0 || (overScrollMode == 1 && this.mAdapter != null && this.mAdapter.getCount() > 1)) {
            if (!this.mLeftEdge.isFinished()) {
                final int save = canvas.save();
                final int n = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
                final int width = this.getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float)(-n + this.getPaddingTop()), this.mFirstOffset * width);
                this.mLeftEdge.setSize(n, width);
                b2 = (false | this.mLeftEdge.draw(canvas));
                canvas.restoreToCount(save);
            }
            b = b2;
            if (!this.mRightEdge.isFinished()) {
                final int save2 = canvas.save();
                final int width2 = this.getWidth();
                final int height = this.getHeight();
                final int paddingTop = this.getPaddingTop();
                final int paddingBottom = this.getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float)(-this.getPaddingTop()), -(this.mLastOffset + 1.0f) * width2);
                this.mRightEdge.setSize(height - paddingTop - paddingBottom, width2);
                b = (b2 | this.mRightEdge.draw(canvas));
                canvas.restoreToCount(save2);
            }
        }
        else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if (b) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        final Drawable mMarginDrawable = this.mMarginDrawable;
        if (mMarginDrawable != null && mMarginDrawable.isStateful()) {
            mMarginDrawable.setState(this.getDrawableState());
        }
    }
    
    public void endFakeDrag() {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        final VelocityTracker mVelocityTracker = this.mVelocityTracker;
        mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaximumVelocity);
        final int n = (int)VelocityTrackerCompat.getXVelocity(mVelocityTracker, this.mActivePointerId);
        this.mPopulatePending = true;
        final int width = this.getWidth();
        final int scrollX = this.getScrollX();
        final ItemInfo infoForCurrentScrollPosition = this.infoForCurrentScrollPosition();
        this.setCurrentItemInternal(this.determineTargetPage(infoForCurrentScrollPosition.position, (scrollX / width - infoForCurrentScrollPosition.offset) / infoForCurrentScrollPosition.widthFactor, n, (int)(this.mLastMotionX - this.mInitialMotionX)), true, true, n, true);
        this.endDrag();
        this.mFakeDragging = false;
    }
    
    public boolean executeKeyEvent(final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            switch (keyEvent.getKeyCode()) {
                case 21: {
                    return this.arrowScroll(17);
                }
                case 22: {
                    return this.arrowScroll(66);
                }
                case 61: {
                    if (KeyEventCompat.hasNoModifiers(keyEvent)) {
                        return this.arrowScroll(2);
                    }
                    if (KeyEventCompat.hasModifiers(keyEvent, 1)) {
                        return this.arrowScroll(1);
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    public void fakeDragBy(float n) {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        this.mLastMotionX += n;
        final float n2 = this.getScrollX() - n;
        final int width = this.getWidth();
        n = width * this.mFirstOffset;
        float n3 = width * this.mLastOffset;
        final ItemInfo itemInfo = this.mItems.get(0);
        final ItemInfo itemInfo2 = this.mItems.get(this.mItems.size() - 1);
        if (itemInfo.position != 0) {
            n = itemInfo.offset * width;
        }
        if (itemInfo2.position != this.mAdapter.getCount() - 1) {
            n3 = itemInfo2.offset * width;
        }
        if (n2 >= n) {
            n = n2;
            if (n2 > n3) {
                n = n3;
            }
        }
        this.mLastMotionX += n - (int)n;
        this.scrollTo((int)n, this.getScrollY());
        this.pageScrolled((int)n);
        final MotionEvent obtain = MotionEvent.obtain(this.mFakeDragBeginTime, SystemClock.uptimeMillis(), 2, this.mLastMotionX, 0.0f, 0);
        this.mVelocityTracker.addMovement(obtain);
        obtain.recycle();
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return this.generateDefaultLayoutParams();
    }
    
    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }
    
    protected int getChildDrawingOrder(int n, final int n2) {
        if (this.mDrawingOrder == 2) {
            n = n - 1 - n2;
        }
        else {
            n = n2;
        }
        return ((LayoutParams)this.mDrawingOrderedChildren.get(n).getLayoutParams()).childIndex;
    }
    
    public int getCurrentItem() {
        return this.mCurItem;
    }
    
    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }
    
    public int getPageMargin() {
        return this.mPageMargin;
    }
    
    ItemInfo infoForAnyChild(View view) {
        while (true) {
            final ViewParent parent = view.getParent();
            if (parent == this) {
                return this.infoForChild(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View)parent;
        }
    }
    
    ItemInfo infoForChild(final View view) {
        for (int i = 0; i < this.mItems.size(); ++i) {
            final ItemInfo itemInfo = this.mItems.get(i);
            if (this.mAdapter.isViewFromObject(view, itemInfo.object)) {
                return itemInfo;
            }
        }
        return null;
    }
    
    ItemInfo infoForPosition(final int n) {
        for (int i = 0; i < this.mItems.size(); ++i) {
            final ItemInfo itemInfo = this.mItems.get(i);
            if (itemInfo.position == n) {
                return itemInfo;
            }
        }
        return null;
    }
    
    void initViewPager() {
        this.setWillNotDraw(false);
        this.setDescendantFocusability(262144);
        this.setFocusable(true);
        final Context context = this.getContext();
        this.mScroller = new Scroller(context, ViewPager.sInterpolator);
        final ViewConfiguration value = ViewConfiguration.get(context);
        final float density = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = value.getScaledTouchSlop();
        this.mMinimumVelocity = (int)(400.0f * density);
        this.mMaximumVelocity = value.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffectCompat(context);
        this.mRightEdge = new EdgeEffectCompat(context);
        this.mFlingDistance = (int)(20.0f * density);
        this.mCloseEnough = (int)(2.0f * density);
        this.mDefaultGutterSize = (int)(16.0f * density);
        ViewCompat.setAccessibilityDelegate((View)this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility((View)this) == 0) {
            ViewCompat.setImportantForAccessibility((View)this, 1);
        }
    }
    
    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }
    
    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.mEndScrollRunnable);
        super.onDetachedFromWindow();
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
            final int scrollX = this.getScrollX();
            final int width = this.getWidth();
            final float n = this.mPageMargin / width;
            int n2 = 0;
            ItemInfo itemInfo = this.mItems.get(0);
            float offset = itemInfo.offset;
            for (int size = this.mItems.size(), i = itemInfo.position; i < this.mItems.get(size - 1).position; ++i) {
                while (i > itemInfo.position && n2 < size) {
                    final ArrayList<ItemInfo> mItems = this.mItems;
                    ++n2;
                    itemInfo = mItems.get(n2);
                }
                float n3;
                if (i == itemInfo.position) {
                    n3 = (itemInfo.offset + itemInfo.widthFactor) * width;
                    offset = itemInfo.offset + itemInfo.widthFactor + n;
                }
                else {
                    final float pageWidth = this.mAdapter.getPageWidth(i);
                    n3 = (offset + pageWidth) * width;
                    offset += pageWidth + n;
                }
                if (this.mPageMargin + n3 > scrollX) {
                    this.mMarginDrawable.setBounds((int)n3, this.mTopPageBounds, (int)(this.mPageMargin + n3 + 0.5f), this.mBottomPageBounds);
                    this.mMarginDrawable.draw(canvas);
                }
                if (n3 > scrollX + width) {
                    break;
                }
            }
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        return this.onInterceptTouchEventInternal(motionEvent);
    }
    
    protected void onLayout(final boolean b, int paddingTop, int n, int i, int paddingBottom) {
        this.mInLayout = true;
        this.populate();
        this.mInLayout = false;
        final int childCount = this.getChildCount();
        final int n2 = i - paddingTop;
        final int n3 = paddingBottom - n;
        n = this.getPaddingLeft();
        paddingTop = this.getPaddingTop();
        int paddingRight = this.getPaddingRight();
        paddingBottom = this.getPaddingBottom();
        final int scrollX = this.getScrollX();
        int mDecorChildCount = 0;
        int n4;
        int n5;
        int n6;
        int n7;
        for (int j = 0; j < childCount; ++j, mDecorChildCount = n4, paddingBottom = n5, n = n6, paddingRight = n7, paddingTop = i) {
            final View child = this.getChildAt(j);
            n4 = mDecorChildCount;
            n5 = paddingBottom;
            n6 = n;
            n7 = paddingRight;
            i = paddingTop;
            if (child.getVisibility() != 8) {
                final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
                n4 = mDecorChildCount;
                n5 = paddingBottom;
                n6 = n;
                n7 = paddingRight;
                i = paddingTop;
                if (layoutParams.isDecor) {
                    i = layoutParams.gravity;
                    final int gravity = layoutParams.gravity;
                    switch (i & 0x7) {
                        default: {
                            i = n;
                            n6 = n;
                            break;
                        }
                        case 3: {
                            i = n;
                            n6 = n + child.getMeasuredWidth();
                            break;
                        }
                        case 1: {
                            i = Math.max((n2 - child.getMeasuredWidth()) / 2, n);
                            n6 = n;
                            break;
                        }
                        case 5: {
                            i = n2 - paddingRight - child.getMeasuredWidth();
                            paddingRight += child.getMeasuredWidth();
                            n6 = n;
                            break;
                        }
                    }
                    switch (gravity & 0x70) {
                        default: {
                            n = paddingTop;
                            break;
                        }
                        case 48: {
                            n = paddingTop;
                            paddingTop += child.getMeasuredHeight();
                            break;
                        }
                        case 16: {
                            n = Math.max((n3 - child.getMeasuredHeight()) / 2, paddingTop);
                            break;
                        }
                        case 80: {
                            n = n3 - paddingBottom - child.getMeasuredHeight();
                            paddingBottom += child.getMeasuredHeight();
                            break;
                        }
                    }
                    i += scrollX;
                    child.layout(i, n, child.getMeasuredWidth() + i, child.getMeasuredHeight() + n);
                    n4 = mDecorChildCount + 1;
                    i = paddingTop;
                    n7 = paddingRight;
                    n5 = paddingBottom;
                }
            }
        }
        View child2;
        LayoutParams layoutParams2;
        ItemInfo infoForChild;
        int n8;
        for (i = 0; i < childCount; ++i) {
            child2 = this.getChildAt(i);
            if (child2.getVisibility() != 8) {
                layoutParams2 = (LayoutParams)child2.getLayoutParams();
                if (!layoutParams2.isDecor) {
                    infoForChild = this.infoForChild(child2);
                    if (infoForChild != null) {
                        n8 = n + (int)(n2 * infoForChild.offset);
                        if (layoutParams2.needsMeasure) {
                            layoutParams2.needsMeasure = false;
                            child2.measure(View$MeasureSpec.makeMeasureSpec((int)((n2 - n - paddingRight) * layoutParams2.widthFactor), 1073741824), View$MeasureSpec.makeMeasureSpec(n3 - paddingTop - paddingBottom, 1073741824));
                        }
                        child2.layout(n8, paddingTop, child2.getMeasuredWidth() + n8, child2.getMeasuredHeight() + paddingTop);
                    }
                }
            }
        }
        this.mTopPageBounds = paddingTop;
        this.mBottomPageBounds = n3 - paddingBottom;
        this.mDecorChildCount = mDecorChildCount;
        this.mFirstLayout = false;
    }
    
    protected void onMeasure(int measuredWidth, int i) {
        this.setMeasuredDimension(getDefaultSize(0, measuredWidth), getDefaultSize(0, i));
        measuredWidth = this.getMeasuredWidth();
        this.mGutterSize = Math.min(measuredWidth / 10, this.mDefaultGutterSize);
        measuredWidth = measuredWidth - this.getPaddingLeft() - this.getPaddingRight();
        i = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom();
        int n;
        int n2;
        for (int childCount = this.getChildCount(), j = 0; j < childCount; ++j, i = n, measuredWidth = n2) {
            final View child = this.getChildAt(j);
            n = i;
            n2 = measuredWidth;
            if (child.getVisibility() != 8) {
                final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
                n = i;
                n2 = measuredWidth;
                if (layoutParams != null) {
                    n = i;
                    n2 = measuredWidth;
                    if (layoutParams.isDecor) {
                        final int n3 = layoutParams.gravity & 0x7;
                        final int n4 = layoutParams.gravity & 0x70;
                        final int n5 = Integer.MIN_VALUE;
                        int n6 = Integer.MIN_VALUE;
                        boolean b;
                        if (n4 == 48 || n4 == 80) {
                            b = true;
                        }
                        else {
                            b = false;
                        }
                        final boolean b2 = n3 == 3 || n3 == 5;
                        int n7;
                        if (b) {
                            n7 = 1073741824;
                        }
                        else {
                            n7 = n5;
                            if (b2) {
                                n6 = 1073741824;
                                n7 = n5;
                            }
                        }
                        final int n8 = measuredWidth;
                        final int n9 = i;
                        int width = n8;
                        if (layoutParams.width != -2) {
                            n7 = 1073741824;
                            width = n8;
                            if (layoutParams.width != -1) {
                                width = layoutParams.width;
                                n7 = n7;
                            }
                        }
                        int height = n9;
                        if (layoutParams.height != -2) {
                            n6 = 1073741824;
                            height = n9;
                            if (layoutParams.height != -1) {
                                height = layoutParams.height;
                                n6 = n6;
                            }
                        }
                        child.measure(View$MeasureSpec.makeMeasureSpec(width, n7), View$MeasureSpec.makeMeasureSpec(height, n6));
                        if (b) {
                            n = i - child.getMeasuredHeight();
                            n2 = measuredWidth;
                        }
                        else {
                            n = i;
                            n2 = measuredWidth;
                            if (b2) {
                                n2 = measuredWidth - child.getMeasuredWidth();
                                n = i;
                            }
                        }
                    }
                }
            }
        }
        this.mChildWidthMeasureSpec = View$MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        this.mChildHeightMeasureSpec = View$MeasureSpec.makeMeasureSpec(i, 1073741824);
        this.mInLayout = true;
        this.populate();
        this.mInLayout = false;
        int childCount2;
        View child2;
        LayoutParams layoutParams2;
        for (childCount2 = this.getChildCount(), i = 0; i < childCount2; ++i) {
            child2 = this.getChildAt(i);
            if (child2.getVisibility() != 8) {
                layoutParams2 = (LayoutParams)child2.getLayoutParams();
                if (layoutParams2 == null || !layoutParams2.isDecor) {
                    child2.measure(View$MeasureSpec.makeMeasureSpec((int)(measuredWidth * layoutParams2.widthFactor), 1073741824), this.mChildHeightMeasureSpec);
                }
            }
        }
    }
    
    protected void onPageScrolled(int i, float n, int scrollX) {
        if (this.mDecorChildCount > 0) {
            final int scrollX2 = this.getScrollX();
            int paddingLeft = this.getPaddingLeft();
            int paddingRight = this.getPaddingRight();
            final int width = this.getWidth();
            int n2;
            int n3;
            for (int childCount = this.getChildCount(), j = 0; j < childCount; ++j, paddingLeft = n3, paddingRight = n2) {
                final View child = this.getChildAt(j);
                final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
                if (!layoutParams.isDecor) {
                    n2 = paddingRight;
                    n3 = paddingLeft;
                }
                else {
                    int max = 0;
                    switch (layoutParams.gravity & 0x7) {
                        default: {
                            max = paddingLeft;
                            break;
                        }
                        case 3: {
                            max = paddingLeft;
                            paddingLeft += child.getWidth();
                            break;
                        }
                        case 1: {
                            max = Math.max((width - child.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        }
                        case 5: {
                            max = width - paddingRight - child.getMeasuredWidth();
                            paddingRight += child.getMeasuredWidth();
                            break;
                        }
                    }
                    final int n4 = max + scrollX2 - child.getLeft();
                    n3 = paddingLeft;
                    n2 = paddingRight;
                    if (n4 != 0) {
                        child.offsetLeftAndRight(n4);
                        n3 = paddingLeft;
                        n2 = paddingRight;
                    }
                }
            }
        }
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(i, n, scrollX);
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrolled(i, n, scrollX);
        }
        if (this.mPageTransformer != null) {
            scrollX = this.getScrollX();
            int childCount2;
            View child2;
            for (childCount2 = this.getChildCount(), i = 0; i < childCount2; ++i) {
                child2 = this.getChildAt(i);
                if (!((LayoutParams)child2.getLayoutParams()).isDecor) {
                    n = (child2.getLeft() - scrollX) / this.getWidth();
                    this.mPageTransformer.transformPage(child2, n);
                }
            }
        }
        this.mCalledSuper = true;
    }
    
    protected boolean onRequestFocusInDescendants(final int n, final Rect rect) {
        int childCount = this.getChildCount();
        int i;
        int n2;
        if ((n & 0x2) != 0x0) {
            i = 0;
            n2 = 1;
        }
        else {
            i = childCount - 1;
            n2 = -1;
            childCount = -1;
        }
        while (i != childCount) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() == 0) {
                final ItemInfo infoForChild = this.infoForChild(child);
                if (infoForChild != null && infoForChild.position == this.mCurItem && child.requestFocus(n, rect)) {
                    return true;
                }
            }
            i += n2;
        }
        return false;
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (this.mAdapter != null) {
            this.mAdapter.restoreState(savedState.adapterState, savedState.loader);
            this.setCurrentItemInternal(savedState.position, false, true);
            return;
        }
        this.mRestoredCurItem = savedState.position;
        this.mRestoredAdapterState = savedState.adapterState;
        this.mRestoredClassLoader = savedState.loader;
    }
    
    public Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.position = this.mCurItem;
        if (this.mAdapter != null) {
            savedState.adapterState = this.mAdapter.saveState();
        }
        return (Parcelable)savedState;
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n != n3) {
            this.recomputeScrollPosition(n, n3, this.mPageMargin, this.mPageMargin);
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (this.mFakeDragging) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.mAdapter == null || this.mAdapter.getCount() == 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        final int action = motionEvent.getAction();
        boolean b2;
        final boolean b = b2 = false;
        while (true) {
            switch (action & 0xFF) {
                default: {
                    b2 = b;
                    break Label_0132;
                }
                case 6: {
                    this.onSecondaryPointerUp(motionEvent);
                    this.mLastMotionX = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId));
                    b2 = b;
                    break Label_0132;
                }
                case 5: {
                    final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                    this.mLastMotionX = MotionEventCompat.getX(motionEvent, actionIndex);
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    b2 = b;
                    break Label_0132;
                }
                case 0: {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = false;
                    this.populate();
                    this.mIsBeingDragged = true;
                    this.setScrollState(1);
                    final float x = motionEvent.getX();
                    this.mInitialMotionX = x;
                    this.mLastMotionX = x;
                    final float y = motionEvent.getY();
                    this.mInitialMotionY = y;
                    this.mLastMotionY = y;
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    b2 = b;
                }
                case 4: {
                    if (b2) {
                        ViewCompat.postInvalidateOnAnimation((View)this);
                    }
                    return true;
                }
                case 2: {
                    if (!this.mIsBeingDragged) {
                        final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                        final float x2 = MotionEventCompat.getX(motionEvent, pointerIndex);
                        final float abs = Math.abs(x2 - this.mLastMotionX);
                        final float y2 = MotionEventCompat.getY(motionEvent, pointerIndex);
                        final float abs2 = Math.abs(y2 - this.mLastMotionY);
                        if (abs > this.mTouchSlop && abs > abs2) {
                            this.mIsBeingDragged = true;
                            float mLastMotionX;
                            if (x2 - this.mInitialMotionX > 0.0f) {
                                mLastMotionX = this.mInitialMotionX + this.mTouchSlop;
                            }
                            else {
                                mLastMotionX = this.mInitialMotionX - this.mTouchSlop;
                            }
                            this.mLastMotionX = mLastMotionX;
                            this.mLastMotionY = y2;
                            this.setScrollState(1);
                            this.setScrollingCacheEnabled(true);
                        }
                    }
                    b2 = b;
                    if (this.mIsBeingDragged) {
                        b2 = (false | this.performDrag(MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId))));
                    }
                    continue;
                }
                case 1: {
                    b2 = b;
                    if (this.mIsBeingDragged) {
                        final VelocityTracker mVelocityTracker = this.mVelocityTracker;
                        mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaximumVelocity);
                        final int n = (int)VelocityTrackerCompat.getXVelocity(mVelocityTracker, this.mActivePointerId);
                        this.mPopulatePending = true;
                        final int width = this.getWidth();
                        final int scrollX = this.getScrollX();
                        final ItemInfo infoForCurrentScrollPosition = this.infoForCurrentScrollPosition();
                        this.setCurrentItemInternal(this.determineTargetPage(infoForCurrentScrollPosition.position, (scrollX / width - infoForCurrentScrollPosition.offset) / infoForCurrentScrollPosition.widthFactor, n, (int)(MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId)) - this.mInitialMotionX)), true, true, n, true);
                        this.mActivePointerId = -1;
                        this.endDrag();
                        b2 = (this.mLeftEdge.onRelease() | this.mRightEdge.onRelease());
                    }
                    continue;
                }
                case 3: {
                    b2 = b;
                    if (this.mIsBeingDragged) {
                        this.scrollToItem(this.mCurItem, true, 0, false);
                        this.mActivePointerId = -1;
                        this.endDrag();
                        b2 = (this.mLeftEdge.onRelease() | this.mRightEdge.onRelease());
                    }
                    continue;
                }
            }
            break;
        }
    }
    
    boolean pageLeft() {
        if (this.mCurItem > 0) {
            this.setCurrentItem(this.mCurItem - 1, true);
            return true;
        }
        return false;
    }
    
    boolean pageRight() {
        if (this.mAdapter != null && this.mCurItem < this.mAdapter.getCount() - 1) {
            this.setCurrentItem(this.mCurItem + 1, true);
            return true;
        }
        return false;
    }
    
    void populate() {
        this.populate(this.mCurItem);
    }
    
    void populate(int i) {
        ItemInfo infoForPosition = null;
        if (this.mCurItem != i) {
            infoForPosition = this.infoForPosition(this.mCurItem);
            this.mCurItem = i;
        }
        if (this.mAdapter != null && !this.mPopulatePending && this.getWindowToken() != null) {
            this.mAdapter.startUpdate(this);
            i = this.mOffscreenPageLimit;
            final int max = Math.max(0, this.mCurItem - i);
            final int count = this.mAdapter.getCount();
            final int min = Math.min(count - 1, this.mCurItem + i);
            final ItemInfo itemInfo = null;
            i = 0;
            ItemInfo itemInfo2;
            while (true) {
                itemInfo2 = itemInfo;
                if (i >= this.mItems.size()) {
                    break;
                }
                final ItemInfo itemInfo3 = this.mItems.get(i);
                if (itemInfo3.position >= this.mCurItem) {
                    itemInfo2 = itemInfo;
                    if (itemInfo3.position == this.mCurItem) {
                        itemInfo2 = itemInfo3;
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
            ItemInfo addNewItem;
            if ((addNewItem = itemInfo2) == null) {
                addNewItem = itemInfo2;
                if (count > 0) {
                    addNewItem = this.addNewItem(this.mCurItem, i);
                }
            }
            if (addNewItem != null) {
                float n = 0.0f;
                int n2 = i - 1;
                ItemInfo itemInfo4;
                if (n2 >= 0) {
                    itemInfo4 = this.mItems.get(n2);
                }
                else {
                    itemInfo4 = null;
                }
                final float widthFactor = addNewItem.widthFactor;
                int j = this.mCurItem - 1;
                ItemInfo itemInfo5 = itemInfo4;
                int n3 = i;
                while (j >= 0) {
                    float n4;
                    ItemInfo itemInfo6;
                    int n5;
                    if (n >= 2.0f - widthFactor && j < max) {
                        if (itemInfo5 == null) {
                            break;
                        }
                        i = n3;
                        n4 = n;
                        itemInfo6 = itemInfo5;
                        n5 = n2;
                        if (j == itemInfo5.position) {
                            i = n3;
                            n4 = n;
                            itemInfo6 = itemInfo5;
                            n5 = n2;
                            if (!itemInfo5.scrolling) {
                                this.mItems.remove(n2);
                                this.mAdapter.destroyItem(this, j, itemInfo5.object);
                                n5 = n2 - 1;
                                i = n3 - 1;
                                if (n5 >= 0) {
                                    itemInfo6 = this.mItems.get(n5);
                                    n4 = n;
                                }
                                else {
                                    itemInfo6 = null;
                                    n4 = n;
                                }
                            }
                        }
                    }
                    else if (itemInfo5 != null && j == itemInfo5.position) {
                        n4 = n + itemInfo5.widthFactor;
                        n5 = n2 - 1;
                        if (n5 >= 0) {
                            itemInfo6 = this.mItems.get(n5);
                        }
                        else {
                            itemInfo6 = null;
                        }
                        i = n3;
                    }
                    else {
                        n4 = n + this.addNewItem(j, n2 + 1).widthFactor;
                        i = n3 + 1;
                        if (n2 >= 0) {
                            itemInfo6 = this.mItems.get(n2);
                        }
                        else {
                            itemInfo6 = null;
                        }
                        n5 = n2;
                    }
                    --j;
                    n3 = i;
                    n = n4;
                    itemInfo5 = itemInfo6;
                    n2 = n5;
                }
                float widthFactor2 = addNewItem.widthFactor;
                int n6 = n3 + 1;
                if (widthFactor2 < 2.0f) {
                    ItemInfo itemInfo7;
                    if (n6 < this.mItems.size()) {
                        itemInfo7 = this.mItems.get(n6);
                    }
                    else {
                        itemInfo7 = null;
                    }
                    int k = this.mCurItem + 1;
                    ItemInfo itemInfo8 = itemInfo7;
                    while (k < count) {
                        float n7;
                        ItemInfo itemInfo9;
                        if (widthFactor2 >= 2.0f && k > min) {
                            if (itemInfo8 == null) {
                                break;
                            }
                            n7 = widthFactor2;
                            itemInfo9 = itemInfo8;
                            i = n6;
                            if (k == itemInfo8.position) {
                                n7 = widthFactor2;
                                itemInfo9 = itemInfo8;
                                i = n6;
                                if (!itemInfo8.scrolling) {
                                    this.mItems.remove(n6);
                                    this.mAdapter.destroyItem(this, k, itemInfo8.object);
                                    if (n6 < this.mItems.size()) {
                                        itemInfo9 = this.mItems.get(n6);
                                        i = n6;
                                        n7 = widthFactor2;
                                    }
                                    else {
                                        itemInfo9 = null;
                                        n7 = widthFactor2;
                                        i = n6;
                                    }
                                }
                            }
                        }
                        else if (itemInfo8 != null && k == itemInfo8.position) {
                            n7 = widthFactor2 + itemInfo8.widthFactor;
                            i = n6 + 1;
                            if (i < this.mItems.size()) {
                                itemInfo9 = this.mItems.get(i);
                            }
                            else {
                                itemInfo9 = null;
                            }
                        }
                        else {
                            final ItemInfo addNewItem2 = this.addNewItem(k, n6);
                            i = n6 + 1;
                            n7 = widthFactor2 + addNewItem2.widthFactor;
                            if (i < this.mItems.size()) {
                                itemInfo9 = this.mItems.get(i);
                            }
                            else {
                                itemInfo9 = null;
                            }
                        }
                        ++k;
                        widthFactor2 = n7;
                        itemInfo8 = itemInfo9;
                        n6 = i;
                    }
                }
                this.calculatePageOffsets(addNewItem, n3, infoForPosition);
            }
            final PagerAdapter mAdapter = this.mAdapter;
            i = this.mCurItem;
            Object object;
            if (addNewItem != null) {
                object = addNewItem.object;
            }
            else {
                object = null;
            }
            mAdapter.setPrimaryItem(this, i, object);
            this.mAdapter.finishUpdate(this);
            if (this.mDrawingOrder != 0) {
                i = 1;
            }
            else {
                i = 0;
            }
            if (i != 0) {
                if (this.mDrawingOrderedChildren == null) {
                    this.mDrawingOrderedChildren = new ArrayList<View>();
                }
                else {
                    this.mDrawingOrderedChildren.clear();
                }
            }
            for (int childCount = this.getChildCount(), l = 0; l < childCount; ++l) {
                final View child = this.getChildAt(l);
                final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
                layoutParams.childIndex = l;
                if (!layoutParams.isDecor && layoutParams.widthFactor == 0.0f) {
                    final ItemInfo infoForChild = this.infoForChild(child);
                    if (infoForChild != null) {
                        layoutParams.widthFactor = infoForChild.widthFactor;
                        layoutParams.position = infoForChild.position;
                    }
                }
                if (i != 0) {
                    this.mDrawingOrderedChildren.add(child);
                }
            }
            if (i != 0) {
                Collections.sort(this.mDrawingOrderedChildren, ViewPager.sPositionComparator);
            }
            if (this.hasFocus()) {
                final View focus = this.findFocus();
                ItemInfo infoForAnyChild;
                if (focus != null) {
                    infoForAnyChild = this.infoForAnyChild(focus);
                }
                else {
                    infoForAnyChild = null;
                }
                if (infoForAnyChild == null || infoForAnyChild.position != this.mCurItem) {
                    View child2;
                    ItemInfo infoForChild2;
                    for (i = 0; i < this.getChildCount(); ++i) {
                        child2 = this.getChildAt(i);
                        infoForChild2 = this.infoForChild(child2);
                        if (infoForChild2 != null && infoForChild2.position == this.mCurItem && child2.requestFocus(2)) {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public void removeView(final View view) {
        if (this.mInLayout) {
            this.removeViewInLayout(view);
            return;
        }
        super.removeView(view);
    }
    
    public void setAdapter(final PagerAdapter mAdapter) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
            this.mAdapter.startUpdate(this);
            for (int i = 0; i < this.mItems.size(); ++i) {
                final ItemInfo itemInfo = this.mItems.get(i);
                this.mAdapter.destroyItem(this, itemInfo.position, itemInfo.object);
            }
            this.mAdapter.finishUpdate(this);
            this.mItems.clear();
            this.removeNonDecorViews();
            this.scrollTo(this.mCurItem = 0, 0);
        }
        final PagerAdapter mAdapter2 = this.mAdapter;
        this.mAdapter = mAdapter;
        if (this.mAdapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new PagerObserver();
            }
            this.mAdapter.registerDataSetObserver(this.mObserver);
            this.mPopulatePending = false;
            this.mFirstLayout = true;
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                this.setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            }
            else {
                this.populate();
            }
        }
        if (this.mAdapterChangeListener != null && mAdapter2 != mAdapter) {
            this.mAdapterChangeListener.onAdapterChanged(mAdapter2, mAdapter);
        }
    }
    
    void setChildrenDrawingOrderEnabledCompat(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/viewpagerindicator/android/osp/ViewPager.mSetChildrenDrawingOrderEnabled:Ljava/lang/reflect/Method;
        //     4: ifnonnull       29
        //     7: aload_0        
        //     8: ldc             Landroid/view/ViewGroup;.class
        //    10: ldc_w           "setChildrenDrawingOrderEnabled"
        //    13: iconst_1       
        //    14: anewarray       Ljava/lang/Class;
        //    17: dup            
        //    18: iconst_0       
        //    19: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    22: aastore        
        //    23: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    26: putfield        com/viewpagerindicator/android/osp/ViewPager.mSetChildrenDrawingOrderEnabled:Ljava/lang/reflect/Method;
        //    29: aload_0        
        //    30: getfield        com/viewpagerindicator/android/osp/ViewPager.mSetChildrenDrawingOrderEnabled:Ljava/lang/reflect/Method;
        //    33: aload_0        
        //    34: iconst_1       
        //    35: anewarray       Ljava/lang/Object;
        //    38: dup            
        //    39: iconst_0       
        //    40: iload_1        
        //    41: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    44: aastore        
        //    45: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    48: pop            
        //    49: return         
        //    50: astore_2       
        //    51: ldc             "ViewPager"
        //    53: ldc_w           "Can't find setChildrenDrawingOrderEnabled"
        //    56: aload_2        
        //    57: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    60: pop            
        //    61: goto            29
        //    64: astore_2       
        //    65: ldc             "ViewPager"
        //    67: ldc_w           "Error changing children drawing order"
        //    70: aload_2        
        //    71: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    74: pop            
        //    75: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  7      29     50     64     Ljava/lang/NoSuchMethodException;
        //  29     49     64     76     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
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
    
    public void setCurrentItem(final int n) {
        this.mPopulatePending = false;
        this.setCurrentItemInternal(n, !this.mFirstLayout, false);
    }
    
    public void setCurrentItem(final int n, final boolean b) {
        this.setCurrentItem(n, b, true);
    }
    
    public void setCurrentItem(final int n, final boolean b, final boolean b2) {
        this.mPopulatePending = false;
        this.setCurrentItemInternal(n, b, true, b2);
    }
    
    void setCurrentItemInternal(final int n, final boolean b, final boolean b2) {
        this.setCurrentItemInternal(n, b, b2, false);
    }
    
    void setCurrentItemInternal(int i, final boolean b, final boolean b2, final int n, final boolean b3) {
        final boolean b4 = true;
        if (this.mAdapter == null || this.mAdapter.getCount() <= 0) {
            this.setScrollingCacheEnabled(false);
            return;
        }
        if (!b2 && this.mCurItem == i && this.mItems.size() != 0) {
            this.setScrollingCacheEnabled(false);
            return;
        }
        int n2;
        if (i < 0) {
            n2 = 0;
        }
        else if ((n2 = i) >= this.mAdapter.getCount()) {
            n2 = this.mAdapter.getCount() - 1;
        }
        i = this.mOffscreenPageLimit;
        if (n2 > this.mCurItem + i || n2 < this.mCurItem - i) {
            for (i = 0; i < this.mItems.size(); ++i) {
                this.mItems.get(i).scrolling = true;
            }
        }
        final boolean b5 = this.mCurItem != n2 && b3 && b4;
        this.populate(n2);
        this.scrollToItem(n2, b, n, b5);
    }
    
    void setCurrentItemInternal(final int n, final boolean b, final boolean b2, final boolean b3) {
        this.setCurrentItemInternal(n, b, b2, 0, b3);
    }
    
    OnPageChangeListener setInternalPageChangeListener(final OnPageChangeListener mInternalPageChangeListener) {
        final OnPageChangeListener mInternalPageChangeListener2 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = mInternalPageChangeListener;
        return mInternalPageChangeListener2;
    }
    
    public void setOffscreenPageLimit(final int n) {
        int mOffscreenPageLimit = n;
        if (n < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + n + " too small; defaulting to " + 1);
            mOffscreenPageLimit = 1;
        }
        if (mOffscreenPageLimit != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = mOffscreenPageLimit;
            this.populate();
        }
    }
    
    void setOnAdapterChangeListener(final OnAdapterChangeListener mAdapterChangeListener) {
        this.mAdapterChangeListener = mAdapterChangeListener;
    }
    
    public void setOnPageChangeListener(final OnPageChangeListener mOnPageChangeListener) {
        this.mOnPageChangeListener = mOnPageChangeListener;
    }
    
    public void setPageMargin(final int mPageMargin) {
        final int mPageMargin2 = this.mPageMargin;
        this.mPageMargin = mPageMargin;
        final int width = this.getWidth();
        this.recomputeScrollPosition(width, width, mPageMargin, mPageMargin2);
        this.requestLayout();
    }
    
    public void setPageMarginDrawable(final int n) {
        this.setPageMarginDrawable(this.getContext().getResources().getDrawable(n));
    }
    
    public void setPageMarginDrawable(final Drawable mMarginDrawable) {
        this.mMarginDrawable = mMarginDrawable;
        if (mMarginDrawable != null) {
            this.refreshDrawableState();
        }
        this.setWillNotDraw(mMarginDrawable == null);
        this.invalidate();
    }
    
    public void setPageTransformer(final boolean b, final PageTransformer mPageTransformer) {
        int mDrawingOrder = 1;
        final boolean childrenDrawingOrderEnabledCompat = mPageTransformer != null;
        int n;
        if (childrenDrawingOrderEnabledCompat != (this.mPageTransformer != null)) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.mPageTransformer = mPageTransformer;
        this.setChildrenDrawingOrderEnabledCompat(childrenDrawingOrderEnabledCompat);
        if (childrenDrawingOrderEnabledCompat) {
            if (b) {
                mDrawingOrder = 2;
            }
            this.mDrawingOrder = mDrawingOrder;
        }
        else {
            this.mDrawingOrder = 0;
        }
        if (n != 0) {
            this.populate();
        }
    }
    
    void smoothScrollTo(final int n, final int n2) {
        this.smoothScrollTo(n, n2, 0);
    }
    
    void smoothScrollTo(int n, int n2, int abs) {
        if (this.getChildCount() == 0) {
            this.setScrollingCacheEnabled(false);
            return;
        }
        final int scrollX = this.getScrollX();
        final int scrollY = this.getScrollY();
        final int n3 = n - scrollX;
        n2 -= scrollY;
        if (n3 == 0 && n2 == 0) {
            this.completeScroll(false);
            this.populate();
            this.setScrollState(0);
            return;
        }
        this.setScrollingCacheEnabled(true);
        this.setScrollState(2);
        n = this.getWidth();
        final int n4 = n / 2;
        final float min = Math.min(1.0f, 1.0f * Math.abs(n3) / n);
        final float n5 = n4;
        final float n6 = n4;
        final float distanceInfluenceForSnapDuration = this.distanceInfluenceForSnapDuration(min);
        abs = Math.abs(abs);
        if (abs > 0) {
            n = Math.round(1000.0f * Math.abs((n5 + n6 * distanceInfluenceForSnapDuration) / abs)) * 4;
        }
        else {
            n = (int)((1.0f + Math.abs(n3) / (this.mPageMargin + n * this.mAdapter.getPageWidth(this.mCurItem))) * 100.0f);
        }
        n = Math.min(n, 400);
        this.mScroller.startScroll(scrollX, scrollY, n3, n2, n);
        ViewCompat.postInvalidateOnAnimation((View)this);
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mMarginDrawable;
    }
    
    interface Decor
    {
    }
    
    static class ItemInfo
    {
        Object object;
        float offset;
        int position;
        boolean scrolling;
        float widthFactor;
    }
    
    public static class LayoutParams extends ViewGroup$LayoutParams
    {
        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor;
        
        public LayoutParams() {
            super(-1, -1);
            this.widthFactor = 0.0f;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.widthFactor = 0.0f;
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, ViewPager.LAYOUT_ATTRS);
            this.gravity = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }
    
    class MyAccessibilityDelegate extends AccessibilityDelegateCompat
    {
        @Override
        public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)ViewPager.class.getName());
        }
        
        @Override
        public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            boolean scrollable = true;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
            if (ViewPager.this.mAdapter == null || ViewPager.this.mAdapter.getCount() <= 1) {
                scrollable = false;
            }
            accessibilityNodeInfoCompat.setScrollable(scrollable);
            if (ViewPager.this.mAdapter != null && ViewPager.this.mCurItem >= 0 && ViewPager.this.mCurItem < ViewPager.this.mAdapter.getCount() - 1) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (ViewPager.this.mAdapter != null && ViewPager.this.mCurItem > 0 && ViewPager.this.mCurItem < ViewPager.this.mAdapter.getCount()) {
                accessibilityNodeInfoCompat.addAction(8192);
            }
        }
        
        @Override
        public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
            if (super.performAccessibilityAction(view, n, bundle)) {
                return true;
            }
            switch (n) {
                default: {
                    return false;
                }
                case 4096: {
                    if (ViewPager.this.mAdapter != null && ViewPager.this.mCurItem >= 0 && ViewPager.this.mCurItem < ViewPager.this.mAdapter.getCount() - 1) {
                        ViewPager.this.setCurrentItem(ViewPager.this.mCurItem + 1);
                        return true;
                    }
                    return false;
                }
                case 8192: {
                    if (ViewPager.this.mAdapter != null && ViewPager.this.mCurItem > 0 && ViewPager.this.mCurItem < ViewPager.this.mAdapter.getCount()) {
                        ViewPager.this.setCurrentItem(ViewPager.this.mCurItem - 1);
                        return true;
                    }
                    return false;
                }
            }
        }
    }
    
    interface OnAdapterChangeListener
    {
        void onAdapterChanged(final PagerAdapter p0, final PagerAdapter p1);
    }
    
    public interface OnPageChangeListener
    {
        void onPageScrollStateChanged(final int p0);
        
        void onPageScrolled(final int p0, final float p1, final int p2);
        
        void onPageSelected(final int p0);
    }
    
    public interface PageTransformer
    {
        void transformPage(final View p0, final float p1);
    }
    
    private class PagerObserver extends DataSetObserver
    {
        public void onChanged() {
            ViewPager.this.dataSetChanged();
        }
        
        public void onInvalidated() {
            ViewPager.this.dataSetChanged();
        }
    }
    
    public static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        Parcelable adapterState;
        ClassLoader loader;
        int position;
        
        static {
            CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SavedState>)new ParcelableCompatCreatorCallbacks<SavedState>() {
                @Override
                public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                    return new SavedState(parcel, classLoader);
                }
                
                @Override
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            });
        }
        
        SavedState(final Parcel parcel, final ClassLoader classLoader) {
            super(parcel);
            ClassLoader classLoader2 = classLoader;
            if (classLoader == null) {
                classLoader2 = this.getClass().getClassLoader();
            }
            this.position = parcel.readInt();
            this.adapterState = parcel.readParcelable(classLoader2);
            this.loader = classLoader2;
        }
        
        public SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.position + "}";
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeInt(this.position);
            parcel.writeParcelable(this.adapterState, n);
        }
    }
    
    public static class SimpleOnPageChangeListener implements OnPageChangeListener
    {
        @Override
        public void onPageScrollStateChanged(final int n) {
        }
        
        @Override
        public void onPageScrolled(final int n, final float n2, final int n3) {
        }
        
        @Override
        public void onPageSelected(final int n) {
        }
    }
    
    static class ViewPositionComparator implements Comparator<View>
    {
        @Override
        public int compare(final View view, final View view2) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final LayoutParams layoutParams2 = (LayoutParams)view2.getLayoutParams();
            if (layoutParams.isDecor == layoutParams2.isDecor) {
                return layoutParams.position - layoutParams2.position;
            }
            if (layoutParams.isDecor) {
                return 1;
            }
            return -1;
        }
    }
}
