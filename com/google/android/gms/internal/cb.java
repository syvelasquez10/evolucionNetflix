// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class cb implements SafeParcelable
{
    public static final ca CREATOR;
    public final String mimeType;
    public final String nN;
    public final String nO;
    public final String nP;
    public final String nQ;
    public final String nR;
    public final String packageName;
    public final int versionCode;
    
    static {
        CREATOR = new ca();
    }
    
    public cb(final int versionCode, final String nn, final String no, final String mimeType, final String packageName, final String np, final String nq, final String nr) {
        this.versionCode = versionCode;
        this.nN = nn;
        this.nO = no;
        this.mimeType = mimeType;
        this.packageName = packageName;
        this.nP = np;
        this.nQ = nq;
        this.nR = nr;
    }
    
    public cb(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        this(1, s, s2, s3, s4, s5, s6, s7);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ca.a(this, parcel, n);
    }
}
