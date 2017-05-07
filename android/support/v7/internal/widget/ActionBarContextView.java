// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.view.View$MeasureSpec;
import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityEvent;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.internal.view.menu.y;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.internal.view.menu.i;
import android.view.View$OnClickListener;
import android.support.v7.view.ActionMode;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.animation.Interpolator;
import android.view.animation.DecelerateInterpolator;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$MarginLayoutParams;
import android.text.TextUtils;
import android.support.v7.appcompat.R$id;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v7.appcompat.R$layout;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListener;

public class ActionBarContextView extends AbsActionBarView implements ViewPropertyAnimatorListener
{
    private static final int ANIMATE_IDLE = 0;
    private static final int ANIMATE_IN = 1;
    private static final int ANIMATE_OUT = 2;
    private static final String TAG = "ActionBarContextView";
    private boolean mAnimateInOnLayout;
    private int mAnimationMode;
    private View mClose;
    private int mCloseItemLayout;
    private ViewPropertyAnimatorCompatSet mCurrentAnimation;
    private View mCustomView;
    private Drawable mSplitBackground;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;
    
    public ActionBarContextView(final Context context) {
        this(context, null);
    }
    
    public ActionBarContextView(final Context context, final AttributeSet set) {
        this(context, set, R$attr.actionModeStyle);
    }
    
