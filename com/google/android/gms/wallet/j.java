// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class j implements Parcelable$Creator<NotifyTransactionStatusRequest>
{
    static void a(final NotifyTransactionStatusRequest notifyTransactionStatusRequest, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, notifyTransactionStatusRequest.kg);
        b.a(parcel, 2, notifyTransactionStatusRequest.Gn, false);
        b.c(parcel, 3, notifyTransactionStatusRequest.status);
        b.a(parcel, 4, notifyTransactionStatusRequest.GV, false);
        b.D(parcel, o);
    }
    
    public NotifyTransactionStatusRequest aN(final Parcel parcel) {
        String m = null;
        int g = 0;
        final int n = a.n(parcel);
        String i = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int j = a.m(parcel);
            switch (a.M(j)) {
                default: {
                    a.b(parcel, j);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, j);
                    continue;
                }
                case 2: {
                    i = a.m(parcel, j);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, j);
                    continue;
                }
                case 4: {
                    m = a.m(parcel, j);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new NotifyTransactionStatusRequest(g2, i, g, m);
    }
    
    public NotifyTransactionStatusRequest[] bF(final int n) {
        return new NotifyTransactionStatusRequest[n];
    }
}
