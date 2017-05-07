// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;
import android.os.IInterface;

public interface i extends IInterface
{
    Tile getTile(final int p0, final int p1, final int p2) throws RemoteException;
    
    public abstract static class a extends Binder implements i
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
        }
        
        public static i aK(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof i) {
                return (i)queryLocalInterface;
            }
            return new i.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                    final Tile tile = this.getTile(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (tile != null) {
                        parcel2.writeInt(1);
                        tile.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
            }
        }
        
        private static class a implements i
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public Tile getTile(final int n, final int n2, final int n3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeInt(n3);
                    this.kn.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    Tile fromParcel;
                    if (obtain2.readInt() != 0) {
                        fromParcel = Tile.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        fromParcel = null;
                    }
                    return fromParcel;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
