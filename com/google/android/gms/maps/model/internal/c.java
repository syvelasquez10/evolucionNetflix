// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import android.os.RemoteException;
import android.os.IInterface;

public interface c extends IInterface
{
    void a(final float p0, final float p1) throws RemoteException;
    
    boolean a(final c p0) throws RemoteException;
    
    float getBearing() throws RemoteException;
    
    LatLngBounds getBounds() throws RemoteException;
    
    float getHeight() throws RemoteException;
    
    String getId() throws RemoteException;
    
    LatLng getPosition() throws RemoteException;
    
    float getTransparency() throws RemoteException;
    
    float getWidth() throws RemoteException;
    
    float getZIndex() throws RemoteException;
    
    int hashCodeRemote() throws RemoteException;
    
    boolean isVisible() throws RemoteException;
    
    void k(final d p0) throws RemoteException;
    
    void remove() throws RemoteException;
    
    void setBearing(final float p0) throws RemoteException;
    
    void setDimensions(final float p0) throws RemoteException;
    
    void setPosition(final LatLng p0) throws RemoteException;
    
    void setPositionFromBounds(final LatLngBounds p0) throws RemoteException;
    
    void setTransparency(final float p0) throws RemoteException;
    
    void setVisible(final boolean p0) throws RemoteException;
    
    void setZIndex(final float p0) throws RemoteException;
    
    public abstract static class a extends Binder implements c
    {
        public static c aD(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof c) {
                return (c)queryLocalInterface;
            }
            return new c.a.a(binder);
        }
        
        public boolean onTransact(int hashCodeRemote, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
            final LatLngBounds latLngBounds = null;
            LatLng fromParcel = null;
            final int n2 = 0;
            final int n3 = 0;
            switch (hashCodeRemote) {
                default: {
                    return super.onTransact(hashCodeRemote, parcel, parcel2, n);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.remove();
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    final String id = this.getId();
                    parcel2.writeNoException();
                    parcel2.writeString(id);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    if (parcel.readInt() != 0) {
                        fromParcel = LatLng.CREATOR.createFromParcel(parcel);
                    }
                    this.setPosition(fromParcel);
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    final LatLng position = this.getPosition();
                    parcel2.writeNoException();
                    if (position != null) {
                        parcel2.writeInt(1);
                        position.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.setDimensions(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.a(parcel.readFloat(), parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    final float width = this.getWidth();
                    parcel2.writeNoException();
                    parcel2.writeFloat(width);
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    final float height = this.getHeight();
                    parcel2.writeNoException();
                    parcel2.writeFloat(height);
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    LatLngBounds fromParcel2 = latLngBounds;
                    if (parcel.readInt() != 0) {
                        fromParcel2 = LatLngBounds.CREATOR.createFromParcel(parcel);
                    }
                    this.setPositionFromBounds(fromParcel2);
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    final LatLngBounds bounds = this.getBounds();
                    parcel2.writeNoException();
                    if (bounds != null) {
                        parcel2.writeInt(1);
                        bounds.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.setBearing(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    final float bearing = this.getBearing();
                    parcel2.writeNoException();
                    parcel2.writeFloat(bearing);
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.setZIndex(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    final float zIndex = this.getZIndex();
                    parcel2.writeNoException();
                    parcel2.writeFloat(zIndex);
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.setVisible(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    final boolean visible = this.isVisible();
                    parcel2.writeNoException();
                    hashCodeRemote = n3;
                    if (visible) {
                        hashCodeRemote = 1;
                    }
                    parcel2.writeInt(hashCodeRemote);
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.setTransparency(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    final float transparency = this.getTransparency();
                    parcel2.writeNoException();
                    parcel2.writeFloat(transparency);
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    final boolean a = this.a(aD(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    hashCodeRemote = n2;
                    if (a) {
                        hashCodeRemote = 1;
                    }
                    parcel2.writeInt(hashCodeRemote);
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    hashCodeRemote = this.hashCodeRemote();
                    parcel2.writeNoException();
                    parcel2.writeInt(hashCodeRemote);
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.k(d.a.K(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements c
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void a(final float n, final float n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(n);
                    obtain.writeFloat(n2);
                    this.kn.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean a(final c c) throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    IBinder binder;
                    if (c != null) {
                        binder = c.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public float getBearing() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public LatLngBounds getBounds() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    LatLngBounds fromParcel;
                    if (obtain2.readInt() != 0) {
                        fromParcel = LatLngBounds.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        fromParcel = null;
                    }
                    return fromParcel;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public float getHeight() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getId() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public LatLng getPosition() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    LatLng fromParcel;
                    if (obtain2.readInt() != 0) {
                        fromParcel = LatLng.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        fromParcel = null;
                    }
                    return fromParcel;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public float getTransparency() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public float getWidth() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public float getZIndex() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int hashCodeRemote() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isVisible() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void k(final d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void remove() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    this.kn.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setBearing(final float n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(n);
                    this.kn.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setDimensions(final float n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(n);
                    this.kn.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setPosition(final LatLng latLng) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setPositionFromBounds(final LatLngBounds latLngBounds) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setTransparency(final float n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(n);
                    this.kn.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setVisible(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setZIndex(final float n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(n);
                    this.kn.transact(13, obtain, obtain2, 0);
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
