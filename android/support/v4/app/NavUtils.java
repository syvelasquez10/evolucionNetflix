// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.pm.PackageManager$NameNotFoundException;
import android.support.v4.content.IntentCompat;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.os.Build$VERSION;

public class NavUtils
{
    private static final NavUtils$NavUtilsImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = new NavUtils$NavUtilsImplJB();
            return;
        }
        IMPL = new NavUtils$NavUtilsImplBase();
    }
    
    public static Intent getParentActivityIntent(final Activity activity) {
        return NavUtils.IMPL.getParentActivityIntent(activity);
    }
    
    public static Intent getParentActivityIntent(final Context context, ComponentName component) {
        final String parentActivityName = getParentActivityName(context, component);
        if (parentActivityName == null) {
            return null;
        }
        component = new ComponentName(component.getPackageName(), parentActivityName);
        if (getParentActivityName(context, component) == null) {
            return IntentCompat.makeMainActivity(component);
        }
        return new Intent().setComponent(component);
    }
    
    public static String getParentActivityName(final Activity activity) {
        try {
            return getParentActivityName((Context)activity, activity.getComponentName());
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new IllegalArgumentException((Throwable)ex);
        }
    }
    
    public static String getParentActivityName(final Context context, final ComponentName componentName) {
        return NavUtils.IMPL.getParentActivityName(context, context.getPackageManager().getActivityInfo(componentName, 128));
    }
    
    public static void navigateUpTo(final Activity activity, final Intent intent) {
        NavUtils.IMPL.navigateUpTo(activity, intent);
    }
    
    public static boolean shouldUpRecreateTask(final Activity activity, final Intent intent) {
        return NavUtils.IMPL.shouldUpRecreateTask(activity, intent);
    }
}
