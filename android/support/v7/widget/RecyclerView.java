// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.View$MeasureSpec;
import android.view.FocusFinder;
import android.view.ViewParent;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.SparseArray;
import android.support.v4.os.TraceCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.recyclerview.R$styleable;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.RectF;
import android.graphics.Rect;
import android.support.v4.view.NestedScrollingChildHelper;
import java.util.List;
import java.util.ArrayList;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.NestedScrollingChild;
import android.view.ViewGroup;

public class RecyclerView extends ViewGroup implements NestedScrollingChild, ScrollingView
{
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
    private static final int[] CLIP_TO_PADDING_ATTR;
    static final boolean DEBUG = false;
    static final boolean DISPATCH_TEMP_DETACH = false;
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
    public static final int HORIZONTAL = 0;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    static final int MAX_SCROLL_DURATION = 2000;
    private static final int[] NESTED_SCROLLING_ATTRS;
    public static final long NO_ID = -1L;
    public static final int NO_POSITION = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final int VERTICAL = 1;
    static final Interpolator sQuinticInterpolator;
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private RecyclerView$OnItemTouchListener mActiveOnItemTouchListener;
    RecyclerView$Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffectCompat mBottomGlow;
    private RecyclerView$ChildDrawingOrderCallback mChildDrawingOrderCallback;
    ChildHelper mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    private int mDispatchScrollCounter;
    private int mEatRequestLayout;
    private int mEatenAccessibilityChangeFlags;
    boolean mFirstLayoutComplete;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    boolean mIsAttached;
    RecyclerView$ItemAnimator mItemAnimator;
    private RecyclerView$ItemAnimator$ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    final ArrayList<RecyclerView$ItemDecoration> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    RecyclerView$LayoutManager mLayout;
    boolean mLayoutFrozen;
    private int mLayoutOrScrollCounter;
    boolean mLayoutRequestEaten;
    private EdgeEffectCompat mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerView$RecyclerViewDataObserver mObserver;
    private List<RecyclerView$OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    private RecyclerView$OnFlingListener mOnFlingListener;
    private final ArrayList<RecyclerView$OnItemTouchListener> mOnItemTouchListeners;
    private RecyclerView$SavedState mPendingSavedState;
    private final boolean mPostUpdatesOnAnimation;
    boolean mPostedAnimatorRunner;
    private boolean mPreserveFocusAfterLayout;
    final RecyclerView$Recycler mRecycler;
    RecyclerView$RecyclerListener mRecyclerListener;
    private EdgeEffectCompat mRightGlow;
    private final int[] mScrollConsumed;
    private float mScrollFactor;
    private RecyclerView$OnScrollListener mScrollListener;
    private List<RecyclerView$OnScrollListener> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private NestedScrollingChildHelper mScrollingChildHelper;
    final RecyclerView$State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffectCompat mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final RecyclerView$ViewFlinger mViewFlinger;
    private final ViewInfoStore$ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;
    
    static {
        NESTED_SCROLLING_ATTRS = new int[] { 16843830 };
        CLIP_TO_PADDING_ATTR = new int[] { 16842987 };
        FORCE_INVALIDATE_DISPLAY_LIST = (Build$VERSION.SDK_INT == 18 || Build$VERSION.SDK_INT == 19 || Build$VERSION.SDK_INT == 20);
        ALLOW_SIZE_IN_UNSPECIFIED_SPEC = (Build$VERSION.SDK_INT >= 23);
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[] { Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE };
        sQuinticInterpolator = (Interpolator)new RecyclerView$3();
    }
    
    public RecyclerView(final Context context) {
        this(context, null);
    }
    
