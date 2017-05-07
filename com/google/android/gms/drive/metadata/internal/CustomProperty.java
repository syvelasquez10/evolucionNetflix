// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CustomProperty implements SafeParcelable
{
    public static final Parcelable$Creator<CustomProperty> CREATOR;
    final int BR;
    final CustomPropertyKey PB;
    final String mValue;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    CustomProperty(final int br, final CustomPropertyKey pb, final String mValue) {
        this.BR = br;
        n.b(pb, "key");
        this.PB = pb;
        this.mValue = mValue;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
