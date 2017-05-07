// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class dj implements SafeParcelable
{
    public static final di CREATOR;
    public final String mimeType;
    public final String packageName;
    public final String rp;
    public final String rq;
    public final String rr;
    public final String rs;
    public final String rt;
    public final int versionCode;
    
    static {
        CREATOR = new di();
    }
    
    public dj(final int versionCode, final String rp, final String rq, final String mimeType, final String packageName, final String rr, final String rs, final String rt) {
        this.versionCode = versionCode;
        this.rp = rp;
        this.rq = rq;
        this.mimeType = mimeType;
        this.packageName = packageName;
        this.rr = rr;
        this.rs = rs;
        this.rt = rt;
    }
    
    public dj(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        this(1, s, s2, s3, s4, s5, s6, s7);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        di.a(this, parcel, n);
    }
}