    public RecyclerView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public RecyclerView(final Context context, final AttributeSet set, final int n) {
        final boolean b = true;
        super(context, set, n);
        this.mObserver = new RecyclerView$RecyclerViewDataObserver(this);
        this.mRecycler = new RecyclerView$Recycler(this);
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new RecyclerView$1(this);
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList<RecyclerView$ItemDecoration>();
        this.mOnItemTouchListeners = new ArrayList<RecyclerView$OnItemTouchListener>();
        this.mEatRequestLayout = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScrollFactor = Float.MIN_VALUE;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new RecyclerView$ViewFlinger(this);
        this.mState = new RecyclerView$State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new RecyclerView$ItemAnimatorRestoreListener(this);
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mNestedOffsets = new int[2];
        this.mItemAnimatorRunner = new RecyclerView$2(this);
        this.mViewInfoProcessCallback = new RecyclerView$4(this);
        if (set != null) {
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, RecyclerView.CLIP_TO_PADDING_ATTR, n, 0);
            this.mClipToPadding = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        }
        else {
            this.mClipToPadding = true;
        }
        this.setScrollContainer(true);
        this.setFocusableInTouchMode(true);
        this.mPostUpdatesOnAnimation = (Build$VERSION.SDK_INT >= 16);
        final ViewConfiguration value = ViewConfiguration.get(context);
        this.mTouchSlop = value.getScaledTouchSlop();
        this.mMinFlingVelocity = value.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = value.getScaledMaximumFlingVelocity();
        this.setWillNotDraw(this.getOverScrollMode() == 2);
        this.mItemAnimator.setListener(this.mItemAnimatorListener);
        this.initAdapterManager();
        this.initChildrenHelper();
        if (ViewCompat.getImportantForAccessibility((View)this) == 0) {
            ViewCompat.setImportantForAccessibility((View)this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
        this.setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        boolean boolean1;
        if (set != null) {
            final TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(set, R$styleable.RecyclerView, n, 0);
            final String string = obtainStyledAttributes2.getString(R$styleable.RecyclerView_layoutManager);
            if (obtainStyledAttributes2.getInt(R$styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
                this.setDescendantFocusability(262144);
            }
            obtainStyledAttributes2.recycle();
            this.createLayoutManager(context, string, set, n, 0);
            boolean1 = b;
            if (Build$VERSION.SDK_INT >= 21) {
                final TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(set, RecyclerView.NESTED_SCROLLING_ATTRS, n, 0);
                boolean1 = obtainStyledAttributes3.getBoolean(0, true);
                obtainStyledAttributes3.recycle();
            }
        }
        else {
            this.setDescendantFocusability(262144);
            boolean1 = b;
        }
        this.setNestedScrollingEnabled(boolean1);
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
    
    private void animateChange(final RecyclerView$ViewHolder mShadowingHolder, final RecyclerView$ViewHolder mShadowedHolder, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2, final boolean b, final boolean b2) {
        mShadowingHolder.setIsRecyclable(false);
        if (b) {
            this.addAnimatingView(mShadowingHolder);
        }
        if (mShadowingHolder != mShadowedHolder) {
            if (b2) {
                this.addAnimatingView(mShadowedHolder);
            }
            mShadowingHolder.mShadowedHolder = mShadowedHolder;
            this.addAnimatingView(mShadowingHolder);
            this.mRecycler.unscrapView(mShadowingHolder);
            mShadowedHolder.setIsRecyclable(false);
            mShadowedHolder.mShadowingHolder = mShadowingHolder;
        }
        if (this.mItemAnimator.animateChange(mShadowingHolder, mShadowedHolder, recyclerView$ItemAnimator$ItemHolderInfo, recyclerView$ItemAnimator$ItemHolderInfo2)) {
            this.postAnimationRunner();
        }
    }
    
    private void cancelTouch() {
        this.resetTouch();
        this.setScrollState(0);
    }
    
    private void createLayoutManager(Context ex, String trim, final AttributeSet set, final int n, final int n2) {
        if (trim != null) {
            trim = trim.trim();
            if (trim.length() != 0) {
                final String fullClassName = this.getFullClassName((Context)ex, trim);
                try {
                    Label_0117: {
                        if (!this.isInEditMode()) {
                            break Label_0117;
                        }
                        ClassLoader classLoader = this.getClass().getClassLoader();
                        while (true) {
                            final Class<? extends RecyclerView$LayoutManager> subclass = classLoader.loadClass(fullClassName).asSubclass(RecyclerView$LayoutManager.class);
                            try {
                                final Constructor<? extends RecyclerView$LayoutManager> constructor = subclass.getConstructor(RecyclerView.LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                                ex = (NoSuchMethodException)new Object[] { ex, set, n, n2 };
                                constructor.setAccessible(true);
                                this.setLayoutManager((RecyclerView$LayoutManager)constructor.newInstance((Object[])(Object)ex));
                                return;
                                classLoader = ((Context)ex).getClassLoader();
                            }
                            catch (NoSuchMethodException ex) {
                                try {
                                    final Constructor<? extends RecyclerView$LayoutManager> constructor = subclass.getConstructor((Class<?>[])new Class[0]);
                                    ex = null;
                                }
                                catch (NoSuchMethodException ex2) {
                                    ex2.initCause(ex);
                                    throw new IllegalStateException(set.getPositionDescription() + ": Error creating LayoutManager " + fullClassName, ex2);
                                }
                            }
                        }
                    }
                }
                catch (ClassNotFoundException ex3) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Unable to find LayoutManager " + fullClassName, ex3);
                }
                catch (InvocationTargetException ex4) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, ex4);
                }
                catch (InstantiationException ex5) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, ex5);
                }
                catch (IllegalAccessException ex6) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Cannot access non-public constructor " + fullClassName, ex6);
                }
                catch (ClassCastException ex7) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Class is not a LayoutManager " + fullClassName, ex7);
                }
            }
        }
    }
    
    private boolean didChildRangeChange(final int n, final int n2) {
        boolean b = false;
        this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mMinMaxLayoutPositions[0] != n || this.mMinMaxLayoutPositions[1] != n2) {
            b = true;
        }
        return b;
    }
    
    private void dispatchContentChangedIfNecessary() {
        final int mEatenAccessibilityChangeFlags = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (mEatenAccessibilityChangeFlags != 0 && this.isAccessibilityEnabled()) {
            final AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            AccessibilityEventCompat.setContentChangeTypes(obtain, mEatenAccessibilityChangeFlags);
            this.sendAccessibilityEventUnchecked(obtain);
        }
    }
    
    private void dispatchLayoutStep1() {
        boolean mTrackOldChangeHolders = true;
        this.mState.assertLayoutStep(1);
        this.mState.mIsMeasuring = false;
        this.eatRequestLayout();
        this.mViewInfoStore.clear();
        this.onEnterLayoutOrScroll();
        this.saveFocusInfo();
        this.processAdapterUpdatesAndSetAnimationFlags();
        final RecyclerView$State mState = this.mState;
        if (!this.mState.mRunSimpleAnimations || !this.mItemsChanged) {
            mTrackOldChangeHolders = false;
        }
        mState.mTrackOldChangeHolders = mTrackOldChangeHolders;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        this.mState.mInPreLayout = this.mState.mRunPredictiveAnimations;
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.mRunSimpleAnimations) {
            for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
                final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                    this.mViewInfoStore.addToPreLayout(childViewHolderInt, this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt, RecyclerView$ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt), childViewHolderInt.getUnmodifiedPayloads()));
                    if (this.mState.mTrackOldChangeHolders && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.addToOldChangeHolders(this.getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.mRunPredictiveAnimations) {
            this.saveOldPositions();
            final boolean mStructureChanged = this.mState.mStructureChanged;
            this.mState.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
            this.mState.mStructureChanged = mStructureChanged;
            for (int j = 0; j < this.mChildHelper.getChildCount(); ++j) {
                final RecyclerView$ViewHolder childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.getChildAt(j));
                if (!childViewHolderInt2.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(childViewHolderInt2)) {
                    final int buildAdapterChangeFlagsForAnimations = RecyclerView$ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt2);
                    final boolean hasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(8192);
                    int n = buildAdapterChangeFlagsForAnimations;
                    if (!hasAnyOfTheFlags) {
                        n = (buildAdapterChangeFlagsForAnimations | 0x1000);
                    }
                    final RecyclerView$ItemAnimator$ItemHolderInfo recordPreLayoutInformation = this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt2, n, childViewHolderInt2.getUnmodifiedPayloads());
                    if (hasAnyOfTheFlags) {
                        this.recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, recordPreLayoutInformation);
                    }
                    else {
                        this.mViewInfoStore.addToAppearedInPreLayoutHolders(childViewHolderInt2, recordPreLayoutInformation);
                    }
                }
            }
            this.clearOldPositions();
        }
        else {
            this.clearOldPositions();
        }
        this.onExitLayoutOrScroll();
        this.resumeRequestLayout(false);
        this.mState.mLayoutStep = 2;
    }
    
    private void dispatchLayoutStep2() {
        this.eatRequestLayout();
        this.onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        this.mState.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
        this.mState.mStructureChanged = false;
        this.mPendingSavedState = null;
        this.mState.mRunSimpleAnimations = (this.mState.mRunSimpleAnimations && this.mItemAnimator != null);
        this.mState.mLayoutStep = 4;
        this.onExitLayoutOrScroll();
        this.resumeRequestLayout(false);
    }
    
    private void dispatchLayoutStep3() {
        this.mState.assertLayoutStep(4);
        this.eatRequestLayout();
        this.onEnterLayoutOrScroll();
        this.mState.mLayoutStep = 1;
        if (this.mState.mRunSimpleAnimations) {
            for (int i = this.mChildHelper.getChildCount() - 1; i >= 0; --i) {
                final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!childViewHolderInt.shouldIgnore()) {
                    final long changedHolderKey = this.getChangedHolderKey(childViewHolderInt);
                    final RecyclerView$ItemAnimator$ItemHolderInfo recordPostLayoutInformation = this.mItemAnimator.recordPostLayoutInformation(this.mState, childViewHolderInt);
                    final RecyclerView$ViewHolder fromOldChangeHolders = this.mViewInfoStore.getFromOldChangeHolders(changedHolderKey);
                    if (fromOldChangeHolders != null && !fromOldChangeHolders.shouldIgnore()) {
                        final boolean disappearing = this.mViewInfoStore.isDisappearing(fromOldChangeHolders);
                        final boolean disappearing2 = this.mViewInfoStore.isDisappearing(childViewHolderInt);
                        if (disappearing && fromOldChangeHolders == childViewHolderInt) {
                            this.mViewInfoStore.addToPostLayout(childViewHolderInt, recordPostLayoutInformation);
                        }
                        else {
                            final RecyclerView$ItemAnimator$ItemHolderInfo popFromPreLayout = this.mViewInfoStore.popFromPreLayout(fromOldChangeHolders);
                            this.mViewInfoStore.addToPostLayout(childViewHolderInt, recordPostLayoutInformation);
                            final RecyclerView$ItemAnimator$ItemHolderInfo popFromPostLayout = this.mViewInfoStore.popFromPostLayout(childViewHolderInt);
                            if (popFromPreLayout == null) {
                                this.handleMissingPreInfoForChangeError(changedHolderKey, childViewHolderInt, fromOldChangeHolders);
                            }
                            else {
                                this.animateChange(fromOldChangeHolders, childViewHolderInt, popFromPreLayout, popFromPostLayout, disappearing, disappearing2);
                            }
                        }
                    }
                    else {
                        this.mViewInfoStore.addToPostLayout(childViewHolderInt, recordPostLayoutInformation);
                    }
                }
            }
            this.mViewInfoStore.process(this.mViewInfoProcessCallback);
        }
        this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        this.mState.mPreviousLayoutItemCount = this.mState.mItemCount;
        this.mDataSetHasChangedAfterLayout = false;
        this.mState.mRunSimpleAnimations = false;
        this.mState.mRunPredictiveAnimations = false;
        this.mLayout.mRequestedSimpleAnimations = false;
        if (this.mRecycler.mChangedScrap != null) {
            this.mRecycler.mChangedScrap.clear();
        }
        this.mLayout.onLayoutCompleted(this.mState);
        this.onExitLayoutOrScroll();
        this.resumeRequestLayout(false);
        this.mViewInfoStore.clear();
        if (this.didChildRangeChange(this.mMinMaxLayoutPositions[0], this.mMinMaxLayoutPositions[1])) {
            this.dispatchOnScrolled(0, 0);
        }
        this.recoverFocusFromState();
        this.resetFocusInfo();
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
    
    private void findMinMaxChildLayoutPositions(final int[] array) {
        final int childCount = this.mChildHelper.getChildCount();
        if (childCount == 0) {
            array[1] = (array[0] = -1);
            return;
        }
        int n = Integer.MAX_VALUE;
        int n2 = Integer.MIN_VALUE;
        for (int i = 0; i < childCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                final int layoutPosition = childViewHolderInt.getLayoutPosition();
                int n3;
                if (layoutPosition < (n3 = n)) {
                    n3 = layoutPosition;
                }
                if (layoutPosition > n2) {
                    n2 = layoutPosition;
                    n = n3;
                }
                else {
                    n = n3;
                }
            }
        }
        array[0] = n;
        array[1] = n2;
    }
    
    static RecyclerView$ViewHolder getChildViewHolderInt(final View view) {
        if (view == null) {
            return null;
        }
        return ((RecyclerView$LayoutParams)view.getLayoutParams()).mViewHolder;
    }
    
    private int getDeepestFocusedViewWithId(View focusedChild) {
        int n = focusedChild.getId();
        while (!focusedChild.isFocused() && focusedChild instanceof ViewGroup && focusedChild.hasFocus()) {
            focusedChild = ((ViewGroup)focusedChild).getFocusedChild();
            if (focusedChild.getId() != -1) {
                n = focusedChild.getId();
            }
        }
        return n;
    }
    
    private String getFullClassName(final Context context, final String s) {
        String string;
        if (s.charAt(0) == '.') {
            string = context.getPackageName() + s;
        }
        else {
            string = s;
            if (!s.contains(".")) {
                return RecyclerView.class.getPackage().getName() + '.' + s;
            }
        }
        return string;
    }
    
    private float getScrollFactor() {
        if (this.mScrollFactor == Float.MIN_VALUE) {
            final TypedValue typedValue = new TypedValue();
            if (!this.getContext().getTheme().resolveAttribute(16842829, typedValue, true)) {
                return 0.0f;
            }
            this.mScrollFactor = typedValue.getDimension(this.getContext().getResources().getDisplayMetrics());
        }
        return this.mScrollFactor;
    }
    
    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper((View)this);
        }
        return this.mScrollingChildHelper;
    }
    
    private void handleMissingPreInfoForChangeError(final long n, final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ViewHolder recyclerView$ViewHolder2) {
        final int childCount = this.mChildHelper.getChildCount();
        int i = 0;
        while (i < childCount) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (childViewHolderInt != recyclerView$ViewHolder && this.getChangedHolderKey(childViewHolderInt) == n) {
                if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
                    throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + childViewHolderInt + " \n View Holder 2:" + recyclerView$ViewHolder);
                }
                throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + childViewHolderInt + " \n View Holder 2:" + recyclerView$ViewHolder);
            }
            else {
                ++i;
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + recyclerView$ViewHolder2 + " cannot be found but it is necessary for " + recyclerView$ViewHolder);
    }
    
    private boolean hasUpdatedView() {
        final boolean b = false;
        final int childCount = this.mChildHelper.getChildCount();
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= childCount) {
                break;
            }
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(n));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    private void initChildrenHelper() {
        this.mChildHelper = new ChildHelper(new RecyclerView$5(this));
    }
    
    private boolean isPreferredNextFocus(final View view, final View view2, final int n) {
        int n2 = false ? 1 : 0;
        if (view2 == null || view2 == this) {
            return false;
        }
        if (view == null) {
            return true;
        }
        if (n != 2 && n != 1) {
            return this.isPreferredNextFocusAbsolute(view, view2, n);
        }
        final boolean b = this.mLayout.getLayoutDirection() == 1;
        if (n == 2) {
            n2 = (true ? 1 : 0);
        }
        int n3;
        if ((n2 ^ (b ? 1 : 0)) != 0x0) {
            n3 = 66;
        }
        else {
            n3 = 17;
        }
        if (this.isPreferredNextFocusAbsolute(view, view2, n3)) {
            return true;
        }
        if (n == 2) {
            return this.isPreferredNextFocusAbsolute(view, view2, 130);
        }
        return this.isPreferredNextFocusAbsolute(view, view2, 33);
    }
    
    private boolean isPreferredNextFocusAbsolute(final View view, final View view2, final int n) {
        this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        this.mTempRect2.set(0, 0, view2.getWidth(), view2.getHeight());
        this.offsetDescendantRectToMyCoords(view, this.mTempRect);
        this.offsetDescendantRectToMyCoords(view2, this.mTempRect2);
        switch (n) {
            default: {
                throw new IllegalArgumentException("direction must be absolute. received:" + n);
            }
            case 17: {
                if ((this.mTempRect.right > this.mTempRect2.right || this.mTempRect.left >= this.mTempRect2.right) && this.mTempRect.left > this.mTempRect2.left) {
                    break;
                }
                return false;
            }
            case 66: {
                if ((this.mTempRect.left >= this.mTempRect2.left && this.mTempRect.right > this.mTempRect2.left) || this.mTempRect.right >= this.mTempRect2.right) {
                    return false;
                }
                break;
            }
            case 33: {
                if ((this.mTempRect.bottom <= this.mTempRect2.bottom && this.mTempRect.top < this.mTempRect2.bottom) || this.mTempRect.top <= this.mTempRect2.top) {
                    return false;
                }
                break;
            }
            case 130: {
                if ((this.mTempRect.top >= this.mTempRect2.top && this.mTempRect.bottom > this.mTempRect2.top) || this.mTempRect.bottom >= this.mTempRect2.bottom) {
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    private void onPointerUp(final MotionEvent motionEvent) {
        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int n;
            if (actionIndex == 0) {
                n = 1;
            }
            else {
                n = 0;
            }
            this.mScrollPointerId = motionEvent.getPointerId(n);
            final int n2 = (int)(motionEvent.getX(n) + 0.5f);
            this.mLastTouchX = n2;
            this.mInitialTouchX = n2;
            final int n3 = (int)(motionEvent.getY(n) + 0.5f);
            this.mLastTouchY = n3;
            this.mInitialTouchY = n3;
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
        if (this.predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.preProcess();
        }
        else {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        }
        boolean b2;
        if (this.mItemsAddedOrRemoved || this.mItemsChanged) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.mState.mRunSimpleAnimations = (this.mFirstLayoutComplete && this.mItemAnimator != null && (this.mDataSetHasChangedAfterLayout || b2 || this.mLayout.mRequestedSimpleAnimations) && (!this.mDataSetHasChangedAfterLayout || this.mAdapter.hasStableIds()));
        this.mState.mRunPredictiveAnimations = (this.mState.mRunSimpleAnimations && b2 && !this.mDataSetHasChangedAfterLayout && this.predictiveItemAnimationsEnabled() && b);
    }
    
    private void pullGlows(final float n, final float n2, final float n3, final float n4) {
        int n5 = 1;
        final boolean b = false;
        int n6;
        if (n2 < 0.0f) {
            this.ensureLeftGlow();
            n6 = (b ? 1 : 0);
            if (this.mLeftGlow.onPull(-n2 / this.getWidth(), 1.0f - n3 / this.getHeight())) {
                n6 = 1;
            }
        }
        else {
            n6 = (b ? 1 : 0);
            if (n2 > 0.0f) {
                this.ensureRightGlow();
                n6 = (b ? 1 : 0);
                if (this.mRightGlow.onPull(n2 / this.getWidth(), n3 / this.getHeight())) {
                    n6 = 1;
                }
            }
        }
        while (true) {
            Label_0196: {
                if (n4 < 0.0f) {
                    this.ensureTopGlow();
                    if (!this.mTopGlow.onPull(-n4 / this.getHeight(), n / this.getWidth())) {
                        break Label_0196;
                    }
                }
                else {
                    if (n4 <= 0.0f) {
                        break Label_0196;
                    }
                    this.ensureBottomGlow();
                    if (!this.mBottomGlow.onPull(n4 / this.getHeight(), 1.0f - n / this.getWidth())) {
                        break Label_0196;
                    }
                }
                if (n5 != 0 || n2 != 0.0f || n4 != 0.0f) {
                    ViewCompat.postInvalidateOnAnimation((View)this);
                }
                return;
            }
            n5 = n6;
            continue;
        }
    }
    
    private void recoverFocusFromState() {
        if (this.mPreserveFocusAfterLayout && this.mAdapter != null && this.hasFocus()) {
            if (!this.isFocused()) {
                final View focusedChild = this.getFocusedChild();
                if (focusedChild == null || !this.mChildHelper.isHidden(focusedChild)) {
                    return;
                }
            }
            RecyclerView$ViewHolder viewHolderForAdapterPosition = null;
            if (this.mState.mFocusedItemPosition != -1) {
                viewHolderForAdapterPosition = this.findViewHolderForAdapterPosition(this.mState.mFocusedItemPosition);
            }
            RecyclerView$ViewHolder viewHolderForItemId;
            if ((viewHolderForItemId = viewHolderForAdapterPosition) == null) {
                viewHolderForItemId = viewHolderForAdapterPosition;
                if (this.mState.mFocusedItemId != -1L) {
                    viewHolderForItemId = viewHolderForAdapterPosition;
                    if (this.mAdapter.hasStableIds()) {
                        viewHolderForItemId = this.findViewHolderForItemId(this.mState.mFocusedItemId);
                    }
                }
            }
            if (viewHolderForItemId != null && !viewHolderForItemId.itemView.hasFocus() && viewHolderForItemId.itemView.hasFocusable()) {
                View itemView = viewHolderForItemId.itemView;
                if (this.mState.mFocusedSubChildId != -1L) {
                    final View viewById = viewHolderForItemId.itemView.findViewById(this.mState.mFocusedSubChildId);
                    if (viewById != null && viewById.isFocusable()) {
                        itemView = viewById;
                    }
                }
                while (true) {
                    itemView.requestFocus();
                    return;
                    continue;
                }
            }
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
    
    private void resetFocusInfo() {
        this.mState.mFocusedItemId = -1L;
        this.mState.mFocusedItemPosition = -1;
        this.mState.mFocusedSubChildId = -1;
    }
    
    private void resetTouch() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.clear();
        }
        this.stopNestedScroll();
        this.releaseGlows();
    }
    
    private void saveFocusInfo() {
        View focusedChild;
        if (this.mPreserveFocusAfterLayout && this.hasFocus() && this.mAdapter != null) {
            focusedChild = this.getFocusedChild();
        }
        else {
            focusedChild = null;
        }
        RecyclerView$ViewHolder containingViewHolder;
        if (focusedChild == null) {
            containingViewHolder = null;
        }
        else {
            containingViewHolder = this.findContainingViewHolder(focusedChild);
        }
        if (containingViewHolder == null) {
            this.resetFocusInfo();
            return;
        }
        final RecyclerView$State mState = this.mState;
        long itemId;
        if (this.mAdapter.hasStableIds()) {
            itemId = containingViewHolder.getItemId();
        }
        else {
            itemId = -1L;
        }
        mState.mFocusedItemId = itemId;
        final RecyclerView$State mState2 = this.mState;
        int adapterPosition;
        if (this.mDataSetHasChangedAfterLayout) {
            adapterPosition = -1;
        }
        else {
            adapterPosition = containingViewHolder.getAdapterPosition();
        }
        mState2.mFocusedItemPosition = adapterPosition;
        this.mState.mFocusedSubChildId = this.getDeepestFocusedViewWithId(containingViewHolder.itemView);
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
            this.mRecycler.clear();
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
    
    private void stopScrollersInternal() {
        this.mViewFlinger.stop();
        if (this.mLayout != null) {
            this.mLayout.stopSmoothScroller();
        }
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
        if (this.mLayout == null || !this.mLayout.onAddFocusables(this, list, n, n2)) {
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
    
    public void addOnChildAttachStateChangeListener(final RecyclerView$OnChildAttachStateChangeListener recyclerView$OnChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList<RecyclerView$OnChildAttachStateChangeListener>();
        }
        this.mOnChildAttachStateListeners.add(recyclerView$OnChildAttachStateChangeListener);
    }
    
    public void addOnItemTouchListener(final RecyclerView$OnItemTouchListener recyclerView$OnItemTouchListener) {
        this.mOnItemTouchListeners.add(recyclerView$OnItemTouchListener);
    }
    
    public void addOnScrollListener(final RecyclerView$OnScrollListener recyclerView$OnScrollListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList<RecyclerView$OnScrollListener>();
        }
        this.mScrollListeners.add(recyclerView$OnScrollListener);
    }
    
    void animateAppearance(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        recyclerView$ViewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateAppearance(recyclerView$ViewHolder, recyclerView$ItemAnimator$ItemHolderInfo, recyclerView$ItemAnimator$ItemHolderInfo2)) {
            this.postAnimationRunner();
        }
    }
    
    void animateDisappearance(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        this.addAnimatingView(recyclerView$ViewHolder);
        recyclerView$ViewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateDisappearance(recyclerView$ViewHolder, recyclerView$ItemAnimator$ItemHolderInfo, recyclerView$ItemAnimator$ItemHolderInfo2)) {
            this.postAnimationRunner();
        }
    }
    
    void assertInLayoutOrScroll(final String s) {
        if (this.isComputingLayout()) {
            return;
        }
        if (s == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(s);
    }
    
    void assertNotInLayoutOrScroll(final String s) {
        if (!this.isComputingLayout()) {
            if (this.mDispatchScrollCounter > 0) {
                Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks might be run during a measure & layout pass where you cannot change the RecyclerView data. Any method call that might change the structure of the RecyclerView or the adapter contents should be postponed to the next frame.", (Throwable)new IllegalStateException(""));
            }
            return;
        }
        if (s == null) {
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(s);
    }
    
    boolean canReuseUpdatedViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        return this.mItemAnimator == null || this.mItemAnimator.canReuseUpdatedViewHolder(recyclerView$ViewHolder, recyclerView$ViewHolder.getUnmodifiedPayloads());
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
    
    public void clearOnChildAttachStateChangeListeners() {
        if (this.mOnChildAttachStateListeners != null) {
            this.mOnChildAttachStateListeners.clear();
        }
    }
    
    public void clearOnScrollListeners() {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.clear();
        }
    }
    
    public int computeHorizontalScrollExtent() {
        if (this.mLayout != null && this.mLayout.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollExtent(this.mState);
        }
        return 0;
    }
    
    public int computeHorizontalScrollOffset() {
        if (this.mLayout != null && this.mLayout.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollOffset(this.mState);
        }
        return 0;
    }
    
    public int computeHorizontalScrollRange() {
        if (this.mLayout != null && this.mLayout.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollRange(this.mState);
        }
        return 0;
    }
    
    public int computeVerticalScrollExtent() {
        if (this.mLayout != null && this.mLayout.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollExtent(this.mState);
        }
        return 0;
    }
    
    public int computeVerticalScrollOffset() {
        if (this.mLayout != null && this.mLayout.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollOffset(this.mState);
        }
        return 0;
    }
    
    public int computeVerticalScrollRange() {
        if (this.mLayout != null && this.mLayout.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollRange(this.mState);
        }
        return 0;
    }
    
    void considerReleasingGlowsOnScroll(final int n, final int n2) {
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
    
    void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            TraceCompat.beginSection("RV FullInvalidate");
            this.dispatchLayout();
            TraceCompat.endSection();
        }
        else if (this.mAdapterHelper.hasPendingUpdates()) {
            if (this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
                TraceCompat.beginSection("RV PartialInvalidate");
                this.eatRequestLayout();
                this.mAdapterHelper.preProcess();
                if (!this.mLayoutRequestEaten) {
                    if (this.hasUpdatedView()) {
                        this.dispatchLayout();
                    }
                    else {
                        this.mAdapterHelper.consumePostponedUpdates();
                    }
                }
                this.resumeRequestLayout(true);
                TraceCompat.endSection();
                return;
            }
            if (this.mAdapterHelper.hasPendingUpdates()) {
                TraceCompat.beginSection("RV FullInvalidate");
                this.dispatchLayout();
                TraceCompat.endSection();
            }
        }
    }
    
    void defaultOnMeasure(final int n, final int n2) {
        this.setMeasuredDimension(RecyclerView$LayoutManager.chooseSize(n, this.getPaddingLeft() + this.getPaddingRight(), ViewCompat.getMinimumWidth((View)this)), RecyclerView$LayoutManager.chooseSize(n2, this.getPaddingTop() + this.getPaddingBottom(), ViewCompat.getMinimumHeight((View)this)));
    }
    
    void dispatchChildAttached(final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        this.onChildAttachedToWindow(view);
        if (this.mAdapter != null && childViewHolderInt != null) {
            this.mAdapter.onViewAttachedToWindow(childViewHolderInt);
        }
        if (this.mOnChildAttachStateListeners != null) {
            for (int i = this.mOnChildAttachStateListeners.size() - 1; i >= 0; --i) {
                this.mOnChildAttachStateListeners.get(i).onChildViewAttachedToWindow(view);
            }
        }
    }
    
    void dispatchChildDetached(final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        this.onChildDetachedFromWindow(view);
        if (this.mAdapter != null && childViewHolderInt != null) {
            this.mAdapter.onViewDetachedFromWindow(childViewHolderInt);
        }
        if (this.mOnChildAttachStateListeners != null) {
            for (int i = this.mOnChildAttachStateListeners.size() - 1; i >= 0; --i) {
                this.mOnChildAttachStateListeners.get(i).onChildViewDetachedFromWindow(view);
            }
        }
    }
    
    void dispatchLayout() {
        if (this.mAdapter == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if (this.mLayout == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        this.mState.mIsMeasuring = false;
        if (this.mState.mLayoutStep == 1) {
            this.dispatchLayoutStep1();
            this.mLayout.setExactMeasureSpecsFrom(this);
            this.dispatchLayoutStep2();
        }
        else if (this.mAdapterHelper.hasUpdates() || this.mLayout.getWidth() != this.getWidth() || this.mLayout.getHeight() != this.getHeight()) {
            this.mLayout.setExactMeasureSpecsFrom(this);
            this.dispatchLayoutStep2();
        }
        else {
            this.mLayout.setExactMeasureSpecsFrom(this);
        }
        this.dispatchLayoutStep3();
    }
    
    public boolean dispatchNestedFling(final float n, final float n2, final boolean b) {
        return this.getScrollingChildHelper().dispatchNestedFling(n, n2, b);
    }
    
    public boolean dispatchNestedPreFling(final float n, final float n2) {
        return this.getScrollingChildHelper().dispatchNestedPreFling(n, n2);
    }
    
    public boolean dispatchNestedPreScroll(final int n, final int n2, final int[] array, final int[] array2) {
        return this.getScrollingChildHelper().dispatchNestedPreScroll(n, n2, array, array2);
    }
    
    public boolean dispatchNestedScroll(final int n, final int n2, final int n3, final int n4, final int[] array) {
        return this.getScrollingChildHelper().dispatchNestedScroll(n, n2, n3, n4, array);
    }
    
    void dispatchOnScrollStateChanged(final int n) {
        if (this.mLayout != null) {
            this.mLayout.onScrollStateChanged(n);
        }
        this.onScrollStateChanged(n);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrollStateChanged(this, n);
        }
        if (this.mScrollListeners != null) {
            for (int i = this.mScrollListeners.size() - 1; i >= 0; --i) {
                this.mScrollListeners.get(i).onScrollStateChanged(this, n);
            }
        }
    }
    
    void dispatchOnScrolled(final int n, final int n2) {
        ++this.mDispatchScrollCounter;
        final int scrollX = this.getScrollX();
        final int scrollY = this.getScrollY();
        this.onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        this.onScrolled(n, n2);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrolled(this, n, n2);
        }
        if (this.mScrollListeners != null) {
            for (int i = this.mScrollListeners.size() - 1; i >= 0; --i) {
                this.mScrollListeners.get(i).onScrolled(this, n, n2);
            }
        }
        --this.mDispatchScrollCounter;
    }
    
    protected void dispatchRestoreInstanceState(final SparseArray<Parcelable> sparseArray) {
        this.dispatchThawSelfOnly((SparseArray)sparseArray);
    }
    
    protected void dispatchSaveInstanceState(final SparseArray<Parcelable> sparseArray) {
        this.dispatchFreezeSelfOnly((SparseArray)sparseArray);
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
    
    public boolean drawChild(final Canvas canvas, final View view, final long n) {
        return super.drawChild(canvas, view, n);
    }
    
    void eatRequestLayout() {
        ++this.mEatRequestLayout;
        if (this.mEatRequestLayout == 1 && !this.mLayoutFrozen) {
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
    
    public View findContainingItemView(View view) {
        ViewParent viewParent;
        for (viewParent = view.getParent(); viewParent != null && viewParent != this && viewParent instanceof View; viewParent = view.getParent()) {
            view = (View)viewParent;
        }
        if (viewParent == this) {
            return view;
        }
        return null;
    }
    
    public RecyclerView$ViewHolder findContainingViewHolder(View containingItemView) {
        containingItemView = this.findContainingItemView(containingItemView);
        if (containingItemView == null) {
            return null;
        }
        return this.getChildViewHolder(containingItemView);
    }
    
    public RecyclerView$ViewHolder findViewHolderForAdapterPosition(final int n) {
        RecyclerView$ViewHolder recyclerView$ViewHolder = null;
        if (!this.mDataSetHasChangedAfterLayout) {
            final int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
            int i = 0;
            RecyclerView$ViewHolder recyclerView$ViewHolder2 = null;
            while (i < unfilteredChildCount) {
                final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && this.getAdapterPositionFor(childViewHolderInt) == n) {
                    recyclerView$ViewHolder = childViewHolderInt;
                    if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                        return recyclerView$ViewHolder;
                    }
                    recyclerView$ViewHolder2 = childViewHolderInt;
                }
                ++i;
            }
            return recyclerView$ViewHolder2;
        }
        return recyclerView$ViewHolder;
    }
    
    public RecyclerView$ViewHolder findViewHolderForItemId(final long n) {
        RecyclerView$ViewHolder recyclerView$ViewHolder = null;
        if (this.mAdapter != null) {
            if (this.mAdapter.hasStableIds()) {
                final int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
                int i = 0;
                RecyclerView$ViewHolder recyclerView$ViewHolder2 = null;
                while (i < unfilteredChildCount) {
                    final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
                    if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.getItemId() == n) {
                        recyclerView$ViewHolder = childViewHolderInt;
                        if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                            return recyclerView$ViewHolder;
                        }
                        recyclerView$ViewHolder2 = childViewHolderInt;
                    }
                    ++i;
                }
                return recyclerView$ViewHolder2;
            }
            recyclerView$ViewHolder = recyclerView$ViewHolder;
        }
        return recyclerView$ViewHolder;
    }
    
    public RecyclerView$ViewHolder findViewHolderForLayoutPosition(final int n) {
        return this.findViewHolderForPosition(n, false);
    }
    
    @Deprecated
    public RecyclerView$ViewHolder findViewHolderForPosition(final int n) {
        return this.findViewHolderForPosition(n, false);
    }
    
    RecyclerView$ViewHolder findViewHolderForPosition(final int n, final boolean b) {
        final int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        int i = 0;
        RecyclerView$ViewHolder recyclerView$ViewHolder = null;
        while (i < unfilteredChildCount) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            RecyclerView$ViewHolder recyclerView$ViewHolder2 = recyclerView$ViewHolder;
            Label_0071: {
                if (childViewHolderInt != null) {
                    recyclerView$ViewHolder2 = recyclerView$ViewHolder;
                    if (!childViewHolderInt.isRemoved()) {
                        if (b) {
                            if (childViewHolderInt.mPosition != n) {
                                recyclerView$ViewHolder2 = recyclerView$ViewHolder;
                                break Label_0071;
                            }
                        }
                        else {
                            recyclerView$ViewHolder2 = recyclerView$ViewHolder;
                            if (childViewHolderInt.getLayoutPosition() != n) {
                                break Label_0071;
                            }
                        }
                        recyclerView$ViewHolder = childViewHolderInt;
                        if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                            break;
                        }
                        recyclerView$ViewHolder2 = childViewHolderInt;
                    }
                }
            }
            ++i;
            recyclerView$ViewHolder = recyclerView$ViewHolder2;
        }
        return recyclerView$ViewHolder;
    }
    
    public boolean fling(int max, int max2) {
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        }
        else if (!this.mLayoutFrozen) {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            int n = 0;
            Label_0063: {
                if (canScrollHorizontally) {
                    n = max;
                    if (Math.abs(max) >= this.mMinFlingVelocity) {
                        break Label_0063;
                    }
                }
                n = 0;
            }
            Label_0083: {
                if (canScrollVertically) {
                    max = max2;
                    if (Math.abs(max2) >= this.mMinFlingVelocity) {
                        break Label_0083;
                    }
                }
                max = 0;
            }
            if ((n != 0 || max != 0) && !this.dispatchNestedPreFling(n, max)) {
                final boolean b = canScrollHorizontally || canScrollVertically;
                this.dispatchNestedFling(n, max, b);
                if (this.mOnFlingListener != null && this.mOnFlingListener.onFling(n, max)) {
                    return true;
                }
                if (b) {
                    max2 = Math.max(-this.mMaxFlingVelocity, Math.min(n, this.mMaxFlingVelocity));
                    max = Math.max(-this.mMaxFlingVelocity, Math.min(max, this.mMaxFlingVelocity));
                    this.mViewFlinger.fling(max2, max);
                    return true;
                }
            }
        }
        return false;
    }
    
    public View focusSearch(final View view, final int n) {
        final boolean b = true;
        View onInterceptFocusSearch = this.mLayout.onInterceptFocusSearch(view, n);
        if (onInterceptFocusSearch == null) {
            boolean b2;
            if (this.mAdapter != null && this.mLayout != null && !this.isComputingLayout() && !this.mLayoutFrozen) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            final FocusFinder instance = FocusFinder.getInstance();
            View view2;
            if (b2 && (n == 2 || n == 1)) {
                boolean b3;
                if (this.mLayout.canScrollVertically()) {
                    int n2;
                    if (n == 2) {
                        n2 = 130;
                    }
                    else {
                        n2 = 33;
                    }
                    if (instance.findNextFocus((ViewGroup)this, view, n2) == null) {
                        b3 = true;
                    }
                    else {
                        b3 = false;
                    }
                }
                else {
                    b3 = false;
                }
                if (!b3 && this.mLayout.canScrollHorizontally()) {
                    boolean b4;
                    if (this.mLayout.getLayoutDirection() == 1) {
                        b4 = true;
                    }
                    else {
                        b4 = false;
                    }
                    boolean b5;
                    if (n == 2) {
                        b5 = true;
                    }
                    else {
                        b5 = false;
                    }
                    int n3;
                    if (b5 ^ b4) {
                        n3 = 66;
                    }
                    else {
                        n3 = 17;
                    }
                    b3 = (instance.findNextFocus((ViewGroup)this, view, n3) == null && b);
                }
                if (b3) {
                    this.consumePendingUpdateOperations();
                    if (this.findContainingItemView(view) == null) {
                        return null;
                    }
                    this.eatRequestLayout();
                    this.mLayout.onFocusSearchFailed(view, n, this.mRecycler, this.mState);
                    this.resumeRequestLayout(false);
                }
                view2 = instance.findNextFocus((ViewGroup)this, view, n);
            }
            else {
                view2 = instance.findNextFocus((ViewGroup)this, view, n);
                if (view2 == null && b2) {
                    this.consumePendingUpdateOperations();
                    if (this.findContainingItemView(view) == null) {
                        return null;
                    }
                    this.eatRequestLayout();
                    view2 = this.mLayout.onFocusSearchFailed(view, n, this.mRecycler, this.mState);
                    this.resumeRequestLayout(false);
                }
            }
            onInterceptFocusSearch = view2;
            if (!this.isPreferredNextFocus(view, view2, n)) {
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
    
    int getAdapterPositionFor(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        if (recyclerView$ViewHolder.hasAnyOfTheFlags(524) || !recyclerView$ViewHolder.isBound()) {
            return -1;
        }
        return this.mAdapterHelper.applyPendingUpdatesToPosition(recyclerView$ViewHolder.mPosition);
    }
    
    public int getBaseline() {
        if (this.mLayout != null) {
            return this.mLayout.getBaseline();
        }
        return super.getBaseline();
    }
    
    long getChangedHolderKey(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        if (this.mAdapter.hasStableIds()) {
            return recyclerView$ViewHolder.getItemId();
        }
        return recyclerView$ViewHolder.mPosition;
    }
    
    public int getChildAdapterPosition(final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getAdapterPosition();
        }
        return -1;
    }
    
    protected int getChildDrawingOrder(final int n, final int n2) {
        if (this.mChildDrawingOrderCallback == null) {
            return super.getChildDrawingOrder(n, n2);
        }
        return this.mChildDrawingOrderCallback.onGetChildDrawingOrder(n, n2);
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
    
    public int getChildLayoutPosition(final View view) {
        final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }
    
    @Deprecated
    public int getChildPosition(final View view) {
        return this.getChildAdapterPosition(view);
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
        if (this.mState.isPreLayout() && (recyclerView$LayoutParams.isItemChanged() || recyclerView$LayoutParams.isViewInvalid())) {
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
    
    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }
    
    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }
    
    public RecyclerView$OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }
    
    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
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
    
    public boolean hasNestedScrollingParent() {
        return this.getScrollingChildHelper().hasNestedScrollingParent();
    }
    
    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }
    
    void initAdapterManager() {
        this.mAdapterHelper = new AdapterHelper(new RecyclerView$6(this));
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
    
    boolean isAccessibilityEnabled() {
        return this.mAccessibilityManager != null && this.mAccessibilityManager.isEnabled();
    }
    
    public boolean isAnimating() {
        return this.mItemAnimator != null && this.mItemAnimator.isRunning();
    }
    
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }
    
    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }
    
    public boolean isLayoutFrozen() {
        return this.mLayoutFrozen;
    }
    
    public boolean isNestedScrollingEnabled() {
        return this.getScrollingChildHelper().isNestedScrollingEnabled();
    }
    
    void jumpToPositionForSmoothScroller(final int n) {
        if (this.mLayout == null) {
            return;
        }
        this.mLayout.scrollToPosition(n);
        this.awakenScrollBars();
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
        boolean mFirstLayoutComplete = true;
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        this.mIsAttached = true;
        if (!this.mFirstLayoutComplete || this.isLayoutRequested()) {
            mFirstLayoutComplete = false;
        }
        this.mFirstLayoutComplete = mFirstLayoutComplete;
        if (this.mLayout != null) {
            this.mLayout.dispatchAttachedToWindow(this);
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
        this.stopScroll();
        this.mIsAttached = false;
        if (this.mLayout != null) {
            this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.onDetach();
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        for (int size = this.mItemDecorations.size(), i = 0; i < size; ++i) {
            this.mItemDecorations.get(i).onDraw(canvas, this, this.mState);
        }
    }
    
    void onEnterLayoutOrScroll() {
        ++this.mLayoutOrScrollCounter;
    }
    
    void onExitLayoutOrScroll() {
        --this.mLayoutOrScrollCounter;
        if (this.mLayoutOrScrollCounter < 1) {
            this.mLayoutOrScrollCounter = 0;
            this.dispatchContentChangedIfNecessary();
        }
    }
    
    public boolean onGenericMotionEvent(final MotionEvent motionEvent) {
        if (this.mLayout != null && !this.mLayoutFrozen && (motionEvent.getSource() & 0x2) != 0x0 && motionEvent.getAction() == 8) {
            float n;
            if (this.mLayout.canScrollVertically()) {
                n = -MotionEventCompat.getAxisValue(motionEvent, 9);
            }
            else {
                n = 0.0f;
            }
            float axisValue;
            if (this.mLayout.canScrollHorizontally()) {
                axisValue = MotionEventCompat.getAxisValue(motionEvent, 10);
            }
            else {
                axisValue = 0.0f;
            }
            if (n != 0.0f || axisValue != 0.0f) {
                final float scrollFactor = this.getScrollFactor();
                this.scrollByInternal((int)(axisValue * scrollFactor), (int)(n * scrollFactor), motionEvent);
                return false;
            }
        }
        return false;
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final int n = -1;
        boolean b = true;
        if (!this.mLayoutFrozen) {
            if (this.dispatchOnItemTouchIntercept(motionEvent)) {
                this.cancelTouch();
                return true;
            }
            if (this.mLayout != null) {
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
                        if (this.mIgnoreMotionEventTillDown) {
                            this.mIgnoreMotionEventTillDown = false;
                        }
                        this.mScrollPointerId = motionEvent.getPointerId(0);
                        final int n2 = (int)(motionEvent.getX() + 0.5f);
                        this.mLastTouchX = n2;
                        this.mInitialTouchX = n2;
                        final int n3 = (int)(motionEvent.getY() + 0.5f);
                        this.mLastTouchY = n3;
                        this.mInitialTouchY = n3;
                        if (this.mScrollState == 2) {
                            this.getParent().requestDisallowInterceptTouchEvent(true);
                            this.setScrollState(1);
                        }
                        this.mNestedOffsets[this.mNestedOffsets[1] = 0] = 0;
                        int n4;
                        if (canScrollHorizontally) {
                            n4 = 1;
                        }
                        else {
                            n4 = 0;
                        }
                        int n5 = n4;
                        if (canScrollVertically) {
                            n5 = (n4 | 0x2);
                        }
                        this.startNestedScroll(n5);
                        break;
                    }
                    case 5: {
                        this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
                        final int n6 = (int)(motionEvent.getX(actionIndex) + 0.5f);
                        this.mLastTouchX = n6;
                        this.mInitialTouchX = n6;
                        final int n7 = (int)(motionEvent.getY(actionIndex) + 0.5f);
                        this.mLastTouchY = n7;
                        this.mInitialTouchY = n7;
                        break;
                    }
                    case 2: {
                        final int pointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
                        if (pointerIndex < 0) {
                            Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                            return false;
                        }
                        final int n8 = (int)(motionEvent.getX(pointerIndex) + 0.5f);
                        final int n9 = (int)(motionEvent.getY(pointerIndex) + 0.5f);
                        if (this.mScrollState == 1) {
                            break;
                        }
                        final int n10 = n8 - this.mInitialTouchX;
                        final int n11 = n9 - this.mInitialTouchY;
                        boolean b2;
                        if (canScrollHorizontally && Math.abs(n10) > this.mTouchSlop) {
                            final int mInitialTouchX = this.mInitialTouchX;
                            final int mTouchSlop = this.mTouchSlop;
                            int n12;
                            if (n10 < 0) {
                                n12 = -1;
                            }
                            else {
                                n12 = 1;
                            }
                            this.mLastTouchX = n12 * mTouchSlop + mInitialTouchX;
                            b2 = true;
                        }
                        else {
                            b2 = false;
                        }
                        int n13 = b2 ? 1 : 0;
                        if (canScrollVertically) {
                            n13 = (b2 ? 1 : 0);
                            if (Math.abs(n11) > this.mTouchSlop) {
                                final int mInitialTouchY = this.mInitialTouchY;
                                final int mTouchSlop2 = this.mTouchSlop;
                                int n14;
                                if (n11 < 0) {
                                    n14 = n;
                                }
                                else {
                                    n14 = 1;
                                }
                                this.mLastTouchY = mInitialTouchY + n14 * mTouchSlop2;
                                n13 = 1;
                            }
                        }
                        if (n13 != 0) {
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
                        this.stopNestedScroll();
                        break;
                    }
                    case 3: {
                        this.cancelTouch();
                        break;
                    }
                }
                if (this.mScrollState != 1) {
                    b = false;
                }
                return b;
            }
        }
        return false;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        TraceCompat.beginSection("RV OnLayout");
        this.dispatchLayout();
        TraceCompat.endSection();
        this.mFirstLayoutComplete = true;
    }
    
    protected void onMeasure(final int n, final int n2) {
        final boolean b = false;
        if (this.mLayout == null) {
            this.defaultOnMeasure(n, n2);
        }
        else if (this.mLayout.mAutoMeasure) {
            final int mode = View$MeasureSpec.getMode(n);
            final int mode2 = View$MeasureSpec.getMode(n2);
            boolean b2 = b;
            if (mode == 1073741824) {
                b2 = b;
                if (mode2 == 1073741824) {
                    b2 = true;
                }
            }
            this.mLayout.onMeasure(this.mRecycler, this.mState, n, n2);
            if (!b2 && this.mAdapter != null) {
                if (this.mState.mLayoutStep == 1) {
                    this.dispatchLayoutStep1();
                }
                this.mLayout.setMeasureSpecs(n, n2);
                this.mState.mIsMeasuring = true;
                this.dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(n, n2);
                if (this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
                    this.mState.mIsMeasuring = true;
                    this.dispatchLayoutStep2();
                    this.mLayout.setMeasuredDimensionFromChildren(n, n2);
                }
            }
        }
        else {
            if (this.mHasFixedSize) {
                this.mLayout.onMeasure(this.mRecycler, this.mState, n, n2);
                return;
            }
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
            this.eatRequestLayout();
            this.mLayout.onMeasure(this.mRecycler, this.mState, n, n2);
            this.resumeRequestLayout(false);
            this.mState.mInPreLayout = false;
        }
    }
    
    protected boolean onRequestFocusInDescendants(final int n, final Rect rect) {
        return !this.isComputingLayout() && super.onRequestFocusInDescendants(n, rect);
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof RecyclerView$SavedState)) {
            super.onRestoreInstanceState(parcelable);
        }
        else {
            this.mPendingSavedState = (RecyclerView$SavedState)parcelable;
            super.onRestoreInstanceState(this.mPendingSavedState.getSuperState());
            if (this.mLayout != null && this.mPendingSavedState.mLayoutState != null) {
                this.mLayout.onRestoreInstanceState(this.mPendingSavedState.mLayoutState);
            }
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
    
    public void onScrollStateChanged(final int n) {
    }
    
    public void onScrolled(final int n, final int n2) {
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n != n3 || n2 != n4) {
            this.invalidateGlows();
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final boolean b = false;
        if (!this.mLayoutFrozen && !this.mIgnoreMotionEventTillDown) {
            if (this.dispatchOnItemTouch(motionEvent)) {
                this.cancelTouch();
                return true;
            }
            if (this.mLayout != null) {
                final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
                final boolean canScrollVertically = this.mLayout.canScrollVertically();
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }
                final MotionEvent obtain = MotionEvent.obtain(motionEvent);
                final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                if (actionMasked == 0) {
                    this.mNestedOffsets[this.mNestedOffsets[1] = 0] = 0;
                }
                obtain.offsetLocation((float)this.mNestedOffsets[0], (float)this.mNestedOffsets[1]);
                int n = b ? 1 : 0;
                while (true) {
                    switch (actionMasked) {
                        default: {
                            n = (b ? 1 : 0);
                            break Label_0184;
                        }
                        case 3: {
                            this.cancelTouch();
                            n = (b ? 1 : 0);
                            break Label_0184;
                        }
                        case 1: {
                            this.mVelocityTracker.addMovement(obtain);
                            this.mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaxFlingVelocity);
                            float n2;
                            if (canScrollHorizontally) {
                                n2 = -VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mScrollPointerId);
                            }
                            else {
                                n2 = 0.0f;
                            }
                            float n3;
                            if (canScrollVertically) {
                                n3 = -VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mScrollPointerId);
                            }
                            else {
                                n3 = 0.0f;
                            }
                            if ((n2 == 0.0f && n3 == 0.0f) || !this.fling((int)n2, (int)n3)) {
                                this.setScrollState(0);
                            }
                            this.resetTouch();
                            n = 1;
                            break Label_0184;
                        }
                        case 6: {
                            this.onPointerUp(motionEvent);
                            n = (b ? 1 : 0);
                            break Label_0184;
                        }
                        case 5: {
                            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
                            final int n4 = (int)(motionEvent.getX(actionIndex) + 0.5f);
                            this.mLastTouchX = n4;
                            this.mInitialTouchX = n4;
                            final int n5 = (int)(motionEvent.getY(actionIndex) + 0.5f);
                            this.mLastTouchY = n5;
                            this.mInitialTouchY = n5;
                            n = (b ? 1 : 0);
                            break Label_0184;
                        }
                        case 0: {
                            this.mScrollPointerId = motionEvent.getPointerId(0);
                            final int n6 = (int)(motionEvent.getX() + 0.5f);
                            this.mLastTouchX = n6;
                            this.mInitialTouchX = n6;
                            final int n7 = (int)(motionEvent.getY() + 0.5f);
                            this.mLastTouchY = n7;
                            this.mInitialTouchY = n7;
                            int n8;
                            if (canScrollHorizontally) {
                                n8 = 1;
                            }
                            else {
                                n8 = 0;
                            }
                            int n9 = n8;
                            if (canScrollVertically) {
                                n9 = (n8 | 0x2);
                            }
                            this.startNestedScroll(n9);
                            n = (b ? 1 : 0);
                        }
                        case 4: {
                            if (n == 0) {
                                this.mVelocityTracker.addMovement(obtain);
                            }
                            obtain.recycle();
                            return true;
                        }
                        case 2: {
                            final int pointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
                            if (pointerIndex < 0) {
                                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                                return false;
                            }
                            final int n10 = (int)(motionEvent.getX(pointerIndex) + 0.5f);
                            final int n11 = (int)(motionEvent.getY(pointerIndex) + 0.5f);
                            final int n12 = this.mLastTouchX - n10;
                            int n14;
                            final int n13 = n14 = this.mLastTouchY - n11;
                            int n15 = n12;
                            if (this.dispatchNestedPreScroll(n12, n13, this.mScrollConsumed, this.mScrollOffset)) {
                                n15 = n12 - this.mScrollConsumed[0];
                                n14 = n13 - this.mScrollConsumed[1];
                                obtain.offsetLocation((float)this.mScrollOffset[0], (float)this.mScrollOffset[1]);
                                final int[] mNestedOffsets = this.mNestedOffsets;
                                mNestedOffsets[0] += this.mScrollOffset[0];
                                final int[] mNestedOffsets2 = this.mNestedOffsets;
                                mNestedOffsets2[1] += this.mScrollOffset[1];
                            }
                            int n16 = n14;
                            int n17 = n15;
                            if (this.mScrollState != 1) {
                                boolean b2;
                                if (canScrollHorizontally && Math.abs(n15) > this.mTouchSlop) {
                                    if (n15 > 0) {
                                        n15 -= this.mTouchSlop;
                                    }
                                    else {
                                        n15 += this.mTouchSlop;
                                    }
                                    b2 = true;
                                }
                                else {
                                    b2 = false;
                                }
                                int n18 = n14;
                                int n19 = b2 ? 1 : 0;
                                if (canScrollVertically) {
                                    n18 = n14;
                                    n19 = (b2 ? 1 : 0);
                                    if (Math.abs(n14) > this.mTouchSlop) {
                                        if (n14 > 0) {
                                            n18 = n14 - this.mTouchSlop;
                                        }
                                        else {
                                            n18 = n14 + this.mTouchSlop;
                                        }
                                        n19 = 1;
                                    }
                                }
                                n16 = n18;
                                n17 = n15;
                                if (n19 != 0) {
                                    this.setScrollState(1);
                                    n17 = n15;
                                    n16 = n18;
                                }
                            }
                            n = (b ? 1 : 0);
                            if (this.mScrollState != 1) {
                                continue;
                            }
                            this.mLastTouchX = n10 - this.mScrollOffset[0];
                            this.mLastTouchY = n11 - this.mScrollOffset[1];
                            if (!canScrollHorizontally) {
                                n17 = 0;
                            }
                            if (!canScrollVertically) {
                                n16 = 0;
                            }
                            n = (b ? 1 : 0);
                            if (this.scrollByInternal(n17, n16, obtain)) {
                                this.getParent().requestDisallowInterceptTouchEvent(true);
                                n = (b ? 1 : 0);
                            }
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation((View)this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }
    
    void recordAnimationInfoIfBouncedHiddenView(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo) {
        recyclerView$ViewHolder.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders && recyclerView$ViewHolder.isUpdated() && !recyclerView$ViewHolder.isRemoved() && !recyclerView$ViewHolder.shouldIgnore()) {
            this.mViewInfoStore.addToOldChangeHolders(this.getChangedHolderKey(recyclerView$ViewHolder), recyclerView$ViewHolder);
        }
        this.mViewInfoStore.addToPreLayout(recyclerView$ViewHolder, recyclerView$ItemAnimator$ItemHolderInfo);
    }
    
    boolean removeAnimatingView(final View view) {
        this.eatRequestLayout();
        final boolean removeViewIfHidden = this.mChildHelper.removeViewIfHidden(view);
        if (removeViewIfHidden) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.unscrapView(childViewHolderInt);
            this.mRecycler.recycleViewHolderInternal(childViewHolderInt);
        }
        this.resumeRequestLayout(!removeViewIfHidden);
        return removeViewIfHidden;
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
            this.setWillNotDraw(this.getOverScrollMode() == 2);
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }
    
    public void removeOnChildAttachStateChangeListener(final RecyclerView$OnChildAttachStateChangeListener recyclerView$OnChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners == null) {
            return;
        }
        this.mOnChildAttachStateListeners.remove(recyclerView$OnChildAttachStateChangeListener);
    }
    
    public void removeOnItemTouchListener(final RecyclerView$OnItemTouchListener recyclerView$OnItemTouchListener) {
        this.mOnItemTouchListeners.remove(recyclerView$OnItemTouchListener);
        if (this.mActiveOnItemTouchListener == recyclerView$OnItemTouchListener) {
            this.mActiveOnItemTouchListener = null;
        }
    }
    
    public void removeOnScrollListener(final RecyclerView$OnScrollListener recyclerView$OnScrollListener) {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.remove(recyclerView$OnScrollListener);
        }
    }
    
    void repositionShadowingViews() {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.mChildHelper.getChildAt(i);
            final RecyclerView$ViewHolder childViewHolder = this.getChildViewHolder(child);
            if (childViewHolder != null && childViewHolder.mShadowingHolder != null) {
                final View itemView = childViewHolder.mShadowingHolder.itemView;
                final int left = child.getLeft();
                final int top = child.getTop();
                if (left != itemView.getLeft() || top != itemView.getTop()) {
                    itemView.layout(left, top, itemView.getWidth() + left, itemView.getHeight() + top);
                }
            }
        }
    }
    
    public void requestChildFocus(final View view, final View view2) {
        if (!this.mLayout.onRequestChildFocus(this, this.mState, view, view2) && view2 != null) {
            this.mTempRect.set(0, 0, view2.getWidth(), view2.getHeight());
            final ViewGroup$LayoutParams layoutParams = view2.getLayoutParams();
            if (layoutParams instanceof RecyclerView$LayoutParams) {
                final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)layoutParams;
                if (!recyclerView$LayoutParams.mInsetsDirty) {
                    final Rect mDecorInsets = recyclerView$LayoutParams.mDecorInsets;
                    final Rect mTempRect = this.mTempRect;
                    mTempRect.left -= mDecorInsets.left;
                    final Rect mTempRect2 = this.mTempRect;
                    mTempRect2.right += mDecorInsets.right;
                    final Rect mTempRect3 = this.mTempRect;
                    mTempRect3.top -= mDecorInsets.top;
                    final Rect mTempRect4 = this.mTempRect;
                    mTempRect4.bottom += mDecorInsets.bottom;
                }
            }
            this.offsetDescendantRectToMyCoords(view2, this.mTempRect);
            this.offsetRectIntoDescendantCoords(view, this.mTempRect);
            this.requestChildRectangleOnScreen(view, this.mTempRect, !this.mFirstLayoutComplete);
        }
        super.requestChildFocus(view, view2);
    }
    
    public boolean requestChildRectangleOnScreen(final View view, final Rect rect, final boolean b) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, b);
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        for (int size = this.mOnItemTouchListeners.size(), i = 0; i < size; ++i) {
            this.mOnItemTouchListeners.get(i).onRequestDisallowInterceptTouchEvent(b);
        }
        super.requestDisallowInterceptTouchEvent(b);
    }
    
    public void requestLayout() {
        if (this.mEatRequestLayout == 0 && !this.mLayoutFrozen) {
            super.requestLayout();
            return;
        }
        this.mLayoutRequestEaten = true;
    }
    
    void resumeRequestLayout(final boolean b) {
        if (this.mEatRequestLayout < 1) {
            this.mEatRequestLayout = 1;
        }
        if (!b) {
            this.mLayoutRequestEaten = false;
        }
        if (this.mEatRequestLayout == 1) {
            if (b && this.mLayoutRequestEaten && !this.mLayoutFrozen && this.mLayout != null && this.mAdapter != null) {
                this.dispatchLayout();
            }
            if (!this.mLayoutFrozen) {
                this.mLayoutRequestEaten = false;
            }
        }
        --this.mEatRequestLayout;
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
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        }
        else if (!this.mLayoutFrozen) {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (canScrollHorizontally || canScrollVertically) {
                if (!canScrollHorizontally) {
                    n = 0;
                }
                if (!canScrollVertically) {
                    n2 = 0;
                }
                this.scrollByInternal(n, n2, null);
            }
        }
    }
    
    boolean scrollByInternal(final int n, final int n2, final MotionEvent motionEvent) {
        boolean b = false;
        this.consumePendingUpdateOperations();
        int scrollHorizontallyBy;
        int n4;
        int n6;
        int n7;
        if (this.mAdapter != null) {
            this.eatRequestLayout();
            this.onEnterLayoutOrScroll();
            TraceCompat.beginSection("RV Scroll");
            int n3;
            if (n != 0) {
                scrollHorizontallyBy = this.mLayout.scrollHorizontallyBy(n, this.mRecycler, this.mState);
                n3 = n - scrollHorizontallyBy;
            }
            else {
                scrollHorizontallyBy = 0;
                n3 = 0;
            }
            int scrollVerticallyBy;
            if (n2 != 0) {
                scrollVerticallyBy = this.mLayout.scrollVerticallyBy(n2, this.mRecycler, this.mState);
                n4 = n2 - scrollVerticallyBy;
            }
            else {
                scrollVerticallyBy = 0;
                n4 = 0;
            }
            TraceCompat.endSection();
            this.repositionShadowingViews();
            this.onExitLayoutOrScroll();
            this.resumeRequestLayout(false);
            final int n5 = scrollVerticallyBy;
            n6 = n3;
            n7 = n5;
        }
        else {
            n7 = 0;
            scrollHorizontallyBy = 0;
            n4 = 0;
            n6 = 0;
        }
        if (!this.mItemDecorations.isEmpty()) {
            this.invalidate();
        }
        if (this.dispatchNestedScroll(scrollHorizontallyBy, n7, n6, n4, this.mScrollOffset)) {
            this.mLastTouchX -= this.mScrollOffset[0];
            this.mLastTouchY -= this.mScrollOffset[1];
            if (motionEvent != null) {
                motionEvent.offsetLocation((float)this.mScrollOffset[0], (float)this.mScrollOffset[1]);
            }
            final int[] mNestedOffsets = this.mNestedOffsets;
            mNestedOffsets[0] += this.mScrollOffset[0];
            final int[] mNestedOffsets2 = this.mNestedOffsets;
            mNestedOffsets2[1] += this.mScrollOffset[1];
        }
        else if (this.getOverScrollMode() != 2) {
            if (motionEvent != null) {
                this.pullGlows(motionEvent.getX(), n6, motionEvent.getY(), n4);
            }
            this.considerReleasingGlowsOnScroll(n, n2);
        }
        if (scrollHorizontallyBy != 0 || n7 != 0) {
            this.dispatchOnScrolled(scrollHorizontallyBy, n7);
        }
        if (!this.awakenScrollBars()) {
            this.invalidate();
        }
        if (scrollHorizontallyBy != 0 || n7 != 0) {
            b = true;
        }
        return b;
    }
    
    public void scrollTo(final int n, final int n2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }
    
    public void scrollToPosition(final int n) {
        if (this.mLayoutFrozen) {
            return;
        }
        this.stopScroll();
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        this.mLayout.scrollToPosition(n);
        this.awakenScrollBars();
    }
    
    public void sendAccessibilityEventUnchecked(final AccessibilityEvent accessibilityEvent) {
        if (this.shouldDeferAccessibilityEvent(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }
    
    public void setAccessibilityDelegateCompat(final RecyclerViewAccessibilityDelegate mAccessibilityDelegate) {
        ViewCompat.setAccessibilityDelegate((View)this, this.mAccessibilityDelegate = mAccessibilityDelegate);
    }
    
    public void setAdapter(final RecyclerView$Adapter recyclerView$Adapter) {
        this.setLayoutFrozen(false);
        this.setAdapterInternal(recyclerView$Adapter, false, true);
        this.requestLayout();
    }
    
    public void setChildDrawingOrderCallback(final RecyclerView$ChildDrawingOrderCallback mChildDrawingOrderCallback) {
        if (mChildDrawingOrderCallback == this.mChildDrawingOrderCallback) {
            return;
        }
        this.mChildDrawingOrderCallback = mChildDrawingOrderCallback;
        this.setChildrenDrawingOrderEnabled(this.mChildDrawingOrderCallback != null);
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
    
    void setDataSetChangedAfterLayout() {
        if (this.mDataSetHasChangedAfterLayout) {
            return;
        }
        this.mDataSetHasChangedAfterLayout = true;
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(512);
            }
        }
        this.mRecycler.setAdapterPositionsAsUnknown();
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
    
    public void setLayoutFrozen(final boolean b) {
        if (b != this.mLayoutFrozen) {
            this.assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            if (b) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                this.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.mLayoutFrozen = true;
                this.mIgnoreMotionEventTillDown = true;
                this.stopScroll();
                return;
            }
            this.mLayoutFrozen = false;
            if (this.mLayoutRequestEaten && this.mLayout != null && this.mAdapter != null) {
                this.requestLayout();
            }
            this.mLayoutRequestEaten = false;
        }
    }
    
    public void setLayoutManager(final RecyclerView$LayoutManager mLayout) {
        if (mLayout == this.mLayout) {
            return;
        }
        this.stopScroll();
        if (this.mLayout != null) {
            if (this.mItemAnimator != null) {
                this.mItemAnimator.endAnimations();
            }
            this.mLayout.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            this.mRecycler.clear();
            if (this.mIsAttached) {
                this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
            }
            this.mLayout.setRecyclerView(null);
            this.mLayout = null;
        }
        else {
            this.mRecycler.clear();
        }
        this.mChildHelper.removeAllViewsUnfiltered();
        this.mLayout = mLayout;
        if (mLayout != null) {
            if (mLayout.mRecyclerView != null) {
                throw new IllegalArgumentException("LayoutManager " + mLayout + " is already attached to a RecyclerView: " + mLayout.mRecyclerView);
            }
            this.mLayout.setRecyclerView(this);
            if (this.mIsAttached) {
                this.mLayout.dispatchAttachedToWindow(this);
            }
        }
        this.requestLayout();
    }
    
    public void setNestedScrollingEnabled(final boolean nestedScrollingEnabled) {
        this.getScrollingChildHelper().setNestedScrollingEnabled(nestedScrollingEnabled);
    }
    
    public void setOnFlingListener(final RecyclerView$OnFlingListener mOnFlingListener) {
        this.mOnFlingListener = mOnFlingListener;
    }
    
    @Deprecated
    public void setOnScrollListener(final RecyclerView$OnScrollListener mScrollListener) {
        this.mScrollListener = mScrollListener;
    }
    
    public void setPreserveFocusAfterLayout(final boolean mPreserveFocusAfterLayout) {
        this.mPreserveFocusAfterLayout = mPreserveFocusAfterLayout;
    }
    
    public void setRecycledViewPool(final RecyclerView$RecycledViewPool recycledViewPool) {
        this.mRecycler.setRecycledViewPool(recycledViewPool);
    }
    
    public void setRecyclerListener(final RecyclerView$RecyclerListener mRecyclerListener) {
        this.mRecyclerListener = mRecyclerListener;
    }
    
    void setScrollState(final int mScrollState) {
        if (mScrollState == this.mScrollState) {
            return;
        }
        if ((this.mScrollState = mScrollState) != 2) {
            this.stopScrollersInternal();
        }
        this.dispatchOnScrollStateChanged(mScrollState);
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
                this.mTouchSlop = value.getScaledPagingTouchSlop();
            }
        }
    }
    
    public void setViewCacheExtension(final RecyclerView$ViewCacheExtension viewCacheExtension) {
        this.mRecycler.setViewCacheExtension(viewCacheExtension);
    }
    
    boolean shouldDeferAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        boolean b = false;
        final boolean b2 = false;
        if (this.isComputingLayout()) {
            int contentChangeTypes;
            if (accessibilityEvent != null) {
                contentChangeTypes = AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent);
            }
            else {
                contentChangeTypes = 0;
            }
            if (contentChangeTypes == 0) {
                contentChangeTypes = (b2 ? 1 : 0);
            }
            this.mEatenAccessibilityChangeFlags |= contentChangeTypes;
            b = true;
        }
        return b;
    }
    
    public void smoothScrollBy(int n, int n2) {
        final int n3 = 0;
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        }
        else if (!this.mLayoutFrozen) {
            if (!this.mLayout.canScrollHorizontally()) {
                n = 0;
            }
            if (!this.mLayout.canScrollVertically()) {
                n2 = n3;
            }
            if (n != 0 || n2 != 0) {
                this.mViewFlinger.smoothScrollBy(n, n2);
            }
        }
    }
    
    public void smoothScrollToPosition(final int n) {
        if (this.mLayoutFrozen) {
            return;
        }
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        this.mLayout.smoothScrollToPosition(this, this.mState, n);
    }
    
    public boolean startNestedScroll(final int n) {
        return this.getScrollingChildHelper().startNestedScroll(n);
    }
    
    public void stopNestedScroll() {
        this.getScrollingChildHelper().stopNestedScroll();
    }
    
    public void stopScroll() {
        this.setScrollState(0);
        this.stopScrollersInternal();
    }
    
    public void swapAdapter(final RecyclerView$Adapter recyclerView$Adapter, final boolean b) {
        this.setLayoutFrozen(false);
        this.setAdapterInternal(recyclerView$Adapter, true, b);
        this.setDataSetChangedAfterLayout();
        this.requestLayout();
    }
    
    void viewRangeUpdate(final int n, final int n2, final Object o) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final View unfilteredChild = this.mChildHelper.getUnfilteredChildAt(i);
            final RecyclerView$ViewHolder childViewHolderInt = getChildViewHolderInt(unfilteredChild);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= n && childViewHolderInt.mPosition < n + n2) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(o);
                ((RecyclerView$LayoutParams)unfilteredChild.getLayoutParams()).mInsetsDirty = true;
            }
        }
        this.mRecycler.viewRangeUpdate(n, n2);
    }
}
