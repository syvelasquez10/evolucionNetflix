// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.internal.a;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class c$a extends Binder implements c
{
    public static c aP(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
        if (queryLocalInterface != null && queryLocalInterface instanceof c) {
            return (c)queryLocalInterface;
        }
        return new c$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final IBinder binder = null;
        final IBinder binder2 = null;
        final IBinder binder3 = null;
        final IBinder binder4 = null;
        final IBinder binder5 = null;
        final IBinder binder6 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.ICreator");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                this.i(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                final IMapFragmentDelegate j = this.j(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                IBinder binder7 = binder6;
                if (j != null) {
                    binder7 = j.asBinder();
                }
                parcel2.writeStrongBinder(binder7);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                final d am = d$a.am(parcel.readStrongBinder());
                GoogleMapOptions cg;
                if (parcel.readInt() != 0) {
                    cg = GoogleMapOptions.CREATOR.cG(parcel);
                }
                else {
                    cg = null;
                }
                final IMapViewDelegate a = this.a(am, cg);
                parcel2.writeNoException();
                IBinder binder8 = binder;
                if (a != null) {
                    binder8 = a.asBinder();
                }
                parcel2.writeStrongBinder(binder8);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                final ICameraUpdateFactoryDelegate mg = this.mG();
                parcel2.writeNoException();
                IBinder binder9 = binder2;
                if (mg != null) {
                    binder9 = mg.asBinder();
                }
                parcel2.writeStrongBinder(binder9);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                final a mh = this.mH();
                parcel2.writeNoException();
                IBinder binder10 = binder3;
                if (mh != null) {
                    binder10 = mh.asBinder();
                }
                parcel2.writeStrongBinder(binder10);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                this.a(d$a.am(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                final d am2 = d$a.am(parcel.readStrongBinder());
                StreetViewPanoramaOptions ch;
                if (parcel.readInt() != 0) {
                    ch = StreetViewPanoramaOptions.CREATOR.cH(parcel);
                }
                else {
                    ch = null;
                }
                final IStreetViewPanoramaViewDelegate a2 = this.a(am2, ch);
                parcel2.writeNoException();
                IBinder binder11 = binder4;
                if (a2 != null) {
                    binder11 = a2.asBinder();
                }
                parcel2.writeStrongBinder(binder11);
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                final IStreetViewPanoramaFragmentDelegate k = this.k(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                IBinder binder12 = binder5;
                if (k != null) {
                    binder12 = k.asBinder();
                }
                parcel2.writeStrongBinder(binder12);
                return true;
            }
        }
    }
}
