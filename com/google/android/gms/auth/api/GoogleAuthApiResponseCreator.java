// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class GoogleAuthApiResponseCreator implements Parcelable$Creator<GoogleAuthApiResponse>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final GoogleAuthApiResponse googleAuthApiResponse, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, googleAuthApiResponse.responseCode);
        b.c(parcel, 1000, googleAuthApiResponse.versionCode);
        b.a(parcel, 2, googleAuthApiResponse.Dz, false);
        b.a(parcel, 3, googleAuthApiResponse.DA, false);
        b.H(parcel, d);
    }
    
    public GoogleAuthApiResponse createFromParcel(final Parcel parcel) {
        byte[] r = null;
        int g = 0;
        final int c = a.C(parcel);
        Bundle q = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
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
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    q = a.q(parcel, b);
                    continue;
                }
                case 3: {
                    r = a.r(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new GoogleAuthApiResponse(g2, g, q, r);
    }
    
    public GoogleAuthApiResponse[] newArray(final int n) {
        return new GoogleAuthApiResponse[n];
    }
}
