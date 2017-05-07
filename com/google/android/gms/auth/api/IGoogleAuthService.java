// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface IGoogleAuthService extends IInterface
{
    void sendConnection(final IGoogleAuthApiCallbacks p0, final GoogleAuthApiRequest p1) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IGoogleAuthService
    {
        public Stub() {
            this.attachInterface((IInterface)this, "com.google.android.gms.auth.api.IGoogleAuthService");
        }
        
        public static IGoogleAuthService asInterface(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.IGoogleAuthService");
            if (queryLocalInterface != null && queryLocalInterface instanceof IGoogleAuthService) {
                return (IGoogleAuthService)queryLocalInterface;
            }
            return new a(binder);
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
                    parcel2.writeString("com.google.android.gms.auth.api.IGoogleAuthService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.auth.api.IGoogleAuthService");
                    final IGoogleAuthApiCallbacks interface1 = IGoogleAuthApiCallbacks.Stub.asInterface(parcel.readStrongBinder());
                    GoogleAuthApiRequest fromParcel;
                    if (parcel.readInt() != 0) {
                        fromParcel = GoogleAuthApiRequest.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel = null;
                    }
                    this.sendConnection(interface1, fromParcel);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements IGoogleAuthService
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void sendConnection(final IGoogleAuthApiCallbacks googleAuthApiCallbacks, final GoogleAuthApiRequest googleAuthApiRequest) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.IGoogleAuthService");
                    IBinder binder;
                    if (googleAuthApiCallbacks != null) {
                        binder = googleAuthApiCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (googleAuthApiRequest != null) {
                        obtain.writeInt(1);
                        googleAuthApiRequest.writeToParcel(obtain, 0);
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
        }
    }
}
