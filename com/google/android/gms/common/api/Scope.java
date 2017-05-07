// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzx;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Scope implements SafeParcelable
{
    public static final Parcelable$Creator<Scope> CREATOR;
    final int mVersionCode;
    private final String zzaaC;
    
    static {
        CREATOR = (Parcelable$Creator)new zzm();
    }
    
    Scope(final int mVersionCode, final String zzaaC) {
        zzx.zzh(zzaaC, "scopeUri must not be null or empty");
        this.mVersionCode = mVersionCode;
        this.zzaaC = zzaaC;
    }
    
    public Scope(final String s) {
        this(1, s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof Scope && this.zzaaC.equals(((Scope)o).zzaaC));
    }
    
    @Override
    public int hashCode() {
        return this.zzaaC.hashCode();
    }
    
    @Override
    public String toString() {
        return this.zzaaC;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzm.zza(this, parcel, n);
    }
    
    public String zznG() {
        return this.zzaaC;
    }
}
