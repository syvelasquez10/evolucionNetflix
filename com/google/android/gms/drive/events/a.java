// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.drive.DriveId;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<ChangeEvent>
{
    static void a(final ChangeEvent changeEvent, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, changeEvent.xH);
        b.a(parcel, 2, (Parcelable)changeEvent.Ew, n, false);
        b.c(parcel, 3, changeEvent.ER);
        b.F(parcel, p3);
    }
    
    public ChangeEvent A(final Parcel parcel) {
        int g = 0;
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        DriveId driveId = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n);
                    continue;
                }
                case 2: {
                    driveId = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n, DriveId.CREATOR);
                    continue;
                }
                case 3: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        return new ChangeEvent(g2, driveId, g);
    }
    
    public ChangeEvent[] ae(final int n) {
        return new ChangeEvent[n];
    }
}
