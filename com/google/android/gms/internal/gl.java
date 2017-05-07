// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.location.d;
import java.util.List;
import com.google.android.gms.location.f;
import com.google.android.gms.location.a;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.location.c;
import com.google.android.gms.location.LocationRequest;
import android.location.Location;
import android.os.RemoteException;
import android.app.PendingIntent;
import android.os.IInterface;

public interface gl extends IInterface
{
    void a(final long p0, final boolean p1, final PendingIntent p2) throws RemoteException;
    
    void a(final PendingIntent p0) throws RemoteException;
    
    void a(final PendingIntent p0, final gk p1, final String p2) throws RemoteException;
    
    void a(final Location p0, final int p1) throws RemoteException;
    
    void a(final gk p0, final String p1) throws RemoteException;
    
    void a(final gt p0, final hi p1, final gz p2) throws RemoteException;
    
    void a(final gv p0, final hi p1, final PendingIntent p2) throws RemoteException;
    
    void a(final hi p0, final PendingIntent p1) throws RemoteException;
    
    void a(final LocationRequest p0, final PendingIntent p1) throws RemoteException;
    
    void a(final LocationRequest p0, final c p1) throws RemoteException;
    
    void a(final LocationRequest p0, final c p1, final String p2) throws RemoteException;
    
    void a(final c p0) throws RemoteException;
    
    void a(final LatLng p0, final gt p1, final hi p2, final gz p3) throws RemoteException;
    
    void a(final LatLngBounds p0, final int p1, final gt p2, final hi p3, final gz p4) throws RemoteException;
    
    void a(final String p0, final gh p1, final gj p2) throws RemoteException;
    
    void a(final String p0, final gj p1) throws RemoteException;
    
    void a(final String p0, final hi p1, final gz p2) throws RemoteException;
    
    void a(final String p0, final com.google.android.gms.location.a p1, final gj p2) throws RemoteException;
    
    void a(final String p0, final f p1, final gj p2) throws RemoteException;
    
    void a(final String p0, final String p1, final hi p2) throws RemoteException;
    
    void a(final String p0, final boolean p1, final gj p2) throws RemoteException;
    
    void a(final List<hd.a> p0) throws RemoteException;
    
    void a(final List<go> p0, final PendingIntent p1, final gk p2, final String p3) throws RemoteException;
    
    void a(final String[] p0, final gk p1, final String p2) throws RemoteException;
    
    Location an(final String p0) throws RemoteException;
    
    d ao(final String p0) throws RemoteException;
    
    void b(final String p0, final gj p1) throws RemoteException;
    
    void b(final String p0, final f p1, final gj p2) throws RemoteException;
    
    Location dH() throws RemoteException;
    
    void removeActivityUpdates(final PendingIntent p0) throws RemoteException;
    
    void setMockLocation(final Location p0) throws RemoteException;
    
    void setMockMode(final boolean p0) throws RemoteException;
    
    public abstract static class a extends Binder implements gl
    {
        public static gl L(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (queryLocalInterface != null && queryLocalInterface instanceof gl) {
                return (gl)queryLocalInterface;
            }
            return new gl.a.a(binder);
        }
        
