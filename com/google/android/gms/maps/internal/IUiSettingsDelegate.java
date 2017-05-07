// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface IUiSettingsDelegate extends IInterface
{
    boolean isCompassEnabled() throws RemoteException;
    
    boolean isMyLocationButtonEnabled() throws RemoteException;
    
    boolean isRotateGesturesEnabled() throws RemoteException;
    
    boolean isScrollGesturesEnabled() throws RemoteException;
    
    boolean isTiltGesturesEnabled() throws RemoteException;
    
    boolean isZoomControlsEnabled() throws RemoteException;
    
    boolean isZoomGesturesEnabled() throws RemoteException;
    
    void setAllGesturesEnabled(final boolean p0) throws RemoteException;
    
    void setCompassEnabled(final boolean p0) throws RemoteException;
    
    void setMyLocationButtonEnabled(final boolean p0) throws RemoteException;
    
    void setRotateGesturesEnabled(final boolean p0) throws RemoteException;
    
    void setScrollGesturesEnabled(final boolean p0) throws RemoteException;
    
    void setTiltGesturesEnabled(final boolean p0) throws RemoteException;
    
    void setZoomControlsEnabled(final boolean p0) throws RemoteException;
    
    void setZoomGesturesEnabled(final boolean p0) throws RemoteException;
    
    public abstract static class a extends Binder implements IUiSettingsDelegate
    {
        public static IUiSettingsDelegate ai(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof IUiSettingsDelegate) {
                return (IUiSettingsDelegate)queryLocalInterface;
            }
            return new IUiSettingsDelegate.a.a(binder);
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final boolean b = false;
            final boolean b2 = false;
            final boolean b3 = false;
            final boolean b4 = false;
            final boolean b5 = false;
            final boolean b6 = false;
            final boolean b7 = false;
            final int n3 = 0;
            final int n4 = 0;
            final int n5 = 0;
            final int n6 = 0;
            final int n7 = 0;
            final int n8 = 0;
            final int n9 = 0;
            boolean zoomControlsEnabled = false;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        zoomControlsEnabled = true;
                    }
                    this.setZoomControlsEnabled(zoomControlsEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    boolean compassEnabled = b;
                    if (parcel.readInt() != 0) {
                        compassEnabled = true;
                    }
                    this.setCompassEnabled(compassEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    boolean myLocationButtonEnabled = b2;
                    if (parcel.readInt() != 0) {
                        myLocationButtonEnabled = true;
                    }
                    this.setMyLocationButtonEnabled(myLocationButtonEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    boolean scrollGesturesEnabled = b3;
                    if (parcel.readInt() != 0) {
                        scrollGesturesEnabled = true;
                    }
                    this.setScrollGesturesEnabled(scrollGesturesEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    boolean zoomGesturesEnabled = b4;
                    if (parcel.readInt() != 0) {
                        zoomGesturesEnabled = true;
                    }
                    this.setZoomGesturesEnabled(zoomGesturesEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    boolean tiltGesturesEnabled = b5;
                    if (parcel.readInt() != 0) {
                        tiltGesturesEnabled = true;
                    }
                    this.setTiltGesturesEnabled(tiltGesturesEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    boolean rotateGesturesEnabled = b6;
                    if (parcel.readInt() != 0) {
                        rotateGesturesEnabled = true;
                    }
                    this.setRotateGesturesEnabled(rotateGesturesEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    boolean allGesturesEnabled = b7;
                    if (parcel.readInt() != 0) {
                        allGesturesEnabled = true;
                    }
                    this.setAllGesturesEnabled(allGesturesEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean zoomControlsEnabled2 = this.isZoomControlsEnabled();
                    parcel2.writeNoException();
                    n = n3;
                    if (zoomControlsEnabled2) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean compassEnabled2 = this.isCompassEnabled();
                    parcel2.writeNoException();
                    n = n4;
                    if (compassEnabled2) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean myLocationButtonEnabled2 = this.isMyLocationButtonEnabled();
                    parcel2.writeNoException();
                    n = n5;
                    if (myLocationButtonEnabled2) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean scrollGesturesEnabled2 = this.isScrollGesturesEnabled();
                    parcel2.writeNoException();
                    n = n6;
                    if (scrollGesturesEnabled2) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean zoomGesturesEnabled2 = this.isZoomGesturesEnabled();
                    parcel2.writeNoException();
                    n = n7;
                    if (zoomGesturesEnabled2) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean tiltGesturesEnabled2 = this.isTiltGesturesEnabled();
                    parcel2.writeNoException();
                    n = n8;
                    if (tiltGesturesEnabled2) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    final boolean rotateGesturesEnabled2 = this.isRotateGesturesEnabled();
                    parcel2.writeNoException();
                    n = n9;
                    if (rotateGesturesEnabled2) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
            }
        }
        
        private static class a implements IUiSettingsDelegate
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            public IBinder asBinder() {
                return this.dU;
            }
            
            @Override
            public boolean isCompassEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.dU.transact(10, obtain, obtain2, 0);
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
            public boolean isMyLocationButtonEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.dU.transact(11, obtain, obtain2, 0);
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
            public boolean isRotateGesturesEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.dU.transact(15, obtain, obtain2, 0);
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
            public boolean isScrollGesturesEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.dU.transact(12, obtain, obtain2, 0);
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
            public boolean isTiltGesturesEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.dU.transact(14, obtain, obtain2, 0);
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
            public boolean isZoomControlsEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.dU.transact(9, obtain, obtain2, 0);
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
            public boolean isZoomGesturesEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    this.dU.transact(13, obtain, obtain2, 0);
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
            public void setAllGesturesEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setCompassEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setMyLocationButtonEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setRotateGesturesEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setScrollGesturesEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setTiltGesturesEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setZoomControlsEnabled(final boolean b) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (!b) {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setZoomGesturesEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(5, obtain, obtain2, 0);
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
