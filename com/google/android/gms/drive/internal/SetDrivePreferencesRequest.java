// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DrivePreferences;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SetDrivePreferencesRequest implements SafeParcelable
{
    public static final Parcelable$Creator<SetDrivePreferencesRequest> CREATOR;
    final int BR;
    final DrivePreferences Pj;
    
    static {
        CREATOR = (Parcelable$Creator)new az();
    }
    
    SetDrivePreferencesRequest(final int br, final DrivePreferences pj) {
        this.BR = br;
        this.Pj = pj;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        az.a(this, parcel, n);
    }
}
