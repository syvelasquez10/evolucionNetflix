// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.SharedPreferences$Editor;
import android.content.Context;

public final class dp
{
    public static boolean a;
    
    static {
        dp.a = false;
    }
    
    public static Boolean a(final Context context) {
        return context.getSharedPreferences("com.crittercism.usersettings", 0).getBoolean("crashedOnLastLoad", false);
    }
    
    public static void a(final Context context, final boolean b) {
        final SharedPreferences$Editor edit = context.getSharedPreferences("com.crittercism.usersettings", 0).edit();
        edit.putBoolean("crashedOnLastLoad", b);
        edit.commit();
    }
}
