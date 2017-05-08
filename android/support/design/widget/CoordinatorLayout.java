// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.content.ContextCompat;
import android.graphics.drawable.ColorDrawable;
import android.util.SparseArray;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import java.io.Serializable;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import java.util.HashMap;
import android.text.TextUtils;
import android.support.v4.view.GravityCompat;
import java.util.Collections;
import android.util.Log;
import android.support.v4.view.ViewCompat;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.design.R$style;
import android.support.design.R$styleable;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.view.ViewGroup$OnHierarchyChangeListener;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.WindowInsetsCompat;
import java.util.List;
import java.lang.reflect.Constructor;
import java.util.Map;
import android.view.View;
import java.util.Comparator;
import android.view.ViewGroup;

public class CoordinatorLayout extends ViewGroup
{
    static final Class<?>[] CONSTRUCTOR_PARAMS;
    static final CoordinatorLayoutInsetsHelper INSETS_HELPER;
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<CoordinatorLayout$Behavior>>> sConstructors;
    private View mBehaviorTouchView;
    private final List<View> mDependencySortedChildren;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private WindowInsetsCompat mLastInsets;
    final Comparator<View> mLayoutDependencyComparator;
    private boolean mNeedsPreDrawListener;
    private View mNestedScrollingDirectChild;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    private ViewGroup$OnHierarchyChangeListener mOnHierarchyChangeListener;
    private CoordinatorLayout$OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempDependenciesList;
    private final int[] mTempIntPair;
    private final List<View> mTempList1;
    private final Rect mTempRect1;
    private final Rect mTempRect2;
    private final Rect mTempRect3;
    
    static {
        WIDGET_PACKAGE_NAME = CoordinatorLayout.class.getPackage().getName();
        if (Build$VERSION.SDK_INT >= 21) {
            TOP_SORTED_CHILDREN_COMPARATOR = new CoordinatorLayout$ViewElevationComparator();
            INSETS_HELPER = new CoordinatorLayoutInsetsHelperLollipop();
        }
        else {
            TOP_SORTED_CHILDREN_COMPARATOR = null;
            INSETS_HELPER = null;
        }
        CONSTRUCTOR_PARAMS = new Class[] { Context.class, AttributeSet.class };
        sConstructors = new ThreadLocal<Map<String, Constructor<CoordinatorLayout$Behavior>>>();
    }
    
    public CoordinatorLayout(final Context context) {
        this(context, null);
    }
    
