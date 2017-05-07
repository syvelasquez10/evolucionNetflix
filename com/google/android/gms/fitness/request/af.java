// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class af implements Parcelable$Creator<ae>
{
    static void a(final ae ae, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)ae.jB(), n, false);
        b.c(parcel, 1000, ae.getVersionCode());
        b.a(parcel, 2, ae.jC());
        b.H(parcel, d);
    }
    
    public ae bS(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        Subscription subscription = null;
        int g = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    subscription = a.a(parcel, b, Subscription.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new ae(g, subscription, c);
    }
    
    public ae[] dk(final int n) {
        return new ae[n];
    }
}
