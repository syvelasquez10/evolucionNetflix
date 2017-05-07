// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.drive.Contents;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class IGamesCallbacks$Stub extends Binder implements IGamesCallbacks
{
    public IGamesCallbacks$Stub() {
        this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IGamesCallbacks");
    }
    
    public static IGamesCallbacks aA(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.games.internal.IGamesCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof IGamesCallbacks) {
            return (IGamesCallbacks)queryLocalInterface;
        }
        return new IGamesCallbacks$Stub$Proxy(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        DataHolder z = null;
        final DataHolder dataHolder = null;
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
        final DataHolder dataHolder23 = null;
        final DataHolder dataHolder24 = null;
        final DataHolder dataHolder25 = null;
        final DataHolder dataHolder26 = null;
        final DataHolder dataHolder27 = null;
        final DataHolder dataHolder28 = null;
        final DataHolder dataHolder29 = null;
        final DataHolder dataHolder30 = null;
        final DataHolder dataHolder31 = null;
        final DataHolder dataHolder32 = null;
        final DataHolder dataHolder33 = null;
        final DataHolder dataHolder34 = null;
        final DataHolder dataHolder35 = null;
        final DataHolder dataHolder36 = null;
        Contents contents = null;
        final DataHolder dataHolder37 = null;
        final DataHolder dataHolder38 = null;
        final DataHolder dataHolder39 = null;
        final DataHolder dataHolder40 = null;
        final DataHolder dataHolder41 = null;
        final DataHolder dataHolder42 = null;
        final DataHolder dataHolder43 = null;
        final DataHolder dataHolder44 = null;
        final DataHolder dataHolder45 = null;
        DataHolder z2 = null;
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
                this.f(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z3;
                if (parcel.readInt() != 0) {
                    z3 = DataHolder.CREATOR.z(parcel);
                }
                else {
                    z3 = null;
                }
                this.c(z3);
                parcel2.writeNoException();
                return true;
            }
            case 5003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                this.g(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                if (parcel.readInt() != 0) {
                    z2 = DataHolder.CREATOR.z(parcel);
                }
                this.e(z2);
                parcel2.writeNoException();
                return true;
            }
            case 5005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z4;
                if (parcel.readInt() != 0) {
                    z4 = DataHolder.CREATOR.z(parcel);
                }
                else {
                    z4 = null;
                }
                if (parcel.readInt() != 0) {
                    z = DataHolder.CREATOR.z(parcel);
                }
                this.a(z4, z);
                parcel2.writeNoException();
                return true;
            }
            case 5006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z5 = dataHolder;
                if (parcel.readInt() != 0) {
                    z5 = DataHolder.CREATOR.z(parcel);
                }
                this.f(z5);
                parcel2.writeNoException();
                return true;
            }
            case 5007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z6 = dataHolder2;
                if (parcel.readInt() != 0) {
                    z6 = DataHolder.CREATOR.z(parcel);
                }
                this.g(z6);
                parcel2.writeNoException();
                return true;
            }
            case 5008: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z7 = dataHolder3;
                if (parcel.readInt() != 0) {
                    z7 = DataHolder.CREATOR.z(parcel);
                }
                this.h(z7);
                parcel2.writeNoException();
                return true;
            }
            case 5009: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z8 = dataHolder4;
                if (parcel.readInt() != 0) {
                    z8 = DataHolder.CREATOR.z(parcel);
                }
                this.i(z8);
                parcel2.writeNoException();
                return true;
            }
            case 5010: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z9 = dataHolder5;
                if (parcel.readInt() != 0) {
                    z9 = DataHolder.CREATOR.z(parcel);
                }
                this.j(z9);
                parcel2.writeNoException();
                return true;
            }
            case 5011: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z10 = dataHolder6;
                if (parcel.readInt() != 0) {
                    z10 = DataHolder.CREATOR.z(parcel);
                }
                this.k(z10);
                parcel2.writeNoException();
                return true;
            }
            case 5016: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                this.fq();
                parcel2.writeNoException();
                return true;
            }
            case 5017: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z11 = dataHolder7;
                if (parcel.readInt() != 0) {
                    z11 = DataHolder.CREATOR.z(parcel);
                }
                this.m(z11);
                parcel2.writeNoException();
                return true;
            }
            case 5037: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z12 = dataHolder8;
                if (parcel.readInt() != 0) {
                    z12 = DataHolder.CREATOR.z(parcel);
                }
                this.n(z12);
                parcel2.writeNoException();
                return true;
            }
            case 5018: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z13 = dataHolder9;
                if (parcel.readInt() != 0) {
                    z13 = DataHolder.CREATOR.z(parcel);
                }
                this.u(z13);
                parcel2.writeNoException();
                return true;
            }
            case 5019: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z14 = dataHolder10;
                if (parcel.readInt() != 0) {
                    z14 = DataHolder.CREATOR.z(parcel);
                }
                this.v(z14);
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
                DataHolder z15 = dataHolder11;
                if (parcel.readInt() != 0) {
                    z15 = DataHolder.CREATOR.z(parcel);
                }
                this.w(z15);
                parcel2.writeNoException();
                return true;
            }
            case 5022: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z16 = dataHolder12;
                if (parcel.readInt() != 0) {
                    z16 = DataHolder.CREATOR.z(parcel);
                }
                this.x(z16);
                parcel2.writeNoException();
                return true;
            }
            case 5023: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z17 = dataHolder13;
                if (parcel.readInt() != 0) {
                    z17 = DataHolder.CREATOR.z(parcel);
                }
                this.y(z17);
                parcel2.writeNoException();
                return true;
            }
            case 5024: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z18 = dataHolder14;
                if (parcel.readInt() != 0) {
                    z18 = DataHolder.CREATOR.z(parcel);
                }
                this.z(z18);
                parcel2.writeNoException();
                return true;
            }
            case 5025: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z19 = dataHolder15;
                if (parcel.readInt() != 0) {
                    z19 = DataHolder.CREATOR.z(parcel);
                }
                this.A(z19);
                parcel2.writeNoException();
                return true;
            }
            case 5026: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z20 = dataHolder16;
                if (parcel.readInt() != 0) {
                    z20 = DataHolder.CREATOR.z(parcel);
                }
                this.a(z20, parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 5027: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z21 = dataHolder17;
                if (parcel.readInt() != 0) {
                    z21 = DataHolder.CREATOR.z(parcel);
                }
                this.b(z21, parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 5028: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z22 = dataHolder18;
                if (parcel.readInt() != 0) {
                    z22 = DataHolder.CREATOR.z(parcel);
                }
                this.c(z22, parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 5029: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z23 = dataHolder19;
                if (parcel.readInt() != 0) {
                    z23 = DataHolder.CREATOR.z(parcel);
                }
                this.d(z23, parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 5030: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z24 = dataHolder20;
                if (parcel.readInt() != 0) {
                    z24 = DataHolder.CREATOR.z(parcel);
                }
                this.e(z24, parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 5031: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z25 = dataHolder21;
                if (parcel.readInt() != 0) {
                    z25 = DataHolder.CREATOR.z(parcel);
                }
                this.f(z25, parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 5032: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                RealTimeMessage realTimeMessage;
                if (parcel.readInt() != 0) {
                    realTimeMessage = (RealTimeMessage)RealTimeMessage.CREATOR.createFromParcel(parcel);
                }
                else {
                    realTimeMessage = null;
                }
                this.onRealTimeMessageReceived(realTimeMessage);
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
                DataHolder z26 = dataHolder22;
                if (parcel.readInt() != 0) {
                    z26 = DataHolder.CREATOR.z(parcel);
                }
                this.B(z26);
                parcel2.writeNoException();
                return true;
            }
            case 5035: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z27 = dataHolder23;
                if (parcel.readInt() != 0) {
                    z27 = DataHolder.CREATOR.z(parcel);
                }
                this.C(z27);
                parcel2.writeNoException();
                return true;
            }
            case 5036: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                this.dx(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 5039: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z28 = dataHolder24;
                if (parcel.readInt() != 0) {
                    z28 = DataHolder.CREATOR.z(parcel);
                }
                this.D(z28);
                parcel2.writeNoException();
                return true;
            }
            case 5040: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                this.dy(parcel.readInt());
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
                DataHolder z29 = dataHolder25;
                if (parcel.readInt() != 0) {
                    z29 = DataHolder.CREATOR.z(parcel);
                }
                this.E(z29);
                parcel2.writeNoException();
                return true;
            }
            case 8002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                n = parcel.readInt();
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.b(n, bundle);
                parcel2.writeNoException();
                return true;
            }
            case 8003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z30 = dataHolder26;
                if (parcel.readInt() != 0) {
                    z30 = DataHolder.CREATOR.z(parcel);
                }
                this.p(z30);
                parcel2.writeNoException();
                return true;
            }
            case 8004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z31 = dataHolder27;
                if (parcel.readInt() != 0) {
                    z31 = DataHolder.CREATOR.z(parcel);
                }
                this.q(z31);
                parcel2.writeNoException();
                return true;
            }
            case 8005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z32 = dataHolder28;
                if (parcel.readInt() != 0) {
                    z32 = DataHolder.CREATOR.z(parcel);
                }
                this.r(z32);
                parcel2.writeNoException();
                return true;
            }
            case 8006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z33 = dataHolder29;
                if (parcel.readInt() != 0) {
                    z33 = DataHolder.CREATOR.z(parcel);
                }
                this.s(z33);
                parcel2.writeNoException();
                return true;
            }
            case 8007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                this.h(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 8008: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z34 = dataHolder30;
                if (parcel.readInt() != 0) {
                    z34 = DataHolder.CREATOR.z(parcel);
                }
                this.t(z34);
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
                DataHolder z35 = dataHolder31;
                if (parcel.readInt() != 0) {
                    z35 = DataHolder.CREATOR.z(parcel);
                }
                this.l(z35);
                parcel2.writeNoException();
                return true;
            }
            case 10001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z36 = dataHolder32;
                if (parcel.readInt() != 0) {
                    z36 = DataHolder.CREATOR.z(parcel);
                }
                this.o(z36);
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
                DataHolder z37 = dataHolder33;
                if (parcel.readInt() != 0) {
                    z37 = DataHolder.CREATOR.z(parcel);
                }
                this.F(z37);
                parcel2.writeNoException();
                return true;
            }
            case 10004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z38 = dataHolder34;
                if (parcel.readInt() != 0) {
                    z38 = DataHolder.CREATOR.z(parcel);
                }
                this.G(z38);
                parcel2.writeNoException();
                return true;
            }
            case 10005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                n = parcel.readInt();
                Bundle bundle2;
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle2 = null;
                }
                this.c(n, bundle2);
                parcel2.writeNoException();
                return true;
            }
            case 10006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z39 = dataHolder35;
                if (parcel.readInt() != 0) {
                    z39 = DataHolder.CREATOR.z(parcel);
                }
                this.H(z39);
                parcel2.writeNoException();
                return true;
            }
            case 11001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                n = parcel.readInt();
                Bundle bundle3;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle3 = null;
                }
                this.d(n, bundle3);
                parcel2.writeNoException();
                return true;
            }
            case 12001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z40 = dataHolder36;
                if (parcel.readInt() != 0) {
                    z40 = DataHolder.CREATOR.z(parcel);
                }
                this.I(z40);
                parcel2.writeNoException();
                return true;
            }
            case 12004: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z41;
                if (parcel.readInt() != 0) {
                    z41 = DataHolder.CREATOR.z(parcel);
                }
                else {
                    z41 = null;
                }
                Contents contents2;
                if (parcel.readInt() != 0) {
                    contents2 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                }
                else {
                    contents2 = null;
                }
                this.a(z41, contents2);
                parcel2.writeNoException();
                return true;
            }
            case 12017: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z42;
                if (parcel.readInt() != 0) {
                    z42 = DataHolder.CREATOR.z(parcel);
                }
                else {
                    z42 = null;
                }
                final String string = parcel.readString();
                Contents contents3;
                if (parcel.readInt() != 0) {
                    contents3 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                }
                else {
                    contents3 = null;
                }
                Contents contents4;
                if (parcel.readInt() != 0) {
                    contents4 = (Contents)Contents.CREATOR.createFromParcel(parcel);
                }
                else {
                    contents4 = null;
                }
                if (parcel.readInt() != 0) {
                    contents = (Contents)Contents.CREATOR.createFromParcel(parcel);
                }
                this.a(z42, string, contents3, contents4, contents);
                parcel2.writeNoException();
                return true;
            }
            case 12005: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z43 = dataHolder37;
                if (parcel.readInt() != 0) {
                    z43 = DataHolder.CREATOR.z(parcel);
                }
                this.J(z43);
                parcel2.writeNoException();
                return true;
            }
            case 12012: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                this.i(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 12003: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                n = parcel.readInt();
                Bundle bundle4;
                if (parcel.readInt() != 0) {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle4 = null;
                }
                this.e(n, bundle4);
                parcel2.writeNoException();
                return true;
            }
            case 12013: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z44 = dataHolder38;
                if (parcel.readInt() != 0) {
                    z44 = DataHolder.CREATOR.z(parcel);
                }
                this.P(z44);
                parcel2.writeNoException();
                return true;
            }
            case 12011: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z45 = dataHolder39;
                if (parcel.readInt() != 0) {
                    z45 = DataHolder.CREATOR.z(parcel);
                }
                this.d(z45);
                parcel2.writeNoException();
                return true;
            }
            case 12006: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z46 = dataHolder40;
                if (parcel.readInt() != 0) {
                    z46 = DataHolder.CREATOR.z(parcel);
                }
                this.K(z46);
                parcel2.writeNoException();
                return true;
            }
            case 12007: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z47 = dataHolder41;
                if (parcel.readInt() != 0) {
                    z47 = DataHolder.CREATOR.z(parcel);
                }
                this.L(z47);
                parcel2.writeNoException();
                return true;
            }
            case 12014: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z48 = dataHolder42;
                if (parcel.readInt() != 0) {
                    z48 = DataHolder.CREATOR.z(parcel);
                }
                this.M(z48);
                parcel2.writeNoException();
                return true;
            }
            case 12016: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z49 = dataHolder43;
                if (parcel.readInt() != 0) {
                    z49 = DataHolder.CREATOR.z(parcel);
                }
                this.N(z49);
                parcel2.writeNoException();
                return true;
            }
            case 12008: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z50 = dataHolder44;
                if (parcel.readInt() != 0) {
                    z50 = DataHolder.CREATOR.z(parcel);
                }
                this.O(z50);
                parcel2.writeNoException();
                return true;
            }
            case 12015: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                n = parcel.readInt();
                Bundle bundle5;
                if (parcel.readInt() != 0) {
                    bundle5 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle5 = null;
                }
                this.f(n, bundle5);
                parcel2.writeNoException();
                return true;
            }
            case 13001: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                DataHolder z51 = dataHolder45;
                if (parcel.readInt() != 0) {
                    z51 = DataHolder.CREATOR.z(parcel);
                }
                this.Q(z51);
                parcel2.writeNoException();
                return true;
            }
            case 13002: {
                parcel.enforceInterface("com.google.android.gms.games.internal.IGamesCallbacks");
                this.dz(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
