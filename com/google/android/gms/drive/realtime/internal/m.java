// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.common.data.DataHolder;
import android.os.RemoteException;
import android.os.IInterface;

public interface m extends IInterface
{
    void a(final int p0, final j p1) throws RemoteException;
    
    void a(final BeginCompoundOperationRequest p0, final o p1) throws RemoteException;
    
    void a(final EndCompoundOperationRequest p0, final j p1) throws RemoteException;
    
    void a(final EndCompoundOperationRequest p0, final o p1) throws RemoteException;
    
    void a(final ParcelableIndexReference p0, final n p1) throws RemoteException;
    
    void a(final c p0) throws RemoteException;
    
    void a(final d p0) throws RemoteException;
    
    void a(final e p0) throws RemoteException;
    
    void a(final h p0) throws RemoteException;
    
    void a(final i p0) throws RemoteException;
    
    void a(final j p0) throws RemoteException;
    
    void a(final l p0) throws RemoteException;
    
    void a(final o p0) throws RemoteException;
    
    void a(final String p0, final int p1, final int p2, final g p3) throws RemoteException;
    
    void a(final String p0, final int p1, final int p2, final j p3) throws RemoteException;
    
    void a(final String p0, final int p1, final DataHolder p2, final g p3) throws RemoteException;
    
    void a(final String p0, final int p1, final DataHolder p2, final j p3) throws RemoteException;
    
    void a(final String p0, final int p1, final o p2) throws RemoteException;
    
    void a(final String p0, final int p1, final String p2, final int p3, final j p4) throws RemoteException;
    
    void a(final String p0, final int p1, final String p2, final j p3) throws RemoteException;
    
    void a(final String p0, final DataHolder p1, final j p2) throws RemoteException;
    
    void a(final String p0, final f p1) throws RemoteException;
    
    void a(final String p0, final j p1) throws RemoteException;
    
    void a(final String p0, final k p1) throws RemoteException;
    
    void a(final String p0, final l p1) throws RemoteException;
    
    void a(final String p0, final n p1) throws RemoteException;
    
    void a(final String p0, final o p1) throws RemoteException;
    
    void a(final String p0, final String p1, final f p2) throws RemoteException;
    
    void a(final String p0, final String p1, final g p2) throws RemoteException;
    
    void a(final String p0, final String p1, final j p2) throws RemoteException;
    
    void b(final c p0) throws RemoteException;
    
    void b(final j p0) throws RemoteException;
    
    void b(final l p0) throws RemoteException;
    
    void b(final o p0) throws RemoteException;
    
    void b(final String p0, final f p1) throws RemoteException;
    
    void b(final String p0, final l p1) throws RemoteException;
    
    void b(final String p0, final n p1) throws RemoteException;
    
    void b(final String p0, final o p1) throws RemoteException;
    
    void c(final c p0) throws RemoteException;
    
    void c(final String p0, final l p1) throws RemoteException;
    
    void d(final c p0) throws RemoteException;
    
