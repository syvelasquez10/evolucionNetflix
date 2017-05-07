// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class p$a extends Binder implements p
{
    public p$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
    }
    
    public static p bg(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof p) {
            return (p)queryLocalInterface;
        }
        return new p$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
                StreetViewPanoramaCamera cq;
                if (parcel.readInt() != 0) {
                    cq = StreetViewPanoramaCamera.CREATOR.cQ(parcel);
                }
                else {
                    cq = null;
                }
                this.onStreetViewPanoramaCameraChange(cq);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
