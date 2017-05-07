// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.CameraPosition;
import android.os.IBinder;

class ICameraUpdateFactoryDelegate$a$a implements ICameraUpdateFactoryDelegate
{
    private IBinder lb;
    
    ICameraUpdateFactoryDelegate$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public d newCameraPosition(final CameraPosition cameraPosition) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (cameraPosition != null) {
                obtain.writeInt(1);
                cameraPosition.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d newLatLng(final LatLng latLng) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (latLng != null) {
                obtain.writeInt(1);
                latLng.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(8, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d newLatLngBounds(final LatLngBounds latLngBounds, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (latLngBounds != null) {
                obtain.writeInt(1);
                latLngBounds.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            obtain.writeInt(n);
            this.lb.transact(10, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d newLatLngBoundsWithSize(final LatLngBounds latLngBounds, final int n, final int n2, final int n3) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (latLngBounds != null) {
                obtain.writeInt(1);
                latLngBounds.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeInt(n3);
            this.lb.transact(11, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d newLatLngZoom(final LatLng latLng, final float n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (latLng != null) {
                obtain.writeInt(1);
                latLng.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            obtain.writeFloat(n);
            this.lb.transact(9, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d scrollBy(final float n, final float n2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            obtain.writeFloat(n);
            obtain.writeFloat(n2);
            this.lb.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d zoomBy(final float n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            obtain.writeFloat(n);
            this.lb.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d zoomByWithFocus(final float n, final int n2, final int n3) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            obtain.writeFloat(n);
            obtain.writeInt(n2);
            obtain.writeInt(n3);
            this.lb.transact(6, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d zoomIn() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d zoomOut() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            this.lb.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d zoomTo(final float n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            obtain.writeFloat(n);
            this.lb.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
