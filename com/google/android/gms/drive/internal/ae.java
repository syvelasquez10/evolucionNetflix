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

public class ae implements Parcelable$Creator<OnListParentsResponse>
{
    static void a(final OnListParentsResponse onListParentsResponse, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, onListParentsResponse.xH);
        b.a(parcel, 2, (Parcelable)onListParentsResponse.FK, n, false);
        b.F(parcel, p3);
    }
    
    public OnListParentsResponse S(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        DataHolder dataHolder = null;
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
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new OnListParentsResponse(g, dataHolder);
    }
    
    public OnListParentsResponse[] aw(final int n) {
        return new OnListParentsResponse[n];
    }
}
