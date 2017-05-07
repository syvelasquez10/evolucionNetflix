// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class r implements Parcelable$Creator<SessionInsertRequest>
{
    static void a(final SessionInsertRequest sessionInsertRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)sessionInsertRequest.getSession(), n, false);
        b.c(parcel, 1000, sessionInsertRequest.getVersionCode());
        b.c(parcel, 2, sessionInsertRequest.getDataSets(), false);
        b.c(parcel, 3, sessionInsertRequest.js(), false);
        b.H(parcel, d);
    }
    
    public SessionInsertRequest bK(final Parcel parcel) {
        List<DataPoint> c = null;
        final int c2 = a.C(parcel);
        int g = 0;
        ArrayList<DataSet> list = null;
        Session session = null;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            Session session2 = null;
            ArrayList<DataSet> list3 = null;
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    final ArrayList<DataSet> list2 = list;
                    session2 = session;
                    list3 = list2;
                    break;
                }
                case 1: {
                    final Session session3 = a.a(parcel, b, Session.CREATOR);
                    list3 = list;
                    session2 = session3;
                    break;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    final Session session4 = session;
                    list3 = list;
                    session2 = session4;
                    break;
                }
                case 2: {
                    final ArrayList<DataSet> c3 = a.c(parcel, b, DataSet.CREATOR);
                    session2 = session;
                    list3 = c3;
                    break;
                }
                case 3: {
                    c = a.c(parcel, b, DataPoint.CREATOR);
                    final Session session5 = session;
                    list3 = list;
                    session2 = session5;
                    break;
                }
            }
            final Session session6 = session2;
            list = list3;
            session = session6;
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new SessionInsertRequest(g, session, list, c);
    }
    
    public SessionInsertRequest[] dc(final int n) {
        return new SessionInsertRequest[n];
    }
}
