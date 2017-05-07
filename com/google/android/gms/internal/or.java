// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IInterface;

public interface or extends IInterface
{
    void a(final int p0, final int p1, final Bundle p2) throws RemoteException;
    
    public abstract static class a extends Binder implements or
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
        }
        
        public static or bK(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof or) {
                return (or)queryLocalInterface;
            }
            return new or.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, int int2) throws RemoteException {
            switch (int1) {
                default: {
                    return super.onTransact(int1, parcel, parcel2, int2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
                    int1 = parcel.readInt();
                    int2 = parcel.readInt();
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    this.a(int1, int2, bundle);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements or
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final int n, final int n2, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
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
            
            public IBinder asBinder() {
                return this.lb;
            }
        }
    }
}
