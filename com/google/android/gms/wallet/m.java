// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class m implements Parcelable$Creator<NotifyTransactionStatusRequest>
{
    static void a(final NotifyTransactionStatusRequest notifyTransactionStatusRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, notifyTransactionStatusRequest.BR);
        b.a(parcel, 2, notifyTransactionStatusRequest.asq, false);
        b.c(parcel, 3, notifyTransactionStatusRequest.status);
        b.a(parcel, 4, notifyTransactionStatusRequest.atq, false);
        b.H(parcel, d);
    }
    
    public NotifyTransactionStatusRequest dy(final Parcel parcel) {
        String o = null;
        int g = 0;
        final int c = a.C(parcel);
        String o2 = null;
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
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new NotifyTransactionStatusRequest(g2, o2, g, o);
    }
    
    public NotifyTransactionStatusRequest[] fy(final int n) {
        return new NotifyTransactionStatusRequest[n];
    }
}
