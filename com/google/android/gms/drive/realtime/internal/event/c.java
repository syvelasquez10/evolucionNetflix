// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import java.util.List;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<ParcelableEventList>
{
    static void a(final ParcelableEventList list, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, list.BR);
        b.c(parcel, 2, list.me, false);
        b.a(parcel, 3, (Parcelable)list.Rw, n, false);
        b.a(parcel, 4, list.Rx);
        b.b(parcel, 5, list.Ry, false);
        b.H(parcel, d);
    }
    
    public ParcelableEventList ba(final Parcel parcel) {
        boolean c = false;
        List<String> c2 = null;
        final int c3 = a.C(parcel);
        DataHolder dataHolder = null;
        List<ParcelableEvent> c4 = null;
        int g = 0;
        while (parcel.dataPosition() < c3) {
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
                    c4 = a.c(parcel, b, ParcelableEvent.CREATOR);
                    continue;
                }
                case 3: {
                    dataHolder = a.a(parcel, b, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR);
                    continue;
                }
                case 4: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 5: {
                    c2 = a.C(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c3) {
            throw new a.a("Overread allowed size end=" + c3, parcel);
        }
        return new ParcelableEventList(g, c4, dataHolder, c, c2);
    }
    
    public ParcelableEventList[] cn(final int n) {
        return new ParcelableEventList[n];
    }
}
