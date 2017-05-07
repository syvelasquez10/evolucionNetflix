// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.maps.model.LatLng;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class g$a extends Binder implements g
{
    public static g bv(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof g) {
            return (g)queryLocalInterface;
        }
        return new g$a$a(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final int n3 = 0;
        final boolean b = false;
        final int n4 = 0;
        final int n5 = 0;
        boolean visible = false;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                this.remove();
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                final String id = this.getId();
                parcel2.writeNoException();
                parcel2.writeString(id);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                this.setPoints(parcel.createTypedArrayList((Parcelable$Creator)LatLng.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                final List<LatLng> points = this.getPoints();
                parcel2.writeNoException();
                parcel2.writeTypedList((List)points);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                this.setHoles(parcel.readArrayList(this.getClass().getClassLoader()));
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                final List holes = this.getHoles();
                parcel2.writeNoException();
                parcel2.writeList(holes);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                this.setStrokeWidth(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                final float strokeWidth = this.getStrokeWidth();
                parcel2.writeNoException();
                parcel2.writeFloat(strokeWidth);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                this.setStrokeColor(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                n = this.getStrokeColor();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                this.setFillColor(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                n = this.getFillColor();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                this.setZIndex(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                final float zIndex = this.getZIndex();
                parcel2.writeNoException();
                parcel2.writeFloat(zIndex);
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                if (parcel.readInt() != 0) {
                    visible = true;
                }
                this.setVisible(visible);
                parcel2.writeNoException();
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
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
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                boolean geodesic = b;
                if (parcel.readInt() != 0) {
                    geodesic = true;
                }
                this.setGeodesic(geodesic);
                parcel2.writeNoException();
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                final boolean geodesic2 = this.isGeodesic();
                parcel2.writeNoException();
                n = n4;
                if (geodesic2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 19: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                final boolean a = this.a(bv(parcel.readStrongBinder()));
                parcel2.writeNoException();
                n = n5;
                if (a) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 20: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IPolygonDelegate");
                n = this.hashCodeRemote();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
        }
    }
}
