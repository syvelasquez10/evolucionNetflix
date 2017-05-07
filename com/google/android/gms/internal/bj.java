// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class bj implements SafeParcelable
{
    public static final bi CREATOR;
    public final String gn;
    public final String go;
    public final String gp;
    public final String gq;
    public final String gr;
    public final String mimeType;
    public final String packageName;
    public final int versionCode;
    
    static {
        CREATOR = new bi();
    }
    
    public bj(final int versionCode, final String gn, final String go, final String mimeType, final String packageName, final String gp, final String gq, final String gr) {
        this.versionCode = versionCode;
        this.gn = gn;
        this.go = go;
        this.mimeType = mimeType;
        this.packageName = packageName;
        this.gp = gp;
        this.gq = gq;
        this.gr = gr;
    }
    
    public bj(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        this(1, s, s2, s3, s4, s5, s6, s7);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        bi.a(this, parcel, n);
    }
}
