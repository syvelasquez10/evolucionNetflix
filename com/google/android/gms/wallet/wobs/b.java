// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class b implements SafeParcelable
{
    public static final Parcelable$Creator<b> CREATOR;
    private final int BR;
    String label;
    String value;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    b() {
        this.BR = 1;
    }
    
    b(final int br, final String label, final String value) {
        this.BR = br;
        this.label = label;
        this.value = value;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
