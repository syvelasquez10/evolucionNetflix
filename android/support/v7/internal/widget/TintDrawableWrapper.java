// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;

class TintDrawableWrapper extends DrawableWrapper
{
    private int mCurrentColor;
    private final PorterDuff$Mode mTintMode;
    private final ColorStateList mTintStateList;
    
    public TintDrawableWrapper(final Drawable drawable, final ColorStateList list) {
        this(drawable, list, TintManager.DEFAULT_MODE);
    }
    
    public TintDrawableWrapper(final Drawable drawable, final ColorStateList mTintStateList, final PorterDuff$Mode mTintMode) {
        super(drawable);
        this.mTintStateList = mTintStateList;
        this.mTintMode = mTintMode;
    }
    
    private boolean updateTint(final int[] array) {
        if (this.mTintStateList != null) {
            final int colorForState = this.mTintStateList.getColorForState(array, this.mCurrentColor);
            if (colorForState != this.mCurrentColor) {
                if (colorForState != 0) {
                    this.setColorFilter(colorForState, this.mTintMode);
                }
                else {
                    this.clearColorFilter();
                }
                this.mCurrentColor = colorForState;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isStateful() {
        return (this.mTintStateList != null && this.mTintStateList.isStateful()) || super.isStateful();
    }
    
    @Override
    public boolean setState(final int[] state) {
        final boolean setState = super.setState(state);
        return this.updateTint(state) || setState;
    }
}
