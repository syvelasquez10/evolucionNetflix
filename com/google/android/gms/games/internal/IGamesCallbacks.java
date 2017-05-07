// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;

public interface IGamesCallbacks extends IInterface
{
    void A(final DataHolder p0) throws RemoteException;
    
    void B(final DataHolder p0) throws RemoteException;
    
    void C(final DataHolder p0) throws RemoteException;
    
    void D(final DataHolder p0) throws RemoteException;
    
    void E(final DataHolder p0) throws RemoteException;
    
    void F(final DataHolder p0) throws RemoteException;
    
    void a(final int p0, final Bundle p1) throws RemoteException;
    
    void a(final int p0, final String p1, final boolean p2) throws RemoteException;
    
    void a(final DataHolder p0, final DataHolder p1) throws RemoteException;
    
    void a(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void aU(final int p0) throws RemoteException;
    
    void aV(final int p0) throws RemoteException;
    
    void b(final int p0, final int p1, final String p2) throws RemoteException;
    
    void b(final int p0, final Bundle p1) throws RemoteException;
    
    void b(final DataHolder p0) throws RemoteException;
    
    void b(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void c(final int p0, final Bundle p1) throws RemoteException;
    
    void c(final DataHolder p0) throws RemoteException;
    
    void c(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void d(final int p0, final String p1) throws RemoteException;
    
    void d(final DataHolder p0) throws RemoteException;
    
    void d(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void du() throws RemoteException;
    
    void e(final int p0, final String p1) throws RemoteException;
    
    void e(final DataHolder p0) throws RemoteException;
    
    void e(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void f(final int p0, final String p1) throws RemoteException;
    
    void f(final DataHolder p0) throws RemoteException;
    
    void f(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void g(final DataHolder p0) throws RemoteException;
    
    void h(final DataHolder p0) throws RemoteException;
    
    void i(final DataHolder p0) throws RemoteException;
    
    void j(final DataHolder p0) throws RemoteException;
    
    void k(final DataHolder p0) throws RemoteException;
    
    void l(final DataHolder p0) throws RemoteException;
    
    void m(final DataHolder p0) throws RemoteException;
    
    void n(final DataHolder p0) throws RemoteException;
    
    void o(final DataHolder p0) throws RemoteException;
    
    void onInvitationRemoved(final String p0) throws RemoteException;
    
    void onLeftRoom(final int p0, final String p1) throws RemoteException;
    
    void onP2PConnected(final String p0) throws RemoteException;
    
    void onP2PDisconnected(final String p0) throws RemoteException;
    
    void onRealTimeMessageReceived(final RealTimeMessage p0) throws RemoteException;
    
    void onRequestRemoved(final String p0) throws RemoteException;
    
    void onTurnBasedMatchRemoved(final String p0) throws RemoteException;
    
    void p(final DataHolder p0) throws RemoteException;
    
    void q(final DataHolder p0) throws RemoteException;
    
    void r(final DataHolder p0) throws RemoteException;
    
    void s(final DataHolder p0) throws RemoteException;
    
    void t(final DataHolder p0) throws RemoteException;
    
    void u(final DataHolder p0) throws RemoteException;
    
    void v(final DataHolder p0) throws RemoteException;
    
    void w(final DataHolder p0) throws RemoteException;
    
    void x(final DataHolder p0) throws RemoteException;
    
    void y(final DataHolder p0) throws RemoteException;
    
    void z(final DataHolder p0) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IGamesCallbacks
    {
        public Stub() {
            this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IGamesCallbacks");
        }
        
        public static IGamesCallbacks M(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.games.internal.IGamesCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof IGamesCallbacks) {
                return (IGamesCallbacks)queryLocalInterface;
            }
            return new Proxy(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final DataHolder dataHolder = null;
            DataHolder fromParcel = null;
            final DataHolder dataHolder2 = null;
            final DataHolder dataHolder3 = null;
            final DataHolder dataHolder4 = null;
            final DataHolder dataHolder5 = null;
            final DataHolder dataHolder6 = null;
            final DataHolder dataHolder7 = null;
            final DataHolder dataHolder8 = null;
            final DataHolder dataHolder9 = null;
            final DataHolder dataHolder10 = null;
            final DataHolder dataHolder11 = null;
            final DataHolder dataHolder12 = null;
            final DataHolder dataHolder13 = null;
            final DataHolder dataHolder14 = null;
            final DataHolder dataHolder15 = null;
            final DataHolder dataHolder16 = null;
            final DataHolder dataHolder17 = null;
            final DataHolder dataHolder18 = null;
            final DataHolder dataHolder19 = null;
            final DataHolder dataHolder20 = null;
            final DataHolder dataHolder21 = null;
            final DataHolder dataHolder22 = null;
            final RealTimeMessage realTimeMessage = null;
            final DataHolder dataHolder23 = null;
            final DataHolder dataHolder24 = null;
            final DataHolder dataHolder25 = null;
            final DataHolder dataHolder26 = null;
            final Bundle bundle = null;
            final DataHolder dataHolder27 = null;
            final DataHolder dataHolder28 = null;
            final DataHolder dataHolder29 = null;
            final DataHolder dataHolder30 = null;
            final DataHolder dataHolder31 = null;
            final DataHolder dataHolder32 = null;
            final DataHolder dataHolder33 = null;
            final DataHolder dataHolder34 = null;
            final DataHolder dataHolder35 = null;
            final Bundle bundle2 = null;
            final DataHolder dataHolder36 = null;
            final Bundle bundle3 = null;
            DataHolder fromParcel2 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.games.internal.IGamesCallbacks");
                    return true;
                }
                case 5001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.d(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (parcel.readInt() != 0) {
                        fromParcel2 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.b(fromParcel2);
                    parcel2.writeNoException();
                    return true;
                }
                case 5003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.e(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel3 = dataHolder;
                    if (parcel.readInt() != 0) {
                        fromParcel3 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.c(fromParcel3);
                    parcel2.writeNoException();
                    return true;
                }
                case 5005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel4;
                    if (parcel.readInt() != 0) {
                        fromParcel4 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel4 = null;
                    }
                    if (parcel.readInt() != 0) {
                        fromParcel = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel4, fromParcel);
                    parcel2.writeNoException();
                    return true;
                }
                case 5006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel5 = dataHolder2;
                    if (parcel.readInt() != 0) {
                        fromParcel5 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.d(fromParcel5);
                    parcel2.writeNoException();
                    return true;
                }
                case 5007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel6 = dataHolder3;
                    if (parcel.readInt() != 0) {
                        fromParcel6 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.e(fromParcel6);
                    parcel2.writeNoException();
                    return true;
                }
                case 5008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel7 = dataHolder4;
                    if (parcel.readInt() != 0) {
                        fromParcel7 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.f(fromParcel7);
                    parcel2.writeNoException();
                    return true;
                }
                case 5009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel8 = dataHolder5;
                    if (parcel.readInt() != 0) {
                        fromParcel8 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.g(fromParcel8);
                    parcel2.writeNoException();
                    return true;
                }
                case 5010: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel9 = dataHolder6;
                    if (parcel.readInt() != 0) {
                        fromParcel9 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.h(fromParcel9);
                    parcel2.writeNoException();
                    return true;
                }
                case 5011: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel10 = dataHolder7;
                    if (parcel.readInt() != 0) {
                        fromParcel10 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.i(fromParcel10);
                    parcel2.writeNoException();
                    return true;
                }
                case 5016: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.du();
                    parcel2.writeNoException();
                    return true;
                }
                case 5017: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel11 = dataHolder8;
                    if (parcel.readInt() != 0) {
                        fromParcel11 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.k(fromParcel11);
                    parcel2.writeNoException();
                    return true;
                }
                case 5037: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel12 = dataHolder9;
                    if (parcel.readInt() != 0) {
                        fromParcel12 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.l(fromParcel12);
                    parcel2.writeNoException();
                    return true;
                }
                case 5018: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel13 = dataHolder10;
                    if (parcel.readInt() != 0) {
                        fromParcel13 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.s(fromParcel13);
                    parcel2.writeNoException();
                    return true;
                }
                case 5019: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel14 = dataHolder11;
                    if (parcel.readInt() != 0) {
                        fromParcel14 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.t(fromParcel14);
                    parcel2.writeNoException();
                    return true;
                }
                case 5020: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.onLeftRoom(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5021: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel15 = dataHolder12;
                    if (parcel.readInt() != 0) {
                        fromParcel15 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.u(fromParcel15);
                    parcel2.writeNoException();
                    return true;
                }
                case 5022: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel16 = dataHolder13;
                    if (parcel.readInt() != 0) {
                        fromParcel16 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.v(fromParcel16);
                    parcel2.writeNoException();
                    return true;
                }
                case 5023: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel17 = dataHolder14;
                    if (parcel.readInt() != 0) {
                        fromParcel17 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.w(fromParcel17);
                    parcel2.writeNoException();
                    return true;
                }
                case 5024: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel18 = dataHolder15;
                    if (parcel.readInt() != 0) {
                        fromParcel18 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.x(fromParcel18);
                    parcel2.writeNoException();
                    return true;
                }
                case 5025: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel19 = dataHolder16;
                    if (parcel.readInt() != 0) {
                        fromParcel19 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.y(fromParcel19);
                    parcel2.writeNoException();
                    return true;
                }
                case 5026: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel20 = dataHolder17;
                    if (parcel.readInt() != 0) {
                        fromParcel20 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.a(fromParcel20, parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 5027: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel21 = dataHolder18;
                    if (parcel.readInt() != 0) {
                        fromParcel21 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.b(fromParcel21, parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 5028: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel22 = dataHolder19;
                    if (parcel.readInt() != 0) {
                        fromParcel22 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.c(fromParcel22, parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 5029: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel23 = dataHolder20;
                    if (parcel.readInt() != 0) {
                        fromParcel23 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.d(fromParcel23, parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 5030: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel24 = dataHolder21;
                    if (parcel.readInt() != 0) {
                        fromParcel24 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.e(fromParcel24, parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 5031: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel25 = dataHolder22;
                    if (parcel.readInt() != 0) {
                        fromParcel25 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.f(fromParcel25, parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 5032: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    RealTimeMessage realTimeMessage2 = realTimeMessage;
                    if (parcel.readInt() != 0) {
                        realTimeMessage2 = (RealTimeMessage)RealTimeMessage.CREATOR.createFromParcel(parcel);
                    }
                    this.onRealTimeMessageReceived(realTimeMessage2);
                    parcel2.writeNoException();
                    return true;
                }
                case 5033: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.b(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 5034: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    n = parcel.readInt();
                    this.a(n, parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                }
                case 5038: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel26 = dataHolder23;
                    if (parcel.readInt() != 0) {
                        fromParcel26 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.z(fromParcel26);
                    parcel2.writeNoException();
                    return true;
                }
                case 5035: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel27 = dataHolder24;
                    if (parcel.readInt() != 0) {
                        fromParcel27 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.A(fromParcel27);
                    parcel2.writeNoException();
                    return true;
                }
                case 5036: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.aU(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5039: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel28 = dataHolder25;
                    if (parcel.readInt() != 0) {
                        fromParcel28 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.B(fromParcel28);
                    parcel2.writeNoException();
                    return true;
                }
                case 5040: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.aV(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 6001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.onP2PConnected(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 6002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.onP2PDisconnected(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel29 = dataHolder26;
                    if (parcel.readInt() != 0) {
                        fromParcel29 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.C(fromParcel29);
                    parcel2.writeNoException();
                    return true;
                }
                case 8002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    n = parcel.readInt();
                    Bundle bundle4 = bundle;
                    if (parcel.readInt() != 0) {
                        bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.a(n, bundle4);
                    parcel2.writeNoException();
                    return true;
                }
                case 8003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel30 = dataHolder27;
                    if (parcel.readInt() != 0) {
                        fromParcel30 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.n(fromParcel30);
                    parcel2.writeNoException();
                    return true;
                }
                case 8004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel31 = dataHolder28;
                    if (parcel.readInt() != 0) {
                        fromParcel31 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.o(fromParcel31);
                    parcel2.writeNoException();
                    return true;
                }
                case 8005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel32 = dataHolder29;
                    if (parcel.readInt() != 0) {
                        fromParcel32 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.p(fromParcel32);
                    parcel2.writeNoException();
                    return true;
                }
                case 8006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel33 = dataHolder30;
                    if (parcel.readInt() != 0) {
                        fromParcel33 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.q(fromParcel33);
                    parcel2.writeNoException();
                    return true;
                }
                case 8007: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.f(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8008: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel34 = dataHolder31;
                    if (parcel.readInt() != 0) {
                        fromParcel34 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.r(fromParcel34);
                    parcel2.writeNoException();
                    return true;
                }
                case 8009: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.onTurnBasedMatchRemoved(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 8010: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.onInvitationRemoved(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 9001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel35 = dataHolder32;
                    if (parcel.readInt() != 0) {
                        fromParcel35 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.j(fromParcel35);
                    parcel2.writeNoException();
                    return true;
                }
                case 10001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel36 = dataHolder33;
                    if (parcel.readInt() != 0) {
                        fromParcel36 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.m(fromParcel36);
                    parcel2.writeNoException();
                    return true;
                }
                case 10002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.onRequestRemoved(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 10003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel37 = dataHolder34;
                    if (parcel.readInt() != 0) {
                        fromParcel37 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.D(fromParcel37);
                    parcel2.writeNoException();
                    return true;
                }
                case 10004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel38 = dataHolder35;
                    if (parcel.readInt() != 0) {
                        fromParcel38 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.E(fromParcel38);
                    parcel2.writeNoException();
                    return true;
                }
                case 10005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    n = parcel.readInt();
                    Bundle bundle5 = bundle2;
                    if (parcel.readInt() != 0) {
                        bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.b(n, bundle5);
                    parcel2.writeNoException();
                    return true;
                }
                case 10006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    DataHolder fromParcel39 = dataHolder36;
                    if (parcel.readInt() != 0) {
                        fromParcel39 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.F(fromParcel39);
                    parcel2.writeNoException();
                    return true;
                }
                case 11001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                    n = parcel.readInt();
                    Bundle bundle6 = bundle3;
                    if (parcel.readInt() != 0) {
                        bundle6 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.c(n, bundle6);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class Proxy implements IGamesCallbacks
        {
            private IBinder kn;
            
            Proxy(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void A(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5035, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void B(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5039, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void C(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(8001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void D(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(10003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void E(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(10004, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void F(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(10006, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final int n, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(8002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(int n, final String s, final boolean b) throws RemoteException {
                final int n2 = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    n = n2;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.kn.transact(5034, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final DataHolder dataHolder, final DataHolder dataHolder2) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                            if (dataHolder != null) {
                                obtain.writeInt(1);
                                dataHolder.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (dataHolder2 != null) {
                                obtain.writeInt(1);
                                dataHolder2.writeToParcel(obtain, 0);
                                this.kn.transact(5005, obtain, obtain2, 0);
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
            public void a(final DataHolder dataHolder, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringArray(array);
                    this.kn.transact(5026, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void aU(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    this.kn.transact(5036, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void aV(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    this.kn.transact(5040, obtain, obtain2, 0);
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
            public void b(final int n, final int n2, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeString(s);
                    this.kn.transact(5033, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final int n, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(10005, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void b(final DataHolder dataHolder, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringArray(array);
                    this.kn.transact(5027, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final int n, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(11001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5004, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void c(final DataHolder dataHolder, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringArray(array);
                    this.kn.transact(5028, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    this.kn.transact(5001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5006, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void d(final DataHolder dataHolder, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringArray(array);
                    this.kn.transact(5029, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void du() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    this.kn.transact(5016, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    this.kn.transact(5003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5007, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void e(final DataHolder dataHolder, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringArray(array);
                    this.kn.transact(5030, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    this.kn.transact(8007, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5008, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void f(final DataHolder dataHolder, final String[] array) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringArray(array);
                    this.kn.transact(5031, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void g(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5009, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void h(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5010, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void i(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5011, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void j(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(9001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void k(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5017, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void l(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5037, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void m(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(10001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void n(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(8003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void o(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
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
            public void onInvitationRemoved(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
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
            public void onLeftRoom(final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    this.kn.transact(5020, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onP2PConnected(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(6001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onP2PDisconnected(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(6002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onRealTimeMessageReceived(final RealTimeMessage realTimeMessage) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (realTimeMessage != null) {
                        obtain.writeInt(1);
                        realTimeMessage.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5032, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onRequestRemoved(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    obtain.writeString(s);
                    this.kn.transact(10002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onTurnBasedMatchRemoved(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
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
            public void p(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(8005, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void q(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(8006, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void r(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(8008, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void s(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5018, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void t(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5019, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void u(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5021, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void v(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5022, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void w(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
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
            public void x(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
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
            public void y(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
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
            public void z(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(5038, obtain, obtain2, 0);
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
