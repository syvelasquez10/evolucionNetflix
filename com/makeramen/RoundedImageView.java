// 
// Decompiled by Procyon v0.5.30
// 

package com.makeramen;

import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.drawable.LayerDrawable;
import android.content.res.Resources;
import com.netflix.mediaclient.Log;
import android.content.res.TypedArray;
import com.netflix.mediaclient.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.DebugImageView;

public abstract class RoundedImageView extends DebugImageView
{
    public static final int DEFAULT_BORDER_WIDTH = 0;
    public static final int DEFAULT_RADIUS = 0;
    private static final ImageView$ScaleType[] SCALE_TYPES;
    public static final String TAG = "RoundedImageView";
    private ColorStateList mBorderColor;
    private int mBorderWidth;
    private int mCornerRadius;
    private Drawable mDrawable;
    private boolean mOval;
    private int mResource;
    private boolean mRoundBackground;
    private ImageView$ScaleType mScaleType;
    
    static {
        SCALE_TYPES = new ImageView$ScaleType[] { ImageView$ScaleType.MATRIX, ImageView$ScaleType.FIT_XY, ImageView$ScaleType.FIT_START, ImageView$ScaleType.FIT_CENTER, ImageView$ScaleType.FIT_END, ImageView$ScaleType.CENTER, ImageView$ScaleType.CENTER_CROP, ImageView$ScaleType.CENTER_INSIDE };
    }
    
    public RoundedImageView(final Context context) {
        this(context, null);
    }
    
