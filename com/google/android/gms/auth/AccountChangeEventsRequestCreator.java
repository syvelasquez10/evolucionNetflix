// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class AccountChangeEventsRequestCreator implements Parcelable$Creator<AccountChangeEventsRequest>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final AccountChangeEventsRequest accountChangeEventsRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, accountChangeEventsRequest.Di);
        b.c(parcel, 2, accountChangeEventsRequest.Dl);
        b.a(parcel, 3, accountChangeEventsRequest.Dd, false);
        b.H(parcel, d);
    }
    
    public AccountChangeEventsRequest createFromParcel(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        String o = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new AccountChangeEventsRequest(g2, g, o);
    }
    
    public AccountChangeEventsRequest[] newArray(final int n) {
        return new AccountChangeEventsRequest[n];
    }
}
