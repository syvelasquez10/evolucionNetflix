// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.annotation.TargetApi;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import android.support.design.R$dimen;
import android.view.View;
import android.os.Build$VERSION;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.widget.ImageView;

@CoordinatorLayout$DefaultBehavior(FloatingActionButton$Behavior.class)
public class FloatingActionButton extends ImageView
{
    private ColorStateList mBackgroundTint;
    private PorterDuff$Mode mBackgroundTintMode;
    private int mBorderWidth;
    private int mContentPadding;
    private final FloatingActionButtonImpl mImpl;
    private int mRippleColor;
    private final Rect mShadowPadding;
    private int mSize;
    
    public FloatingActionButton(final Context context) {
        this(context, null);
    }
    
    public FloatingActionButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public FloatingActionButton(final Context context, final AttributeSet set, int sdk_INT) {
        super(context, set, sdk_INT);
        this.mShadowPadding = new Rect();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.FloatingActionButton, sdk_INT, R$style.Widget_Design_FloatingActionButton);
        final Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.FloatingActionButton_android_background);
        this.mBackgroundTint = obtainStyledAttributes.getColorStateList(R$styleable.FloatingActionButton_backgroundTint);
        this.mBackgroundTintMode = parseTintMode(obtainStyledAttributes.getInt(R$styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.mRippleColor = obtainStyledAttributes.getColor(R$styleable.FloatingActionButton_rippleColor, 0);
        this.mSize = obtainStyledAttributes.getInt(R$styleable.FloatingActionButton_fabSize, 0);
        this.mBorderWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.FloatingActionButton_borderWidth, 0);
        final float dimension = obtainStyledAttributes.getDimension(R$styleable.FloatingActionButton_elevation, 0.0f);
        final float dimension2 = obtainStyledAttributes.getDimension(R$styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        obtainStyledAttributes.recycle();
        final FloatingActionButton$1 floatingActionButton$1 = new FloatingActionButton$1(this);
        sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            this.mImpl = new FloatingActionButtonLollipop((View)this, floatingActionButton$1);
        }
        else if (sdk_INT >= 12) {
            this.mImpl = new FloatingActionButtonHoneycombMr1((View)this, floatingActionButton$1);
        }
        else {
            this.mImpl = new FloatingActionButtonEclairMr1((View)this, floatingActionButton$1);
        }
        sdk_INT = (int)this.getResources().getDimension(R$dimen.design_fab_content_size);
        this.mContentPadding = (this.getSizeDimension() - sdk_INT) / 2;
        this.mImpl.setBackgroundDrawable(drawable, this.mBackgroundTint, this.mBackgroundTintMode, this.mRippleColor, this.mBorderWidth);
        this.mImpl.setElevation(dimension);
        this.mImpl.setPressedTranslationZ(dimension2);
        this.setClickable(true);
    }
    
    static PorterDuff$Mode parseTintMode(final int n, final PorterDuff$Mode porterDuff$Mode) {
        switch (n) {
            default: {
                return porterDuff$Mode;
            }
            case 3: {
                return PorterDuff$Mode.SRC_OVER;
            }
            case 5: {
                return PorterDuff$Mode.SRC_IN;
            }
            case 9: {
                return PorterDuff$Mode.SRC_ATOP;
            }
            case 14: {
                return PorterDuff$Mode.MULTIPLY;
            }
            case 15: {
                return PorterDuff$Mode.SCREEN;
            }
        }
    }
    
    private static int resolveAdjustedSize(final int n, int size) {
        final int mode = View$MeasureSpec.getMode(size);
        size = View$MeasureSpec.getSize(size);
        switch (mode) {
            default: {
                return n;
            }
            case Integer.MIN_VALUE: {
                return Math.min(n, size);
            }
            case 1073741824: {
                return size;
            }
        }
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.mImpl.onDrawableStateChanged(this.getDrawableState());
    }
    
    public ColorStateList getBackgroundTintList() {
        return this.mBackgroundTint;
    }
    
    public PorterDuff$Mode getBackgroundTintMode() {
        return this.mBackgroundTintMode;
    }
    
    final int getSizeDimension() {
        switch (this.mSize) {
            default: {
                return this.getResources().getDimensionPixelSize(R$dimen.design_fab_size_normal);
            }
            case 1: {
                return this.getResources().getDimensionPixelSize(R$dimen.design_fab_size_mini);
            }
        }
    }
    
    public void hide() {
        this.mImpl.hide();
    }
    
    @TargetApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.mImpl.jumpDrawableToCurrentState();
    }
    
    protected void onMeasure(int min, final int n) {
        final int sizeDimension = this.getSizeDimension();
        min = Math.min(resolveAdjustedSize(sizeDimension, min), resolveAdjustedSize(sizeDimension, n));
        this.setMeasuredDimension(this.mShadowPadding.left + min + this.mShadowPadding.right, min + this.mShadowPadding.top + this.mShadowPadding.bottom);
    }
    
    public void setBackgroundDrawable(final Drawable drawable) {
        if (this.mImpl != null) {
            this.mImpl.setBackgroundDrawable(drawable, this.mBackgroundTint, this.mBackgroundTintMode, this.mRippleColor, this.mBorderWidth);
        }
    }
    
    public void setBackgroundTintList(final ColorStateList list) {
        if (this.mBackgroundTint != list) {
            this.mBackgroundTint = list;
            this.mImpl.setBackgroundTintList(list);
        }
    }
    
    public void setBackgroundTintMode(final PorterDuff$Mode porterDuff$Mode) {
        if (this.mBackgroundTintMode != porterDuff$Mode) {
            this.mBackgroundTintMode = porterDuff$Mode;
            this.mImpl.setBackgroundTintMode(porterDuff$Mode);
        }
    }
    
    public void setRippleColor(final int n) {
        if (this.mRippleColor != n) {
            this.mRippleColor = n;
            this.mImpl.setRippleColor(n);
        }
    }
    
    public void show() {
        this.mImpl.show();
    }
}
