// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.annotation.Nullable;
import android.graphics.drawable.Drawable;

public interface ActionBarDrawerToggle$Delegate
{
    @Nullable
    Drawable getThemeUpIndicator();
    
    void setActionBarDescription(final int p0);
    
    void setActionBarUpIndicator(final Drawable p0, final int p1);
}
