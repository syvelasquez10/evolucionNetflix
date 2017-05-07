// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.app.ActionBar;
import android.util.Log;
import android.os.Build$VERSION;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.app.Activity;

class ActionBarDrawerToggleHoneycomb
{
    private static final String TAG = "ActionBarDrawerToggleHoneycomb";
    private static final int[] THEME_ATTRS;
    
    static {
        THEME_ATTRS = new int[] { 16843531 };
    }
    
    public static Drawable getThemeUpIndicator(final Activity activity) {
        final TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(ActionBarDrawerToggleHoneycomb.THEME_ATTRS);
        final Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }
    
    public static ActionBarDrawerToggleHoneycomb$SetIndicatorInfo setActionBarDescription(final ActionBarDrawerToggleHoneycomb$SetIndicatorInfo actionBarDrawerToggleHoneycomb$SetIndicatorInfo, final Activity activity, final int n) {
        ActionBarDrawerToggleHoneycomb$SetIndicatorInfo actionBarDrawerToggleHoneycomb$SetIndicatorInfo2 = actionBarDrawerToggleHoneycomb$SetIndicatorInfo;
        if (actionBarDrawerToggleHoneycomb$SetIndicatorInfo == null) {
            actionBarDrawerToggleHoneycomb$SetIndicatorInfo2 = new ActionBarDrawerToggleHoneycomb$SetIndicatorInfo(activity);
        }
        if (actionBarDrawerToggleHoneycomb$SetIndicatorInfo2.setHomeAsUpIndicator == null) {
            return actionBarDrawerToggleHoneycomb$SetIndicatorInfo2;
        }
        try {
            final ActionBar actionBar = activity.getActionBar();
            actionBarDrawerToggleHoneycomb$SetIndicatorInfo2.setHomeActionContentDescription.invoke(actionBar, n);
            if (Build$VERSION.SDK_INT <= 19) {
                actionBar.setSubtitle(actionBar.getSubtitle());
            }
            return actionBarDrawerToggleHoneycomb$SetIndicatorInfo2;
        }
        catch (Exception ex) {
            Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set content description via JB-MR2 API", (Throwable)ex);
            return actionBarDrawerToggleHoneycomb$SetIndicatorInfo2;
        }
    }
    
    public static ActionBarDrawerToggleHoneycomb$SetIndicatorInfo setActionBarUpIndicator(ActionBarDrawerToggleHoneycomb$SetIndicatorInfo actionBarDrawerToggleHoneycomb$SetIndicatorInfo, final Activity activity, final Drawable imageDrawable, final int n) {
        actionBarDrawerToggleHoneycomb$SetIndicatorInfo = new ActionBarDrawerToggleHoneycomb$SetIndicatorInfo(activity);
        if (actionBarDrawerToggleHoneycomb$SetIndicatorInfo.setHomeAsUpIndicator != null) {
            try {
                final ActionBar actionBar = activity.getActionBar();
                actionBarDrawerToggleHoneycomb$SetIndicatorInfo.setHomeAsUpIndicator.invoke(actionBar, imageDrawable);
                actionBarDrawerToggleHoneycomb$SetIndicatorInfo.setHomeActionContentDescription.invoke(actionBar, n);
                return actionBarDrawerToggleHoneycomb$SetIndicatorInfo;
            }
            catch (Exception ex) {
                Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set home-as-up indicator via JB-MR2 API", (Throwable)ex);
                return actionBarDrawerToggleHoneycomb$SetIndicatorInfo;
            }
        }
        if (actionBarDrawerToggleHoneycomb$SetIndicatorInfo.upIndicatorView != null) {
            actionBarDrawerToggleHoneycomb$SetIndicatorInfo.upIndicatorView.setImageDrawable(imageDrawable);
            return actionBarDrawerToggleHoneycomb$SetIndicatorInfo;
        }
        Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set home-as-up indicator");
        return actionBarDrawerToggleHoneycomb$SetIndicatorInfo;
    }
}
