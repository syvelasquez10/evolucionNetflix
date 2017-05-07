// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.d;
import android.os.Bundle;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class IMapViewDelegate$a extends Binder implements IMapViewDelegate
{
    public static IMapViewDelegate aU(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof IMapViewDelegate) {
            return (IMapViewDelegate)queryLocalInterface;
        }
        return new IMapViewDelegate$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final Bundle bundle = null;
        final Bundle bundle2 = null;
        final IBinder binder = null;
        final IBinder binder2 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.IMapViewDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                final IGoogleMapDelegate map = this.getMap();
                parcel2.writeNoException();
                IBinder binder3 = binder2;
                if (map != null) {
                    binder3 = map.asBinder();
                }
                parcel2.writeStrongBinder(binder3);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                Bundle bundle3 = bundle;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.onCreate(bundle3);
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.onResume();
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.onPause();
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.onDestroy();
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                this.onLowMemory();
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                Bundle bundle4 = bundle2;
                if (parcel.readInt() != 0) {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.onSaveInstanceState(bundle4);
                parcel2.writeNoException();
                if (bundle4 != null) {
                    parcel2.writeInt(1);
                    bundle4.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
                final d view = this.getView();
                parcel2.writeNoException();
                IBinder binder4 = binder;
                if (view != null) {
                    binder4 = view.asBinder();
                }
                parcel2.writeStrongBinder(binder4);
                return true;
            }
        }
    }
}
