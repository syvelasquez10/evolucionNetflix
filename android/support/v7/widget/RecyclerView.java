// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import java.util.Collections;
import android.util.SparseArray;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.View$MeasureSpec;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.os.Parcelable;
import android.view.ViewParent;
import android.view.FocusFinder;
import android.graphics.Canvas;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.Rect;
import java.util.ArrayList;
import android.view.View;
import java.util.List;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.view.ViewGroup;

public class RecyclerView extends ViewGroup
{
    private static final boolean DEBUG = false;
    private static final boolean DISPATCH_TEMP_DETACH = false;
    private static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
    public static final int HORIZONTAL = 0;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final int MAX_SCROLL_DURATION = 2000;
    public static final long NO_ID = -1L;
    public static final int NO_POSITION = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    public static final int VERTICAL = 1;
    private static final Interpolator sQuinticInterpolator;
    private RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private RecyclerView$OnItemTouchListener mActiveOnItemTouchListener;
    private RecyclerView$Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    private boolean mAdapterUpdateDuringMeasure;
    private EdgeEffectCompat mBottomGlow;
    ChildHelper mChildHelper;
    private boolean mClipToPadding;
    private boolean mDataSetHasChangedAfterLayout;
    final List<View> mDisappearingViewsInLayoutPass;
    private boolean mEatRequestLayout;
    private boolean mFirstLayoutComplete;
    private boolean mHasFixedSize;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private boolean mIsAttached;
    RecyclerView$ItemAnimator mItemAnimator;
    private RecyclerView$ItemAnimator$ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    private final ArrayList<RecyclerView$ItemDecoration> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    private RecyclerView$LayoutManager mLayout;
    private boolean mLayoutRequestEaten;
    private EdgeEffectCompat mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final RecyclerView$RecyclerViewDataObserver mObserver;
    private final ArrayList<RecyclerView$OnItemTouchListener> mOnItemTouchListeners;
    private RecyclerView$SavedState mPendingSavedState;
    private final boolean mPostUpdatesOnAnimation;
    private boolean mPostedAnimatorRunner;
    final RecyclerView$Recycler mRecycler;
    private RecyclerView$RecyclerListener mRecyclerListener;
    private EdgeEffectCompat mRightGlow;
    private boolean mRunningLayoutOrScroll;
    private RecyclerView$OnScrollListener mScrollListener;
    private int mScrollPointerId;
    private int mScrollState;
    final RecyclerView$State mState;
    private final Rect mTempRect;
    private EdgeEffectCompat mTopGlow;
    private int mTouchSlop;
    private final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    private final RecyclerView$ViewFlinger mViewFlinger;
    
    static {
        FORCE_INVALIDATE_DISPLAY_LIST = (Build$VERSION.SDK_INT == 19 || Build$VERSION.SDK_INT == 20);
        sQuinticInterpolator = (Interpolator)new RecyclerView$3();
    }
    
    public RecyclerView(final Context context) {
        this(context, null);
    }
    
    public RecyclerView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public RecyclerView(final Context context, final AttributeSet set, final int n) {
        final boolean b = false;
        super(context, set, n);
        this.mObserver = new RecyclerView$RecyclerViewDataObserver(this, null);
        this.mRecycler = new RecyclerView$Recycler(this);
        this.mDisappearingViewsInLayoutPass = new ArrayList<View>();
        this.mUpdateChildViewsRunnable = new RecyclerView$1(this);
        this.mTempRect = new Rect();
        this.mItemDecorations = new ArrayList<RecyclerView$ItemDecoration>();
        this.mOnItemTouchListeners = new ArrayList<RecyclerView$OnItemTouchListener>();
        this.mDataSetHasChangedAfterLayout = false;
        this.mRunningLayoutOrScroll = false;
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mViewFlinger = new RecyclerView$ViewFlinger(this);
        this.mState = new RecyclerView$State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new RecyclerView$ItemAnimatorRestoreListener(this, null);
        this.mPostedAnimatorRunner = false;
        this.mItemAnimatorRunner = new RecyclerView$2(this);
        this.mPostUpdatesOnAnimation = (Build$VERSION.SDK_INT >= 16);
        final ViewConfiguration value = ViewConfiguration.get(context);
        this.mTouchSlop = value.getScaledTouchSlop();
        this.mMinFlingVelocity = value.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = value.getScaledMaximumFlingVelocity();
        boolean willNotDraw = b;
        if (ViewCompat.getOverScrollMode((View)this) == 2) {
            willNotDraw = true;
        }
        this.setWillNotDraw(willNotDraw);
        this.mItemAnimator.setListener(this.mItemAnimatorListener);
        this.initAdapterManager();
        this.initChildrenHelper();
        if (ViewCompat.getImportantForAccessibility((View)this) == 0) {
            ViewCompat.setImportantForAccessibility((View)this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
        this.setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
    }
    
    private void addAnimatingView(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final View itemView = recyclerView$ViewHolder.itemView;
        final boolean b = itemView.getParent() == this;
        this.mRecycler.unscrapView(this.getChildViewHolder(itemView));
        if (recyclerView$ViewHolder.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(itemView, -1, itemView.getLayoutParams(), true);
            return;
        }
        if (!b) {
            this.mChildHelper.addView(itemView, true);
            return;
        }
        this.mChildHelper.hide(itemView);
    }
    
    private void addToDisappearingList(final View view) {
        if (!this.mDisappearingViewsInLayoutPass.contains(view)) {
            this.mDisappearingViewsInLayoutPass.add(view);
        }
    }
    
    private void animateAppearance(final RecyclerView$ViewHolder recyclerView$ViewHolder, final Rect rect, final int n, final int n2) {
        final View itemView = recyclerView$ViewHolder.itemView;
        if (rect != null && (rect.left != n || rect.top != n2)) {
            recyclerView$ViewHolder.setIsRecyclable(false);
            if (this.mItemAnimator.animateMove(recyclerView$ViewHolder, rect.left, rect.top, n, n2)) {
                this.postAnimationRunner();
            }
        }
        else {
            recyclerView$ViewHolder.setIsRecyclable(false);
            if (this.mItemAnimator.animateAdd(recyclerView$ViewHolder)) {
                this.postAnimationRunner();
            }
        }
    }
    
    private void animateChange(final RecyclerView$ViewHolder mShadowingHolder, final RecyclerView$ViewHolder mShadowedHolder) {
        mShadowingHolder.setIsRecyclable(false);
        this.addAnimatingView(mShadowingHolder);
        mShadowingHolder.mShadowedHolder = mShadowedHolder;
        this.mRecycler.unscrapView(mShadowingHolder);
        final int left = mShadowingHolder.itemView.getLeft();
        final int top = mShadowingHolder.itemView.getTop();
        int top2;
        int left2;
        if (mShadowedHolder == null || mShadowedHolder.shouldIgnore()) {
            top2 = top;
            left2 = left;
        }
        else {
            left2 = mShadowedHolder.itemView.getLeft();
            top2 = mShadowedHolder.itemView.getTop();
            mShadowedHolder.setIsRecyclable(false);
            mShadowedHolder.mShadowingHolder = mShadowingHolder;
        }
        if (this.mItemAnimator.animateChange(mShadowingHolder, mShadowedHolder, left, top, left2, top2)) {
            this.postAnimationRunner();
        }
    }
    
    private void animateDisappearance(final RecyclerView$ItemHolderInfo recyclerView$ItemHolderInfo) {
        final View itemView = recyclerView$ItemHolderInfo.holder.itemView;
        this.addAnimatingView(recyclerView$ItemHolderInfo.holder);
        final int left = recyclerView$ItemHolderInfo.left;
        final int top = recyclerView$ItemHolderInfo.top;
        final int left2 = itemView.getLeft();
        final int top2 = itemView.getTop();
        if (left != left2 || top != top2) {
            recyclerView$ItemHolderInfo.holder.setIsRecyclable(false);
            itemView.layout(left2, top2, itemView.getWidth() + left2, itemView.getHeight() + top2);
            if (this.mItemAnimator.animateMove(recyclerView$ItemHolderInfo.holder, left, top, left2, top2)) {
                this.postAnimationRunner();
            }
        }
        else {
            recyclerView$ItemHolderInfo.holder.setIsRecyclable(false);
            if (this.mItemAnimator.animateRemove(recyclerView$ItemHolderInfo.holder)) {
                this.postAnimationRunner();
            }
        }
    }
    
    private void cancelTouch() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.clear();
        }
        this.releaseGlows();
        this.setScrollState(0);
    }
    
