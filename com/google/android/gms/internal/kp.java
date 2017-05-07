// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;
import android.os.IInterface;

public interface kp extends IInterface
{
    void a(final ListSubscriptionsResult p0) throws RemoteException;
    
    public abstract static class a extends Binder implements kp
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
        }
        
        public static kp at(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof kp) {
                return (kp)queryLocalInterface;
            }
            return new kp.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
                    ListSubscriptionsResult listSubscriptionsResult;
                    if (parcel.readInt() != 0) {
                        listSubscriptionsResult = (ListSubscriptionsResult)ListSubscriptionsResult.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        listSubscriptionsResult = null;
                    }
                    this.a(listSubscriptionsResult);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements kp
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final ListSubscriptionsResult listSubscriptionsResult) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
                    if (listSubscriptionsResult != null) {
                        obtain.writeInt(1);
                        listSubscriptionsResult.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(1, obtain, obtain2, 0);
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
