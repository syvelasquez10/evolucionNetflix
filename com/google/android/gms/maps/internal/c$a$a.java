// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.internal.a$a;
import com.google.android.gms.maps.model.internal.a;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import android.os.Parcel;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;

class c$a$a implements c
{
    private IBinder lb;
    
    c$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public IMapViewDelegate a(final d d, final GoogleMapOptions googleMapOptions) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            IBinder binder;
            if (d != null) {
                binder = d.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (googleMapOptions != null) {
                obtain.writeInt(1);
                googleMapOptions.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return IMapViewDelegate$a.aU(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public IStreetViewPanoramaViewDelegate a(final d d, final StreetViewPanoramaOptions streetViewPanoramaOptions) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            IBinder binder;
            if (d != null) {
                binder = d.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (streetViewPanoramaOptions != null) {
                obtain.writeInt(1);
                streetViewPanoramaOptions.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            return IStreetViewPanoramaViewDelegate$a.bn(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final d d, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            IBinder binder;
            if (d != null) {
                binder = d.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            this.lb.transact(6, obtain, obtain2, 0);
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
    public void i(final d d) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            IBinder binder;
            if (d != null) {
                binder = d.asBinder();
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
    public IMapFragmentDelegate j(final d d) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            IBinder binder;
            if (d != null) {
                binder = d.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return IMapFragmentDelegate$a.aT(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public IStreetViewPanoramaFragmentDelegate k(final d d) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            IBinder binder;
            if (d != null) {
                binder = d.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(8, obtain, obtain2, 0);
            obtain2.readException();
            return IStreetViewPanoramaFragmentDelegate$a.bm(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public ICameraUpdateFactoryDelegate mG() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            this.lb.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            return ICameraUpdateFactoryDelegate$a.aN(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public a mH() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
            this.lb.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            return a$a.bp(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
