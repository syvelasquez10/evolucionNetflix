// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import com.google.android.gms.drive.realtime.internal.event.ParcelableEventList;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;

public interface g extends IInterface
{
    void a(final DataHolder p0, final ParcelableEventList p1) throws RemoteException;
    
    void o(final Status p0) throws RemoteException;
    
    public abstract static class a extends Binder implements g
    {
        public static g ac(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof g) {
                return (g)queryLocalInterface;
            }
            return new g.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            Status fromParcel = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback");
                    DataHolder z;
                    if (parcel.readInt() != 0) {
                        z = DataHolder.CREATOR.z(parcel);
                    }
                    else {
                        z = null;
                    }
                    ParcelableEventList list;
                    if (parcel.readInt() != 0) {
                        list = (ParcelableEventList)ParcelableEventList.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        list = null;
                    }
                    this.a(z, list);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback");
                    if (parcel.readInt() != 0) {
                        fromParcel = Status.CREATOR.createFromParcel(parcel);
                    }
                    this.o(fromParcel);
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
            
            @Override
            public void a(final DataHolder dataHolder, final ParcelableEventList list) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback");
                            if (dataHolder != null) {
                                obtain.writeInt(1);
                                dataHolder.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (list != null) {
                                obtain.writeInt(1);
                                list.writeToParcel(obtain, 0);
                                this.lb.transact(1, obtain, obtain2, 0);
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
                return this.lb;
            }
            
            @Override
            public void o(final Status status) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IDataHolderEventCallback");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
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
