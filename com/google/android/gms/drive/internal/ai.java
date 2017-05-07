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

public class ai implements Parcelable$Creator<OpenFileIntentSenderRequest>
{
    static void a(final OpenFileIntentSenderRequest openFileIntentSenderRequest, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, openFileIntentSenderRequest.xH);
        b.a(parcel, 2, openFileIntentSenderRequest.EB, false);
        b.a(parcel, 3, openFileIntentSenderRequest.EQ, false);
        b.a(parcel, 4, (Parcelable)openFileIntentSenderRequest.EC, n, false);
        b.F(parcel, p3);
    }
    
    public OpenFileIntentSenderRequest W(final Parcel parcel) {
        DriveId driveId = null;
        final int o = a.o(parcel);
        int g = 0;
        String[] z = null;
        String n = null;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 3: {
                    z = a.z(parcel, n2);
                    continue;
                }
                case 4: {
                    driveId = a.a(parcel, n2, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new OpenFileIntentSenderRequest(g, n, z, driveId);
    }
    
    public OpenFileIntentSenderRequest[] aA(final int n) {
        return new OpenFileIntentSenderRequest[n];
    }
}
