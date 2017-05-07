// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences$Editor;
import android.content.Context;

@ez
public final class gh
{
    public static void a(final Context context, final boolean b) {
        final SharedPreferences$Editor edit = n(context).edit();
        edit.putBoolean("use_https", b);
        edit.commit();
    }
    
    private static SharedPreferences n(final Context context) {
        return context.getSharedPreferences("admob", 0);
    }
    
    public static boolean o(final Context context) {
        return n(context).getBoolean("use_https", true);
    }
}
