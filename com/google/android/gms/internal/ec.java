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

public interface ec extends IInterface
{
    void a(final eb p0, final int p1) throws RemoteException;
    
    void a(final eb p0, final int p1, final String p2) throws RemoteException;
    
    void a(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void a(final eb p0, final int p1, final String p2, final IBinder p3, final Bundle p4) throws RemoteException;
    
    void a(final eb p0, final int p1, final String p2, final String p3, final String[] p4) throws RemoteException;
    
    void a(final eb p0, final int p1, final String p2, final String p3, final String[] p4, final String p5, final Bundle p6) throws RemoteException;
    
    void a(final eb p0, final int p1, final String p2, final String p3, final String[] p4, final String p5, final IBinder p6, final String p7, final Bundle p8) throws RemoteException;
    
    void a(final eb p0, final int p1, final String p2, final String[] p3, final String p4, final Bundle p5) throws RemoteException;
    
    void b(final eb p0, final int p1, final String p2) throws RemoteException;
    
    void b(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void c(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void d(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void e(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void f(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void g(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void h(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void i(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void j(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void k(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void l(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    void m(final eb p0, final int p1, final String p2, final Bundle p3) throws RemoteException;
    
    public abstract static class a extends Binder implements ec
    {
        public static ec y(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            if (queryLocalInterface != null && queryLocalInterface instanceof ec) {
                return (ec)queryLocalInterface;
            }
            return new ec.a.a(binder);
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
            Bundle bundle13 = null;
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
                    final eb x = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string = parcel.readString();
                    final String string2 = parcel.readString();
                    final String[] stringArray = parcel.createStringArray();
                    final String string3 = parcel.readString();
                    Bundle bundle14;
                    if (parcel.readInt() != 0) {
                        bundle14 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle14 = null;
                    }
                    this.a(x, n, string, string2, stringArray, string3, bundle14);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x2 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string4 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle13 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.a(x2, n, string4, bundle13);
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.a(eb.a.x(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.a(eb.a.x(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x3 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string5 = parcel.readString();
                    Bundle bundle15 = bundle;
                    if (parcel.readInt() != 0) {
                        bundle15 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.b(x3, n, string5, bundle15);
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x4 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string6 = parcel.readString();
                    Bundle bundle16 = bundle2;
                    if (parcel.readInt() != 0) {
                        bundle16 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.c(x4, n, string6, bundle16);
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x5 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string7 = parcel.readString();
                    Bundle bundle17 = bundle3;
                    if (parcel.readInt() != 0) {
                        bundle17 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.d(x5, n, string7, bundle17);
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x6 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string8 = parcel.readString();
                    Bundle bundle18 = bundle4;
                    if (parcel.readInt() != 0) {
                        bundle18 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.e(x6, n, string8, bundle18);
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x7 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string9 = parcel.readString();
                    final String string10 = parcel.readString();
                    final String[] stringArray2 = parcel.createStringArray();
                    final String string11 = parcel.readString();
                    final IBinder strongBinder = parcel.readStrongBinder();
                    final String string12 = parcel.readString();
                    Bundle bundle19;
                    if (parcel.readInt() != 0) {
                        bundle19 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle19 = null;
                    }
                    this.a(x7, n, string9, string10, stringArray2, string11, strongBinder, string12, bundle19);
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.a(eb.a.x(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x8 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string13 = parcel.readString();
                    Bundle bundle20 = bundle5;
                    if (parcel.readInt() != 0) {
                        bundle20 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.f(x8, n, string13, bundle20);
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x9 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string14 = parcel.readString();
                    Bundle bundle21 = bundle6;
                    if (parcel.readInt() != 0) {
                        bundle21 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.g(x9, n, string14, bundle21);
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x10 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string15 = parcel.readString();
                    Bundle bundle22 = bundle7;
                    if (parcel.readInt() != 0) {
                        bundle22 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.h(x10, n, string15, bundle22);
                    parcel2.writeNoException();
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x11 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string16 = parcel.readString();
                    Bundle bundle23 = bundle8;
                    if (parcel.readInt() != 0) {
                        bundle23 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.i(x11, n, string16, bundle23);
                    parcel2.writeNoException();
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x12 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string17 = parcel.readString();
                    Bundle bundle24 = bundle9;
                    if (parcel.readInt() != 0) {
                        bundle24 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.j(x12, n, string17, bundle24);
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x13 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string18 = parcel.readString();
                    Bundle bundle25 = bundle10;
                    if (parcel.readInt() != 0) {
                        bundle25 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.k(x13, n, string18, bundle25);
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x14 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string19 = parcel.readString();
                    Bundle bundle26 = bundle11;
                    if (parcel.readInt() != 0) {
                        bundle26 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.l(x14, n, string19, bundle26);
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x15 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string20 = parcel.readString();
                    Bundle bundle27 = bundle12;
                    if (parcel.readInt() != 0) {
                        bundle27 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.m(x15, n, string20, bundle27);
                    parcel2.writeNoException();
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x16 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string21 = parcel.readString();
                    final IBinder strongBinder2 = parcel.readStrongBinder();
                    Bundle bundle28;
                    if (parcel.readInt() != 0) {
                        bundle28 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle28 = null;
                    }
                    this.a(x16, n, string21, strongBinder2, bundle28);
                    parcel2.writeNoException();
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    final eb x17 = eb.a.x(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final String string22 = parcel.readString();
                    final String[] stringArray3 = parcel.createStringArray();
                    final String string23 = parcel.readString();
                    Bundle bundle29;
                    if (parcel.readInt() != 0) {
                        bundle29 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle29 = null;
                    }
                    this.a(x17, n, string22, stringArray3, string23, bundle29);
                    parcel2.writeNoException();
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    this.b(eb.a.x(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements ec
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            @Override
            public void a(final eb eb, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    this.dU.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final eb eb, final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
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
            public void a(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final eb eb, final int n, final String s, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder2;
                    if (eb != null) {
                        binder2 = eb.asBinder();
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
                    this.dU.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final eb eb, final int n, final String s, final String s2, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeStringArray(array);
                    this.dU.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final eb eb, final int n, final String s, final String s2, final String[] array, final String s3, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final eb eb, final int n, final String s, final String s2, final String[] array, final String s3, final IBinder binder, final String s4, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder2;
                    if (eb != null) {
                        binder2 = eb.asBinder();
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
                    this.dU.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final eb eb, final int n, final String s, final String[] array, final String s2, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
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
            public void b(final eb eb, final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    this.dU.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void g(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void h(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void i(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void j(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void k(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void l(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void m(final eb eb, final int n, final String s, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    IBinder binder;
                    if (eb != null) {
                        binder = eb.asBinder();
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
                    this.dU.transact(18, obtain, obtain2, 0);
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
