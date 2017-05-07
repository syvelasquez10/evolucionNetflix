// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class InstrumentInfo implements SafeParcelable
{
    public static final Parcelable$Creator<InstrumentInfo> CREATOR;
    private final int BR;
    private String asC;
    private String asD;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
    }
    
    InstrumentInfo(final int br, final String asC, final String asD) {
        this.BR = br;
        this.asC = asC;
        this.asD = asD;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getInstrumentDetails() {
        return this.asD;
    }
    
    public String getInstrumentType() {
        return this.asC;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
