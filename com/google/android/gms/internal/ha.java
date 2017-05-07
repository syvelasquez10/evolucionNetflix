// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.location.b;
import java.util.List;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.location.a;
import com.google.android.gms.location.LocationRequest;
import android.location.Location;
import android.os.RemoteException;
import android.app.PendingIntent;
import android.os.IInterface;

public interface ha extends IInterface
{
    void a(final long p0, final boolean p1, final PendingIntent p2) throws RemoteException;
    
    void a(final PendingIntent p0) throws RemoteException;
    
    void a(final PendingIntent p0, final gz p1, final String p2) throws RemoteException;
    
    void a(final Location p0, final int p1) throws RemoteException;
    
    void a(final gz p0, final String p1) throws RemoteException;
    
    void a(final hg p0, final hs p1, final hq p2) throws RemoteException;
    
    void a(final hi p0, final hs p1) throws RemoteException;
    
    void a(final hk p0, final hs p1, final PendingIntent p2) throws RemoteException;
    
    void a(final ho p0, final hs p1, final hq p2) throws RemoteException;
    
    void a(final hs p0, final PendingIntent p1) throws RemoteException;
    
    void a(final LocationRequest p0, final PendingIntent p1) throws RemoteException;
    
    void a(final LocationRequest p0, final com.google.android.gms.location.a p1) throws RemoteException;
    
    void a(final LocationRequest p0, final com.google.android.gms.location.a p1, final String p2) throws RemoteException;
    
    void a(final com.google.android.gms.location.a p0) throws RemoteException;
    
    void a(final LatLng p0, final hg p1, final hs p2, final hq p3) throws RemoteException;
    
    void a(final LatLngBounds p0, final int p1, final hg p2, final hs p3, final hq p4) throws RemoteException;
    
    void a(final LatLngBounds p0, final int p1, final String p2, final hg p3, final hs p4, final hq p5) throws RemoteException;
    
    void a(final String p0, final hs p1, final hq p2) throws RemoteException;
    
    void a(final String p0, final LatLngBounds p1, final hg p2, final hs p3, final hq p4) throws RemoteException;
    
    void a(final List<hd> p0, final PendingIntent p1, final gz p2, final String p3) throws RemoteException;
    
    void a(final String[] p0, final gz p1, final String p2) throws RemoteException;
    
    Location aW(final String p0) throws RemoteException;
    
    b aX(final String p0) throws RemoteException;
    
    void b(final String p0, final hs p1, final hq p2) throws RemoteException;
    
    Location hP() throws RemoteException;
    
    void removeActivityUpdates(final PendingIntent p0) throws RemoteException;
    
    void setMockLocation(final Location p0) throws RemoteException;
    
    void setMockMode(final boolean p0) throws RemoteException;
    
