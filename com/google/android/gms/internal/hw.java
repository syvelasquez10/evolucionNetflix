// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;

public interface hw extends IInterface
{
    void a(final Status p0) throws RemoteException;
    
    void a(final Status p0, final ParcelFileDescriptor p1) throws RemoteException;
    
    void a(final Status p0, final boolean p1) throws RemoteException;
    
    void a(final hm.b p0) throws RemoteException;
    
    public abstract static class a extends Binder implements hw
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
        }
        
        public static hw G(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof hw) {
                return (hw)queryLocalInterface;
            }
            return new hw.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            ParcelFileDescriptor parcelFileDescriptor = null;
            final Status status = null;
            final hm.b b = null;
            final Status status2 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                    Status fromParcel = status2;
                    if (parcel.readInt() != 0) {
                        fromParcel = Status.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                    Status fromParcel2;
                    if (parcel.readInt() != 0) {
                        fromParcel2 = Status.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel2 = null;
                    }
                    if (parcel.readInt() != 0) {
                        parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel2, parcelFileDescriptor);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                    Status fromParcel3 = status;
                    if (parcel.readInt() != 0) {
                        fromParcel3 = Status.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel3, parcel.readInt() != 0);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                    hm.b q = b;
                    if (parcel.readInt() != 0) {
                        q = hm.b.CREATOR.q(parcel);
                    }
                    this.a(q);
                    return true;
                }
            }
        }
        
        private static class a implements hw
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final Status status) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
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
            public void a(final Status status, final ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                            if (status != null) {
                                obtain.writeInt(1);
                                status.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (parcelFileDescriptor != null) {
                                obtain.writeInt(1);
                                parcelFileDescriptor.writeToParcel(obtain, 0);
                                this.lb.transact(2, obtain, (Parcel)null, 1);
                                return;
                            }
                        }
                        finally {
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final Status status, final boolean b) throws RemoteException {
            Label_0078_Outer:
                while (true) {
                    int n = 1;
                    final Parcel obtain = Parcel.obtain();
                    while (true) {
                        while (true) {
                            Label_0083: {
                                try {
                                    obtain.writeInterfaceToken("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                                    if (status != null) {
                                        obtain.writeInt(1);
                                        status.writeToParcel(obtain, 0);
                                        break Label_0083;
                                    }
                                    obtain.writeInt(0);
                                    break Label_0083;
                                    obtain.writeInt(n);
                                    this.lb.transact(3, obtain, (Parcel)null, 1);
                                    return;
                                }
                                finally {
                                    obtain.recycle();
                                }
                                n = 0;
                                continue Label_0078_Outer;
                            }
                            if (b) {
                                continue Label_0078_Outer;
                            }
                            break;
                        }
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final hm.b b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
                    if (b != null) {
                        obtain.writeInt(1);
                        b.writeToParcel(obtain, 0);
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
            
            public IBinder asBinder() {
                return this.lb;
            }
        }
    }
}
