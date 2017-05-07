// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class am implements Parcelable$Creator<OnEventResponse>
{
    static void a(final OnEventResponse onEventResponse, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, onEventResponse.BR);
        b.c(parcel, 2, onEventResponse.NS);
        b.a(parcel, 3, (Parcelable)onEventResponse.Pk, n, false);
        b.a(parcel, 5, (Parcelable)onEventResponse.Pl, n, false);
        b.H(parcel, d);
    }
    
    public OnEventResponse ao(final Parcel parcel) {
        CompletionEvent completionEvent = null;
        int n = 0;
        final int c = a.C(parcel);
        ChangeEvent changeEvent = null;
        int n2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            int n4 = 0;
            int n5 = 0;
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    final int n3 = n;
                    n4 = n2;
                    n5 = n3;
                    break;
                }
                case 1: {
                    final int g = a.g(parcel, b);
                    n5 = n;
                    n4 = g;
                    break;
                }
                case 2: {
                    final int g2 = a.g(parcel, b);
                    n4 = n2;
                    n5 = g2;
                    break;
                }
                case 3: {
                    changeEvent = a.a(parcel, b, ChangeEvent.CREATOR);
                    final int n6 = n2;
                    n5 = n;
                    n4 = n6;
                    break;
                }
                case 5: {
                    completionEvent = a.a(parcel, b, CompletionEvent.CREATOR);
                    final int n7 = n2;
                    n5 = n;
                    n4 = n7;
                    break;
                }
            }
            final int n8 = n4;
            n = n5;
            n2 = n8;
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new OnEventResponse(n2, n, changeEvent, completionEvent);
    }
    
    public OnEventResponse[] bA(final int n) {
        return new OnEventResponse[n];
    }
}
