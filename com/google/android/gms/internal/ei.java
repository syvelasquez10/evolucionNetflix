// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.content.Intent;
import android.os.IInterface;

public interface ei extends IInterface
{
    void onActivityResult(final int p0, final int p1, final Intent p2) throws RemoteException;
    
    void onCreate() throws RemoteException;
    
    void onDestroy() throws RemoteException;
    
    public abstract static class a extends Binder implements ei
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
        }
        
        public static ei u(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
            if (queryLocalInterface != null && queryLocalInterface instanceof ei) {
                return (ei)queryLocalInterface;
            }
            return new ei.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
                    this.onCreate();
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
                    this.onDestroy();
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
                    int1 = parcel.readInt();
                    int2 = parcel.readInt();
                    Intent intent;
                    if (parcel.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        intent = null;
                    }
                    this.onActivityResult(int1, int2, intent);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements ei
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void onActivityResult(final int n, final int n2, final Intent intent) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onCreate() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onDestroy() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
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
