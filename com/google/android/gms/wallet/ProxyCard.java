// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ProxyCard implements SafeParcelable
{
    public static final Parcelable$Creator<ProxyCard> CREATOR;
    String GY;
    String GZ;
    int Ha;
    int Hb;
    private final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new l();
    }
    
    ProxyCard(final int kg, final String gy, final String gz, final int ha, final int hb) {
        this.kg = kg;
        this.GY = gy;
        this.GZ = gz;
        this.Ha = ha;
        this.Hb = hb;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getCvn() {
        return this.GZ;
    }
    
    public int getExpirationMonth() {
        return this.Ha;
    }
    
    public int getExpirationYear() {
        return this.Hb;
    }
    
    public String getPan() {
        return this.GY;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        l.a(this, parcel, n);
    }
}
