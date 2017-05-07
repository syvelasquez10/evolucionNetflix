// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DrivePreferences;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnDrivePreferencesResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnDrivePreferencesResponse> CREATOR;
    final int BR;
    DrivePreferences Pj;
    
    static {
        CREATOR = (Parcelable$Creator)new al();
    }
    
    OnDrivePreferencesResponse(final int br, final DrivePreferences pj) {
        this.BR = br;
        this.Pj = pj;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        al.a(this, parcel, n);
    }
}
