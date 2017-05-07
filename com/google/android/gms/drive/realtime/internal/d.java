// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface d extends IInterface
{
    void a(final ParcelableCollaborator p0) throws RemoteException;
    
    void b(final ParcelableCollaborator p0) throws RemoteException;
    
    public abstract static class a extends Binder implements d
    {
        public static d Z(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof d) {
                return (d)queryLocalInterface;
            }
            return new d.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final ParcelableCollaborator parcelableCollaborator = null;
            ParcelableCollaborator parcelableCollaborator2 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback");
                    if (parcel.readInt() != 0) {
                        parcelableCollaborator2 = (ParcelableCollaborator)ParcelableCollaborator.CREATOR.createFromParcel(parcel);
                    }
                    this.a(parcelableCollaborator2);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback");
                    ParcelableCollaborator parcelableCollaborator3 = parcelableCollaborator;
                    if (parcel.readInt() != 0) {
                        parcelableCollaborator3 = (ParcelableCollaborator)ParcelableCollaborator.CREATOR.createFromParcel(parcel);
                    }
                    this.b(parcelableCollaborator3);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements d
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final ParcelableCollaborator parcelableCollaborator) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback");
                    if (parcelableCollaborator != null) {
                        obtain.writeInt(1);
                        parcelableCollaborator.writeToParcel(obtain, 0);
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
            
            @Override
            public void b(final ParcelableCollaborator parcelableCollaborator) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.ICollaboratorEventCallback");
                    if (parcelableCollaborator != null) {
                        obtain.writeInt(1);
                        parcelableCollaborator.writeToParcel(obtain, 0);
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
