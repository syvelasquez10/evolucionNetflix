// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcelable;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.Binder;
import com.google.android.gms.common.data.DataHolder;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import android.os.Bundle;
import android.os.IBinder;
import android.net.Uri;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.internal.request.GameRequestCluster;
import com.google.android.gms.games.internal.multiplayer.ZInvitationCluster;
import android.content.Intent;
import android.os.RemoteException;
import android.os.IInterface;

public interface IGamesService extends IInterface
{
    void A(final boolean p0) throws RemoteException;
    
    int a(final IGamesCallbacks p0, final byte[] p1, final String p2, final String p3) throws RemoteException;
    
    Intent a(final int p0, final int p1, final boolean p2) throws RemoteException;
    
    Intent a(final int p0, final byte[] p1, final int p2, final String p3) throws RemoteException;
    
    Intent a(final ZInvitationCluster p0, final String p1, final String p2) throws RemoteException;
    
    Intent a(final GameRequestCluster p0, final String p1) throws RemoteException;
    
    Intent a(final RoomEntity p0, final int p1) throws RemoteException;
    
    Intent a(final ParticipantEntity[] p0, final String p1, final String p2, final Uri p3, final Uri p4) throws RemoteException;
    
    void a(final long p0, final String p1) throws RemoteException;
    
    void a(final IBinder p0, final Bundle p1) throws RemoteException;
    
