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
        final int p3 = b.p(parcel);
        b.a(parcel, 1, dataHolder.er(), false);
        b.c(parcel, 1000, dataHolder.getVersionCode());
        b.a(parcel, 2, dataHolder.es(), n, false);
        b.c(parcel, 3, dataHolder.getStatusCode());
        b.a(parcel, 4, dataHolder.getMetadata(), false);
        b.F(parcel, p3);
    }
    
    public DataHolder createFromParcel(final Parcel parcel) {
        int g = 0;
        Bundle p = null;
        final int o = a.o(parcel);
        CursorWindow[] array = null;
        String[] z = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    z = a.z(parcel, n);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    array = a.b(parcel, n, (android.os.Parcelable$Creator<CursorWindow>)CursorWindow.CREATOR);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 4: {
                    p = a.p(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        final DataHolder dataHolder = new DataHolder(g2, z, array, g, p);
        dataHolder.validateContents();
        return dataHolder;
    }
    
    public DataHolder[] newArray(final int n) {
        return new DataHolder[n];
    }
}
