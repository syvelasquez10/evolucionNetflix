// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ba implements Parcelable$Creator<SetResourceParentsRequest>
{
    static void a(final SetResourceParentsRequest setResourceParentsRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, setResourceParentsRequest.BR);
        b.a(parcel, 2, (Parcelable)setResourceParentsRequest.Pr, n, false);
        b.c(parcel, 3, setResourceParentsRequest.Ps, false);
        b.H(parcel, d);
    }
    
    public SetResourceParentsRequest aB(final Parcel parcel) {
        List<DriveId> c = null;
        final int c2 = a.C(parcel);
        int g = 0;
        DriveId driveId = null;
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
                    c = a.c(parcel, b, DriveId.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new SetResourceParentsRequest(g, driveId, c);
    }
    
    public SetResourceParentsRequest[] bN(final int n) {
        return new SetResourceParentsRequest[n];
    }
}
