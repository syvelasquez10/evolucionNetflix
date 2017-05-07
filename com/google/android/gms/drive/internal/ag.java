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

public class ag implements Parcelable$Creator<LoadRealtimeRequest>
{
    static void a(final LoadRealtimeRequest loadRealtimeRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, loadRealtimeRequest.BR);
        b.a(parcel, 2, (Parcelable)loadRealtimeRequest.MO, n, false);
        b.a(parcel, 3, loadRealtimeRequest.Pc);
        b.H(parcel, d);
    }
    
    public LoadRealtimeRequest aj(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        DriveId driveId = null;
        int g = 0;
        while (parcel.dataPosition() < c2) {
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
                    driveId = a.a(parcel, b, DriveId.CREATOR);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new LoadRealtimeRequest(g, driveId, c);
    }
    
    public LoadRealtimeRequest[] bv(final int n) {
        return new LoadRealtimeRequest[n];
    }
}
