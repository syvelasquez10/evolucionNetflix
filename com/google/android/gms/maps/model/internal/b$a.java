// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import com.google.android.gms.maps.model.LatLng;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class b$a extends Binder implements b
{
    public static b bq(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof b) {
            return (b)queryLocalInterface;
        }
        return new b$a$a(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final int n3 = 0;
        final int n4 = 0;
        boolean visible = false;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.ICircleDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.remove();
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final String id = this.getId();
                parcel2.writeNoException();
                parcel2.writeString(id);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                LatLng cm;
                if (parcel.readInt() != 0) {
                    cm = LatLng.CREATOR.cM(parcel);
                }
                else {
                    cm = null;
                }
                this.setCenter(cm);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final LatLng center = this.getCenter();
                parcel2.writeNoException();
                if (center != null) {
                    parcel2.writeInt(1);
                    center.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.setRadius(parcel.readDouble());
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final double radius = this.getRadius();
                parcel2.writeNoException();
                parcel2.writeDouble(radius);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.setStrokeWidth(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final float strokeWidth = this.getStrokeWidth();
                parcel2.writeNoException();
                parcel2.writeFloat(strokeWidth);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.setStrokeColor(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                n = this.getStrokeColor();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.setFillColor(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                n = this.getFillColor();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                this.setZIndex(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final float zIndex = this.getZIndex();
                parcel2.writeNoException();
                parcel2.writeFloat(zIndex);
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                if (parcel.readInt() != 0) {
                    visible = true;
                }
                this.setVisible(visible);
                parcel2.writeNoException();
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final boolean visible2 = this.isVisible();
                parcel2.writeNoException();
                n = n3;
                if (visible2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                final boolean a = this.a(bq(parcel.readStrongBinder()));
                parcel2.writeNoException();
                n = n4;
                if (a) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
                n = this.hashCodeRemote();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
        }
    }
}
