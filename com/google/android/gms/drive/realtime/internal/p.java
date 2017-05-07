// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class p implements Parcelable$Creator<ParcelableCollaborator>
{
    static void a(final ParcelableCollaborator parcelableCollaborator, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, parcelableCollaborator.BR);
        b.a(parcel, 2, parcelableCollaborator.Rc);
        b.a(parcel, 3, parcelableCollaborator.Rd);
        b.a(parcel, 4, parcelableCollaborator.vL, false);
        b.a(parcel, 5, parcelableCollaborator.Re, false);
        b.a(parcel, 6, parcelableCollaborator.Nz, false);
        b.a(parcel, 7, parcelableCollaborator.Rf, false);
        b.a(parcel, 8, parcelableCollaborator.Rg, false);
        b.H(parcel, d);
    }
    
    public ParcelableCollaborator aW(final Parcel parcel) {
        boolean c = false;
        String o = null;
        final int c2 = a.C(parcel);
        String o2 = null;
        String o3 = null;
        String o4 = null;
        String o5 = null;
        boolean c3 = false;
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
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 4: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 8: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new ParcelableCollaborator(g, c3, c, o5, o4, o3, o2, o);
    }
    
    public ParcelableCollaborator[] cj(final int n) {
        return new ParcelableCollaborator[n];
    }
}
