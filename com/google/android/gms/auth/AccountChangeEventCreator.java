// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class AccountChangeEventCreator implements Parcelable$Creator<AccountChangeEvent>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final AccountChangeEvent accountChangeEvent, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, accountChangeEvent.Di);
        b.a(parcel, 2, accountChangeEvent.Dj);
        b.a(parcel, 3, accountChangeEvent.Dd, false);
        b.c(parcel, 4, accountChangeEvent.Dk);
        b.c(parcel, 5, accountChangeEvent.Dl);
        b.a(parcel, 6, accountChangeEvent.Dm, false);
        b.H(parcel, d);
    }
    
    public AccountChangeEvent createFromParcel(final Parcel parcel) {
        String o = null;
        int g = 0;
        final int c = a.C(parcel);
        long i = 0L;
        int g2 = 0;
        String o2 = null;
        int g3 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new AccountChangeEvent(g3, i, o2, g2, g, o);
    }
    
    public AccountChangeEvent[] newArray(final int n) {
        return new AccountChangeEvent[n];
    }
}
