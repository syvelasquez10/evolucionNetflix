// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.io.Serializable;
import android.os.Parcelable$Creator;
import com.google.android.gms.wearable.e;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ki implements SafeParcelable, e
{
    public static final Parcelable$Creator<ki> CREATOR;
    private final int Eu;
    private final byte[] Nf;
    private final String adF;
    private final String adG;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new kj();
    }
    
    ki(final int xh, final int eu, final String adF, final byte[] nf, final String adG) {
        this.xH = xh;
        this.Eu = eu;
        this.adF = adF;
        this.Nf = nf;
        this.adG = adG;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int fA() {
        return this.Eu;
    }
    
    public byte[] getData() {
        return this.Nf;
    }
    
    public String getPath() {
        return this.adF;
    }
    
    public String getSource() {
        return this.adG;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("MessageEventParcelable[").append(this.Eu).append(",").append(this.adF);
        Serializable value;
        if (this.Nf == null) {
            value = "null";
        }
        else {
            value = this.Nf.length;
        }
        return append.append(value).append("]").toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        kj.a(this, parcel, n);
    }
}