    public abstract static class a extends Binder implements m
    {
        public static m ai(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
            if (queryLocalInterface != null && queryLocalInterface instanceof m) {
                return (m)queryLocalInterface;
            }
            return new m.a.a(binder);
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final DataHolder dataHolder = null;
            final DataHolder dataHolder2 = null;
            final BeginCompoundOperationRequest beginCompoundOperationRequest = null;
            final EndCompoundOperationRequest endCompoundOperationRequest = null;
            final ParcelableIndexReference parcelableIndexReference = null;
            final EndCompoundOperationRequest endCompoundOperationRequest2 = null;
            DataHolder z = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), n.a.aj(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(c.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(o.a.ak(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 33: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.b(c.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 35: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.b(o.a.ak(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 40: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(l.a.ah(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), parcel.readString(), f.a.ab(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), l.a.ah(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    final String string = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = DataHolder.CREATOR.z(parcel);
                    }
                    this.a(string, z, j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), f.a.ab(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), parcel.readString(), g.a.ac(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.b(parcel.readString(), l.a.ah(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.b(parcel.readString(), n.a.aj(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), parcel.readInt(), parcel.readString(), j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), parcel.readInt(), parcel.readInt(), j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), parcel.readString(), j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.b(parcel.readString(), f.a.ab(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.c(parcel.readString(), l.a.ah(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    final String string2 = parcel.readString();
                    n = parcel.readInt();
                    DataHolder z2 = dataHolder;
                    if (parcel.readInt() != 0) {
                        z2 = DataHolder.CREATOR.z(parcel);
                    }
                    this.a(string2, n, z2, j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    final String string3 = parcel.readString();
                    n = parcel.readInt();
                    DataHolder z3 = dataHolder2;
                    if (parcel.readInt() != 0) {
                        z3 = DataHolder.CREATOR.z(parcel);
                    }
                    this.a(string3, n, z3, g.a.ac(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), parcel.readInt(), parcel.readInt(), g.a.ac(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 37: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt(), j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    BeginCompoundOperationRequest beginCompoundOperationRequest2 = beginCompoundOperationRequest;
                    if (parcel.readInt() != 0) {
                        beginCompoundOperationRequest2 = (BeginCompoundOperationRequest)BeginCompoundOperationRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(beginCompoundOperationRequest2, o.a.ak(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 41: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    EndCompoundOperationRequest endCompoundOperationRequest3 = endCompoundOperationRequest;
                    if (parcel.readInt() != 0) {
                        endCompoundOperationRequest3 = (EndCompoundOperationRequest)EndCompoundOperationRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(endCompoundOperationRequest3, j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 22: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 23: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.b(j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 24: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.c(c.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 25: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.d(c.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 26: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    ParcelableIndexReference parcelableIndexReference2 = parcelableIndexReference;
                    if (parcel.readInt() != 0) {
                        parcelableIndexReference2 = (ParcelableIndexReference)ParcelableIndexReference.CREATOR.createFromParcel(parcel);
                    }
                    this.a(parcelableIndexReference2, n.a.aj(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 27: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), k.a.ag(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 28: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), parcel.readInt(), o.a.ak(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 29: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.b(l.a.ah(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 30: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readInt(), j.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 31: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(e.a.aa(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 32: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(d.a.Z(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 34: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(i.a.ae(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 36: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(h.a.ad(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 38: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.a(parcel.readString(), o.a.ak(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 39: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    this.b(parcel.readString(), o.a.ak(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    EndCompoundOperationRequest endCompoundOperationRequest4 = endCompoundOperationRequest2;
                    if (parcel.readInt() != 0) {
                        endCompoundOperationRequest4 = (EndCompoundOperationRequest)EndCompoundOperationRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(endCompoundOperationRequest4, o.a.ak(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements m
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final int n, final j j) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeInt(n);
                    IBinder binder;
                    if (j != null) {
                        binder = j.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final BeginCompoundOperationRequest beginCompoundOperationRequest, final o o) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                            if (beginCompoundOperationRequest != null) {
                                obtain.writeInt(1);
                                beginCompoundOperationRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (o != null) {
                                final IBinder binder = o.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(18, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final EndCompoundOperationRequest endCompoundOperationRequest, final j j) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                            if (endCompoundOperationRequest != null) {
                                obtain.writeInt(1);
                                endCompoundOperationRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (j != null) {
                                final IBinder binder = j.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(41, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final EndCompoundOperationRequest endCompoundOperationRequest, final o o) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                            if (endCompoundOperationRequest != null) {
                                obtain.writeInt(1);
                                endCompoundOperationRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (o != null) {
                                final IBinder binder = o.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(19, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final ParcelableIndexReference parcelableIndexReference, final n n) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                            if (parcelableIndexReference != null) {
                                obtain.writeInt(1);
                                parcelableIndexReference.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (n != null) {
                                final IBinder binder = n.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(26, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final c c) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (c != null) {
                        binder = c.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final e e) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (e != null) {
                        binder = e.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final h h) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (h != null) {
                        binder = h.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final i i) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (i != null) {
                        binder = i.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final j j) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (j != null) {
                        binder = j.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final l l) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (l != null) {
                        binder = l.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final o o) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (o != null) {
                        binder = o.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final int n, final int n2, final g g) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    IBinder binder;
                    if (g != null) {
                        binder = g.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final int n, final int n2, final j j) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    IBinder binder;
                    if (j != null) {
                        binder = j.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final int n, final DataHolder dataHolder, final g g) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                            obtain.writeString(s);
                            obtain.writeInt(n);
                            if (dataHolder != null) {
                                obtain.writeInt(1);
                                dataHolder.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (g != null) {
                                final IBinder binder = g.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(16, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final String s, final int n, final DataHolder dataHolder, final j j) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                            obtain.writeString(s);
                            obtain.writeInt(n);
                            if (dataHolder != null) {
                                obtain.writeInt(1);
                                dataHolder.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (j != null) {
                                final IBinder binder = j.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(15, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final String s, final int n, final o o) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    IBinder binder;
                    if (o != null) {
                        binder = o.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final int n, final String s2, final int n2, final j j) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    obtain.writeString(s2);
                    obtain.writeInt(n2);
                    IBinder binder;
                    if (j != null) {
                        binder = j.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final int n, final String s2, final j j) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    obtain.writeString(s2);
                    IBinder binder;
                    if (j != null) {
                        binder = j.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final DataHolder dataHolder, final j j) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                            obtain.writeString(s);
                            if (dataHolder != null) {
                                obtain.writeInt(1);
                                dataHolder.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (j != null) {
                                final IBinder binder = j.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(6, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final String s, final f f) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (f != null) {
                        binder = f.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final j j) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (j != null) {
                        binder = j.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final k k) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (k != null) {
                        binder = k.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final l l) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (l != null) {
                        binder = l.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final n n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (n != null) {
                        binder = n.asBinder();
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
            
            @Override
            public void a(final String s, final o o) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (o != null) {
                        binder = o.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final String s2, final f f) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    IBinder binder;
                    if (f != null) {
                        binder = f.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final String s2, final g g) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    IBinder binder;
                    if (g != null) {
                        binder = g.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final String s2, final j j) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    IBinder binder;
                    if (j != null) {
                        binder = j.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(12, obtain, obtain2, 0);
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
            public void b(final c c) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (c != null) {
                        binder = c.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final j j) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (j != null) {
                        binder = j.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final l l) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (l != null) {
                        binder = l.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final o o) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (o != null) {
                        binder = o.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final String s, final f f) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (f != null) {
                        binder = f.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final String s, final l l) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (l != null) {
                        binder = l.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final String s, final n n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (n != null) {
                        binder = n.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final String s, final o o) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (o != null) {
                        binder = o.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final c c) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (c != null) {
                        binder = c.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final String s, final l l) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (l != null) {
                        binder = l.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final c c) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    IBinder binder;
                    if (c != null) {
                        binder = c.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(25, obtain, obtain2, 0);
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
