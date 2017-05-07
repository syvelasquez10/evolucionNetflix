// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.support.v4.view.KeyEventCompat;
import android.view.KeyEvent;
import android.support.v4.view.MotionEventCompat;
import android.support.annotation.Nullable;
import android.view.ViewGroup$MarginLayoutParams;
import android.support.v4.view.GravityCompat;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.view.ViewGroup;

public class DrawerLayout extends ViewGroup implements DrawerLayoutImpl
{
    private static final boolean ALLOW_EDGE_LOCK = false;
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;
    static final DrawerLayout$DrawerLayoutCompatImpl IMPL;
    private static final int[] LAYOUT_ATTRS;
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 64;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 160;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final float TOUCH_SLOP_SENSITIVITY = 1.0f;
    private final DrawerLayout$ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private boolean mDrawStatusBarBackground;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private final DrawerLayout$ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    private DrawerLayout$DrawerListener mListener;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mMinDrawerMargin;
    private final DrawerLayout$ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowLeft;
    private Drawable mShadowRight;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;
    
    static {
        LAYOUT_ATTRS = new int[] { 16842931 };
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = new DrawerLayout$DrawerLayoutCompatImplApi21();
            return;
        }
        IMPL = new DrawerLayout$DrawerLayoutCompatImplBase();
    }
    
    public DrawerLayout(final Context context) {
        this(context, null);
    }
    
    public DrawerLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public DrawerLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mChildAccessibilityDelegate = new DrawerLayout$ChildAccessibilityDelegate(this);
        this.mScrimColor = -1728053248;
        this.mScrimPaint = new Paint();
        this.mFirstLayout = true;
        this.setDescendantFocusability(262144);
        final float density = this.getResources().getDisplayMetrics().density;
        this.mMinDrawerMargin = (int)(64.0f * density + 0.5f);
        final float n2 = density * 400.0f;
        this.mLeftCallback = new DrawerLayout$ViewDragCallback(this, 3);
        this.mRightCallback = new DrawerLayout$ViewDragCallback(this, 5);
        (this.mLeftDragger = ViewDragHelper.create(this, 1.0f, this.mLeftCallback)).setEdgeTrackingEnabled(1);
        this.mLeftDragger.setMinVelocity(n2);
        this.mLeftCallback.setDragger(this.mLeftDragger);
        (this.mRightDragger = ViewDragHelper.create(this, 1.0f, this.mRightCallback)).setEdgeTrackingEnabled(2);
        this.mRightDragger.setMinVelocity(n2);
        this.mRightCallback.setDragger(this.mRightDragger);
        this.setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility((View)this, 1);
        ViewCompat.setAccessibilityDelegate((View)this, new DrawerLayout$AccessibilityDelegate(this));
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
        if (ViewCompat.getFitsSystemWindows((View)this)) {
            DrawerLayout.IMPL.configureApplyInsets((View)this);
        }
    }
    
    private View findVisibleDrawer() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (this.isDrawerView(child) && this.isDrawerVisible(child)) {
                return child;
            }
        }
        return null;
    }
    
    static String gravityToString(final int n) {
        if ((n & 0x3) == 0x3) {
            return "LEFT";
        }
        if ((n & 0x5) == 0x5) {
            return "RIGHT";
        }
        return Integer.toHexString(n);
    }
    
    private static boolean hasOpaqueBackground(final View view) {
        final boolean b = false;
        final Drawable background = view.getBackground();
        boolean b2 = b;
        if (background != null) {
            b2 = b;
            if (background.getOpacity() == -1) {
                b2 = true;
            }
        }
        return b2;
    }
    
    private boolean hasPeekingDrawer() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            if (((DrawerLayout$LayoutParams)this.getChildAt(i).getLayoutParams()).isPeeking) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hasVisibleDrawer() {
        return this.findVisibleDrawer() != null;
    }
    
    private static boolean includeChildForAccessibility(final View view) {
        return ViewCompat.getImportantForAccessibility(view) != 4 && ViewCompat.getImportantForAccessibility(view) != 2;
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (n > 0 || (n < 0 && this.getChildCount() > 0)) {
            ViewCompat.setImportantForAccessibility(view, 4);
            ViewCompat.setAccessibilityDelegate(view, this.mChildAccessibilityDelegate);
        }
        else {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
        super.addView(view, n, viewGroup$LayoutParams);
    }
    
    void cancelChildViewTouch() {
        int i = 0;
        if (!this.mChildrenCanceledTouch) {
            final long uptimeMillis = SystemClock.uptimeMillis();
            final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            while (i < this.getChildCount()) {
                this.getChildAt(i).dispatchTouchEvent(obtain);
                ++i;
            }
            obtain.recycle();
            this.mChildrenCanceledTouch = true;
        }
    }
    
    boolean checkDrawerViewAbsoluteGravity(final View view, final int n) {
        return (this.getDrawerViewAbsoluteGravity(view) & n) == n;
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof DrawerLayout$LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams);
    }
    
    public void closeDrawer(final int n) {
        final View drawerWithGravity = this.findDrawerWithGravity(n);
        if (drawerWithGravity == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(n));
        }
        this.closeDrawer(drawerWithGravity);
    }
    
    public void closeDrawer(final View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        if (this.mFirstLayout) {
            final DrawerLayout$LayoutParams drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)view.getLayoutParams();
            drawerLayout$LayoutParams.onScreen = 0.0f;
            drawerLayout$LayoutParams.knownOpen = false;
        }
        else if (this.checkDrawerViewAbsoluteGravity(view, 3)) {
            this.mLeftDragger.smoothSlideViewTo(view, -view.getWidth(), view.getTop());
        }
        else {
            this.mRightDragger.smoothSlideViewTo(view, this.getWidth(), view.getTop());
        }
        this.invalidate();
    }
    
    public void closeDrawers() {
        this.closeDrawers(false);
    }
    
    void closeDrawers(final boolean b) {
        final int childCount = this.getChildCount();
        int i = 0;
        boolean b2 = false;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            final DrawerLayout$LayoutParams drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)child.getLayoutParams();
            boolean b3 = b2;
            if (this.isDrawerView(child)) {
                if (b && !drawerLayout$LayoutParams.isPeeking) {
                    b3 = b2;
                }
                else {
                    final int width = child.getWidth();
                    boolean b4;
                    if (this.checkDrawerViewAbsoluteGravity(child, 3)) {
                        b4 = (b2 | this.mLeftDragger.smoothSlideViewTo(child, -width, child.getTop()));
                    }
                    else {
                        b4 = (b2 | this.mRightDragger.smoothSlideViewTo(child, this.getWidth(), child.getTop()));
                    }
                    drawerLayout$LayoutParams.isPeeking = false;
                    b3 = b4;
                }
            }
            ++i;
            b2 = b3;
        }
        this.mLeftCallback.removeCallbacks();
        this.mRightCallback.removeCallbacks();
        if (b2) {
            this.invalidate();
        }
    }
    
    public void computeScroll() {
        final int childCount = this.getChildCount();
        float max = 0.0f;
        for (int i = 0; i < childCount; ++i) {
            max = Math.max(max, ((DrawerLayout$LayoutParams)this.getChildAt(i).getLayoutParams()).onScreen);
        }
        this.mScrimOpacity = max;
        if (this.mLeftDragger.continueSettling(true) | this.mRightDragger.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    void dispatchOnDrawerClosed(View rootView) {
        final DrawerLayout$LayoutParams drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)rootView.getLayoutParams();
        if (drawerLayout$LayoutParams.knownOpen) {
            drawerLayout$LayoutParams.knownOpen = false;
            if (this.mListener != null) {
                this.mListener.onDrawerClosed(rootView);
            }
            final View child = this.getChildAt(0);
            if (child != null) {
                ViewCompat.setImportantForAccessibility(child, 1);
            }
            ViewCompat.setImportantForAccessibility(rootView, 4);
            if (this.hasWindowFocus()) {
                rootView = this.getRootView();
                if (rootView != null) {
                    rootView.sendAccessibilityEvent(32);
                }
            }
        }
    }
    
    void dispatchOnDrawerOpened(final View view) {
        final DrawerLayout$LayoutParams drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)view.getLayoutParams();
        if (!drawerLayout$LayoutParams.knownOpen) {
            drawerLayout$LayoutParams.knownOpen = true;
            if (this.mListener != null) {
                this.mListener.onDrawerOpened(view);
            }
            final View child = this.getChildAt(0);
            if (child != null) {
                ViewCompat.setImportantForAccessibility(child, 4);
            }
            ViewCompat.setImportantForAccessibility(view, 1);
            this.sendAccessibilityEvent(32);
            view.requestFocus();
        }
    }
    
    void dispatchOnDrawerSlide(final View view, final float n) {
        if (this.mListener != null) {
            this.mListener.onDrawerSlide(view, n);
        }
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        final int height = this.getHeight();
        final boolean contentView = this.isContentView(view);
        int n2 = 0;
        final int n3 = 0;
        int width = this.getWidth();
        final int save = canvas.save();
        int n4 = width;
        if (contentView) {
            final int childCount = this.getChildCount();
            int i = 0;
            n2 = n3;
        Label_0117_Outer:
            while (i < childCount) {
                final View child = this.getChildAt(i);
                while (true) {
                    Label_0191: {
                        if (child == view || child.getVisibility() != 0 || !hasOpaqueBackground(child) || !this.isDrawerView(child)) {
                            break Label_0191;
                        }
                        int n5;
                        int left;
                        if (child.getHeight() < height) {
                            n5 = n2;
                            left = width;
                        }
                        else if (this.checkDrawerViewAbsoluteGravity(child, 3)) {
                            int right = child.getRight();
                            if (right <= n2) {
                                right = n2;
                            }
                            n5 = right;
                            left = width;
                        }
                        else {
                            left = child.getLeft();
                            n5 = n2;
                            if (left >= width) {
                                break Label_0191;
                            }
                        }
                        ++i;
                        width = left;
                        n2 = n5;
                        continue Label_0117_Outer;
                    }
                    int left = width;
                    int n5 = n2;
                    continue;
                }
            }
            canvas.clipRect(n2, 0, width, this.getHeight());
            n4 = width;
        }
        final boolean drawChild = super.drawChild(canvas, view, n);
        canvas.restoreToCount(save);
        if (this.mScrimOpacity > 0.0f && contentView) {
            this.mScrimPaint.setColor((int)(((this.mScrimColor & 0xFF000000) >>> 24) * this.mScrimOpacity) << 24 | (this.mScrimColor & 0xFFFFFF));
            canvas.drawRect((float)n2, 0.0f, (float)n4, (float)this.getHeight(), this.mScrimPaint);
        }
        else {
            if (this.mShadowLeft != null && this.checkDrawerViewAbsoluteGravity(view, 3)) {
                final int intrinsicWidth = this.mShadowLeft.getIntrinsicWidth();
                final int right2 = view.getRight();
                final float max = Math.max(0.0f, Math.min(right2 / this.mLeftDragger.getEdgeSize(), 1.0f));
                this.mShadowLeft.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
                this.mShadowLeft.setAlpha((int)(255.0f * max));
                this.mShadowLeft.draw(canvas);
                return drawChild;
            }
            if (this.mShadowRight != null && this.checkDrawerViewAbsoluteGravity(view, 5)) {
                final int intrinsicWidth2 = this.mShadowRight.getIntrinsicWidth();
                final int left2 = view.getLeft();
                final float max2 = Math.max(0.0f, Math.min((this.getWidth() - left2) / this.mRightDragger.getEdgeSize(), 1.0f));
                this.mShadowRight.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
                this.mShadowRight.setAlpha((int)(255.0f * max2));
                this.mShadowRight.draw(canvas);
                return drawChild;
            }
        }
        return drawChild;
    }
    
    View findDrawerWithGravity(int i) {
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection((View)this));
        int childCount;
        View child;
        for (childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            if ((this.getDrawerViewAbsoluteGravity(child) & 0x7) == (absoluteGravity & 0x7)) {
                return child;
            }
        }
        return null;
    }
    
    View findOpenDrawer() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (((DrawerLayout$LayoutParams)child.getLayoutParams()).knownOpen) {
                return child;
            }
        }
        return null;
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return (ViewGroup$LayoutParams)new DrawerLayout$LayoutParams(-1, -1);
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return (ViewGroup$LayoutParams)new DrawerLayout$LayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof DrawerLayout$LayoutParams) {
            return (ViewGroup$LayoutParams)new DrawerLayout$LayoutParams((DrawerLayout$LayoutParams)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return (ViewGroup$LayoutParams)new DrawerLayout$LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return (ViewGroup$LayoutParams)new DrawerLayout$LayoutParams(viewGroup$LayoutParams);
    }
    
    public int getDrawerLockMode(int absoluteGravity) {
        absoluteGravity = GravityCompat.getAbsoluteGravity(absoluteGravity, ViewCompat.getLayoutDirection((View)this));
        if (absoluteGravity == 3) {
            return this.mLockModeLeft;
        }
        if (absoluteGravity == 5) {
            return this.mLockModeRight;
        }
        return 0;
    }
    
    public int getDrawerLockMode(final View view) {
        final int drawerViewAbsoluteGravity = this.getDrawerViewAbsoluteGravity(view);
        if (drawerViewAbsoluteGravity == 3) {
            return this.mLockModeLeft;
        }
        if (drawerViewAbsoluteGravity == 5) {
            return this.mLockModeRight;
        }
        return 0;
    }
    
    @Nullable
    public CharSequence getDrawerTitle(int absoluteGravity) {
        absoluteGravity = GravityCompat.getAbsoluteGravity(absoluteGravity, ViewCompat.getLayoutDirection((View)this));
        if (absoluteGravity == 3) {
            return this.mTitleLeft;
        }
        if (absoluteGravity == 5) {
            return this.mTitleRight;
        }
        return null;
    }
    
    int getDrawerViewAbsoluteGravity(final View view) {
        return GravityCompat.getAbsoluteGravity(((DrawerLayout$LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection((View)this));
    }
    
    float getDrawerViewOffset(final View view) {
        return ((DrawerLayout$LayoutParams)view.getLayoutParams()).onScreen;
    }
    
    boolean isContentView(final View view) {
        return ((DrawerLayout$LayoutParams)view.getLayoutParams()).gravity == 0;
    }
    
    public boolean isDrawerOpen(final int n) {
        final View drawerWithGravity = this.findDrawerWithGravity(n);
        return drawerWithGravity != null && this.isDrawerOpen(drawerWithGravity);
    }
    
    public boolean isDrawerOpen(final View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
        return ((DrawerLayout$LayoutParams)view.getLayoutParams()).knownOpen;
    }
    
    boolean isDrawerView(final View view) {
        return (GravityCompat.getAbsoluteGravity(((DrawerLayout$LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view)) & 0x7) != 0x0;
    }
    
    public boolean isDrawerVisible(final int n) {
        final View drawerWithGravity = this.findDrawerWithGravity(n);
        return drawerWithGravity != null && this.isDrawerVisible(drawerWithGravity);
    }
    
    public boolean isDrawerVisible(final View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
        return ((DrawerLayout$LayoutParams)view.getLayoutParams()).onScreen > 0.0f;
    }
    
    void moveDrawerToOffset(final View view, final float n) {
        final float drawerViewOffset = this.getDrawerViewOffset(view);
        final int width = view.getWidth();
        int n2 = (int)(width * n) - (int)(drawerViewOffset * width);
        if (!this.checkDrawerViewAbsoluteGravity(view, 3)) {
            n2 = -n2;
        }
        view.offsetLeftAndRight(n2);
        this.setDrawerViewOffset(view, n);
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            final int topInset = DrawerLayout.IMPL.getTopInset(this.mLastInsets);
            if (topInset > 0) {
                this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), topInset);
                this.mStatusBarBackground.draw(canvas);
            }
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        boolean b = false;
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        final boolean shouldInterceptTouchEvent = this.mLeftDragger.shouldInterceptTouchEvent(motionEvent);
        final boolean shouldInterceptTouchEvent2 = this.mRightDragger.shouldInterceptTouchEvent(motionEvent);
        boolean b2 = false;
        Label_0063: {
            switch (actionMasked) {
                case 0: {
                    final float x = motionEvent.getX();
                    final float y = motionEvent.getY();
                    this.mInitialMotionX = x;
                    this.mInitialMotionY = y;
                    if (this.mScrimOpacity > 0.0f && this.isContentView(this.mLeftDragger.findTopChildUnder((int)x, (int)y))) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    this.mDisallowInterceptRequested = false;
                    this.mChildrenCanceledTouch = false;
                    break Label_0063;
                }
                case 2: {
                    if (this.mLeftDragger.checkTouchSlop(3)) {
                        this.mLeftCallback.removeCallbacks();
                        this.mRightCallback.removeCallbacks();
                        b2 = false;
                        break Label_0063;
                    }
                    break;
                }
                case 1:
                case 3: {
                    this.closeDrawers(true);
                    this.mDisallowInterceptRequested = false;
                    this.mChildrenCanceledTouch = false;
                    break;
                }
            }
            b2 = false;
        }
        if ((shouldInterceptTouchEvent | shouldInterceptTouchEvent2) || b2 || this.hasPeekingDrawer() || this.mChildrenCanceledTouch) {
            b = true;
        }
        return b;
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4 && this.hasVisibleDrawer()) {
            KeyEventCompat.startTracking(keyEvent);
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            final View visibleDrawer = this.findVisibleDrawer();
            if (visibleDrawer != null && this.getDrawerLockMode(visibleDrawer) == 0) {
                this.closeDrawers();
            }
            return visibleDrawer != null;
        }
        return super.onKeyUp(n, keyEvent);
    }
    
    protected void onLayout(final boolean b, int topMargin, final int n, int i, final int n2) {
        this.mInLayout = true;
        final int n3 = i - topMargin;
        int childCount;
        View child;
        DrawerLayout$LayoutParams drawerLayout$LayoutParams;
        int measuredWidth;
        int measuredHeight;
        int n4;
        float n5;
        int n6;
        int n7;
        int n8;
        for (childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)child.getLayoutParams();
                if (this.isContentView(child)) {
                    child.layout(drawerLayout$LayoutParams.leftMargin, drawerLayout$LayoutParams.topMargin, drawerLayout$LayoutParams.leftMargin + child.getMeasuredWidth(), drawerLayout$LayoutParams.topMargin + child.getMeasuredHeight());
                }
                else {
                    measuredWidth = child.getMeasuredWidth();
                    measuredHeight = child.getMeasuredHeight();
                    if (this.checkDrawerViewAbsoluteGravity(child, 3)) {
                        topMargin = -measuredWidth;
                        n4 = (int)(measuredWidth * drawerLayout$LayoutParams.onScreen) + topMargin;
                        n5 = (measuredWidth + n4) / measuredWidth;
                    }
                    else {
                        n4 = n3 - (int)(measuredWidth * drawerLayout$LayoutParams.onScreen);
                        n5 = (n3 - n4) / measuredWidth;
                    }
                    if (n5 != drawerLayout$LayoutParams.onScreen) {
                        n6 = 1;
                    }
                    else {
                        n6 = 0;
                    }
                    switch (drawerLayout$LayoutParams.gravity & 0x70) {
                        default: {
                            child.layout(n4, drawerLayout$LayoutParams.topMargin, measuredWidth + n4, measuredHeight + drawerLayout$LayoutParams.topMargin);
                            break;
                        }
                        case 80: {
                            topMargin = n2 - n;
                            child.layout(n4, topMargin - drawerLayout$LayoutParams.bottomMargin - child.getMeasuredHeight(), measuredWidth + n4, topMargin - drawerLayout$LayoutParams.bottomMargin);
                            break;
                        }
                        case 16: {
                            n7 = n2 - n;
                            n8 = (n7 - measuredHeight) / 2;
                            if (n8 < drawerLayout$LayoutParams.topMargin) {
                                topMargin = drawerLayout$LayoutParams.topMargin;
                            }
                            else {
                                topMargin = n8;
                                if (n8 + measuredHeight > n7 - drawerLayout$LayoutParams.bottomMargin) {
                                    topMargin = n7 - drawerLayout$LayoutParams.bottomMargin - measuredHeight;
                                }
                            }
                            child.layout(n4, topMargin, measuredWidth + n4, measuredHeight + topMargin);
                            break;
                        }
                    }
                    if (n6 != 0) {
                        this.setDrawerViewOffset(child, n5);
                    }
                    if (drawerLayout$LayoutParams.onScreen > 0.0f) {
                        topMargin = 0;
                    }
                    else {
                        topMargin = 4;
                    }
                    if (child.getVisibility() != topMargin) {
                        child.setVisibility(topMargin);
                    }
                }
            }
        }
        this.mInLayout = false;
        this.mFirstLayout = false;
    }
    
    protected void onMeasure(final int n, final int n2) {
        final int n3 = 300;
        final int mode = View$MeasureSpec.getMode(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        final int size = View$MeasureSpec.getSize(n);
        final int size2 = View$MeasureSpec.getSize(n2);
        while (true) {
            int n4 = 0;
            Label_0181: {
                if (mode == 1073741824) {
                    n4 = size;
                    if (mode2 == 1073741824) {
                        break Label_0181;
                    }
                }
                if (!this.isInEditMode()) {
                    throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
                }
                if (mode == Integer.MIN_VALUE) {
                    n4 = size;
                }
                else {
                    n4 = size;
                    if (mode == 0) {
                        n4 = 300;
                    }
                }
                int n5;
                int n6;
                if (mode2 == Integer.MIN_VALUE) {
                    n5 = size2;
                    n6 = n4;
                }
                else {
                    n5 = n3;
                    n6 = n4;
                    if (mode2 != 0) {
                        break Label_0181;
                    }
                }
                this.setMeasuredDimension(n6, n5);
                final boolean b = this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this);
                final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
                for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                    final View child = this.getChildAt(i);
                    if (child.getVisibility() != 8) {
                        final DrawerLayout$LayoutParams drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)child.getLayoutParams();
                        if (b) {
                            final int absoluteGravity = GravityCompat.getAbsoluteGravity(drawerLayout$LayoutParams.gravity, layoutDirection);
                            if (ViewCompat.getFitsSystemWindows(child)) {
                                DrawerLayout.IMPL.dispatchChildInsets(child, this.mLastInsets, absoluteGravity);
                            }
                            else {
                                DrawerLayout.IMPL.applyMarginInsets(drawerLayout$LayoutParams, this.mLastInsets, absoluteGravity);
                            }
                        }
                        if (this.isContentView(child)) {
                            child.measure(View$MeasureSpec.makeMeasureSpec(n6 - drawerLayout$LayoutParams.leftMargin - drawerLayout$LayoutParams.rightMargin, 1073741824), View$MeasureSpec.makeMeasureSpec(n5 - drawerLayout$LayoutParams.topMargin - drawerLayout$LayoutParams.bottomMargin, 1073741824));
                        }
                        else {
                            if (!this.isDrawerView(child)) {
                                throw new IllegalStateException("Child " + child + " at index " + i + " does not have a valid layout_gravity - must be Gravity.LEFT, " + "Gravity.RIGHT or Gravity.NO_GRAVITY");
                            }
                            final int n7 = this.getDrawerViewAbsoluteGravity(child) & 0x7;
                            if ((0x0 & n7) != 0x0) {
                                throw new IllegalStateException("Child drawer has absolute gravity " + gravityToString(n7) + " but this " + "DrawerLayout" + " already has a " + "drawer view along that edge");
                            }
                            child.measure(getChildMeasureSpec(n, this.mMinDrawerMargin + drawerLayout$LayoutParams.leftMargin + drawerLayout$LayoutParams.rightMargin, drawerLayout$LayoutParams.width), getChildMeasureSpec(n2, drawerLayout$LayoutParams.topMargin + drawerLayout$LayoutParams.bottomMargin, drawerLayout$LayoutParams.height));
                        }
                    }
                }
                return;
            }
            int n5 = size2;
            int n6 = n4;
            continue;
        }
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        final DrawerLayout$SavedState drawerLayout$SavedState = (DrawerLayout$SavedState)parcelable;
        super.onRestoreInstanceState(drawerLayout$SavedState.getSuperState());
        if (drawerLayout$SavedState.openDrawerGravity != 0) {
            final View drawerWithGravity = this.findDrawerWithGravity(drawerLayout$SavedState.openDrawerGravity);
            if (drawerWithGravity != null) {
                this.openDrawer(drawerWithGravity);
            }
        }
        this.setDrawerLockMode(drawerLayout$SavedState.lockModeLeft, 3);
        this.setDrawerLockMode(drawerLayout$SavedState.lockModeRight, 5);
    }
    
    protected Parcelable onSaveInstanceState() {
        final DrawerLayout$SavedState drawerLayout$SavedState = new DrawerLayout$SavedState(super.onSaveInstanceState());
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (this.isDrawerView(child)) {
                final DrawerLayout$LayoutParams drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)child.getLayoutParams();
                if (drawerLayout$LayoutParams.knownOpen) {
                    drawerLayout$SavedState.openDrawerGravity = drawerLayout$LayoutParams.gravity;
                    break;
                }
            }
        }
        drawerLayout$SavedState.lockModeLeft = this.mLockModeLeft;
        drawerLayout$SavedState.lockModeRight = this.mLockModeRight;
        return (Parcelable)drawerLayout$SavedState;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.mLeftDragger.processTouchEvent(motionEvent);
        this.mRightDragger.processTouchEvent(motionEvent);
        switch (motionEvent.getAction() & 0xFF) {
            default: {
                return true;
            }
            case 0: {
                final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                return true;
            }
            case 1: {
                final float x2 = motionEvent.getX();
                final float y2 = motionEvent.getY();
                final View topChildUnder = this.mLeftDragger.findTopChildUnder((int)x2, (int)y2);
                while (true) {
                    Label_0221: {
                        if (topChildUnder == null || !this.isContentView(topChildUnder)) {
                            break Label_0221;
                        }
                        final float n = x2 - this.mInitialMotionX;
                        final float n2 = y2 - this.mInitialMotionY;
                        final int touchSlop = this.mLeftDragger.getTouchSlop();
                        if (n * n + n2 * n2 >= touchSlop * touchSlop) {
                            break Label_0221;
                        }
                        final View openDrawer = this.findOpenDrawer();
                        if (openDrawer == null) {
                            break Label_0221;
                        }
                        final boolean b = this.getDrawerLockMode(openDrawer) == 2;
                        this.closeDrawers(b);
                        this.mDisallowInterceptRequested = false;
                        return true;
                    }
                    final boolean b = true;
                    continue;
                }
            }
            case 3: {
                this.closeDrawers(true);
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                return true;
            }
        }
    }
    
    public void openDrawer(final int n) {
        final View drawerWithGravity = this.findDrawerWithGravity(n);
        if (drawerWithGravity == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(n));
        }
        this.openDrawer(drawerWithGravity);
    }
    
    public void openDrawer(final View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        if (this.mFirstLayout) {
            final DrawerLayout$LayoutParams drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)view.getLayoutParams();
            drawerLayout$LayoutParams.onScreen = 1.0f;
            drawerLayout$LayoutParams.knownOpen = true;
            final View child = this.getChildAt(0);
            if (child != null) {
                ViewCompat.setImportantForAccessibility(child, 4);
            }
            ViewCompat.setImportantForAccessibility(view, 1);
        }
        else if (this.checkDrawerViewAbsoluteGravity(view, 3)) {
            this.mLeftDragger.smoothSlideViewTo(view, 0, view.getTop());
        }
        else {
            this.mRightDragger.smoothSlideViewTo(view, this.getWidth() - view.getWidth(), view.getTop());
        }
        this.invalidate();
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean mDisallowInterceptRequested) {
        super.requestDisallowInterceptTouchEvent(mDisallowInterceptRequested);
        this.mDisallowInterceptRequested = mDisallowInterceptRequested;
        if (mDisallowInterceptRequested) {
            this.closeDrawers(true);
        }
    }
    
    public void requestLayout() {
        if (!this.mInLayout) {
            super.requestLayout();
        }
    }
    
    public void setChildInsets(final Object mLastInsets, final boolean mDrawStatusBarBackground) {
        this.mLastInsets = mLastInsets;
        this.mDrawStatusBarBackground = mDrawStatusBarBackground;
        this.setWillNotDraw(!mDrawStatusBarBackground && this.getBackground() == null);
        this.requestLayout();
    }
    
    public void setDrawerListener(final DrawerLayout$DrawerListener mListener) {
        this.mListener = mListener;
    }
    
    public void setDrawerLockMode(final int n) {
        this.setDrawerLockMode(n, 3);
        this.setDrawerLockMode(n, 5);
    }
    
    public void setDrawerLockMode(final int n, int absoluteGravity) {
        absoluteGravity = GravityCompat.getAbsoluteGravity(absoluteGravity, ViewCompat.getLayoutDirection((View)this));
        if (absoluteGravity == 3) {
            this.mLockModeLeft = n;
        }
        else if (absoluteGravity == 5) {
            this.mLockModeRight = n;
        }
        if (n != 0) {
            ViewDragHelper viewDragHelper;
            if (absoluteGravity == 3) {
                viewDragHelper = this.mLeftDragger;
            }
            else {
                viewDragHelper = this.mRightDragger;
            }
            viewDragHelper.cancel();
        }
        switch (n) {
            case 2: {
                final View drawerWithGravity = this.findDrawerWithGravity(absoluteGravity);
                if (drawerWithGravity != null) {
                    this.openDrawer(drawerWithGravity);
                    return;
                }
                break;
            }
            case 1: {
                final View drawerWithGravity2 = this.findDrawerWithGravity(absoluteGravity);
                if (drawerWithGravity2 != null) {
                    this.closeDrawer(drawerWithGravity2);
                    return;
                }
                break;
            }
        }
    }
    
    public void setDrawerLockMode(final int n, final View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a " + "drawer with appropriate layout_gravity");
        }
        this.setDrawerLockMode(n, ((DrawerLayout$LayoutParams)view.getLayoutParams()).gravity);
    }
    
    public void setDrawerShadow(final int n, final int n2) {
        this.setDrawerShadow(this.getResources().getDrawable(n), n2);
    }
    
    public void setDrawerShadow(final Drawable drawable, int absoluteGravity) {
        absoluteGravity = GravityCompat.getAbsoluteGravity(absoluteGravity, ViewCompat.getLayoutDirection((View)this));
        if ((absoluteGravity & 0x3) == 0x3) {
            this.mShadowLeft = drawable;
            this.invalidate();
        }
        if ((absoluteGravity & 0x5) == 0x5) {
            this.mShadowRight = drawable;
            this.invalidate();
        }
    }
    
    public void setDrawerTitle(int absoluteGravity, final CharSequence charSequence) {
        absoluteGravity = GravityCompat.getAbsoluteGravity(absoluteGravity, ViewCompat.getLayoutDirection((View)this));
        if (absoluteGravity == 3) {
            this.mTitleLeft = charSequence;
        }
        else if (absoluteGravity == 5) {
            this.mTitleRight = charSequence;
        }
    }
    
    void setDrawerViewOffset(final View view, final float onScreen) {
        final DrawerLayout$LayoutParams drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)view.getLayoutParams();
        if (onScreen == drawerLayout$LayoutParams.onScreen) {
            return;
        }
        this.dispatchOnDrawerSlide(view, drawerLayout$LayoutParams.onScreen = onScreen);
    }
    
    public void setScrimColor(final int mScrimColor) {
        this.mScrimColor = mScrimColor;
        this.invalidate();
    }
    
    public void setStatusBarBackground(final int n) {
        Drawable drawable;
        if (n != 0) {
            drawable = ContextCompat.getDrawable(this.getContext(), n);
        }
        else {
            drawable = null;
        }
        this.mStatusBarBackground = drawable;
    }
    
    public void setStatusBarBackground(final Drawable mStatusBarBackground) {
        this.mStatusBarBackground = mStatusBarBackground;
    }
    
    public void setStatusBarBackgroundColor(final int n) {
        this.mStatusBarBackground = (Drawable)new ColorDrawable(n);
    }
    
    void updateDrawerState(int mDrawerState, final int n, final View view) {
        final int n2 = 1;
        final int viewDragState = this.mLeftDragger.getViewDragState();
        final int viewDragState2 = this.mRightDragger.getViewDragState();
        mDrawerState = n2;
        if (viewDragState != 1) {
            if (viewDragState2 == 1) {
                mDrawerState = n2;
            }
            else if (viewDragState == 2 || viewDragState2 == 2) {
                mDrawerState = 2;
            }
            else {
                mDrawerState = 0;
            }
        }
        if (view != null && n == 0) {
            final DrawerLayout$LayoutParams drawerLayout$LayoutParams = (DrawerLayout$LayoutParams)view.getLayoutParams();
            if (drawerLayout$LayoutParams.onScreen == 0.0f) {
                this.dispatchOnDrawerClosed(view);
            }
            else if (drawerLayout$LayoutParams.onScreen == 1.0f) {
                this.dispatchOnDrawerOpened(view);
            }
        }
        if (mDrawerState != this.mDrawerState) {
            this.mDrawerState = mDrawerState;
            if (this.mListener != null) {
                this.mListener.onDrawerStateChanged(mDrawerState);
            }
        }
    }
}
