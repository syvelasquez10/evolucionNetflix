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
        final int d = b.D(parcel);
        b.c(parcel, 1, changeEvent.BR);
        b.a(parcel, 2, (Parcelable)changeEvent.MO, n, false);
        b.c(parcel, 3, changeEvent.NE);
        b.H(parcel, d);
    }
    
    public ChangeEvent T(final Parcel parcel) {
        int g = 0;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        DriveId driveId = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    driveId = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, DriveId.CREATOR);
                    continue;
                }
                case 3: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c, parcel);
        }
        return new ChangeEvent(g2, driveId, g);
    }
    
    public ChangeEvent[] ba(final int n) {
        return new ChangeEvent[n];
    }
}
