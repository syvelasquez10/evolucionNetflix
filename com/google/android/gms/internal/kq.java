// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;
import android.os.IInterface;

public interface kq extends IInterface
{
    void a(final SessionReadResult p0) throws RemoteException;
    
    public abstract static class a extends Binder implements kq
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.fitness.internal.ISessionReadCallback");
        }
        
        public static kq au(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionReadCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof kq) {
                return (kq)queryLocalInterface;
            }
            return new kq.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.fitness.internal.ISessionReadCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.ISessionReadCallback");
                    SessionReadResult sessionReadResult;
                    if (parcel.readInt() != 0) {
                        sessionReadResult = (SessionReadResult)SessionReadResult.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        sessionReadResult = null;
                    }
                    this.a(sessionReadResult);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements kq
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final SessionReadResult sessionReadResult) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.ISessionReadCallback");
                    if (sessionReadResult != null) {
                        obtain.writeInt(1);
                        sessionReadResult.writeToParcel(obtain, 0);
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
