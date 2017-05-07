// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.support.v7.widget.Toolbar;

class ActionBarDrawerToggle$ToolbarCompatDelegate implements ActionBarDrawerToggle$Delegate
{
    final Toolbar mToolbar;
    
    ActionBarDrawerToggle$ToolbarCompatDelegate(final Toolbar mToolbar) {
        this.mToolbar = mToolbar;
    }
    
    @Override
    public Context getActionBarThemedContext() {
        return this.mToolbar.getContext();
    }
    
    @Override
    public Drawable getThemeUpIndicator() {
        final TypedArray obtainStyledAttributes = this.mToolbar.getContext().obtainStyledAttributes(new int[] { 16908332 });
        final Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }
    
    @Override
    public void setActionBarDescription(final int navigationContentDescription) {
        this.mToolbar.setNavigationContentDescription(navigationContentDescription);
    }
    
    @Override
    public void setActionBarUpIndicator(final Drawable navigationIcon, final int navigationContentDescription) {
        this.mToolbar.setNavigationIcon(navigationIcon);
        this.mToolbar.setNavigationContentDescription(navigationContentDescription);
    }
}
