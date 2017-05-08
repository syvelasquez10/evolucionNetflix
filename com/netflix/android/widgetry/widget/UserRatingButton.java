// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.view.View$OnTouchListener;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.text.TextUtils;
import android.content.res.TypedArray;
import com.netflix.android.widgetry.R$color;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.content.ContextCompat;
import com.netflix.android.widgetry.R$drawable;
import com.netflix.android.widgetry.R$styleable;
import com.netflix.android.widgetry.R$id;
import android.view.ViewGroup;
import com.netflix.android.widgetry.R$layout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.res.ColorStateList;
import android.widget.FrameLayout;

public class UserRatingButton extends FrameLayout
{
    private static final long RIPPLE_DURATION_MS = 1080L;
    private static final String TAG = "UserRatingButton";
    public static final int THUMBS_DOWN = 1;
    public static final String THUMBS_DOWN_STR = "THUMBS_DOWN";
    public static final int THUMBS_UNRATED = 0;
    public static final String THUMBS_UNRATED_STR = "UNRATED";
    public static final int THUMBS_UP = 2;
    public static final String THUMBS_UP_STR = "THUMBS_UP";
    private boolean mDark;
    private ColorStateList mDarkForegroundTextColor;
    private ImageView mIcon;
    private TextView mLabel;
    private UserRatingButton$OnRateListener mOnRateListener;
    private int mRating;
    private boolean mRespectLayoutDirection;
    private Drawable mThumbDownDrawable;
    private CharSequence mThumbDownLabel;
    private Drawable mThumbUpDrawable;
    private CharSequence mThumbUpLabel;
    private Drawable mUnratedDrawable;
    private CharSequence mUnratedLabel;
    private Drawable mUserBackground;
    private UserRatingButtonOverlay mUserRatingButtonOverlay;
    private final Runnable updateIconBackground;
    
    public UserRatingButton(final Context context) {
        super(context);
        this.mDark = true;
        this.updateIconBackground = new UserRatingButton$1(this);
        this.mRating = 0;
        this.mRespectLayoutDirection = true;
        this.sharedConstructor(null);
    }
    
