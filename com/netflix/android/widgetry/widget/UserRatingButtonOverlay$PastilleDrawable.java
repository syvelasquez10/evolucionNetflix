// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.animation.TimeInterpolator;
import android.animation.Animator$AnimatorListener;
import android.animation.ValueAnimator;
import android.view.ViewGroup$LayoutParams;
import android.support.design.widget.CoordinatorLayout$LayoutParams;
import android.view.MotionEvent;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import com.netflix.android.widgetry.R$drawable;
import android.view.View$OnClickListener;
import com.netflix.android.widgetry.R$id;
import android.graphics.drawable.ColorDrawable;
import com.netflix.android.widgetry.R$layout;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.graphics.Point;
import android.support.design.widget.CoordinatorLayout;
import android.graphics.Rect;
import android.widget.ImageView;
import android.view.animation.Interpolator;
import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.content.res.Resources;
import android.content.Context;
import com.netflix.android.widgetry.R$dimen;
import com.netflix.android.widgetry.R$color;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.content.ContextCompat;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

class UserRatingButtonOverlay$PastilleDrawable extends Drawable
{
    private static final float ICON_DOWNSCALE = 0.15f;
    private boolean mApplyAlphaAlsoToIcon;
    private final Paint mBackgroundPaint;
    private Drawable mIcon;
    private float mScale;
    private boolean mSelected;
    private final Drawable mSelectedIcon;
    private final Paint mSelectionPaint;
    private final int mSize;
    private final Paint mStrokePaint;
    private final Drawable mUnselectedIcon;
    final /* synthetic */ UserRatingButtonOverlay this$0;
    
    private UserRatingButtonOverlay$PastilleDrawable(final UserRatingButtonOverlay this$0, int n, final int n2, final boolean b) {
        this.this$0 = this$0;
        this.mBackgroundPaint = new Paint();
        this.mSelectionPaint = new Paint();
        this.mStrokePaint = new Paint();
        this.mScale = 1.0f;
        this.mApplyAlphaAlsoToIcon = true;
        this.mSelected = false;
        this.mUnselectedIcon = DrawableCompat.wrap(ContextCompat.getDrawable(this$0.getContext(), n).mutate());
        final Drawable mUnselectedIcon = this.mUnselectedIcon;
        final Context context = this$0.getContext();
        if (this$0.mDark) {
            n = R$color.thumb_button_dark_foreground;
        }
        else {
            n = R$color.thumb_button_light_foreground;
        }
        DrawableCompat.setTint(mUnselectedIcon, ContextCompat.getColor(context, n));
        this.mSelectedIcon = DrawableCompat.wrap(ContextCompat.getDrawable(this$0.getContext(), n2).mutate());
        final Drawable mSelectedIcon = this.mSelectedIcon;
        final Context context2 = this$0.getContext();
        if (this$0.mDark) {
            n = R$color.thumb_button_dark_foreground;
        }
        else {
            n = R$color.thumb_button_light_foreground;
        }
        DrawableCompat.setTint(mSelectedIcon, ContextCompat.getColor(context2, n));
        final Resources resources = this$0.getResources();
        if (b) {
            n = R$dimen.thumbs_size;
        }
        else {
            n = R$dimen.thumbs_close_size;
        }
        this.mSize = resources.getDimensionPixelOffset(n);
        this.updatePaints(b);
        this.setSelected(false);
    }
    
