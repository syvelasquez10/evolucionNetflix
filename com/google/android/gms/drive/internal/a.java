// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.app.PendingIntent;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<AddEventListenerRequest>
{
    static void a(final AddEventListenerRequest addEventListenerRequest, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, addEventListenerRequest.xH);
        b.a(parcel, 2, (Parcelable)addEventListenerRequest.Ew, n, false);
        b.c(parcel, 3, addEventListenerRequest.ES);
        b.a(parcel, 4, (Parcelable)addEventListenerRequest.ET, n, false);
        b.F(parcel, p3);
    }
    
    public AddEventListenerRequest C(final Parcel parcel) {
        PendingIntent pendingIntent = null;
        int n = 0;
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        DriveId driveId = null;
        int n2 = 0;
        while (parcel.dataPosition() < o) {
            final int n3 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            int n5 = 0;
            int n6 = 0;
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n3)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n3);
                    final int n4 = n;
                    n5 = n2;
                    n6 = n4;
                    break;
                }
                case 1: {
                    final int g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n3);
                    n6 = n;
                    n5 = g;
                    break;
                }
                case 2: {
                    driveId = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n3, DriveId.CREATOR);
                    final int n7 = n2;
                    n6 = n;
                    n5 = n7;
                    break;
                }
                case 3: {
                    final int g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n3);
                    n5 = n2;
                    n6 = g2;
                    break;
                }
                case 4: {
                    pendingIntent = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n3, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    final int n8 = n2;
                    n6 = n;
                    n5 = n8;
                    break;
                }
            }
            final int n9 = n5;
            n = n6;
            n2 = n9;
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        return new AddEventListenerRequest(n2, driveId, n, pendingIntent);
    }
    
    public AddEventListenerRequest[] ag(final int n) {
        return new AddEventListenerRequest[n];
    }
}
