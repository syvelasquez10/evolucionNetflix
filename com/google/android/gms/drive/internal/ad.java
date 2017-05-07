// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ad implements Parcelable$Creator<OnListEntriesResponse>
{
    static void a(final OnListEntriesResponse onListEntriesResponse, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, onListEntriesResponse.xH);
        b.a(parcel, 2, (Parcelable)onListEntriesResponse.FJ, n, false);
        b.a(parcel, 3, onListEntriesResponse.Fg);
        b.F(parcel, p3);
    }
    
    public OnListEntriesResponse R(final Parcel parcel) {
        boolean c = false;
        final int o = a.o(parcel);
        DataHolder dataHolder = null;
        int g = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    dataHolder = a.a(parcel, n, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new OnListEntriesResponse(g, dataHolder, c);
    }
    
    public OnListEntriesResponse[] av(final int n) {
        return new OnListEntriesResponse[n];
    }
}
