// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hf implements Parcelable$Creator<he>
{
    static void a(final he he, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, he.BS, n, false);
        b.c(parcel, 1000, he.BR);
        b.a(parcel, 2, he.BT, false);
        b.a(parcel, 3, he.BU);
        b.a(parcel, 4, (Parcelable)he.account, n, false);
        b.H(parcel, d);
    }
    
    public he[] I(final int n) {
        return new he[n];
    }
    
    public he l(final Parcel parcel) {
        boolean c = false;
        Account account = null;
        final int c2 = a.C(parcel);
        String o = null;
        hi[] array = null;
        int g = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    array = a.b(parcel, b, (android.os.Parcelable$Creator<hi>)hi.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 4: {
                    account = a.a(parcel, b, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new he(g, array, o, c, account);
    }
}
