// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class t implements Parcelable$Creator<OnDriveIdResponse>
{
    static void a(final OnDriveIdResponse onDriveIdResponse, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, onDriveIdResponse.kg);
        b.a(parcel, 2, (Parcelable)onDriveIdResponse.rr, n, false);
        b.D(parcel, o);
    }
    
    public OnDriveIdResponse I(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        DriveId driveId = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    driveId = a.a(parcel, m, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new OnDriveIdResponse(g, driveId);
    }
    
    public OnDriveIdResponse[] ai(final int n) {
        return new OnDriveIdResponse[n];
    }
}
