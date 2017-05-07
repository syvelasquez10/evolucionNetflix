// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.CredentialRequest;
import android.os.Parcel;
import android.os.IBinder;

class zzh$zza$zza implements zzh
{
    private IBinder zznI;
    
    zzh$zza$zza(final IBinder zznI) {
        this.zznI = zznI;
    }
    
    public IBinder asBinder() {
        return this.zznI;
    }
    
    @Override
    public void zza(final zzg zzg) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
            IBinder binder;
            if (zzg != null) {
                binder = zzg.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.zznI.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void zza(final zzg zzg, final CredentialRequest credentialRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
            IBinder binder;
            if (zzg != null) {
                binder = zzg.asBinder();
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
            this.zznI.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void zza(final zzg zzg, final DeleteRequest deleteRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
            IBinder binder;
            if (zzg != null) {
                binder = zzg.asBinder();
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
            this.zznI.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void zza(final zzg zzg, final SaveRequest saveRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
            IBinder binder;
            if (zzg != null) {
                binder = zzg.asBinder();
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
            this.zznI.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
