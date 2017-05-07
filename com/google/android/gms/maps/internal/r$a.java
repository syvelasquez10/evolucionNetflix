// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class r$a extends Binder implements r
{
    public r$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
    }
    
    public static r bi(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
        if (queryLocalInterface != null && queryLocalInterface instanceof r) {
            return (r)queryLocalInterface;
        }
        return new r$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
                StreetViewPanoramaOrientation ct;
                if (parcel.readInt() != 0) {
                    ct = StreetViewPanoramaOrientation.CREATOR.cT(parcel);
                }
                else {
                    ct = null;
                }
                this.onStreetViewPanoramaClick(ct);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
