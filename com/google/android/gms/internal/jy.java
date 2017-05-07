// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jy implements SafeParcelable
{
    public static final Parcelable$Creator<jy> CREATOR;
    String adn;
    ju adr;
    jw ads;
    jw adt;
    String pm;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new jz();
    }
    
    jy() {
        this.xH = 1;
    }
    
    jy(final int xh, final String adn, final String pm, final ju adr, final jw ads, final jw adt) {
        this.xH = xh;
        this.adn = adn;
        this.pm = pm;
        this.adr = adr;
        this.ads = ads;
        this.adt = adt;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        jz.a(this, parcel, n);
    }
}
