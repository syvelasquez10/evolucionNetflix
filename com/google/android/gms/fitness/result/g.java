// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<SessionStopResult>
{
    static void a(final SessionStopResult sessionStopResult, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1000, sessionStopResult.getVersionCode());
        b.a(parcel, 2, (Parcelable)sessionStopResult.getStatus(), n, false);
        b.c(parcel, 3, sessionStopResult.getSessions(), false);
        b.H(parcel, d);
    }
    
    public SessionStopResult cb(final Parcel parcel) {
        List<Session> c = null;
        final int c2 = a.C(parcel);
        int g = 0;
        Status status = null;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    status = a.a(parcel, b, (android.os.Parcelable$Creator<Status>)Status.CREATOR);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b, Session.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new SessionStopResult(g, status, c);
    }
    
    public SessionStopResult[] dt(final int n) {
        return new SessionStopResult[n];
    }
}
