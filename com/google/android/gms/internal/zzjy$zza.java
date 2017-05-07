// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.auth.api.proxy.ProxyGrpcRequest;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzjy$zza extends Binder implements zzjy
{
    public static zzjy zzaw(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzjy) {
            return (zzjy)queryLocalInterface;
        }
        return new zzjy$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final ProxyGrpcRequest proxyGrpcRequest = null;
        ProxyRequest proxyRequest = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.auth.api.internal.IAuthService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.auth.api.internal.IAuthService");
                final zzjx zzav = zzjx$zza.zzav(parcel.readStrongBinder());
                if (parcel.readInt() != 0) {
                    proxyRequest = (ProxyRequest)ProxyRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(zzav, proxyRequest);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.auth.api.internal.IAuthService");
                final zzjx zzav2 = zzjx$zza.zzav(parcel.readStrongBinder());
                ProxyGrpcRequest proxyGrpcRequest2 = proxyGrpcRequest;
                if (parcel.readInt() != 0) {
                    proxyGrpcRequest2 = (ProxyGrpcRequest)ProxyGrpcRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(zzav2, proxyGrpcRequest2);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
