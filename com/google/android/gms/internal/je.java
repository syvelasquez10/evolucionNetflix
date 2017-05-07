// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.MaskedWallet;
import android.os.RemoteException;
import android.os.Bundle;
import com.google.android.gms.wallet.FullWallet;
import android.os.IInterface;

public interface je extends IInterface
{
    void a(final int p0, final FullWallet p1, final Bundle p2) throws RemoteException;
    
    void a(final int p0, final MaskedWallet p1, final Bundle p2) throws RemoteException;
    
    void a(final int p0, final boolean p1, final Bundle p2) throws RemoteException;
    
    void a(final Status p0, final ix p1, final Bundle p2) throws RemoteException;
    
    void f(final int p0, final Bundle p1) throws RemoteException;
    
    public abstract static class a extends Binder implements je
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
        }
        
        public static je aX(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof je) {
                return (je)queryLocalInterface;
            }
            return new je.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
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
                    this.f(n, bundle4);
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
                    ix ix;
                    if (parcel.readInt() != 0) {
                        ix = (ix)com.google.android.gms.internal.ix.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        ix = null;
                    }
                    Bundle bundle5;
                    if (parcel.readInt() != 0) {
                        bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle5 = null;
                    }
                    this.a(fromParcel, ix, bundle5);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements je
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void a(final int n, final FullWallet fullWallet, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                            obtain.writeInt(n);
                            if (fullWallet != null) {
                                obtain.writeInt(1);
                                fullWallet.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                                this.kn.transact(2, obtain, obtain2, 0);
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
            
            @Override
            public void a(final int n, final MaskedWallet maskedWallet, final Bundle bundle) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                            obtain.writeInt(n);
                            if (maskedWallet != null) {
                                obtain.writeInt(1);
                                maskedWallet.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                                this.kn.transact(1, obtain, obtain2, 0);
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
            
            @Override
            public void a(int n, final boolean b, final Bundle bundle) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                    obtain.writeInt(n);
                    if (b) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final Status status, final ix ix, final Bundle bundle) throws RemoteException {
            Label_0068_Outer:
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0131:
                        while (true) {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                                if (status != null) {
                                    obtain.writeInt(1);
                                    status.writeToParcel(obtain, 0);
                                }
                                else {
                                    obtain.writeInt(0);
                                }
                                if (ix != null) {
                                    obtain.writeInt(1);
                                    ix.writeToParcel(obtain, 0);
                                    if (bundle != null) {
                                        obtain.writeInt(1);
                                        bundle.writeToParcel(obtain, 0);
                                        this.kn.transact(5, obtain, obtain2, 0);
                                        obtain2.readException();
                                        return;
                                    }
                                    break Label_0131;
                                }
                            }
                            finally {
                                obtain2.recycle();
                                obtain.recycle();
                            }
                            obtain.writeInt(0);
                            continue Label_0068_Outer;
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void f(final int n, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(4, obtain, obtain2, 0);
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
