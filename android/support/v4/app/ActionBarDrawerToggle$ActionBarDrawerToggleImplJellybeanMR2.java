// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.graphics.drawable.Drawable;
import android.app.Activity;
import android.annotation.TargetApi;

@TargetApi(18)
class ActionBarDrawerToggle$ActionBarDrawerToggleImplJellybeanMR2 implements ActionBarDrawerToggle$ActionBarDrawerToggleImpl
{
    @Override
    public Drawable getThemeUpIndicator(final Activity activity) {
        return ActionBarDrawerToggleJellybeanMR2.getThemeUpIndicator(activity);
    }
    
    @Override
    public Object setActionBarDescription(final Object o, final Activity activity, final int n) {
        return ActionBarDrawerToggleJellybeanMR2.setActionBarDescription(o, activity, n);
    }
    
    @Override
    public Object setActionBarUpIndicator(final Object o, final Activity activity, final Drawable drawable, final int n) {
        return ActionBarDrawerToggleJellybeanMR2.setActionBarUpIndicator(o, activity, drawable, n);
    }
}