    public ActionBarContextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R$styleable.ActionMode, n, 0);
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R$styleable.ActionMode_background));
        this.mTitleStyleRes = obtainStyledAttributes.getResourceId(R$styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = obtainStyledAttributes.getResourceId(R$styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = obtainStyledAttributes.getLayoutDimension(R$styleable.ActionMode_height, 0);
        this.mSplitBackground = obtainStyledAttributes.getDrawable(R$styleable.ActionMode_backgroundSplit);
        this.mCloseItemLayout = obtainStyledAttributes.getResourceId(R$styleable.ActionMode_closeItemLayout, R$layout.abc_action_mode_close_item_material);
        obtainStyledAttributes.recycle();
    }
    
    private void finishAnimation() {
        final ViewPropertyAnimatorCompatSet mCurrentAnimation = this.mCurrentAnimation;
        if (mCurrentAnimation != null) {
            this.mCurrentAnimation = null;
            mCurrentAnimation.cancel();
        }
    }
    
    private void initTitle() {
        final int n = 8;
        boolean b = true;
        if (this.mTitleLayout == null) {
            LayoutInflater.from(this.getContext()).inflate(R$layout.abc_action_bar_title_item, (ViewGroup)this);
            this.mTitleLayout = (LinearLayout)this.getChildAt(this.getChildCount() - 1);
            this.mTitleView = (TextView)this.mTitleLayout.findViewById(R$id.action_bar_title);
            this.mSubtitleView = (TextView)this.mTitleLayout.findViewById(R$id.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(this.getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(this.getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        int n2;
        if (!TextUtils.isEmpty(this.mTitle)) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (TextUtils.isEmpty(this.mSubtitle)) {
            b = false;
        }
        final TextView mSubtitleView = this.mSubtitleView;
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        mSubtitleView.setVisibility(visibility);
        final LinearLayout mTitleLayout = this.mTitleLayout;
        int visibility2 = 0;
        Label_0204: {
            if (n2 == 0) {
                visibility2 = n;
                if (!b) {
                    break Label_0204;
                }
            }
            visibility2 = 0;
        }
        mTitleLayout.setVisibility(visibility2);
        if (this.mTitleLayout.getParent() == null) {
            this.addView((View)this.mTitleLayout);
        }
    }
    
    private ViewPropertyAnimatorCompatSet makeInAnimation() {
        ViewCompat.setTranslationX(this.mClose, -this.mClose.getWidth() - ((ViewGroup$MarginLayoutParams)this.mClose.getLayoutParams()).leftMargin);
        final ViewPropertyAnimatorCompat translationX = ViewCompat.animate(this.mClose).translationX(0.0f);
        translationX.setDuration(200L);
        translationX.setListener(this);
        translationX.setInterpolator((Interpolator)new DecelerateInterpolator());
        final ViewPropertyAnimatorCompatSet set = new ViewPropertyAnimatorCompatSet();
        set.play(translationX);
        if (this.mMenuView != null) {
            final int childCount = this.mMenuView.getChildCount();
            if (childCount > 0) {
                for (int i = childCount - 1, n = 0; i >= 0; --i, ++n) {
                    final View child = this.mMenuView.getChildAt(i);
                    ViewCompat.setScaleY(child, 0.0f);
                    final ViewPropertyAnimatorCompat scaleY = ViewCompat.animate(child).scaleY(1.0f);
                    scaleY.setDuration(300L);
                    set.play(scaleY);
                }
            }
        }
        return set;
    }
    
    private ViewPropertyAnimatorCompatSet makeOutAnimation() {
        final ViewPropertyAnimatorCompat translationX = ViewCompat.animate(this.mClose).translationX(-this.mClose.getWidth() - ((ViewGroup$MarginLayoutParams)this.mClose.getLayoutParams()).leftMargin);
        translationX.setDuration(200L);
        translationX.setListener(this);
        translationX.setInterpolator((Interpolator)new DecelerateInterpolator());
        final ViewPropertyAnimatorCompatSet set = new ViewPropertyAnimatorCompatSet();
        set.play(translationX);
        if (this.mMenuView == null || this.mMenuView.getChildCount() > 0) {}
        return set;
    }
    
    public void closeMode() {
        if (this.mAnimationMode == 2) {
            return;
        }
        if (this.mClose == null) {
            this.killMode();
            return;
        }
        this.finishAnimation();
        this.mAnimationMode = 2;
        (this.mCurrentAnimation = this.makeOutAnimation()).start();
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return (ViewGroup$LayoutParams)new ViewGroup$MarginLayoutParams(-1, -2);
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return (ViewGroup$LayoutParams)new ViewGroup$MarginLayoutParams(this.getContext(), set);
    }
    
    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }
    
    public CharSequence getTitle() {
        return this.mTitle;
    }
    
    @Override
    public boolean hideOverflowMenu() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.hideOverflowMenu();
    }
    
    public void initForMode(final ActionMode actionMode) {
        if (this.mClose == null) {
            this.addView(this.mClose = LayoutInflater.from(this.getContext()).inflate(this.mCloseItemLayout, (ViewGroup)this, false));
        }
        else if (this.mClose.getParent() == null) {
            this.addView(this.mClose);
        }
        this.mClose.findViewById(R$id.action_mode_close_button).setOnClickListener((View$OnClickListener)new ActionBarContextView$1(this, actionMode));
        final i i = (i)actionMode.getMenu();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
        (this.mActionMenuPresenter = new ActionMenuPresenter(this.getContext())).setReserveOverflow(true);
        final ViewGroup$LayoutParams viewGroup$LayoutParams = new ViewGroup$LayoutParams(-2, -1);
        if (!this.mSplitActionBar) {
            i.a(this.mActionMenuPresenter, this.mPopupContext);
            (this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this)).setBackgroundDrawable((Drawable)null);
            this.addView((View)this.mMenuView, viewGroup$LayoutParams);
        }
        else {
            this.mActionMenuPresenter.setWidthLimit(this.getContext().getResources().getDisplayMetrics().widthPixels, true);
            this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
            viewGroup$LayoutParams.width = -1;
            viewGroup$LayoutParams.height = this.mContentHeight;
            i.a(this.mActionMenuPresenter, this.mPopupContext);
            (this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this)).setBackgroundDrawable(this.mSplitBackground);
            this.mSplitView.addView((View)this.mMenuView, viewGroup$LayoutParams);
        }
        this.mAnimateInOnLayout = true;
    }
    
    @Override
    public boolean isOverflowMenuShowing() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.isOverflowMenuShowing();
    }
    
    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }
    
    public void killMode() {
        this.finishAnimation();
        this.removeAllViews();
        if (this.mSplitView != null) {
            this.mSplitView.removeView((View)this.mMenuView);
        }
        this.mCustomView = null;
        this.mMenuView = null;
        this.mAnimateInOnLayout = false;
    }
    
    @Override
    public void onAnimationCancel(final View view) {
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        if (this.mAnimationMode == 2) {
            this.killMode();
        }
        this.mAnimationMode = 0;
    }
    
    @Override
    public void onAnimationStart(final View view) {
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }
    
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        if (Build$VERSION.SDK_INT >= 14) {
            if (accessibilityEvent.getEventType() != 32) {
                super.onInitializeAccessibilityEvent(accessibilityEvent);
                return;
            }
            accessibilityEvent.setSource((View)this);
            accessibilityEvent.setClassName((CharSequence)this.getClass().getName());
            accessibilityEvent.setPackageName((CharSequence)this.getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.mTitle);
        }
    }
    
    protected void onLayout(final boolean b, int paddingLeft, int n, final int n2, int n3) {
        final boolean layoutRtl = ViewUtils.isLayoutRtl((View)this);
        int paddingLeft2;
        if (layoutRtl) {
            paddingLeft2 = n2 - paddingLeft - this.getPaddingRight();
        }
        else {
            paddingLeft2 = this.getPaddingLeft();
        }
        final int paddingTop = this.getPaddingTop();
        final int n4 = n3 - n - this.getPaddingTop() - this.getPaddingBottom();
        n = paddingLeft2;
        if (this.mClose != null) {
            n = paddingLeft2;
            if (this.mClose.getVisibility() != 8) {
                final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)this.mClose.getLayoutParams();
                if (layoutRtl) {
                    n = viewGroup$MarginLayoutParams.rightMargin;
                }
                else {
                    n = viewGroup$MarginLayoutParams.leftMargin;
                }
                if (layoutRtl) {
                    n3 = viewGroup$MarginLayoutParams.leftMargin;
                }
                else {
                    n3 = viewGroup$MarginLayoutParams.rightMargin;
                }
                n = AbsActionBarView.next(paddingLeft2, n, layoutRtl);
                n3 = (n = AbsActionBarView.next(this.positionChild(this.mClose, n, paddingTop, n4, layoutRtl) + n, n3, layoutRtl));
                if (this.mAnimateInOnLayout) {
                    this.mAnimationMode = 1;
                    (this.mCurrentAnimation = this.makeInAnimation()).start();
                    this.mAnimateInOnLayout = false;
                    n = n3;
                }
            }
        }
        n3 = n;
        if (this.mTitleLayout != null) {
            n3 = n;
            if (this.mCustomView == null) {
                n3 = n;
                if (this.mTitleLayout.getVisibility() != 8) {
                    n3 = n + this.positionChild((View)this.mTitleLayout, n, paddingTop, n4, layoutRtl);
                }
            }
        }
        if (this.mCustomView != null) {
            this.positionChild(this.mCustomView, n3, paddingTop, n4, layoutRtl);
        }
        if (layoutRtl) {
            paddingLeft = this.getPaddingLeft();
        }
        else {
            paddingLeft = n2 - paddingLeft - this.getPaddingRight();
        }
        if (this.mMenuView != null) {
            this.positionChild((View)this.mMenuView, paddingLeft, paddingTop, n4, !layoutRtl);
        }
    }
    
    protected void onMeasure(int visibility, int i) {
        final int n = 1073741824;
        final int n2 = 0;
        if (View$MeasureSpec.getMode(visibility) != 1073741824) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View$MeasureSpec.getMode(i) == 0) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        }
        final int size = View$MeasureSpec.getSize(visibility);
        int n3;
        if (this.mContentHeight > 0) {
            n3 = this.mContentHeight;
        }
        else {
            n3 = View$MeasureSpec.getSize(i);
        }
        final int n4 = this.getPaddingTop() + this.getPaddingBottom();
        visibility = size - this.getPaddingLeft() - this.getPaddingRight();
        final int n5 = n3 - n4;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(n5, Integer.MIN_VALUE);
        i = visibility;
        if (this.mClose != null) {
            visibility = this.measureChildView(this.mClose, visibility, measureSpec, 0);
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)this.mClose.getLayoutParams();
            i = viewGroup$MarginLayoutParams.leftMargin;
            i = visibility - (viewGroup$MarginLayoutParams.rightMargin + i);
        }
        visibility = i;
        if (this.mMenuView != null) {
            visibility = i;
            if (this.mMenuView.getParent() == this) {
                visibility = this.measureChildView((View)this.mMenuView, i, measureSpec, 0);
            }
        }
        i = visibility;
        if (this.mTitleLayout != null) {
            i = visibility;
            if (this.mCustomView == null) {
                if (this.mTitleOptional) {
                    i = View$MeasureSpec.makeMeasureSpec(0, 0);
                    this.mTitleLayout.measure(i, measureSpec);
                    final int measuredWidth = this.mTitleLayout.getMeasuredWidth();
                    boolean b;
                    if (measuredWidth <= visibility) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    i = visibility;
                    if (b) {
                        i = visibility - measuredWidth;
                    }
                    final LinearLayout mTitleLayout = this.mTitleLayout;
                    if (b) {
                        visibility = 0;
                    }
                    else {
                        visibility = 8;
                    }
                    mTitleLayout.setVisibility(visibility);
                }
                else {
                    i = this.measureChildView((View)this.mTitleLayout, visibility, measureSpec, 0);
                }
            }
        }
        if (this.mCustomView != null) {
            final ViewGroup$LayoutParams layoutParams = this.mCustomView.getLayoutParams();
            if (layoutParams.width != -2) {
                visibility = 1073741824;
            }
            else {
                visibility = Integer.MIN_VALUE;
            }
            int min = i;
            if (layoutParams.width >= 0) {
                min = Math.min(layoutParams.width, i);
            }
            if (layoutParams.height != -2) {
                i = n;
            }
            else {
                i = Integer.MIN_VALUE;
            }
            int min2;
            if (layoutParams.height >= 0) {
                min2 = Math.min(layoutParams.height, n5);
            }
            else {
                min2 = n5;
            }
            this.mCustomView.measure(View$MeasureSpec.makeMeasureSpec(min, visibility), View$MeasureSpec.makeMeasureSpec(min2, i));
        }
        if (this.mContentHeight <= 0) {
            final int childCount = this.getChildCount();
            visibility = 0;
            int n6;
            for (i = n2; i < childCount; ++i) {
                n6 = this.getChildAt(i).getMeasuredHeight() + n4;
                if (n6 > visibility) {
                    visibility = n6;
                }
            }
            this.setMeasuredDimension(size, visibility);
            return;
        }
        this.setMeasuredDimension(size, n3);
    }
    
    @Override
    public void setContentHeight(final int mContentHeight) {
        this.mContentHeight = mContentHeight;
    }
    
    public void setCustomView(final View mCustomView) {
        if (this.mCustomView != null) {
            this.removeView(this.mCustomView);
        }
        this.mCustomView = mCustomView;
        if (this.mTitleLayout != null) {
            this.removeView((View)this.mTitleLayout);
            this.mTitleLayout = null;
        }
        if (mCustomView != null) {
            this.addView(mCustomView);
        }
        this.requestLayout();
    }
    
    @Override
    public void setSplitToolbar(final boolean splitToolbar) {
        if (this.mSplitActionBar != splitToolbar) {
            if (this.mActionMenuPresenter != null) {
                final ViewGroup$LayoutParams viewGroup$LayoutParams = new ViewGroup$LayoutParams(-2, -1);
                if (!splitToolbar) {
                    (this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this)).setBackgroundDrawable((Drawable)null);
                    final ViewGroup viewGroup = (ViewGroup)this.mMenuView.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView((View)this.mMenuView);
                    }
                    this.addView((View)this.mMenuView, viewGroup$LayoutParams);
                }
                else {
                    this.mActionMenuPresenter.setWidthLimit(this.getContext().getResources().getDisplayMetrics().widthPixels, true);
                    this.mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
                    viewGroup$LayoutParams.width = -1;
                    viewGroup$LayoutParams.height = this.mContentHeight;
                    (this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this)).setBackgroundDrawable(this.mSplitBackground);
                    final ViewGroup viewGroup2 = (ViewGroup)this.mMenuView.getParent();
                    if (viewGroup2 != null) {
                        viewGroup2.removeView((View)this.mMenuView);
                    }
                    this.mSplitView.addView((View)this.mMenuView, viewGroup$LayoutParams);
                }
            }
            super.setSplitToolbar(splitToolbar);
        }
    }
    
    public void setSubtitle(final CharSequence mSubtitle) {
        this.mSubtitle = mSubtitle;
        this.initTitle();
    }
    
    public void setTitle(final CharSequence mTitle) {
        this.mTitle = mTitle;
        this.initTitle();
    }
    
    public void setTitleOptional(final boolean mTitleOptional) {
        if (mTitleOptional != this.mTitleOptional) {
            this.requestLayout();
        }
        this.mTitleOptional = mTitleOptional;
    }
    
    public boolean shouldDelayChildPressedState() {
        return false;
    }
    
    @Override
    public boolean showOverflowMenu() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.showOverflowMenu();
    }
}
