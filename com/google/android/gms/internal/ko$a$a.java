// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

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
import android.os.Parcel;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import android.os.IBinder;

class ko$a$a implements ko
{
    private IBinder lb;
    
    ko$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final DataDeleteRequest dataDeleteRequest, final ks ks, final String s) {
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
    public void a(final DataInsertRequest dataInsertRequest, final ks ks, final String s) {
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
    public void a(final DataReadRequest dataReadRequest, final kl kl, final String s) {
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
    public void a(final DataSourcesRequest dataSourcesRequest, final km km, final String s) {
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
    public void a(final DataTypeCreateRequest dataTypeCreateRequest, final kn kn, final String s) {
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
    public void a(final SessionInsertRequest sessionInsertRequest, final ks ks, final String s) {
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
    public void a(final SessionReadRequest sessionReadRequest, final kq kq, final String s) {
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
    public void a(final StartBleScanRequest startBleScanRequest, final ks ks, final String s) {
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
    public void a(final UnclaimBleDeviceRequest unclaimBleDeviceRequest, final ks ks, final String s) {
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
    public void a(final ac ac, final ks ks, final String s) {
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
    public void a(final ae ae, final ks ks, final String s) {
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
    public void a(final ah ah, final ks ks, final String s) {
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
    public void a(final b b, final ks ks, final String s) {
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
    public void a(final i i, final kn kn, final String s) {
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
    public void a(final l l, final kp kp, final String s) {
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
    public void a(final n n, final ks ks, final String s) {
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
    public void a(final p p3, final ks ks, final String s) {
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
    public void a(final t t, final ks ks, final String s) {
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
    public void a(final v v, final ks ks, final String s) {
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
    public void a(final x x, final kr kr, final String s) {
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
    public void a(final z z, final ks ks, final String s) {
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
    public void a(final ks ks, final String s) {
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
    public void a(final le le, final String s) {
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
    public void b(final ks ks, final String s) {
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
