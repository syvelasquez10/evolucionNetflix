// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jf$a implements SafeParcelable
{
    public static final jh CREATOR;
    final String Mo;
    final int Mp;
    final int versionCode;
    
    static {
        CREATOR = new jh();
    }
    
    jf$a(final int versionCode, final String mo, final int mp) {
        this.versionCode = versionCode;
        this.Mo = mo;
        this.Mp = mp;
    }
    
    jf$a(final String mo, final int mp) {
        this.versionCode = 1;
        this.Mo = mo;
        this.Mp = mp;
    }
    
    public int describeContents() {
        final jh creator = jf$a.CREATOR;
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final jh creator = jf$a.CREATOR;
        jh.a(this, parcel, n);
    }
}
