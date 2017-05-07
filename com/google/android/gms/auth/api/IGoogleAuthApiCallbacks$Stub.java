// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class IGoogleAuthApiCallbacks$Stub extends Binder implements IGoogleAuthApiCallbacks
{
    public IGoogleAuthApiCallbacks$Stub() {
        this.attachInterface((IInterface)this, "com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
    }
    
    public static IGoogleAuthApiCallbacks asInterface(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof IGoogleAuthApiCallbacks) {
            return (IGoogleAuthApiCallbacks)queryLocalInterface;
        }
        return new IGoogleAuthApiCallbacks$Stub$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) {
        PendingIntent pendingIntent = null;
        final GoogleAuthApiResponse googleAuthApiResponse = null;
        switch (int1) {
            default: {
                return super.onTransact(int1, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
                GoogleAuthApiResponse fromParcel = googleAuthApiResponse;
                if (parcel.readInt() != 0) {
                    fromParcel = GoogleAuthApiResponse.CREATOR.createFromParcel(parcel);
                }
                this.onConnectionSuccess(fromParcel);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.auth.api.IGoogleAuthApiCallbacks");
                int1 = parcel.readInt();
                final String string = parcel.readString();
                if (parcel.readInt() != 0) {
                    pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                this.onError(int1, string, pendingIntent);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
