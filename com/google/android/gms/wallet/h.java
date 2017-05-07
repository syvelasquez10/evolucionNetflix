// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class h implements Parcelable$Creator<InstrumentInfo>
{
    static void a(final InstrumentInfo instrumentInfo, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, instrumentInfo.getVersionCode());
        b.a(parcel, 2, instrumentInfo.getInstrumentType(), false);
        b.a(parcel, 3, instrumentInfo.getInstrumentDetails(), false);
        b.H(parcel, d);
    }
    
    public InstrumentInfo dt(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        String o2 = null;
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
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new InstrumentInfo(g, o2, o);
    }
    
    public InstrumentInfo[] ft(final int n) {
        return new InstrumentInfo[n];
    }
}
