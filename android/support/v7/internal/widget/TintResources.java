// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.graphics.drawable.Drawable;
import android.content.res.Resources;

class TintResources extends Resources
{
    private final TintManager mTintManager;
    
    public TintResources(final Resources resources, final TintManager mTintManager) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.mTintManager = mTintManager;
    }
    
    public Drawable getDrawable(final int n) {
        final Drawable drawable = super.getDrawable(n);
        if (drawable != null) {
            this.mTintManager.tintDrawable(n, drawable);
        }
        return drawable;
    }
}
