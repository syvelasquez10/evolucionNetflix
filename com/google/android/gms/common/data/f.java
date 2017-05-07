// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a$a;
import android.database.CursorWindow;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class f implements Parcelable$Creator<DataHolder>
{
    static void a(final DataHolder dataHolder, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, dataHolder.gC(), false);
        b.c(parcel, 1000, dataHolder.getVersionCode());
        b.a(parcel, 2, dataHolder.gD(), n, false);
        b.c(parcel, 3, dataHolder.getStatusCode());
        b.a(parcel, 4, dataHolder.gz(), false);
        b.H(parcel, d);
    }
    
    public DataHolder[] at(final int n) {
        return new DataHolder[n];
    }
    
    public DataHolder z(final Parcel parcel) {
        int g = 0;
        Bundle q = null;
        final int c = a.C(parcel);
        CursorWindow[] array = null;
        String[] a = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, b);
                    continue;
                }
                case 1000: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    array = com.google.android.gms.common.internal.safeparcel.a.b(parcel, b, (android.os.Parcelable$Creator<CursorWindow>)CursorWindow.CREATOR);
                    continue;
                }
                case 3: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 4: {
                    q = com.google.android.gms.common.internal.safeparcel.a.q(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        final DataHolder dataHolder = new DataHolder(g2, a, array, g, q);
        dataHolder.gB();
        return dataHolder;
    }
}
