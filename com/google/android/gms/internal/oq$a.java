// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class oq$a extends Binder implements oq
{
    public static oq bJ(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof oq) {
            return (oq)queryLocalInterface;
        }
        return new oq$a$a(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, int int1) {
        final IBinder binder = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, int1);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                final d am = d$a.am(parcel.readStrongBinder());
                WalletFragmentOptions walletFragmentOptions;
                if (parcel.readInt() != 0) {
                    walletFragmentOptions = (WalletFragmentOptions)WalletFragmentOptions.CREATOR.createFromParcel(parcel);
                }
                else {
                    walletFragmentOptions = null;
                }
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.a(am, walletFragmentOptions, bundle);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                Bundle bundle2;
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle2 = null;
                }
                this.onCreate(bundle2);
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                final d am2 = d$a.am(parcel.readStrongBinder());
                final d am3 = d$a.am(parcel.readStrongBinder());
                Bundle bundle3;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle3 = null;
                }
                final d onCreateView = this.onCreateView(am2, am3, bundle3);
                parcel2.writeNoException();
                IBinder binder2 = binder;
                if (onCreateView != null) {
                    binder2 = onCreateView.asBinder();
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                this.onStart();
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                this.onResume();
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                this.onPause();
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                this.onStop();
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                Bundle bundle4;
                if (parcel.readInt() != 0) {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle4 = null;
                }
                this.onSaveInstanceState(bundle4);
                parcel2.writeNoException();
                if (bundle4 != null) {
                    parcel2.writeInt(1);
                    bundle4.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                n = parcel.readInt();
                int1 = parcel.readInt();
                Intent intent;
                if (parcel.readInt() != 0) {
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                }
                else {
                    intent = null;
                }
                this.onActivityResult(n, int1, intent);
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                WalletFragmentInitParams walletFragmentInitParams;
                if (parcel.readInt() != 0) {
                    walletFragmentInitParams = (WalletFragmentInitParams)WalletFragmentInitParams.CREATOR.createFromParcel(parcel);
                }
                else {
                    walletFragmentInitParams = null;
                }
                this.initialize(walletFragmentInitParams);
                parcel2.writeNoException();
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                MaskedWalletRequest maskedWalletRequest;
                if (parcel.readInt() != 0) {
                    maskedWalletRequest = (MaskedWalletRequest)MaskedWalletRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    maskedWalletRequest = null;
                }
                this.updateMaskedWalletRequest(maskedWalletRequest);
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                this.setEnabled(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                n = this.getState();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                MaskedWallet maskedWallet;
                if (parcel.readInt() != 0) {
                    maskedWallet = (MaskedWallet)MaskedWallet.CREATOR.createFromParcel(parcel);
                }
                else {
                    maskedWallet = null;
                }
                this.updateMaskedWallet(maskedWallet);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
