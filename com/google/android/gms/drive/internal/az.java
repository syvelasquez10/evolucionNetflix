// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.DrivePreferences;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class az implements Parcelable$Creator<SetDrivePreferencesRequest>
{
    static void a(final SetDrivePreferencesRequest setDrivePreferencesRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, setDrivePreferencesRequest.BR);
        b.a(parcel, 2, (Parcelable)setDrivePreferencesRequest.Pj, n, false);
        b.H(parcel, d);
    }
    
    public SetDrivePreferencesRequest aA(final Parcel parcel) {
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
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new SetDrivePreferencesRequest(g, drivePreferences);
    }
    
    public SetDrivePreferencesRequest[] bM(final int n) {
        return new SetDrivePreferencesRequest[n];
    }
}
