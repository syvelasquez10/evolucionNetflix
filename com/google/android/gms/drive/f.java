// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class f implements Parcelable$Creator<RealtimeDocumentSyncRequest>
{
    static void a(final RealtimeDocumentSyncRequest realtimeDocumentSyncRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, realtimeDocumentSyncRequest.BR);
        b.b(parcel, 2, realtimeDocumentSyncRequest.Nr, false);
        b.b(parcel, 3, realtimeDocumentSyncRequest.Ns, false);
        b.H(parcel, d);
    }
    
    public RealtimeDocumentSyncRequest Q(final Parcel parcel) {
        List<String> c = null;
        final int c2 = a.C(parcel);
        int g = 0;
        List<String> c3 = null;
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
                    c3 = a.C(parcel, b);
                    continue;
                }
                case 3: {
                    c = a.C(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new RealtimeDocumentSyncRequest(g, c3, c);
    }
    
    public RealtimeDocumentSyncRequest[] aX(final int n) {
        return new RealtimeDocumentSyncRequest[n];
    }
}
