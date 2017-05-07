// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class f implements Parcelable$Creator<FullWallet>
{
    static void a(final FullWallet fullWallet, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, fullWallet.getVersionCode());
        b.a(parcel, 2, fullWallet.asq, false);
        b.a(parcel, 3, fullWallet.asr, false);
        b.a(parcel, 4, (Parcelable)fullWallet.ass, n, false);
        b.a(parcel, 5, fullWallet.ast, false);
        b.a(parcel, 6, (Parcelable)fullWallet.asu, n, false);
        b.a(parcel, 7, (Parcelable)fullWallet.asv, n, false);
        b.a(parcel, 8, fullWallet.asw, false);
        b.a(parcel, 9, (Parcelable)fullWallet.asx, n, false);
        b.a(parcel, 10, (Parcelable)fullWallet.asy, n, false);
        b.a(parcel, 11, fullWallet.asz, n, false);
        b.H(parcel, d);
    }
    
    public FullWallet dr(final Parcel parcel) {
        InstrumentInfo[] array = null;
        final int c = a.C(parcel);
        int g = 0;
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        String[] a = null;
        Address address = null;
        Address address2 = null;
        String o = null;
        ProxyCard proxyCard = null;
        String o2 = null;
        String o3 = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 4: {
                    proxyCard = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, ProxyCard.CREATOR);
                    continue;
                }
                case 5: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 6: {
                    address2 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, Address.CREATOR);
                    continue;
                }
                case 7: {
                    address = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, Address.CREATOR);
                    continue;
                }
                case 8: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, b);
                    continue;
                }
                case 9: {
                    userAddress2 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, UserAddress.CREATOR);
                    continue;
                }
                case 10: {
                    userAddress = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, UserAddress.CREATOR);
                    continue;
                }
                case 11: {
                    array = com.google.android.gms.common.internal.safeparcel.a.b(parcel, b, InstrumentInfo.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new FullWallet(g, o3, o2, proxyCard, o, address2, address, a, userAddress2, userAddress, array);
    }
    
    public FullWallet[] fr(final int n) {
        return new FullWallet[n];
    }
}