    public UserRatingButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.mDark = true;
        this.updateIconBackground = new UserRatingButton$1(this);
        this.mRating = 0;
        this.mRespectLayoutDirection = true;
        this.sharedConstructor(set);
    }
    
    public UserRatingButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mDark = true;
        this.updateIconBackground = new UserRatingButton$1(this);
        this.mRating = 0;
        this.mRespectLayoutDirection = true;
        this.sharedConstructor(set);
    }
    
    public static int asThumbsRating(final int n) {
        if (n == 2) {
            return 2;
        }
        if (n == 1) {
            return 1;
        }
        return 0;
    }
    
    private void doAction(final CoordinatorLayout coordinatorLayout, final int n, final boolean b) {
        if (this.mRating == 0) {
            this.openOverlay(coordinatorLayout, n, b);
            return;
        }
        this.removeRating();
    }
    
    private void openOverlay(final CoordinatorLayout coordinatorLayout, final int n, final boolean b) {
        if (this.mOnRateListener == null) {
            throw new IllegalStateException("openOverlay called before user set a OnRateListener");
        }
        if (this.mUserRatingButtonOverlay == null) {
            this.mUserRatingButtonOverlay = new UserRatingButtonOverlay(this.getContext(), this.mOnRateListener, this.mDark, this.mRespectLayoutDirection, this.getLayoutDirection());
        }
        this.getParent().requestDisallowInterceptTouchEvent(true);
        this.mUserRatingButtonOverlay.show(coordinatorLayout, this, n);
        this.mOnRateListener.onOpened(this, b);
    }
    
    private void refresh(final long n) {
        CharSequence text = null;
        final int imageAlpha = this.mIcon.getImageAlpha();
        Drawable imageDrawable = null;
        switch (this.mRating) {
            default: {
                imageDrawable = null;
                break;
            }
            case 1: {
                imageDrawable = this.mThumbDownDrawable;
                text = this.mThumbDownLabel;
                break;
            }
            case 2: {
                imageDrawable = this.mThumbUpDrawable;
                text = this.mThumbUpLabel;
                break;
            }
            case 0: {
                imageDrawable = this.mUnratedDrawable;
                text = this.mUnratedLabel;
                break;
            }
        }
        this.mIcon.setImageDrawable(imageDrawable);
        this.mIcon.setImageAlpha(imageAlpha);
        this.mLabel.setText(text);
        this.updateIconBackground(n);
    }
    
    private void removeRating() {
        if (this.mOnRateListener != null) {
            this.mOnRateListener.onRate(this, 0);
            this.setRating(0, 1080L);
        }
    }
    
    private void setRating(final int mRating, final long n) {
        if (this.mRating != mRating) {
            this.mRating = mRating;
            this.refresh(n);
        }
    }
    
    private void sharedConstructor(final AttributeSet set) {
        inflate(this.getContext(), R$layout.user_rating_button, (ViewGroup)this);
        this.mIcon = (ImageView)this.findViewById(R$id.user_rating_button_icon);
        this.mLabel = (TextView)this.findViewById(R$id.user_rating_button_label);
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R$styleable.UserRatingButton);
        this.mThumbUpLabel = obtainStyledAttributes.getText(R$styleable.UserRatingButton_urb_thumb_up_label);
        this.mThumbDownLabel = obtainStyledAttributes.getText(R$styleable.UserRatingButton_urb_thumb_down_label);
        this.mUnratedLabel = obtainStyledAttributes.getText(R$styleable.UserRatingButton_urb_unrated_label);
        this.mThumbUpDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this.getContext(), R$drawable.ic_thumbs_up).mutate());
        this.mThumbDownDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this.getContext(), R$drawable.ic_thumbs_down).mutate());
        this.mUnratedDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this.getContext(), R$drawable.ic_thumbs_up_outline).mutate());
        final ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R$styleable.UserRatingButton_urb_text_color);
        if (colorStateList != null) {
            this.mDarkForegroundTextColor = colorStateList;
        }
        else {
            this.mDarkForegroundTextColor = ContextCompat.getColorStateList(this.getContext(), R$color.thumb_button_dark_foreground);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.UserRatingButton_urb_text_size)) {
            this.mLabel.setTextSize(0, obtainStyledAttributes.getDimension(R$styleable.UserRatingButton_urb_text_size, this.mLabel.getTextSize()));
        }
        this.setDark(obtainStyledAttributes.getBoolean(R$styleable.UserRatingButton_urb_dark, true));
        obtainStyledAttributes.recycle();
        this.refresh(0L);
    }
    
    public static int thumbRatingFromString(final String s) {
        if (TextUtils.equals((CharSequence)s, (CharSequence)"THUMBS_DOWN")) {
            return 2;
        }
        if (TextUtils.equals((CharSequence)s, (CharSequence)"THUMBS_UP")) {
            return 1;
        }
        return 0;
    }
    
    private void updateIconBackground() {
        if (this.mRating != 0) {
            this.mIcon.setBackground(this.mUserBackground);
            return;
        }
        this.mIcon.setBackground((Drawable)null);
    }
    
    private void updateIconBackground(final long n) {
        if (n > 0L) {
            this.removeCallbacks(this.updateIconBackground);
            this.postDelayed(this.updateIconBackground, n);
            return;
        }
        this.updateIconBackground();
    }
    
    private UserRatingButton$OnRateListener wrap(final UserRatingButton$OnRateListener userRatingButton$OnRateListener) {
        return new UserRatingButton$5(this, userRatingButton$OnRateListener);
    }
    
    public ImageView getIconView() {
        return this.mIcon;
    }
    
    public TextView getLabelView() {
        return this.mLabel;
    }
    
    public int getRating() {
        return this.mRating;
    }
    
    public void setBackground(final Drawable mUserBackground) {
        this.mUserBackground = mUserBackground;
        if (this.mIcon != null) {
            this.updateIconBackground();
        }
    }
    
    public void setDark(final boolean mDark) {
        this.mDark = mDark;
        final Context context = this.getContext();
        int n;
        if (this.mDark) {
            n = R$color.thumb_button_dark_foreground;
        }
        else {
            n = R$color.thumb_button_light_foreground;
        }
        final ColorStateList colorStateList = ContextCompat.getColorStateList(context, n);
        DrawableCompat.setTintList(this.mThumbUpDrawable, colorStateList);
        DrawableCompat.setTintList(this.mThumbDownDrawable, colorStateList);
        DrawableCompat.setTintList(this.mUnratedDrawable, colorStateList);
        final TextView mLabel = this.mLabel;
        ColorStateList textColor;
        if (this.mDark) {
            textColor = this.mDarkForegroundTextColor;
        }
        else {
            textColor = ContextCompat.getColorStateList(this.getContext(), R$color.thumb_button_light_foreground);
        }
        mLabel.setTextColor(textColor);
    }
    
    public void setOnClickListener(final View$OnClickListener view$OnClickListener) {
        throw new UnsupportedOperationException("Don't call to setOnClickListener, instead call setOnRateListener");
    }
    
    public void setOnRateListener(final CoordinatorLayout coordinatorLayout, final UserRatingButton$OnRateListener userRatingButton$OnRateListener, final boolean b, final int n) {
        if (!this.isClickable()) {
            this.setClickable(true);
        }
        this.mOnRateListener = this.wrap(userRatingButton$OnRateListener);
        this.mIcon.setOnClickListener((View$OnClickListener)new UserRatingButton$2(this, coordinatorLayout, n));
        if (b) {
            this.mIcon.setOnLongClickListener((View$OnLongClickListener)new UserRatingButton$3(this, coordinatorLayout, n));
            this.mIcon.setOnTouchListener((View$OnTouchListener)new UserRatingButton$4(this));
        }
    }
    
    public void setRating(final int n) {
        this.setRating(n, 0L);
    }
    
    public void setRespectLayoutDirection(final boolean mRespectLayoutDirection) {
        this.mRespectLayoutDirection = mRespectLayoutDirection;
    }
}
