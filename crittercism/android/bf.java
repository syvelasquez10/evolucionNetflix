// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.Build$VERSION;
import com.crittercism.app.CrittercismConfig;
import android.content.Context;

public final class bf
{
    public boolean a;
    public boolean b;
    public boolean c;
    
    public bf(final Context context, final CrittercismConfig crittercismConfig) {
        boolean a = true;
        if (!crittercismConfig.isLogcatReportingEnabled() || (Build$VERSION.SDK_INT < 16 && !a("android.permission.READ_LOGS", context))) {
            a = false;
        }
        this.a = a;
        this.c = a("android.permission.ACCESS_NETWORK_STATE", context);
        this.b = a("android.permission.GET_TASKS", context);
    }
    
    private static boolean a(final String s, final Context context) {
        return context.getPackageManager().checkPermission(s, context.getPackageName()) == 0;
    }
}
