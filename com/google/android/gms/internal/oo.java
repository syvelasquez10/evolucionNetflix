// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class oo implements SafeParcelable
{
    public static final Parcelable$Creator<oo> CREATOR;
    private final int BR;
    String[] atD;
    byte[][] atE;
    
    static {
        CREATOR = (Parcelable$Creator)new op();
    }
    
    oo() {
        this(1, new String[0], new byte[0][]);
    }
    
    oo(final int br, final String[] atD, final byte[][] atE) {
        this.BR = br;
        this.atD = atD;
        this.atE = atE;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        op.a(this, parcel, n);
    }
}
