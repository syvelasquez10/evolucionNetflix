// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.f;
import android.os.IInterface;

public interface g extends IInterface
{
    void e(final f p0) throws RemoteException;
    
    public abstract static class a extends Binder implements g
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
        }
        
        public static g aX(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof g) {
                return (g)queryLocalInterface;
            }
            return new g.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
                    this.e(f.a.bu(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements g
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void e(final f f) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnInfoWindowClickListener");
                    IBinder binder;
                    if (f != null) {
                        binder = f.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
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
