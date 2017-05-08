// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.ComponentName;
import android.app.Activity;
import android.view.MenuItem;
import android.view.Menu;
import android.os.Build$VERSION;

public final class ShareCompat
{
    public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    static ShareCompat$ShareCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            ShareCompat.IMPL = new ShareCompat$ShareCompatImplJB();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            ShareCompat.IMPL = new ShareCompat$ShareCompatImplICS();
            return;
        }
        ShareCompat.IMPL = new ShareCompat$ShareCompatImplBase();
    }
    
    public static void configureMenuItem(final Menu menu, final int n, final ShareCompat$IntentBuilder shareCompat$IntentBuilder) {
        final MenuItem item = menu.findItem(n);
        if (item == null) {
            throw new IllegalArgumentException("Could not find menu item with id " + n + " in the supplied menu");
        }
        configureMenuItem(item, shareCompat$IntentBuilder);
    }
    
    public static void configureMenuItem(final MenuItem menuItem, final ShareCompat$IntentBuilder shareCompat$IntentBuilder) {
        ShareCompat.IMPL.configureMenuItem(menuItem, shareCompat$IntentBuilder);
    }
    
    public static ComponentName getCallingActivity(final Activity activity) {
        ComponentName callingActivity;
        if ((callingActivity = activity.getCallingActivity()) == null) {
            callingActivity = (ComponentName)activity.getIntent().getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY");
        }
        return callingActivity;
    }
    
    public static String getCallingPackage(final Activity activity) {
        String s;
        if ((s = activity.getCallingPackage()) == null) {
            s = activity.getIntent().getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE");
        }
        return s;
    }
}
