// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import android.content.Intent;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import android.os.RemoteException;
import android.os.Bundle;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface oq extends IInterface
{
    void a(final d p0, final WalletFragmentOptions p1, final Bundle p2) throws RemoteException;
    
    int getState() throws RemoteException;
    
    void initialize(final WalletFragmentInitParams p0) throws RemoteException;
    
    void onActivityResult(final int p0, final int p1, final Intent p2) throws RemoteException;
    
    void onCreate(final Bundle p0) throws RemoteException;
    
    d onCreateView(final d p0, final d p1, final Bundle p2) throws RemoteException;
    
    void onPause() throws RemoteException;
    
    void onResume() throws RemoteException;
    
    void onSaveInstanceState(final Bundle p0) throws RemoteException;
    
    void onStart() throws RemoteException;
    
    void onStop() throws RemoteException;
    
    void setEnabled(final boolean p0) throws RemoteException;
    
    void updateMaskedWallet(final MaskedWallet p0) throws RemoteException;
    
    void updateMaskedWalletRequest(final MaskedWalletRequest p0) throws RemoteException;
    
    public abstract static class a extends Binder implements oq
    {
        public static oq bJ(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof oq) {
                return (oq)queryLocalInterface;
            }
            return new oq.a.a(binder);
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, int int1) throws RemoteException {
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
                    final d am = d.a.am(parcel.readStrongBinder());
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
                    final d am2 = d.a.am(parcel.readStrongBinder());
                    final d am3 = d.a.am(parcel.readStrongBinder());
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
        
        private static class a implements oq
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final d d, final WalletFragmentOptions walletFragmentOptions, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                            IBinder binder;
                            if (d != null) {
                                binder = d.asBinder();
                            }
                            else {
                                binder = null;
                            }
                            obtain.writeStrongBinder(binder);
                            if (walletFragmentOptions != null) {
                                obtain.writeInt(1);
                                walletFragmentOptions.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                                this.lb.transact(1, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public int getState() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    this.lb.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void initialize(final WalletFragmentInitParams walletFragmentInitParams) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    if (walletFragmentInitParams != null) {
                        obtain.writeInt(1);
                        walletFragmentInitParams.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onActivityResult(final int n, final int n2, final Intent intent) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onCreate(final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d onCreateView(d am, final d d, final Bundle bundle) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    IBinder binder2;
                    if (am != null) {
                        binder2 = am.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    IBinder binder3 = binder;
                    if (d != null) {
                        binder3 = d.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    am = com.google.android.gms.dynamic.d.a.am(obtain2.readStrongBinder());
                    return am;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onPause() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    this.lb.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onResume() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    this.lb.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onSaveInstanceState(final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onStart() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onStop() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    this.lb.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.lb.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void updateMaskedWallet(final MaskedWallet maskedWallet) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    if (maskedWallet != null) {
                        obtain.writeInt(1);
                        maskedWallet.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void updateMaskedWalletRequest(final MaskedWalletRequest maskedWalletRequest) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
                    if (maskedWalletRequest != null) {
                        obtain.writeInt(1);
                        maskedWalletRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
