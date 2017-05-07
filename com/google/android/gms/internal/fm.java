// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Binder;
import android.os.IBinder;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface fm extends IInterface
{
    void a(final fl p0, final int p1) throws RemoteException;
    
    void a(final fl p0, final int p1, final String p2) throws RemoteException;
    
    void a(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void a(final fl p0, final int p1, final String p2, final IBinder p3, final Bundle p4) throws RemoteException;
    
    void a(final fl p0, final int p1, final String p2, final String p3, final String[] p4) throws RemoteException;
    
    void a(final fl p0, final int p1, final String p2, final String p3, final String[] p4, final String p5, final Bundle p6) throws RemoteException;
    
    void a(final fl p0, final int p1, final String p2, final String p3, final String[] p4, final String p5, final IBinder p6, final String p7, final Bundle p8) throws RemoteException;
    
    void a(final fl p0, final int p1, final String p2, final String[] p3, final String p4, final Bundle p5) throws RemoteException;
    
    void b(final fl p0, final int p1, final String p2) throws RemoteException;
    
    void b(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void b(final fl p0, final int p1, final String p2, final String p3, final String[] p4) throws RemoteException;
    
    void c(final fl p0, final int p1, final String p2) throws RemoteException;
    
    void c(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void d(final fl p0, final int p1, final String p2) throws RemoteException;
    
    void d(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void e(final fl p0, final int p1, final String p2) throws RemoteException;
    
    void e(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void f(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void g(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void h(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void i(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void j(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void k(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void l(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void m(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void n(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void o(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void p(final fl p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    public abstract static class a extends Binder implements fm
    {
        public static fm C(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            if (queryLocalInterface != null && queryLocalInterface instanceof fm) {
                return (fm)queryLocalInterface;
            }
            return new fm.a.a(binder);
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final Bundle bundle = null;
            final Bundle bundle2 = null;
            final Bundle bundle3 = null;
            final Bundle bundle4 = null;
            final Bundle bundle5 = null;
            final Bundle bundle6 = null;
            final Bundle bundle7 = null;
            final Bundle bundle8 = null;
            final Bundle bundle9 = null;
            final Bundle bundle10 = null;
            final Bundle bundle11 = null;
            final Bundle bundle12 = null;
            final Bundle bundle13 = null;
            final Bundle bundle14 = null;
            final Bundle bundle15 = null;
            Bundle bundle16 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.common.internal.IGmsServiceBroker");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string = parcel.readString();
                    final String string2 = parcel.readString();
                    final String[] stringArray = parcel.createStringArray();
                    final String string3 = parcel.readString();
                    Bundle bundle17;
                    if (parcel.readInt() != 0) {
                        bundle17 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle17 = null;
                    }
                    this.a(b, n, string, string2, stringArray, string3, bundle17);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b2 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string4 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle16 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.a(b2, n, string4, bundle16);
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.a(fl.a.B(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.a(fl.a.B(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b3 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string5 = parcel.readString();
                    Bundle bundle18 = bundle;
                    if (parcel.readInt() != 0) {
                        bundle18 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.b(b3, n, string5, bundle18);
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b4 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string6 = parcel.readString();
                    Bundle bundle19 = bundle2;
                    if (parcel.readInt() != 0) {
                        bundle19 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.c(b4, n, string6, bundle19);
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b5 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string7 = parcel.readString();
                    Bundle bundle20 = bundle3;
                    if (parcel.readInt() != 0) {
                        bundle20 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.d(b5, n, string7, bundle20);
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b6 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string8 = parcel.readString();
                    Bundle bundle21 = bundle4;
                    if (parcel.readInt() != 0) {
                        bundle21 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.e(b6, n, string8, bundle21);
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b7 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string9 = parcel.readString();
                    final String string10 = parcel.readString();
                    final String[] stringArray2 = parcel.createStringArray();
                    final String string11 = parcel.readString();
                    final IBinder strongBinder = parcel.readStrongBinder();
                    final String string12 = parcel.readString();
                    Bundle bundle22;
                    if (parcel.readInt() != 0) {
                        bundle22 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle22 = null;
                    }
                    this.a(b7, n, string9, string10, stringArray2, string11, strongBinder, string12, bundle22);
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.a(fl.a.B(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b8 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string13 = parcel.readString();
                    Bundle bundle23 = bundle5;
                    if (parcel.readInt() != 0) {
                        bundle23 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.f(b8, n, string13, bundle23);
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b9 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string14 = parcel.readString();
                    Bundle bundle24 = bundle6;
                    if (parcel.readInt() != 0) {
                        bundle24 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.g(b9, n, string14, bundle24);
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b10 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string15 = parcel.readString();
                    Bundle bundle25 = bundle7;
                    if (parcel.readInt() != 0) {
                        bundle25 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.h(b10, n, string15, bundle25);
                    parcel2.writeNoException();
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b11 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string16 = parcel.readString();
                    Bundle bundle26 = bundle8;
                    if (parcel.readInt() != 0) {
                        bundle26 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.i(b11, n, string16, bundle26);
                    parcel2.writeNoException();
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b12 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string17 = parcel.readString();
                    Bundle bundle27 = bundle9;
                    if (parcel.readInt() != 0) {
                        bundle27 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.j(b12, n, string17, bundle27);
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b13 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string18 = parcel.readString();
                    Bundle bundle28 = bundle10;
                    if (parcel.readInt() != 0) {
                        bundle28 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.k(b13, n, string18, bundle28);
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b14 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string19 = parcel.readString();
                    Bundle bundle29 = bundle11;
                    if (parcel.readInt() != 0) {
                        bundle29 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.l(b14, n, string19, bundle29);
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b15 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string20 = parcel.readString();
                    Bundle bundle30 = bundle12;
                    if (parcel.readInt() != 0) {
                        bundle30 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.m(b15, n, string20, bundle30);
                    parcel2.writeNoException();
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b16 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string21 = parcel.readString();
                    final IBinder strongBinder2 = parcel.readStrongBinder();
                    Bundle bundle31;
                    if (parcel.readInt() != 0) {
                        bundle31 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle31 = null;
                    }
                    this.a(b16, n, string21, strongBinder2, bundle31);
                    parcel2.writeNoException();
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b17 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string22 = parcel.readString();
                    final String[] stringArray3 = parcel.createStringArray();
                    final String string23 = parcel.readString();
                    Bundle bundle32;
                    if (parcel.readInt() != 0) {
                        bundle32 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle32 = null;
                    }
                    this.a(b17, n, string22, stringArray3, string23, bundle32);
                    parcel2.writeNoException();
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.b(fl.a.B(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 22: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.c(fl.a.B(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 23: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b18 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string24 = parcel.readString();
                    Bundle bundle33 = bundle13;
                    if (parcel.readInt() != 0) {
                        bundle33 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.n(b18, n, string24, bundle33);
                    parcel2.writeNoException();
                    return true;
                }
                case 24: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.d(fl.a.B(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 25: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b19 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string25 = parcel.readString();
                    Bundle bundle34 = bundle14;
                    if (parcel.readInt() != 0) {
                        bundle34 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.o(b19, n, string25, bundle34);
                    parcel2.writeNoException();
                    return true;
                }
                case 26: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.e(fl.a.B(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 27: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final fl b20 = fl.a.B(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string26 = parcel.readString();
                    Bundle bundle35 = bundle15;
                    if (parcel.readInt() != 0) {
                        bundle35 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.p(b20, n, string26, bundle35);
                    parcel2.writeNoException();
                    return true;
                }
                case 28: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.b(fl.a.B(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements fm
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void a(final fl fl, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    this.kn.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fl fl, final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
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
            public void a(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fl fl, final int n, final String s, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder2;
                    if (fl != null) {
                        binder2 = fl.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeStrongBinder(binder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fl fl, final int n, final String s, final String s2, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeStringArray(array);
                    this.kn.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fl fl, final int n, final String s, final String s2, final String[] array, final String s3, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeStringArray(array);
                    obtain.writeString(s3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fl fl, final int n, final String s, final String s2, final String[] array, final String s3, final IBinder binder, final String s4, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder2;
                    if (fl != null) {
                        binder2 = fl.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeStringArray(array);
                    obtain.writeString(s3);
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s4);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fl fl, final int n, final String s, final String[] array, final String s2, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeStringArray(array);
                    obtain.writeString(s2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
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
            public void b(final fl fl, final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    this.kn.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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
            public void b(final fl fl, final int n, final String s, final String s2, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeStringArray(array);
                    this.kn.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final fl fl, final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    this.kn.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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
            public void d(final fl fl, final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    this.kn.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final fl fl, final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    this.kn.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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
            public void g(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void h(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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
            public void i(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void j(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void k(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void l(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void m(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void n(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void o(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void p(final fl fl, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (fl != null) {
                        binder = fl.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(27, obtain, obtain2, 0);
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
