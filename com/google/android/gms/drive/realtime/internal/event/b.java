// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal.event;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<ParcelableEvent>
{
    static void a(final ParcelableEvent parcelableEvent, final Parcel parcel, final int n) {
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, parcelableEvent.BR);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, parcelableEvent.vL, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, parcelableEvent.Re, false);
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 4, parcelableEvent.Rl, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, parcelableEvent.Rm);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, parcelableEvent.Rh, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 7, parcelableEvent.Rn, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 8, (Parcelable)parcelableEvent.Ro, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 9, (Parcelable)parcelableEvent.Rp, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 10, (Parcelable)parcelableEvent.Rq, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 11, (Parcelable)parcelableEvent.Rr, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 12, (Parcelable)parcelableEvent.Rs, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 13, (Parcelable)parcelableEvent.Rt, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 14, (Parcelable)parcelableEvent.Ru, n, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 15, (Parcelable)parcelableEvent.Rv, n, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public ParcelableEvent aZ(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
        List<String> c2 = null;
        boolean c3 = false;
        String o3 = null;
        String o4 = null;
        TextInsertedDetails textInsertedDetails = null;
        TextDeletedDetails textDeletedDetails = null;
        ValuesAddedDetails valuesAddedDetails = null;
        ValuesRemovedDetails valuesRemovedDetails = null;
        ValuesSetDetails valuesSetDetails = null;
        ValueChangedDetails valueChangedDetails = null;
        ReferenceShiftedDetails referenceShiftedDetails = null;
        ObjectChangedDetails objectChangedDetails = null;
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
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    c2 = a.C(parcel, b);
                    continue;
                }
                case 5: {
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 6: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 8: {
                    textInsertedDetails = a.a(parcel, b, TextInsertedDetails.CREATOR);
                    continue;
                }
                case 9: {
                    textDeletedDetails = a.a(parcel, b, TextDeletedDetails.CREATOR);
                    continue;
                }
                case 10: {
                    valuesAddedDetails = a.a(parcel, b, ValuesAddedDetails.CREATOR);
                    continue;
                }
                case 11: {
                    valuesRemovedDetails = a.a(parcel, b, ValuesRemovedDetails.CREATOR);
                    continue;
                }
                case 12: {
                    valuesSetDetails = a.a(parcel, b, ValuesSetDetails.CREATOR);
                    continue;
                }
                case 13: {
                    valueChangedDetails = a.a(parcel, b, ValueChangedDetails.CREATOR);
                    continue;
                }
                case 14: {
                    referenceShiftedDetails = a.a(parcel, b, ReferenceShiftedDetails.CREATOR);
                    continue;
                }
                case 15: {
                    objectChangedDetails = a.a(parcel, b, ObjectChangedDetails.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ParcelableEvent(g, o, o2, c2, c3, o3, o4, textInsertedDetails, textDeletedDetails, valuesAddedDetails, valuesRemovedDetails, valuesSetDetails, valueChangedDetails, referenceShiftedDetails, objectChangedDetails);
    }
    
    public ParcelableEvent[] cm(final int n) {
        return new ParcelableEvent[n];
    }
}
