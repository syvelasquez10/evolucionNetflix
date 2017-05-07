// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a$a;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<b>
{
    static void a(final b b, final Parcel parcel, final int n) {
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, b.BR);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, b.pT(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, b.ava, n, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public b dS(final Parcel parcel) {
        IntentFilter[] array = null;
        final int c = a.C(parcel);
        int g = 0;
        IBinder p = null;
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
                case 2: {
                    p = a.p(parcel, b);
                    continue;
                }
                case 3: {
                    array = a.b(parcel, b, (android.os.Parcelable$Creator<IntentFilter>)IntentFilter.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new b(g, p, array);
    }
    
    public b[] fU(final int n) {
        return new b[n];
    }
}
