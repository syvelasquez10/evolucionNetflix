// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.SharedPreferences;

final class zzbu$2 extends zzbu<Integer>
{
    zzbu$2(final String s, final Integer n) {
        super(s, n, null);
    }
    
    public Integer zzc(final SharedPreferences sharedPreferences) {
        return sharedPreferences.getInt(this.getKey(), (int)this.zzde());
    }
}
