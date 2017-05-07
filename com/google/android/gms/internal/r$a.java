// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import android.os.Bundle;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class r$a extends Binder implements r
{
    public static r a(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
        if (queryLocalInterface != null && queryLocalInterface instanceof r) {
            return (r)queryLocalInterface;
        }
        return new r$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final Bundle bundle = null;
        final AccountChangeEventsRequest accountChangeEventsRequest = null;
        Bundle bundle2 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.auth.IAuthManagerService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                final String string = parcel.readString();
                final String string2 = parcel.readString();
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                final Bundle a = this.a(string, string2, bundle2);
                parcel2.writeNoException();
                if (a != null) {
                    parcel2.writeInt(1);
                    a.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                final String string3 = parcel.readString();
                Bundle bundle3 = bundle;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                final Bundle a2 = this.a(string3, bundle3);
                parcel2.writeNoException();
                if (a2 != null) {
                    parcel2.writeInt(1);
                    a2.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.auth.IAuthManagerService");
                AccountChangeEventsRequest fromParcel = accountChangeEventsRequest;
                if (parcel.readInt() != 0) {
                    fromParcel = AccountChangeEventsRequest.CREATOR.createFromParcel(parcel);
                }
                final AccountChangeEventsResponse a3 = this.a(fromParcel);
                parcel2.writeNoException();
                if (a3 != null) {
                    parcel2.writeInt(1);
                    a3.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }
}
