// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.Bundle;
import android.database.CursorWindow;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class DataHolderCreator implements Parcelable$Creator<DataHolder>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final DataHolder dataHolder, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, dataHolder.bv(), false);
        b.c(parcel, 1000, dataHolder.getVersionCode());
        b.a(parcel, 2, dataHolder.bw(), n, false);
        b.c(parcel, 3, dataHolder.getStatusCode());
        b.a(parcel, 4, dataHolder.getMetadata(), false);
        b.D(parcel, o);
    }
    
    public DataHolder createFromParcel(final Parcel parcel) {
        int g = 0;
        Bundle o = null;
        final int n = a.n(parcel);
        CursorWindow[] array = null;
        String[] x = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    x = a.x(parcel, m);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    array = a.b(parcel, m, (android.os.Parcelable$Creator<CursorWindow>)CursorWindow.CREATOR);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 4: {
                    o = a.o(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        final DataHolder dataHolder = new DataHolder(g2, x, array, g, o);
        dataHolder.validateContents();
        return dataHolder;
    }
    
    public DataHolder[] newArray(final int n) {
        return new DataHolder[n];
    }
}
