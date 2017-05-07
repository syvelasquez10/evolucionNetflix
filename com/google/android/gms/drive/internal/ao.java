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

public class ao implements Parcelable$Creator<OnListParentsResponse>
{
    static void a(final OnListParentsResponse onListParentsResponse, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, onListParentsResponse.BR);
        b.a(parcel, 2, (Parcelable)onListParentsResponse.Pn, n, false);
        b.H(parcel, d);
    }
    
    public OnListParentsResponse aq(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        DataHolder dataHolder = null;
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
                    dataHolder = a.a(parcel, b, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new OnListParentsResponse(g, dataHolder);
    }
    
    public OnListParentsResponse[] bC(final int n) {
        return new OnListParentsResponse[n];
    }
}
