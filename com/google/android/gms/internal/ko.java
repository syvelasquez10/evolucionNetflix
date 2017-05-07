// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.fitness.request.z;
import com.google.android.gms.fitness.request.x;
import com.google.android.gms.fitness.request.v;
import com.google.android.gms.fitness.request.t;
import com.google.android.gms.fitness.request.p;
import com.google.android.gms.fitness.request.n;
import com.google.android.gms.fitness.request.l;
import com.google.android.gms.fitness.request.i;
import com.google.android.gms.fitness.request.b;
import com.google.android.gms.fitness.request.ah;
import com.google.android.gms.fitness.request.ae;
import com.google.android.gms.fitness.request.ac;
import com.google.android.gms.fitness.request.UnclaimBleDeviceRequest;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataInsertRequest;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import android.os.IInterface;

public interface ko extends IInterface
{
    void a(final DataDeleteRequest p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final DataInsertRequest p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final DataReadRequest p0, final kl p1, final String p2) throws RemoteException;
    
    void a(final DataSourcesRequest p0, final km p1, final String p2) throws RemoteException;
    
    void a(final DataTypeCreateRequest p0, final kn p1, final String p2) throws RemoteException;
    
    void a(final SessionInsertRequest p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final SessionReadRequest p0, final kq p1, final String p2) throws RemoteException;
    
    void a(final StartBleScanRequest p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final UnclaimBleDeviceRequest p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final ac p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final ae p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final ah p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final b p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final i p0, final kn p1, final String p2) throws RemoteException;
    
    void a(final l p0, final kp p1, final String p2) throws RemoteException;
    
    void a(final n p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final p p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final t p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final v p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final x p0, final kr p1, final String p2) throws RemoteException;
    
    void a(final z p0, final ks p1, final String p2) throws RemoteException;
    
    void a(final ks p0, final String p1) throws RemoteException;
    
    void a(final le p0, final String p1) throws RemoteException;
    
    void b(final ks p0, final String p1) throws RemoteException;
    