    public RoundedImageView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public RoundedImageView(final Context context, final AttributeSet set, int int1) {
        super(context, set, int1);
        this.mCornerRadius = 0;
        this.mBorderWidth = 0;
        this.mBorderColor = ColorStateList.valueOf(-16777216);
        this.mOval = false;
        this.mRoundBackground = false;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.RoundedImageView, int1, 0);
        int1 = obtainStyledAttributes.getInt(0, -1);
        if (int1 >= 0) {
            this.setScaleType(RoundedImageView.SCALE_TYPES[int1]);
        }
        else {
            this.setScaleType(ImageView$ScaleType.FIT_CENTER);
        }
        this.mCornerRadius = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        this.mBorderWidth = obtainStyledAttributes.getDimensionPixelSize(2, -1);
        if (this.mCornerRadius < 0) {
            this.mCornerRadius = 0;
        }
        if (this.mBorderWidth < 0) {
            this.mBorderWidth = 0;
        }
        this.mBorderColor = obtainStyledAttributes.getColorStateList(3);
        if (this.mBorderColor == null) {
            this.mBorderColor = ColorStateList.valueOf(-16777216);
        }
        this.mRoundBackground = obtainStyledAttributes.getBoolean(4, false);
        this.mOval = obtainStyledAttributes.getBoolean(5, false);
        this.updateDrawableAttrs();
        this.updateBackgroundDrawableAttrs();
        obtainStyledAttributes.recycle();
    }
    
    private Drawable resolveResource() {
        final Drawable drawable = null;
        final Resources resources = this.getResources();
        if (resources == null) {
            return null;
        }
        Drawable drawable2 = drawable;
        Label_0031: {
            if (this.mResource == 0) {
                break Label_0031;
            }
            try {
                drawable2 = resources.getDrawable(this.mResource);
                return RoundedDrawable.fromDrawable(drawable2);
            }
            catch (Exception ex) {
                Log.w("RoundedImageView", "Unable to find resource: " + this.mResource, ex);
                this.mResource = 0;
                drawable2 = drawable;
                return RoundedDrawable.fromDrawable(drawable2);
            }
        }
    }
    
    private void updateAttrs(final Drawable drawable, final boolean b) {
        int i = 0;
        if (drawable != null) {
            if (drawable instanceof RoundedDrawable) {
                final RoundedDrawable setScaleType = ((RoundedDrawable)drawable).setScaleType(this.mScaleType);
                float cornerRadius;
                if (b && !this.mRoundBackground) {
                    cornerRadius = 0.0f;
                }
                else {
                    cornerRadius = this.mCornerRadius;
                }
                final RoundedDrawable setCornerRadius = setScaleType.setCornerRadius(cornerRadius);
                int mBorderWidth;
                if (b && !this.mRoundBackground) {
                    mBorderWidth = 0;
                }
                else {
                    mBorderWidth = this.mBorderWidth;
                }
                setCornerRadius.setBorderWidth(mBorderWidth).setBorderColors(this.mBorderColor).setOval(this.mOval);
                return;
            }
            if (drawable instanceof LayerDrawable) {
                for (LayerDrawable layerDrawable = (LayerDrawable)drawable; i < layerDrawable.getNumberOfLayers(); ++i) {
                    this.updateAttrs(layerDrawable.getDrawable(i), b);
                }
            }
        }
    }
    
    private void updateBackgroundDrawableAttrs() {
    }
    
    private void updateDrawableAttrs() {
        this.updateAttrs(this.mDrawable, false);
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.invalidate();
    }
    
    public int getBorderColor() {
        return this.mBorderColor.getDefaultColor();
    }
    
    public ColorStateList getBorderColors() {
        return this.mBorderColor;
    }
    
    public int getBorderWidth() {
        return this.mBorderWidth;
    }
    
    public int getCornerRadius() {
        return this.mCornerRadius;
    }
    
    public ImageView$ScaleType getScaleType() {
        return this.mScaleType;
    }
    
    public boolean isOval() {
        return this.mOval;
    }
    
    public boolean isRoundBackground() {
        return this.mRoundBackground;
    }
    
    public void setBorderColor(final int n) {
        this.setBorderColors(ColorStateList.valueOf(n));
    }
    
    public void setBorderColors(ColorStateList value) {
        if (!this.mBorderColor.equals(value)) {
            if (value == null) {
                value = ColorStateList.valueOf(-16777216);
            }
            this.mBorderColor = value;
            this.updateDrawableAttrs();
            this.updateBackgroundDrawableAttrs();
            if (this.mBorderWidth > 0) {
                this.invalidate();
            }
        }
    }
    
    public void setBorderWidth(final int mBorderWidth) {
        if (this.mBorderWidth == mBorderWidth) {
            return;
        }
        this.mBorderWidth = mBorderWidth;
        this.updateDrawableAttrs();
        this.updateBackgroundDrawableAttrs();
        this.invalidate();
    }
    
    public void setCornerRadius(final int mCornerRadius) {
        if (this.mCornerRadius == mCornerRadius) {
            return;
        }
        this.mCornerRadius = mCornerRadius;
        this.updateDrawableAttrs();
        this.updateBackgroundDrawableAttrs();
    }
    
    @Override
    public void setImageBitmap(final Bitmap bitmap) {
        this.mResource = 0;
        this.mDrawable = RoundedDrawable.fromBitmap(bitmap);
        this.updateDrawableAttrs();
        super.setImageDrawable(this.mDrawable);
    }
    
    @Override
    public void setImageDrawable(final Drawable imageDrawable) {
        if (this.mCornerRadius == 0 && this.mBorderWidth == 0) {
            super.setImageDrawable(imageDrawable);
            return;
        }
        this.mResource = 0;
        this.mDrawable = RoundedDrawable.fromDrawable(imageDrawable);
        this.updateDrawableAttrs();
        super.setImageDrawable(this.mDrawable);
    }
    
    @Override
    public void setImageResource(final int mResource) {
        if (this.mResource != mResource) {
            this.mResource = mResource;
            this.mDrawable = this.resolveResource();
            this.updateDrawableAttrs();
            super.setImageDrawable(this.mDrawable);
        }
    }
    
    public void setImageURI(final Uri imageURI) {
        super.setImageURI(imageURI);
        this.setImageDrawable(this.getDrawable());
    }
    
    public void setOval(final boolean mOval) {
        this.mOval = mOval;
        this.updateDrawableAttrs();
        this.updateBackgroundDrawableAttrs();
        this.invalidate();
    }
    
    public void setRoundBackground(final boolean mRoundBackground) {
        if (this.mRoundBackground == mRoundBackground) {
            return;
        }
        this.mRoundBackground = mRoundBackground;
        this.updateBackgroundDrawableAttrs();
        this.invalidate();
    }
    
    public void setScaleType(final ImageView$ScaleType imageView$ScaleType) {
        if (imageView$ScaleType == null) {
            throw new NullPointerException();
        }
        if (this.mScaleType != imageView$ScaleType) {
            this.mScaleType = imageView$ScaleType;
            switch (RoundedImageView$1.$SwitchMap$android$widget$ImageView$ScaleType[imageView$ScaleType.ordinal()]) {
                default: {
                    super.setScaleType(imageView$ScaleType);
                    break;
                }
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: {
                    super.setScaleType(ImageView$ScaleType.FIT_XY);
                    break;
                }
            }
            this.updateDrawableAttrs();
            this.updateBackgroundDrawableAttrs();
            this.invalidate();
        }
    }
}
