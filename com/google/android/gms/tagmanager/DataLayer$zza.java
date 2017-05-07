// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Arrays;

final class DataLayer$zza
{
    public final Object zzID;
    public final String zztP;
    
    DataLayer$zza(final String zztP, final Object zzID) {
        this.zztP = zztP;
        this.zzID = zzID;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof DataLayer$zza) {
            final DataLayer$zza dataLayer$zza = (DataLayer$zza)o;
            if (this.zztP.equals(dataLayer$zza.zztP) && this.zzID.equals(dataLayer$zza.zzID)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Integer[] { this.zztP.hashCode(), this.zzID.hashCode() });
    }
    
    @Override
    public String toString() {
        return "Key: " + this.zztP + " value: " + this.zzID.toString();
    }
}