    void a(final IGamesCallbacks p0) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final int p1) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final int p1, final int p2, final int p3) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final int p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final int p1, final int p2, final String[] p3, final Bundle p4) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final int p1, final int[] p2) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final long p1) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final long p1, final String p2) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final Bundle p1, final int p2, final int p3) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final IBinder p1, final int p2, final String[] p3, final Bundle p4, final boolean p5, final long p6) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final IBinder p1, final String p2, final boolean p3, final long p4) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final int p2) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final int p3, final int p4, final boolean p5) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final IBinder p3, final Bundle p4) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4, final boolean p5, final boolean p6) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final int p2, final int[] p3) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final long p2) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final long p2, final String p3) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final IBinder p2, final Bundle p3) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final String p2) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final int p4) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final int p4, final int p5) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final int p4, final int p5, final boolean p6) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final boolean p4, final boolean p5) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final boolean p3) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final String p2, final String[] p3) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final boolean p2) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final byte[] p2, final String p3, final ParticipantResult[] p4) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final byte[] p2, final ParticipantResult[] p3) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final int[] p2) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String p1, final String[] p2, final int p3, final byte[] p4, final int p5) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final boolean p1) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final boolean p1, final Bundle p2) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final int[] p1) throws RemoteException;
    
    void a(final IGamesCallbacks p0, final String[] p1) throws RemoteException;
    
    Intent aA(final String p0) throws RemoteException;
    
    String aD(final String p0) throws RemoteException;
    
    String aE(final String p0) throws RemoteException;
    
    void aF(final String p0) throws RemoteException;
    
    int aG(final String p0) throws RemoteException;
    
    Uri aH(final String p0) throws RemoteException;
    
    void aI(final String p0) throws RemoteException;
    
    ParcelFileDescriptor aJ(final String p0) throws RemoteException;
    
    void aY(final int p0) throws RemoteException;
    
    int b(final byte[] p0, final String p1, final String[] p2) throws RemoteException;
    
    Intent b(final int p0, final int p1, final boolean p2) throws RemoteException;
    
    void b(final long p0, final String p1) throws RemoteException;
    
    void b(final IGamesCallbacks p0) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final long p1) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final long p1, final String p2) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String p1, final int p2, final int p3, final int p4, final boolean p5) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String p1, final int p2, final IBinder p3, final Bundle p4) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String p1, final IBinder p2, final Bundle p3) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String p1, final String p2) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String p1, final String p2, final int p3, final int p4, final int p5, final boolean p6) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String p1, final String p2, final boolean p3) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String p1, final boolean p2) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final boolean p1) throws RemoteException;
    
    void b(final IGamesCallbacks p0, final String[] p1) throws RemoteException;
    
    void b(final String p0, final String p1, final int p2) throws RemoteException;
    
    void c(final long p0, final String p1) throws RemoteException;
    
    void c(final IGamesCallbacks p0) throws RemoteException;
    
    void c(final IGamesCallbacks p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void c(final IGamesCallbacks p0, final long p1) throws RemoteException;
    
    void c(final IGamesCallbacks p0, final long p1, final String p2) throws RemoteException;
    
    void c(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void c(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void c(final IGamesCallbacks p0, final String p1, final String p2) throws RemoteException;
    
    void c(final IGamesCallbacks p0, final String p1, final boolean p2) throws RemoteException;
    
    void c(final IGamesCallbacks p0, final boolean p1) throws RemoteException;
    
    void c(final IGamesCallbacks p0, final String[] p1) throws RemoteException;
    
    void c(final String p0, final String p1, final int p2) throws RemoteException;
    
    void d(final IGamesCallbacks p0) throws RemoteException;
    
    void d(final IGamesCallbacks p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void d(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void d(final IGamesCallbacks p0, final String p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void d(final IGamesCallbacks p0, final String p1, final String p2) throws RemoteException;
    
    void d(final IGamesCallbacks p0, final String p1, final boolean p2) throws RemoteException;
    
    Bundle dG() throws RemoteException;
    
    void e(final IGamesCallbacks p0) throws RemoteException;
    
    void e(final IGamesCallbacks p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void e(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void e(final IGamesCallbacks p0, final String p1, final String p2) throws RemoteException;
    
    ParcelFileDescriptor f(final Uri p0) throws RemoteException;
    
    void f(final IGamesCallbacks p0) throws RemoteException;
    
    void f(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void g(final IGamesCallbacks p0) throws RemoteException;
    
    void g(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    int gA() throws RemoteException;
    
    Intent gB() throws RemoteException;
    
    int gC() throws RemoteException;
    
    int gD() throws RemoteException;
    
    void gF() throws RemoteException;
    
    DataHolder gG() throws RemoteException;
    
    boolean gH() throws RemoteException;
    
    DataHolder gI() throws RemoteException;
    
    void gJ() throws RemoteException;
    
    Intent gK() throws RemoteException;
    
    void gL() throws RemoteException;
    
    String gl() throws RemoteException;
    
    String gm() throws RemoteException;
    
    Intent gp() throws RemoteException;
    
    Intent gq() throws RemoteException;
    
    Intent gr() throws RemoteException;
    
    Intent gs() throws RemoteException;
    
    Intent gw() throws RemoteException;
    
    Intent gx() throws RemoteException;
    
    int gy() throws RemoteException;
    
    String gz() throws RemoteException;
    
    RoomEntity h(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void h(final IGamesCallbacks p0) throws RemoteException;
    
    void i(final IGamesCallbacks p0) throws RemoteException;
    
    void i(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void j(final IGamesCallbacks p0) throws RemoteException;
    
    void j(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void j(final String p0, final String p1) throws RemoteException;
    
    void k(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void k(final String p0, final String p1) throws RemoteException;
    
    void l(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void l(final String p0, final int p1) throws RemoteException;
    
    void m(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void m(final String p0, final int p1) throws RemoteException;
    
    void n(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void n(final String p0, final int p1) throws RemoteException;
    
    void o(final long p0) throws RemoteException;
    
    void o(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void o(final String p0, final int p1) throws RemoteException;
    
    void p(final long p0) throws RemoteException;
    
    void p(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void q(final long p0) throws RemoteException;
    
    void q(final IGamesCallbacks p0, final String p1) throws RemoteException;
    
    void r(final long p0) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IGamesService
    {
        public Stub() {
            this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IGamesService");
        }
        
        public static IGamesService N(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
            if (queryLocalInterface != null && queryLocalInterface instanceof IGamesService) {
                return (IGamesService)queryLocalInterface;
            }
            return new Proxy(binder);
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, int n2) throws RemoteException {
            final Bundle bundle = null;
            final Bundle bundle2 = null;
            final Bundle bundle3 = null;
            Uri uri = null;
            final GameRequestCluster gameRequestCluster = null;
            final ZInvitationCluster zInvitationCluster = null;
            Bundle bundle4 = null;
            boolean b = false;
            final boolean b2 = false;
            final boolean b3 = false;
            final boolean b4 = false;
            final boolean b5 = false;
            final int n3 = 0;
            final boolean b6 = false;
            final boolean b7 = false;
            final boolean b8 = false;
            final boolean b9 = false;
            final boolean b10 = false;
            final boolean b11 = false;
            boolean b12 = false;
            final boolean b13 = false;
            final boolean b14 = false;
            final boolean b15 = false;
            final boolean b16 = false;
            final boolean b17 = false;
            final boolean b18 = false;
            final boolean b19 = false;
            final boolean b20 = false;
            boolean b21 = false;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.games.internal.IGamesService");
                    return true;
                }
                case 5001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.o(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final String gz = this.gz();
                    parcel2.writeNoException();
                    parcel2.writeString(gz);
                    return true;
                }
                case 5004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Bundle dg = this.dG();
                    parcel2.writeNoException();
                    if (dg != null) {
                        parcel2.writeInt(1);
                        dg.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 5005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IBinder strongBinder = parcel.readStrongBinder();
                    Bundle bundle5;
                    if (parcel.readInt() != 0) {
                        bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle5 = null;
                    }
                    this.a(strongBinder, bundle5);
                    parcel2.writeNoException();
                    return true;
                }
                case 5006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.gF();
                    parcel2.writeNoException();
                    return true;
                }
                case 5007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final String gl = this.gl();
                    parcel2.writeNoException();
                    parcel2.writeString(gl);
                    return true;
                }
                case 5064: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final String ad = this.aD(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(ad);
                    return true;
                }
                case 5065: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.j(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5012: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final String gm = this.gm();
                    parcel2.writeNoException();
                    parcel2.writeString(gm);
                    return true;
                }
                case 5013: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final DataHolder gg = this.gG();
                    parcel2.writeNoException();
                    if (gg != null) {
                        parcel2.writeInt(1);
                        gg.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 5014: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5015: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final boolean b22 = parcel.readInt() != 0;
                    if (parcel.readInt() != 0) {
                        b21 = true;
                    }
                    this.a(m, n, b22, b21);
                    parcel2.writeNoException();
                    return true;
                }
                case 5016: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5017: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(IGamesCallbacks.Stub.M(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5018: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5019: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks i = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string = parcel.readString();
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    this.a(i, string, n, n2, parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5020: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks j = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string2 = parcel.readString();
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    this.b(j, string2, n, n2, parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5021: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks k = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    Bundle bundle6;
                    if (parcel.readInt() != 0) {
                        bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle6 = null;
                    }
                    this.a(k, bundle6, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5022: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(IGamesCallbacks.Stub.M(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5023: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks l = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string3 = parcel.readString();
                    final IBinder strongBinder2 = parcel.readStrongBinder();
                    Bundle bundle7;
                    if (parcel.readInt() != 0) {
                        bundle7 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle7 = null;
                    }
                    this.a(l, string3, strongBinder2, bundle7);
                    parcel2.writeNoException();
                    return true;
                }
                case 5024: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m2 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string4 = parcel.readString();
                    final IBinder strongBinder3 = parcel.readStrongBinder();
                    Bundle bundle8;
                    if (parcel.readInt() != 0) {
                        bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle8 = null;
                    }
                    this.b(m2, string4, strongBinder3, bundle8);
                    parcel2.writeNoException();
                    return true;
                }
                case 5025: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m3 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string5 = parcel.readString();
                    n = parcel.readInt();
                    final IBinder strongBinder4 = parcel.readStrongBinder();
                    if (parcel.readInt() != 0) {
                        bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.a(m3, string5, n, strongBinder4, bundle4);
                    parcel2.writeNoException();
                    return true;
                }
                case 5026: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.d(IGamesCallbacks.Stub.M(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5027: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.e(IGamesCallbacks.Stub.M(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5028: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.m(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5029: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.l(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5058: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5059: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.p(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5030: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m4 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final IBinder strongBinder5 = parcel.readStrongBinder();
                    n = parcel.readInt();
                    final String[] stringArray = parcel.createStringArray();
                    Bundle bundle9 = bundle;
                    if (parcel.readInt() != 0) {
                        bundle9 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.a(m4, strongBinder5, n, stringArray, bundle9, parcel.readInt() != 0, parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5031: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readStrongBinder(), parcel.readString(), parcel.readInt() != 0, parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5032: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5033: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.createByteArray(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 5034: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.b(parcel.createByteArray(), parcel.readString(), parcel.createStringArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 5035: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final String ae = this.aE(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(ae);
                    return true;
                }
                case 5036: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.aY(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5037: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.d(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5038: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5039: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m5 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string6 = parcel.readString();
                    final String string7 = parcel.readString();
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    final int int1 = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        b = true;
                    }
                    this.a(m5, string6, string7, n, n2, int1, b);
                    parcel2.writeNoException();
                    return true;
                }
                case 5040: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m6 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string8 = parcel.readString();
                    final String string9 = parcel.readString();
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    final int int2 = parcel.readInt();
                    boolean b23 = b2;
                    if (parcel.readInt() != 0) {
                        b23 = true;
                    }
                    this.b(m6, string8, string9, n, n2, int2, b23);
                    parcel2.writeNoException();
                    return true;
                }
                case 5041: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5042: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.e(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5043: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.f(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5044: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m7 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    this.a(m7, n, n2, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5045: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m8 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string10 = parcel.readString();
                    n = parcel.readInt();
                    this.a(m8, string10, n, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5046: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m9 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final boolean b24 = parcel.readInt() != 0;
                    boolean b25 = b3;
                    if (parcel.readInt() != 0) {
                        b25 = true;
                    }
                    this.b(m9, n, b24, b25);
                    parcel2.writeNoException();
                    return true;
                }
                case 5047: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.f(IGamesCallbacks.Stub.M(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5048: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m10 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final boolean b26 = parcel.readInt() != 0;
                    boolean b27 = b4;
                    if (parcel.readInt() != 0) {
                        b27 = true;
                    }
                    this.c(m10, n, b26, b27);
                    parcel2.writeNoException();
                    return true;
                }
                case 5049: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.g(IGamesCallbacks.Stub.M(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5050: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.aF(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5051: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5052: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.g(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5053: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final RoomEntity h = this.h(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    if (h != null) {
                        parcel2.writeInt(1);
                        h.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 5060: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.aG(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 5054: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m11 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string11 = parcel.readString();
                    boolean b28 = b5;
                    if (parcel.readInt() != 0) {
                        b28 = true;
                    }
                    this.a(m11, string11, b28);
                    parcel2.writeNoException();
                    return true;
                }
                case 5061: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.i(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5055: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.n(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5067: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final boolean gh = this.gH();
                    parcel2.writeNoException();
                    n = n3;
                    if (gh) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 5068: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    boolean b29 = b6;
                    if (parcel.readInt() != 0) {
                        b29 = true;
                    }
                    this.A(b29);
                    parcel2.writeNoException();
                    return true;
                }
                case 5056: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.h(IGamesCallbacks.Stub.M(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5057: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.j(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5062: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.i(IGamesCallbacks.Stub.M(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5063: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m12 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    boolean b30 = b7;
                    if (parcel.readInt() != 0) {
                        b30 = true;
                    }
                    Bundle bundle10;
                    if (parcel.readInt() != 0) {
                        bundle10 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle10 = null;
                    }
                    this.a(m12, b30, bundle10);
                    parcel2.writeNoException();
                    return true;
                }
                case 5066: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Uri ah = this.aH(parcel.readString());
                    parcel2.writeNoException();
                    if (ah != null) {
                        parcel2.writeInt(1);
                        ah.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 5501: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m13 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string12 = parcel.readString();
                    n = parcel.readInt();
                    this.b(m13, string12, n, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5502: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final DataHolder gi = this.gI();
                    parcel2.writeNoException();
                    if (gi != null) {
                        parcel2.writeInt(1);
                        gi.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 6001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m14 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    boolean b31 = b8;
                    if (parcel.readInt() != 0) {
                        b31 = true;
                    }
                    this.a(m14, b31);
                    parcel2.writeNoException();
                    return true;
                }
                case 6002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m15 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string13 = parcel.readString();
                    final String string14 = parcel.readString();
                    boolean b32 = b9;
                    if (parcel.readInt() != 0) {
                        b32 = true;
                    }
                    this.a(m15, string13, string14, b32);
                    parcel2.writeNoException();
                    return true;
                }
                case 6003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m16 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final boolean b33 = parcel.readInt() != 0;
                    boolean b34 = b10;
                    if (parcel.readInt() != 0) {
                        b34 = true;
                    }
                    this.d(m16, n, b33, b34);
                    parcel2.writeNoException();
                    return true;
                }
                case 6004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m17 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final boolean b35 = parcel.readInt() != 0;
                    boolean b36 = b11;
                    if (parcel.readInt() != 0) {
                        b36 = true;
                    }
                    this.e(m17, n, b35, b36);
                    parcel2.writeNoException();
                    return true;
                }
                case 6501: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m18 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string15 = parcel.readString();
                    n = parcel.readInt();
                    final boolean b37 = parcel.readInt() != 0;
                    final boolean b38 = parcel.readInt() != 0;
                    final boolean b39 = parcel.readInt() != 0;
                    if (parcel.readInt() != 0) {
                        b12 = true;
                    }
                    this.a(m18, string15, n, b37, b38, b39, b12);
                    parcel2.writeNoException();
                    return true;
                }
                case 6502: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m19 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string16 = parcel.readString();
                    boolean b40 = b13;
                    if (parcel.readInt() != 0) {
                        b40 = true;
                    }
                    this.b(m19, string16, b40);
                    parcel2.writeNoException();
                    return true;
                }
                case 6503: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m20 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    boolean b41 = b14;
                    if (parcel.readInt() != 0) {
                        b41 = true;
                    }
                    this.b(m20, b41);
                    parcel2.writeNoException();
                    return true;
                }
                case 6504: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m21 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string17 = parcel.readString();
                    boolean b42 = b15;
                    if (parcel.readInt() != 0) {
                        b42 = true;
                    }
                    this.c(m21, string17, b42);
                    parcel2.writeNoException();
                    return true;
                }
                case 6505: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m22 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string18 = parcel.readString();
                    boolean b43 = b16;
                    if (parcel.readInt() != 0) {
                        b43 = true;
                    }
                    this.d(m22, string18, b43);
                    parcel2.writeNoException();
                    return true;
                }
                case 6506: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m23 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string19 = parcel.readString();
                    final String string20 = parcel.readString();
                    boolean b44 = b17;
                    if (parcel.readInt() != 0) {
                        b44 = true;
                    }
                    this.b(m23, string19, string20, b44);
                    parcel2.writeNoException();
                    return true;
                }
                case 6507: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    Uri uri2;
                    if (parcel.readInt() != 0) {
                        uri2 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        uri2 = null;
                    }
                    final ParcelFileDescriptor f = this.f(uri2);
                    parcel2.writeNoException();
                    if (f != null) {
                        parcel2.writeInt(1);
                        f.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 7001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.k(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 7002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 7003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m24 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string21 = parcel.readString();
                    n = parcel.readInt();
                    final IBinder strongBinder6 = parcel.readStrongBinder();
                    Bundle bundle11 = bundle2;
                    if (parcel.readInt() != 0) {
                        bundle11 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.b(m24, string21, n, strongBinder6, bundle11);
                    parcel2.writeNoException();
                    return true;
                }
                case 8001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 8002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.aI(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 8004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m25 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    final String[] stringArray2 = parcel.createStringArray();
                    Bundle bundle12 = bundle3;
                    if (parcel.readInt() != 0) {
                        bundle12 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.a(m25, n, n2, stringArray2, bundle12);
                    parcel2.writeNoException();
                    return true;
                }
                case 8005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.l(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.m(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), parcel.readString(), (ParticipantResult[])parcel.createTypedArray((Parcelable$Creator)ParticipantResult.CREATOR));
                    parcel2.writeNoException();
                    return true;
                }
                case 8008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), (ParticipantResult[])parcel.createTypedArray((Parcelable$Creator)ParticipantResult.CREATOR));
                    parcel2.writeNoException();
                    return true;
                }
                case 8009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.n(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8010: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.o(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8011: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8012: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 8013: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.q(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 8014: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.p(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8024: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.gA();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 8025: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.k(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8015: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.d(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8016: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.e(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8017: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 8026: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 8018: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8019: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8020: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8021: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8022: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.gJ();
                    parcel2.writeNoException();
                    return true;
                }
                case 8023: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m26 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string22 = parcel.readString();
                    n = parcel.readInt();
                    boolean b45 = b18;
                    if (parcel.readInt() != 0) {
                        b45 = true;
                    }
                    this.a(m26, string22, n, b45);
                    parcel2.writeNoException();
                    return true;
                }
                case 8027: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m27 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    boolean b46 = b19;
                    if (parcel.readInt() != 0) {
                        b46 = true;
                    }
                    this.c(m27, b46);
                    parcel2.writeNoException();
                    return true;
                }
                case 9001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m28 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string23 = parcel.readString();
                    n = parcel.readInt();
                    this.c(m28, string23, n, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 9002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.q(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 9003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent gp = this.gp();
                    parcel2.writeNoException();
                    if (gp != null) {
                        parcel2.writeInt(1);
                        gp.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent aa = this.aA(parcel.readString());
                    parcel2.writeNoException();
                    if (aa != null) {
                        parcel2.writeInt(1);
                        aa.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent gq = this.gq();
                    parcel2.writeNoException();
                    if (gq != null) {
                        parcel2.writeInt(1);
                        gq.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent gr = this.gr();
                    parcel2.writeNoException();
                    if (gr != null) {
                        parcel2.writeInt(1);
                        gr.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent gs = this.gs();
                    parcel2.writeNoException();
                    if (gs != null) {
                        parcel2.writeInt(1);
                        gs.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    final Intent a = this.a(n, n2, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    final Intent b47 = this.b(n, n2, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (b47 != null) {
                        parcel2.writeInt(1);
                        b47.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9010: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent gw = this.gw();
                    parcel2.writeNoException();
                    if (gw != null) {
                        parcel2.writeInt(1);
                        gw.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9011: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    RoomEntity roomEntity;
                    if (parcel.readInt() != 0) {
                        roomEntity = (RoomEntity)RoomEntity.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        roomEntity = null;
                    }
                    final Intent a2 = this.a(roomEntity, parcel.readInt());
                    parcel2.writeNoException();
                    if (a2 != null) {
                        parcel2.writeInt(1);
                        a2.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9012: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent gx = this.gx();
                    parcel2.writeNoException();
                    if (gx != null) {
                        parcel2.writeInt(1);
                        gx.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9013: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent gk = this.gK();
                    parcel2.writeNoException();
                    if (gk != null) {
                        parcel2.writeInt(1);
                        gk.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9031: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final ParticipantEntity[] array = (ParticipantEntity[])parcel.createTypedArray((Parcelable$Creator)ParticipantEntity.CREATOR);
                    final String string24 = parcel.readString();
                    final String string25 = parcel.readString();
                    Uri uri3;
                    if (parcel.readInt() != 0) {
                        uri3 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        uri3 = null;
                    }
                    if (parcel.readInt() != 0) {
                        uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                    }
                    final Intent a3 = this.a(array, string24, string25, uri3, uri);
                    parcel2.writeNoException();
                    if (a3 != null) {
                        parcel2.writeInt(1);
                        a3.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9019: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.gy();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 9020: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m29 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string26 = parcel.readString();
                    n = parcel.readInt();
                    this.d(m29, string26, n, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 9028: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m30 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string27 = parcel.readString();
                    final String string28 = parcel.readString();
                    n = parcel.readInt();
                    this.a(m30, string27, string28, n, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 9030: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final ParcelFileDescriptor aj = this.aJ(parcel.readString());
                    parcel2.writeNoException();
                    if (aj != null) {
                        parcel2.writeInt(1);
                        aj.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 10001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 10002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.r(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 10003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 10004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 10005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.createStringArray(), parcel.readInt(), parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 10006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 10007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 10008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 10009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 10010: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 10011: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 10012: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent a4 = this.a(parcel.readInt(), parcel.createByteArray(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (a4 != null) {
                        parcel2.writeInt(1);
                        a4.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 10013: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.gC();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 10023: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.gD();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 10015: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent gb = this.gB();
                    parcel2.writeNoException();
                    if (gb != null) {
                        parcel2.writeInt(1);
                        gb.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 10022: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    GameRequestCluster at = gameRequestCluster;
                    if (parcel.readInt() != 0) {
                        at = GameRequestCluster.CREATOR.at(parcel);
                    }
                    final Intent a5 = this.a(at, parcel.readString());
                    parcel2.writeNoException();
                    if (a5 != null) {
                        parcel2.writeInt(1);
                        a5.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 10014: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.o(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 10016: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 10017: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final IGamesCallbacks m31 = IGamesCallbacks.Stub.M(parcel.readStrongBinder());
                    final String string29 = parcel.readString();
                    n = parcel.readInt();
                    boolean b48 = b20;
                    if (parcel.readInt() != 0) {
                        b48 = true;
                    }
                    this.b(m31, string29, n, b48);
                    parcel2.writeNoException();
                    return true;
                }
                case 10021: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    ZInvitationCluster as = zInvitationCluster;
                    if (parcel.readInt() != 0) {
                        as = ZInvitationCluster.CREATOR.as(parcel);
                    }
                    final Intent a6 = this.a(as, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (a6 != null) {
                        parcel2.writeInt(1);
                        a6.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 10018: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 10019: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 10020: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(IGamesCallbacks.Stub.M(parcel.readStrongBinder()), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 11001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.j(IGamesCallbacks.Stub.M(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 11002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.gL();
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class Proxy implements IGamesService
        {
            private IBinder kn;
            
            Proxy(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void A(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5068, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int a(final IGamesCallbacks gamesCallbacks, final byte[] array, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeByteArray(array);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(5033, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent a(int n, final int n2, final boolean b) throws RemoteException {
                final int n3 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    n = n3;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(9008, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent a(final int n, final byte[] array, final int n2, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeInt(n);
                    obtain.writeByteArray(array);
                    obtain.writeInt(n2);
                    obtain.writeString(s);
                    this.kn.transact(10012, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent a(final ZInvitationCluster zInvitationCluster, final String s, final String s2) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                        if (zInvitationCluster != null) {
                            obtain.writeInt(1);
                            zInvitationCluster.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        obtain.writeString(s);
                        obtain.writeString(s2);
                        this.kn.transact(10021, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (Intent)Intent.CREATOR.createFromParcel(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
            
            @Override
            public Intent a(final GameRequestCluster gameRequestCluster, final String s) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                        if (gameRequestCluster != null) {
                            obtain.writeInt(1);
                            gameRequestCluster.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        obtain.writeString(s);
                        this.kn.transact(10022, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (Intent)Intent.CREATOR.createFromParcel(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
            
            @Override
            public Intent a(final RoomEntity roomEntity, final int n) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                        if (roomEntity != null) {
                            obtain.writeInt(1);
                            roomEntity.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        obtain.writeInt(n);
                        this.kn.transact(9011, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (Intent)Intent.CREATOR.createFromParcel(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
            
            @Override
            public Intent a(final ParticipantEntity[] array, final String s, final String s2, final Uri uri, final Uri uri2) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                            obtain.writeTypedArray((Parcelable[])array, 0);
                            obtain.writeString(s);
                            obtain.writeString(s2);
                            if (uri != null) {
                                obtain.writeInt(1);
                                uri.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (uri2 != null) {
                                obtain.writeInt(1);
                                uri2.writeToParcel(obtain, 0);
                                this.kn.transact(9031, obtain, obtain2, 0);
                                obtain2.readException();
                                if (obtain2.readInt() != 0) {
                                    return (Intent)Intent.CREATOR.createFromParcel(obtain2);
                                }
                                return null;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                    return null;
                }
            }
            
            @Override
            public void a(final long n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(n);
                    obtain.writeString(s);
                    this.kn.transact(8019, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(binder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5005, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(5002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    this.kn.transact(10016, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final int n, final int n2, final int n3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeInt(n3);
                    this.kn.transact(10009, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, int n, final int n2, final boolean b, final boolean b2) throws RemoteException {
                final int n3 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n3;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5044, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final int n, final int n2, final String[] array, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeStringArray(array);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(8004, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5015, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final int n, final int[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeIntArray(array);
                    this.kn.transact(10018, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeLong(n);
                    this.kn.transact(5058, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final long n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeLong(n);
                    obtain.writeString(s);
                    this.kn.transact(8018, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final Bundle bundle, final int n, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    this.kn.transact(5021, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final IBinder binder, int n, final String[] array, final Bundle bundle, final boolean b, final long n2) throws RemoteException {
            Label_0152_Outer:
                while (true) {
                    final int n3 = 1;
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        while (true) {
                            Label_0157: {
                                try {
                                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                                    IBinder binder2;
                                    if (gamesCallbacks != null) {
                                        binder2 = gamesCallbacks.asBinder();
                                    }
                                    else {
                                        binder2 = null;
                                    }
                                    obtain.writeStrongBinder(binder2);
                                    obtain.writeStrongBinder(binder);
                                    obtain.writeInt(n);
                                    obtain.writeStringArray(array);
                                    if (bundle != null) {
                                        obtain.writeInt(1);
                                        bundle.writeToParcel(obtain, 0);
                                        break Label_0157;
                                    }
                                    obtain.writeInt(0);
                                    break Label_0157;
                                    obtain.writeInt(n);
                                    obtain.writeLong(n2);
                                    this.kn.transact(5030, obtain, obtain2, 0);
                                    obtain2.readException();
                                    return;
                                }
                                finally {
                                    obtain2.recycle();
                                    obtain.recycle();
                                }
                                n = 0;
                                continue Label_0152_Outer;
                            }
                            if (b) {
                                n = n3;
                                continue Label_0152_Outer;
                            }
                            break;
                        }
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final IBinder binder, final String s, final boolean b, final long n) throws RemoteException {
                int n2 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder2;
                    if (gamesCallbacks != null) {
                        binder2 = gamesCallbacks.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    if (b) {
                        n2 = 1;
                    }
                    obtain.writeInt(n2);
                    obtain.writeLong(n);
                    this.kn.transact(5031, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(5014, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.kn.transact(10011, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, int n, final int n2, final int n3, final boolean b) throws RemoteException {
                final int n4 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeInt(n3);
                    n = n4;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5019, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final int n, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder2;
                    if (gamesCallbacks != null) {
                        binder2 = gamesCallbacks.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    obtain.writeStrongBinder(binder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5025, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b) throws RemoteException {
                final int n2 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    n = n2;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(8023, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5045, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2, final boolean b3, final boolean b4) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b3) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b4) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(6501, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final int n, final int[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    obtain.writeIntArray(array);
                    this.kn.transact(10019, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeLong(n);
                    this.kn.transact(5016, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final long n, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeLong(n);
                    obtain.writeString(s2);
                    this.kn.transact(7002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder2;
                    if (gamesCallbacks != null) {
                        binder2 = gamesCallbacks.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    obtain.writeStrongBinder(binder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5023, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(5038, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final int n, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    this.kn.transact(8001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final int n, final int n2, final int n3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeInt(n3);
                    this.kn.transact(10010, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, int n, final int n2, final int n3, final boolean b) throws RemoteException {
                final int n4 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeInt(n3);
                    n = n4;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5039, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(9028, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(6002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeStringArray(array);
                    this.kn.transact(10008, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5054, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final byte[] array, final String s2, final ParticipantResult[] array2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeByteArray(array);
                    obtain.writeString(s2);
                    obtain.writeTypedArray((Parcelable[])array2, 0);
                    this.kn.transact(8007, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final byte[] array, final ParticipantResult[] array2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeByteArray(array);
                    obtain.writeTypedArray((Parcelable[])array2, 0);
                    this.kn.transact(8008, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final int[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeIntArray(array);
                    this.kn.transact(8017, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String s, final String[] array, final int n, final byte[] array2, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeStringArray(array);
                    obtain.writeInt(n);
                    obtain.writeByteArray(array2);
                    obtain.writeInt(n2);
                    this.kn.transact(10005, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(6001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final boolean b, final Bundle bundle) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (!b) {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5063, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final int[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeIntArray(array);
                    this.kn.transact(8003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final IGamesCallbacks gamesCallbacks, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeStringArray(array);
                    this.kn.transact(10006, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent aA(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.kn.transact(9004, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String aD(String string) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(string);
                    this.kn.transact(5064, obtain, obtain2, 0);
                    obtain2.readException();
                    string = obtain2.readString();
                    return string;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String aE(String string) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(string);
                    this.kn.transact(5035, obtain, obtain2, 0);
                    obtain2.readException();
                    string = obtain2.readString();
                    return string;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void aF(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.kn.transact(5050, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int aG(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.kn.transact(5060, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Uri aH(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.kn.transact(5066, obtain, obtain2, 0);
                    obtain2.readException();
                    Uri uri;
                    if (obtain2.readInt() != 0) {
                        uri = (Uri)Uri.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        uri = null;
                    }
                    return uri;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void aI(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.kn.transact(8002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public ParcelFileDescriptor aJ(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.kn.transact(9030, obtain, obtain2, 0);
                    obtain2.readException();
                    ParcelFileDescriptor parcelFileDescriptor;
                    if (obtain2.readInt() != 0) {
                        parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        parcelFileDescriptor = null;
                    }
                    return parcelFileDescriptor;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void aY(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeInt(n);
                    this.kn.transact(5036, obtain, obtain2, 0);
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
            public int b(final byte[] array, final String s, final String[] array2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeByteArray(array);
                    obtain.writeString(s);
                    obtain.writeStringArray(array2);
                    this.kn.transact(5034, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent b(int n, final int n2, final boolean b) throws RemoteException {
                final int n3 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    n = n3;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(9009, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final long n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(n);
                    obtain.writeString(s);
                    this.kn.transact(8021, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(5017, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5046, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeLong(n);
                    this.kn.transact(8012, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final long n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeLong(n);
                    obtain.writeString(s);
                    this.kn.transact(8020, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(5018, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String s, int n, final int n2, final int n3, final boolean b) throws RemoteException {
                final int n4 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeInt(n3);
                    n = n4;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5020, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String s, final int n, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder2;
                    if (gamesCallbacks != null) {
                        binder2 = gamesCallbacks.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    obtain.writeStrongBinder(binder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(7003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b) throws RemoteException {
                final int n2 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    n = n2;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(10017, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5501, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String s, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder2;
                    if (gamesCallbacks != null) {
                        binder2 = gamesCallbacks.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s);
                    obtain.writeStrongBinder(binder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5024, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(5041, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String s, final String s2, int n, final int n2, final int n3, final boolean b) throws RemoteException {
                final int n4 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeInt(n3);
                    n = n4;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5040, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(6506, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String s, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(6502, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(6503, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final IGamesCallbacks gamesCallbacks, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeStringArray(array);
                    this.kn.transact(10007, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final String s, final String s2, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeInt(n);
                    this.kn.transact(5051, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final long n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(n);
                    obtain.writeString(s);
                    this.kn.transact(10004, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final IGamesCallbacks gamesCallbacks) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(5022, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final IGamesCallbacks gamesCallbacks, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5048, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final IGamesCallbacks gamesCallbacks, final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeLong(n);
                    this.kn.transact(10001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final IGamesCallbacks gamesCallbacks, final long n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeLong(n);
                    obtain.writeString(s);
                    this.kn.transact(10003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(5032, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(9001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final IGamesCallbacks gamesCallbacks, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(8011, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final IGamesCallbacks gamesCallbacks, final String s, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(6504, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final IGamesCallbacks gamesCallbacks, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(8027, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final IGamesCallbacks gamesCallbacks, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeStringArray(array);
                    this.kn.transact(10020, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final String s, final String s2, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeInt(n);
                    this.kn.transact(8026, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final IGamesCallbacks gamesCallbacks) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(5026, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final IGamesCallbacks gamesCallbacks, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(6003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(5037, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(9020, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final IGamesCallbacks gamesCallbacks, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(8015, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final IGamesCallbacks gamesCallbacks, final String s, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(6505, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Bundle dG() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(5004, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle;
                    if (obtain2.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        bundle = null;
                    }
                    return bundle;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final IGamesCallbacks gamesCallbacks) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(5027, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final IGamesCallbacks gamesCallbacks, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    if (b2) {
                        n = n2;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(6004, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(5042, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final IGamesCallbacks gamesCallbacks, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(8016, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public ParcelFileDescriptor f(final Uri uri) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                        if (uri != null) {
                            obtain.writeInt(1);
                            uri.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.kn.transact(6507, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
            
            @Override
            public void f(final IGamesCallbacks gamesCallbacks) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(5047, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(5043, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void g(final IGamesCallbacks gamesCallbacks) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(5049, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void g(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(5052, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int gA() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(8024, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent gB() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(10015, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int gC() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(10013, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int gD() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(10023, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void gF() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(5006, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public DataHolder gG() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(5013, obtain, obtain2, 0);
                    obtain2.readException();
                    DataHolder fromParcel;
                    if (obtain2.readInt() != 0) {
                        fromParcel = DataHolder.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        fromParcel = null;
                    }
                    return fromParcel;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean gH() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(5067, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public DataHolder gI() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(5502, obtain, obtain2, 0);
                    obtain2.readException();
                    DataHolder fromParcel;
                    if (obtain2.readInt() != 0) {
                        fromParcel = DataHolder.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        fromParcel = null;
                    }
                    return fromParcel;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void gJ() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(8022, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent gK() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(9013, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void gL() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(11002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String gl() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(5007, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String gm() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(5012, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent gp() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(9003, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent gq() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(9005, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent gr() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(9006, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent gs() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(9007, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent gw() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(9010, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent gx() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(9012, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int gy() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(9019, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String gz() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.kn.transact(5003, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public RoomEntity h(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final RoomEntity roomEntity = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(5053, obtain, obtain2, 0);
                    obtain2.readException();
                    RoomEntity roomEntity2 = roomEntity;
                    if (obtain2.readInt() != 0) {
                        roomEntity2 = (RoomEntity)RoomEntity.CREATOR.createFromParcel(obtain2);
                    }
                    return roomEntity2;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void h(final IGamesCallbacks gamesCallbacks) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(5056, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void i(final IGamesCallbacks gamesCallbacks) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(5062, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void i(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(5061, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void j(final IGamesCallbacks gamesCallbacks) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(11001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void j(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(5057, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void j(final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(5065, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void k(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(7001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void k(final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.kn.transact(8025, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void l(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(8005, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void l(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.kn.transact(5029, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void m(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(8006, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void m(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.kn.transact(5028, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void n(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(8009, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void n(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.kn.transact(5055, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void o(final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(n);
                    this.kn.transact(5001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void o(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(8010, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void o(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.kn.transact(10014, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void p(final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(n);
                    this.kn.transact(5059, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void p(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(8014, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void q(final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(n);
                    this.kn.transact(8013, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void q(final IGamesCallbacks gamesCallbacks, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.kn.transact(9002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void r(final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(n);
                    this.kn.transact(10002, obtain, obtain2, 0);
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
