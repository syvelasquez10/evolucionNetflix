// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.internal.i;
import com.google.android.gms.internal.jb;
import java.util.List;
import android.os.Bundle;
import android.net.Uri;
import com.google.android.gms.internal.jp;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class d$a extends Binder implements d
{
    public static d bG(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusService");
        if (queryLocalInterface != null && queryLocalInterface instanceof d) {
            return (d)queryLocalInterface;
        }
        return new d$a$a(binder);
    }
    
    public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) {
        final jp jp = null;
        jb e = null;
        final IBinder binder = null;
        switch (int1) {
            default: {
                return super.onTransact(int1, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.plus.internal.IPlusService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.a(b$a.bE(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.a(b$a.bE(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.b(b$a.bE(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                jp m;
                if (parcel.readInt() != 0) {
                    m = com.google.android.gms.internal.jp.CREATOR.M(parcel);
                }
                else {
                    m = null;
                }
                this.a(m);
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                final String accountName = this.getAccountName();
                parcel2.writeNoException();
                parcel2.writeString(accountName);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.clearDefaultAccount();
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.a(b$a.bE(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                final b be = b$a.bE(parcel.readStrongBinder());
                Uri uri;
                if (parcel.readInt() != 0) {
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                }
                else {
                    uri = null;
                }
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.a(be, uri, bundle);
                parcel2.writeNoException();
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                final b be2 = b$a.bE(parcel.readStrongBinder());
                int1 = parcel.readInt();
                final String string = parcel.readString();
                Uri uri2;
                if (parcel.readInt() != 0) {
                    uri2 = (Uri)Uri.CREATOR.createFromParcel(parcel);
                }
                else {
                    uri2 = null;
                }
                this.a(be2, int1, string, uri2, parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                final i a = this.a(b$a.bE(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                IBinder binder2 = binder;
                if (a != null) {
                    binder2 = a.asBinder();
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.removeMoment(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.c(b$a.bE(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 19: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.b(b$a.bE(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 34: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.a(b$a.bE(parcel.readStrongBinder()), parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            }
            case 40: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.d(b$a.bE(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 41: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                final String mz = this.mZ();
                parcel2.writeNoException();
                parcel2.writeString(mz);
                return true;
            }
            case 42: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                final boolean na = this.na();
                parcel2.writeNoException();
                if (na) {
                    int1 = 1;
                }
                else {
                    int1 = 0;
                }
                parcel2.writeInt(int1);
                return true;
            }
            case 43: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                final String nb = this.nb();
                parcel2.writeNoException();
                parcel2.writeString(nb);
                return true;
            }
            case 44: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                this.e(b$a.bE(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 45: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                final b be3 = b$a.bE(parcel.readStrongBinder());
                jp i = jp;
                if (parcel.readInt() != 0) {
                    i = com.google.android.gms.internal.jp.CREATOR.M(parcel);
                }
                this.a(be3, i);
                parcel2.writeNoException();
                return true;
            }
            case 46: {
                parcel.enforceInterface("com.google.android.gms.plus.internal.IPlusService");
                final String string2 = parcel.readString();
                jb e2;
                if (parcel.readInt() != 0) {
                    e2 = jb.CREATOR.E(parcel);
                }
                else {
                    e2 = null;
                }
                if (parcel.readInt() != 0) {
                    e = jb.CREATOR.E(parcel);
                }
                this.a(string2, e2, e);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