    private void updatePaints(final boolean b) {
        if (this.this$0.mDark) {
            this.mSelectionPaint.setColor(ContextCompat.getColor(this.this$0.getContext(), R$color.thumb_button_dark_foreground));
            final Paint mBackgroundPaint = this.mBackgroundPaint;
            final Context context = this.this$0.getContext();
            int n;
            if (b) {
                n = R$color.thumb_button_dark_background;
            }
            else {
                n = R$color.thumb_button_dark_close_background;
            }
            mBackgroundPaint.setColor(ContextCompat.getColor(context, n));
            final Paint mStrokePaint = this.mStrokePaint;
            final Context context2 = this.this$0.getContext();
            int n2;
            if (b) {
                n2 = R$color.thumb_button_dark_stroke;
            }
            else {
                n2 = R$color.thumb_button_dark_close_stroke;
            }
            mStrokePaint.setColor(ContextCompat.getColor(context2, n2));
        }
        else {
            this.mSelectionPaint.setColor(ContextCompat.getColor(this.this$0.getContext(), R$color.thumb_button_light_foreground));
            final Paint mBackgroundPaint2 = this.mBackgroundPaint;
            final Context context3 = this.this$0.getContext();
            int n3;
            if (b) {
                n3 = R$color.thumb_button_light_background;
            }
            else {
                n3 = R$color.thumb_button_light_close_background;
            }
            mBackgroundPaint2.setColor(ContextCompat.getColor(context3, n3));
            final Paint mStrokePaint2 = this.mStrokePaint;
            final Context context4 = this.this$0.getContext();
            int n4;
            if (b) {
                n4 = R$color.thumb_button_light_stroke;
            }
            else {
                n4 = R$color.thumb_button_light_close_stroke;
            }
            mStrokePaint2.setColor(ContextCompat.getColor(context4, n4));
        }
        this.mBackgroundPaint.setAntiAlias(true);
        this.mBackgroundPaint.setStyle(Paint$Style.FILL);
        this.mSelectionPaint.setAntiAlias(true);
        this.mSelectionPaint.setStyle(Paint$Style.FILL);
        this.mSelectionPaint.setAlpha(0);
        this.mStrokePaint.setAntiAlias(true);
        this.mStrokePaint.setStyle(Paint$Style.STROKE);
        this.mStrokePaint.setStrokeWidth((float)this.this$0.getResources().getDimensionPixelOffset(R$dimen.thumb_button_stroke_width));
    }
    
    public void draw(final Canvas canvas) {
        final float n = this.getBounds().width() / 2;
        final float n2 = (n - this.mStrokePaint.getStrokeWidth()) * this.mScale;
        canvas.drawCircle(n, n, n2, this.mBackgroundPaint);
        if (this.mSelectionPaint.getAlpha() > 0) {
            canvas.drawCircle(n, n, n2, this.mSelectionPaint);
        }
        canvas.drawCircle(n, n, n2, this.mStrokePaint);
        canvas.save();
        final float n3 = this.mScale - this.this$0.mThumbsAnimations.getScaleValue() * 0.15f;
        final float n4 = n - this.mIcon.getBounds().width() * n3 / 2.0f;
        canvas.translate(n4, n4);
        canvas.scale(n3, n3);
        this.mIcon.draw(canvas);
        canvas.restore();
    }
    
    Drawable getCurrentDrawable() {
        if (this.mSelected) {
            return this.mSelectedIcon;
        }
        return this.mUnselectedIcon;
    }
    
    public int getOpacity() {
        return -3;
    }
    
    public boolean isSelected() {
        return this.mSelected;
    }
    
    public void setAlpha(final int alpha) {
        this.mBackgroundPaint.setAlpha(alpha);
        this.mStrokePaint.setAlpha(alpha);
        if (this.mApplyAlphaAlsoToIcon) {
            this.mIcon.setAlpha(alpha);
        }
        else {
            this.mIcon.setAlpha(255);
        }
        this.invalidateSelf();
    }
    
    void setApplyAlphaAlsoToIcon(final boolean mApplyAlphaAlsoToIcon) {
        this.mApplyAlphaAlsoToIcon = mApplyAlphaAlsoToIcon;
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
    }
    
    void setPressedStateAlpha(final int alpha) {
        this.mSelectionPaint.setAlpha(alpha);
        this.invalidateSelf();
    }
    
    public void setScale(final float mScale) {
        this.mScale = mScale;
        this.invalidateSelf();
    }
    
    public void setSelected(final boolean mSelected) {
        if (this.mIcon == null || this.mSelected != mSelected) {
            this.mSelected = mSelected;
            this.mIcon = this.getCurrentDrawable();
            final int n = (int)(this.mSize / 0.68f);
            this.mIcon.setBounds(0, 0, n, n);
            this.invalidateSelf();
        }
    }
}
