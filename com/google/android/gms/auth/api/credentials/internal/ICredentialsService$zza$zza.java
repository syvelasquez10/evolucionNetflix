// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.CredentialRequest;
import android.os.Parcel;
import android.os.IBinder;

class ICredentialsService$zza$zza implements ICredentialsService
{
    private IBinder zznF;
    
    ICredentialsService$zza$zza(final IBinder zznF) {
        this.zznF = zznF;
    }
    
    public IBinder asBinder() {
        return this.zznF;
    }
    
    @Override
    public void performCredentialsDeleteOperation(final ICredentialsCallbacks credentialsCallbacks, final DeleteRequest deleteRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
            IBinder binder;
            if (credentialsCallbacks != null) {
                binder = credentialsCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (deleteRequest != null) {
                obtain.writeInt(1);
                deleteRequest.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.zznF.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void performCredentialsRequestOperation(final ICredentialsCallbacks credentialsCallbacks, final CredentialRequest credentialRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
            IBinder binder;
            if (credentialsCallbacks != null) {
                binder = credentialsCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (credentialRequest != null) {
                obtain.writeInt(1);
                credentialRequest.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.zznF.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void performCredentialsSaveOperation(final ICredentialsCallbacks credentialsCallbacks, final SaveRequest saveRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
            IBinder binder;
            if (credentialsCallbacks != null) {
                binder = credentialsCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (saveRequest != null) {
                obtain.writeInt(1);
                saveRequest.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.zznF.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void performDisableAutoSignInOperation(final ICredentialsCallbacks credentialsCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
            IBinder binder;
            if (credentialsCallbacks != null) {
                binder = credentialsCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.zznF.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
