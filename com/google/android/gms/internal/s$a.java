// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class s$a extends Binder implements s
{
    public static s b(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        if (queryLocalInterface != null && queryLocalInterface instanceof s) {
            return (s)queryLocalInterface;
        }
        return new s$a$a(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        boolean b = false;
        final int n3 = 0;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                final String id = this.getId();
                parcel2.writeNoException();
                parcel2.writeString(id);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                final boolean a = this.a(parcel.readInt() != 0);
                parcel2.writeNoException();
                n = n3;
                if (a) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                final String c = this.c(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(c);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                final String string = parcel.readString();
                if (parcel.readInt() != 0) {
                    b = true;
                }
                this.b(string, b);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
