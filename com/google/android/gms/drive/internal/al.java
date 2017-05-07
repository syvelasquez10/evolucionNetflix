// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DrivePreferences;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class al implements Parcelable$Creator<OnDrivePreferencesResponse>
{
    static void a(final OnDrivePreferencesResponse onDrivePreferencesResponse, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, onDrivePreferencesResponse.BR);
        b.a(parcel, 2, (Parcelable)onDrivePreferencesResponse.Pj, n, false);
        b.H(parcel, d);
    }
    
    public OnDrivePreferencesResponse an(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        DrivePreferences drivePreferences = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    drivePreferences = a.a(parcel, b, DrivePreferences.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new OnDrivePreferencesResponse(g, drivePreferences);
    }
    
    public OnDrivePreferencesResponse[] bz(final int n) {
        return new OnDrivePreferencesResponse[n];
    }
}
