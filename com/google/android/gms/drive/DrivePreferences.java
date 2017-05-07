// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DrivePreferences implements SafeParcelable
{
    public static final Parcelable$Creator<DrivePreferences> CREATOR;
    final int BR;
    final boolean Ne;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    DrivePreferences(final int br, final boolean ne) {
        this.BR = br;
        this.Ne = ne;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
