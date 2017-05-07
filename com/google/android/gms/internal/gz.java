// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.app.PendingIntent;
import android.os.RemoteException;
import android.os.IInterface;

public interface gz extends IInterface
{
    void onAddGeofencesResult(final int p0, final String[] p1) throws RemoteException;
    
    void onRemoveGeofencesByPendingIntentResult(final int p0, final PendingIntent p1) throws RemoteException;
    
    void onRemoveGeofencesByRequestIdsResult(final int p0, final String[] p1) throws RemoteException;
    
    public abstract static class a extends Binder implements gz
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.location.internal.IGeofencerCallbacks");
        }
        
        public static gz V(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof gz) {
                return (gz)queryLocalInterface;
            }
            return new gz.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    this.onAddGeofencesResult(parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    this.onRemoveGeofencesByRequestIdsResult(parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    int1 = parcel.readInt();
                    PendingIntent pendingIntent;
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent = null;
                    }
                    this.onRemoveGeofencesByPendingIntentResult(int1, pendingIntent);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements gz
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void onAddGeofencesResult(final int n, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    obtain.writeInt(n);
                    obtain.writeStringArray(array);
                    this.kn.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onRemoveGeofencesByPendingIntentResult(final int n, final PendingIntent pendingIntent) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    obtain.writeInt(n);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onRemoveGeofencesByRequestIdsResult(final int n, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
                    obtain.writeInt(n);
                    obtain.writeStringArray(array);
                    this.kn.transact(2, obtain, obtain2, 0);
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
