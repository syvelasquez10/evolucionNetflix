// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.d;
import android.os.Bundle;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class IStreetViewPanoramaFragmentDelegate$a extends Binder implements IStreetViewPanoramaFragmentDelegate
{
    public static IStreetViewPanoramaFragmentDelegate bm(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof IStreetViewPanoramaFragmentDelegate) {
            return (IStreetViewPanoramaFragmentDelegate)queryLocalInterface;
        }
        return new IStreetViewPanoramaFragmentDelegate$a$a(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final IBinder binder = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                final IStreetViewPanoramaDelegate streetViewPanorama = this.getStreetViewPanorama();
                parcel2.writeNoException();
                IBinder binder2;
                if (streetViewPanorama != null) {
                    binder2 = streetViewPanorama.asBinder();
                }
                else {
                    binder2 = null;
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                final d am = d$a.am(parcel.readStrongBinder());
                StreetViewPanoramaOptions ch;
                if (parcel.readInt() != 0) {
                    ch = StreetViewPanoramaOptions.CREATOR.cH(parcel);
                }
                else {
                    ch = null;
                }
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.onInflate(am, ch, bundle);
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                Bundle bundle2;
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle2 = null;
                }
                this.onCreate(bundle2);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                final d am2 = d$a.am(parcel.readStrongBinder());
                final d am3 = d$a.am(parcel.readStrongBinder());
                Bundle bundle3;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle3 = null;
                }
                final d onCreateView = this.onCreateView(am2, am3, bundle3);
                parcel2.writeNoException();
                IBinder binder3 = binder;
                if (onCreateView != null) {
                    binder3 = onCreateView.asBinder();
                }
                parcel2.writeStrongBinder(binder3);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                this.onResume();
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                this.onPause();
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                this.onDestroyView();
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                this.onDestroy();
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                this.onLowMemory();
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                Bundle bundle4;
                if (parcel.readInt() != 0) {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle4 = null;
                }
                this.onSaveInstanceState(bundle4);
                parcel2.writeNoException();
                if (bundle4 != null) {
                    parcel2.writeInt(1);
                    bundle4.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
                final boolean ready = this.isReady();
                parcel2.writeNoException();
                if (ready) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                parcel2.writeInt(n);
                return true;
            }
        }
    }
}
