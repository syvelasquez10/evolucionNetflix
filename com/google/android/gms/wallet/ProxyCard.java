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
    String ack;
    String acl;
    int acm;
    int acn;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new o();
    }
    
    ProxyCard(final int xh, final String ack, final String acl, final int acm, final int acn) {
        this.xH = xh;
        this.ack = ack;
        this.acl = acl;
        this.acm = acm;
        this.acn = acn;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getCvn() {
        return this.acl;
    }
    
    public int getExpirationMonth() {
        return this.acm;
    }
    
    public int getExpirationYear() {
        return this.acn;
    }
    
    public String getPan() {
        return this.ack;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        o.a(this, parcel, n);
    }
}
