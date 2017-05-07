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

public class u implements Parcelable$Creator<OnListEntriesResponse>
{
    static void a(final OnListEntriesResponse onListEntriesResponse, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, onListEntriesResponse.kg);
        b.a(parcel, 2, (Parcelable)onListEntriesResponse.rz, n, false);
        b.D(parcel, o);
    }
    
    public OnListEntriesResponse J(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        DataHolder dataHolder = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    dataHolder = a.a(parcel, m, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new OnListEntriesResponse(g, dataHolder);
    }
    
    public OnListEntriesResponse[] aj(final int n) {
        return new OnListEntriesResponse[n];
    }
}
