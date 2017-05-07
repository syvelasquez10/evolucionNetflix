// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.Binder;
import android.os.IBinder;
import com.google.android.gms.location.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.location.LocationRequest;
import java.util.List;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.location.a;
import android.location.Location;
import android.os.RemoteException;
import android.app.PendingIntent;
import android.os.IInterface;

public interface lw extends IInterface
{
    void a(final long p0, final boolean p1, final PendingIntent p2) throws RemoteException;
    
    void a(final PendingIntent p0) throws RemoteException;
    
    void a(final PendingIntent p0, final lv p1, final String p2) throws RemoteException;
    
    void a(final Location p0, final int p1) throws RemoteException;
    
    void a(final lv p0, final String p1) throws RemoteException;
    
    void a(final lz p0, final PendingIntent p1) throws RemoteException;
    
    void a(final lz p0, final com.google.android.gms.location.a p1) throws RemoteException;
    
    void a(final mg p0, final mw p1, final PendingIntent p2) throws RemoteException;
    
    void a(final mi p0, final mw p1, final mu p2) throws RemoteException;
    
    void a(final mk p0, final mw p1) throws RemoteException;
    
    void a(final mm p0, final mw p1, final PendingIntent p2) throws RemoteException;
    
    void a(final mq p0, final mw p1, final mu p2) throws RemoteException;
    
    void a(final ms p0, final LatLngBounds p1, final List<String> p2, final mw p3, final mu p4) throws RemoteException;
    
    void a(final mw p0, final PendingIntent p1) throws RemoteException;
    
    void a(final LocationRequest p0, final PendingIntent p1) throws RemoteException;
    
    void a(final LocationRequest p0, final com.google.android.gms.location.a p1) throws RemoteException;
    
    void a(final LocationRequest p0, final com.google.android.gms.location.a p1, final String p2) throws RemoteException;
    
    void a(final com.google.android.gms.location.a p0) throws RemoteException;
    
    void a(final LatLng p0, final mi p1, final mw p2, final mu p3) throws RemoteException;
    
    void a(final LatLngBounds p0, final int p1, final mi p2, final mw p3, final mu p4) throws RemoteException;
    
    void a(final LatLngBounds p0, final int p1, final String p2, final mi p3, final mw p4, final mu p5) throws RemoteException;
    
    void a(final String p0, final mw p1, final mu p2) throws RemoteException;
    
    void a(final String p0, final LatLngBounds p1, final me p2, final mw p3, final mu p4) throws RemoteException;
    
    void a(final List<mb> p0, final PendingIntent p1, final lv p2, final String p3) throws RemoteException;
    
    void a(final String[] p0, final lv p1, final String p2) throws RemoteException;
    
    void b(final mw p0, final PendingIntent p1) throws RemoteException;
    
    void b(final String p0, final mw p1, final mu p2) throws RemoteException;
    
    Location bT(final String p0) throws RemoteException;
    
    c bU(final String p0) throws RemoteException;
    
    Location lT() throws RemoteException;
    
    IBinder lU() throws RemoteException;
    
    IBinder lV() throws RemoteException;
    
    void removeActivityUpdates(final PendingIntent p0) throws RemoteException;
    
    void setMockLocation(final Location p0) throws RemoteException;
    
    void setMockMode(final boolean p0) throws RemoteException;
    
