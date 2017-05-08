// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.tooltips;

import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.graphics.Typeface;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.KeyEvent;
import android.animation.Animator$AnimatorListener;
import android.graphics.drawable.Drawable;
import android.graphics.Paint$Style;
import android.graphics.PathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.Xfermode;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff$Mode;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Paint;
import android.widget.TextView;
import android.graphics.Rect;
import android.view.ViewPropertyAnimator;
import android.view.ViewGroup;

public class TooltipLayout extends ViewGroup
{
    private static final String TAG = "Tooltip";
    private static final boolean sDebug = false;
    private int mAccentColor;
    private ViewPropertyAnimator mAnimator;
    private final Rect mArrow;
    private int mArrowCenterXInTooltipBounds;
    private int mBackgroundColor;
    private Tooltip$Callback mCallback;
    private TextView mDetail;
    private int mGravity;
    private final Paint mHighlightPaint;
    private ImageView mImageTitle;
    private int mLastMeasuredHeight;
    private int mLastMeasuredWidth;
    private final int[] mLocationHelper;
    private final int[] mLocationHelperParent;
    private View mParent;
    private final Paint mScrimPaint;
    private View mTarget;
    private final RectF mTargetBounds;
    private TextView mTextTitle;
    private FrameLayout mTitle;
    private ViewGroup mTooltip;
    private final int mTooltipDefaultSize;
    private final TooltipLayout$TooltipDesignDrawable mTooltipDesignDrawable;
    private final int mTooltipElevation;
    private final int mTooltipMaxWidth;
    private final Rect mTooltipViewBounds;
    private View$OnClickListener mUserOnClickListener;
    
    public TooltipLayout(final Context context) {
        this(context, null);
    }
    
