// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<ConflictEvent>
{
    static void a(final ConflictEvent conflictEvent, final Parcel parcel, final int n) {
        final int p3 = com.google.android.gms.common.internal.safeparcel.b.p(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, conflictEvent.xH);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable)conflictEvent.Ew, n, false);
        com.google.android.gms.common.internal.safeparcel.b.F(parcel, p3);
    }
    
    public ConflictEvent B(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        DriveId driveId = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    driveId = a.a(parcel, n, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ConflictEvent(g, driveId);
    }
    
    public ConflictEvent[] af(final int n) {
        return new ConflictEvent[n];
    }
}
