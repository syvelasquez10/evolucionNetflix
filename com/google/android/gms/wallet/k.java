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
        final int p3 = b.p(parcel);
        b.c(parcel, 1, maskedWallet.getVersionCode());
        b.a(parcel, 2, maskedWallet.abh, false);
        b.a(parcel, 3, maskedWallet.abi, false);
        b.a(parcel, 4, maskedWallet.abn, false);
        b.a(parcel, 5, maskedWallet.abk, false);
        b.a(parcel, 6, (Parcelable)maskedWallet.abl, n, false);
        b.a(parcel, 7, (Parcelable)maskedWallet.abm, n, false);
        b.a(parcel, 8, maskedWallet.abT, n, false);
        b.a(parcel, 9, maskedWallet.abU, n, false);
        b.a(parcel, 10, (Parcelable)maskedWallet.abo, n, false);
        b.a(parcel, 11, (Parcelable)maskedWallet.abp, n, false);
        b.a(parcel, 12, maskedWallet.abq, n, false);
        b.F(parcel, p3);
    }
    
    public MaskedWallet bg(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        String n2 = null;
        String[] z = null;
        String n3 = null;
        Address address = null;
        Address address2 = null;
        LoyaltyWalletObject[] array = null;
        OfferWalletObject[] array2 = null;
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        InstrumentInfo[] array3 = null;
        while (parcel.dataPosition() < o) {
            final int n4 = a.n(parcel);
            switch (a.R(n4)) {
                default: {
                    a.b(parcel, n4);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n4);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n4);
                    continue;
                }
                case 3: {
                    n2 = a.n(parcel, n4);
                    continue;
                }
                case 4: {
                    z = a.z(parcel, n4);
                    continue;
                }
                case 5: {
                    n3 = a.n(parcel, n4);
                    continue;
                }
                case 6: {
                    address = a.a(parcel, n4, Address.CREATOR);
                    continue;
                }
                case 7: {
                    address2 = a.a(parcel, n4, Address.CREATOR);
                    continue;
                }
                case 8: {
                    array = a.b(parcel, n4, LoyaltyWalletObject.CREATOR);
                    continue;
                }
                case 9: {
                    array2 = a.b(parcel, n4, OfferWalletObject.CREATOR);
                    continue;
                }
                case 10: {
                    userAddress = a.a(parcel, n4, UserAddress.CREATOR);
                    continue;
                }
                case 11: {
                    userAddress2 = a.a(parcel, n4, UserAddress.CREATOR);
                    continue;
                }
                case 12: {
                    array3 = a.b(parcel, n4, InstrumentInfo.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new MaskedWallet(g, n, n2, z, n3, address, address2, array, array2, userAddress, userAddress2, array3);
    }
    
    public MaskedWallet[] cs(final int n) {
        return new MaskedWallet[n];
    }
}
