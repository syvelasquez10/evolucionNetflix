// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface jc extends IInterface
{
    iz a(final d p0, final c p1, final WalletFragmentOptions p2, final ja p3) throws RemoteException;
    
    public abstract static class a extends Binder implements jc
    {
        public static jc aV(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
            if (queryLocalInterface != null && queryLocalInterface instanceof jc) {
                return (jc)queryLocalInterface;
            }
            return new jc.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final IBinder binder = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                    final d k = d.a.K(parcel.readStrongBinder());
                    final c j = c.a.J(parcel.readStrongBinder());
                    WalletFragmentOptions walletFragmentOptions;
                    if (parcel.readInt() != 0) {
                        walletFragmentOptions = (WalletFragmentOptions)WalletFragmentOptions.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        walletFragmentOptions = null;
                    }
                    final iz a = this.a(k, j, walletFragmentOptions, ja.a.aT(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder2 = binder;
                    if (a != null) {
                        binder2 = a.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    return true;
                }
            }
        }
        
        private static class a implements jc
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public iz a(final d d, final c c, final WalletFragmentOptions walletFragmentOptions, final ja ja) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                    IBinder binder2;
                    if (d != null) {
                        binder2 = d.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    IBinder binder3;
                    if (c != null) {
                        binder3 = c.asBinder();
                    }
                    else {
                        binder3 = null;
                    }
                    obtain.writeStrongBinder(binder3);
                    if (walletFragmentOptions != null) {
                        obtain.writeInt(1);
                        walletFragmentOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder4 = binder;
                    if (ja != null) {
                        binder4 = ja.asBinder();
                    }
                    obtain.writeStrongBinder(binder4);
                    this.kn.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return iz.a.aS(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
        }
    }
}
