// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import com.google.android.gms.dynamic.d$a;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class c$a extends Binder implements c
{
    public static c br(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof c) {
            return (c)queryLocalInterface;
        }
        return new c$a$a(binder);
    }
    
    public boolean onTransact(int hashCodeRemote, final Parcel parcel, final Parcel parcel2, final int n) {
        final LatLngBounds latLngBounds = null;
        LatLng cm = null;
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
                    cm = LatLng.CREATOR.cM(parcel);
                }
                this.setPosition(cm);
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
                LatLngBounds cl = latLngBounds;
                if (parcel.readInt() != 0) {
                    cl = LatLngBounds.CREATOR.cL(parcel);
                }
                this.setPositionFromBounds(cl);
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
                final boolean a = this.a(br(parcel.readStrongBinder()));
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
                this.m(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
