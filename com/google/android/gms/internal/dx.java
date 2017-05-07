// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class dx implements SafeParcelable
{
    public static final dy CREATOR;
    public String rq;
    public int rr;
    public int rs;
    public boolean rt;
    public final int versionCode;
    
    static {
        CREATOR = new dy();
    }
    
    public dx(final int n, final int n2, final boolean b) {
        final StringBuilder append = new StringBuilder().append("afma-sdk-a-v").append(n).append(".").append(n2).append(".");
        String s;
        if (b) {
            s = "0";
        }
        else {
            s = "1";
        }
        this(1, append.append(s).toString(), n, n2, b);
    }
    
    dx(final int versionCode, final String rq, final int rr, final int rs, final boolean rt) {
        this.versionCode = versionCode;
        this.rq = rq;
        this.rr = rr;
        this.rs = rs;
        this.rt = rt;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        dy.a(this, parcel, n);
    }
}
