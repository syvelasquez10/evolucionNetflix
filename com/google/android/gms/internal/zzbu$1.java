// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.SharedPreferences;

final class zzbu$1 extends zzbu<Boolean>
{
    zzbu$1(final String s, final Boolean b) {
        super(s, b, null);
    }
    
    public Boolean zzb(final SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean(this.getKey(), (boolean)this.zzde());
    }
}