    public TooltipLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.mGravity = 80;
        this.mLastMeasuredWidth = 0;
        this.mLastMeasuredHeight = 0;
        this.mTargetBounds = new RectF();
        this.mTooltipViewBounds = new Rect();
        this.mLocationHelper = new int[2];
        this.mLocationHelperParent = new int[2];
        this.mArrow = new Rect();
        this.mTooltipDesignDrawable = new TooltipLayout$TooltipDesignDrawable(this, null);
        this.mScrimPaint = new Paint();
        this.mHighlightPaint = new Paint();
        this.mAccentColor = ContextCompat.getColor(this.getContext(), R$color.tooltip_accent_color);
        this.mBackgroundColor = ContextCompat.getColor(this.getContext(), R$color.tooltip_background_color);
        this.mTooltipMaxWidth = this.getResources().getDimensionPixelSize(R$dimen.tooltip_max_width);
        this.mTooltipElevation = this.getResources().getDimensionPixelSize(R$dimen.tooltip_elevation);
        this.mArrow.right = this.getResources().getDimensionPixelSize(R$dimen.tooltip_arrow_width);
        this.mArrow.bottom = this.getResources().getDimensionPixelSize(R$dimen.tooltip_arrow_height);
        this.mTooltipDefaultSize = this.getResources().getDimensionPixelSize(R$dimen.tooltip_height);
        this.mHighlightPaint.setColor(0);
        this.mHighlightPaint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.CLEAR));
        this.mHighlightPaint.setPathEffect((PathEffect)new CornerPathEffect((float)this.getResources().getDimensionPixelSize(R$dimen.tooltip_radius)));
        this.mScrimPaint.setColor(ContextCompat.getColor(this.getContext(), R$color.tooltip_scrim_color));
        this.mScrimPaint.setStyle(Paint$Style.FILL);
        this.mScrimPaint.setAntiAlias(true);
        this.setBackground((Drawable)new TooltipLayout$ScrimDrawable(this, null));
        this.setLayerType(1, (Paint)null);
        this.setFocusableInTouchMode(true);
        this.requestFocus();
    }
    
    private void debug(final String s) {
    }
    
    void dismiss(final ViewGroup viewGroup) {
        if (this.mAnimator == null) {
            final ViewGroup viewGroup2 = (ViewGroup)this.getParent();
            if (viewGroup2 != null) {
                (this.mAnimator = this.animate().alpha(0.0f).setDuration((long)viewGroup.getResources().getInteger(17694720))).setListener((Animator$AnimatorListener)new TooltipLayout$2(this, viewGroup2));
                this.mAnimator.start();
            }
        }
    }
    
    public boolean dispatchKeyEventPreIme(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && this.isShown() && this.mAnimator == null) {
            if (keyEvent.getAction() == 1) {
                this.dismiss((ViewGroup)this.getParent());
            }
            return true;
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }
    
    protected ViewGroup$MarginLayoutParams generateDefaultLayoutParams() {
        return new ViewGroup$MarginLayoutParams(-1, -1);
    }
    
    public ViewGroup$MarginLayoutParams generateLayoutParams(final AttributeSet set) {
        return new ViewGroup$MarginLayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$MarginLayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return new ViewGroup$MarginLayoutParams(viewGroup$LayoutParams);
    }
    
    public View getTarget() {
        return this.mTarget;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mParent = (View)this.getParent();
    }
    
    protected void onDetachedFromWindow() {
        this.mParent = null;
        super.onDetachedFromWindow();
    }
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitle = (FrameLayout)this.findViewById(R$id.title);
        this.mTextTitle = (TextView)this.findViewById(R$id.title_text);
        this.mImageTitle = (ImageView)this.findViewById(R$id.title_image);
        this.mDetail = (TextView)this.findViewById(R$id.detail);
        (this.mTooltip = (ViewGroup)this.findViewById(R$id.tooltip)).setBackground((Drawable)this.mTooltipDesignDrawable);
        this.mTooltip.setOnClickListener((View$OnClickListener)new TooltipLayout$3(this));
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.mTooltip.layout(this.mTooltipViewBounds.left, this.mTooltipViewBounds.top, this.mTooltipViewBounds.right, this.mTooltipViewBounds.bottom);
    }
    
    protected void onMeasure(int n, int n2) {
        super.onMeasure(n, n2);
        if (this.mTarget != null && this.mParent != null && (this.mLastMeasuredWidth != this.getMeasuredWidth() || this.mLastMeasuredHeight != this.getMeasuredHeight())) {
            this.mTarget.getLocationInWindow(this.mLocationHelper);
            this.mParent.getLocationInWindow(this.mLocationHelperParent);
            final int[] mLocationHelper = this.mLocationHelper;
            mLocationHelper[0] -= this.mLocationHelperParent[0];
            final int[] mLocationHelper2 = this.mLocationHelper;
            mLocationHelper2[1] -= this.mLocationHelperParent[1];
            this.mTargetBounds.left = this.mLocationHelper[0];
            this.mTargetBounds.top = this.mLocationHelper[1];
            this.mTargetBounds.right = this.mLocationHelper[0] + this.mTarget.getMeasuredWidth();
            this.mTargetBounds.bottom = this.mLocationHelper[1] + this.mTarget.getMeasuredHeight();
            n = Math.min(this.mTooltipMaxWidth, this.getMeasuredWidth());
            this.measureChildWithMargins((View)this.mTooltip, View$MeasureSpec.makeMeasureSpec(n, 1073741824), 0, n2, 0);
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)this.mTooltip.getLayoutParams();
            this.mTooltipViewBounds.top = (int)this.mTargetBounds.bottom + viewGroup$MarginLayoutParams.topMargin;
            this.mTooltipViewBounds.bottom = this.mTooltipViewBounds.top + this.mTooltip.getMeasuredHeight();
            this.mTooltipViewBounds.left = Math.max(0, (int)this.mTargetBounds.centerX() - this.mTooltip.getMeasuredWidth() / 2) + viewGroup$MarginLayoutParams.getMarginStart();
            this.mTooltipViewBounds.right = this.mTooltipViewBounds.left + this.mTooltip.getMeasuredWidth();
            if (this.mTooltipViewBounds.right + viewGroup$MarginLayoutParams.getMarginEnd() > this.getMeasuredWidth()) {
                this.mTooltipViewBounds.left = this.getMeasuredWidth() - this.mTooltip.getMeasuredWidth() - viewGroup$MarginLayoutParams.getMarginEnd();
                this.mTooltipViewBounds.right = this.getMeasuredWidth() - viewGroup$MarginLayoutParams.getMarginEnd();
            }
            if (this.mTooltipViewBounds.top + this.mTooltip.getMeasuredHeight() + this.mTooltipDefaultSize > this.getMeasuredHeight()) {
                this.mGravity = 48;
                this.mTooltipViewBounds.bottom = (int)this.mTargetBounds.top - viewGroup$MarginLayoutParams.topMargin;
                this.mTooltipViewBounds.top = this.mTooltipViewBounds.bottom - this.mTooltip.getMeasuredHeight();
            }
            else {
                this.mGravity = 80;
            }
            this.mArrowCenterXInTooltipBounds = (int)(this.mTargetBounds.centerX() - this.mTooltipViewBounds.left);
            n2 = this.mArrowCenterXInTooltipBounds - this.mArrow.width() / 2;
            n = this.mArrowCenterXInTooltipBounds;
            final int n3 = this.mArrow.width() / 2 + n;
            if (this.getLayoutDirection() == 1) {
                n = this.mTooltip.getMeasuredWidth() - this.mTitle.getMeasuredWidth();
            }
            else {
                n = this.mTitle.getMeasuredWidth();
            }
            if (n2 < n && n3 > n) {
                final int width = this.mArrow.width();
                if (Math.abs(n - n2) > Math.abs(n - n3)) {
                    this.mArrowCenterXInTooltipBounds = n - width;
                }
                else {
                    this.mArrowCenterXInTooltipBounds = n + width;
                }
            }
            n = this.mArrowCenterXInTooltipBounds;
            this.mArrowCenterXInTooltipBounds = viewGroup$MarginLayoutParams.getMarginStart() + n;
            this.mTooltipDesignDrawable.update();
            this.mLastMeasuredWidth = this.getMeasuredWidth();
            this.mLastMeasuredHeight = this.getMeasuredHeight();
        }
    }
    
    public void requestLayout() {
        this.mLastMeasuredWidth = 0;
        this.mLastMeasuredHeight = 0;
        super.requestLayout();
    }
    
    public void setAccentColor(final int mAccentColor) {
        this.mAccentColor = mAccentColor;
        this.requestLayout();
    }
    
    public void setBackgroundColor(final int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
        this.requestLayout();
    }
    
    void setCallback(final Tooltip$Callback mCallback) {
        this.mCallback = mCallback;
    }
    
    public void setDetail(final CharSequence text) {
        this.mDetail.setText(text);
    }
    
    public void setDetailTypeface(final Typeface typeface) {
        this.mDetail.setTypeface(typeface);
    }
    
    public void setScrimColor(final int color) {
        this.mScrimPaint.setColor(color);
        this.invalidate();
    }
    
    void setTarget(final View mTarget) {
        this.mTarget = mTarget;
        ViewCompat.setElevation((View)this, ViewCompat.getElevation(this.mTarget) + this.mTooltipElevation);
        this.requestLayout();
    }
    
    public void setTitle(final Drawable imageDrawable) {
        this.mTextTitle.setVisibility(8);
        this.mImageTitle.setVisibility(0);
        this.mImageTitle.setImageDrawable(imageDrawable);
    }
    
    public void setTitle(final CharSequence text) {
        this.mImageTitle.setVisibility(8);
        this.mTextTitle.setVisibility(0);
        this.mTextTitle.setText(text);
    }
    
    public void setTitleTypeface(final Typeface typeface) {
        this.mTextTitle.setTypeface(typeface);
    }
    
    void setUserOnClickListener(final View$OnClickListener mUserOnClickListener) {
        this.mUserOnClickListener = mUserOnClickListener;
    }
    
    void show(final ViewGroup viewGroup) {
        if (this.mAnimator == null) {
            viewGroup.addView((View)this);
            this.setAlpha(0.0f);
            (this.mAnimator = this.animate().alpha(1.0f).setDuration((long)viewGroup.getResources().getInteger(17694720))).setListener((Animator$AnimatorListener)new TooltipLayout$1(this));
            this.mAnimator.start();
        }
    }
}
