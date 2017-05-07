// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class an implements Parcelable$Creator<OnListEntriesResponse>
{
    static void a(final OnListEntriesResponse onListEntriesResponse, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, onListEntriesResponse.BR);
        b.a(parcel, 2, (Parcelable)onListEntriesResponse.Pm, n, false);
        b.a(parcel, 3, onListEntriesResponse.Or);
        b.H(parcel, d);
    }
    
    public OnListEntriesResponse ap(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        DataHolder dataHolder = null;
        int g = 0;
        while (parcel.dataPosition() < c2) {
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
                    dataHolder = a.a(parcel, b, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new OnListEntriesResponse(g, dataHolder, c);
    }
    
    public OnListEntriesResponse[] bB(final int n) {
        return new OnListEntriesResponse[n];
    }
}
