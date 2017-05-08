// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import com.crittercism.app.CrittercismConfig;
import android.content.Context;

public final class bd
{
    public boolean a;
    public boolean b;
    public boolean c;
    
    public bd(final Context context, final CrittercismConfig crittercismConfig) {
        this.a = crittercismConfig.isLogcatReportingEnabled();
        this.c = a("android.permission.ACCESS_NETWORK_STATE", context);
        this.b = a("android.permission.GET_TASKS", context);
    }
    
    private static boolean a(final String s, final Context context) {
        return context.getPackageManager().checkPermission(s, context.getPackageName()) == 0;
    }
}
