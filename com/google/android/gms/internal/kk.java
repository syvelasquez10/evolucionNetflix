// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.wearable.f;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class kk implements SafeParcelable, f
{
    public static final Parcelable$Creator<kk> CREATOR;
    private final String HA;
    private final String wp;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new kl();
    }
    
    kk(final int xh, final String wp, final String ha) {
        this.xH = xh;
        this.wp = wp;
        this.HA = ha;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof kk) {
            final kk kk = (kk)o;
            if (kk.wp.equals(this.wp) && kk.HA.equals(this.HA)) {
                return true;
            }
        }
        return false;
    }
    
    public String getDisplayName() {
        return this.HA;
    }
    
    public String getId() {
        return this.wp;
    }
    
    @Override
    public int hashCode() {
        return (this.wp.hashCode() + 629) * 37 + this.HA.hashCode();
    }
    
    @Override
    public String toString() {
        return "NodeParcelable{" + this.wp + "," + this.HA + "}";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        kl.a(this, parcel, n);
    }
}
