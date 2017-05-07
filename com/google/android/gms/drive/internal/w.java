// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface w extends IInterface
{
    void a(final OnEventResponse p0) throws RemoteException;
    
    public abstract static class a extends Binder implements w
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.drive.internal.IEventCallback");
        }
        
        public static w I(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.internal.IEventCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof w) {
                return (w)queryLocalInterface;
            }
            return new w.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.drive.internal.IEventCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IEventCallback");
                    OnEventResponse onEventResponse;
                    if (parcel.readInt() != 0) {
                        onEventResponse = (OnEventResponse)OnEventResponse.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        onEventResponse = null;
                    }
                    this.a(onEventResponse);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements w
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void a(final OnEventResponse onEventResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IEventCallback");
                    if (onEventResponse != null) {
                        obtain.writeInt(1);
                        onEventResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
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
