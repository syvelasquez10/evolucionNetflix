// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.wallet.d;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.FullWalletRequest;
import android.os.Bundle;
import com.google.android.gms.wallet.MaskedWalletRequest;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class os$a extends Binder implements os
{
    public static os bL(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
        if (queryLocalInterface != null && queryLocalInterface instanceof os) {
            return (os)queryLocalInterface;
        }
        return new os$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.wallet.internal.IOwService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                MaskedWalletRequest maskedWalletRequest;
                if (parcel.readInt() != 0) {
                    maskedWalletRequest = (MaskedWalletRequest)MaskedWalletRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    maskedWalletRequest = null;
                }
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.a(maskedWalletRequest, bundle, ov$a.bO(parcel.readStrongBinder()));
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                FullWalletRequest fullWalletRequest;
                if (parcel.readInt() != 0) {
                    fullWalletRequest = (FullWalletRequest)FullWalletRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    fullWalletRequest = null;
                }
                Bundle bundle2;
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle2 = null;
                }
                this.a(fullWalletRequest, bundle2, ov$a.bO(parcel.readStrongBinder()));
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                final String string = parcel.readString();
                final String string2 = parcel.readString();
                Bundle bundle3;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle3 = null;
                }
                this.a(string, string2, bundle3, ov$a.bO(parcel.readStrongBinder()));
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                NotifyTransactionStatusRequest notifyTransactionStatusRequest;
                if (parcel.readInt() != 0) {
                    notifyTransactionStatusRequest = (NotifyTransactionStatusRequest)NotifyTransactionStatusRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    notifyTransactionStatusRequest = null;
                }
                Bundle bundle4;
                if (parcel.readInt() != 0) {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle4 = null;
                }
                this.a(notifyTransactionStatusRequest, bundle4);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                Bundle bundle5;
                if (parcel.readInt() != 0) {
                    bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle5 = null;
                }
                this.a(bundle5, ov$a.bO(parcel.readStrongBinder()));
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                d d;
                if (parcel.readInt() != 0) {
                    d = (d)com.google.android.gms.wallet.d.CREATOR.createFromParcel(parcel);
                }
                else {
                    d = null;
                }
                Bundle bundle6;
                if (parcel.readInt() != 0) {
                    bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle6 = null;
                }
                this.a(d, bundle6, ov$a.bO(parcel.readStrongBinder()));
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                MaskedWalletRequest maskedWalletRequest2;
                if (parcel.readInt() != 0) {
                    maskedWalletRequest2 = (MaskedWalletRequest)MaskedWalletRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    maskedWalletRequest2 = null;
                }
                Bundle bundle7;
                if (parcel.readInt() != 0) {
                    bundle7 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle7 = null;
                }
                this.a(maskedWalletRequest2, bundle7, ou$a.bN(parcel.readStrongBinder()));
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                om om;
                if (parcel.readInt() != 0) {
                    om = (om)com.google.android.gms.internal.om.CREATOR.createFromParcel(parcel);
                }
                else {
                    om = null;
                }
                Bundle bundle8;
                if (parcel.readInt() != 0) {
                    bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle8 = null;
                }
                this.a(om, bundle8, ov$a.bO(parcel.readStrongBinder()));
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IOwService");
                Bundle bundle9;
                if (parcel.readInt() != 0) {
                    bundle9 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle9 = null;
                }
                this.p(bundle9);
                return true;
            }
        }
    }
}
