// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ix implements SafeParcelable
{
    public static final Parcelable$Creator<ix> CREATOR;
    String[] act;
    byte[][] acu;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new iy();
    }
    
    ix() {
        this(1, new String[0], new byte[0][]);
    }
    
    ix(final int xh, final String[] act, final byte[][] acu) {
        this.xH = xh;
        this.act = act;
        this.acu = acu;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        iy.a(this, parcel, n);
    }
}
