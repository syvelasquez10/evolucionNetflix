// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class q$a extends Binder implements q
{
    public q$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
    }
    
    public static q bh(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof q) {
            return (q)queryLocalInterface;
        }
        return new q$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                StreetViewPanoramaLocation cs;
                if (parcel.readInt() != 0) {
                    cs = StreetViewPanoramaLocation.CREATOR.cS(parcel);
                }
                else {
                    cs = null;
                }
                this.onStreetViewPanoramaChange(cs);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
