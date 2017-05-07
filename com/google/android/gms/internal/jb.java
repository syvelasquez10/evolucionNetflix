// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class jb implements SafeParcelable
{
    public static final jc CREATOR;
    final int BR;
    public final String Mi;
    public final int Mj;
    
    static {
        CREATOR = new jc();
    }
    
    public jb(final int br, final String mi, final int mj) {
        this.BR = br;
        this.Mi = mi;
        this.Mj = mj;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        jc.a(this, parcel, n);
    }
}