    public abstract static class a extends Binder implements ha
    {
        public static ha W(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (queryLocalInterface != null && queryLocalInterface instanceof ha) {
                return (ha)queryLocalInterface;
            }
            return new ha.a.a(binder);
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean mockMode = false;
            hs ai = null;
            final hs hs = null;
            final hs hs2 = null;
            hs ai2 = null;
            final hs hs3 = null;
            final hs hs4 = null;
            final LocationRequest locationRequest = null;
            hs ai3 = null;
            final LocationRequest locationRequest2 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final ArrayList typedArrayList = parcel.createTypedArrayList((Parcelable$Creator)hd.CREATOR);
                    PendingIntent pendingIntent;
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent = null;
                    }
                    this.a(typedArrayList, pendingIntent, gz.a.V(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    PendingIntent pendingIntent2;
                    if (parcel.readInt() != 0) {
                        pendingIntent2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent2 = null;
                    }
                    this.a(pendingIntent2, gz.a.V(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(parcel.createStringArray(), gz.a.V(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(gz.a.V(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final long long1 = parcel.readLong();
                    final boolean b = parcel.readInt() != 0;
                    PendingIntent pendingIntent3;
                    if (parcel.readInt() != 0) {
                        pendingIntent3 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent3 = null;
                    }
                    this.a(long1, b, pendingIntent3);
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    PendingIntent pendingIntent4;
                    if (parcel.readInt() != 0) {
                        pendingIntent4 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent4 = null;
                    }
                    this.removeActivityUpdates(pendingIntent4);
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final Location hp = this.hP();
                    parcel2.writeNoException();
                    if (hp != null) {
                        parcel2.writeInt(1);
                        hp.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LocationRequest fromParcel = locationRequest2;
                    if (parcel.readInt() != 0) {
                        fromParcel = LocationRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel, a.a.U(parcel.readStrongBinder()));
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
                    PendingIntent pendingIntent5;
                    if (parcel.readInt() != 0) {
                        pendingIntent5 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent5 = null;
                    }
                    this.a(fromParcel2, pendingIntent5);
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(a.a.U(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    PendingIntent pendingIntent6;
                    if (parcel.readInt() != 0) {
                        pendingIntent6 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent6 = null;
                    }
                    this.a(pendingIntent6);
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        mockMode = true;
                    }
                    this.setMockMode(mockMode);
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    Location mockLocation;
                    if (parcel.readInt() != 0) {
                        mockLocation = (Location)Location.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        mockLocation = null;
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
                    n = parcel.readInt();
                    hg ad;
                    if (parcel.readInt() != 0) {
                        ad = hg.CREATOR.aD(parcel);
                    }
                    else {
                        ad = null;
                    }
                    hs ai4;
                    if (parcel.readInt() != 0) {
                        ai4 = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    else {
                        ai4 = null;
                    }
                    this.a(fromParcel3, n, ad, ai4, hq.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 47: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LatLngBounds fromParcel4;
                    if (parcel.readInt() != 0) {
                        fromParcel4 = LatLngBounds.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel4 = null;
                    }
                    n = parcel.readInt();
                    final String string = parcel.readString();
                    hg ad2;
                    if (parcel.readInt() != 0) {
                        ad2 = hg.CREATOR.aD(parcel);
                    }
                    else {
                        ad2 = null;
                    }
                    if (parcel.readInt() != 0) {
                        ai = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    this.a(fromParcel4, n, string, ad2, ai, hq.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string2 = parcel.readString();
                    hs ai5 = hs;
                    if (parcel.readInt() != 0) {
                        ai5 = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    this.a(string2, ai5, hq.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LatLng fromParcel5;
                    if (parcel.readInt() != 0) {
                        fromParcel5 = LatLng.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel5 = null;
                    }
                    hg ad3;
                    if (parcel.readInt() != 0) {
                        ad3 = hg.CREATOR.aD(parcel);
                    }
                    else {
                        ad3 = null;
                    }
                    hs ai6 = hs2;
                    if (parcel.readInt() != 0) {
                        ai6 = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    this.a(fromParcel5, ad3, ai6, hq.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    hg ad4;
                    if (parcel.readInt() != 0) {
                        ad4 = hg.CREATOR.aD(parcel);
                    }
                    else {
                        ad4 = null;
                    }
                    if (parcel.readInt() != 0) {
                        ai2 = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    this.a(ad4, ai2, hq.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 42: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string3 = parcel.readString();
                    hs ai7 = hs3;
                    if (parcel.readInt() != 0) {
                        ai7 = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    this.b(string3, ai7, hq.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    hk af;
                    if (parcel.readInt() != 0) {
                        af = hk.CREATOR.aF(parcel);
                    }
                    else {
                        af = null;
                    }
                    hs ai8;
                    if (parcel.readInt() != 0) {
                        ai8 = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    else {
                        ai8 = null;
                    }
                    PendingIntent pendingIntent7;
                    if (parcel.readInt() != 0) {
                        pendingIntent7 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent7 = null;
                    }
                    this.a(af, ai8, pendingIntent7);
                    parcel2.writeNoException();
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    hs ai9;
                    if (parcel.readInt() != 0) {
                        ai9 = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    else {
                        ai9 = null;
                    }
                    PendingIntent pendingIntent8;
                    if (parcel.readInt() != 0) {
                        pendingIntent8 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent8 = null;
                    }
                    this.a(ai9, pendingIntent8);
                    parcel2.writeNoException();
                    return true;
                }
                case 45: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string4 = parcel.readString();
                    LatLngBounds fromParcel6;
                    if (parcel.readInt() != 0) {
                        fromParcel6 = LatLngBounds.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel6 = null;
                    }
                    hg ad5;
                    if (parcel.readInt() != 0) {
                        ad5 = hg.CREATOR.aD(parcel);
                    }
                    else {
                        ad5 = null;
                    }
                    hs ai10;
                    if (parcel.readInt() != 0) {
                        ai10 = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    else {
                        ai10 = null;
                    }
                    this.a(string4, fromParcel6, ad5, ai10, hq.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 46: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    ho ho;
                    if (parcel.readInt() != 0) {
                        ho = (ho)com.google.android.gms.internal.ho.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        ho = null;
                    }
                    hs ai11 = hs4;
                    if (parcel.readInt() != 0) {
                        ai11 = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    this.a(ho, ai11, hq.a.Y(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LocationRequest fromParcel7 = locationRequest;
                    if (parcel.readInt() != 0) {
                        fromParcel7 = LocationRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel7, a.a.U(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final Location aw = this.aW(parcel.readString());
                    parcel2.writeNoException();
                    if (aw != null) {
                        parcel2.writeInt(1);
                        aw.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 25: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    hi ae;
                    if (parcel.readInt() != 0) {
                        ae = hi.CREATOR.aE(parcel);
                    }
                    else {
                        ae = null;
                    }
                    if (parcel.readInt() != 0) {
                        ai3 = com.google.android.gms.internal.hs.CREATOR.aI(parcel);
                    }
                    this.a(ae, ai3);
                    return true;
                }
                case 26: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    Location location;
                    if (parcel.readInt() != 0) {
                        location = (Location)Location.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        location = null;
                    }
                    this.a(location, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 34: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final b ax = this.aX(parcel.readString());
                    parcel2.writeNoException();
                    if (ax != null) {
                        parcel2.writeInt(1);
                        ax.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
            }
        }
        
        private static class a implements ha
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
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
                    this.kn.transact(5, obtain, obtain2, 0);
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
                    this.kn.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final PendingIntent pendingIntent, final gz gz, final String s) throws RemoteException {
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
                            if (gz != null) {
                                final IBinder binder = gz.asBinder();
                                obtain.writeStrongBinder(binder);
                                obtain.writeString(s);
                                this.kn.transact(2, obtain, obtain2, 0);
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
                    this.kn.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final gz gz, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    IBinder binder;
                    if (gz != null) {
                        binder = gz.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final hg hg, final hs hs, final hq hq) throws RemoteException {
            Label_0062_Outer:
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0132:
                        while (true) {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                if (hg != null) {
                                    obtain.writeInt(1);
                                    hg.writeToParcel(obtain, 0);
                                }
                                else {
                                    obtain.writeInt(0);
                                }
                                if (hs != null) {
                                    obtain.writeInt(1);
                                    hs.writeToParcel(obtain, 0);
                                    if (hq != null) {
                                        final IBinder binder = hq.asBinder();
                                        obtain.writeStrongBinder(binder);
                                        this.kn.transact(17, obtain, obtain2, 0);
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
            public void a(final hi hi, final hs hs) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
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
                            if (hs != null) {
                                obtain.writeInt(1);
                                hs.writeToParcel(obtain, 0);
                                this.kn.transact(25, obtain, (Parcel)null, 1);
                                return;
                            }
                        }
                        finally {
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final hk hk, final hs hs, final PendingIntent pendingIntent) throws RemoteException {
            Label_0068_Outer:
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0132:
                        while (true) {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                if (hk != null) {
                                    obtain.writeInt(1);
                                    hk.writeToParcel(obtain, 0);
                                }
                                else {
                                    obtain.writeInt(0);
                                }
                                if (hs != null) {
                                    obtain.writeInt(1);
                                    hs.writeToParcel(obtain, 0);
                                    if (pendingIntent != null) {
                                        obtain.writeInt(1);
                                        pendingIntent.writeToParcel(obtain, 0);
                                        this.kn.transact(18, obtain, obtain2, 0);
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
            public void a(final ho ho, final hs hs, final hq hq) throws RemoteException {
            Label_0062_Outer:
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0132:
                        while (true) {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                if (ho != null) {
                                    obtain.writeInt(1);
                                    ho.writeToParcel(obtain, 0);
                                }
                                else {
                                    obtain.writeInt(0);
                                }
                                if (hs != null) {
                                    obtain.writeInt(1);
                                    hs.writeToParcel(obtain, 0);
                                    if (hq != null) {
                                        final IBinder binder = hq.asBinder();
                                        obtain.writeStrongBinder(binder);
                                        this.kn.transact(46, obtain, obtain2, 0);
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
            public void a(final hs hs, final PendingIntent pendingIntent) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (hs != null) {
                                obtain.writeInt(1);
                                hs.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (pendingIntent != null) {
                                obtain.writeInt(1);
                                pendingIntent.writeToParcel(obtain, 0);
                                this.kn.transact(19, obtain, obtain2, 0);
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
                                this.kn.transact(9, obtain, obtain2, 0);
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
            public void a(final LocationRequest locationRequest, final com.google.android.gms.location.a a) throws RemoteException {
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
                            if (a != null) {
                                final IBinder binder = a.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(8, obtain, obtain2, 0);
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
            public void a(final LocationRequest locationRequest, final com.google.android.gms.location.a a, final String s) throws RemoteException {
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
                            if (a != null) {
                                final IBinder binder = a.asBinder();
                                obtain.writeStrongBinder(binder);
                                obtain.writeString(s);
                                this.kn.transact(20, obtain, obtain2, 0);
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
            public void a(final com.google.android.gms.location.a a) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    IBinder binder;
                    if (a != null) {
                        binder = a.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final LatLng latLng, final hg hg, final hs hs, final hq hq) throws RemoteException {
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
                                    if (hg != null) {
                                        obtain.writeInt(1);
                                        hg.writeToParcel(obtain, 0);
                                        if (hs == null) {
                                            break Label_0151;
                                        }
                                        obtain.writeInt(1);
                                        hs.writeToParcel(obtain, 0);
                                        if (hq != null) {
                                            binder = hq.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.kn.transact(16, obtain, obtain2, 0);
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
            public void a(final LatLngBounds latLngBounds, final int n, final hg hg, final hs hs, final hq hq) throws RemoteException {
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
                                    if (hg != null) {
                                        obtain.writeInt(1);
                                        hg.writeToParcel(obtain, 0);
                                        if (hs == null) {
                                            break Label_0159;
                                        }
                                        obtain.writeInt(1);
                                        hs.writeToParcel(obtain, 0);
                                        if (hq != null) {
                                            binder = hq.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.kn.transact(14, obtain, obtain2, 0);
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
            public void a(final LatLngBounds latLngBounds, final int n, final String s, final hg hg, final hs hs, final hq hq) throws RemoteException {
                Parcel obtain;
                Parcel obtain2;
                IBinder binder;
                Label_0084_Outer:Label_0097_Outer:
                while (true) {
                    obtain = Parcel.obtain();
                    obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0176:
                        while (true) {
                        Label_0167:
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
                                    obtain.writeString(s);
                                    if (hg != null) {
                                        obtain.writeInt(1);
                                        hg.writeToParcel(obtain, 0);
                                        if (hs == null) {
                                            break Label_0167;
                                        }
                                        obtain.writeInt(1);
                                        hs.writeToParcel(obtain, 0);
                                        if (hq != null) {
                                            binder = hq.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.kn.transact(47, obtain, obtain2, 0);
                                            obtain2.readException();
                                            return;
                                        }
                                        break Label_0176;
                                    }
                                }
                                finally {
                                    obtain2.recycle();
                                    obtain.recycle();
                                }
                                obtain.writeInt(0);
                                continue Label_0084_Outer;
                            }
                            obtain.writeInt(0);
                            continue Label_0097_Outer;
                        }
                        binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final String s, final hs hs, final hq hq) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeString(s);
                            if (hs != null) {
                                obtain.writeInt(1);
                                hs.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (hq != null) {
                                final IBinder binder = hq.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(15, obtain, obtain2, 0);
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
            public void a(final String s, final LatLngBounds latLngBounds, final hg hg, final hs hs, final hq hq) throws RemoteException {
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
                                    obtain.writeString(s);
                                    if (latLngBounds != null) {
                                        obtain.writeInt(1);
                                        latLngBounds.writeToParcel(obtain, 0);
                                    }
                                    else {
                                        obtain.writeInt(0);
                                    }
                                    if (hg != null) {
                                        obtain.writeInt(1);
                                        hg.writeToParcel(obtain, 0);
                                        if (hs == null) {
                                            break Label_0159;
                                        }
                                        obtain.writeInt(1);
                                        hs.writeToParcel(obtain, 0);
                                        if (hq != null) {
                                            binder = hq.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.kn.transact(45, obtain, obtain2, 0);
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
            public void a(final List<hd> list, final PendingIntent pendingIntent, final gz gz, final String s) throws RemoteException {
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
                            if (gz != null) {
                                final IBinder binder = gz.asBinder();
                                obtain.writeStrongBinder(binder);
                                obtain.writeString(s);
                                this.kn.transact(1, obtain, obtain2, 0);
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
            public void a(final String[] array, final gz gz, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeStringArray(array);
                    IBinder binder;
                    if (gz != null) {
                        binder = gz.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Location aW(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    this.kn.transact(21, obtain, obtain2, 0);
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
            public b aX(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    this.kn.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    b ab;
                    if (obtain2.readInt() != 0) {
                        ab = b.CREATOR.aB(obtain2);
                    }
                    else {
                        ab = null;
                    }
                    return ab;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void b(final String s, final hs hs, final hq hq) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeString(s);
                            if (hs != null) {
                                obtain.writeInt(1);
                                hs.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (hq != null) {
                                final IBinder binder = hq.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(42, obtain, obtain2, 0);
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
            public Location hP() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.kn.transact(7, obtain, obtain2, 0);
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
                    this.kn.transact(6, obtain, obtain2, 0);
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
                    this.kn.transact(13, obtain, obtain2, 0);
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
                    this.kn.transact(12, obtain, obtain2, 0);
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
