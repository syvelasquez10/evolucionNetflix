// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.wearable.c;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Asset;
import android.net.Uri;
import android.os.RemoteException;
import android.os.IInterface;

public interface af extends IInterface
{
    void a(final ad p0) throws RemoteException;
    
    void a(final ad p0, final Uri p1) throws RemoteException;
    
    void a(final ad p0, final Asset p1) throws RemoteException;
    
    void a(final ad p0, final PutDataRequest p1) throws RemoteException;
    
    void a(final ad p0, final c p1) throws RemoteException;
    
    void a(final ad p0, final aq p1) throws RemoteException;
    
    void a(final ad p0, final b p1) throws RemoteException;
    
    void a(final ad p0, final String p1) throws RemoteException;
    
    void a(final ad p0, final String p1, final String p2, final byte[] p3) throws RemoteException;
    
    void b(final ad p0) throws RemoteException;
    
    void b(final ad p0, final Uri p1) throws RemoteException;
    
    void b(final ad p0, final c p1) throws RemoteException;
    
    void b(final ad p0, final String p1) throws RemoteException;
    
    void c(final ad p0) throws RemoteException;
    
    void c(final ad p0, final Uri p1) throws RemoteException;
    
    void c(final ad p0, final String p1) throws RemoteException;
    
    void d(final ad p0) throws RemoteException;
    
    void e(final ad p0) throws RemoteException;
    
    void f(final ad p0) throws RemoteException;
    
    void g(final ad p0) throws RemoteException;
    
    void h(final ad p0) throws RemoteException;
    
    void i(final ad p0) throws RemoteException;
    
    public abstract static class a extends Binder implements af
    {
        public static af bT(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");
            if (queryLocalInterface != null && queryLocalInterface instanceof af) {
                return (af)queryLocalInterface;
            }
            return new af.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final PutDataRequest putDataRequest = null;
            final Uri uri = null;
            final Uri uri2 = null;
            final Uri uri3 = null;
            final Asset asset = null;
            final b b = null;
            final aq aq = null;
            final c c = null;
            c c2 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.wearable.internal.IWearableService");
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    final ad br = ad.a.bR(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        c2 = (c)com.google.android.gms.wearable.c.CREATOR.createFromParcel(parcel);
                    }
                    this.a(br, c2);
                    parcel2.writeNoException();
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.a(ad.a.bR(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 22: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.a(ad.a.bR(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 23: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.b(ad.a.bR(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 24: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.c(ad.a.bR(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    final ad br2 = ad.a.bR(parcel.readStrongBinder());
                    PutDataRequest putDataRequest2 = putDataRequest;
                    if (parcel.readInt() != 0) {
                        putDataRequest2 = (PutDataRequest)PutDataRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(br2, putDataRequest2);
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    final ad br3 = ad.a.bR(parcel.readStrongBinder());
                    Uri uri4 = uri;
                    if (parcel.readInt() != 0) {
                        uri4 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                    }
                    this.a(br3, uri4);
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.b(ad.a.bR(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    final ad br4 = ad.a.bR(parcel.readStrongBinder());
                    Uri uri5 = uri2;
                    if (parcel.readInt() != 0) {
                        uri5 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                    }
                    this.b(br4, uri5);
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    final ad br5 = ad.a.bR(parcel.readStrongBinder());
                    Uri uri6 = uri3;
                    if (parcel.readInt() != 0) {
                        uri6 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                    }
                    this.c(br5, uri6);
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.a(ad.a.bR(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    final ad br6 = ad.a.bR(parcel.readStrongBinder());
                    Asset asset2 = asset;
                    if (parcel.readInt() != 0) {
                        asset2 = (Asset)Asset.CREATOR.createFromParcel(parcel);
                    }
                    this.a(br6, asset2);
                    parcel2.writeNoException();
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.c(ad.a.bR(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.d(ad.a.bR(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    final ad br7 = ad.a.bR(parcel.readStrongBinder());
                    b b2 = b;
                    if (parcel.readInt() != 0) {
                        b2 = (b)com.google.android.gms.wearable.internal.b.CREATOR.createFromParcel(parcel);
                    }
                    this.a(br7, b2);
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    final ad br8 = ad.a.bR(parcel.readStrongBinder());
                    aq aq2 = aq;
                    if (parcel.readInt() != 0) {
                        aq2 = (aq)com.google.android.gms.wearable.internal.aq.CREATOR.createFromParcel(parcel);
                    }
                    this.a(br8, aq2);
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.e(ad.a.bR(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.f(ad.a.bR(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    final ad br9 = ad.a.bR(parcel.readStrongBinder());
                    c c3 = c;
                    if (parcel.readInt() != 0) {
                        c3 = (c)com.google.android.gms.wearable.c.CREATOR.createFromParcel(parcel);
                    }
                    this.b(br9, c3);
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.g(ad.a.bR(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.h(ad.a.bR(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableService");
                    this.i(ad.a.bR(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements af
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final ad ad) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ad ad, final Uri uri) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ad ad, final Asset asset) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (asset != null) {
                        obtain.writeInt(1);
                        asset.writeToParcel(obtain, 0);
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
            public void a(final ad ad, final PutDataRequest putDataRequest) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (putDataRequest != null) {
                        obtain.writeInt(1);
                        putDataRequest.writeToParcel(obtain, 0);
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
            public void a(final ad ad, final c c) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (c != null) {
                        obtain.writeInt(1);
                        c.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ad ad, final aq aq) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (aq != null) {
                        obtain.writeInt(1);
                        aq.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ad ad, final b b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (b != null) {
                        obtain.writeInt(1);
                        b.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ad ad, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.lb.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final ad ad, final String s, final String s2, final byte[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeByteArray(array);
                    this.lb.transact(12, obtain, obtain2, 0);
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
            public void b(final ad ad) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final ad ad, final Uri uri) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final ad ad, final c c) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (c != null) {
                        obtain.writeInt(1);
                        c.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final ad ad, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.lb.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final ad ad) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final ad ad, final Uri uri) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
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
            public void c(final ad ad, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.lb.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final ad ad) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final ad ad) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final ad ad) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void g(final ad ad) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void h(final ad ad) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void i(final ad ad) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    IBinder binder;
                    if (ad != null) {
                        binder = ad.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(5, obtain, obtain2, 0);
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
