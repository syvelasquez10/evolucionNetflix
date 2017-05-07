// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.events.ConflictEvent;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ac implements Parcelable$Creator<OnEventResponse>
{
    static void a(final OnEventResponse onEventResponse, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, onEventResponse.xH);
        b.c(parcel, 2, onEventResponse.ES);
        b.a(parcel, 3, (Parcelable)onEventResponse.FH, n, false);
        b.a(parcel, 4, (Parcelable)onEventResponse.FI, n, false);
        b.F(parcel, p3);
    }
    
    public OnEventResponse Q(final Parcel parcel) {
        ConflictEvent conflictEvent = null;
        int n = 0;
        final int o = a.o(parcel);
        ChangeEvent changeEvent = null;
        int n2 = 0;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            int n5 = 0;
            int n6 = 0;
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    final int n4 = n;
                    n5 = n2;
                    n6 = n4;
                    break;
                }
                case 1: {
                    final int g = a.g(parcel, n3);
                    n6 = n;
                    n5 = g;
                    break;
                }
                case 2: {
                    final int g2 = a.g(parcel, n3);
                    n5 = n2;
                    n6 = g2;
                    break;
                }
                case 3: {
                    changeEvent = a.a(parcel, n3, ChangeEvent.CREATOR);
                    final int n7 = n2;
                    n6 = n;
                    n5 = n7;
                    break;
                }
                case 4: {
                    conflictEvent = a.a(parcel, n3, ConflictEvent.CREATOR);
                    final int n8 = n2;
                    n6 = n;
                    n5 = n8;
                    break;
                }
            }
            final int n9 = n5;
            n = n6;
            n2 = n9;
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new OnEventResponse(n2, n, changeEvent, conflictEvent);
    }
    
    public OnEventResponse[] au(final int n) {
        return new OnEventResponse[n];
    }
}
