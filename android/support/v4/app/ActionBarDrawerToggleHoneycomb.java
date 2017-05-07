// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.ActionBar;
import android.util.Log;
import android.os.Build$VERSION;
import android.app.Activity;

class ActionBarDrawerToggleHoneycomb
{
    private static final int[] THEME_ATTRS;
    
    static {
        THEME_ATTRS = new int[] { 16843531 };
    }
    
    public static Object setActionBarDescription(Object o, final Activity activity, final int n) {
        if (o == null) {
            o = new ActionBarDrawerToggleHoneycomb$SetIndicatorInfo(activity);
        }
        while (true) {
            final ActionBarDrawerToggleHoneycomb$SetIndicatorInfo actionBarDrawerToggleHoneycomb$SetIndicatorInfo = (ActionBarDrawerToggleHoneycomb$SetIndicatorInfo)o;
            if (actionBarDrawerToggleHoneycomb$SetIndicatorInfo.setHomeAsUpIndicator == null) {
                return o;
            }
            try {
                final ActionBar actionBar = activity.getActionBar();
                actionBarDrawerToggleHoneycomb$SetIndicatorInfo.setHomeActionContentDescription.invoke(actionBar, n);
                if (Build$VERSION.SDK_INT <= 19) {
                    actionBar.setSubtitle(actionBar.getSubtitle());
                }
                return o;
            }
            catch (Exception ex) {
                Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set content description via JB-MR2 API", (Throwable)ex);
                return o;
            }
            continue;
        }
    }
}
