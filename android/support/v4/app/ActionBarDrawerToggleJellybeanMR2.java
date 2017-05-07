// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;

class ActionBarDrawerToggleJellybeanMR2
{
    private static final int[] THEME_ATTRS;
    
    static {
        THEME_ATTRS = new int[] { 16843531 };
    }
    
    public static Object setActionBarDescription(final Object o, final Activity activity, final int homeActionContentDescription) {
        final ActionBar actionBar = activity.getActionBar();
        if (actionBar != null) {
            actionBar.setHomeActionContentDescription(homeActionContentDescription);
        }
        return o;
    }
}
