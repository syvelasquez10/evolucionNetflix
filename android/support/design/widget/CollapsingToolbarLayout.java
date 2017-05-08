// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.content.ContextCompat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable$Callback;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.graphics.Typeface;
import android.widget.FrameLayout$LayoutParams;
import android.graphics.Canvas;
import android.view.ViewGroup;
import android.support.design.R$id;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.content.res.TypedArray;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.graphics.Rect;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;

public class CollapsingToolbarLayout extends FrameLayout
{
    private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
    final CollapsingTextHelper mCollapsingTextHelper;
    private boolean mCollapsingTitleEnabled;
    private Drawable mContentScrim;
    int mCurrentOffset;
    private boolean mDrawCollapsingTitle;
    private View mDummyView;
    private int mExpandedMarginBottom;
    private int mExpandedMarginEnd;
    private int mExpandedMarginStart;
    private int mExpandedMarginTop;
    WindowInsetsCompat mLastInsets;
    private AppBarLayout$OnOffsetChangedListener mOnOffsetChangedListener;
    private boolean mRefreshToolbar;
    private int mScrimAlpha;
    private long mScrimAnimationDuration;
    private ValueAnimatorCompat mScrimAnimator;
    private int mScrimVisibleHeightTrigger;
    private boolean mScrimsAreShown;
    Drawable mStatusBarScrim;
    private final Rect mTmpRect;
    private Toolbar mToolbar;
    private View mToolbarDirectChild;
    private int mToolbarId;
    
    public CollapsingToolbarLayout(final Context context) {
        this(context, null);
    }
    
