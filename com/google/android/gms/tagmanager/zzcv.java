// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Build$VERSION;
import android.content.SharedPreferences$Editor;

class zzcv
{
    static void zza(final SharedPreferences$Editor sharedPreferences$Editor) {
        if (Build$VERSION.SDK_INT >= 9) {
            sharedPreferences$Editor.apply();
            return;
        }
        new Thread(new zzcv$1(sharedPreferences$Editor)).start();
    }
    
    static void zzb(final Context context, final String s, final String s2, final String s3) {
        final SharedPreferences$Editor edit = context.getSharedPreferences(s, 0).edit();
        edit.putString(s2, s3);
        zza(edit);
    }
}
