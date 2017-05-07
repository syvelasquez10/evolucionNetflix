// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class js implements SafeParcelable
{
    public static final Parcelable$Creator<js> CREATOR;
    String adn;
    String pm;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new jt();
    }
    
    js() {
        this.xH = 1;
    }
    
    js(final int xh, final String adn, final String pm) {
        this.xH = xh;
        this.adn = adn;
        this.pm = pm;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        jt.a(this, parcel, n);
    }
}
