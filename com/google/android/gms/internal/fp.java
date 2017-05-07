// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

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
import android.content.Intent;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import android.os.RemoteException;
import android.os.IInterface;

public interface fp extends IInterface
{
    int a(final fo p0, final byte[] p1, final String p2, final String p3) throws RemoteException;
    
    Intent a(final RoomEntity p0, final int p1) throws RemoteException;
    
    Intent a(final ParticipantEntity[] p0, final String p1, final String p2, final Uri p3, final Uri p4) throws RemoteException;
    
    void a(final long p0, final String p1) throws RemoteException;
    
    void a(final IBinder p0, final Bundle p1) throws RemoteException;
    
    void a(final fo p0) throws RemoteException;
    
    void a(final fo p0, final int p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void a(final fo p0, final int p1, final int p2, final String[] p3, final Bundle p4) throws RemoteException;
    
    void a(final fo p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void a(final fo p0, final long p1) throws RemoteException;
    
    void a(final fo p0, final long p1, final String p2) throws RemoteException;
    
    void a(final fo p0, final Bundle p1, final int p2, final int p3) throws RemoteException;
    
    void a(final fo p0, final IBinder p1, final int p2, final String[] p3, final Bundle p4, final boolean p5, final long p6) throws RemoteException;
    
    void a(final fo p0, final IBinder p1, final String p2, final boolean p3, final long p4) throws RemoteException;
    
    void a(final fo p0, final String p1) throws RemoteException;
    
    void a(final fo p0, final String p1, final int p2, final int p3, final int p4, final boolean p5) throws RemoteException;
    
    void a(final fo p0, final String p1, final int p2, final IBinder p3, final Bundle p4) throws RemoteException;
    
    void a(final fo p0, final String p1, final int p2, final boolean p3) throws RemoteException;
    
    void a(final fo p0, final String p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void a(final fo p0, final String p1, final int p2, final boolean p3, final boolean p4, final boolean p5, final boolean p6) throws RemoteException;
    
    void a(final fo p0, final String p1, final long p2) throws RemoteException;
    
    void a(final fo p0, final String p1, final long p2, final String p3) throws RemoteException;
    
    void a(final fo p0, final String p1, final IBinder p2, final Bundle p3) throws RemoteException;
    
    void a(final fo p0, final String p1, final String p2) throws RemoteException;
    
    void a(final fo p0, final String p1, final String p2, final int p3, final int p4) throws RemoteException;
    
    void a(final fo p0, final String p1, final String p2, final int p3, final int p4, final int p5, final boolean p6) throws RemoteException;
    
    void a(final fo p0, final String p1, final String p2, final int p3, final boolean p4, final boolean p5) throws RemoteException;
    
    void a(final fo p0, final String p1, final String p2, final boolean p3) throws RemoteException;
    
    void a(final fo p0, final String p1, final boolean p2) throws RemoteException;
    
    void a(final fo p0, final String p1, final boolean p2, final long[] p3) throws RemoteException;
    
    void a(final fo p0, final String p1, final byte[] p2, final String p3, final ParticipantResult[] p4) throws RemoteException;
    
    void a(final fo p0, final String p1, final byte[] p2, final ParticipantResult[] p3) throws RemoteException;
    
    void a(final fo p0, final String p1, final int[] p2) throws RemoteException;
    
    void a(final fo p0, final boolean p1) throws RemoteException;
    
    void a(final fo p0, final boolean p1, final Bundle p2) throws RemoteException;
    
    void a(final fo p0, final int[] p1) throws RemoteException;
    
    Bundle aU() throws RemoteException;
    
    String af(final String p0) throws RemoteException;
    
    String ag(final String p0) throws RemoteException;
    
    void ah(final String p0) throws RemoteException;
    
    int ai(final String p0) throws RemoteException;
    
    Uri aj(final String p0) throws RemoteException;
    
    void ak(final String p0) throws RemoteException;
    
    ParcelFileDescriptor al(final String p0) throws RemoteException;
    
    int b(final byte[] p0, final String p1, final String[] p2) throws RemoteException;
    
    void b(final long p0, final String p1) throws RemoteException;
    
    void b(final fo p0) throws RemoteException;
    
    void b(final fo p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void b(final fo p0, final long p1) throws RemoteException;
    
    void b(final fo p0, final long p1, final String p2) throws RemoteException;
    
    void b(final fo p0, final String p1) throws RemoteException;
    
    void b(final fo p0, final String p1, final int p2, final int p3, final int p4, final boolean p5) throws RemoteException;
    
    void b(final fo p0, final String p1, final int p2, final IBinder p3, final Bundle p4) throws RemoteException;
    
    void b(final fo p0, final String p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void b(final fo p0, final String p1, final IBinder p2, final Bundle p3) throws RemoteException;
    
    void b(final fo p0, final String p1, final String p2) throws RemoteException;
    
    void b(final fo p0, final String p1, final String p2, final int p3, final int p4, final int p5, final boolean p6) throws RemoteException;
    
    void b(final fo p0, final String p1, final String p2, final boolean p3) throws RemoteException;
    
    void b(final fo p0, final String p1, final boolean p2) throws RemoteException;
    
    void b(final fo p0, final boolean p1) throws RemoteException;
    
    void b(final String p0, final String p1, final int p2) throws RemoteException;
    
    void c(final fo p0) throws RemoteException;
    
    void c(final fo p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void c(final fo p0, final String p1) throws RemoteException;
    
    void c(final fo p0, final String p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void c(final fo p0, final String p1, final String p2) throws RemoteException;
    
    void c(final fo p0, final String p1, final boolean p2) throws RemoteException;
    
    void c(final fo p0, final boolean p1) throws RemoteException;
    
    void c(final String p0, final String p1, final int p2) throws RemoteException;
    
    void clearNotifications(final int p0) throws RemoteException;
    
    void d(final fo p0) throws RemoteException;
    
    void d(final fo p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void d(final fo p0, final String p1) throws RemoteException;
    
    void d(final fo p0, final String p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void d(final fo p0, final String p1, final String p2) throws RemoteException;
    
    void d(final fo p0, final String p1, final boolean p2) throws RemoteException;
    
    int dd() throws RemoteException;
    
    void df() throws RemoteException;
    
    DataHolder dg() throws RemoteException;
    
    boolean dh() throws RemoteException;
    
    DataHolder di() throws RemoteException;
    
    void dj() throws RemoteException;
    
    Intent dk() throws RemoteException;
    
    ParcelFileDescriptor e(final Uri p0) throws RemoteException;
    
    void e(final fo p0) throws RemoteException;
    
    void e(final fo p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void e(final fo p0, final String p1) throws RemoteException;
    
    void e(final fo p0, final String p1, final String p2) throws RemoteException;
    
    void e(final String p0, final String p1) throws RemoteException;
    
    void f(final fo p0) throws RemoteException;
    
    void f(final fo p0, final String p1) throws RemoteException;
    
    void f(final fo p0, final String p1, final String p2) throws RemoteException;
    
    void f(final String p0, final String p1) throws RemoteException;
    
    void g(final fo p0) throws RemoteException;
    
    void g(final fo p0, final String p1) throws RemoteException;
    
    Intent getAchievementsIntent() throws RemoteException;
    
    Intent getAllLeaderboardsIntent() throws RemoteException;
    
    String getAppId() throws RemoteException;
    
    String getCurrentAccountName() throws RemoteException;
    
    String getCurrentPlayerId() throws RemoteException;
    
    Intent getInvitationInboxIntent() throws RemoteException;
    
    Intent getLeaderboardIntent(final String p0) throws RemoteException;
    
    Intent getMatchInboxIntent() throws RemoteException;
    
    int getMaxTurnBasedMatchDataSize() throws RemoteException;
    
    Intent getPlayerSearchIntent() throws RemoteException;
    
    Intent getRealTimeSelectOpponentsIntent(final int p0, final int p1, final boolean p2) throws RemoteException;
    
    Intent getSettingsIntent() throws RemoteException;
    
    Intent getTurnBasedSelectOpponentsIntent(final int p0, final int p1, final boolean p2) throws RemoteException;
    
    void h(final fo p0) throws RemoteException;
    
    void h(final fo p0, final String p1) throws RemoteException;
    
    void i(final fo p0) throws RemoteException;
    
    void i(final fo p0, final String p1) throws RemoteException;
    
    void i(final String p0, final int p1) throws RemoteException;
    
    DataHolder j(final fo p0, final String p1) throws RemoteException;
    
    void j(final long p0) throws RemoteException;
    
    void j(final String p0, final int p1) throws RemoteException;
    
    void k(final long p0) throws RemoteException;
    
    void k(final fo p0, final String p1) throws RemoteException;
    
    void k(final String p0, final int p1) throws RemoteException;
    
    void l(final long p0) throws RemoteException;
    
    void l(final fo p0, final String p1) throws RemoteException;
    
    void m(final fo p0, final String p1) throws RemoteException;
    
    void n(final fo p0, final String p1) throws RemoteException;
    
    void o(final fo p0, final String p1) throws RemoteException;
    
    void p(final fo p0, final String p1) throws RemoteException;
    
    void q(final fo p0, final String p1) throws RemoteException;
    
    void r(final fo p0, final String p1) throws RemoteException;
    
    void s(final fo p0, final String p1) throws RemoteException;
    
    void s(final boolean p0) throws RemoteException;
    
    public abstract static class a extends Binder implements fp
    {
        public static fp H(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
            if (queryLocalInterface != null && queryLocalInterface instanceof fp) {
                return (fp)queryLocalInterface;
            }
            return new fp.a.a(binder);
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, int n2) throws RemoteException {
            final Bundle bundle = null;
            final Bundle bundle2 = null;
            final Bundle bundle3 = null;
            Uri uri = null;
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
            boolean b20 = false;
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
                    this.j(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final String appId = this.getAppId();
                    parcel2.writeNoException();
                    parcel2.writeString(appId);
                    return true;
                }
                case 5004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Bundle au = this.aU();
                    parcel2.writeNoException();
                    if (au != null) {
                        parcel2.writeInt(1);
                        au.writeToParcel(parcel2, 1);
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
                    this.df();
                    parcel2.writeNoException();
                    return true;
                }
                case 5007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final String currentAccountName = this.getCurrentAccountName();
                    parcel2.writeNoException();
                    parcel2.writeString(currentAccountName);
                    return true;
                }
                case 5064: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final String af = this.af(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(af);
                    return true;
                }
                case 5065: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.e(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5010: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5011: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0, parcel.createLongArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 5012: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final String currentPlayerId = this.getCurrentPlayerId();
                    parcel2.writeNoException();
                    parcel2.writeString(currentPlayerId);
                    return true;
                }
                case 5013: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final DataHolder dg = this.dg();
                    parcel2.writeNoException();
                    if (dg != null) {
                        parcel2.writeInt(1);
                        dg.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 5014: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5015: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g = fo.a.G(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final boolean b21 = parcel.readInt() != 0;
                    if (parcel.readInt() != 0) {
                        b20 = true;
                    }
                    this.a(g, n, b21, b20);
                    parcel2.writeNoException();
                    return true;
                }
                case 5016: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5017: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(fo.a.G(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5018: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.d(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5019: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g2 = fo.a.G(parcel.readStrongBinder());
                    final String string = parcel.readString();
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    this.a(g2, string, n, n2, parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5020: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g3 = fo.a.G(parcel.readStrongBinder());
                    final String string2 = parcel.readString();
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    this.b(g3, string2, n, n2, parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5021: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g4 = fo.a.G(parcel.readStrongBinder());
                    Bundle bundle6;
                    if (parcel.readInt() != 0) {
                        bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle6 = null;
                    }
                    this.a(g4, bundle6, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5022: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(fo.a.G(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5023: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g5 = fo.a.G(parcel.readStrongBinder());
                    final String string3 = parcel.readString();
                    final IBinder strongBinder2 = parcel.readStrongBinder();
                    Bundle bundle7;
                    if (parcel.readInt() != 0) {
                        bundle7 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle7 = null;
                    }
                    this.a(g5, string3, strongBinder2, bundle7);
                    parcel2.writeNoException();
                    return true;
                }
                case 5024: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g6 = fo.a.G(parcel.readStrongBinder());
                    final String string4 = parcel.readString();
                    final IBinder strongBinder3 = parcel.readStrongBinder();
                    Bundle bundle8;
                    if (parcel.readInt() != 0) {
                        bundle8 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle8 = null;
                    }
                    this.b(g6, string4, strongBinder3, bundle8);
                    parcel2.writeNoException();
                    return true;
                }
                case 5025: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g7 = fo.a.G(parcel.readStrongBinder());
                    final String string5 = parcel.readString();
                    n = parcel.readInt();
                    final IBinder strongBinder4 = parcel.readStrongBinder();
                    if (parcel.readInt() != 0) {
                        bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.a(g7, string5, n, strongBinder4, bundle4);
                    parcel2.writeNoException();
                    return true;
                }
                case 5026: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.d(fo.a.G(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5027: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.e(fo.a.G(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5028: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.j(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5029: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.i(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5058: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5059: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.k(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5030: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g8 = fo.a.G(parcel.readStrongBinder());
                    final IBinder strongBinder5 = parcel.readStrongBinder();
                    n = parcel.readInt();
                    final String[] stringArray = parcel.createStringArray();
                    Bundle bundle9 = bundle;
                    if (parcel.readInt() != 0) {
                        bundle9 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.a(g8, strongBinder5, n, stringArray, bundle9, parcel.readInt() != 0, parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5031: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readStrongBinder(), parcel.readString(), parcel.readInt() != 0, parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 5032: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.e(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5033: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.a(fo.a.G(parcel.readStrongBinder()), parcel.createByteArray(), parcel.readString(), parcel.readString());
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
                    final String ag = this.ag(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(ag);
                    return true;
                }
                case 5036: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.clearNotifications(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5037: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.f(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5038: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5039: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g9 = fo.a.G(parcel.readStrongBinder());
                    final String string6 = parcel.readString();
                    final String string7 = parcel.readString();
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    final int int1 = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        b = true;
                    }
                    this.a(g9, string6, string7, n, n2, int1, b);
                    parcel2.writeNoException();
                    return true;
                }
                case 5040: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g10 = fo.a.G(parcel.readStrongBinder());
                    final String string8 = parcel.readString();
                    final String string9 = parcel.readString();
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    final int int2 = parcel.readInt();
                    boolean b22 = b2;
                    if (parcel.readInt() != 0) {
                        b22 = true;
                    }
                    this.b(g10, string8, string9, n, n2, int2, b22);
                    parcel2.writeNoException();
                    return true;
                }
                case 5041: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.c(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5042: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.g(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5043: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.h(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5044: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g11 = fo.a.G(parcel.readStrongBinder());
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    this.a(g11, n, n2, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5045: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g12 = fo.a.G(parcel.readStrongBinder());
                    final String string10 = parcel.readString();
                    n = parcel.readInt();
                    this.a(g12, string10, n, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5046: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g13 = fo.a.G(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final boolean b23 = parcel.readInt() != 0;
                    boolean b24 = b3;
                    if (parcel.readInt() != 0) {
                        b24 = true;
                    }
                    this.b(g13, n, b23, b24);
                    parcel2.writeNoException();
                    return true;
                }
                case 5047: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.f(fo.a.G(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5048: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g14 = fo.a.G(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final boolean b25 = parcel.readInt() != 0;
                    boolean b26 = b4;
                    if (parcel.readInt() != 0) {
                        b26 = true;
                    }
                    this.c(g14, n, b25, b26);
                    parcel2.writeNoException();
                    return true;
                }
                case 5049: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.g(fo.a.G(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5050: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.ah(parcel.readString());
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
                    this.i(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5053: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final DataHolder j = this.j(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    if (j != null) {
                        parcel2.writeInt(1);
                        j.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 5060: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.ai(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 5054: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g15 = fo.a.G(parcel.readStrongBinder());
                    final String string11 = parcel.readString();
                    boolean b27 = b5;
                    if (parcel.readInt() != 0) {
                        b27 = true;
                    }
                    this.a(g15, string11, b27);
                    parcel2.writeNoException();
                    return true;
                }
                case 5061: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.k(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5055: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.k(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5067: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final boolean dh = this.dh();
                    parcel2.writeNoException();
                    n = n3;
                    if (dh) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 5068: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    boolean b28 = b6;
                    if (parcel.readInt() != 0) {
                        b28 = true;
                    }
                    this.s(b28);
                    parcel2.writeNoException();
                    return true;
                }
                case 5056: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.h(fo.a.G(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5057: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.l(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5062: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.i(fo.a.G(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5063: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g16 = fo.a.G(parcel.readStrongBinder());
                    boolean b29 = b7;
                    if (parcel.readInt() != 0) {
                        b29 = true;
                    }
                    Bundle bundle10;
                    if (parcel.readInt() != 0) {
                        bundle10 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle10 = null;
                    }
                    this.a(g16, b29, bundle10);
                    parcel2.writeNoException();
                    return true;
                }
                case 5066: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Uri aj = this.aj(parcel.readString());
                    parcel2.writeNoException();
                    if (aj != null) {
                        parcel2.writeInt(1);
                        aj.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 5501: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g17 = fo.a.G(parcel.readStrongBinder());
                    final String string12 = parcel.readString();
                    n = parcel.readInt();
                    this.b(g17, string12, n, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5502: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final DataHolder di = this.di();
                    parcel2.writeNoException();
                    if (di != null) {
                        parcel2.writeInt(1);
                        di.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 6001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g18 = fo.a.G(parcel.readStrongBinder());
                    boolean b30 = b8;
                    if (parcel.readInt() != 0) {
                        b30 = true;
                    }
                    this.a(g18, b30);
                    parcel2.writeNoException();
                    return true;
                }
                case 6002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g19 = fo.a.G(parcel.readStrongBinder());
                    final String string13 = parcel.readString();
                    final String string14 = parcel.readString();
                    boolean b31 = b9;
                    if (parcel.readInt() != 0) {
                        b31 = true;
                    }
                    this.a(g19, string13, string14, b31);
                    parcel2.writeNoException();
                    return true;
                }
                case 6003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g20 = fo.a.G(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final boolean b32 = parcel.readInt() != 0;
                    boolean b33 = b10;
                    if (parcel.readInt() != 0) {
                        b33 = true;
                    }
                    this.d(g20, n, b32, b33);
                    parcel2.writeNoException();
                    return true;
                }
                case 6004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g21 = fo.a.G(parcel.readStrongBinder());
                    n = parcel.readInt();
                    final boolean b34 = parcel.readInt() != 0;
                    boolean b35 = b11;
                    if (parcel.readInt() != 0) {
                        b35 = true;
                    }
                    this.e(g21, n, b34, b35);
                    parcel2.writeNoException();
                    return true;
                }
                case 6501: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g22 = fo.a.G(parcel.readStrongBinder());
                    final String string15 = parcel.readString();
                    n = parcel.readInt();
                    final boolean b36 = parcel.readInt() != 0;
                    final boolean b37 = parcel.readInt() != 0;
                    final boolean b38 = parcel.readInt() != 0;
                    if (parcel.readInt() != 0) {
                        b12 = true;
                    }
                    this.a(g22, string15, n, b36, b37, b38, b12);
                    parcel2.writeNoException();
                    return true;
                }
                case 6502: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g23 = fo.a.G(parcel.readStrongBinder());
                    final String string16 = parcel.readString();
                    boolean b39 = b13;
                    if (parcel.readInt() != 0) {
                        b39 = true;
                    }
                    this.b(g23, string16, b39);
                    parcel2.writeNoException();
                    return true;
                }
                case 6503: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g24 = fo.a.G(parcel.readStrongBinder());
                    boolean b40 = b14;
                    if (parcel.readInt() != 0) {
                        b40 = true;
                    }
                    this.b(g24, b40);
                    parcel2.writeNoException();
                    return true;
                }
                case 6504: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g25 = fo.a.G(parcel.readStrongBinder());
                    final String string17 = parcel.readString();
                    boolean b41 = b15;
                    if (parcel.readInt() != 0) {
                        b41 = true;
                    }
                    this.c(g25, string17, b41);
                    parcel2.writeNoException();
                    return true;
                }
                case 6505: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g26 = fo.a.G(parcel.readStrongBinder());
                    final String string18 = parcel.readString();
                    boolean b42 = b16;
                    if (parcel.readInt() != 0) {
                        b42 = true;
                    }
                    this.d(g26, string18, b42);
                    parcel2.writeNoException();
                    return true;
                }
                case 6506: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g27 = fo.a.G(parcel.readStrongBinder());
                    final String string19 = parcel.readString();
                    final String string20 = parcel.readString();
                    boolean b43 = b17;
                    if (parcel.readInt() != 0) {
                        b43 = true;
                    }
                    this.b(g27, string19, string20, b43);
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
                    final ParcelFileDescriptor e = this.e(uri2);
                    parcel2.writeNoException();
                    if (e != null) {
                        parcel2.writeInt(1);
                        e.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 7001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.m(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 7002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 7003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g28 = fo.a.G(parcel.readStrongBinder());
                    final String string21 = parcel.readString();
                    n = parcel.readInt();
                    final IBinder strongBinder6 = parcel.readStrongBinder();
                    Bundle bundle11 = bundle2;
                    if (parcel.readInt() != 0) {
                        bundle11 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.b(g28, string21, n, strongBinder6, bundle11);
                    parcel2.writeNoException();
                    return true;
                }
                case 8001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 8002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.ak(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 8004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g29 = fo.a.G(parcel.readStrongBinder());
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    final String[] stringArray2 = parcel.createStringArray();
                    Bundle bundle12 = bundle3;
                    if (parcel.readInt() != 0) {
                        bundle12 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.a(g29, n, n2, stringArray2, bundle12);
                    parcel2.writeNoException();
                    return true;
                }
                case 8005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.n(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.o(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), parcel.readString(), (ParticipantResult[])parcel.createTypedArray((Parcelable$Creator)ParticipantResult.CREATOR));
                    parcel2.writeNoException();
                    return true;
                }
                case 8008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray(), (ParticipantResult[])parcel.createTypedArray((Parcelable$Creator)ParticipantResult.CREATOR));
                    parcel2.writeNoException();
                    return true;
                }
                case 8009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.p(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8010: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.q(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8011: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.d(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8012: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.b(fo.a.G(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 8013: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.l(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 8014: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.r(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8024: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.getMaxTurnBasedMatchDataSize();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 8025: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.f(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8015: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.e(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8016: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.f(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8017: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readString(), parcel.createIntArray());
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
                    this.a(fo.a.G(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
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
                    this.b(fo.a.G(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
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
                    this.dj();
                    parcel2.writeNoException();
                    return true;
                }
                case 8023: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g30 = fo.a.G(parcel.readStrongBinder());
                    final String string22 = parcel.readString();
                    n = parcel.readInt();
                    boolean b44 = b18;
                    if (parcel.readInt() != 0) {
                        b44 = true;
                    }
                    this.a(g30, string22, n, b44);
                    parcel2.writeNoException();
                    return true;
                }
                case 8027: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g31 = fo.a.G(parcel.readStrongBinder());
                    boolean b45 = b19;
                    if (parcel.readInt() != 0) {
                        b45 = true;
                    }
                    this.c(g31, b45);
                    parcel2.writeNoException();
                    return true;
                }
                case 9001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g32 = fo.a.G(parcel.readStrongBinder());
                    final String string23 = parcel.readString();
                    n = parcel.readInt();
                    this.c(g32, string23, n, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 9002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    this.s(fo.a.G(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 9003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent allLeaderboardsIntent = this.getAllLeaderboardsIntent();
                    parcel2.writeNoException();
                    if (allLeaderboardsIntent != null) {
                        parcel2.writeInt(1);
                        allLeaderboardsIntent.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent leaderboardIntent = this.getLeaderboardIntent(parcel.readString());
                    parcel2.writeNoException();
                    if (leaderboardIntent != null) {
                        parcel2.writeInt(1);
                        leaderboardIntent.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent achievementsIntent = this.getAchievementsIntent();
                    parcel2.writeNoException();
                    if (achievementsIntent != null) {
                        parcel2.writeInt(1);
                        achievementsIntent.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent matchInboxIntent = this.getMatchInboxIntent();
                    parcel2.writeNoException();
                    if (matchInboxIntent != null) {
                        parcel2.writeInt(1);
                        matchInboxIntent.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent invitationInboxIntent = this.getInvitationInboxIntent();
                    parcel2.writeNoException();
                    if (invitationInboxIntent != null) {
                        parcel2.writeInt(1);
                        invitationInboxIntent.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    final Intent turnBasedSelectOpponentsIntent = this.getTurnBasedSelectOpponentsIntent(n, n2, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (turnBasedSelectOpponentsIntent != null) {
                        parcel2.writeInt(1);
                        turnBasedSelectOpponentsIntent.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = parcel.readInt();
                    n2 = parcel.readInt();
                    final Intent realTimeSelectOpponentsIntent = this.getRealTimeSelectOpponentsIntent(n, n2, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (realTimeSelectOpponentsIntent != null) {
                        parcel2.writeInt(1);
                        realTimeSelectOpponentsIntent.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9010: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent playerSearchIntent = this.getPlayerSearchIntent();
                    parcel2.writeNoException();
                    if (playerSearchIntent != null) {
                        parcel2.writeInt(1);
                        playerSearchIntent.writeToParcel(parcel2, 1);
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
                    final Intent a = this.a(roomEntity, parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9012: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent settingsIntent = this.getSettingsIntent();
                    parcel2.writeNoException();
                    if (settingsIntent != null) {
                        parcel2.writeInt(1);
                        settingsIntent.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9013: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final Intent dk = this.dk();
                    parcel2.writeNoException();
                    if (dk != null) {
                        parcel2.writeInt(1);
                        dk.writeToParcel(parcel2, 1);
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
                    final Intent a2 = this.a(array, string24, string25, uri3, uri);
                    parcel2.writeNoException();
                    if (a2 != null) {
                        parcel2.writeInt(1);
                        a2.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 9019: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    n = this.dd();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 9020: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g33 = fo.a.G(parcel.readStrongBinder());
                    final String string26 = parcel.readString();
                    n = parcel.readInt();
                    this.d(g33, string26, n, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 9028: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final fo g34 = fo.a.G(parcel.readStrongBinder());
                    final String string27 = parcel.readString();
                    final String string28 = parcel.readString();
                    n = parcel.readInt();
                    this.a(g34, string27, string28, n, parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 9030: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesService");
                    final ParcelFileDescriptor al = this.al(parcel.readString());
                    parcel2.writeNoException();
                    if (al != null) {
                        parcel2.writeInt(1);
                        al.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
            }
        }
        
        private static class a implements fp
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            @Override
            public int a(final fo fo, final byte[] array, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeByteArray(array);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.dU.transact(5033, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
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
                        this.dU.transact(9011, obtain, obtain2, 0);
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
                                this.dU.transact(9031, obtain, obtain2, 0);
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
                    this.dU.transact(8019, obtain, obtain2, 0);
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
                    this.dU.transact(5005, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(5002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, int n, final int n2, final boolean b, final boolean b2) throws RemoteException {
                final int n3 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5044, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final int n, final int n2, final String[] array, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(8004, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5015, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeLong(n);
                    this.dU.transact(5058, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final long n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeLong(n);
                    obtain.writeString(s);
                    this.dU.transact(8018, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final Bundle bundle, final int n, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5021, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final IBinder binder, int n, final String[] array, final Bundle bundle, final boolean b, final long n2) throws RemoteException {
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
                                    if (fo != null) {
                                        binder2 = fo.asBinder();
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
                                    this.dU.transact(5030, obtain, obtain2, 0);
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
            public void a(final fo fo, final IBinder binder, final String s, final boolean b, final long n) throws RemoteException {
                int n2 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder2;
                    if (fo != null) {
                        binder2 = fo.asBinder();
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
                    this.dU.transact(5031, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5008, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, int n, final int n2, final int n3, final boolean b) throws RemoteException {
                final int n4 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5019, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final int n, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder2;
                    if (fo != null) {
                        binder2 = fo.asBinder();
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
                    this.dU.transact(5025, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, int n, final boolean b) throws RemoteException {
                final int n2 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(8023, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5045, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, int n, final boolean b, final boolean b2, final boolean b3, final boolean b4) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(6501, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeLong(n);
                    this.dU.transact(5016, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final long n, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeLong(n);
                    obtain.writeString(s2);
                    this.dU.transact(7002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder2;
                    if (fo != null) {
                        binder2 = fo.asBinder();
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
                    this.dU.transact(5023, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.dU.transact(5009, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final String s2, final int n, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    this.dU.transact(8001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final String s2, int n, final int n2, final int n3, final boolean b) throws RemoteException {
                final int n4 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5039, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final String s2, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(9028, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final String s2, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(6002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5054, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final boolean b, final long[] array) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    obtain.writeLongArray(array);
                    this.dU.transact(5011, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final byte[] array, final String s2, final ParticipantResult[] array2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeByteArray(array);
                    obtain.writeString(s2);
                    obtain.writeTypedArray((Parcelable[])array2, 0);
                    this.dU.transact(8007, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final byte[] array, final ParticipantResult[] array2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeByteArray(array);
                    obtain.writeTypedArray((Parcelable[])array2, 0);
                    this.dU.transact(8008, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final String s, final int[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeIntArray(array);
                    this.dU.transact(8017, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(6001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final boolean b, final Bundle bundle) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5063, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final fo fo, final int[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeIntArray(array);
                    this.dU.transact(8003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Bundle aU() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(5004, obtain, obtain2, 0);
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
            public String af(String string) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(string);
                    this.dU.transact(5064, obtain, obtain2, 0);
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
            public String ag(String string) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(string);
                    this.dU.transact(5035, obtain, obtain2, 0);
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
            public void ah(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.dU.transact(5050, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int ai(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.dU.transact(5060, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Uri aj(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.dU.transact(5066, obtain, obtain2, 0);
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
            public void ak(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.dU.transact(8002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public ParcelFileDescriptor al(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.dU.transact(9030, obtain, obtain2, 0);
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
            
            public IBinder asBinder() {
                return this.dU;
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
                    this.dU.transact(5034, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
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
                    this.dU.transact(8021, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(5017, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5046, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeLong(n);
                    this.dU.transact(8012, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final long n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeLong(n);
                    obtain.writeString(s);
                    this.dU.transact(8020, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5010, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final String s, int n, final int n2, final int n3, final boolean b) throws RemoteException {
                final int n4 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5020, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final String s, final int n, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder2;
                    if (fo != null) {
                        binder2 = fo.asBinder();
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
                    this.dU.transact(7003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final String s, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5501, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final String s, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder2;
                    if (fo != null) {
                        binder2 = fo.asBinder();
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
                    this.dU.transact(5024, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.dU.transact(5038, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final String s, final String s2, int n, final int n2, final int n3, final boolean b) throws RemoteException {
                final int n4 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5040, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final String s, final String s2, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(6506, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final String s, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(6502, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final fo fo, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(6503, obtain, obtain2, 0);
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
                    this.dU.transact(5051, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final fo fo) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(5022, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final fo fo, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(5048, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5014, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final fo fo, final String s, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(9001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final fo fo, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.dU.transact(5041, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final fo fo, final String s, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(6504, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final fo fo, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(8027, obtain, obtain2, 0);
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
                    this.dU.transact(8026, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void clearNotifications(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeInt(n);
                    this.dU.transact(5036, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final fo fo) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(5026, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final fo fo, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(6003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5018, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final fo fo, final String s, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(9020, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final fo fo, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.dU.transact(8011, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final fo fo, final String s, final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(6505, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int dd() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(9019, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void df() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(5006, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public DataHolder dg() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(5013, obtain, obtain2, 0);
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
            public boolean dh() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(5067, obtain, obtain2, 0);
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
            public DataHolder di() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(5502, obtain, obtain2, 0);
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
            public void dj() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(8022, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent dk() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(9013, obtain, obtain2, 0);
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
            public ParcelFileDescriptor e(final Uri uri) throws RemoteException {
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
                        this.dU.transact(6507, obtain, obtain2, 0);
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
            public void e(final fo fo) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(5027, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final fo fo, int n, final boolean b, final boolean b2) throws RemoteException {
                final int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
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
                    this.dU.transact(6004, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5032, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final fo fo, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.dU.transact(8015, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.dU.transact(5065, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final fo fo) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(5047, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5037, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final fo fo, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.dU.transact(8016, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.dU.transact(8025, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void g(final fo fo) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(5049, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void g(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5042, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent getAchievementsIntent() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(9005, obtain, obtain2, 0);
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
            public Intent getAllLeaderboardsIntent() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(9003, obtain, obtain2, 0);
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
            public String getAppId() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(5003, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getCurrentAccountName() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(5007, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getCurrentPlayerId() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(5012, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent getInvitationInboxIntent() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(9007, obtain, obtain2, 0);
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
            public Intent getLeaderboardIntent(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    this.dU.transact(9004, obtain, obtain2, 0);
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
            public Intent getMatchInboxIntent() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(9006, obtain, obtain2, 0);
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
            public int getMaxTurnBasedMatchDataSize() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(8024, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent getPlayerSearchIntent() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(9010, obtain, obtain2, 0);
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
            public Intent getRealTimeSelectOpponentsIntent(int n, final int n2, final boolean b) throws RemoteException {
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
                    this.dU.transact(9009, obtain, obtain2, 0);
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
            public Intent getSettingsIntent() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.dU.transact(9012, obtain, obtain2, 0);
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
            public Intent getTurnBasedSelectOpponentsIntent(int n, final int n2, final boolean b) throws RemoteException {
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
                    this.dU.transact(9008, obtain, obtain2, 0);
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
            public void h(final fo fo) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(5056, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void h(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5043, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void i(final fo fo) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(5062, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void i(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5052, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void i(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.dU.transact(5029, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public DataHolder j(final fo fo, final String s) throws RemoteException {
                final DataHolder dataHolder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5053, obtain, obtain2, 0);
                    obtain2.readException();
                    DataHolder fromParcel = dataHolder;
                    if (obtain2.readInt() != 0) {
                        fromParcel = DataHolder.CREATOR.createFromParcel(obtain2);
                    }
                    return fromParcel;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void j(final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(n);
                    this.dU.transact(5001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void j(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.dU.transact(5028, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void k(final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(n);
                    this.dU.transact(5059, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void k(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5061, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void k(final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.dU.transact(5055, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void l(final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(n);
                    this.dU.transact(8013, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void l(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(5057, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void m(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(7001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void n(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(8005, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void o(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(8006, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void p(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(8009, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void q(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(8010, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void r(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(8014, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void s(final fo fo, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (fo != null) {
                        binder = fo.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.dU.transact(9002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void s(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(5068, obtain, obtain2, 0);
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
