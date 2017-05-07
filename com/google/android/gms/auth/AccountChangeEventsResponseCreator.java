// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class AccountChangeEventsResponseCreator implements Parcelable$Creator<AccountChangeEventsResponse>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final AccountChangeEventsResponse accountChangeEventsResponse, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, accountChangeEventsResponse.Di);
        b.c(parcel, 2, accountChangeEventsResponse.me, false);
        b.H(parcel, d);
    }
    
    public AccountChangeEventsResponse createFromParcel(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        Object c2 = null;
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
                    c2 = a.c(parcel, b, (android.os.Parcelable$Creator<Object>)AccountChangeEvent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new AccountChangeEventsResponse(g, (List<AccountChangeEvent>)c2);
    }
    
    public AccountChangeEventsResponse[] newArray(final int n) {
        return new AccountChangeEventsResponse[n];
    }
}
