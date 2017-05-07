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

public class k implements Parcelable$Creator<MaskedWallet>
{
    static void a(final MaskedWallet maskedWallet, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, maskedWallet.getVersionCode());
        b.a(parcel, 2, maskedWallet.asq, false);
        b.a(parcel, 3, maskedWallet.asr, false);
        b.a(parcel, 4, maskedWallet.asw, false);
        b.a(parcel, 5, maskedWallet.ast, false);
        b.a(parcel, 6, (Parcelable)maskedWallet.asu, n, false);
        b.a(parcel, 7, (Parcelable)maskedWallet.asv, n, false);
        b.a(parcel, 8, maskedWallet.atb, n, false);
        b.a(parcel, 9, maskedWallet.atc, n, false);
        b.a(parcel, 10, (Parcelable)maskedWallet.asx, n, false);
        b.a(parcel, 11, (Parcelable)maskedWallet.asy, n, false);
        b.a(parcel, 12, maskedWallet.asz, n, false);
        b.H(parcel, d);
    }
    
    public MaskedWallet dw(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
        String[] a = null;
        String o3 = null;
        Address address = null;
        Address address2 = null;
        LoyaltyWalletObject[] array = null;
        OfferWalletObject[] array2 = null;
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        InstrumentInfo[] array3 = null;
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
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 4: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, b);
                    continue;
                }
                case 5: {
                    o3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 6: {
                    address = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, Address.CREATOR);
                    continue;
                }
                case 7: {
                    address2 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, Address.CREATOR);
                    continue;
                }
                case 8: {
                    array = com.google.android.gms.common.internal.safeparcel.a.b(parcel, b, LoyaltyWalletObject.CREATOR);
                    continue;
                }
                case 9: {
                    array2 = com.google.android.gms.common.internal.safeparcel.a.b(parcel, b, OfferWalletObject.CREATOR);
                    continue;
                }
                case 10: {
                    userAddress = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, UserAddress.CREATOR);
                    continue;
                }
                case 11: {
                    userAddress2 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, UserAddress.CREATOR);
                    continue;
                }
                case 12: {
                    array3 = com.google.android.gms.common.internal.safeparcel.a.b(parcel, b, InstrumentInfo.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new MaskedWallet(g, o, o2, a, o3, address, address2, array, array2, userAddress, userAddress2, array3);
    }
    
    public MaskedWallet[] fw(final int n) {
        return new MaskedWallet[n];
    }
}
