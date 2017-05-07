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

public class ak implements Parcelable$Creator<RemoveEventListenerRequest>
{
    static void a(final RemoveEventListenerRequest removeEventListenerRequest, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, removeEventListenerRequest.xH);
        b.a(parcel, 2, (Parcelable)removeEventListenerRequest.Ew, n, false);
        b.c(parcel, 3, removeEventListenerRequest.ES);
        b.F(parcel, p3);
    }
    
    public RemoveEventListenerRequest Y(final Parcel parcel) {
        int g = 0;
        final int o = a.o(parcel);
        DriveId driveId = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    driveId = a.a(parcel, n, DriveId.CREATOR);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new RemoveEventListenerRequest(g2, driveId, g);
    }
    
    public RemoveEventListenerRequest[] aC(final int n) {
        return new RemoveEventListenerRequest[n];
    }
}