    public CoordinatorLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public CoordinatorLayout(final Context context, final AttributeSet set, int i) {
        final int n = 0;
        super(context, set, i);
        this.mLayoutDependencyComparator = new CoordinatorLayout$1(this);
        this.mDependencySortedChildren = new ArrayList<View>();
        this.mTempList1 = new ArrayList<View>();
        this.mTempDependenciesList = new ArrayList<View>();
        this.mTempRect1 = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRect3 = new Rect();
        this.mTempIntPair = new int[2];
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.CoordinatorLayout, i, R$style.Widget_Design_CoordinatorLayout);
        i = obtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_keylines, 0);
        if (i != 0) {
            final Resources resources = context.getResources();
            this.mKeylines = resources.getIntArray(i);
            final float density = resources.getDisplayMetrics().density;
            int length;
            int[] mKeylines;
            for (length = this.mKeylines.length, i = n; i < length; ++i) {
                mKeylines = this.mKeylines;
                mKeylines[i] *= (int)density;
            }
        }
        this.mStatusBarBackground = obtainStyledAttributes.getDrawable(R$styleable.CoordinatorLayout_statusBarBackground);
        obtainStyledAttributes.recycle();
        if (CoordinatorLayout.INSETS_HELPER != null) {
            CoordinatorLayout.INSETS_HELPER.setupForWindowInsets((View)this, new CoordinatorLayout$ApplyInsetsListener(this));
        }
        super.setOnHierarchyChangeListener((ViewGroup$OnHierarchyChangeListener)new CoordinatorLayout$HierarchyChangeListener(this));
    }
    
    private void dispatchChildApplyWindowInsets(WindowInsetsCompat onApplyWindowInsets) {
        if (!onApplyWindowInsets.isConsumed()) {
            WindowInsetsCompat dispatchApplyWindowInsets;
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i, onApplyWindowInsets = dispatchApplyWindowInsets) {
                final View child = this.getChildAt(i);
                dispatchApplyWindowInsets = onApplyWindowInsets;
                if (ViewCompat.getFitsSystemWindows(child)) {
                    final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)child.getLayoutParams()).getBehavior();
                    if (behavior != null) {
                        onApplyWindowInsets = behavior.onApplyWindowInsets(this, child, onApplyWindowInsets);
                        if (onApplyWindowInsets.isConsumed()) {
                            break;
                        }
                    }
                    dispatchApplyWindowInsets = ViewCompat.dispatchApplyWindowInsets(child, onApplyWindowInsets);
                    if (dispatchApplyWindowInsets.isConsumed()) {
                        break;
                    }
                }
            }
        }
    }
    
    private int getKeyline(final int n) {
        if (this.mKeylines == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + n);
            return 0;
        }
        if (n < 0 || n >= this.mKeylines.length) {
            Log.e("CoordinatorLayout", "Keyline index " + n + " out of range for " + this);
            return 0;
        }
        return this.mKeylines[n];
    }
    
    private void getTopSortedChildren(final List<View> list) {
        list.clear();
        final boolean childrenDrawingOrderEnabled = this.isChildrenDrawingOrderEnabled();
        final int childCount = this.getChildCount();
        for (int i = childCount - 1; i >= 0; --i) {
            int childDrawingOrder;
            if (childrenDrawingOrderEnabled) {
                childDrawingOrder = this.getChildDrawingOrder(childCount, i);
            }
            else {
                childDrawingOrder = i;
            }
            list.add(this.getChildAt(childDrawingOrder));
        }
        if (CoordinatorLayout.TOP_SORTED_CHILDREN_COMPARATOR != null) {
            Collections.sort((List<Object>)list, (Comparator<? super Object>)CoordinatorLayout.TOP_SORTED_CHILDREN_COMPARATOR);
        }
    }
    
    private void layoutChild(final View view, final int n) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        final Rect mTempRect1 = this.mTempRect1;
        mTempRect1.set(this.getPaddingLeft() + coordinatorLayout$LayoutParams.leftMargin, this.getPaddingTop() + coordinatorLayout$LayoutParams.topMargin, this.getWidth() - this.getPaddingRight() - coordinatorLayout$LayoutParams.rightMargin, this.getHeight() - this.getPaddingBottom() - coordinatorLayout$LayoutParams.bottomMargin);
        if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this) && !ViewCompat.getFitsSystemWindows(view)) {
            mTempRect1.left += this.mLastInsets.getSystemWindowInsetLeft();
            mTempRect1.top += this.mLastInsets.getSystemWindowInsetTop();
            mTempRect1.right -= this.mLastInsets.getSystemWindowInsetRight();
            mTempRect1.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
        }
        final Rect mTempRect2 = this.mTempRect2;
        GravityCompat.apply(resolveGravity(coordinatorLayout$LayoutParams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), mTempRect1, mTempRect2, n);
        view.layout(mTempRect2.left, mTempRect2.top, mTempRect2.right, mTempRect2.bottom);
    }
    
    private void layoutChildWithAnchor(final View view, final View view2, final int n) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        final Rect mTempRect1 = this.mTempRect1;
        final Rect mTempRect2 = this.mTempRect2;
        this.getDescendantRect(view2, mTempRect1);
        this.getDesiredAnchoredChildRect(view, n, mTempRect1, mTempRect2);
        view.layout(mTempRect2.left, mTempRect2.top, mTempRect2.right, mTempRect2.bottom);
    }
    
    private void layoutChildWithKeyline(final View view, int max, int max2) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(coordinatorLayout$LayoutParams.gravity), max2);
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        int n = max;
        if (max2 == 1) {
            n = width - max;
        }
        max = this.getKeyline(n) - measuredWidth;
        max2 = 0;
        switch (absoluteGravity & 0x7) {
            case 5: {
                max += measuredWidth;
                break;
            }
            case 1: {
                max += measuredWidth / 2;
                break;
            }
        }
        switch (absoluteGravity & 0x70) {
            case 80: {
                max2 = 0 + measuredHeight;
                break;
            }
            case 16: {
                max2 = 0 + measuredHeight / 2;
                break;
            }
        }
        max = Math.max(this.getPaddingLeft() + coordinatorLayout$LayoutParams.leftMargin, Math.min(max, width - this.getPaddingRight() - measuredWidth - coordinatorLayout$LayoutParams.rightMargin));
        max2 = Math.max(this.getPaddingTop() + coordinatorLayout$LayoutParams.topMargin, Math.min(max2, height - this.getPaddingBottom() - measuredHeight - coordinatorLayout$LayoutParams.bottomMargin));
        view.layout(max, max2, max + measuredWidth, max2 + measuredHeight);
    }
    
    static CoordinatorLayout$Behavior parseBehavior(final Context context, final AttributeSet set, final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        Label_0147: {
            if (!s.startsWith(".")) {
                break Label_0147;
            }
            String s2 = context.getPackageName() + s;
        Label_0070_Outer:
            while (true) {
                while (true) {
                    Label_0217: {
                        try {
                            Map<String, Constructor<CoordinatorLayout$Behavior>> map = null;
                            Label_0041: {
                                map = CoordinatorLayout.sConstructors.get();
                            }
                            if (map == null) {
                                map = new HashMap<String, Constructor<CoordinatorLayout$Behavior>>();
                                CoordinatorLayout.sConstructors.set(map);
                                Constructor<?> constructor;
                                if ((constructor = map.get(s2)) == null) {
                                    constructor = Class.forName(s2, true, context.getClassLoader()).getConstructor(CoordinatorLayout.CONSTRUCTOR_PARAMS);
                                    constructor.setAccessible(true);
                                    map.put(s2, (Constructor<CoordinatorLayout$Behavior>)constructor);
                                }
                                return constructor.newInstance(context, set);
                            }
                            break Label_0217;
                            while (true) {
                                s2 = CoordinatorLayout.WIDGET_PACKAGE_NAME + '.' + s;
                                continue Label_0070_Outer;
                                s2 = s;
                                continue;
                            }
                        }
                        // iftrue(Label_0041:, s.indexOf(46) >= 0)
                        catch (Exception ex) {
                            throw new RuntimeException("Could not inflate Behavior subclass " + s2, ex);
                        }
                    }
                    continue;
                }
            }
        }
    }
    
    private boolean performIntercept(final MotionEvent motionEvent, final int n) {
        boolean b = false;
        int n2 = 0;
        MotionEvent obtain = null;
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        final List<View> mTempList1 = this.mTempList1;
        this.getTopSortedChildren(mTempList1);
        while (true) {
            for (int size = mTempList1.size(), i = 0; i < size; ++i) {
                final View mBehaviorTouchView = mTempList1.get(i);
                final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)mBehaviorTouchView.getLayoutParams();
                final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                if ((b || n2 != 0) && actionMasked != 0) {
                    if (behavior != null) {
                        if (obtain == null) {
                            final long uptimeMillis = SystemClock.uptimeMillis();
                            obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                        }
                        switch (n) {
                            case 0: {
                                behavior.onInterceptTouchEvent(this, mBehaviorTouchView, obtain);
                                break;
                            }
                            case 1: {
                                behavior.onTouchEvent(this, mBehaviorTouchView, obtain);
                                break;
                            }
                        }
                    }
                }
                else {
                    boolean b2 = b;
                    if (!b) {
                        b2 = b;
                        if (behavior != null) {
                            switch (n) {
                                case 0: {
                                    b = behavior.onInterceptTouchEvent(this, mBehaviorTouchView, motionEvent);
                                    break;
                                }
                                case 1: {
                                    b = behavior.onTouchEvent(this, mBehaviorTouchView, motionEvent);
                                    break;
                                }
                            }
                            b2 = b;
                            if (b) {
                                this.mBehaviorTouchView = mBehaviorTouchView;
                                b2 = b;
                            }
                        }
                    }
                    b = b2;
                    final boolean didBlockInteraction = coordinatorLayout$LayoutParams.didBlockInteraction();
                    final boolean blockingInteractionBelow = coordinatorLayout$LayoutParams.isBlockingInteractionBelow(this, mBehaviorTouchView);
                    if (blockingInteractionBelow && !didBlockInteraction) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    if (blockingInteractionBelow && n2 == 0) {
                        mTempList1.clear();
                        return b;
                    }
                }
            }
            continue;
        }
    }
    
    private void prepareChildren() {
        final int n = 0;
        final int childCount = this.getChildCount();
        int n2;
        if (this.mDependencySortedChildren.size() != childCount) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        int n3;
        for (int i = 0; i < childCount; ++i, n2 = n3) {
            final View child = this.getChildAt(i);
            final CoordinatorLayout$LayoutParams resolvedLayoutParams = this.getResolvedLayoutParams(child);
            if ((n3 = n2) == 0) {
                n3 = n2;
                if (resolvedLayoutParams.isDirty(this, child)) {
                    n3 = 1;
                }
            }
            resolvedLayoutParams.findAnchorView(this, child);
        }
        if (n2 != 0) {
            this.mDependencySortedChildren.clear();
            for (int j = n; j < childCount; ++j) {
                this.mDependencySortedChildren.add(this.getChildAt(j));
            }
            Collections.sort(this.mDependencySortedChildren, this.mLayoutDependencyComparator);
        }
    }
    
    private void resetTouchBehaviors() {
        int i = 0;
        if (this.mBehaviorTouchView != null) {
            final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior();
            if (behavior != null) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                behavior.onTouchEvent(this, this.mBehaviorTouchView, obtain);
                obtain.recycle();
            }
            this.mBehaviorTouchView = null;
        }
        while (i < this.getChildCount()) {
            ((CoordinatorLayout$LayoutParams)this.getChildAt(i).getLayoutParams()).resetTouchBehaviorTracking();
            ++i;
        }
    }
    
    private static int resolveAnchoredChildGravity(final int n) {
        int n2 = n;
        if (n == 0) {
            n2 = 17;
        }
        return n2;
    }
    
    private static int resolveGravity(final int n) {
        int n2 = n;
        if (n == 0) {
            n2 = 8388659;
        }
        return n2;
    }
    
    private static int resolveKeylineGravity(final int n) {
        int n2 = n;
        if (n == 0) {
            n2 = 8388661;
        }
        return n2;
    }
    
    private void setWindowInsets(final WindowInsetsCompat mLastInsets) {
        final boolean b = true;
        if (this.mLastInsets != mLastInsets) {
            this.mLastInsets = mLastInsets;
            this.mDrawStatusBarBackground = (mLastInsets != null && mLastInsets.getSystemWindowInsetTop() > 0);
            this.setWillNotDraw(!this.mDrawStatusBarBackground && this.getBackground() == null && b);
            this.dispatchChildApplyWindowInsets(mLastInsets);
            this.requestLayout();
        }
    }
    
    void addPreDrawListener() {
        if (this.mIsAttachedToWindow) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new CoordinatorLayout$OnPreDrawListener(this);
            }
            this.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof CoordinatorLayout$LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams);
    }
    
    void dispatchDependentViewRemoved(final View view) {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)child.getLayoutParams()).getBehavior();
            if (behavior != null && behavior.layoutDependsOn(this, child, view)) {
                behavior.onDependentViewRemoved(this, child, view);
            }
        }
    }
    
    public void dispatchDependentViewsChanged(final View view) {
        final int size = this.mDependencySortedChildren.size();
        int i = 0;
        boolean b = false;
        while (i < size) {
            final View view2 = this.mDependencySortedChildren.get(i);
            if (view2 == view) {
                b = true;
            }
            else if (b) {
                final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view2.getLayoutParams();
                final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                if (behavior != null && coordinatorLayout$LayoutParams.dependsOn(this, view2, view)) {
                    behavior.onDependentViewChanged(this, view2, view);
                }
            }
            ++i;
        }
    }
    
    void dispatchOnDependentViewChanged(final boolean b) {
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        for (int size = this.mDependencySortedChildren.size(), i = 0; i < size; ++i) {
            final View view = this.mDependencySortedChildren.get(i);
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
            for (int j = 0; j < i; ++j) {
                if (coordinatorLayout$LayoutParams.mAnchorDirectChild == this.mDependencySortedChildren.get(j)) {
                    this.offsetChildToAnchor(view, layoutDirection);
                }
            }
            final Rect mTempRect1 = this.mTempRect1;
            final Rect mTempRect2 = this.mTempRect2;
            this.getLastChildRect(view, mTempRect1);
            this.getChildRect(view, true, mTempRect2);
            if (!mTempRect1.equals((Object)mTempRect2)) {
                this.recordLastChildRect(view, mTempRect2);
                for (int k = i + 1; k < size; ++k) {
                    final View view2 = this.mDependencySortedChildren.get(k);
                    final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams2 = (CoordinatorLayout$LayoutParams)view2.getLayoutParams();
                    final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams2.getBehavior();
                    if (behavior != null && behavior.layoutDependsOn(this, view2, view)) {
                        if (!b && coordinatorLayout$LayoutParams2.getChangedAfterNestedScroll()) {
                            coordinatorLayout$LayoutParams2.resetChangedAfterNestedScroll();
                        }
                        else {
                            final boolean onDependentViewChanged = behavior.onDependentViewChanged(this, view2, view);
                            if (b) {
                                coordinatorLayout$LayoutParams2.setChangedAfterNestedScroll(onDependentViewChanged);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean doViewsOverlap(final View view, final View view2) {
        if (view.getVisibility() == 0 && view2.getVisibility() == 0) {
            final Rect mTempRect1 = this.mTempRect1;
            this.getChildRect(view, view.getParent() != this, mTempRect1);
            final Rect mTempRect2 = this.mTempRect2;
            this.getChildRect(view2, view2.getParent() != this, mTempRect2);
            return mTempRect1.left <= mTempRect2.right && mTempRect1.top <= mTempRect2.bottom && mTempRect1.right >= mTempRect2.left && mTempRect1.bottom >= mTempRect2.top;
        }
        return false;
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        if (coordinatorLayout$LayoutParams.mBehavior != null && coordinatorLayout$LayoutParams.mBehavior.getScrimOpacity(this, view) > 0.0f) {
            if (this.mScrimPaint == null) {
                this.mScrimPaint = new Paint();
            }
            this.mScrimPaint.setColor(coordinatorLayout$LayoutParams.mBehavior.getScrimColor(this, view));
            canvas.drawRect((float)this.getPaddingLeft(), (float)this.getPaddingTop(), (float)(this.getWidth() - this.getPaddingRight()), (float)(this.getHeight() - this.getPaddingBottom()), this.mScrimPaint);
        }
        return super.drawChild(canvas, view, n);
    }
    
    void ensurePreDrawListener() {
        final boolean b = false;
        final int childCount = this.getChildCount();
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= childCount) {
                break;
            }
            if (this.hasDependencies(this.getChildAt(n))) {
                b2 = true;
                break;
            }
            ++n;
        }
        if (b2 != this.mNeedsPreDrawListener) {
            if (!b2) {
                this.removePreDrawListener();
                return;
            }
            this.addPreDrawListener();
        }
    }
    
    protected CoordinatorLayout$LayoutParams generateDefaultLayoutParams() {
        return new CoordinatorLayout$LayoutParams(-2, -2);
    }
    
    public CoordinatorLayout$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new CoordinatorLayout$LayoutParams(this.getContext(), set);
    }
    
    protected CoordinatorLayout$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof CoordinatorLayout$LayoutParams) {
            return new CoordinatorLayout$LayoutParams((CoordinatorLayout$LayoutParams)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new CoordinatorLayout$LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new CoordinatorLayout$LayoutParams(viewGroup$LayoutParams);
    }
    
    void getChildRect(final View view, final boolean b, final Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.set(0, 0, 0, 0);
            return;
        }
        if (b) {
            this.getDescendantRect(view, rect);
            return;
        }
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }
    
    public List<View> getDependencies(final View view) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        final List<View> mTempDependenciesList = this.mTempDependenciesList;
        mTempDependenciesList.clear();
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child != view && coordinatorLayout$LayoutParams.dependsOn(this, view, child)) {
                mTempDependenciesList.add(child);
            }
        }
        return mTempDependenciesList;
    }
    
    void getDescendantRect(final View view, final Rect rect) {
        ViewGroupUtils.getDescendantRect(this, view, rect);
    }
    
    void getDesiredAnchoredChildRect(final View view, int n, final Rect rect, final Rect rect2) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(coordinatorLayout$LayoutParams.gravity), n);
        final int absoluteGravity2 = GravityCompat.getAbsoluteGravity(resolveGravity(coordinatorLayout$LayoutParams.anchorGravity), n);
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        switch (absoluteGravity2 & 0x7) {
            default: {
                n = rect.left;
                break;
            }
            case 5: {
                n = rect.right;
                break;
            }
            case 1: {
                n = rect.left;
                n += rect.width() / 2;
                break;
            }
        }
        int n2 = 0;
        switch (absoluteGravity2 & 0x70) {
            default: {
                n2 = rect.top;
                break;
            }
            case 80: {
                n2 = rect.bottom;
                break;
            }
            case 16: {
                n2 = rect.top + rect.height() / 2;
                break;
            }
        }
        int n3 = n;
        switch (absoluteGravity & 0x7) {
            case 1: {
                n3 = n - measuredWidth / 2;
            }
            default: {
                n3 = n - measuredWidth;
            }
            case 5: {
                n = n2;
                switch (absoluteGravity & 0x70) {
                    case 16: {
                        n = n2 - measuredHeight / 2;
                    }
                    default: {
                        n = n2 - measuredHeight;
                    }
                    case 80: {
                        final int width = this.getWidth();
                        final int height = this.getHeight();
                        final int max = Math.max(this.getPaddingLeft() + coordinatorLayout$LayoutParams.leftMargin, Math.min(n3, width - this.getPaddingRight() - measuredWidth - coordinatorLayout$LayoutParams.rightMargin));
                        n = Math.max(this.getPaddingTop() + coordinatorLayout$LayoutParams.topMargin, Math.min(n, height - this.getPaddingBottom() - measuredHeight - coordinatorLayout$LayoutParams.bottomMargin));
                        rect2.set(max, n, max + measuredWidth, n + measuredHeight);
                        return;
                    }
                }
                break;
            }
        }
    }
    
    void getLastChildRect(final View view, final Rect rect) {
        rect.set(((CoordinatorLayout$LayoutParams)view.getLayoutParams()).getLastChildRect());
    }
    
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }
    
    CoordinatorLayout$LayoutParams getResolvedLayoutParams(final View view) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        if (coordinatorLayout$LayoutParams.mBehaviorResolved) {
            return coordinatorLayout$LayoutParams;
        }
        Serializable s = view.getClass();
        CoordinatorLayout$DefaultBehavior coordinatorLayout$DefaultBehavior = null;
        CoordinatorLayout$DefaultBehavior coordinatorLayout$DefaultBehavior2;
        while (true) {
            coordinatorLayout$DefaultBehavior2 = coordinatorLayout$DefaultBehavior;
            if (s == null) {
                break;
            }
            coordinatorLayout$DefaultBehavior = ((Class<? extends View>)s).getAnnotation(CoordinatorLayout$DefaultBehavior.class);
            if ((coordinatorLayout$DefaultBehavior2 = coordinatorLayout$DefaultBehavior) != null) {
                break;
            }
            s = ((Class<? extends View>)s).getSuperclass();
        }
        while (true) {
            if (coordinatorLayout$DefaultBehavior2 == null) {
                break Label_0076;
            }
            try {
                coordinatorLayout$LayoutParams.setBehavior((CoordinatorLayout$Behavior)coordinatorLayout$DefaultBehavior2.value().newInstance());
                coordinatorLayout$LayoutParams.mBehaviorResolved = true;
                return coordinatorLayout$LayoutParams;
            }
            catch (Exception ex) {
                Log.e("CoordinatorLayout", "Default behavior class " + coordinatorLayout$DefaultBehavior2.value().getName() + " could not be instantiated. Did you forget a default constructor?", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }
    
    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), this.getPaddingTop() + this.getPaddingBottom());
    }
    
    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), this.getPaddingLeft() + this.getPaddingRight());
    }
    
    boolean hasDependencies(final View view) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        if (coordinatorLayout$LayoutParams.mAnchorView != null) {
            return true;
        }
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child != view && coordinatorLayout$LayoutParams.dependsOn(this, view, child)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isPointInChildBounds(final View view, final int n, final int n2) {
        final Rect mTempRect1 = this.mTempRect1;
        this.getDescendantRect(view, mTempRect1);
        return mTempRect1.contains(n, n2);
    }
    
    void offsetChildToAnchor(final View view, int n) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        if (coordinatorLayout$LayoutParams.mAnchorView != null) {
            final Rect mTempRect1 = this.mTempRect1;
            final Rect mTempRect2 = this.mTempRect2;
            final Rect mTempRect3 = this.mTempRect3;
            this.getDescendantRect(coordinatorLayout$LayoutParams.mAnchorView, mTempRect1);
            this.getChildRect(view, false, mTempRect2);
            this.getDesiredAnchoredChildRect(view, n, mTempRect1, mTempRect3);
            n = mTempRect3.left - mTempRect2.left;
            final int n2 = mTempRect3.top - mTempRect2.top;
            if (n != 0) {
                view.offsetLeftAndRight(n);
            }
            if (n2 != 0) {
                view.offsetTopAndBottom(n2);
            }
            if (n != 0 || n2 != 0) {
                final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onDependentViewChanged(this, view, coordinatorLayout$LayoutParams.mAnchorView);
                }
            }
        }
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.resetTouchBehaviors();
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new CoordinatorLayout$OnPreDrawListener(this);
            }
            this.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null && ViewCompat.getFitsSystemWindows((View)this)) {
            ViewCompat.requestApplyInsets((View)this);
        }
        this.mIsAttachedToWindow = true;
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.resetTouchBehaviors();
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            this.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        if (this.mNestedScrollingTarget != null) {
            this.onStopNestedScroll(this.mNestedScrollingTarget);
        }
        this.mIsAttachedToWindow = false;
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            int systemWindowInsetTop;
            if (this.mLastInsets != null) {
                systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
            }
            else {
                systemWindowInsetTop = 0;
            }
            if (systemWindowInsetTop > 0) {
                this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), systemWindowInsetTop);
                this.mStatusBarBackground.draw(canvas);
            }
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.resetTouchBehaviors();
        }
        final boolean performIntercept = this.performIntercept(motionEvent, 0);
        if (false) {
            throw new NullPointerException();
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.resetTouchBehaviors();
        }
        return performIntercept;
    }
    
    protected void onLayout(final boolean b, int i, int layoutDirection, int size, final int n) {
        layoutDirection = ViewCompat.getLayoutDirection((View)this);
        View view;
        CoordinatorLayout$Behavior behavior;
        for (size = this.mDependencySortedChildren.size(), i = 0; i < size; ++i) {
            view = this.mDependencySortedChildren.get(i);
            behavior = ((CoordinatorLayout$LayoutParams)view.getLayoutParams()).getBehavior();
            if (behavior == null || !behavior.onLayoutChild(this, view, layoutDirection)) {
                this.onLayoutChild(view, layoutDirection);
            }
        }
    }
    
    public void onLayoutChild(final View view, final int n) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        if (coordinatorLayout$LayoutParams.checkAnchorChanged()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        if (coordinatorLayout$LayoutParams.mAnchorView != null) {
            this.layoutChildWithAnchor(view, coordinatorLayout$LayoutParams.mAnchorView, n);
            return;
        }
        if (coordinatorLayout$LayoutParams.keyline >= 0) {
            this.layoutChildWithKeyline(view, coordinatorLayout$LayoutParams.keyline, n);
            return;
        }
        this.layoutChild(view, n);
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.prepareChildren();
        this.ensurePreDrawListener();
        final int paddingLeft = this.getPaddingLeft();
        final int paddingTop = this.getPaddingTop();
        final int paddingRight = this.getPaddingRight();
        final int paddingBottom = this.getPaddingBottom();
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        boolean b;
        if (layoutDirection == 1) {
            b = true;
        }
        else {
            b = false;
        }
        final int mode = View$MeasureSpec.getMode(n);
        final int size = View$MeasureSpec.getSize(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        final int size2 = View$MeasureSpec.getSize(n2);
        int n3 = this.getSuggestedMinimumWidth();
        int n4 = this.getSuggestedMinimumHeight();
        boolean b2;
        if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this)) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final int size3 = this.mDependencySortedChildren.size();
        int i = 0;
        int combineMeasuredStates = 0;
        while (i < size3) {
            final View view = this.mDependencySortedChildren.get(i);
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
            int n6;
            final int n5 = n6 = 0;
            Label_0236: {
                if (coordinatorLayout$LayoutParams.keyline >= 0) {
                    n6 = n5;
                    if (mode != 0) {
                        final int keyline = this.getKeyline(coordinatorLayout$LayoutParams.keyline);
                        final int n7 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(coordinatorLayout$LayoutParams.gravity), layoutDirection) & 0x7;
                        if ((n7 == 3 && !b) || (n7 == 5 && b)) {
                            n6 = Math.max(0, size - paddingRight - keyline);
                        }
                        else {
                            if (n7 != 5 || b) {
                                n6 = n5;
                                if (n7 != 3) {
                                    break Label_0236;
                                }
                                n6 = n5;
                                if (!b) {
                                    break Label_0236;
                                }
                            }
                            n6 = Math.max(0, keyline - paddingLeft);
                        }
                    }
                }
            }
            int measureSpec;
            int measureSpec2;
            if (b2 && !ViewCompat.getFitsSystemWindows(view)) {
                final int systemWindowInsetLeft = this.mLastInsets.getSystemWindowInsetLeft();
                final int systemWindowInsetRight = this.mLastInsets.getSystemWindowInsetRight();
                final int systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
                final int systemWindowInsetBottom = this.mLastInsets.getSystemWindowInsetBottom();
                measureSpec = View$MeasureSpec.makeMeasureSpec(size - (systemWindowInsetLeft + systemWindowInsetRight), mode);
                measureSpec2 = View$MeasureSpec.makeMeasureSpec(size2 - (systemWindowInsetTop + systemWindowInsetBottom), mode2);
            }
            else {
                measureSpec2 = n2;
                measureSpec = n;
            }
            final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
            if (behavior == null || !behavior.onMeasureChild(this, view, measureSpec, n6, measureSpec2, 0)) {
                this.onMeasureChild(view, measureSpec, n6, measureSpec2, 0);
            }
            n3 = Math.max(n3, view.getMeasuredWidth() + (paddingLeft + paddingRight) + coordinatorLayout$LayoutParams.leftMargin + coordinatorLayout$LayoutParams.rightMargin);
            n4 = Math.max(n4, view.getMeasuredHeight() + (paddingTop + paddingBottom) + coordinatorLayout$LayoutParams.topMargin + coordinatorLayout$LayoutParams.bottomMargin);
            combineMeasuredStates = ViewCompat.combineMeasuredStates(combineMeasuredStates, ViewCompat.getMeasuredState(view));
            ++i;
        }
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(n3, n, 0xFF000000 & combineMeasuredStates), ViewCompat.resolveSizeAndState(n4, n2, combineMeasuredStates << 16));
    }
    
    public void onMeasureChild(final View view, final int n, final int n2, final int n3, final int n4) {
        this.measureChildWithMargins(view, n, n2, n3, n4);
    }
    
    public boolean onNestedFling(final View view, final float n, final float n2, final boolean b) {
        final int childCount = this.getChildCount();
        int i = 0;
        boolean b2 = false;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
            if (coordinatorLayout$LayoutParams.isNestedScrollAccepted()) {
                final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                if (behavior != null) {
                    b2 |= behavior.onNestedFling(this, child, view, n, n2, b);
                }
            }
            ++i;
        }
        if (b2) {
            this.dispatchOnDependentViewChanged(true);
        }
        return b2;
    }
    
    public boolean onNestedPreFling(final View view, final float n, final float n2) {
        final int childCount = this.getChildCount();
        int i = 0;
        boolean b = false;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
            if (coordinatorLayout$LayoutParams.isNestedScrollAccepted()) {
                final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                if (behavior != null) {
                    b |= behavior.onNestedPreFling(this, child, view, n, n2);
                }
            }
            ++i;
        }
        return b;
    }
    
    public void onNestedPreScroll(final View view, final int n, final int n2, final int[] array) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n12;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; i = n12) {
            final View child = this.getChildAt(i);
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
            int n7;
            int n8;
            int n9;
            if (!coordinatorLayout$LayoutParams.isNestedScrollAccepted()) {
                final int n6 = n5;
                n7 = n3;
                n8 = n4;
                n9 = n6;
            }
            else {
                final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                if (behavior != null) {
                    this.mTempIntPair[this.mTempIntPair[1] = 0] = 0;
                    behavior.onNestedPreScroll(this, child, view, n, n2, this.mTempIntPair);
                    if (n > 0) {
                        n7 = Math.max(n3, this.mTempIntPair[0]);
                    }
                    else {
                        n7 = Math.min(n3, this.mTempIntPair[0]);
                    }
                    int n10;
                    if (n2 > 0) {
                        n10 = Math.max(n4, this.mTempIntPair[1]);
                    }
                    else {
                        n10 = Math.min(n4, this.mTempIntPair[1]);
                    }
                    n8 = n10;
                    n9 = 1;
                }
                else {
                    final int n11 = n5;
                    n7 = n3;
                    n8 = n4;
                    n9 = n11;
                }
            }
            n12 = i + 1;
            final int n13 = n7;
            n5 = n9;
            n4 = n8;
            n3 = n13;
        }
        array[0] = n3;
        array[1] = n4;
        if (n5 != 0) {
            this.dispatchOnDependentViewChanged(true);
        }
    }
    
    public void onNestedScroll(final View view, final int n, final int n2, final int n3, final int n4) {
        final int childCount = this.getChildCount();
        boolean b = false;
        for (int i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
            if (coordinatorLayout$LayoutParams.isNestedScrollAccepted()) {
                final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onNestedScroll(this, child, view, n, n2, n3, n4);
                    b = true;
                }
            }
        }
        if (b) {
            this.dispatchOnDependentViewChanged(true);
        }
    }
    
    public void onNestedScrollAccepted(final View mNestedScrollingDirectChild, final View mNestedScrollingTarget, final int n) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(mNestedScrollingDirectChild, mNestedScrollingTarget, n);
        this.mNestedScrollingDirectChild = mNestedScrollingDirectChild;
        this.mNestedScrollingTarget = mNestedScrollingTarget;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
            if (coordinatorLayout$LayoutParams.isNestedScrollAccepted()) {
                final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onNestedScrollAccepted(this, child, mNestedScrollingDirectChild, mNestedScrollingTarget, n);
                }
            }
        }
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        final CoordinatorLayout$SavedState coordinatorLayout$SavedState = (CoordinatorLayout$SavedState)parcelable;
        super.onRestoreInstanceState(coordinatorLayout$SavedState.getSuperState());
        final SparseArray<Parcelable> behaviorStates = coordinatorLayout$SavedState.behaviorStates;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final int id = child.getId();
            final CoordinatorLayout$Behavior behavior = this.getResolvedLayoutParams(child).getBehavior();
            if (id != -1 && behavior != null) {
                final Parcelable parcelable2 = (Parcelable)behaviorStates.get(id);
                if (parcelable2 != null) {
                    behavior.onRestoreInstanceState(this, child, parcelable2);
                }
            }
        }
    }
    
    protected Parcelable onSaveInstanceState() {
        final CoordinatorLayout$SavedState coordinatorLayout$SavedState = new CoordinatorLayout$SavedState(super.onSaveInstanceState());
        final SparseArray behaviorStates = new SparseArray();
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final int id = child.getId();
            final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)child.getLayoutParams()).getBehavior();
            if (id != -1 && behavior != null) {
                final Parcelable onSaveInstanceState = behavior.onSaveInstanceState(this, child);
                if (onSaveInstanceState != null) {
                    behaviorStates.append(id, (Object)onSaveInstanceState);
                }
            }
        }
        coordinatorLayout$SavedState.behaviorStates = (SparseArray<Parcelable>)behaviorStates;
        return (Parcelable)coordinatorLayout$SavedState;
    }
    
    public boolean onStartNestedScroll(final View view, final View view2, final int n) {
        final int childCount = this.getChildCount();
        int i = 0;
        boolean b = false;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
            final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
            if (behavior != null) {
                final boolean onStartNestedScroll = behavior.onStartNestedScroll(this, child, view, view2, n);
                b |= onStartNestedScroll;
                coordinatorLayout$LayoutParams.acceptNestedScroll(onStartNestedScroll);
            }
            else {
                coordinatorLayout$LayoutParams.acceptNestedScroll(false);
            }
            ++i;
        }
        return b;
    }
    
    public void onStopNestedScroll(final View view) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view);
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
            if (coordinatorLayout$LayoutParams.isNestedScrollAccepted()) {
                final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onStopNestedScroll(this, child, view);
                }
                coordinatorLayout$LayoutParams.resetNestedScroll();
                coordinatorLayout$LayoutParams.resetChangedAfterNestedScroll();
            }
        }
        this.mNestedScrollingDirectChild = null;
        this.mNestedScrollingTarget = null;
    }
    
    public boolean onTouchEvent(MotionEvent obtain) {
        final MotionEvent motionEvent = null;
        final int actionMasked = MotionEventCompat.getActionMasked(obtain);
        int performIntercept = 0;
        boolean b = false;
        Label_0060: {
            if (this.mBehaviorTouchView == null) {
                performIntercept = (this.performIntercept(obtain, 1) ? 1 : 0);
                if (performIntercept == 0) {
                    b = false;
                    break Label_0060;
                }
            }
            else {
                performIntercept = 0;
            }
            final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior();
            b = (behavior != null && behavior.onTouchEvent(this, this.mBehaviorTouchView, obtain));
        }
        boolean b2;
        if (this.mBehaviorTouchView == null) {
            b2 = (b | super.onTouchEvent(obtain));
            obtain = motionEvent;
        }
        else {
            obtain = motionEvent;
            b2 = b;
            if (performIntercept != 0) {
                if (!false) {
                    final long uptimeMillis = SystemClock.uptimeMillis();
                    obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                else {
                    obtain = null;
                }
                super.onTouchEvent(obtain);
                b2 = b;
            }
        }
        if (b2 || actionMasked == 0) {}
        if (obtain != null) {
            obtain.recycle();
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.resetTouchBehaviors();
        }
        return b2;
    }
    
    void recordLastChildRect(final View view, final Rect lastChildRect) {
        ((CoordinatorLayout$LayoutParams)view.getLayoutParams()).setLastChildRect(lastChildRect);
    }
    
    void removePreDrawListener() {
        if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            this.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        super.requestDisallowInterceptTouchEvent(b);
        if (b) {
            this.resetTouchBehaviors();
        }
    }
    
    public void setOnHierarchyChangeListener(final ViewGroup$OnHierarchyChangeListener mOnHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = mOnHierarchyChangeListener;
    }
    
    public void setStatusBarBackground(final Drawable mStatusBarBackground) {
        this.mStatusBarBackground = mStatusBarBackground;
        this.invalidate();
    }
    
    public void setStatusBarBackgroundColor(final int n) {
        this.setStatusBarBackground((Drawable)new ColorDrawable(n));
    }
    
    public void setStatusBarBackgroundResource(final int n) {
        Drawable drawable;
        if (n != 0) {
            drawable = ContextCompat.getDrawable(this.getContext(), n);
        }
        else {
            drawable = null;
        }
        this.setStatusBarBackground(drawable);
    }
}
