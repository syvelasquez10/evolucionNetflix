// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class cu implements SafeParcelable
{
    public static final cv CREATOR;
    public String iJ;
    public int iK;
    public int iL;
    public boolean iM;
    public final int versionCode;
    
    static {
        CREATOR = new cv();
    }
    
    public cu(final int n, final int n2, final boolean b) {
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
    
    cu(final int versionCode, final String ij, final int ik, final int il, final boolean im) {
        this.versionCode = versionCode;
        this.iJ = ij;
        this.iK = ik;
        this.iL = il;
        this.iM = im;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        cv.a(this, parcel, n);
    }
}