    public CollapsingToolbarLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public CollapsingToolbarLayout(final Context context, final AttributeSet set, int dimensionPixelSize) {
        super(context, set, dimensionPixelSize);
        this.mRefreshToolbar = true;
        this.mTmpRect = new Rect();
        this.mScrimVisibleHeightTrigger = -1;
        ThemeUtils.checkAppCompatTheme(context);
        (this.mCollapsingTextHelper = new CollapsingTextHelper((View)this)).setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.CollapsingToolbarLayout, dimensionPixelSize, R$style.Widget_Design_CollapsingToolbar);
        this.mCollapsingTextHelper.setExpandedTextGravity(obtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        this.mCollapsingTextHelper.setCollapsedTextGravity(obtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.mExpandedMarginBottom = dimensionPixelSize;
        this.mExpandedMarginEnd = dimensionPixelSize;
        this.mExpandedMarginTop = dimensionPixelSize;
        this.mExpandedMarginStart = dimensionPixelSize;
        if (obtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginStart)) {
            this.mExpandedMarginStart = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
            this.mExpandedMarginEnd = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
            this.mExpandedMarginTop = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
            this.mExpandedMarginBottom = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
        }
        this.mCollapsingTitleEnabled = obtainStyledAttributes.getBoolean(R$styleable.CollapsingToolbarLayout_titleEnabled, true);
        this.setTitle(obtainStyledAttributes.getText(R$styleable.CollapsingToolbarLayout_title));
        this.mCollapsingTextHelper.setExpandedTextAppearance(R$style.TextAppearance_Design_CollapsingToolbar_Expanded);
        this.mCollapsingTextHelper.setCollapsedTextAppearance(android.support.v7.appcompat.R$style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (obtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
            this.mCollapsingTextHelper.setExpandedTextAppearance(obtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
        }
        if (obtainStyledAttributes.hasValue(R$styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
            this.mCollapsingTextHelper.setCollapsedTextAppearance(obtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
        }
        this.mScrimVisibleHeightTrigger = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        this.mScrimAnimationDuration = obtainStyledAttributes.getInt(R$styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        this.setContentScrim(obtainStyledAttributes.getDrawable(R$styleable.CollapsingToolbarLayout_contentScrim));
        this.setStatusBarScrim(obtainStyledAttributes.getDrawable(R$styleable.CollapsingToolbarLayout_statusBarScrim));
        this.mToolbarId = obtainStyledAttributes.getResourceId(R$styleable.CollapsingToolbarLayout_toolbarId, -1);
        obtainStyledAttributes.recycle();
        this.setWillNotDraw(false);
        ViewCompat.setOnApplyWindowInsetsListener((View)this, new CollapsingToolbarLayout$1(this));
    }
    
    private void animateScrim(final int n) {
        this.ensureToolbar();
        if (this.mScrimAnimator == null) {
            (this.mScrimAnimator = ViewUtils.createAnimator()).setDuration(this.mScrimAnimationDuration);
            final ValueAnimatorCompat mScrimAnimator = this.mScrimAnimator;
            Interpolator interpolator;
            if (n > this.mScrimAlpha) {
                interpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
            }
            else {
                interpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
            }
            mScrimAnimator.setInterpolator(interpolator);
            this.mScrimAnimator.addUpdateListener(new CollapsingToolbarLayout$2(this));
        }
        else if (this.mScrimAnimator.isRunning()) {
            this.mScrimAnimator.cancel();
        }
        this.mScrimAnimator.setIntValues(this.mScrimAlpha, n);
        this.mScrimAnimator.start();
    }
    
    private void ensureToolbar() {
        if (!this.mRefreshToolbar) {
            return;
        }
        this.mToolbar = null;
        this.mToolbarDirectChild = null;
        if (this.mToolbarId != -1) {
            this.mToolbar = (Toolbar)this.findViewById(this.mToolbarId);
            if (this.mToolbar != null) {
                this.mToolbarDirectChild = this.findDirectChild((View)this.mToolbar);
            }
        }
        Label_0102: {
            if (this.mToolbar == null) {
                while (true) {
                    for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                        final View child = this.getChildAt(i);
                        if (child instanceof Toolbar) {
                            final Toolbar mToolbar = (Toolbar)child;
                            this.mToolbar = mToolbar;
                            break Label_0102;
                        }
                    }
                    final Toolbar mToolbar = null;
                    continue;
                }
            }
        }
        this.updateDummyView();
        this.mRefreshToolbar = false;
    }
    
    private View findDirectChild(final View view) {
        final ViewParent parent = view.getParent();
        View view2 = view;
        for (Object parent2 = parent; parent2 != this && parent2 != null; parent2 = ((ViewParent)parent2).getParent()) {
            if (parent2 instanceof View) {
                view2 = (View)parent2;
            }
        }
        return view2;
    }
    
    private static int getHeightWithMargins(final View view) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup$MarginLayoutParams) {
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)layoutParams;
            return viewGroup$MarginLayoutParams.bottomMargin + (view.getHeight() + viewGroup$MarginLayoutParams.topMargin);
        }
        return view.getHeight();
    }
    
    static ViewOffsetHelper getViewOffsetHelper(final View view) {
        ViewOffsetHelper viewOffsetHelper;
        if ((viewOffsetHelper = (ViewOffsetHelper)view.getTag(R$id.view_offset_helper)) == null) {
            viewOffsetHelper = new ViewOffsetHelper(view);
            view.setTag(R$id.view_offset_helper, (Object)viewOffsetHelper);
        }
        return viewOffsetHelper;
    }
    
    private boolean isToolbarChild(final View view) {
        if (this.mToolbarDirectChild == null || this.mToolbarDirectChild == this) {
            if (view != this.mToolbar) {
                return false;
            }
        }
        else if (view != this.mToolbarDirectChild) {
            return false;
        }
        return true;
    }
    
    private void updateDummyView() {
        if (!this.mCollapsingTitleEnabled && this.mDummyView != null) {
            final ViewParent parent = this.mDummyView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup)parent).removeView(this.mDummyView);
            }
        }
        if (this.mCollapsingTitleEnabled && this.mToolbar != null) {
            if (this.mDummyView == null) {
                this.mDummyView = new View(this.getContext());
            }
            if (this.mDummyView.getParent() == null) {
                this.mToolbar.addView(this.mDummyView, -1, -1);
            }
        }
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof CollapsingToolbarLayout$LayoutParams;
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        this.ensureToolbar();
        if (this.mToolbar == null && this.mContentScrim != null && this.mScrimAlpha > 0) {
            this.mContentScrim.mutate().setAlpha(this.mScrimAlpha);
            this.mContentScrim.draw(canvas);
        }
        if (this.mCollapsingTitleEnabled && this.mDrawCollapsingTitle) {
            this.mCollapsingTextHelper.draw(canvas);
        }
        if (this.mStatusBarScrim != null && this.mScrimAlpha > 0) {
            int systemWindowInsetTop;
            if (this.mLastInsets != null) {
                systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
            }
            else {
                systemWindowInsetTop = 0;
            }
            if (systemWindowInsetTop > 0) {
                this.mStatusBarScrim.setBounds(0, -this.mCurrentOffset, this.getWidth(), systemWindowInsetTop - this.mCurrentOffset);
                this.mStatusBarScrim.mutate().setAlpha(this.mScrimAlpha);
                this.mStatusBarScrim.draw(canvas);
            }
        }
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        boolean b = false;
        boolean b2;
        if (this.mContentScrim != null && this.mScrimAlpha > 0 && this.isToolbarChild(view)) {
            this.mContentScrim.mutate().setAlpha(this.mScrimAlpha);
            this.mContentScrim.draw(canvas);
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (super.drawChild(canvas, view, n) || b2) {
            b = true;
        }
        return b;
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        final int[] drawableState = this.getDrawableState();
        final boolean b = false;
        final Drawable mStatusBarScrim = this.mStatusBarScrim;
        boolean b2 = b;
        if (mStatusBarScrim != null) {
            b2 = b;
            if (mStatusBarScrim.isStateful()) {
                b2 = (false | mStatusBarScrim.setState(drawableState));
            }
        }
        final Drawable mContentScrim = this.mContentScrim;
        boolean b3 = b2;
        if (mContentScrim != null) {
            b3 = b2;
            if (mContentScrim.isStateful()) {
                b3 = (b2 | mContentScrim.setState(drawableState));
            }
        }
        boolean b4 = b3;
        if (this.mCollapsingTextHelper != null) {
            b4 = (b3 | this.mCollapsingTextHelper.setState(drawableState));
        }
        if (b4) {
            this.invalidate();
        }
    }
    
    protected CollapsingToolbarLayout$LayoutParams generateDefaultLayoutParams() {
        return new CollapsingToolbarLayout$LayoutParams(-1, -1);
    }
    
    public FrameLayout$LayoutParams generateLayoutParams(final AttributeSet set) {
        return new CollapsingToolbarLayout$LayoutParams(this.getContext(), set);
    }
    
    protected FrameLayout$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return new CollapsingToolbarLayout$LayoutParams(viewGroup$LayoutParams);
    }
    
    public int getCollapsedTitleGravity() {
        return this.mCollapsingTextHelper.getCollapsedTextGravity();
    }
    
    public Typeface getCollapsedTitleTypeface() {
        return this.mCollapsingTextHelper.getCollapsedTypeface();
    }
    
    public Drawable getContentScrim() {
        return this.mContentScrim;
    }
    
    public int getExpandedTitleGravity() {
        return this.mCollapsingTextHelper.getExpandedTextGravity();
    }
    
    public int getExpandedTitleMarginBottom() {
        return this.mExpandedMarginBottom;
    }
    
    public int getExpandedTitleMarginEnd() {
        return this.mExpandedMarginEnd;
    }
    
    public int getExpandedTitleMarginStart() {
        return this.mExpandedMarginStart;
    }
    
    public int getExpandedTitleMarginTop() {
        return this.mExpandedMarginTop;
    }
    
    public Typeface getExpandedTitleTypeface() {
        return this.mCollapsingTextHelper.getExpandedTypeface();
    }
    
    final int getMaxOffsetForPinChild(final View view) {
        return this.getHeight() - getViewOffsetHelper(view).getLayoutTop() - view.getHeight() - ((CollapsingToolbarLayout$LayoutParams)view.getLayoutParams()).bottomMargin;
    }
    
    int getScrimAlpha() {
        return this.mScrimAlpha;
    }
    
    public long getScrimAnimationDuration() {
        return this.mScrimAnimationDuration;
    }
    
    public int getScrimVisibleHeightTrigger() {
        if (this.mScrimVisibleHeightTrigger >= 0) {
            return this.mScrimVisibleHeightTrigger;
        }
        int systemWindowInsetTop;
        if (this.mLastInsets != null) {
            systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
        }
        else {
            systemWindowInsetTop = 0;
        }
        final int minimumHeight = ViewCompat.getMinimumHeight((View)this);
        if (minimumHeight > 0) {
            return Math.min(systemWindowInsetTop + minimumHeight * 2, this.getHeight());
        }
        return this.getHeight() / 3;
    }
    
    public Drawable getStatusBarScrim() {
        return this.mStatusBarScrim;
    }
    
    public CharSequence getTitle() {
        if (this.mCollapsingTitleEnabled) {
            return this.mCollapsingTextHelper.getText();
        }
        return null;
    }
    
    public boolean isTitleEnabled() {
        return this.mCollapsingTitleEnabled;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final ViewParent parent = this.getParent();
        if (parent instanceof AppBarLayout) {
            ViewCompat.setFitsSystemWindows((View)this, ViewCompat.getFitsSystemWindows((View)parent));
            if (this.mOnOffsetChangedListener == null) {
                this.mOnOffsetChangedListener = new CollapsingToolbarLayout$OffsetUpdateListener(this);
            }
            ((AppBarLayout)parent).addOnOffsetChangedListener(this.mOnOffsetChangedListener);
            ViewCompat.requestApplyInsets((View)this);
        }
    }
    
    protected void onDetachedFromWindow() {
        final ViewParent parent = this.getParent();
        if (this.mOnOffsetChangedListener != null && parent instanceof AppBarLayout) {
            ((AppBarLayout)parent).removeOnOffsetChangedListener(this.mOnOffsetChangedListener);
        }
        super.onDetachedFromWindow();
    }
    
    protected void onLayout(final boolean b, int i, int childCount, final int n, final int n2) {
        final boolean b2 = true;
        final int n3 = 0;
        super.onLayout(b, i, childCount, n, n2);
        if (this.mLastInsets != null) {
            final int systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
            for (int childCount2 = this.getChildCount(), j = 0; j < childCount2; ++j) {
                final View child = this.getChildAt(j);
                if (!ViewCompat.getFitsSystemWindows(child) && child.getTop() < systemWindowInsetTop) {
                    ViewCompat.offsetTopAndBottom(child, systemWindowInsetTop);
                }
            }
        }
        if (this.mCollapsingTitleEnabled && this.mDummyView != null) {
            this.mDrawCollapsingTitle = (ViewCompat.isAttachedToWindow(this.mDummyView) && this.mDummyView.getVisibility() == 0);
            if (this.mDrawCollapsingTitle) {
                final boolean b3 = ViewCompat.getLayoutDirection((View)this) == 1 && b2;
                Object o;
                if (this.mToolbarDirectChild != null) {
                    o = this.mToolbarDirectChild;
                }
                else {
                    o = this.mToolbar;
                }
                final int maxOffsetForPinChild = this.getMaxOffsetForPinChild((View)o);
                ViewGroupUtils.getDescendantRect((ViewGroup)this, this.mDummyView, this.mTmpRect);
                final CollapsingTextHelper mCollapsingTextHelper = this.mCollapsingTextHelper;
                final int left = this.mTmpRect.left;
                int n4;
                if (b3) {
                    n4 = this.mToolbar.getTitleMarginEnd();
                }
                else {
                    n4 = this.mToolbar.getTitleMarginStart();
                }
                final int top = this.mTmpRect.top;
                final int titleMarginTop = this.mToolbar.getTitleMarginTop();
                final int right = this.mTmpRect.right;
                int n5;
                if (b3) {
                    n5 = this.mToolbar.getTitleMarginStart();
                }
                else {
                    n5 = this.mToolbar.getTitleMarginEnd();
                }
                mCollapsingTextHelper.setCollapsedBounds(left + n4, titleMarginTop + (top + maxOffsetForPinChild), n5 + right, maxOffsetForPinChild + this.mTmpRect.bottom - this.mToolbar.getTitleMarginBottom());
                final CollapsingTextHelper mCollapsingTextHelper2 = this.mCollapsingTextHelper;
                int n6;
                if (b3) {
                    n6 = this.mExpandedMarginEnd;
                }
                else {
                    n6 = this.mExpandedMarginStart;
                }
                final int top2 = this.mTmpRect.top;
                final int mExpandedMarginTop = this.mExpandedMarginTop;
                int n7;
                if (b3) {
                    n7 = this.mExpandedMarginStart;
                }
                else {
                    n7 = this.mExpandedMarginEnd;
                }
                mCollapsingTextHelper2.setExpandedBounds(n6, top2 + mExpandedMarginTop, n - i - n7, n2 - childCount - this.mExpandedMarginBottom);
                this.mCollapsingTextHelper.recalculate();
            }
        }
        for (childCount = this.getChildCount(), i = n3; i < childCount; ++i) {
            getViewOffsetHelper(this.getChildAt(i)).onViewLayout();
        }
        if (this.mToolbar != null) {
            if (this.mCollapsingTitleEnabled && TextUtils.isEmpty(this.mCollapsingTextHelper.getText())) {
                this.mCollapsingTextHelper.setText(this.mToolbar.getTitle());
            }
            if (this.mToolbarDirectChild == null || this.mToolbarDirectChild == this) {
                this.setMinimumHeight(getHeightWithMargins((View)this.mToolbar));
            }
            else {
                this.setMinimumHeight(getHeightWithMargins(this.mToolbarDirectChild));
            }
        }
        this.updateScrimVisibility();
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.ensureToolbar();
        super.onMeasure(n, n2);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.mContentScrim != null) {
            this.mContentScrim.setBounds(0, 0, n, n2);
        }
    }
    
    WindowInsetsCompat onWindowInsetChanged(final WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat mLastInsets = null;
        if (ViewCompat.getFitsSystemWindows((View)this)) {
            mLastInsets = windowInsetsCompat;
        }
        if (!ViewUtils.objectEquals(this.mLastInsets, mLastInsets)) {
            this.mLastInsets = mLastInsets;
            this.requestLayout();
        }
        return windowInsetsCompat.consumeSystemWindowInsets();
    }
    
    public void setCollapsedTitleGravity(final int collapsedTextGravity) {
        this.mCollapsingTextHelper.setCollapsedTextGravity(collapsedTextGravity);
    }
    
    public void setCollapsedTitleTextAppearance(final int collapsedTextAppearance) {
        this.mCollapsingTextHelper.setCollapsedTextAppearance(collapsedTextAppearance);
    }
    
    public void setCollapsedTitleTextColor(final int n) {
        this.setCollapsedTitleTextColor(ColorStateList.valueOf(n));
    }
    
    public void setCollapsedTitleTextColor(final ColorStateList collapsedTextColor) {
        this.mCollapsingTextHelper.setCollapsedTextColor(collapsedTextColor);
    }
    
    public void setCollapsedTitleTypeface(final Typeface collapsedTypeface) {
        this.mCollapsingTextHelper.setCollapsedTypeface(collapsedTypeface);
    }
    
    public void setContentScrim(final Drawable drawable) {
        Drawable mutate = null;
        if (this.mContentScrim != drawable) {
            if (this.mContentScrim != null) {
                this.mContentScrim.setCallback((Drawable$Callback)null);
            }
            if (drawable != null) {
                mutate = drawable.mutate();
            }
            this.mContentScrim = mutate;
            if (this.mContentScrim != null) {
                this.mContentScrim.setBounds(0, 0, this.getWidth(), this.getHeight());
                this.mContentScrim.setCallback((Drawable$Callback)this);
                this.mContentScrim.setAlpha(this.mScrimAlpha);
            }
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public void setContentScrimColor(final int n) {
        this.setContentScrim((Drawable)new ColorDrawable(n));
    }
    
    public void setContentScrimResource(final int n) {
        this.setContentScrim(ContextCompat.getDrawable(this.getContext(), n));
    }
    
    public void setExpandedTitleColor(final int n) {
        this.setExpandedTitleTextColor(ColorStateList.valueOf(n));
    }
    
    public void setExpandedTitleGravity(final int expandedTextGravity) {
        this.mCollapsingTextHelper.setExpandedTextGravity(expandedTextGravity);
    }
    
    public void setExpandedTitleMargin(final int mExpandedMarginStart, final int mExpandedMarginTop, final int mExpandedMarginEnd, final int mExpandedMarginBottom) {
        this.mExpandedMarginStart = mExpandedMarginStart;
        this.mExpandedMarginTop = mExpandedMarginTop;
        this.mExpandedMarginEnd = mExpandedMarginEnd;
        this.mExpandedMarginBottom = mExpandedMarginBottom;
        this.requestLayout();
    }
    
    public void setExpandedTitleMarginBottom(final int mExpandedMarginBottom) {
        this.mExpandedMarginBottom = mExpandedMarginBottom;
        this.requestLayout();
    }
    
    public void setExpandedTitleMarginEnd(final int mExpandedMarginEnd) {
        this.mExpandedMarginEnd = mExpandedMarginEnd;
        this.requestLayout();
    }
    
    public void setExpandedTitleMarginStart(final int mExpandedMarginStart) {
        this.mExpandedMarginStart = mExpandedMarginStart;
        this.requestLayout();
    }
    
    public void setExpandedTitleMarginTop(final int mExpandedMarginTop) {
        this.mExpandedMarginTop = mExpandedMarginTop;
        this.requestLayout();
    }
    
    public void setExpandedTitleTextAppearance(final int expandedTextAppearance) {
        this.mCollapsingTextHelper.setExpandedTextAppearance(expandedTextAppearance);
    }
    
    public void setExpandedTitleTextColor(final ColorStateList expandedTextColor) {
        this.mCollapsingTextHelper.setExpandedTextColor(expandedTextColor);
    }
    
    public void setExpandedTitleTypeface(final Typeface expandedTypeface) {
        this.mCollapsingTextHelper.setExpandedTypeface(expandedTypeface);
    }
    
    void setScrimAlpha(final int mScrimAlpha) {
        if (mScrimAlpha != this.mScrimAlpha) {
            if (this.mContentScrim != null && this.mToolbar != null) {
                ViewCompat.postInvalidateOnAnimation((View)this.mToolbar);
            }
            this.mScrimAlpha = mScrimAlpha;
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public void setScrimAnimationDuration(final long mScrimAnimationDuration) {
        this.mScrimAnimationDuration = mScrimAnimationDuration;
    }
    
    public void setScrimVisibleHeightTrigger(final int mScrimVisibleHeightTrigger) {
        if (this.mScrimVisibleHeightTrigger != mScrimVisibleHeightTrigger) {
            this.mScrimVisibleHeightTrigger = mScrimVisibleHeightTrigger;
            this.updateScrimVisibility();
        }
    }
    
    public void setScrimsShown(final boolean b) {
        this.setScrimsShown(b, ViewCompat.isLaidOut((View)this) && !this.isInEditMode());
    }
    
    public void setScrimsShown(final boolean mScrimsAreShown, final boolean b) {
        int scrimAlpha = 255;
        if (this.mScrimsAreShown != mScrimsAreShown) {
            if (b) {
                if (!mScrimsAreShown) {
                    scrimAlpha = 0;
                }
                this.animateScrim(scrimAlpha);
            }
            else {
                if (!mScrimsAreShown) {
                    scrimAlpha = 0;
                }
                this.setScrimAlpha(scrimAlpha);
            }
            this.mScrimsAreShown = mScrimsAreShown;
        }
    }
    
    public void setStatusBarScrim(Drawable mStatusBarScrim) {
        Drawable mutate = null;
        if (this.mStatusBarScrim != mStatusBarScrim) {
            if (this.mStatusBarScrim != null) {
                this.mStatusBarScrim.setCallback((Drawable$Callback)null);
            }
            if (mStatusBarScrim != null) {
                mutate = mStatusBarScrim.mutate();
            }
            this.mStatusBarScrim = mutate;
            if (this.mStatusBarScrim != null) {
                if (this.mStatusBarScrim.isStateful()) {
                    this.mStatusBarScrim.setState(this.getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.mStatusBarScrim, ViewCompat.getLayoutDirection((View)this));
                mStatusBarScrim = this.mStatusBarScrim;
                mStatusBarScrim.setVisible(this.getVisibility() == 0, false);
                this.mStatusBarScrim.setCallback((Drawable$Callback)this);
                this.mStatusBarScrim.setAlpha(this.mScrimAlpha);
            }
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public void setStatusBarScrimColor(final int n) {
        this.setStatusBarScrim((Drawable)new ColorDrawable(n));
    }
    
    public void setStatusBarScrimResource(final int n) {
        this.setStatusBarScrim(ContextCompat.getDrawable(this.getContext(), n));
    }
    
    public void setTitle(final CharSequence text) {
        this.mCollapsingTextHelper.setText(text);
    }
    
    public void setTitleEnabled(final boolean mCollapsingTitleEnabled) {
        if (mCollapsingTitleEnabled != this.mCollapsingTitleEnabled) {
            this.mCollapsingTitleEnabled = mCollapsingTitleEnabled;
            this.updateDummyView();
            this.requestLayout();
        }
    }
    
    public void setVisibility(final int visibility) {
        super.setVisibility(visibility);
        final boolean b = visibility == 0;
        if (this.mStatusBarScrim != null && this.mStatusBarScrim.isVisible() != b) {
            this.mStatusBarScrim.setVisible(b, false);
        }
        if (this.mContentScrim != null && this.mContentScrim.isVisible() != b) {
            this.mContentScrim.setVisible(b, false);
        }
    }
    
    final void updateScrimVisibility() {
        if (this.mContentScrim != null || this.mStatusBarScrim != null) {
            this.setScrimsShown(this.getHeight() + this.mCurrentOffset < this.getScrimVisibleHeightTrigger());
        }
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mContentScrim || drawable == this.mStatusBarScrim;
    }
}
