// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class IGoogleAuthService$Stub extends Binder implements IGoogleAuthService
{
    public IGoogleAuthService$Stub() {
        this.attachInterface((IInterface)this, "com.google.android.gms.auth.api.IGoogleAuthService");
    }
    
    public static IGoogleAuthService asInterface(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.IGoogleAuthService");
        if (queryLocalInterface != null && queryLocalInterface instanceof IGoogleAuthService) {
            return (IGoogleAuthService)queryLocalInterface;
        }
        return new IGoogleAuthService$Stub$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.auth.api.IGoogleAuthService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.auth.api.IGoogleAuthService");
                final IGoogleAuthApiCallbacks interface1 = IGoogleAuthApiCallbacks$Stub.asInterface(parcel.readStrongBinder());
                GoogleAuthApiRequest fromParcel;
                if (parcel.readInt() != 0) {
                    fromParcel = GoogleAuthApiRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    fromParcel = null;
                }
                this.sendConnection(interface1, fromParcel);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
