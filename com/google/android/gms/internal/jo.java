// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jo implements SafeParcelable
{
    public static final Parcelable$Creator<jo> CREATOR;
    ju abJ;
    jp adg;
    String label;
    String type;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new jr();
    }
    
    jo() {
        this.xH = 1;
    }
    
    jo(final int xh, final String label, final jp adg, final String type, final ju abJ) {
        this.xH = xh;
        this.label = label;
        this.adg = adg;
        this.type = type;
        this.abJ = abJ;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        jr.a(this, parcel, n);
    }
}
