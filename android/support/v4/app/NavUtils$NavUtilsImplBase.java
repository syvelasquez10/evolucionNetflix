// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.support.v4.content.IntentCompat;
import android.content.Context;
import android.content.ComponentName;
import android.content.Intent;
import android.app.Activity;

class NavUtils$NavUtilsImplBase implements NavUtils$NavUtilsImpl
{
    @Override
    public Intent getParentActivityIntent(final Activity activity) {
        final String parentActivityName = NavUtils.getParentActivityName(activity);
        if (parentActivityName == null) {
            return null;
        }
        final ComponentName component = new ComponentName((Context)activity, parentActivityName);
        try {
            if (NavUtils.getParentActivityName((Context)activity, component) == null) {
                return IntentCompat.makeMainActivity(component);
            }
            return new Intent().setComponent(component);
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + parentActivityName + "' in manifest");
            return null;
        }
    }
    
    @Override
    public String getParentActivityName(final Context context, final ActivityInfo activityInfo) {
        String s;
        if (activityInfo.metaData == null) {
            s = null;
        }
        else {
            final String string = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY");
            if (string == null) {
                return null;
            }
            s = string;
            if (string.charAt(0) == '.') {
                return context.getPackageName() + string;
            }
        }
        return s;
    }
    
    @Override
    public void navigateUpTo(final Activity activity, final Intent intent) {
        intent.addFlags(67108864);
        activity.startActivity(intent);
        activity.finish();
    }
    
    @Override
    public boolean shouldUpRecreateTask(final Activity activity, final Intent intent) {
        final String action = activity.getIntent().getAction();
        return action != null && !action.equals("android.intent.action.MAIN");
    }
}
