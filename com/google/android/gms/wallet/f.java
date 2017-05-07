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
        final int p3 = b.p(parcel);
        b.c(parcel, 1, fullWallet.getVersionCode());
        b.a(parcel, 2, fullWallet.abh, false);
        b.a(parcel, 3, fullWallet.abi, false);
        b.a(parcel, 4, (Parcelable)fullWallet.abj, n, false);
        b.a(parcel, 5, fullWallet.abk, false);
        b.a(parcel, 6, (Parcelable)fullWallet.abl, n, false);
        b.a(parcel, 7, (Parcelable)fullWallet.abm, n, false);
        b.a(parcel, 8, fullWallet.abn, false);
        b.a(parcel, 9, (Parcelable)fullWallet.abo, n, false);
        b.a(parcel, 10, (Parcelable)fullWallet.abp, n, false);
        b.a(parcel, 11, fullWallet.abq, n, false);
        b.F(parcel, p3);
    }
    
    public FullWallet bb(final Parcel parcel) {
        InstrumentInfo[] array = null;
        final int o = a.o(parcel);
        int g = 0;
        UserAddress userAddress = null;
        UserAddress userAddress2 = null;
        String[] z = null;
        Address address = null;
        Address address2 = null;
        String n = null;
        ProxyCard proxyCard = null;
        String n2 = null;
        String n3 = null;
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
                    n3 = a.n(parcel, n4);
                    continue;
                }
                case 3: {
                    n2 = a.n(parcel, n4);
                    continue;
                }
                case 4: {
                    proxyCard = a.a(parcel, n4, ProxyCard.CREATOR);
                    continue;
                }
                case 5: {
                    n = a.n(parcel, n4);
                    continue;
                }
                case 6: {
                    address2 = a.a(parcel, n4, Address.CREATOR);
                    continue;
                }
                case 7: {
                    address = a.a(parcel, n4, Address.CREATOR);
                    continue;
                }
                case 8: {
                    z = a.z(parcel, n4);
                    continue;
                }
                case 9: {
                    userAddress2 = a.a(parcel, n4, UserAddress.CREATOR);
                    continue;
                }
                case 10: {
                    userAddress = a.a(parcel, n4, UserAddress.CREATOR);
                    continue;
                }
                case 11: {
                    array = a.b(parcel, n4, InstrumentInfo.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new FullWallet(g, n3, n2, proxyCard, n, address2, address, z, userAddress2, userAddress, array);
    }
    
    public FullWallet[] cn(final int n) {
        return new FullWallet[n];
    }
}
