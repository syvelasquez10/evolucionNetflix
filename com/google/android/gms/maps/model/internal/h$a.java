// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class h$a extends Binder implements h
{
    public static h bx(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof h) {
            return (h)queryLocalInterface;
        }
        return new h$a$a(binder);
    }
    
    public boolean onTransact(int hashCodeRemote, final Parcel parcel, final Parcel parcel2, final int n) {
        final int n2 = 0;
        final int n3 = 0;
        final boolean b = false;
        final int n4 = 0;
        boolean visible = false;
        switch (hashCodeRemote) {
            default: {
                return super.onTransact(hashCodeRemote, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                this.remove();
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                this.clearTileCache();
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                final String id = this.getId();
                parcel2.writeNoException();
                parcel2.writeString(id);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                this.setZIndex(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                final float zIndex = this.getZIndex();
                parcel2.writeNoException();
                parcel2.writeFloat(zIndex);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                if (parcel.readInt() != 0) {
                    visible = true;
                }
                this.setVisible(visible);
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                final boolean visible2 = this.isVisible();
                parcel2.writeNoException();
                hashCodeRemote = n2;
                if (visible2) {
                    hashCodeRemote = 1;
                }
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                final boolean a = this.a(bx(parcel.readStrongBinder()));
                parcel2.writeNoException();
                hashCodeRemote = n3;
                if (a) {
                    hashCodeRemote = 1;
                }
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                hashCodeRemote = this.hashCodeRemote();
                parcel2.writeNoException();
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                boolean fadeIn = b;
                if (parcel.readInt() != 0) {
                    fadeIn = true;
                }
                this.setFadeIn(fadeIn);
                parcel2.writeNoException();
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
                final boolean fadeIn2 = this.getFadeIn();
                parcel2.writeNoException();
                hashCodeRemote = n4;
                if (fadeIn2) {
                    hashCodeRemote = 1;
                }
                parcel2.writeInt(hashCodeRemote);
                return true;
            }
        }
    }
}
