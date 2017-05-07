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
    private final int BR;
    String atu;
    String atv;
    int atw;
    int atx;
    
    static {
        CREATOR = (Parcelable$Creator)new o();
    }
    
    ProxyCard(final int br, final String atu, final String atv, final int atw, final int atx) {
        this.BR = br;
        this.atu = atu;
        this.atv = atv;
        this.atw = atw;
        this.atx = atx;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getCvn() {
        return this.atv;
    }
    
    public int getExpirationMonth() {
        return this.atw;
    }
    
    public int getExpirationYear() {
        return this.atx;
    }
    
    public String getPan() {
        return this.atu;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        o.a(this, parcel, n);
    }
}
