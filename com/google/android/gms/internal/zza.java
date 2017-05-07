// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;

public class zza extends zzr
{
    private Intent zza;
    
    public zza() {
    }
    
    public zza(final zzi zzi) {
        super(zzi);
    }
    
    @Override
    public String getMessage() {
        if (this.zza != null) {
            return "User needs to (re)enter credentials.";
        }
        return super.getMessage();
    }
}
