// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import java.util.List;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class GoogleAuthApiRequestCreator implements Parcelable$Creator<GoogleAuthApiRequest>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final GoogleAuthApiRequest googleAuthApiRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, googleAuthApiRequest.name, false);
        b.c(parcel, 1000, googleAuthApiRequest.versionCode);
        b.a(parcel, 2, googleAuthApiRequest.version, false);
        b.a(parcel, 3, googleAuthApiRequest.Dt, false);
        b.a(parcel, 4, googleAuthApiRequest.yR, false);
        b.a(parcel, 5, googleAuthApiRequest.Du, false);
        b.a(parcel, 6, googleAuthApiRequest.Dv, false);
        b.b(parcel, 7, googleAuthApiRequest.Dw, false);
        b.a(parcel, 8, googleAuthApiRequest.Dx, false);
        b.c(parcel, 9, googleAuthApiRequest.Dy);
        b.a(parcel, 10, googleAuthApiRequest.Dz, false);
        b.a(parcel, 11, googleAuthApiRequest.DA, false);
        b.a(parcel, 12, googleAuthApiRequest.DB);
        b.H(parcel, d);
    }
    
    public GoogleAuthApiRequest createFromParcel(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
        String o3 = null;
        String o4 = null;
        Bundle q = null;
        String o5 = null;
        List<String> c2 = null;
        String o6 = null;
        int g2 = 0;
        Bundle q2 = null;
        byte[] r = null;
        long i = 0L;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    q = a.q(parcel, b);
                    continue;
                }
                case 6: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    c2 = a.C(parcel, b);
                    continue;
                }
                case 8: {
                    o6 = a.o(parcel, b);
                    continue;
                }
                case 9: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 10: {
                    q2 = a.q(parcel, b);
                    continue;
                }
                case 11: {
                    r = a.r(parcel, b);
                    continue;
                }
                case 12: {
                    i = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new GoogleAuthApiRequest(g, o, o2, o3, o4, q, o5, c2, o6, g2, q2, r, i);
    }
    
    public GoogleAuthApiRequest[] newArray(final int n) {
        return new GoogleAuthApiRequest[n];
    }
}
