// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.common.data.DataHolder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;

public interface ad extends IInterface
{
    void a(final Status p0) throws RemoteException;
    
    void a(final ab p0) throws RemoteException;
    
    void a(final ao p0) throws RemoteException;
    
    void a(final as p0) throws RemoteException;
    
    void a(final au p0) throws RemoteException;
    
    void a(final p p0) throws RemoteException;
    
    void a(final r p0) throws RemoteException;
    
    void a(final t p0) throws RemoteException;
    
    void a(final v p0) throws RemoteException;
    
    void a(final x p0) throws RemoteException;
    
    void a(final z p0) throws RemoteException;
    
    void aa(final DataHolder p0) throws RemoteException;
    
    public abstract static class a extends Binder implements ad
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.wearable.internal.IWearableCallbacks");
        }
        
        public static ad bR(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof ad) {
                return (ad)queryLocalInterface;
            }
            return new ad.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final t t = null;
            final ao ao = null;
            final x x = null;
            final DataHolder dataHolder = null;
            final p p4 = null;
            final as as = null;
            final z z = null;
            final ab ab = null;
            final v v = null;
            final Status status = null;
            final au au = null;
            r r = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (parcel.readInt() != 0) {
                        r = (r)com.google.android.gms.wearable.internal.r.CREATOR.createFromParcel(parcel);
                    }
                    this.a(r);
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    t t2 = t;
                    if (parcel.readInt() != 0) {
                        t2 = (t)com.google.android.gms.wearable.internal.t.CREATOR.createFromParcel(parcel);
                    }
                    this.a(t2);
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    ao ao2 = ao;
                    if (parcel.readInt() != 0) {
                        ao2 = (ao)com.google.android.gms.wearable.internal.ao.CREATOR.createFromParcel(parcel);
                    }
                    this.a(ao2);
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    x x2 = x;
                    if (parcel.readInt() != 0) {
                        x2 = (x)com.google.android.gms.wearable.internal.x.CREATOR.createFromParcel(parcel);
                    }
                    this.a(x2);
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    DataHolder z2 = dataHolder;
                    if (parcel.readInt() != 0) {
                        z2 = DataHolder.CREATOR.z(parcel);
                    }
                    this.aa(z2);
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    p p5 = p4;
                    if (parcel.readInt() != 0) {
                        p5 = (p)p.CREATOR.createFromParcel(parcel);
                    }
                    this.a(p5);
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    as as2 = as;
                    if (parcel.readInt() != 0) {
                        as2 = (as)com.google.android.gms.wearable.internal.as.CREATOR.createFromParcel(parcel);
                    }
                    this.a(as2);
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    z z3 = z;
                    if (parcel.readInt() != 0) {
                        z3 = (z)com.google.android.gms.wearable.internal.z.CREATOR.createFromParcel(parcel);
                    }
                    this.a(z3);
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    ab ab2 = ab;
                    if (parcel.readInt() != 0) {
                        ab2 = (ab)com.google.android.gms.wearable.internal.ab.CREATOR.createFromParcel(parcel);
                    }
                    this.a(ab2);
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    v v2 = v;
                    if (parcel.readInt() != 0) {
                        v2 = (v)com.google.android.gms.wearable.internal.v.CREATOR.createFromParcel(parcel);
                    }
                    this.a(v2);
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    Status fromParcel = status;
                    if (parcel.readInt() != 0) {
                        fromParcel = Status.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel);
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    au au2 = au;
                    if (parcel.readInt() != 0) {
                        au2 = (au)com.google.android.gms.wearable.internal.au.CREATOR.createFromParcel(parcel);
                    }
                    this.a(au2);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements ad
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final Status status) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ab ab) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (ab != null) {
                        obtain.writeInt(1);
                        ab.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ao ao) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (ao != null) {
                        obtain.writeInt(1);
                        ao.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final as as) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (as != null) {
                        obtain.writeInt(1);
                        as.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final au au) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (au != null) {
                        obtain.writeInt(1);
                        au.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final p p) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (p != null) {
                        obtain.writeInt(1);
                        p.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final r r) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (r != null) {
                        obtain.writeInt(1);
                        r.writeToParcel(obtain, 0);
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
            
            @Override
            public void a(final t t) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (t != null) {
                        obtain.writeInt(1);
                        t.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final v v) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (v != null) {
                        obtain.writeInt(1);
                        v.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final x x) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (x != null) {
                        obtain.writeInt(1);
                        x.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final z z) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (z != null) {
                        obtain.writeInt(1);
                        z.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void aa(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(5, obtain, obtain2, 0);
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
        }
    }
}
