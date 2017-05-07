// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.ConnectionResult;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class zze$zza extends Binder implements zze
{
    public zze$zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.signin.internal.ISignInCallbacks");
    }
    
    public static zze zzdC(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof zze) {
            return (zze)queryLocalInterface;
        }
        return new zze$zza$zza(binder);
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
                parcel2.writeString("com.google.android.gms.signin.internal.ISignInCallbacks");
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
                ConnectionResult connectionResult;
                if (parcel.readInt() != 0) {
                    connectionResult = (ConnectionResult)ConnectionResult.CREATOR.createFromParcel(parcel);
                }
                else {
                    connectionResult = null;
                }
                AuthAccountResult authAccountResult;
                if (parcel.readInt() != 0) {
                    authAccountResult = (AuthAccountResult)AuthAccountResult.CREATOR.createFromParcel(parcel);
                }
                else {
                    authAccountResult = null;
                }
                this.zza(connectionResult, authAccountResult);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
                Status status;
                if (parcel.readInt() != 0) {
                    status = (Status)Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    status = null;
                }
                this.zzaT(status);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
