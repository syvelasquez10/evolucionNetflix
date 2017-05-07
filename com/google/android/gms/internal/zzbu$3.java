// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.SharedPreferences;

final class zzbu$3 extends zzbu<Long>
{
    zzbu$3(final String s, final Long n) {
        super(s, n, null);
    }
    
    public Long zzd(final SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(this.getKey(), (long)this.zzde());
    }
}
