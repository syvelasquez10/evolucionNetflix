// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class ICredentialsCallbacks$zza extends Binder implements ICredentialsCallbacks
{
    public ICredentialsCallbacks$zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
    }
    
    public static ICredentialsCallbacks zzap(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof ICredentialsCallbacks) {
            return (ICredentialsCallbacks)queryLocalInterface;
        }
        return new ICredentialsCallbacks$zza$zza(binder);
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
                parcel2.writeString("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
                Status status;
                if (parcel.readInt() != 0) {
                    status = (Status)Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    status = null;
                }
                Credential credential;
                if (parcel.readInt() != 0) {
                    credential = (Credential)Credential.CREATOR.createFromParcel(parcel);
                }
                else {
                    credential = null;
                }
                this.onCredentialResult(status, credential);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
                Status status2;
                if (parcel.readInt() != 0) {
                    status2 = (Status)Status.CREATOR.createFromParcel(parcel);
                }
                else {
                    status2 = null;
                }
                this.onStatusResult(status2);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
