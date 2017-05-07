// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import android.os.Bundle;
import com.google.android.gms.wallet.MaskedWallet;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class ov$a extends Binder implements ov
{
    public ov$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
    }
    
    public static ov bO(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof ov) {
            return (ov)queryLocalInterface;
        }
        return new ov$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                n = parcel.readInt();
                MaskedWallet maskedWallet;
                if (parcel.readInt() != 0) {
                    maskedWallet = (MaskedWallet)MaskedWallet.CREATOR.createFromParcel(parcel);
                }
                else {
                    maskedWallet = null;
                }
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.a(n, maskedWallet, bundle);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                n = parcel.readInt();
                FullWallet fullWallet;
                if (parcel.readInt() != 0) {
                    fullWallet = (FullWallet)FullWallet.CREATOR.createFromParcel(parcel);
                }
                else {
                    fullWallet = null;
                }
                Bundle bundle2;
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle2 = null;
                }
                this.a(n, fullWallet, bundle2);
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                n = parcel.readInt();
                final boolean b = parcel.readInt() != 0;
                Bundle bundle3;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle3 = null;
                }
                this.a(n, b, bundle3);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                n = parcel.readInt();
                Bundle bundle4;
                if (parcel.readInt() != 0) {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle4 = null;
                }
                this.i(n, bundle4);
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                Status fromParcel;
                if (parcel.readInt() != 0) {
                    fromParcel = Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    fromParcel = null;
                }
                oo oo;
                if (parcel.readInt() != 0) {
                    oo = (oo)com.google.android.gms.internal.oo.CREATOR.createFromParcel(parcel);
                }
                else {
                    oo = null;
                }
                Bundle bundle5;
                if (parcel.readInt() != 0) {
                    bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle5 = null;
                }
                this.a(fromParcel, oo, bundle5);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
