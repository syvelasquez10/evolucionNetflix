// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class x implements SafeParcelable
{
    public static final y CREATOR;
    public final boolean lX;
    public final boolean mh;
    public final int versionCode;
    
    static {
        CREATOR = new y();
    }
    
    x(final int versionCode, final boolean lx, final boolean mh) {
        this.versionCode = versionCode;
        this.lX = lx;
        this.mh = mh;
    }
    
    public x(final boolean lx, final boolean mh) {
        this.versionCode = 1;
        this.lX = lx;
        this.mh = mh;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        y.a(this, parcel, n);
    }
}
