// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import com.google.android.gms.dynamic.d$a;
import com.google.android.gms.maps.model.LatLng;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class f$a extends Binder implements f
{
    public static f bu(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof f) {
            return (f)queryLocalInterface;
        }
        return new f$a$a(binder);
    }
    
    public boolean onTransact(int hashCodeRemote, final Parcel parcel, final Parcel parcel2, final int n) {
        final int n2 = 0;
        final int n3 = 0;
        final boolean b = false;
        final int n4 = 0;
        final int n5 = 0;
        final boolean b2 = false;
        final int n6 = 0;
        boolean draggable = false;
        switch (hashCodeRemote) {
            default: {
                return super.onTransact(hashCodeRemote, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.remove();
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final String id = this.getId();
                parcel2.writeNoException();
                parcel2.writeString(id);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                LatLng cm;
                if (parcel.readInt() != 0) {
                    cm = LatLng.CREATOR.cM(parcel);
                }
                else {
                    cm = null;
                }
                this.setPosition(cm);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setTitle(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final String title = this.getTitle();
                parcel2.writeNoException();
                parcel2.writeString(title);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setSnippet(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final String snippet = this.getSnippet();
                parcel2.writeNoException();
                parcel2.writeString(snippet);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                if (parcel.readInt() != 0) {
                    draggable = true;
                }
                this.setDraggable(draggable);
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final boolean draggable2 = this.isDraggable();
                parcel2.writeNoException();
                hashCodeRemote = n2;
                if (draggable2) {
                    hashCodeRemote = 1;
                }
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.showInfoWindow();
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.hideInfoWindow();
                parcel2.writeNoException();
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final boolean infoWindowShown = this.isInfoWindowShown();
                parcel2.writeNoException();
                hashCodeRemote = n3;
                if (infoWindowShown) {
                    hashCodeRemote = 1;
                }
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                boolean visible = b;
                if (parcel.readInt() != 0) {
                    visible = true;
                }
                this.setVisible(visible);
                parcel2.writeNoException();
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final boolean visible2 = this.isVisible();
                parcel2.writeNoException();
                hashCodeRemote = n4;
                if (visible2) {
                    hashCodeRemote = 1;
                }
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final boolean h = this.h(bu(parcel.readStrongBinder()));
                parcel2.writeNoException();
                hashCodeRemote = n5;
                if (h) {
                    hashCodeRemote = 1;
                }
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                hashCodeRemote = this.hashCodeRemote();
                parcel2.writeNoException();
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.n(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 19: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setAnchor(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 20: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                boolean flat = b2;
                if (parcel.readInt() != 0) {
                    flat = true;
                }
                this.setFlat(flat);
                parcel2.writeNoException();
                return true;
            }
            case 21: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final boolean flat2 = this.isFlat();
                parcel2.writeNoException();
                hashCodeRemote = n6;
                if (flat2) {
                    hashCodeRemote = 1;
                }
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 22: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setRotation(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 23: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final float rotation = this.getRotation();
                parcel2.writeNoException();
                parcel2.writeFloat(rotation);
                return true;
            }
            case 24: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setInfoWindowAnchor(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 25: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                this.setAlpha(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 26: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                final float alpha = this.getAlpha();
                parcel2.writeNoException();
                parcel2.writeFloat(alpha);
                return true;
            }
        }
    }
}
