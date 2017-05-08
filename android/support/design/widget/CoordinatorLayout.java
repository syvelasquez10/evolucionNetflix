// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.content.ContextCompat;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.drawable.Drawable$Callback;
import android.util.SparseArray;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import java.io.Serializable;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.Region$Op;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.util.Collection;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import java.util.HashMap;
import android.text.TextUtils;
import java.util.Collections;
import android.util.Log;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.R$style;
import android.support.design.R$styleable;
import java.util.ArrayList;
import android.support.v4.util.Pools$SynchronizedPool;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.view.ViewGroup$OnHierarchyChangeListener;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.WindowInsetsCompat;
import java.util.List;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.graphics.Rect;
import android.support.v4.util.Pools$Pool;
import java.lang.reflect.Constructor;
import java.util.Map;
import android.view.View;
import java.util.Comparator;
import android.support.v4.view.NestedScrollingParent;
import android.view.ViewGroup;

public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent
{
    static final Class<?>[] CONSTRUCTOR_PARAMS;
    static final int EVENT_NESTED_SCROLL = 1;
    static final int EVENT_PRE_DRAW = 0;
    static final int EVENT_VIEW_REMOVED = 2;
    static final String TAG = "CoordinatorLayout";
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<CoordinatorLayout$Behavior>>> sConstructors;
    private static final Pools$Pool<Rect> sRectPool;
    private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    private View mBehaviorTouchView;
    private final DirectedAcyclicGraph<View> mChildDag;
    private final List<View> mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private WindowInsetsCompat mLastInsets;
    private boolean mNeedsPreDrawListener;
    private View mNestedScrollingDirectChild;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    ViewGroup$OnHierarchyChangeListener mOnHierarchyChangeListener;
    private CoordinatorLayout$OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempDependenciesList;
    private final int[] mTempIntPair;
    private final List<View> mTempList1;
    
    static {
        final Package package1 = CoordinatorLayout.class.getPackage();
        String name;
        if (package1 != null) {
            name = package1.getName();
        }
        else {
            name = null;
        }
        WIDGET_PACKAGE_NAME = name;
        if (Build$VERSION.SDK_INT >= 21) {
            TOP_SORTED_CHILDREN_COMPARATOR = new CoordinatorLayout$ViewElevationComparator();
        }
        else {
            TOP_SORTED_CHILDREN_COMPARATOR = null;
        }
        CONSTRUCTOR_PARAMS = new Class[] { Context.class, AttributeSet.class };
        sConstructors = new ThreadLocal<Map<String, Constructor<CoordinatorLayout$Behavior>>>();
        sRectPool = new Pools$SynchronizedPool<Rect>(12);
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
        this.mDependencySortedChildren = new ArrayList<View>();
        this.mChildDag = new DirectedAcyclicGraph<View>();
        this.mTempList1 = new ArrayList<View>();
        this.mTempDependenciesList = new ArrayList<View>();
        this.mTempIntPair = new int[2];
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        ThemeUtils.checkAppCompatTheme(context);
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
        this.setupForInsets();
        super.setOnHierarchyChangeListener((ViewGroup$OnHierarchyChangeListener)new CoordinatorLayout$HierarchyChangeListener(this));
    }
    
    private static Rect acquireTempRect() {
        Rect rect;
        if ((rect = CoordinatorLayout.sRectPool.acquire()) == null) {
            rect = new Rect();
        }
        return rect;
    }
    
    private void constrainChildRect(final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams, final Rect rect, final int n, final int n2) {
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int max = Math.max(this.getPaddingLeft() + coordinatorLayout$LayoutParams.leftMargin, Math.min(rect.left, width - this.getPaddingRight() - n - coordinatorLayout$LayoutParams.rightMargin));
        final int max2 = Math.max(this.getPaddingTop() + coordinatorLayout$LayoutParams.topMargin, Math.min(rect.top, height - this.getPaddingBottom() - n2 - coordinatorLayout$LayoutParams.bottomMargin));
        rect.set(max, max2, max + n, max2 + n2);
    }
    
    private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat onApplyWindowInsets) {
        if (onApplyWindowInsets.isConsumed()) {
            return onApplyWindowInsets;
        }
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (ViewCompat.getFitsSystemWindows(child)) {
                final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)child.getLayoutParams()).getBehavior();
                if (behavior != null) {
                    final WindowInsetsCompat windowInsetsCompat = onApplyWindowInsets = behavior.onApplyWindowInsets(this, child, onApplyWindowInsets);
                    if (windowInsetsCompat.isConsumed()) {
                        return windowInsetsCompat;
                    }
                }
            }
        }
        return onApplyWindowInsets;
    }
    
    private void getDesiredAnchoredChildRectWithoutConstraints(final View view, int n, final Rect rect, final Rect rect2, final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams, final int n2, final int n3) {
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(coordinatorLayout$LayoutParams.gravity), n);
        final int absoluteGravity2 = GravityCompat.getAbsoluteGravity(resolveGravity(coordinatorLayout$LayoutParams.anchorGravity), n);
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
        int n4 = 0;
        switch (absoluteGravity2 & 0x70) {
            default: {
                n4 = rect.top;
                break;
            }
            case 80: {
                n4 = rect.bottom;
                break;
            }
            case 16: {
                n4 = rect.top + rect.height() / 2;
                break;
            }
        }
        int n5 = n;
        switch (absoluteGravity & 0x7) {
            case 1: {
                n5 = n - n2 / 2;
            }
            default: {
                n5 = n - n2;
            }
            case 5: {
                n = n4;
                switch (absoluteGravity & 0x70) {
                    case 16: {
                        n = n4 - n3 / 2;
                    }
                    default: {
                        n = n4 - n3;
                    }
                    case 80: {
                        rect2.set(n5, n, n5 + n2, n + n3);
                        return;
                    }
                }
                break;
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
    
    private boolean hasDependencies(final View view) {
        return this.mChildDag.hasOutgoingEdges(view);
    }
    
    private void layoutChild(final View view, final int n) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        final Rect acquireTempRect = acquireTempRect();
        acquireTempRect.set(this.getPaddingLeft() + coordinatorLayout$LayoutParams.leftMargin, this.getPaddingTop() + coordinatorLayout$LayoutParams.topMargin, this.getWidth() - this.getPaddingRight() - coordinatorLayout$LayoutParams.rightMargin, this.getHeight() - this.getPaddingBottom() - coordinatorLayout$LayoutParams.bottomMargin);
        if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this) && !ViewCompat.getFitsSystemWindows(view)) {
            acquireTempRect.left += this.mLastInsets.getSystemWindowInsetLeft();
            acquireTempRect.top += this.mLastInsets.getSystemWindowInsetTop();
            acquireTempRect.right -= this.mLastInsets.getSystemWindowInsetRight();
            acquireTempRect.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
        }
        final Rect acquireTempRect2 = acquireTempRect();
        GravityCompat.apply(resolveGravity(coordinatorLayout$LayoutParams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), acquireTempRect, acquireTempRect2, n);
        view.layout(acquireTempRect2.left, acquireTempRect2.top, acquireTempRect2.right, acquireTempRect2.bottom);
        releaseTempRect(acquireTempRect);
        releaseTempRect(acquireTempRect2);
    }
    
    private void layoutChildWithAnchor(final View view, final View view2, final int n) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        final Rect acquireTempRect = acquireTempRect();
        final Rect acquireTempRect2 = acquireTempRect();
        try {
            this.getDescendantRect(view2, acquireTempRect);
            this.getDesiredAnchoredChildRect(view, n, acquireTempRect, acquireTempRect2);
            view.layout(acquireTempRect2.left, acquireTempRect2.top, acquireTempRect2.right, acquireTempRect2.bottom);
        }
        finally {
            releaseTempRect(acquireTempRect);
            releaseTempRect(acquireTempRect2);
        }
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
    
    private void offsetChildByInset(final View view, final Rect rect, int n) {
        if (!ViewCompat.isLaidOut(view) || view.getWidth() <= 0 || view.getHeight() <= 0) {
            return;
        }
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
        final Rect acquireTempRect = acquireTempRect();
        final Rect acquireTempRect2 = acquireTempRect();
        acquireTempRect2.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if (behavior != null && behavior.getInsetDodgeRect(this, view, acquireTempRect)) {
            if (!acquireTempRect2.contains(acquireTempRect)) {
                throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + acquireTempRect.toShortString() + " | Bounds:" + acquireTempRect2.toShortString());
            }
        }
        else {
            acquireTempRect.set(acquireTempRect2);
        }
        releaseTempRect(acquireTempRect2);
        if (acquireTempRect.isEmpty()) {
            releaseTempRect(acquireTempRect);
            return;
        }
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(coordinatorLayout$LayoutParams.dodgeInsetEdges, n);
        while (true) {
            Label_0441: {
                if ((absoluteGravity & 0x30) != 0x30) {
                    break Label_0441;
                }
                n = acquireTempRect.top - coordinatorLayout$LayoutParams.topMargin - coordinatorLayout$LayoutParams.mInsetOffsetY;
                if (n >= rect.top) {
                    break Label_0441;
                }
                this.setInsetOffsetY(view, rect.top - n);
                n = 1;
                int n2 = n;
                if ((absoluteGravity & 0x50) == 0x50) {
                    final int n3 = this.getHeight() - acquireTempRect.bottom - coordinatorLayout$LayoutParams.bottomMargin + coordinatorLayout$LayoutParams.mInsetOffsetY;
                    n2 = n;
                    if (n3 < rect.bottom) {
                        this.setInsetOffsetY(view, n3 - rect.bottom);
                        n2 = 1;
                    }
                }
                if (n2 == 0) {
                    this.setInsetOffsetY(view, 0);
                }
                while (true) {
                    Label_0436: {
                        if ((absoluteGravity & 0x3) != 0x3) {
                            break Label_0436;
                        }
                        n = acquireTempRect.left - coordinatorLayout$LayoutParams.leftMargin - coordinatorLayout$LayoutParams.mInsetOffsetX;
                        if (n >= rect.left) {
                            break Label_0436;
                        }
                        this.setInsetOffsetX(view, rect.left - n);
                        n = 1;
                        if ((absoluteGravity & 0x5) == 0x5) {
                            final int n4 = coordinatorLayout$LayoutParams.mInsetOffsetX + (this.getWidth() - acquireTempRect.right - coordinatorLayout$LayoutParams.rightMargin);
                            if (n4 < rect.right) {
                                this.setInsetOffsetX(view, n4 - rect.right);
                                n = 1;
                            }
                        }
                        while (true) {
                            if (n == 0) {
                                this.setInsetOffsetX(view, 0);
                            }
                            releaseTempRect(acquireTempRect);
                            return;
                            continue;
                        }
                    }
                    n = 0;
                    continue;
                }
            }
            n = 0;
            continue;
        }
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
                    Label_0228: {
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
                            break Label_0228;
                            s2 = s;
                            // iftrue(Label_0041:, s.indexOf(46) >= 0)
                            s2 = s;
                            // iftrue(Label_0041:, TextUtils.isEmpty((CharSequence)CoordinatorLayout.WIDGET_PACKAGE_NAME))
                            s2 = CoordinatorLayout.WIDGET_PACKAGE_NAME + '.' + s;
                            continue Label_0070_Outer;
                        }
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
        this.mDependencySortedChildren.clear();
        this.mChildDag.clear();
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            this.getResolvedLayoutParams(child).findAnchorView(this, child);
            this.mChildDag.addNode(child);
            for (int j = 0; j < childCount; ++j) {
                if (j != i) {
                    final View child2 = this.getChildAt(j);
                    if (this.getResolvedLayoutParams(child2).dependsOn(this, child2, child)) {
                        if (!this.mChildDag.contains(child2)) {
                            this.mChildDag.addNode(child2);
                        }
                        this.mChildDag.addEdge(child, child2);
                    }
                }
            }
        }
        this.mDependencySortedChildren.addAll(this.mChildDag.getSortedList());
        Collections.reverse(this.mDependencySortedChildren);
    }
    
    private static void releaseTempRect(final Rect rect) {
        rect.setEmpty();
        CoordinatorLayout.sRectPool.release(rect);
    }
    
    private void resetTouchBehaviors() {
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
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            ((CoordinatorLayout$LayoutParams)this.getChildAt(i).getLayoutParams()).resetTouchBehaviorTracking();
        }
        this.mDisallowInterceptReset = false;
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
    
    private void setInsetOffsetX(final View view, final int mInsetOffsetX) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        if (coordinatorLayout$LayoutParams.mInsetOffsetX != mInsetOffsetX) {
            ViewCompat.offsetLeftAndRight(view, mInsetOffsetX - coordinatorLayout$LayoutParams.mInsetOffsetX);
            coordinatorLayout$LayoutParams.mInsetOffsetX = mInsetOffsetX;
        }
    }
    
    private void setInsetOffsetY(final View view, final int mInsetOffsetY) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        if (coordinatorLayout$LayoutParams.mInsetOffsetY != mInsetOffsetY) {
            ViewCompat.offsetTopAndBottom(view, mInsetOffsetY - coordinatorLayout$LayoutParams.mInsetOffsetY);
            coordinatorLayout$LayoutParams.mInsetOffsetY = mInsetOffsetY;
        }
    }
    
    private void setupForInsets() {
        if (Build$VERSION.SDK_INT < 21) {
            return;
        }
        if (ViewCompat.getFitsSystemWindows((View)this)) {
            if (this.mApplyWindowInsetsListener == null) {
                this.mApplyWindowInsetsListener = new CoordinatorLayout$1(this);
            }
            ViewCompat.setOnApplyWindowInsetsListener((View)this, this.mApplyWindowInsetsListener);
            this.setSystemUiVisibility(1280);
            return;
        }
        ViewCompat.setOnApplyWindowInsetsListener((View)this, null);
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
    
    public void dispatchDependentViewsChanged(final View view) {
        final List incomingEdges = this.mChildDag.getIncomingEdges(view);
        if (incomingEdges != null && !incomingEdges.isEmpty()) {
            for (int i = 0; i < incomingEdges.size(); ++i) {
                final View view2 = incomingEdges.get(i);
                final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)view2.getLayoutParams()).getBehavior();
                if (behavior != null) {
                    behavior.onDependentViewChanged(this, view2, view);
                }
            }
        }
    }
    
    public boolean doViewsOverlap(View acquireTempRect, final View view) {
        final boolean b = true;
        if (acquireTempRect.getVisibility() == 0 && view.getVisibility() == 0) {
            final Rect acquireTempRect2 = acquireTempRect();
            while (true) {
                while (true) {
                    boolean b2 = false;
                    Label_0033: {
                        if (acquireTempRect.getParent() != this) {
                            b2 = true;
                            break Label_0033;
                        }
                        Label_0135: {
                            break Label_0135;
                            while (true) {
                                this.getChildRect(view, b2, (Rect)acquireTempRect);
                                try {
                                    b2 = (acquireTempRect2.left <= ((Rect)acquireTempRect).right && acquireTempRect2.top <= ((Rect)acquireTempRect).bottom && acquireTempRect2.right >= ((Rect)acquireTempRect).left && acquireTempRect2.bottom >= ((Rect)acquireTempRect).top && b);
                                    return b2;
                                    b2 = false;
                                    continue;
                                    b2 = false;
                                    break;
                                }
                                finally {
                                    releaseTempRect(acquireTempRect2);
                                    releaseTempRect((Rect)acquireTempRect);
                                }
                                return false;
                            }
                        }
                    }
                    this.getChildRect(acquireTempRect, b2, acquireTempRect2);
                    acquireTempRect = (View)acquireTempRect();
                    if (view.getParent() != this) {
                        b2 = true;
                        continue;
                    }
                    break;
                }
                continue;
            }
        }
        return false;
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        if (coordinatorLayout$LayoutParams.mBehavior != null) {
            final float scrimOpacity = coordinatorLayout$LayoutParams.mBehavior.getScrimOpacity(this, view);
            if (scrimOpacity > 0.0f) {
                if (this.mScrimPaint == null) {
                    this.mScrimPaint = new Paint();
                }
                this.mScrimPaint.setColor(coordinatorLayout$LayoutParams.mBehavior.getScrimColor(this, view));
                this.mScrimPaint.setAlpha(MathUtils.constrain(Math.round(scrimOpacity * 255.0f), 0, 255));
                final int save = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect((float)view.getLeft(), (float)view.getTop(), (float)view.getRight(), (float)view.getBottom(), Region$Op.DIFFERENCE);
                }
                canvas.drawRect((float)this.getPaddingLeft(), (float)this.getPaddingTop(), (float)(this.getWidth() - this.getPaddingRight()), (float)(this.getHeight() - this.getPaddingBottom()), this.mScrimPaint);
                canvas.restoreToCount(save);
            }
        }
        return super.drawChild(canvas, view, n);
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        final int[] drawableState = this.getDrawableState();
        final boolean b = false;
        final Drawable mStatusBarBackground = this.mStatusBarBackground;
        boolean b2 = b;
        if (mStatusBarBackground != null) {
            b2 = b;
            if (mStatusBarBackground.isStateful()) {
                b2 = (false | mStatusBarBackground.setState(drawableState));
            }
        }
        if (b2) {
            this.invalidate();
        }
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
            rect.setEmpty();
            return;
        }
        if (b) {
            this.getDescendantRect(view, rect);
            return;
        }
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }
    
    public List<View> getDependencies(final View view) {
        final List outgoingEdges = this.mChildDag.getOutgoingEdges(view);
        this.mTempDependenciesList.clear();
        if (outgoingEdges != null) {
            this.mTempDependenciesList.addAll(outgoingEdges);
        }
        return this.mTempDependenciesList;
    }
    
    final List<View> getDependencySortedChildren() {
        this.prepareChildren();
        return Collections.unmodifiableList((List<? extends View>)this.mDependencySortedChildren);
    }
    
    public List<View> getDependents(final View view) {
        final List incomingEdges = this.mChildDag.getIncomingEdges(view);
        this.mTempDependenciesList.clear();
        if (incomingEdges != null) {
            this.mTempDependenciesList.addAll(incomingEdges);
        }
        return this.mTempDependenciesList;
    }
    
    void getDescendantRect(final View view, final Rect rect) {
        ViewGroupUtils.getDescendantRect(this, view, rect);
    }
    
    void getDesiredAnchoredChildRect(final View view, final int n, final Rect rect, final Rect rect2) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        this.getDesiredAnchoredChildRectWithoutConstraints(view, n, rect, rect2, coordinatorLayout$LayoutParams, measuredWidth, measuredHeight);
        this.constrainChildRect(coordinatorLayout$LayoutParams, rect2, measuredWidth, measuredHeight);
    }
    
    void getLastChildRect(final View view, final Rect rect) {
        rect.set(((CoordinatorLayout$LayoutParams)view.getLayoutParams()).getLastChildRect());
    }
    
    final WindowInsetsCompat getLastWindowInsets() {
        return this.mLastInsets;
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
    
    public boolean isPointInChildBounds(final View view, final int n, final int n2) {
        final Rect acquireTempRect = acquireTempRect();
        this.getDescendantRect(view, acquireTempRect);
        try {
            return acquireTempRect.contains(n, n2);
        }
        finally {
            releaseTempRect(acquireTempRect);
        }
    }
    
    void offsetChildToAnchor(final View view, int n) {
        final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
        if (coordinatorLayout$LayoutParams.mAnchorView != null) {
            final Rect acquireTempRect = acquireTempRect();
            final Rect acquireTempRect2 = acquireTempRect();
            final Rect acquireTempRect3 = acquireTempRect();
            this.getDescendantRect(coordinatorLayout$LayoutParams.mAnchorView, acquireTempRect);
            this.getChildRect(view, false, acquireTempRect2);
            final int measuredWidth = view.getMeasuredWidth();
            final int measuredHeight = view.getMeasuredHeight();
            this.getDesiredAnchoredChildRectWithoutConstraints(view, n, acquireTempRect, acquireTempRect3, coordinatorLayout$LayoutParams, measuredWidth, measuredHeight);
            if (acquireTempRect3.left != acquireTempRect2.left || acquireTempRect3.top != acquireTempRect2.top) {
                n = 1;
            }
            else {
                n = 0;
            }
            this.constrainChildRect(coordinatorLayout$LayoutParams, acquireTempRect3, measuredWidth, measuredHeight);
            final int n2 = acquireTempRect3.left - acquireTempRect2.left;
            final int n3 = acquireTempRect3.top - acquireTempRect2.top;
            if (n2 != 0) {
                ViewCompat.offsetLeftAndRight(view, n2);
            }
            if (n3 != 0) {
                ViewCompat.offsetTopAndBottom(view, n3);
            }
            if (n != 0) {
                final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                if (behavior != null) {
                    behavior.onDependentViewChanged(this, view, coordinatorLayout$LayoutParams.mAnchorView);
                }
            }
            releaseTempRect(acquireTempRect);
            releaseTempRect(acquireTempRect2);
            releaseTempRect(acquireTempRect3);
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
    
    final void onChildViewsChanged(final int n) {
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        final int size = this.mDependencySortedChildren.size();
        final Rect acquireTempRect = acquireTempRect();
        final Rect acquireTempRect2 = acquireTempRect();
        final Rect acquireTempRect3 = acquireTempRect();
        for (int i = 0; i < size; ++i) {
            final View view = this.mDependencySortedChildren.get(i);
            final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
            if (n != 0 || view.getVisibility() != 8) {
                for (int j = 0; j < i; ++j) {
                    if (coordinatorLayout$LayoutParams.mAnchorDirectChild == this.mDependencySortedChildren.get(j)) {
                        this.offsetChildToAnchor(view, layoutDirection);
                    }
                }
                this.getChildRect(view, true, acquireTempRect2);
                if (coordinatorLayout$LayoutParams.insetEdge != 0 && !acquireTempRect2.isEmpty()) {
                    final int absoluteGravity = GravityCompat.getAbsoluteGravity(coordinatorLayout$LayoutParams.insetEdge, layoutDirection);
                    switch (absoluteGravity & 0x70) {
                        case 48: {
                            acquireTempRect.top = Math.max(acquireTempRect.top, acquireTempRect2.bottom);
                            break;
                        }
                        case 80: {
                            acquireTempRect.bottom = Math.max(acquireTempRect.bottom, this.getHeight() - acquireTempRect2.top);
                            break;
                        }
                    }
                    switch (absoluteGravity & 0x7) {
                        case 3: {
                            acquireTempRect.left = Math.max(acquireTempRect.left, acquireTempRect2.right);
                            break;
                        }
                        case 5: {
                            acquireTempRect.right = Math.max(acquireTempRect.right, this.getWidth() - acquireTempRect2.left);
                            break;
                        }
                    }
                }
                if (coordinatorLayout$LayoutParams.dodgeInsetEdges != 0 && view.getVisibility() == 0) {
                    this.offsetChildByInset(view, acquireTempRect, layoutDirection);
                }
                if (n == 0) {
                    this.getLastChildRect(view, acquireTempRect3);
                    if (acquireTempRect3.equals((Object)acquireTempRect2)) {
                        continue;
                    }
                    this.recordLastChildRect(view, acquireTempRect2);
                }
                for (int k = i + 1; k < size; ++k) {
                    final View view2 = this.mDependencySortedChildren.get(k);
                    final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams2 = (CoordinatorLayout$LayoutParams)view2.getLayoutParams();
                    final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams2.getBehavior();
                    if (behavior != null && behavior.layoutDependsOn(this, view2, view)) {
                        if (n == 0 && coordinatorLayout$LayoutParams2.getChangedAfterNestedScroll()) {
                            coordinatorLayout$LayoutParams2.resetChangedAfterNestedScroll();
                        }
                        else {
                            boolean onDependentViewChanged = false;
                            switch (n) {
                                default: {
                                    onDependentViewChanged = behavior.onDependentViewChanged(this, view2, view);
                                    break;
                                }
                                case 2: {
                                    behavior.onDependentViewRemoved(this, view2, view);
                                    onDependentViewChanged = true;
                                    break;
                                }
                            }
                            if (n == 1) {
                                coordinatorLayout$LayoutParams2.setChangedAfterNestedScroll(onDependentViewChanged);
                            }
                        }
                    }
                }
            }
        }
        releaseTempRect(acquireTempRect);
        releaseTempRect(acquireTempRect2);
        releaseTempRect(acquireTempRect3);
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
            if (view.getVisibility() != 8) {
                behavior = ((CoordinatorLayout$LayoutParams)view.getLayoutParams()).getBehavior();
                if (behavior == null || !behavior.onLayoutChild(this, view, layoutDirection)) {
                    this.onLayoutChild(view, layoutDirection);
                }
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
        final boolean b = layoutDirection == 1;
        final int mode = View$MeasureSpec.getMode(n);
        final int size = View$MeasureSpec.getSize(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        final int size2 = View$MeasureSpec.getSize(n2);
        int suggestedMinimumWidth = this.getSuggestedMinimumWidth();
        int n3 = this.getSuggestedMinimumHeight();
        int n4 = 0;
        final boolean b2 = this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this);
        int n6;
        int n7;
        int n11;
        for (int size3 = this.mDependencySortedChildren.size(), i = 0; i < size3; ++i, n11 = n6, n4 = n7, suggestedMinimumWidth = n11) {
            final View view = this.mDependencySortedChildren.get(i);
            if (view.getVisibility() == 8) {
                final int n5 = n4;
                n6 = suggestedMinimumWidth;
                n7 = n5;
            }
            else {
                final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)view.getLayoutParams();
                int n9;
                final int n8 = n9 = 0;
                Label_0289: {
                    if (coordinatorLayout$LayoutParams.keyline >= 0) {
                        n9 = n8;
                        if (mode != 0) {
                            final int keyline = this.getKeyline(coordinatorLayout$LayoutParams.keyline);
                            final int n10 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(coordinatorLayout$LayoutParams.gravity), layoutDirection) & 0x7;
                            if ((n10 == 3 && !b) || (n10 == 5 && b)) {
                                n9 = Math.max(0, size - paddingRight - keyline);
                            }
                            else {
                                if (n10 != 5 || b) {
                                    n9 = n8;
                                    if (n10 != 3) {
                                        break Label_0289;
                                    }
                                    n9 = n8;
                                    if (!b) {
                                        break Label_0289;
                                    }
                                }
                                n9 = Math.max(0, keyline - paddingLeft);
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
                if (behavior == null || !behavior.onMeasureChild(this, view, measureSpec, n9, measureSpec2, 0)) {
                    this.onMeasureChild(view, measureSpec, n9, measureSpec2, 0);
                }
                final int max = Math.max(suggestedMinimumWidth, view.getMeasuredWidth() + (paddingLeft + paddingRight) + coordinatorLayout$LayoutParams.leftMargin + coordinatorLayout$LayoutParams.rightMargin);
                n3 = Math.max(n3, view.getMeasuredHeight() + (paddingTop + paddingBottom) + coordinatorLayout$LayoutParams.topMargin + coordinatorLayout$LayoutParams.bottomMargin);
                final int combineMeasuredStates = ViewCompat.combineMeasuredStates(n4, ViewCompat.getMeasuredState(view));
                n6 = max;
                n7 = combineMeasuredStates;
            }
        }
        this.setMeasuredDimension(ViewCompat.resolveSizeAndState(suggestedMinimumWidth, n, 0xFF000000 & n4), ViewCompat.resolveSizeAndState(n3, n2, n4 << 16));
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
            if (child.getVisibility() != 8) {
                final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
                if (coordinatorLayout$LayoutParams.isNestedScrollAccepted()) {
                    final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                    if (behavior != null) {
                        b2 |= behavior.onNestedFling(this, child, view, n, n2, b);
                    }
                }
            }
            ++i;
        }
        if (b2) {
            this.onChildViewsChanged(1);
        }
        return b2;
    }
    
    public boolean onNestedPreFling(final View view, final float n, final float n2) {
        final int childCount = this.getChildCount();
        int i = 0;
        boolean b = false;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
                if (coordinatorLayout$LayoutParams.isNestedScrollAccepted()) {
                    final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                    if (behavior != null) {
                        b |= behavior.onNestedPreFling(this, child, view, n, n2);
                    }
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
        int n13;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; i = n13) {
            final View child = this.getChildAt(i);
            int n7;
            int n8;
            int n9;
            if (child.getVisibility() == 8) {
                final int n6 = n5;
                n7 = n3;
                n8 = n4;
                n9 = n6;
            }
            else {
                final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
                if (!coordinatorLayout$LayoutParams.isNestedScrollAccepted()) {
                    final int n10 = n5;
                    n7 = n3;
                    n8 = n4;
                    n9 = n10;
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
                        int n11;
                        if (n2 > 0) {
                            n11 = Math.max(n4, this.mTempIntPair[1]);
                        }
                        else {
                            n11 = Math.min(n4, this.mTempIntPair[1]);
                        }
                        n8 = n11;
                        n9 = 1;
                    }
                    else {
                        final int n12 = n5;
                        n7 = n3;
                        n8 = n4;
                        n9 = n12;
                    }
                }
            }
            n13 = i + 1;
            final int n14 = n7;
            n5 = n9;
            n4 = n8;
            n3 = n14;
        }
        array[0] = n3;
        array[1] = n4;
        if (n5 != 0) {
            this.onChildViewsChanged(1);
        }
    }
    
    public void onNestedScroll(final View view, final int n, final int n2, final int n3, final int n4) {
        final int childCount = this.getChildCount();
        boolean b = false;
        for (int i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                final CoordinatorLayout$LayoutParams coordinatorLayout$LayoutParams = (CoordinatorLayout$LayoutParams)child.getLayoutParams();
                if (coordinatorLayout$LayoutParams.isNestedScrollAccepted()) {
                    final CoordinatorLayout$Behavior behavior = coordinatorLayout$LayoutParams.getBehavior();
                    if (behavior != null) {
                        behavior.onNestedScroll(this, child, view, n, n2, n3, n4);
                        b = true;
                    }
                }
            }
        }
        if (b) {
            this.onChildViewsChanged(1);
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
        if (!(parcelable instanceof CoordinatorLayout$SavedState)) {
            super.onRestoreInstanceState(parcelable);
        }
        else {
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
            if (child.getVisibility() != 8) {
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
    
    public boolean requestChildRectangleOnScreen(final View view, final Rect rect, final boolean b) {
        final CoordinatorLayout$Behavior behavior = ((CoordinatorLayout$LayoutParams)view.getLayoutParams()).getBehavior();
        return (behavior != null && behavior.onRequestChildRectangleOnScreen(this, view, rect, b)) || super.requestChildRectangleOnScreen(view, rect, b);
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        super.requestDisallowInterceptTouchEvent(b);
        if (b && !this.mDisallowInterceptReset) {
            this.resetTouchBehaviors();
            this.mDisallowInterceptReset = true;
        }
    }
    
    public void setFitsSystemWindows(final boolean fitsSystemWindows) {
        super.setFitsSystemWindows(fitsSystemWindows);
        this.setupForInsets();
    }
    
    public void setOnHierarchyChangeListener(final ViewGroup$OnHierarchyChangeListener mOnHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = mOnHierarchyChangeListener;
    }
    
    public void setStatusBarBackground(Drawable mStatusBarBackground) {
        Drawable mutate = null;
        if (this.mStatusBarBackground != mStatusBarBackground) {
            if (this.mStatusBarBackground != null) {
                this.mStatusBarBackground.setCallback((Drawable$Callback)null);
            }
            if (mStatusBarBackground != null) {
                mutate = mStatusBarBackground.mutate();
            }
            this.mStatusBarBackground = mutate;
            if (this.mStatusBarBackground != null) {
                if (this.mStatusBarBackground.isStateful()) {
                    this.mStatusBarBackground.setState(this.getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection((View)this));
                mStatusBarBackground = this.mStatusBarBackground;
                mStatusBarBackground.setVisible(this.getVisibility() == 0, false);
                this.mStatusBarBackground.setCallback((Drawable$Callback)this);
            }
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
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
    
    public void setVisibility(final int visibility) {
        super.setVisibility(visibility);
        final boolean b = visibility == 0;
        if (this.mStatusBarBackground != null && this.mStatusBarBackground.isVisible() != b) {
            this.mStatusBarBackground.setVisible(b, false);
        }
    }
    
    final WindowInsetsCompat setWindowInsets(final WindowInsetsCompat mLastInsets) {
        final boolean b = true;
        WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors = mLastInsets;
        if (!ViewUtils.objectEquals(this.mLastInsets, mLastInsets)) {
            this.mLastInsets = mLastInsets;
            this.mDrawStatusBarBackground = (mLastInsets != null && mLastInsets.getSystemWindowInsetTop() > 0);
            this.setWillNotDraw(!this.mDrawStatusBarBackground && this.getBackground() == null && b);
            dispatchApplyWindowInsetsToBehaviors = this.dispatchApplyWindowInsetsToBehaviors(mLastInsets);
            this.requestLayout();
        }
        return dispatchApplyWindowInsetsToBehaviors;
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mStatusBarBackground;
    }
}