    public abstract static class a extends Binder implements lw
    {
        public static lw aK(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (queryLocalInterface != null && queryLocalInterface instanceof lw) {
                return (lw)queryLocalInterface;
            }
            return new lw.a.a(binder);
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean mockMode = false;
            final LocationRequest locationRequest = null;
            final lz lz = null;
            mw cf = null;
            final mw mw = null;
            final mw mw2 = null;
            mw cf2 = null;
            final mw mw3 = null;
            final mw mw4 = null;
            mw cf3 = null;
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
                    final ArrayList typedArrayList = parcel.createTypedArrayList((Parcelable$Creator)mb.CREATOR);
                    PendingIntent pendingIntent;
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent = null;
                    }
                    this.a(typedArrayList, pendingIntent, lv.a.aJ(parcel.readStrongBinder()), parcel.readString());
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
                    this.a(pendingIntent2, lv.a.aJ(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(parcel.createStringArray(), lv.a.aJ(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(lv.a.aJ(parcel.readStrongBinder()), parcel.readString());
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
                    final Location lt = this.lT();
                    parcel2.writeNoException();
                    if (lt != null) {
                        parcel2.writeInt(1);
                        lt.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LocationRequest cs = locationRequest2;
                    if (parcel.readInt() != 0) {
                        cs = LocationRequest.CREATOR.cs(parcel);
                    }
                    this.a(cs, a.a.aI(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LocationRequest cs2 = locationRequest;
                    if (parcel.readInt() != 0) {
                        cs2 = LocationRequest.CREATOR.cs(parcel);
                    }
                    this.a(cs2, a.a.aI(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LocationRequest cs3;
                    if (parcel.readInt() != 0) {
                        cs3 = LocationRequest.CREATOR.cs(parcel);
                    }
                    else {
                        cs3 = null;
                    }
                    PendingIntent pendingIntent5;
                    if (parcel.readInt() != 0) {
                        pendingIntent5 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent5 = null;
                    }
                    this.a(cs3, pendingIntent5);
                    parcel2.writeNoException();
                    return true;
                }
                case 52: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    lz cv = lz;
                    if (parcel.readInt() != 0) {
                        cv = com.google.android.gms.internal.lz.CREATOR.cv(parcel);
                    }
                    this.a(cv, a.a.aI(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 53: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    lz cv2;
                    if (parcel.readInt() != 0) {
                        cv2 = com.google.android.gms.internal.lz.CREATOR.cv(parcel);
                    }
                    else {
                        cv2 = null;
                    }
                    PendingIntent pendingIntent6;
                    if (parcel.readInt() != 0) {
                        pendingIntent6 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent6 = null;
                    }
                    this.a(cv2, pendingIntent6);
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.a(a.a.aI(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    PendingIntent pendingIntent7;
                    if (parcel.readInt() != 0) {
                        pendingIntent7 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent7 = null;
                    }
                    this.a(pendingIntent7);
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
                    LatLngBounds cl;
                    if (parcel.readInt() != 0) {
                        cl = LatLngBounds.CREATOR.cL(parcel);
                    }
                    else {
                        cl = null;
                    }
                    n = parcel.readInt();
                    mi cz;
                    if (parcel.readInt() != 0) {
                        cz = mi.CREATOR.cz(parcel);
                    }
                    else {
                        cz = null;
                    }
                    mw cf4;
                    if (parcel.readInt() != 0) {
                        cf4 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    else {
                        cf4 = null;
                    }
                    this.a(cl, n, cz, cf4, mu.a.aM(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 47: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LatLngBounds cl2;
                    if (parcel.readInt() != 0) {
                        cl2 = LatLngBounds.CREATOR.cL(parcel);
                    }
                    else {
                        cl2 = null;
                    }
                    n = parcel.readInt();
                    final String string = parcel.readString();
                    mi cz2;
                    if (parcel.readInt() != 0) {
                        cz2 = mi.CREATOR.cz(parcel);
                    }
                    else {
                        cz2 = null;
                    }
                    if (parcel.readInt() != 0) {
                        cf = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    this.a(cl2, n, string, cz2, cf, mu.a.aM(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string2 = parcel.readString();
                    mw cf5 = mw;
                    if (parcel.readInt() != 0) {
                        cf5 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    this.a(string2, cf5, mu.a.aM(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LatLng cm;
                    if (parcel.readInt() != 0) {
                        cm = LatLng.CREATOR.cM(parcel);
                    }
                    else {
                        cm = null;
                    }
                    mi cz3;
                    if (parcel.readInt() != 0) {
                        cz3 = mi.CREATOR.cz(parcel);
                    }
                    else {
                        cz3 = null;
                    }
                    mw cf6 = mw2;
                    if (parcel.readInt() != 0) {
                        cf6 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    this.a(cm, cz3, cf6, mu.a.aM(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    mi cz4;
                    if (parcel.readInt() != 0) {
                        cz4 = mi.CREATOR.cz(parcel);
                    }
                    else {
                        cz4 = null;
                    }
                    if (parcel.readInt() != 0) {
                        cf2 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    this.a(cz4, cf2, mu.a.aM(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 42: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string3 = parcel.readString();
                    mw cf7 = mw3;
                    if (parcel.readInt() != 0) {
                        cf7 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    this.b(string3, cf7, mu.a.aM(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 50: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    ms ce;
                    if (parcel.readInt() != 0) {
                        ce = ms.CREATOR.cE(parcel);
                    }
                    else {
                        ce = null;
                    }
                    LatLngBounds cl3;
                    if (parcel.readInt() != 0) {
                        cl3 = LatLngBounds.CREATOR.cL(parcel);
                    }
                    else {
                        cl3 = null;
                    }
                    final ArrayList stringArrayList = parcel.createStringArrayList();
                    mw cf8;
                    if (parcel.readInt() != 0) {
                        cf8 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    else {
                        cf8 = null;
                    }
                    this.a(ce, cl3, stringArrayList, cf8, mu.a.aM(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    mm cb;
                    if (parcel.readInt() != 0) {
                        cb = mm.CREATOR.cB(parcel);
                    }
                    else {
                        cb = null;
                    }
                    mw cf9;
                    if (parcel.readInt() != 0) {
                        cf9 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    else {
                        cf9 = null;
                    }
                    PendingIntent pendingIntent8;
                    if (parcel.readInt() != 0) {
                        pendingIntent8 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent8 = null;
                    }
                    this.a(cb, cf9, pendingIntent8);
                    parcel2.writeNoException();
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    mw cf10;
                    if (parcel.readInt() != 0) {
                        cf10 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    else {
                        cf10 = null;
                    }
                    PendingIntent pendingIntent9;
                    if (parcel.readInt() != 0) {
                        pendingIntent9 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent9 = null;
                    }
                    this.a(cf10, pendingIntent9);
                    parcel2.writeNoException();
                    return true;
                }
                case 48: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    mg cy;
                    if (parcel.readInt() != 0) {
                        cy = mg.CREATOR.cy(parcel);
                    }
                    else {
                        cy = null;
                    }
                    mw cf11;
                    if (parcel.readInt() != 0) {
                        cf11 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    else {
                        cf11 = null;
                    }
                    PendingIntent pendingIntent10;
                    if (parcel.readInt() != 0) {
                        pendingIntent10 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent10 = null;
                    }
                    this.a(cy, cf11, pendingIntent10);
                    parcel2.writeNoException();
                    return true;
                }
                case 49: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    mw cf12;
                    if (parcel.readInt() != 0) {
                        cf12 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    else {
                        cf12 = null;
                    }
                    PendingIntent pendingIntent11;
                    if (parcel.readInt() != 0) {
                        pendingIntent11 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        pendingIntent11 = null;
                    }
                    this.b(cf12, pendingIntent11);
                    parcel2.writeNoException();
                    return true;
                }
                case 55: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final String string4 = parcel.readString();
                    LatLngBounds cl4;
                    if (parcel.readInt() != 0) {
                        cl4 = LatLngBounds.CREATOR.cL(parcel);
                    }
                    else {
                        cl4 = null;
                    }
                    me cx;
                    if (parcel.readInt() != 0) {
                        cx = me.CREATOR.cx(parcel);
                    }
                    else {
                        cx = null;
                    }
                    mw cf13;
                    if (parcel.readInt() != 0) {
                        cf13 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    else {
                        cf13 = null;
                    }
                    this.a(string4, cl4, cx, cf13, mu.a.aM(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 46: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    mq mq;
                    if (parcel.readInt() != 0) {
                        mq = (mq)com.google.android.gms.internal.mq.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        mq = null;
                    }
                    mw cf14 = mw4;
                    if (parcel.readInt() != 0) {
                        cf14 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    this.a(mq, cf14, mu.a.aM(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final Location bt = this.bT(parcel.readString());
                    parcel2.writeNoException();
                    if (bt != null) {
                        parcel2.writeInt(1);
                        bt.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 25: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    mk ca;
                    if (parcel.readInt() != 0) {
                        ca = mk.CREATOR.cA(parcel);
                    }
                    else {
                        ca = null;
                    }
                    if (parcel.readInt() != 0) {
                        cf3 = com.google.android.gms.internal.mw.CREATOR.cF(parcel);
                    }
                    this.a(ca, cf3);
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
                    final c bu = this.bU(parcel.readString());
                    parcel2.writeNoException();
                    if (bu != null) {
                        parcel2.writeInt(1);
                        bu.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 51: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final IBinder lu = this.lU();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(lu);
                    return true;
                }
                case 54: {
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    final IBinder lv = this.lV();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(lv);
                    return true;
                }
            }
        }
        
        private static class a implements lw
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
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
                    this.lb.transact(5, obtain, obtain2, 0);
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
                    this.lb.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final PendingIntent pendingIntent, final lv lv, final String s) throws RemoteException {
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
                            if (lv != null) {
                                final IBinder binder = lv.asBinder();
                                obtain.writeStrongBinder(binder);
                                obtain.writeString(s);
                                this.lb.transact(2, obtain, obtain2, 0);
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
                    this.lb.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final lv lv, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    IBinder binder;
                    if (lv != null) {
                        binder = lv.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final lz lz, final PendingIntent pendingIntent) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (lz != null) {
                                obtain.writeInt(1);
                                lz.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (pendingIntent != null) {
                                obtain.writeInt(1);
                                pendingIntent.writeToParcel(obtain, 0);
                                this.lb.transact(53, obtain, obtain2, 0);
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
            public void a(final lz lz, final com.google.android.gms.location.a a) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (lz != null) {
                                obtain.writeInt(1);
                                lz.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (a != null) {
                                final IBinder binder = a.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(52, obtain, obtain2, 0);
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
            public void a(final mg mg, final mw mw, final PendingIntent pendingIntent) throws RemoteException {
            Label_0068_Outer:
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0132:
                        while (true) {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                if (mg != null) {
                                    obtain.writeInt(1);
                                    mg.writeToParcel(obtain, 0);
                                }
                                else {
                                    obtain.writeInt(0);
                                }
                                if (mw != null) {
                                    obtain.writeInt(1);
                                    mw.writeToParcel(obtain, 0);
                                    if (pendingIntent != null) {
                                        obtain.writeInt(1);
                                        pendingIntent.writeToParcel(obtain, 0);
                                        this.lb.transact(48, obtain, obtain2, 0);
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
            public void a(final mi mi, final mw mw, final mu mu) throws RemoteException {
            Label_0062_Outer:
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0132:
                        while (true) {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                if (mi != null) {
                                    obtain.writeInt(1);
                                    mi.writeToParcel(obtain, 0);
                                }
                                else {
                                    obtain.writeInt(0);
                                }
                                if (mw != null) {
                                    obtain.writeInt(1);
                                    mw.writeToParcel(obtain, 0);
                                    if (mu != null) {
                                        final IBinder binder = mu.asBinder();
                                        obtain.writeStrongBinder(binder);
                                        this.lb.transact(17, obtain, obtain2, 0);
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
            public void a(final mk mk, final mw mw) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (mk != null) {
                                obtain.writeInt(1);
                                mk.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (mw != null) {
                                obtain.writeInt(1);
                                mw.writeToParcel(obtain, 0);
                                this.lb.transact(25, obtain, (Parcel)null, 1);
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
            public void a(final mm mm, final mw mw, final PendingIntent pendingIntent) throws RemoteException {
            Label_0068_Outer:
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0132:
                        while (true) {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                if (mm != null) {
                                    obtain.writeInt(1);
                                    mm.writeToParcel(obtain, 0);
                                }
                                else {
                                    obtain.writeInt(0);
                                }
                                if (mw != null) {
                                    obtain.writeInt(1);
                                    mw.writeToParcel(obtain, 0);
                                    if (pendingIntent != null) {
                                        obtain.writeInt(1);
                                        pendingIntent.writeToParcel(obtain, 0);
                                        this.lb.transact(18, obtain, obtain2, 0);
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
            public void a(final mq mq, final mw mw, final mu mu) throws RemoteException {
            Label_0062_Outer:
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                    Label_0132:
                        while (true) {
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                                if (mq != null) {
                                    obtain.writeInt(1);
                                    mq.writeToParcel(obtain, 0);
                                }
                                else {
                                    obtain.writeInt(0);
                                }
                                if (mw != null) {
                                    obtain.writeInt(1);
                                    mw.writeToParcel(obtain, 0);
                                    if (mu != null) {
                                        final IBinder binder = mu.asBinder();
                                        obtain.writeStrongBinder(binder);
                                        this.lb.transact(46, obtain, obtain2, 0);
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
            public void a(final ms ms, final LatLngBounds latLngBounds, final List<String> list, final mw mw, final mu mu) throws RemoteException {
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
                                    if (ms != null) {
                                        obtain.writeInt(1);
                                        ms.writeToParcel(obtain, 0);
                                    }
                                    else {
                                        obtain.writeInt(0);
                                    }
                                    if (latLngBounds != null) {
                                        obtain.writeInt(1);
                                        latLngBounds.writeToParcel(obtain, 0);
                                        obtain.writeStringList((List)list);
                                        if (mw == null) {
                                            break Label_0159;
                                        }
                                        obtain.writeInt(1);
                                        mw.writeToParcel(obtain, 0);
                                        if (mu != null) {
                                            binder = mu.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.lb.transact(50, obtain, obtain2, 0);
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
            public void a(final mw mw, final PendingIntent pendingIntent) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (mw != null) {
                                obtain.writeInt(1);
                                mw.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (pendingIntent != null) {
                                obtain.writeInt(1);
                                pendingIntent.writeToParcel(obtain, 0);
                                this.lb.transact(19, obtain, obtain2, 0);
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
                                this.lb.transact(9, obtain, obtain2, 0);
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
                                this.lb.transact(8, obtain, obtain2, 0);
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
                                this.lb.transact(20, obtain, obtain2, 0);
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
                    this.lb.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final LatLng latLng, final mi mi, final mw mw, final mu mu) throws RemoteException {
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
                                    if (mi != null) {
                                        obtain.writeInt(1);
                                        mi.writeToParcel(obtain, 0);
                                        if (mw == null) {
                                            break Label_0151;
                                        }
                                        obtain.writeInt(1);
                                        mw.writeToParcel(obtain, 0);
                                        if (mu != null) {
                                            binder = mu.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.lb.transact(16, obtain, obtain2, 0);
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
            public void a(final LatLngBounds latLngBounds, final int n, final mi mi, final mw mw, final mu mu) throws RemoteException {
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
                                    if (mi != null) {
                                        obtain.writeInt(1);
                                        mi.writeToParcel(obtain, 0);
                                        if (mw == null) {
                                            break Label_0159;
                                        }
                                        obtain.writeInt(1);
                                        mw.writeToParcel(obtain, 0);
                                        if (mu != null) {
                                            binder = mu.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.lb.transact(14, obtain, obtain2, 0);
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
            public void a(final LatLngBounds latLngBounds, final int n, final String s, final mi mi, final mw mw, final mu mu) throws RemoteException {
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
                                    if (mi != null) {
                                        obtain.writeInt(1);
                                        mi.writeToParcel(obtain, 0);
                                        if (mw == null) {
                                            break Label_0167;
                                        }
                                        obtain.writeInt(1);
                                        mw.writeToParcel(obtain, 0);
                                        if (mu != null) {
                                            binder = mu.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.lb.transact(47, obtain, obtain2, 0);
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
            public void a(final String s, final mw mw, final mu mu) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeString(s);
                            if (mw != null) {
                                obtain.writeInt(1);
                                mw.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (mu != null) {
                                final IBinder binder = mu.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(15, obtain, obtain2, 0);
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
            public void a(final String s, final LatLngBounds latLngBounds, final me me, final mw mw, final mu mu) throws RemoteException {
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
                                    if (me != null) {
                                        obtain.writeInt(1);
                                        me.writeToParcel(obtain, 0);
                                        if (mw == null) {
                                            break Label_0159;
                                        }
                                        obtain.writeInt(1);
                                        mw.writeToParcel(obtain, 0);
                                        if (mu != null) {
                                            binder = mu.asBinder();
                                            obtain.writeStrongBinder(binder);
                                            this.lb.transact(55, obtain, obtain2, 0);
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
            public void a(final List<mb> list, final PendingIntent pendingIntent, final lv lv, final String s) throws RemoteException {
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
                            if (lv != null) {
                                final IBinder binder = lv.asBinder();
                                obtain.writeStrongBinder(binder);
                                obtain.writeString(s);
                                this.lb.transact(1, obtain, obtain2, 0);
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
            public void a(final String[] array, final lv lv, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeStringArray(array);
                    IBinder binder;
                    if (lv != null) {
                        binder = lv.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void b(final mw mw, final PendingIntent pendingIntent) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            if (mw != null) {
                                obtain.writeInt(1);
                                mw.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (pendingIntent != null) {
                                obtain.writeInt(1);
                                pendingIntent.writeToParcel(obtain, 0);
                                this.lb.transact(49, obtain, obtain2, 0);
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
            public void b(final String s, final mw mw, final mu mu) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                            obtain.writeString(s);
                            if (mw != null) {
                                obtain.writeInt(1);
                                mw.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (mu != null) {
                                final IBinder binder = mu.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(42, obtain, obtain2, 0);
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
            public Location bT(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    this.lb.transact(21, obtain, obtain2, 0);
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
            public c bU(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(s);
                    this.lb.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    c ct;
                    if (obtain2.readInt() != 0) {
                        ct = c.CREATOR.ct(obtain2);
                    }
                    else {
                        ct = null;
                    }
                    return ct;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Location lT() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.lb.transact(7, obtain, obtain2, 0);
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
            public IBinder lU() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.lb.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IBinder lV() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.lb.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
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
                    this.lb.transact(6, obtain, obtain2, 0);
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
                    this.lb.transact(13, obtain, obtain2, 0);
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
                    this.lb.transact(12, obtain, obtain2, 0);
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