        public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
            final boolean b = false;
            final boolean b2 = false;
            boolean b3 = false;
            final PendingIntent pendingIntent = null;
            final PendingIntent pendingIntent2 = null;
            final PendingIntent pendingIntent3 = null;
            final LocationRequest locationRequest = null;
            PendingIntent pendingIntent4 = null;
            final PendingIntent pendingIntent5 = null;
            final Location location = null;
            final hi hi = null;
            hi aq = null;
            final hi hi2 = null;
            final PendingIntent pendingIntent6 = null;
            final PendingIntent pendingIntent7 = null;
            final LocationRequest locationRequest2 = null;
            final hi hi3 = null;
            final Location location2 = null;
            final a a = null;
            final gh gh = null;
            final f f = null;
            final f f2 = null;
            final PendingIntent pendingIntent8 = null;
            switch (int1) {
                default: {
                    return super.onTransact(int1, parcel, parcel2, n);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final ArrayList typedArrayList = parcel.createTypedArrayList((Parcelable$Creator)go.CREATOR);
                    PendingIntent pendingIntent9 = pendingIntent8;
                    if (parcel.readInt() != 0) {
                        pendingIntent9 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    this.a(typedArrayList, pendingIntent9, gk.a.K(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    PendingIntent pendingIntent10 = pendingIntent;
                    if (parcel.readInt() != 0) {
                        pendingIntent10 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    this.a(pendingIntent10, gk.a.K(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(parcel.createStringArray(), gk.a.K(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(gk.a.K(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final long long1 = parcel.readLong();
                    if (parcel.readInt() != 0) {
                        b3 = true;
                    }
                    PendingIntent pendingIntent11 = pendingIntent2;
                    if (parcel.readInt() != 0) {
                        pendingIntent11 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    this.a(long1, b3, pendingIntent11);
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    PendingIntent pendingIntent12 = pendingIntent3;
                    if (parcel.readInt() != 0) {
                        pendingIntent12 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    this.removeActivityUpdates(pendingIntent12);
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final Location dh = this.dH();
                    parcel2.writeNoException();
                    if (dh != null) {
                        parcel2.writeInt(1);
                        dh.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LocationRequest fromParcel = locationRequest;
                    if (parcel.readInt() != 0) {
                        fromParcel = LocationRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel, c.a.I(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LocationRequest fromParcel2;
                    if (parcel.readInt() != 0) {
                        fromParcel2 = LocationRequest.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel2 = null;
                    }
                    if (parcel.readInt() != 0) {
                        pendingIntent4 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel2, pendingIntent4);
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(c.a.I(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    PendingIntent pendingIntent13 = pendingIntent5;
                    if (parcel.readInt() != 0) {
                        pendingIntent13 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    this.a(pendingIntent13);
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    boolean mockMode = b;
                    if (parcel.readInt() != 0) {
                        mockMode = true;
                    }
                    this.setMockMode(mockMode);
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    Location mockLocation = location;
                    if (parcel.readInt() != 0) {
                        mockLocation = (Location)Location.CREATOR.createFromParcel(parcel);
                    }
                    this.setMockLocation(mockLocation);
                    parcel2.writeNoException();
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LatLngBounds fromParcel3;
                    if (parcel.readInt() != 0) {
                        fromParcel3 = LatLngBounds.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel3 = null;
                    }
                    int1 = parcel.readInt();
                    gt aj;
                    if (parcel.readInt() != 0) {
                        aj = gt.CREATOR.aj(parcel);
                    }
                    else {
                        aj = null;
                    }
                    hi aq2;
                    if (parcel.readInt() != 0) {
                        aq2 = com.google.android.gms.internal.hi.CREATOR.aq(parcel);
                    }
                    else {
                        aq2 = null;
                    }
                    this.a(fromParcel3, int1, aj, aq2, gz.a.N(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string = parcel.readString();
                    hi aq3 = hi;
                    if (parcel.readInt() != 0) {
                        aq3 = com.google.android.gms.internal.hi.CREATOR.aq(parcel);
                    }
                    this.a(string, aq3, gz.a.N(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LatLng fromParcel4;
                    if (parcel.readInt() != 0) {
                        fromParcel4 = LatLng.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel4 = null;
                    }
                    gt aj2;
                    if (parcel.readInt() != 0) {
                        aj2 = gt.CREATOR.aj(parcel);
                    }
                    else {
                        aj2 = null;
                    }
                    if (parcel.readInt() != 0) {
                        aq = com.google.android.gms.internal.hi.CREATOR.aq(parcel);
                    }
                    this.a(fromParcel4, aj2, aq, gz.a.N(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    gt aj3;
                    if (parcel.readInt() != 0) {
                        aj3 = gt.CREATOR.aj(parcel);
                    }
                    else {
                        aj3 = null;
                    }
                    hi aq4 = hi2;
                    if (parcel.readInt() != 0) {
                        aq4 = com.google.android.gms.internal.hi.CREATOR.aq(parcel);
                    }
                    this.a(aj3, aq4, gz.a.N(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    gv ak;
                    if (parcel.readInt() != 0) {
                        ak = gv.CREATOR.ak(parcel);
                    }
                    else {
                        ak = null;
                    }
                    hi aq5;
                    if (parcel.readInt() != 0) {
                        aq5 = com.google.android.gms.internal.hi.CREATOR.aq(parcel);
                    }
                    else {
                        aq5 = null;
                    }
                    PendingIntent pendingIntent14 = pendingIntent6;
                    if (parcel.readInt() != 0) {
                        pendingIntent14 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    this.a(ak, aq5, pendingIntent14);
                    parcel2.writeNoException();
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    hi aq6;
                    if (parcel.readInt() != 0) {
                        aq6 = com.google.android.gms.internal.hi.CREATOR.aq(parcel);
                    }
                    else {
                        aq6 = null;
                    }
                    PendingIntent pendingIntent15 = pendingIntent7;
                    if (parcel.readInt() != 0) {
                        pendingIntent15 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    this.a(aq6, pendingIntent15);
                    parcel2.writeNoException();
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LocationRequest fromParcel5 = locationRequest2;
                    if (parcel.readInt() != 0) {
                        fromParcel5 = LocationRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel5, c.a.I(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final Location an = this.an(parcel.readString());
                    parcel2.writeNoException();
                    if (an != null) {
                        parcel2.writeInt(1);
                        an.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 24: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(parcel.createTypedArrayList((Parcelable$Creator)hd.a.CREATOR));
                    return true;
                }
                case 25: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string2 = parcel.readString();
                    final String string3 = parcel.readString();
                    hi aq7 = hi3;
                    if (parcel.readInt() != 0) {
                        aq7 = com.google.android.gms.internal.hi.CREATOR.aq(parcel);
                    }
                    this.a(string2, string3, aq7);
                    return true;
                }
                case 26: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    Location location3 = location2;
                    if (parcel.readInt() != 0) {
                        location3 = (Location)Location.CREATOR.createFromParcel(parcel);
                    }
                    this.a(location3, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 27: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string4 = parcel.readString();
                    a ae = a;
                    if (parcel.readInt() != 0) {
                        ae = com.google.android.gms.location.a.CREATOR.ae(parcel);
                    }
                    this.a(string4, ae, gj.a.J(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 28: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string5 = parcel.readString();
                    gh ah = gh;
                    if (parcel.readInt() != 0) {
                        ah = com.google.android.gms.internal.gh.CREATOR.ah(parcel);
                    }
                    this.a(string5, ah, gj.a.J(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 29: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(parcel.readString(), gj.a.J(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 30: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string6 = parcel.readString();
                    f ag = f;
                    if (parcel.readInt() != 0) {
                        ag = com.google.android.gms.location.f.CREATOR.ag(parcel);
                    }
                    this.a(string6, ag, gj.a.J(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 31: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string7 = parcel.readString();
                    f ag2 = f2;
                    if (parcel.readInt() != 0) {
                        ag2 = com.google.android.gms.location.f.CREATOR.ag(parcel);
                    }
                    this.b(string7, ag2, gj.a.J(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 32: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string8 = parcel.readString();
                    boolean b4 = b2;
                    if (parcel.readInt() != 0) {
                        b4 = true;
                    }
                    this.a(string8, b4, gj.a.J(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 33: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.b(parcel.readString(), gj.a.J(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 34: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final d ao = this.ao(parcel.readString());
                    parcel2.writeNoException();
                    if (ao != null) {
                        parcel2.writeInt(1);
                        ao.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
            }
        }
        
        private static class a implements gl
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            @Override
            public void a(final long n, final boolean b, final PendingIntent pendingIntent) throws RemoteException {
                int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeLong(n);
                    if (!b) {
                        n2 = 0;
                    }
                    obtain.writeInt(n2);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final PendingIntent pendingIntent) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final PendingIntent pendingIntent, final gk gk, final String s) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (pendingIntent != null) {
                                obtain.writeInt(1);
                                pendingIntent.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (gk != null) {
                                final IBinder binder = gk.asBinder();
                                obtain.writeStrongBinder(binder);
                                obtain.writeString(s);
                                this.dU.transact(2, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final Location location, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(n);
                    this.dU.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final gk gk, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    IBinder binder;
                    if (gk != null) {
                        binder = gk.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final gt gt, final hi hi, final gz gz) throws RemoteException {
            Label_0062_Outer:
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0132:
                        while (true) {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                if (gt != null) {
                                    obtain.writeInt(1);
                                    gt.writeToParcel(obtain, 0);
                                }
                                else {
                                    obtain.writeInt(0);
                                }
                                if (hi != null) {
                                    obtain.writeInt(1);
                                    hi.writeToParcel(obtain, 0);
                                    if (gz != null) {
                                        final IBinder binder = gz.asBinder();
                                        obtain.writeStrongBinder(binder);
                                        this.dU.transact(17, obtain, obtain2, 0);
                                        obtain2.readException();
                                        return;
                                    }
                                    break Label_0132;
                                }
                            }
                            finally {
                                obtain2.recycle();
                                obtain.recycle();
                            }
                            obtain.writeInt(0);
                            continue Label_0062_Outer;
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final gv gv, final hi hi, final PendingIntent pendingIntent) throws RemoteException {
            Label_0068_Outer:
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0132:
                        while (true) {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                if (gv != null) {
                                    obtain.writeInt(1);
                                    gv.writeToParcel(obtain, 0);
                                }
                                else {
                                    obtain.writeInt(0);
                                }
                                if (hi != null) {
                                    obtain.writeInt(1);
                                    hi.writeToParcel(obtain, 0);
                                    if (pendingIntent != null) {
                                        obtain.writeInt(1);
                                        pendingIntent.writeToParcel(obtain, 0);
                                        this.dU.transact(18, obtain, obtain2, 0);
                                        obtain2.readException();
                                        return;
                                    }
                                    break Label_0132;
                                }
                            }
                            finally {
                                obtain2.recycle();
                                obtain.recycle();
                            }
                            obtain.writeInt(0);
                            continue Label_0068_Outer;
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final hi hi, final PendingIntent pendingIntent) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (hi != null) {
                                obtain.writeInt(1);
                                hi.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (pendingIntent != null) {
                                obtain.writeInt(1);
                                pendingIntent.writeToParcel(obtain, 0);
                                this.dU.transact(19, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final LocationRequest locationRequest, final PendingIntent pendingIntent) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (locationRequest != null) {
                                obtain.writeInt(1);
                                locationRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (pendingIntent != null) {
                                obtain.writeInt(1);
                                pendingIntent.writeToParcel(obtain, 0);
                                this.dU.transact(9, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final LocationRequest locationRequest, final c c) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (locationRequest != null) {
                                obtain.writeInt(1);
                                locationRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (c != null) {
                                final IBinder binder = c.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(8, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final LocationRequest locationRequest, final c c, final String s) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (locationRequest != null) {
                                obtain.writeInt(1);
                                locationRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (c != null) {
                                final IBinder binder = c.asBinder();
                                obtain.writeStrongBinder(binder);
                                obtain.writeString(s);
                                this.dU.transact(20, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final c c) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    IBinder binder;
                    if (c != null) {
                        binder = c.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final LatLng latLng, final gt gt, final hi hi, final gz gz) throws RemoteException {
                Parcel obtain;
                Parcel obtain2;
                IBinder binder;
                Label_0068_Outer:Label_0081_Outer:
                while (true) {
                    obtain = Parcel.obtain();
                    obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0160:
                        while (true) {
                        Label_0151:
                            while (true) {
                                try {
                                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                    if (latLng != null) {
                                        obtain.writeInt(1);
                                        latLng.writeToParcel(obtain, 0);
                                    }
                                    else {
                                        obtain.writeInt(0);
                                    }
                                    if (gt != null) {
                                        obtain.writeInt(1);
                                        gt.writeToParcel(obtain, 0);
                                        if (hi == null) {
                                            break Label_0151;
                                        }
                                        obtain.writeInt(1);
                                        hi.writeToParcel(obtain, 0);
                                        if (gz != null) {
                                            binder = gz.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.dU.transact(16, obtain, obtain2, 0);
                                            obtain2.readException();
                                            return;
                                        }
                                        break Label_0160;
                                    }
                                }
                                finally {
                                    obtain2.recycle();
                                    obtain.recycle();
                                }
                                obtain.writeInt(0);
                                continue Label_0068_Outer;
                            }
                            obtain.writeInt(0);
                            continue Label_0081_Outer;
                        }
                        binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final LatLngBounds latLngBounds, final int n, final gt gt, final hi hi, final gz gz) throws RemoteException {
                Parcel obtain;
                Parcel obtain2;
                IBinder binder;
                Label_0076_Outer:Label_0089_Outer:
                while (true) {
                    obtain = Parcel.obtain();
                    obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0168:
                        while (true) {
                        Label_0159:
                            while (true) {
                                try {
                                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                    if (latLngBounds != null) {
                                        obtain.writeInt(1);
                                        latLngBounds.writeToParcel(obtain, 0);
                                    }
                                    else {
                                        obtain.writeInt(0);
                                    }
                                    obtain.writeInt(n);
                                    if (gt != null) {
                                        obtain.writeInt(1);
                                        gt.writeToParcel(obtain, 0);
                                        if (hi == null) {
                                            break Label_0159;
                                        }
                                        obtain.writeInt(1);
                                        hi.writeToParcel(obtain, 0);
                                        if (gz != null) {
                                            binder = gz.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.dU.transact(14, obtain, obtain2, 0);
                                            obtain2.readException();
                                            return;
                                        }
                                        break Label_0168;
                                    }
                                }
                                finally {
                                    obtain2.recycle();
                                    obtain.recycle();
                                }
                                obtain.writeInt(0);
                                continue Label_0076_Outer;
                            }
                            obtain.writeInt(0);
                            continue Label_0089_Outer;
                        }
                        binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final String s, final gh gh, final gj gj) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeString(s);
                            if (gh != null) {
                                obtain.writeInt(1);
                                gh.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (gj != null) {
                                final IBinder binder = gj.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(28, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final String s, final gj gj) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (gj != null) {
                        binder = gj.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final hi hi, final gz gz) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeString(s);
                            if (hi != null) {
                                obtain.writeInt(1);
                                hi.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (gz != null) {
                                final IBinder binder = gz.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(15, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final String s, final com.google.android.gms.location.a a, final gj gj) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeString(s);
                            if (a != null) {
                                obtain.writeInt(1);
                                a.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (gj != null) {
                                final IBinder binder = gj.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(27, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final String s, final f f, final gj gj) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeString(s);
                            if (f != null) {
                                obtain.writeInt(1);
                                f.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (gj != null) {
                                final IBinder binder = gj.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(30, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final String s, final String s2, final hi hi) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    if (hi != null) {
                        obtain.writeInt(1);
                        hi.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(25, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final String s, final boolean b, final gj gj) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    IBinder binder;
                    if (gj != null) {
                        binder = gj.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final List<hd.a> list) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeTypedList((List)list);
                    this.dU.transact(24, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final List<go> list, final PendingIntent pendingIntent, final gk gk, final String s) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeTypedList((List)list);
                            if (pendingIntent != null) {
                                obtain.writeInt(1);
                                pendingIntent.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (gk != null) {
                                final IBinder binder = gk.asBinder();
                                obtain.writeStrongBinder(binder);
                                obtain.writeString(s);
                                this.dU.transact(1, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final String[] array, final gk gk, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeStringArray(array);
                    IBinder binder;
                    if (gk != null) {
                        binder = gk.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Location an(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    this.dU.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    Location location;
                    if (obtain2.readInt() != 0) {
                        location = (Location)Location.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        location = null;
                    }
                    return location;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d ao(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    this.dU.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    d af;
                    if (obtain2.readInt() != 0) {
                        af = d.CREATOR.af(obtain2);
                    }
                    else {
                        af = null;
                    }
                    return af;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.dU;
            }
            
            @Override
            public void b(final String s, final gj gj) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    IBinder binder;
                    if (gj != null) {
                        binder = gj.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final String s, final f f, final gj gj) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeString(s);
                            if (f != null) {
                                obtain.writeInt(1);
                                f.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (gj != null) {
                                final IBinder binder = gj.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(31, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public Location dH() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.dU.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    Location location;
                    if (obtain2.readInt() != 0) {
                        location = (Location)Location.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        location = null;
                    }
                    return location;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void removeActivityUpdates(final PendingIntent pendingIntent) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setMockLocation(final Location location) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setMockMode(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
