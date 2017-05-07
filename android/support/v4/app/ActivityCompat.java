// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.os.Build$VERSION;
import android.app.Activity;
import android.support.v4.content.ContextCompat;

public class ActivityCompat extends ContextCompat
{
    public static void finishAffinity(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.finishAffinity(activity);
            return;
        }
        activity.finish();
    }
    
    public static boolean invalidateOptionsMenu(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 11) {
            ActivityCompatHoneycomb.invalidateOptionsMenu(activity);
            return true;
        }
        return false;
    }
    
    public static void startActivity(final Activity activity, final Intent intent, final Bundle bundle) {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.startActivity((Context)activity, intent, bundle);
            return;
        }
        activity.startActivity(intent);
    }
    
    public static void startActivityForResult(final Activity activity, final Intent intent, final int n, final Bundle bundle) {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.startActivityForResult(activity, intent, n, bundle);
            return;
        }
        activity.startActivityForResult(intent, n);
    }
}
