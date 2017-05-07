// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class m implements Parcelable$Creator<NotifyTransactionStatusRequest>
{
    static void a(final NotifyTransactionStatusRequest notifyTransactionStatusRequest, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, notifyTransactionStatusRequest.xH);
        b.a(parcel, 2, notifyTransactionStatusRequest.abh, false);
        b.c(parcel, 3, notifyTransactionStatusRequest.status);
        b.a(parcel, 4, notifyTransactionStatusRequest.ach, false);
        b.F(parcel, p3);
    }
    
    public NotifyTransactionStatusRequest bi(final Parcel parcel) {
        String n = null;
        int g = 0;
        final int o = a.o(parcel);
        String n2 = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 4: {
                    n = a.n(parcel, n3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new NotifyTransactionStatusRequest(g2, n2, g, n);
    }
    
    public NotifyTransactionStatusRequest[] cu(final int n) {
        return new NotifyTransactionStatusRequest[n];
    }
}
