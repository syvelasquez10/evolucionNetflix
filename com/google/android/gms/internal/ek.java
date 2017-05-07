// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.content.Intent;
import android.os.RemoteException;
import android.os.IInterface;

public interface ek extends IInterface
{
    void finishPurchase() throws RemoteException;
    
    String getProductId() throws RemoteException;
    
    Intent getPurchaseData() throws RemoteException;
    
    int getResultCode() throws RemoteException;
    
    boolean isVerified() throws RemoteException;
    
    public abstract static class a extends Binder implements ek
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
        }
        
        public static ek w(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
            if (queryLocalInterface != null && queryLocalInterface instanceof ek) {
                return (ek)queryLocalInterface;
            }
            return new ek.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int resultCode, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
            final int n2 = 0;
            switch (resultCode) {
                default: {
                    return super.onTransact(resultCode, parcel, parcel2, n);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    final String productId = this.getProductId();
                    parcel2.writeNoException();
                    parcel2.writeString(productId);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    final Intent purchaseData = this.getPurchaseData();
                    parcel2.writeNoException();
                    if (purchaseData != null) {
                        parcel2.writeInt(1);
                        purchaseData.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    resultCode = this.getResultCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(resultCode);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    final boolean verified = this.isVerified();
                    parcel2.writeNoException();
                    resultCode = n2;
                    if (verified) {
                        resultCode = 1;
                    }
                    parcel2.writeInt(resultCode);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.finishPurchase();
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements ek
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void finishPurchase() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.lb.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getProductId() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent getPurchaseData() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int getResultCode() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isVerified() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
