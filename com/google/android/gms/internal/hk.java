// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hk implements SafeParcelable
{
    public static final hl CREATOR;
    final int BR;
    final Bundle Ci;
    public final int id;
    
    static {
        CREATOR = new hl();
    }
    
    hk(final int br, final int id, final Bundle ci) {
        this.BR = br;
        this.id = id;
        this.Ci = ci;
    }
    
    public int describeContents() {
        final hl creator = hk.CREATOR;
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hl creator = hk.CREATOR;
        hl.a(this, parcel, n);
    }
}
