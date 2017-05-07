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

public class x implements Parcelable$Creator<OpenFileIntentSenderRequest>
{
    static void a(final OpenFileIntentSenderRequest openFileIntentSenderRequest, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, openFileIntentSenderRequest.kg);
        b.a(parcel, 2, openFileIntentSenderRequest.qL, false);
        b.a(parcel, 3, openFileIntentSenderRequest.qW, false);
        b.a(parcel, 4, (Parcelable)openFileIntentSenderRequest.qM, n, false);
        b.D(parcel, o);
    }
    
    public OpenFileIntentSenderRequest M(final Parcel parcel) {
        DriveId driveId = null;
        final int n = a.n(parcel);
        int g = 0;
        String[] x = null;
        String m = null;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 3: {
                    x = a.x(parcel, i);
                    continue;
                }
                case 4: {
                    driveId = a.a(parcel, i, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new OpenFileIntentSenderRequest(g, m, x, driveId);
    }
    
    public OpenFileIntentSenderRequest[] am(final int n) {
        return new OpenFileIntentSenderRequest[n];
    }
}
