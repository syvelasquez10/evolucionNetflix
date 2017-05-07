// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class om implements SafeParcelable
{
    public static final Parcelable$Creator<om> CREATOR;
    private final int BR;
    int[] atC;
    
    static {
        CREATOR = (Parcelable$Creator)new on();
    }
    
    om() {
        this(1, null);
    }
    
    om(final int br, final int[] atC) {
        this.BR = br;
        this.atC = atC;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        on.a(this, parcel, n);
    }
}
