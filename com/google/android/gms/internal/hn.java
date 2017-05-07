// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hn implements Parcelable$Creator<hm$a>
{
    static void a(final hm$a hm$a, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)hm$a.Cj, n, false);
        b.c(parcel, 1000, hm$a.BR);
        b.H(parcel, d);
    }
    
    public hm$a[] M(final int n) {
        return new hm$a[n];
    }
    
    public hm$a p(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        Account account = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    account = a.a(parcel, b, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new hm$a(g, account);
    }
}
