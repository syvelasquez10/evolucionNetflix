// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;

public interface hk extends IInterface
{
    void a(final int p0, final Bundle p1, final int p2, final Intent p3) throws RemoteException;
    
    public abstract static class a extends Binder implements hk
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.panorama.internal.IPanoramaCallbacks");
        }
        
        public static hk ar(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof hk) {
                return (hk)queryLocalInterface;
            }
            return new hk.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
                    int1 = parcel.readInt();
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    int2 = parcel.readInt();
                    Intent intent;
                    if (parcel.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        intent = null;
                    }
                    this.a(int1, bundle, int2, intent);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements hk
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            @Override
            public void a(final int n, final Bundle bundle, final int n2, final Intent intent) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.panorama.internal.IPanoramaCallbacks");
                            obtain.writeInt(n);
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            obtain.writeInt(n2);
                            if (intent != null) {
                                obtain.writeInt(1);
                                intent.writeToParcel(obtain, 0);
                                this.dU.transact(1, obtain, obtain2, 0);
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
            
            public IBinder asBinder() {
                return this.dU;
            }
        }
    }
}
