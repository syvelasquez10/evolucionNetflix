// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzu;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Scope implements SafeParcelable
{
    public static final Parcelable$Creator<Scope> CREATOR;
    final int zzCY;
    private final String zzXN;
    
    static {
        CREATOR = (Parcelable$Creator)new zzj();
    }
    
    Scope(final int zzCY, final String zzXN) {
        zzu.zzh(zzXN, "scopeUri must not be null or empty");
        this.zzCY = zzCY;
        this.zzXN = zzXN;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof Scope && this.zzXN.equals(((Scope)o).zzXN));
    }
    
    @Override
    public int hashCode() {
        return this.zzXN.hashCode();
    }
    
    @Override
    public String toString() {
        return this.zzXN;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzj.zza(this, parcel, n);
    }
    
    public String zzmQ() {
        return this.zzXN;
    }
}
