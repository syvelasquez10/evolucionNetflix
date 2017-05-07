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

public interface lm extends IInterface
{
    void g(final int p0, final Bundle p1) throws RemoteException;
    
    public abstract static class a extends Binder implements lm
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.identity.intents.internal.IAddressCallbacks");
        }
        
        public static lm aG(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof lm) {
                return (lm)queryLocalInterface;
            }
            return new lm.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
            switch (int1) {
                default: {
                    return super.onTransact(int1, parcel, parcel2, n);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
                    int1 = parcel.readInt();
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    this.g(int1, bundle);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements lm
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void g(final int n, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
                    obtain.writeInt(n);
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
        }
    }
}