    public abstract static class a extends Binder implements ko
    {
        public static ko as(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
            if (queryLocalInterface != null && queryLocalInterface instanceof ko) {
                return (ko)queryLocalInterface;
            }
            return new ko.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final n n3 = null;
            final p p4 = null;
            final ae ae = null;
            final ah ah = null;
            final l l = null;
            final DataInsertRequest dataInsertRequest = null;
            final DataDeleteRequest dataDeleteRequest = null;
            final DataTypeCreateRequest dataTypeCreateRequest = null;
            final i i = null;
            final DataReadRequest dataReadRequest = null;
            final SessionInsertRequest sessionInsertRequest = null;
            final SessionReadRequest sessionReadRequest = null;
            final v v = null;
            final x x = null;
            final StartBleScanRequest startBleScanRequest = null;
            final ac ac = null;
            final b b = null;
            final UnclaimBleDeviceRequest unclaimBleDeviceRequest = null;
            final t t = null;
            final z z = null;
            final DataSourcesRequest dataSourcesRequest = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    DataSourcesRequest dataSourcesRequest2 = dataSourcesRequest;
                    if (parcel.readInt() != 0) {
                        dataSourcesRequest2 = (DataSourcesRequest)DataSourcesRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(dataSourcesRequest2, km.a.aq(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    n n4 = n3;
                    if (parcel.readInt() != 0) {
                        n4 = (n)n.CREATOR.createFromParcel(parcel);
                    }
                    this.a(n4, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    p p5 = p4;
                    if (parcel.readInt() != 0) {
                        p5 = (p)p.CREATOR.createFromParcel(parcel);
                    }
                    this.a(p5, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    ae ae2 = ae;
                    if (parcel.readInt() != 0) {
                        ae2 = (ae)com.google.android.gms.fitness.request.ae.CREATOR.createFromParcel(parcel);
                    }
                    this.a(ae2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    ah ah2 = ah;
                    if (parcel.readInt() != 0) {
                        ah2 = (ah)com.google.android.gms.fitness.request.ah.CREATOR.createFromParcel(parcel);
                    }
                    this.a(ah2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    l j = l;
                    if (parcel.readInt() != 0) {
                        j = (l)com.google.android.gms.fitness.request.l.CREATOR.createFromParcel(parcel);
                    }
                    this.a(j, kp.a.at(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    DataInsertRequest dataInsertRequest2 = dataInsertRequest;
                    if (parcel.readInt() != 0) {
                        dataInsertRequest2 = (DataInsertRequest)DataInsertRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(dataInsertRequest2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    DataDeleteRequest dataDeleteRequest2 = dataDeleteRequest;
                    if (parcel.readInt() != 0) {
                        dataDeleteRequest2 = (DataDeleteRequest)DataDeleteRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(dataDeleteRequest2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    DataTypeCreateRequest dataTypeCreateRequest2 = dataTypeCreateRequest;
                    if (parcel.readInt() != 0) {
                        dataTypeCreateRequest2 = (DataTypeCreateRequest)DataTypeCreateRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(dataTypeCreateRequest2, kn.a.ar(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    i k = i;
                    if (parcel.readInt() != 0) {
                        k = (i)com.google.android.gms.fitness.request.i.CREATOR.createFromParcel(parcel);
                    }
                    this.a(k, kn.a.ar(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    DataReadRequest dataReadRequest2 = dataReadRequest;
                    if (parcel.readInt() != 0) {
                        dataReadRequest2 = (DataReadRequest)DataReadRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(dataReadRequest2, kl.a.ap(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    SessionInsertRequest sessionInsertRequest2 = sessionInsertRequest;
                    if (parcel.readInt() != 0) {
                        sessionInsertRequest2 = (SessionInsertRequest)SessionInsertRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(sessionInsertRequest2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    SessionReadRequest sessionReadRequest2 = sessionReadRequest;
                    if (parcel.readInt() != 0) {
                        sessionReadRequest2 = (SessionReadRequest)SessionReadRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(sessionReadRequest2, kq.a.au(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    v v2 = v;
                    if (parcel.readInt() != 0) {
                        v2 = (v)com.google.android.gms.fitness.request.v.CREATOR.createFromParcel(parcel);
                    }
                    this.a(v2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    x x2 = x;
                    if (parcel.readInt() != 0) {
                        x2 = (x)com.google.android.gms.fitness.request.x.CREATOR.createFromParcel(parcel);
                    }
                    this.a(x2, kr.a.av(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    StartBleScanRequest startBleScanRequest2 = startBleScanRequest;
                    if (parcel.readInt() != 0) {
                        startBleScanRequest2 = (StartBleScanRequest)StartBleScanRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(startBleScanRequest2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    ac ac2 = ac;
                    if (parcel.readInt() != 0) {
                        ac2 = (ac)com.google.android.gms.fitness.request.ac.CREATOR.createFromParcel(parcel);
                    }
                    this.a(ac2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    b b2 = b;
                    if (parcel.readInt() != 0) {
                        b2 = (b)com.google.android.gms.fitness.request.b.CREATOR.createFromParcel(parcel);
                    }
                    this.a(b2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    UnclaimBleDeviceRequest unclaimBleDeviceRequest2 = unclaimBleDeviceRequest;
                    if (parcel.readInt() != 0) {
                        unclaimBleDeviceRequest2 = (UnclaimBleDeviceRequest)UnclaimBleDeviceRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(unclaimBleDeviceRequest2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    t t2 = t;
                    if (parcel.readInt() != 0) {
                        t2 = (t)com.google.android.gms.fitness.request.t.CREATOR.createFromParcel(parcel);
                    }
                    this.a(t2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    z z2 = z;
                    if (parcel.readInt() != 0) {
                        z2 = (z)com.google.android.gms.fitness.request.z.CREATOR.createFromParcel(parcel);
                    }
                    this.a(z2, ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 22: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    this.a(ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 23: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    this.b(ks.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
                case 24: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    this.a(le.a.ax(parcel.readStrongBinder()), parcel.readString());
                    return true;
                }
            }
        }
        
        private static class a implements ko
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final DataDeleteRequest dataDeleteRequest, final ks ks, final String s) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                            if (dataDeleteRequest != null) {
                                obtain.writeInt(1);
                                dataDeleteRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (ks != null) {
                                final IBinder binder = ks.asBinder();
                                obtain.writeStrongBinder(binder);
                                obtain.writeString(s);
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
            public void a(final DataInsertRequest dataInsertRequest, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (dataInsertRequest != null) {
                        obtain.writeInt(1);
                        dataInsertRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(7, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final DataReadRequest dataReadRequest, final kl kl, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (dataReadRequest != null) {
                        obtain.writeInt(1);
                        dataReadRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (kl != null) {
                        binder2 = kl.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(8, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final DataSourcesRequest dataSourcesRequest, final km km, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (dataSourcesRequest != null) {
                        obtain.writeInt(1);
                        dataSourcesRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (km != null) {
                        binder2 = km.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final DataTypeCreateRequest dataTypeCreateRequest, final kn kn, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (dataTypeCreateRequest != null) {
                        obtain.writeInt(1);
                        dataTypeCreateRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (kn != null) {
                        binder2 = kn.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(13, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final SessionInsertRequest sessionInsertRequest, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (sessionInsertRequest != null) {
                        obtain.writeInt(1);
                        sessionInsertRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(9, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final SessionReadRequest sessionReadRequest, final kq kq, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (sessionReadRequest != null) {
                        obtain.writeInt(1);
                        sessionReadRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (kq != null) {
                        binder2 = kq.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(10, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final StartBleScanRequest startBleScanRequest, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (startBleScanRequest != null) {
                        obtain.writeInt(1);
                        startBleScanRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(15, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final UnclaimBleDeviceRequest unclaimBleDeviceRequest, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (unclaimBleDeviceRequest != null) {
                        obtain.writeInt(1);
                        unclaimBleDeviceRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(18, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ac ac, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (ac != null) {
                        obtain.writeInt(1);
                        ac.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(16, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ae ae, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (ae != null) {
                        obtain.writeInt(1);
                        ae.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(4, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ah ah, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (ah != null) {
                        obtain.writeInt(1);
                        ah.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(5, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final b b, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (b != null) {
                        obtain.writeInt(1);
                        b.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(17, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final i i, final kn kn, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (i != null) {
                        obtain.writeInt(1);
                        i.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (kn != null) {
                        binder2 = kn.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(14, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final l l, final kp kp, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (l != null) {
                        obtain.writeInt(1);
                        l.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (kp != null) {
                        binder2 = kp.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(6, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final n n, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (n != null) {
                        obtain.writeInt(1);
                        n.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(2, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final p p3, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (p3 != null) {
                        obtain.writeInt(1);
                        p3.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(3, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final t t, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (t != null) {
                        obtain.writeInt(1);
                        t.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(20, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final v v, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (v != null) {
                        obtain.writeInt(1);
                        v.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(11, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final x x, final kr kr, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (x != null) {
                        obtain.writeInt(1);
                        x.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (kr != null) {
                        binder2 = kr.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(12, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final z z, final ks ks, final String s) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (z != null) {
                        obtain.writeInt(1);
                        z.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    IBinder binder2 = binder;
                    if (ks != null) {
                        binder2 = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    this.lb.transact(21, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ks ks, final String s) throws RemoteException {
                IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (ks != null) {
                        binder = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.lb.transact(22, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final le le, final String s) throws RemoteException {
                IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (le != null) {
                        binder = le.asBinder();
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.lb.transact(24, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void b(final ks ks, final String s) throws RemoteException {
                IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (ks != null) {
                        binder = ks.asBinder();
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.lb.transact(23, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
