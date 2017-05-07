// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.SharedPreferences;

final class zzbu$4 extends zzbu<String>
{
    zzbu$4(final String s, final String s2) {
        super(s, s2, null);
    }
    
    public String zze(final SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(this.getKey(), (String)this.zzde());
    }
}
