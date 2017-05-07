// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import java.util.List;
import android.os.RemoteException;
import android.os.IInterface;

public interface ng extends IInterface
{
    void a(final String p0, final nl p1, final nh p2) throws RemoteException;
    
    void a(final String p0, final nl p1, final List<nh> p2) throws RemoteException;
    
    void a(final String p0, final nl p1, final byte[] p2) throws RemoteException;
    
    public abstract static class a extends Binder implements ng
    {
        public static ng bC(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.playlog.internal.IPlayLogService");
            if (queryLocalInterface != null && queryLocalInterface instanceof ng) {
                return (ng)queryLocalInterface;
            }
            return new ng.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final nl nl = null;
            final nl nl2 = null;
            nh cx = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.playlog.internal.IPlayLogService");
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                    final String string = parcel.readString();
                    nl cy;
                    if (parcel.readInt() != 0) {
                        cy = com.google.android.gms.internal.nl.CREATOR.cY(parcel);
                    }
                    else {
                        cy = null;
                    }
                    if (parcel.readInt() != 0) {
                        cx = nh.CREATOR.cX(parcel);
                    }
                    this.a(string, cy, cx);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                    final String string2 = parcel.readString();
                    nl cy2 = nl;
                    if (parcel.readInt() != 0) {
                        cy2 = com.google.android.gms.internal.nl.CREATOR.cY(parcel);
                    }
                    this.a(string2, cy2, parcel.createTypedArrayList((Parcelable$Creator)nh.CREATOR));
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                    final String string3 = parcel.readString();
                    nl cy3 = nl2;
                    if (parcel.readInt() != 0) {
                        cy3 = com.google.android.gms.internal.nl.CREATOR.cY(parcel);
                    }
                    this.a(string3, cy3, parcel.createByteArray());
                    return true;
                }
            }
        }
        
        private static class a implements ng
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final String s, final nl nl, final nh nh) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
                            obtain.writeString(s);
                            if (nl != null) {
                                obtain.writeInt(1);
                                nl.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (nh != null) {
                                obtain.writeInt(1);
                                nh.writeToParcel(obtain, 0);
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
            public void a(final String s, final nl nl, final List<nh> list) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
                    obtain.writeString(s);
                    if (nl != null) {
                        obtain.writeInt(1);
                        nl.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeTypedList((List)list);
                    this.lb.transact(3, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final nl nl, final byte[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
                    obtain.writeString(s);
                    if (nl != null) {
                        obtain.writeInt(1);
                        nl.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeByteArray(array);
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
