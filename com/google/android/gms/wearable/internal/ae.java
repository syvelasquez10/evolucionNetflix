// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;

public interface ae extends IInterface
{
    void Z(final DataHolder p0) throws RemoteException;
    
    void a(final ah p0) throws RemoteException;
    
    void a(final ak p0) throws RemoteException;
    
    void b(final ak p0) throws RemoteException;
    
    public abstract static class a extends Binder implements ae
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.wearable.internal.IWearableListener");
        }
        
        public static ae bS(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof ae) {
                return (ae)queryLocalInterface;
            }
            return new ae.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final ah ah = null;
            final ak ak = null;
            final ak ak2 = null;
            final DataHolder dataHolder = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.wearable.internal.IWearableListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                    DataHolder z = dataHolder;
                    if (parcel.readInt() != 0) {
                        z = DataHolder.CREATOR.z(parcel);
                    }
                    this.Z(z);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                    ah ah2 = ah;
                    if (parcel.readInt() != 0) {
                        ah2 = (ah)com.google.android.gms.wearable.internal.ah.CREATOR.createFromParcel(parcel);
                    }
                    this.a(ah2);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                    ak ak3 = ak;
                    if (parcel.readInt() != 0) {
                        ak3 = (ak)com.google.android.gms.wearable.internal.ak.CREATOR.createFromParcel(parcel);
                    }
                    this.a(ak3);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                    ak ak4 = ak2;
                    if (parcel.readInt() != 0) {
                        ak4 = (ak)com.google.android.gms.wearable.internal.ak.CREATOR.createFromParcel(parcel);
                    }
                    this.b(ak4);
                    return true;
                }
            }
        }
        
        private static class a implements ae
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void Z(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ah ah) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
                    if (ah != null) {
                        obtain.writeInt(1);
                        ah.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(2, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ak ak) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
                    if (ak != null) {
                        obtain.writeInt(1);
                        ak.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(3, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void b(final ak ak) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableListener");
                    if (ak != null) {
                        obtain.writeInt(1);
                        ak.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(4, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
