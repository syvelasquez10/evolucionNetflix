// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.graphics.drawable.Drawable;
import android.content.res.Resources;

class TintContextWrapper$TintResources extends ResourcesWrapper
{
    private final TintManager mTintManager;
    
    public TintContextWrapper$TintResources(final Resources resources, final TintManager mTintManager) {
        super(resources);
        this.mTintManager = mTintManager;
    }
    
    @Override
    public Drawable getDrawable(final int n) {
        final Drawable drawable = super.getDrawable(n);
        if (drawable != null) {
            this.mTintManager.tintDrawableUsingColorFilter(n, drawable);
        }
        return drawable;
    }
}
