// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.annotation.TargetApi;

@TargetApi(19)
class DrawableWrapperKitKat extends DrawableWrapperHoneycomb
{
    DrawableWrapperKitKat(final Drawable drawable) {
        super(drawable);
    }
    
    DrawableWrapperKitKat(final DrawableWrapperGingerbread$DrawableWrapperState drawableWrapperGingerbread$DrawableWrapperState, final Resources resources) {
        super(drawableWrapperGingerbread$DrawableWrapperState, resources);
    }
    
    public boolean isAutoMirrored() {
        return this.mDrawable.isAutoMirrored();
    }
    
    @Override
    DrawableWrapperGingerbread$DrawableWrapperState mutateConstantState() {
        return new DrawableWrapperKitKat$DrawableWrapperStateKitKat(this.mState, null);
    }
    
    public void setAutoMirrored(final boolean autoMirrored) {
        this.mDrawable.setAutoMirrored(autoMirrored);
    }
}
