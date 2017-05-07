// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.CredentialRequest;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class ICredentialsService$zza extends Binder implements ICredentialsService
{
    public static ICredentialsService zzaq(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        if (queryLocalInterface != null && queryLocalInterface instanceof ICredentialsService) {
            return (ICredentialsService)queryLocalInterface;
        }
        return new ICredentialsService$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final SaveRequest saveRequest = null;
        final DeleteRequest deleteRequest = null;
        CredentialRequest credentialRequest = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
                final ICredentialsCallbacks zzap = ICredentialsCallbacks$zza.zzap(parcel.readStrongBinder());
                if (parcel.readInt() != 0) {
                    credentialRequest = (CredentialRequest)CredentialRequest.CREATOR.createFromParcel(parcel);
                }
                this.performCredentialsRequestOperation(zzap, credentialRequest);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
                final ICredentialsCallbacks zzap2 = ICredentialsCallbacks$zza.zzap(parcel.readStrongBinder());
                SaveRequest saveRequest2 = saveRequest;
                if (parcel.readInt() != 0) {
                    saveRequest2 = (SaveRequest)SaveRequest.CREATOR.createFromParcel(parcel);
                }
                this.performCredentialsSaveOperation(zzap2, saveRequest2);
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
                final ICredentialsCallbacks zzap3 = ICredentialsCallbacks$zza.zzap(parcel.readStrongBinder());
                DeleteRequest deleteRequest2 = deleteRequest;
                if (parcel.readInt() != 0) {
                    deleteRequest2 = (DeleteRequest)DeleteRequest.CREATOR.createFromParcel(parcel);
                }
                this.performCredentialsDeleteOperation(zzap3, deleteRequest2);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
                this.performDisableAutoSignInOperation(ICredentialsCallbacks$zza.zzap(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
