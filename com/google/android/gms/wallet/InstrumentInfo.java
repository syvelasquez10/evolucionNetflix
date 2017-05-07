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
    private String abt;
    private String abu;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
    }
    
    InstrumentInfo(final int xh, final String abt, final String abu) {
        this.xH = xh;
        this.abt = abt;
        this.abu = abu;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getInstrumentDetails() {
        return this.abu;
    }
    
    public String getInstrumentType() {
        return this.abt;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
