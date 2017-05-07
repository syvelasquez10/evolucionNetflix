// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.auth.api.proxy.ProxyRequest;
import android.os.Parcel;
import com.google.android.gms.auth.api.proxy.ProxyGrpcRequest;
import android.os.IBinder;

class zzkk$zza$zza implements zzkk
{
    private IBinder zznJ;
    
    zzkk$zza$zza(final IBinder zznJ) {
        this.zznJ = zznJ;
    }
    
    public IBinder asBinder() {
        return this.zznJ;
    }
    
    @Override
    public void zza(final zzkj zzkj, final ProxyGrpcRequest proxyGrpcRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.internal.IAuthService");
            IBinder binder;
            if (zzkj != null) {
                binder = zzkj.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (proxyGrpcRequest != null) {
                obtain.writeInt(1);
                proxyGrpcRequest.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.zznJ.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void zza(final zzkj zzkj, final ProxyRequest proxyRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.internal.IAuthService");
            IBinder binder;
            if (zzkj != null) {
                binder = zzkj.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (proxyRequest != null) {
                obtain.writeInt(1);
                proxyRequest.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.zznJ.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
