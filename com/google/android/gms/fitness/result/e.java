// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import java.util.List;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<ListSubscriptionsResult>
{
    static void a(final ListSubscriptionsResult listSubscriptionsResult, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, listSubscriptionsResult.getSubscriptions(), false);
        b.c(parcel, 1000, listSubscriptionsResult.getVersionCode());
        b.a(parcel, 2, (Parcelable)listSubscriptionsResult.getStatus(), n, false);
        b.H(parcel, d);
    }
    
    public ListSubscriptionsResult bZ(final Parcel parcel) {
        Status status = null;
        final int c = a.C(parcel);
        int g = 0;
        List<Subscription> c2 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c2 = a.c(parcel, b, Subscription.CREATOR);
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
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ListSubscriptionsResult(g, c2, status);
    }
    
    public ListSubscriptionsResult[] dr(final int n) {
        return new ListSubscriptionsResult[n];
    }
}