    private void considerReleasingGlowsOnScroll(final int n, final int n2) {
        boolean onRelease;
        final boolean b = onRelease = false;
        if (this.mLeftGlow != null) {
            onRelease = b;
            if (!this.mLeftGlow.isFinished()) {
                onRelease = b;
                if (n > 0) {
                    onRelease = this.mLeftGlow.onRelease();
                }
            }
        }
        boolean b2 = onRelease;
        if (this.mRightGlow != null) {
            b2 = onRelease;
            if (!this.mRightGlow.isFinished()) {
                b2 = onRelease;
                if (n < 0) {
                    b2 = (onRelease | this.mRightGlow.onRelease());
                }
            }
        }
        boolean b3 = b2;
        if (this.mTopGlow != null) {
            b3 = b2;
            if (!this.mTopGlow.isFinished()) {
                b3 = b2;
                if (n2 > 0) {
                    b3 = (b2 | this.mTopGlow.onRelease());
                }
            }
        }
        boolean b4 = b3;
        if (this.mBottomGlow != null) {
            b4 = b3;
            if (!this.mBottomGlow.isFinished()) {
                b4 = b3;
                if (n2 < 0) {
                    b4 = (b3 | this.mBottomGlow.onRelease());
                }
            }
        }
        if (b4) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    private void consumePendingUpdateOperations() {
        this.mUpdateChildViewsRunnable.run();
    }
    
    private void dispatchChildAttached(final View view) {
        if (this.mAdapter != null) {
            this.mAdapter.onViewAttachedToWindow(getChildViewHolderInt(view));
        }
        this.onChildAttachedToWindow(view);
    }
    
    private void dispatchChildDetached(final View view) {
        if (this.mAdapter != null) {
            this.mAdapter.onViewDetachedFromWindow(getChildViewHolderInt(view));
        }
        this.onChildDetachedFromWindow(view);
    }
    
    private boolean dispatchOnItemTouch(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (this.mActiveOnItemTouchListener != null) {
            if (action != 0) {
                this.mActiveOnItemTouchListener.onTouchEvent(this, motionEvent);
                if (action == 3 || action == 1) {
                    this.mActiveOnItemTouchListener = null;
                }
                return true;
            }
            this.mActiveOnItemTouchListener = null;
        }
        if (action != 0) {
            for (int size = this.mOnItemTouchListeners.size(), i = 0; i < size; ++i) {
                final RecyclerView$OnItemTouchListener mActiveOnItemTouchListener = this.mOnItemTouchListeners.get(i);
                if (mActiveOnItemTouchListener.onInterceptTouchEvent(this, motionEvent)) {
                    this.mActiveOnItemTouchListener = mActiveOnItemTouchListener;
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dispatchOnItemTouchIntercept(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.mActiveOnItemTouchListener = null;
        }
        for (int size = this.mOnItemTouchListeners.size(), i = 0; i < size; ++i) {
            final RecyclerView$OnItemTouchListener mActiveOnItemTouchListener = this.mOnItemTouchListeners.get(i);
            if (mActiveOnItemTouchListener.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.mActiveOnItemTouchListener = mActiveOnItemTouchListener;
                return true;
            }
        }
        return false;
    }
    
    static RecyclerView$ViewHolder getChildViewHolderInt(final View view) {
        if (view == null) {
            return null;
        }
        return ((RecyclerView$LayoutParams)view.getLayoutParams()).mViewHolder;
    }
    
    private void initChildrenHelper() {
        this.mChildHelper = new ChildHelper(new RecyclerView$4(this));
    }
    
    private void onPointerUp(final MotionEvent motionEvent) {
        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mScrollPointerId) {
            int n;
            if (actionIndex == 0) {
                n = 1;
            }
            else {
                n = 0;
            }
            this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, n);
            final int n2 = (int)(MotionEventCompat.getX(motionEvent, n) + 0.5f);
            this.mLastTouchX = n2;
            this.mInitialTouchX = n2;
            final int n3 = (int)(MotionEventCompat.getY(motionEvent, n) + 0.5f);
            this.mLastTouchY = n3;
            this.mInitialTouchY = n3;
        }
    }
    
    private void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation((View)this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }
    
    private boolean predictiveItemAnimationsEnabled() {
        return this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations();
    }
    
    private void processAdapterUpdatesAndSetAnimationFlags() {
        final boolean b = true;
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.reset();
            this.markKnownViewsInvalid();
            this.mLayout.onItemsChanged(this);
        }
        if (this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations()) {
            this.mAdapterHelper.preProcess();
        }
        else {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        }
        boolean b2;
        if ((this.mItemsAddedOrRemoved && !this.mItemsChanged) || this.mItemsAddedOrRemoved || (this.mItemsChanged && this.supportsChangeAnimations())) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.mState.mRunSimpleAnimations = (this.mFirstLayoutComplete && this.mItemAnimator != null && (this.mDataSetHasChangedAfterLayout || b2 || this.mLayout.mRequestedSimpleAnimations) && (!this.mDataSetHasChangedAfterLayout || this.mAdapter.hasStableIds()));
        this.mState.mRunPredictiveAnimations = (this.mState.mRunSimpleAnimations && b2 && !this.mDataSetHasChangedAfterLayout && this.predictiveItemAnimationsEnabled() && b);
    }
    
    private void processDisappearingList(final ArrayMap<View, Rect> arrayMap) {
        for (int size = this.mDisappearingViewsInLayoutPass.size(), i = 0; i < size; ++i) {
            final View view = this.mDisappearingViewsInLayoutPass.get(i);
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            final RecyclerView$ItemHolderInfo recyclerView$ItemHolderInfo = this.mState.mPreLayoutHolderMap.remove(childViewHolderInt);
            if (!this.mState.isPreLayout()) {
                this.mState.mPostLayoutHolderMap.remove(childViewHolderInt);
            }
            if (arrayMap.remove(view) != null) {
                this.mLayout.removeAndRecycleView(view, this.mRecycler);
            }
            else if (recyclerView$ItemHolderInfo != null) {
                this.animateDisappearance(recyclerView$ItemHolderInfo);
            }
            else {
                this.animateDisappearance(new RecyclerView$ItemHolderInfo(childViewHolderInt, view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            }
        }
        this.mDisappearingViewsInLayoutPass.clear();
    }
    
    private void pullGlows(final int n, final int n2) {
        if (n < 0) {
            this.ensureLeftGlow();
            this.mLeftGlow.onPull(-n / this.getWidth());
        }
        else if (n > 0) {
            this.ensureRightGlow();
            this.mRightGlow.onPull(n / this.getWidth());
        }
        if (n2 < 0) {
            this.ensureTopGlow();
            this.mTopGlow.onPull(-n2 / this.getHeight());
        }
        else if (n2 > 0) {
            this.ensureBottomGlow();
            this.mBottomGlow.onPull(n2 / this.getHeight());
        }
        if (n != 0 || n2 != 0) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    private void releaseGlows() {
        boolean onRelease = false;
        if (this.mLeftGlow != null) {
            onRelease = this.mLeftGlow.onRelease();
        }
        boolean b = onRelease;
        if (this.mTopGlow != null) {
            b = (onRelease | this.mTopGlow.onRelease());
        }
        boolean b2 = b;
        if (this.mRightGlow != null) {
            b2 = (b | this.mRightGlow.onRelease());
        }
        boolean b3 = b2;
        if (this.mBottomGlow != null) {
            b3 = (b2 | this.mBottomGlow.onRelease());
        }
        if (b3) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    private boolean removeAnimatingView(final View view) {
        this.eatRequestLayout();
        final boolean removeViewIfHidden = this.mChildHelper.removeViewIfHidden(view);
        if (removeViewIfHidden) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.unscrapView(childViewHolderInt);
            this.mRecycler.recycleViewHolderInternal(childViewHolderInt);
        }
        this.resumeRequestLayout(false);
        return removeViewIfHidden;
    }
    
    private void removeFromDisappearingList(final View view) {
        this.mDisappearingViewsInLayoutPass.remove(view);
    }
    
    private void setAdapterInternal(final RecyclerView$Adapter mAdapter, final boolean b, final boolean b2) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!b || b2) {
            if (this.mItemAnimator != null) {
                this.mItemAnimator.endAnimations();
            }
            if (this.mLayout != null) {
                this.mLayout.removeAndRecycleAllViews(this.mRecycler);
                this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            }
        }
        this.mAdapterHelper.reset();
        final RecyclerView$Adapter mAdapter2 = this.mAdapter;
        if ((this.mAdapter = mAdapter) != null) {
            mAdapter.registerAdapterDataObserver(this.mObserver);
            mAdapter.onAttachedToRecyclerView(this);
        }
        if (this.mLayout != null) {
            this.mLayout.onAdapterChanged(mAdapter2, this.mAdapter);
        }
        this.mRecycler.onAdapterChanged(mAdapter2, this.mAdapter, b);
        this.mState.mStructureChanged = true;
        this.markKnownViewsInvalid();
    }
    
    private void setScrollState(final int mScrollState) {
        if (mScrollState == this.mScrollState) {
            return;
        }
        if ((this.mScrollState = mScrollState) != 2) {
            this.stopScrollersInternal();
        }
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrollStateChanged(this, mScrollState);
        }
        this.mLayout.onScrollStateChanged(mScrollState);
    }
    
    private void stopScrollersInternal() {
        this.mViewFlinger.stop();
        this.mLayout.stopSmoothScroller();
    }
    
    private boolean supportsChangeAnimations() {
        return this.mItemAnimator != null && this.mItemAnimator.getSupportsChangeAnimations();
    }
    
    void absorbGlows(final int n, final int n2) {
        if (n < 0) {
            this.ensureLeftGlow();
            this.mLeftGlow.onAbsorb(-n);
        }
        else if (n > 0) {
            this.ensureRightGlow();
            this.mRightGlow.onAbsorb(n);
        }
        if (n2 < 0) {
            this.ensureTopGlow();
            this.mTopGlow.onAbsorb(-n2);
        }
        else if (n2 > 0) {
            this.ensureBottomGlow();
            this.mBottomGlow.onAbsorb(n2);
        }
        if (n != 0 || n2 != 0) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public void addFocusables(final ArrayList<View> list, final int n, final int n2) {
        if (!this.mLayout.onAddFocusables(this, list, n, n2)) {
            super.addFocusables((ArrayList)list, n, n2);
        }
    }
    
    public void addItemDecoration(final RecyclerView$ItemDecoration recyclerView$ItemDecoration) {
        this.addItemDecoration(recyclerView$ItemDecoration, -1);
    }
    
    public void addItemDecoration(final RecyclerView$ItemDecoration recyclerView$ItemDecoration, final int n) {
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            this.setWillNotDraw(false);
        }
        if (n < 0) {
            this.mItemDecorations.add(recyclerView$ItemDecoration);
        }
        else {
            this.mItemDecorations.add(n, recyclerView$ItemDecoration);
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }
    
    public void addOnItemTouchListener(final RecyclerView$OnItemTouchListener recyclerView$OnItemTouchListener) {
        this.mOnItemTouchListeners.add(recyclerView$OnItemTouchListener);
    }
    
    void assertInLayoutOrScroll(final String s) {
        if (this.mRunningLayoutOrScroll) {
            return;
        }
        if (s == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(s);
    }
    
    void assertNotInLayoutOrScroll(final String s) {
        if (!this.mRunningLayoutOrScroll) {
            return;
        }
        if (s == null) {
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(s);
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof RecyclerView$LayoutParams && this.mLayout.checkLayoutParams((RecyclerView$LayoutParams)viewGroup$LayoutParams);
    }
    
    void clearOldPositions() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        this.mRecycler.clearOldPositions();
    }
    
    protected int computeHorizontalScrollExtent() {
        if (this.mLayout.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollExtent(this.mState);
        }
        return 0;
    }
    
    protected int computeHorizontalScrollOffset() {
        if (this.mLayout.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollOffset(this.mState);
        }
        return 0;
    }
    
    protected int computeHorizontalScrollRange() {
        if (this.mLayout.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollRange(this.mState);
        }
        return 0;
    }
    
    protected int computeVerticalScrollExtent() {
        if (this.mLayout.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollExtent(this.mState);
        }
        return 0;
    }
    
    protected int computeVerticalScrollOffset() {
        if (this.mLayout.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollOffset(this.mState);
        }
        return 0;
    }
    
    protected int computeVerticalScrollRange() {
        if (this.mLayout.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollRange(this.mState);
        }
        return 0;
    }
    
    void dispatchLayout() {
        if (this.mAdapter == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        this.mDisappearingViewsInLayoutPass.clear();
        this.eatRequestLayout();
        this.mRunningLayoutOrScroll = true;
        this.processAdapterUpdatesAndSetAnimationFlags();
        final RecyclerView$State mState = this.mState;
        ArrayMap<Long, RecyclerView$ViewHolder> mOldChangedHolders;
        if (this.mState.mRunSimpleAnimations && this.mItemsChanged && this.supportsChangeAnimations()) {
            mOldChangedHolders = new ArrayMap<Long, RecyclerView$ViewHolder>();
        }
        else {
            mOldChangedHolders = null;
        }
        mState.mOldChangedHolders = mOldChangedHolders;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        this.mState.mInPreLayout = this.mState.mRunPredictiveAnimations;
        this.mState.mItemCount = this.mAdapter.getItemCount();
        if (this.mState.mRunSimpleAnimations) {
            this.mState.mPreLayoutHolderMap.clear();
            this.mState.mPostLayoutHolderMap.clear();
            for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
                final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                    final View itemView = childViewHolderInt.itemView;
                    this.mState.mPreLayoutHolderMap.put(childViewHolderInt, new RecyclerView$ItemHolderInfo(childViewHolderInt, itemView.getLeft(), itemView.getTop(), itemView.getRight(), itemView.getBottom()));
                }
            }
        }
        Object o;
        if (this.mState.mRunPredictiveAnimations) {
            this.saveOldPositions();
            if (this.mState.mOldChangedHolders != null) {
                for (int childCount2 = this.mChildHelper.getChildCount(), j = 0; j < childCount2; ++j) {
                    final RecyclerView$ViewHolder childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.getChildAt(j));
                    if (childViewHolderInt2.isChanged() && !childViewHolderInt2.isRemoved() && !childViewHolderInt2.shouldIgnore()) {
                        this.mState.mOldChangedHolders.put(this.getChangedHolderKey(childViewHolderInt2), childViewHolderInt2);
                        this.mState.mPreLayoutHolderMap.remove(childViewHolderInt2);
                    }
                }
            }
            final boolean access$1200 = this.mState.mStructureChanged;
            this.mState.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
            this.mState.mStructureChanged = access$1200;
            o = new ArrayMap<Object, Rect>();
        Label_0479:
            for (int k = 0; k < this.mChildHelper.getChildCount(); ++k) {
                final View child = this.mChildHelper.getChildAt(k);
                if (!getChildViewHolderInt(child).shouldIgnore()) {
                    int l = 0;
                    while (true) {
                        while (l < this.mState.mPreLayoutHolderMap.size()) {
                            if (((RecyclerView$ViewHolder)this.mState.mPreLayoutHolderMap.keyAt(l)).itemView == child) {
                                final int n = 1;
                                if (n == 0) {
                                    ((SimpleArrayMap<View, Rect>)o).put(child, new Rect(child.getLeft(), child.getTop(), child.getRight(), child.getBottom()));
                                }
                                continue Label_0479;
                            }
                            else {
                                ++l;
                            }
                        }
                        final int n = 0;
                        continue;
                    }
                }
            }
            this.clearOldPositions();
            this.mAdapterHelper.consumePostponedUpdates();
        }
        else {
            this.clearOldPositions();
            this.mAdapterHelper.consumeUpdatesInOnePass();
            if (this.mState.mOldChangedHolders != null) {
                for (int childCount3 = this.mChildHelper.getChildCount(), n2 = 0; n2 < childCount3; ++n2) {
                    final RecyclerView$ViewHolder childViewHolderInt3 = getChildViewHolderInt(this.mChildHelper.getChildAt(n2));
                    if (childViewHolderInt3.isChanged() && !childViewHolderInt3.isRemoved() && !childViewHolderInt3.shouldIgnore()) {
                        this.mState.mOldChangedHolders.put(this.getChangedHolderKey(childViewHolderInt3), childViewHolderInt3);
                        this.mState.mPreLayoutHolderMap.remove(childViewHolderInt3);
                    }
                }
            }
            o = null;
        }
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        this.mState.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
        this.mState.mStructureChanged = false;
        this.mPendingSavedState = null;
        this.mState.mRunSimpleAnimations = (this.mState.mRunSimpleAnimations && this.mItemAnimator != null);
        if (this.mState.mRunSimpleAnimations) {
            SimpleArrayMap<Long, RecyclerView$ViewHolder> simpleArrayMap;
            if (this.mState.mOldChangedHolders != null) {
                simpleArrayMap = new ArrayMap<Long, RecyclerView$ViewHolder>();
            }
            else {
                simpleArrayMap = null;
            }
            for (int childCount4 = this.mChildHelper.getChildCount(), n3 = 0; n3 < childCount4; ++n3) {
                final RecyclerView$ViewHolder childViewHolderInt4 = getChildViewHolderInt(this.mChildHelper.getChildAt(n3));
                if (!childViewHolderInt4.shouldIgnore()) {
                    final View itemView2 = childViewHolderInt4.itemView;
                    final long changedHolderKey = this.getChangedHolderKey(childViewHolderInt4);
                    if (simpleArrayMap != null && this.mState.mOldChangedHolders.get(changedHolderKey) != null) {
                        simpleArrayMap.put(changedHolderKey, childViewHolderInt4);
                    }
                    else {
                        this.mState.mPostLayoutHolderMap.put(childViewHolderInt4, new RecyclerView$ItemHolderInfo(childViewHolderInt4, itemView2.getLeft(), itemView2.getTop(), itemView2.getRight(), itemView2.getBottom()));
                    }
                }
            }
            this.processDisappearingList((ArrayMap<View, Rect>)o);
            for (int n4 = this.mState.mPreLayoutHolderMap.size() - 1; n4 >= 0; --n4) {
                if (!this.mState.mPostLayoutHolderMap.containsKey(this.mState.mPreLayoutHolderMap.keyAt(n4))) {
                    final RecyclerView$ItemHolderInfo recyclerView$ItemHolderInfo = this.mState.mPreLayoutHolderMap.valueAt(n4);
                    this.mState.mPreLayoutHolderMap.removeAt(n4);
                    final View itemView3 = recyclerView$ItemHolderInfo.holder.itemView;
                    this.mRecycler.unscrapView(recyclerView$ItemHolderInfo.holder);
                    this.animateDisappearance(recyclerView$ItemHolderInfo);
                }
            }
            final int size = this.mState.mPostLayoutHolderMap.size();
            if (size > 0) {
                for (int n5 = size - 1; n5 >= 0; --n5) {
                    final RecyclerView$ViewHolder recyclerView$ViewHolder = this.mState.mPostLayoutHolderMap.keyAt(n5);
                    final RecyclerView$ItemHolderInfo recyclerView$ItemHolderInfo2 = this.mState.mPostLayoutHolderMap.valueAt(n5);
                    if (this.mState.mPreLayoutHolderMap.isEmpty() || !this.mState.mPreLayoutHolderMap.containsKey(recyclerView$ViewHolder)) {
                        this.mState.mPostLayoutHolderMap.removeAt(n5);
                        Rect rect;
                        if (o != null) {
                            rect = ((SimpleArrayMap<Object, Rect>)o).get(recyclerView$ViewHolder.itemView);
                        }
                        else {
                            rect = null;
                        }
                        this.animateAppearance(recyclerView$ViewHolder, rect, recyclerView$ItemHolderInfo2.left, recyclerView$ItemHolderInfo2.top);
                    }
                }
            }
            for (int size2 = this.mState.mPostLayoutHolderMap.size(), n6 = 0; n6 < size2; ++n6) {
                final RecyclerView$ViewHolder recyclerView$ViewHolder2 = this.mState.mPostLayoutHolderMap.keyAt(n6);
                final RecyclerView$ItemHolderInfo recyclerView$ItemHolderInfo3 = this.mState.mPostLayoutHolderMap.valueAt(n6);
                final RecyclerView$ItemHolderInfo recyclerView$ItemHolderInfo4 = this.mState.mPreLayoutHolderMap.get(recyclerView$ViewHolder2);
                if (recyclerView$ItemHolderInfo4 != null && recyclerView$ItemHolderInfo3 != null && (recyclerView$ItemHolderInfo4.left != recyclerView$ItemHolderInfo3.left || recyclerView$ItemHolderInfo4.top != recyclerView$ItemHolderInfo3.top)) {
                    recyclerView$ViewHolder2.setIsRecyclable(false);
                    if (this.mItemAnimator.animateMove(recyclerView$ViewHolder2, recyclerView$ItemHolderInfo4.left, recyclerView$ItemHolderInfo4.top, recyclerView$ItemHolderInfo3.left, recyclerView$ItemHolderInfo3.top)) {
                        this.postAnimationRunner();
                    }
                }
            }
            int size3;
            if (this.mState.mOldChangedHolders != null) {
                size3 = this.mState.mOldChangedHolders.size();
            }
            else {
                size3 = 0;
            }
            for (int n7 = size3 - 1; n7 >= 0; --n7) {
                final long longValue = this.mState.mOldChangedHolders.keyAt(n7);
                final RecyclerView$ViewHolder recyclerView$ViewHolder3 = this.mState.mOldChangedHolders.get(longValue);
                final View itemView4 = recyclerView$ViewHolder3.itemView;
                if (!recyclerView$ViewHolder3.shouldIgnore() && this.mRecycler.mChangedScrap != null && this.mRecycler.mChangedScrap.contains(recyclerView$ViewHolder3)) {
                    this.animateChange(recyclerView$ViewHolder3, simpleArrayMap.get(longValue));
                }
            }
        }
        this.resumeRequestLayout(false);
        this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        this.mState.mPreviousLayoutItemCount = this.mState.mItemCount;
        this.mDataSetHasChangedAfterLayout = false;
        this.mState.mRunSimpleAnimations = false;
        this.mState.mRunPredictiveAnimations = false;
        this.mRunningLayoutOrScroll = false;
        this.mLayout.mRequestedSimpleAnimations = false;
        if (this.mRecycler.mChangedScrap != null) {
            this.mRecycler.mChangedScrap.clear();
        }
        this.mState.mOldChangedHolders = null;
    }
    
    public void draw(final Canvas canvas) {
        final boolean b = true;
        final boolean b2 = false;
        super.draw(canvas);
        for (int size = this.mItemDecorations.size(), i = 0; i < size; ++i) {
            this.mItemDecorations.get(i).onDrawOver(canvas, this, this.mState);
        }
        boolean b3;
        if (this.mLeftGlow != null && !this.mLeftGlow.isFinished()) {
            final int save = canvas.save();
            int paddingBottom;
            if (this.mClipToPadding) {
                paddingBottom = this.getPaddingBottom();
            }
            else {
                paddingBottom = 0;
            }
            canvas.rotate(270.0f);
            canvas.translate((float)(paddingBottom + -this.getHeight()), 0.0f);
            b3 = (this.mLeftGlow != null && this.mLeftGlow.draw(canvas) && true);
            canvas.restoreToCount(save);
        }
        else {
            b3 = false;
        }
        boolean b4 = b3;
        if (this.mTopGlow != null) {
            b4 = b3;
            if (!this.mTopGlow.isFinished()) {
                final int save2 = canvas.save();
                if (this.mClipToPadding) {
                    canvas.translate((float)this.getPaddingLeft(), (float)this.getPaddingTop());
                }
                b4 = (b3 | (this.mTopGlow != null && this.mTopGlow.draw(canvas) && true));
                canvas.restoreToCount(save2);
            }
        }
        boolean b5 = b4;
        if (this.mRightGlow != null) {
            b5 = b4;
            if (!this.mRightGlow.isFinished()) {
                final int save3 = canvas.save();
                final int width = this.getWidth();
                int paddingTop;
                if (this.mClipToPadding) {
                    paddingTop = this.getPaddingTop();
                }
                else {
                    paddingTop = 0;
                }
                canvas.rotate(90.0f);
                canvas.translate((float)(-paddingTop), (float)(-width));
                b5 = (b4 | (this.mRightGlow != null && this.mRightGlow.draw(canvas) && true));
                canvas.restoreToCount(save3);
            }
        }
        boolean b6 = b5;
        if (this.mBottomGlow != null) {
            b6 = b5;
            if (!this.mBottomGlow.isFinished()) {
                final int save4 = canvas.save();
                canvas.rotate(180.0f);
                if (this.mClipToPadding) {
                    canvas.translate((float)(-this.getWidth() + this.getPaddingRight()), (float)(-this.getHeight() + this.getPaddingBottom()));
                }
                else {
                    canvas.translate((float)(-this.getWidth()), (float)(-this.getHeight()));
                }
                boolean b7 = b2;
                if (this.mBottomGlow != null) {
                    b7 = b2;
                    if (this.mBottomGlow.draw(canvas)) {
                        b7 = true;
                    }
                }
                b6 = (b5 | b7);
                canvas.restoreToCount(save4);
            }
        }
        if (!b6 && this.mItemAnimator != null && this.mItemDecorations.size() > 0 && this.mItemAnimator.isRunning()) {
            b6 = b;
        }
        if (b6) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    void eatRequestLayout() {
        if (!this.mEatRequestLayout) {
            this.mEatRequestLayout = true;
            this.mLayoutRequestEaten = false;
        }
    }
    
    void ensureBottomGlow() {
        if (this.mBottomGlow != null) {
            return;
        }
        this.mBottomGlow = new EdgeEffectCompat(this.getContext());
        if (this.mClipToPadding) {
            this.mBottomGlow.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
            return;
        }
        this.mBottomGlow.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
    }
    
    void ensureLeftGlow() {
        if (this.mLeftGlow != null) {
            return;
        }
        this.mLeftGlow = new EdgeEffectCompat(this.getContext());
        if (this.mClipToPadding) {
            this.mLeftGlow.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
            return;
        }
        this.mLeftGlow.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
    }
    
    void ensureRightGlow() {
        if (this.mRightGlow != null) {
            return;
        }
        this.mRightGlow = new EdgeEffectCompat(this.getContext());
        if (this.mClipToPadding) {
            this.mRightGlow.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
            return;
        }
        this.mRightGlow.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
    }
    
    void ensureTopGlow() {
        if (this.mTopGlow != null) {
            return;
        }
        this.mTopGlow = new EdgeEffectCompat(this.getContext());
        if (this.mClipToPadding) {
            this.mTopGlow.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
            return;
        }
        this.mTopGlow.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
    }
    
    public View findChildViewUnder(final float n, final float n2) {
        for (int i = this.mChildHelper.getChildCount() - 1; i >= 0; --i) {
            final View child = this.mChildHelper.getChildAt(i);
            final float translationX = ViewCompat.getTranslationX(child);
            final float translationY = ViewCompat.getTranslationY(child);
            if (n >= child.getLeft() + translationX && n <= translationX + child.getRight() && n2 >= child.getTop() + translationY && n2 <= child.getBottom() + translationY) {
                return child;
            }
        }
        return null;
    }
    
    public RecyclerView$ViewHolder findViewHolderForItemId(final long n) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && childViewHolderInt.getItemId() == n) {
                return childViewHolderInt;
            }
        }
        return null;
    }
    
    public RecyclerView$ViewHolder findViewHolderForPosition(final int n) {
        return this.findViewHolderForPosition(n, false);
    }
    
    RecyclerView$ViewHolder findViewHolderForPosition(final int n, final boolean b) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved()) {
                if (b) {
                    if (childViewHolderInt.mPosition != n) {
                        continue;
                    }
                }
                else if (childViewHolderInt.getPosition() != n) {
                    continue;
                }
                return childViewHolderInt;
            }
        }
        return null;
    }
    
    public boolean fling(int max, int max2) {
        boolean b = false;
        int n = max;
        if (Math.abs(max) < this.mMinFlingVelocity) {
            n = 0;
        }
        max = max2;
        if (Math.abs(max2) < this.mMinFlingVelocity) {
            max = 0;
        }
        max2 = Math.max(-this.mMaxFlingVelocity, Math.min(n, this.mMaxFlingVelocity));
        max = Math.max(-this.mMaxFlingVelocity, Math.min(max, this.mMaxFlingVelocity));
        if (max2 != 0 || max != 0) {
            this.mViewFlinger.fling(max2, max);
            b = true;
        }
        return b;
    }
    
    public View focusSearch(final View view, final int n) {
        View onInterceptFocusSearch = this.mLayout.onInterceptFocusSearch(view, n);
        if (onInterceptFocusSearch == null) {
            final View nextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup)this, view, n);
            View onFocusSearchFailed;
            if ((onFocusSearchFailed = nextFocus) == null) {
                onFocusSearchFailed = nextFocus;
                if (this.mAdapter != null) {
                    this.eatRequestLayout();
                    onFocusSearchFailed = this.mLayout.onFocusSearchFailed(view, n, this.mRecycler, this.mState);
                    this.resumeRequestLayout(false);
                }
            }
            if ((onInterceptFocusSearch = onFocusSearchFailed) == null) {
                return super.focusSearch(view, n);
            }
        }
        return onInterceptFocusSearch;
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        }
        return (ViewGroup$LayoutParams)this.mLayout.generateDefaultLayoutParams();
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        }
        return (ViewGroup$LayoutParams)this.mLayout.generateLayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        }
        return (ViewGroup$LayoutParams)this.mLayout.generateLayoutParams(viewGroup$LayoutParams);
    }
    
    public RecyclerView$Adapter getAdapter() {
        return this.mAdapter;
    }
    
    long getChangedHolderKey(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        if (this.mAdapter.hasStableIds()) {
            return recyclerView$ViewHolder.getItemId();
        }
        return recyclerView$ViewHolder.mPosition;
    }
    
    public long getChildItemId(final View view) {
        if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                return childViewHolderInt.getItemId();
            }
        }
        return -1L;
    }
    
    public int getChildPosition(final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getPosition();
        }
        return -1;
    }
    
    public RecyclerView$ViewHolder getChildViewHolder(final View view) {
        final ViewParent parent = view.getParent();
        if (parent != null && parent != this) {
            throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
        }
        return getChildViewHolderInt(view);
    }
    
    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }
    
    public RecyclerView$ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }
    
    Rect getItemDecorInsetsForChild(final View view) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        if (!recyclerView$LayoutParams.mInsetsDirty) {
            return recyclerView$LayoutParams.mDecorInsets;
        }
        final Rect mDecorInsets = recyclerView$LayoutParams.mDecorInsets;
        mDecorInsets.set(0, 0, 0, 0);
        for (int size = this.mItemDecorations.size(), i = 0; i < size; ++i) {
            this.mTempRect.set(0, 0, 0, 0);
            this.mItemDecorations.get(i).getItemOffsets(this.mTempRect, view, this, this.mState);
            mDecorInsets.left += this.mTempRect.left;
            mDecorInsets.top += this.mTempRect.top;
            mDecorInsets.right += this.mTempRect.right;
            mDecorInsets.bottom += this.mTempRect.bottom;
        }
        recyclerView$LayoutParams.mInsetsDirty = false;
        return mDecorInsets;
    }
    
    public RecyclerView$LayoutManager getLayoutManager() {
        return this.mLayout;
    }
    
    public RecyclerView$RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.getRecycledViewPool();
    }
    
    public int getScrollState() {
        return this.mScrollState;
    }
    
    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }
    
    void initAdapterManager() {
        this.mAdapterHelper = new AdapterHelper(new RecyclerView$5(this));
    }
    
    void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }
    
    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() == 0) {
            return;
        }
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }
    
    void markItemDecorInsetsDirty() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            ((RecyclerView$LayoutParams)this.mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }
        this.mRecycler.markItemDecorInsetsDirty();
    }
    
    void markKnownViewsInvalid() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        this.markItemDecorInsetsDirty();
        this.mRecycler.markKnownViewsInvalid();
    }
    
    public void offsetChildrenHorizontal(final int n) {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            this.mChildHelper.getChildAt(i).offsetLeftAndRight(n);
        }
    }
    
    public void offsetChildrenVertical(final int n) {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            this.mChildHelper.getChildAt(i).offsetTopAndBottom(n);
        }
    }
    
    void offsetPositionRecordsForInsert(final int n, final int n2) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= n) {
                childViewHolderInt.offsetPosition(n2, false);
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForInsert(n, n2);
        this.requestLayout();
    }
    
    void offsetPositionRecordsForMove(final int n, final int n2) {
        final int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        int n3;
        int n4;
        int n5;
        if (n < n2) {
            n3 = -1;
            n4 = n2;
            n5 = n;
        }
        else {
            n3 = 1;
            n4 = n;
            n5 = n2;
        }
        for (int i = 0; i < unfilteredChildCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && childViewHolderInt.mPosition >= n5 && childViewHolderInt.mPosition <= n4) {
                if (childViewHolderInt.mPosition == n) {
                    childViewHolderInt.offsetPosition(n2 - n, false);
                }
                else {
                    childViewHolderInt.offsetPosition(n3, false);
                }
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForMove(n, n2);
        this.requestLayout();
    }
    
    void offsetPositionRecordsForRemove(final int n, final int n2, final boolean b) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                if (childViewHolderInt.mPosition >= n + n2) {
                    childViewHolderInt.offsetPosition(-n2, b);
                    this.mState.mStructureChanged = true;
                }
                else if (childViewHolderInt.mPosition >= n) {
                    childViewHolderInt.flagRemovedAndOffsetPosition(n - 1, -n2, b);
                    this.mState.mStructureChanged = true;
                }
            }
        }
        this.mRecycler.offsetPositionRecordsForRemove(n, n2, b);
        this.requestLayout();
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttached = true;
        this.mFirstLayoutComplete = false;
        if (this.mLayout != null) {
            this.mLayout.onAttachedToWindow(this);
        }
        this.mPostedAnimatorRunner = false;
    }
    
    public void onChildAttachedToWindow(final View view) {
    }
    
    public void onChildDetachedFromWindow(final View view) {
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
        }
        this.mFirstLayoutComplete = false;
        this.stopScroll();
        this.mIsAttached = false;
        if (this.mLayout != null) {
            this.mLayout.onDetachedFromWindow(this, this.mRecycler);
        }
        this.removeCallbacks(this.mItemAnimatorRunner);
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        for (int size = this.mItemDecorations.size(), i = 0; i < size; ++i) {
            this.mItemDecorations.get(i).onDraw(canvas, this, this.mState);
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final int n = -1;
        if (this.dispatchOnItemTouchIntercept(motionEvent)) {
            this.cancelTouch();
        }
        else {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
            switch (actionMasked) {
                case 0: {
                    this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    final int n2 = (int)(motionEvent.getX() + 0.5f);
                    this.mLastTouchX = n2;
                    this.mInitialTouchX = n2;
                    final int n3 = (int)(motionEvent.getY() + 0.5f);
                    this.mLastTouchY = n3;
                    this.mInitialTouchY = n3;
                    if (this.mScrollState == 2) {
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                        this.setScrollState(1);
                        break;
                    }
                    break;
                }
                case 5: {
                    this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    final int n4 = (int)(MotionEventCompat.getX(motionEvent, actionIndex) + 0.5f);
                    this.mLastTouchX = n4;
                    this.mInitialTouchX = n4;
                    final int n5 = (int)(MotionEventCompat.getY(motionEvent, actionIndex) + 0.5f);
                    this.mLastTouchY = n5;
                    this.mInitialTouchY = n5;
                    break;
                }
                case 2: {
                    final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mScrollPointerId);
                    if (pointerIndex < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                        return false;
                    }
                    final int n6 = (int)(MotionEventCompat.getX(motionEvent, pointerIndex) + 0.5f);
                    final int n7 = (int)(MotionEventCompat.getY(motionEvent, pointerIndex) + 0.5f);
                    if (this.mScrollState == 1) {
                        break;
                    }
                    final int n8 = n6 - this.mInitialTouchX;
                    final int n9 = n7 - this.mInitialTouchY;
                    boolean b;
                    if (canScrollHorizontally && Math.abs(n8) > this.mTouchSlop) {
                        final int mInitialTouchX = this.mInitialTouchX;
                        final int mTouchSlop = this.mTouchSlop;
                        int n10;
                        if (n8 < 0) {
                            n10 = -1;
                        }
                        else {
                            n10 = 1;
                        }
                        this.mLastTouchX = n10 * mTouchSlop + mInitialTouchX;
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    int n11 = b ? 1 : 0;
                    if (canScrollVertically) {
                        n11 = (b ? 1 : 0);
                        if (Math.abs(n9) > this.mTouchSlop) {
                            final int mInitialTouchY = this.mInitialTouchY;
                            final int mTouchSlop2 = this.mTouchSlop;
                            int n12;
                            if (n9 < 0) {
                                n12 = n;
                            }
                            else {
                                n12 = 1;
                            }
                            this.mLastTouchY = mInitialTouchY + n12 * mTouchSlop2;
                            n11 = 1;
                        }
                    }
                    if (n11 != 0) {
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                        this.setScrollState(1);
                        break;
                    }
                    break;
                }
                case 6: {
                    this.onPointerUp(motionEvent);
                    break;
                }
                case 1: {
                    this.mVelocityTracker.clear();
                    break;
                }
                case 3: {
                    this.cancelTouch();
                    break;
                }
            }
            if (this.mScrollState != 1) {
                return false;
            }
        }
        return true;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.eatRequestLayout();
        this.dispatchLayout();
        this.resumeRequestLayout(false);
        this.mFirstLayoutComplete = true;
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (this.mAdapterUpdateDuringMeasure) {
            this.eatRequestLayout();
            this.processAdapterUpdatesAndSetAnimationFlags();
            if (this.mState.mRunPredictiveAnimations) {
                this.mState.mInPreLayout = true;
            }
            else {
                this.mAdapterHelper.consumeUpdatesInOnePass();
                this.mState.mInPreLayout = false;
            }
            this.resumeRequestLayout(this.mAdapterUpdateDuringMeasure = false);
        }
        if (this.mAdapter != null) {
            this.mState.mItemCount = this.mAdapter.getItemCount();
        }
        else {
            this.mState.mItemCount = 0;
        }
        this.mLayout.onMeasure(this.mRecycler, this.mState, n, n2);
        this.mState.mInPreLayout = false;
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        this.mPendingSavedState = (RecyclerView$SavedState)parcelable;
        super.onRestoreInstanceState(this.mPendingSavedState.getSuperState());
        if (this.mLayout != null && this.mPendingSavedState.mLayoutState != null) {
            this.mLayout.onRestoreInstanceState(this.mPendingSavedState.mLayoutState);
        }
    }
    
    protected Parcelable onSaveInstanceState() {
        final RecyclerView$SavedState recyclerView$SavedState = new RecyclerView$SavedState(super.onSaveInstanceState());
        if (this.mPendingSavedState != null) {
            recyclerView$SavedState.copyFrom(this.mPendingSavedState);
            return (Parcelable)recyclerView$SavedState;
        }
        if (this.mLayout != null) {
            recyclerView$SavedState.mLayoutState = this.mLayout.onSaveInstanceState();
            return (Parcelable)recyclerView$SavedState;
        }
        recyclerView$SavedState.mLayoutState = null;
        return (Parcelable)recyclerView$SavedState;
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n != n3 || n2 != n4) {
            this.invalidateGlows();
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int n = -1;
        if (this.dispatchOnItemTouch(motionEvent)) {
            this.cancelTouch();
            return true;
        }
        final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
        final boolean canScrollVertically = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        switch (actionMasked) {
            default: {
                return true;
            }
            case 0: {
                this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                final int n2 = (int)(motionEvent.getX() + 0.5f);
                this.mLastTouchX = n2;
                this.mInitialTouchX = n2;
                final int n3 = (int)(motionEvent.getY() + 0.5f);
                this.mLastTouchY = n3;
                this.mInitialTouchY = n3;
                return true;
            }
            case 5: {
                this.mScrollPointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                final int n4 = (int)(MotionEventCompat.getX(motionEvent, actionIndex) + 0.5f);
                this.mLastTouchX = n4;
                this.mInitialTouchX = n4;
                final int n5 = (int)(MotionEventCompat.getY(motionEvent, actionIndex) + 0.5f);
                this.mLastTouchY = n5;
                this.mInitialTouchY = n5;
                return true;
            }
            case 2: {
                final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mScrollPointerId);
                if (pointerIndex < 0) {
                    Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                    return false;
                }
                final int mLastTouchX = (int)(MotionEventCompat.getX(motionEvent, pointerIndex) + 0.5f);
                final int mLastTouchY = (int)(MotionEventCompat.getY(motionEvent, pointerIndex) + 0.5f);
                if (this.mScrollState != 1) {
                    final int n6 = mLastTouchX - this.mInitialTouchX;
                    final int n7 = mLastTouchY - this.mInitialTouchY;
                    boolean b;
                    if (canScrollHorizontally && Math.abs(n6) > this.mTouchSlop) {
                        final int mInitialTouchX = this.mInitialTouchX;
                        final int mTouchSlop = this.mTouchSlop;
                        int n8;
                        if (n6 < 0) {
                            n8 = -1;
                        }
                        else {
                            n8 = 1;
                        }
                        this.mLastTouchX = n8 * mTouchSlop + mInitialTouchX;
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    int n9 = b ? 1 : 0;
                    if (canScrollVertically) {
                        n9 = (b ? 1 : 0);
                        if (Math.abs(n7) > this.mTouchSlop) {
                            final int mInitialTouchY = this.mInitialTouchY;
                            final int mTouchSlop2 = this.mTouchSlop;
                            int n10;
                            if (n7 < 0) {
                                n10 = n;
                            }
                            else {
                                n10 = 1;
                            }
                            this.mLastTouchY = mInitialTouchY + n10 * mTouchSlop2;
                            n9 = 1;
                        }
                    }
                    if (n9 != 0) {
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                        this.setScrollState(1);
                    }
                }
                if (this.mScrollState == 1) {
                    final int mLastTouchX2 = this.mLastTouchX;
                    final int mLastTouchY2 = this.mLastTouchY;
                    int n11;
                    if (canScrollHorizontally) {
                        n11 = -(mLastTouchX - mLastTouchX2);
                    }
                    else {
                        n11 = 0;
                    }
                    int n12;
                    if (canScrollVertically) {
                        n12 = -(mLastTouchY - mLastTouchY2);
                    }
                    else {
                        n12 = 0;
                    }
                    this.scrollByInternal(n11, n12);
                }
                this.mLastTouchX = mLastTouchX;
                this.mLastTouchY = mLastTouchY;
                return true;
            }
            case 6: {
                this.onPointerUp(motionEvent);
                return true;
            }
            case 1: {
                this.mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaxFlingVelocity);
                float n13;
                if (canScrollHorizontally) {
                    n13 = -VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mScrollPointerId);
                }
                else {
                    n13 = 0.0f;
                }
                float n14;
                if (canScrollVertically) {
                    n14 = -VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mScrollPointerId);
                }
                else {
                    n14 = 0.0f;
                }
                if ((n13 == 0.0f && n14 == 0.0f) || !this.fling((int)n13, (int)n14)) {
                    this.setScrollState(0);
                }
                this.mVelocityTracker.clear();
                this.releaseGlows();
                return true;
            }
            case 3: {
                this.cancelTouch();
                return true;
            }
        }
    }
    
    void rebindUpdatedViewHolders() {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                if (childViewHolderInt.isRemoved() || childViewHolderInt.isInvalid()) {
                    this.requestLayout();
                }
                else if (childViewHolderInt.needsUpdate()) {
                    if (childViewHolderInt.getItemViewType() == this.mAdapter.getItemViewType(childViewHolderInt.mPosition)) {
                        if (!childViewHolderInt.isChanged() || !this.supportsChangeAnimations()) {
                            this.mAdapter.bindViewHolder(childViewHolderInt, childViewHolderInt.mPosition);
                        }
                        else {
                            this.requestLayout();
                        }
                    }
                    else {
                        childViewHolderInt.addFlags(4);
                        this.requestLayout();
                    }
                }
            }
        }
    }
    
    protected void removeDetachedView(final View view, final boolean b) {
        final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            }
            else if (!childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + childViewHolderInt);
            }
        }
        this.dispatchChildDetached(view);
        super.removeDetachedView(view, b);
    }
    
    public void removeItemDecoration(final RecyclerView$ItemDecoration recyclerView$ItemDecoration) {
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(recyclerView$ItemDecoration);
        if (this.mItemDecorations.isEmpty()) {
            this.setWillNotDraw(ViewCompat.getOverScrollMode((View)this) == 2);
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }
    
    public void removeOnItemTouchListener(final RecyclerView$OnItemTouchListener recyclerView$OnItemTouchListener) {
        this.mOnItemTouchListeners.remove(recyclerView$OnItemTouchListener);
        if (this.mActiveOnItemTouchListener == recyclerView$OnItemTouchListener) {
            this.mActiveOnItemTouchListener = null;
        }
    }
    
    public void requestChildFocus(final View view, final View view2) {
        boolean b = false;
        if (!this.mLayout.onRequestChildFocus(this, this.mState, view, view2) && view2 != null) {
            this.mTempRect.set(0, 0, view2.getWidth(), view2.getHeight());
            this.offsetDescendantRectToMyCoords(view2, this.mTempRect);
            this.offsetRectIntoDescendantCoords(view, this.mTempRect);
            final Rect mTempRect = this.mTempRect;
            if (!this.mFirstLayoutComplete) {
                b = true;
            }
            this.requestChildRectangleOnScreen(view, mTempRect, b);
        }
        super.requestChildFocus(view, view2);
    }
    
    public boolean requestChildRectangleOnScreen(final View view, final Rect rect, final boolean b) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, b);
    }
    
    public void requestLayout() {
        if (!this.mEatRequestLayout) {
            super.requestLayout();
            return;
        }
        this.mLayoutRequestEaten = true;
    }
    
    void resumeRequestLayout(final boolean b) {
        if (this.mEatRequestLayout) {
            if (b && this.mLayoutRequestEaten && this.mLayout != null && this.mAdapter != null) {
                this.dispatchLayout();
            }
            this.mEatRequestLayout = false;
            this.mLayoutRequestEaten = false;
        }
    }
    
    void saveOldPositions() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }
    
    public void scrollBy(int n, int n2) {
        if (this.mLayout == null) {
            throw new IllegalStateException("Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        }
        final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
        final boolean canScrollVertically = this.mLayout.canScrollVertically();
        if (canScrollHorizontally || canScrollVertically) {
            if (!canScrollHorizontally) {
                n = 0;
            }
            if (!canScrollVertically) {
                n2 = 0;
            }
            this.scrollByInternal(n, n2);
        }
    }
    
    void scrollByInternal(final int n, final int n2) {
        this.consumePendingUpdateOperations();
        int scrollVerticallyBy;
        int n7;
        int n8;
        int n9;
        if (this.mAdapter != null) {
            this.eatRequestLayout();
            this.mRunningLayoutOrScroll = true;
            int scrollHorizontallyBy;
            int n3;
            if (n != 0) {
                scrollHorizontallyBy = this.mLayout.scrollHorizontallyBy(n, this.mRecycler, this.mState);
                n3 = n - scrollHorizontallyBy;
            }
            else {
                scrollHorizontallyBy = 0;
                n3 = 0;
            }
            int n4;
            if (n2 != 0) {
                scrollVerticallyBy = this.mLayout.scrollVerticallyBy(n2, this.mRecycler, this.mState);
                n4 = n2 - scrollVerticallyBy;
            }
            else {
                scrollVerticallyBy = 0;
                n4 = 0;
            }
            if (this.supportsChangeAnimations()) {
                for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
                    final View child = this.mChildHelper.getChildAt(i);
                    final RecyclerView$ViewHolder childViewHolder = this.getChildViewHolder(child);
                    if (childViewHolder != null && childViewHolder.mShadowingHolder != null) {
                        final RecyclerView$ViewHolder mShadowingHolder = childViewHolder.mShadowingHolder;
                        View itemView;
                        if (mShadowingHolder != null) {
                            itemView = mShadowingHolder.itemView;
                        }
                        else {
                            itemView = null;
                        }
                        if (itemView != null) {
                            final int left = child.getLeft();
                            final int top = child.getTop();
                            if (left != itemView.getLeft() || top != itemView.getTop()) {
                                itemView.layout(left, top, itemView.getWidth() + left, itemView.getHeight() + top);
                            }
                        }
                    }
                }
            }
            this.resumeRequestLayout(this.mRunningLayoutOrScroll = false);
            final int n5 = n4;
            final int n6 = scrollHorizontallyBy;
            n7 = n3;
            n8 = n5;
            n9 = n6;
        }
        else {
            scrollVerticallyBy = 0;
            n9 = 0;
            n8 = 0;
            n7 = 0;
        }
        if (!this.mItemDecorations.isEmpty()) {
            this.invalidate();
        }
        if (ViewCompat.getOverScrollMode((View)this) != 2) {
            this.considerReleasingGlowsOnScroll(n, n2);
            this.pullGlows(n7, n8);
        }
        if (n9 != 0 || scrollVerticallyBy != 0) {
            this.onScrollChanged(0, 0, 0, 0);
            if (this.mScrollListener != null) {
                this.mScrollListener.onScrolled(this, n9, scrollVerticallyBy);
            }
        }
        if (!this.awakenScrollBars()) {
            this.invalidate();
        }
    }
    
    public void scrollTo(final int n, final int n2) {
        throw new UnsupportedOperationException("RecyclerView does not support scrolling to an absolute position.");
    }
    
    public void scrollToPosition(final int n) {
        this.stopScroll();
        this.mLayout.scrollToPosition(n);
        this.awakenScrollBars();
    }
    
    public void setAccessibilityDelegateCompat(final RecyclerViewAccessibilityDelegate mAccessibilityDelegate) {
        ViewCompat.setAccessibilityDelegate((View)this, this.mAccessibilityDelegate = mAccessibilityDelegate);
    }
    
    public void setAdapter(final RecyclerView$Adapter recyclerView$Adapter) {
        this.setAdapterInternal(recyclerView$Adapter, false, true);
        this.requestLayout();
    }
    
    public void setClipToPadding(final boolean mClipToPadding) {
        if (mClipToPadding != this.mClipToPadding) {
            this.invalidateGlows();
        }
        super.setClipToPadding(this.mClipToPadding = mClipToPadding);
        if (this.mFirstLayoutComplete) {
            this.requestLayout();
        }
    }
    
    public void setHasFixedSize(final boolean mHasFixedSize) {
        this.mHasFixedSize = mHasFixedSize;
    }
    
    public void setItemAnimator(final RecyclerView$ItemAnimator mItemAnimator) {
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
            this.mItemAnimator.setListener(null);
        }
        this.mItemAnimator = mItemAnimator;
        if (this.mItemAnimator != null) {
            this.mItemAnimator.setListener(this.mItemAnimatorListener);
        }
    }
    
    public void setItemViewCacheSize(final int viewCacheSize) {
        this.mRecycler.setViewCacheSize(viewCacheSize);
    }
    
    public void setLayoutManager(final RecyclerView$LayoutManager mLayout) {
        if (mLayout == this.mLayout) {
            return;
        }
        if (this.mLayout != null) {
            if (this.mIsAttached) {
                this.mLayout.onDetachedFromWindow(this, this.mRecycler);
            }
            this.mLayout.setRecyclerView(null);
        }
        this.mRecycler.clear();
        this.mChildHelper.removeAllViewsUnfiltered();
        if ((this.mLayout = mLayout) != null) {
            if (mLayout.mRecyclerView != null) {
                throw new IllegalArgumentException("LayoutManager " + mLayout + " is already attached to a RecyclerView: " + mLayout.mRecyclerView);
            }
            this.mLayout.setRecyclerView(this);
            if (this.mIsAttached) {
                this.mLayout.onAttachedToWindow(this);
            }
        }
        this.requestLayout();
    }
    
    public void setOnScrollListener(final RecyclerView$OnScrollListener mScrollListener) {
        this.mScrollListener = mScrollListener;
    }
    
    public void setRecycledViewPool(final RecyclerView$RecycledViewPool recycledViewPool) {
        this.mRecycler.setRecycledViewPool(recycledViewPool);
    }
    
    public void setRecyclerListener(final RecyclerView$RecyclerListener mRecyclerListener) {
        this.mRecyclerListener = mRecyclerListener;
    }
    
    public void setScrollingTouchSlop(final int n) {
        final ViewConfiguration value = ViewConfiguration.get(this.getContext());
        switch (n) {
            default: {
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + n + "; using default value");
            }
            case 0: {
                this.mTouchSlop = value.getScaledTouchSlop();
            }
            case 1: {
                this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(value);
            }
        }
    }
    
    public void setViewCacheExtension(final RecyclerView$ViewCacheExtension viewCacheExtension) {
        this.mRecycler.setViewCacheExtension(viewCacheExtension);
    }
    
    public void smoothScrollBy(final int n, final int n2) {
        if (n != 0 || n2 != 0) {
            this.mViewFlinger.smoothScrollBy(n, n2);
        }
    }
    
    public void smoothScrollToPosition(final int n) {
        this.mLayout.smoothScrollToPosition(this, this.mState, n);
    }
    
    public void stopScroll() {
        this.setScrollState(0);
        this.stopScrollersInternal();
    }
    
    public void swapAdapter(final RecyclerView$Adapter recyclerView$Adapter, final boolean b) {
        this.setAdapterInternal(recyclerView$Adapter, true, b);
        this.mDataSetHasChangedAfterLayout = true;
        this.requestLayout();
    }
    
    void viewRangeUpdate(final int n, final int n2) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final View unfilteredChild = this.mChildHelper.getUnfilteredChildAt(i);
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(unfilteredChild);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= n && childViewHolderInt.mPosition < n + n2) {
                childViewHolderInt.addFlags(2);
                if (this.supportsChangeAnimations()) {
                    childViewHolderInt.addFlags(64);
                }
                ((RecyclerView$LayoutParams)unfilteredChild.getLayoutParams()).mInsetsDirty = true;
            }
        }
        this.mRecycler.viewRangeUpdate(n, n2);
    }
}
