// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class aw implements Parcelable$Creator<OpenFileIntentSenderRequest>
{
    static void a(final OpenFileIntentSenderRequest openFileIntentSenderRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, openFileIntentSenderRequest.BR);
        b.a(parcel, 2, openFileIntentSenderRequest.No, false);
        b.a(parcel, 3, openFileIntentSenderRequest.Np, false);
        b.a(parcel, 4, (Parcelable)openFileIntentSenderRequest.Nq, n, false);
        b.H(parcel, d);
    }
    
    public OpenFileIntentSenderRequest ax(final Parcel parcel) {
        DriveId driveId = null;
        final int c = a.C(parcel);
        int g = 0;
        String[] a = null;
        String o = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 3: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, b);
                    continue;
                }
                case 4: {
                    driveId = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new OpenFileIntentSenderRequest(g, o, a, driveId);
    }
    
    public OpenFileIntentSenderRequest[] bJ(final int n) {
        return new OpenFileIntentSenderRequest[n];
    }
}
