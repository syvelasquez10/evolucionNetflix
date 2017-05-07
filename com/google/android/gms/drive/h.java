// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class h implements Parcelable$Creator<UserMetadata>
{
    static void a(final UserMetadata userMetadata, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, userMetadata.BR);
        b.a(parcel, 2, userMetadata.Ny, false);
        b.a(parcel, 3, userMetadata.Nz, false);
        b.a(parcel, 4, userMetadata.NA, false);
        b.a(parcel, 5, userMetadata.NB);
        b.a(parcel, 6, userMetadata.NC, false);
        b.H(parcel, d);
    }
    
    public UserMetadata S(final Parcel parcel) {
        boolean c = false;
        String o = null;
        final int c2 = a.C(parcel);
        String o2 = null;
        String o3 = null;
        String o4 = null;
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
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 6: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new UserMetadata(g, o4, o3, o2, c, o);
    }
    
    public UserMetadata[] aZ(final int n) {
        return new UserMetadata[n];
    }
}
