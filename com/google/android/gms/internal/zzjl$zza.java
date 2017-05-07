// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.auth.api.proxy.ProxyRequest;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzjl$zza extends Binder implements zzjl
{
    public static zzjl zzat(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzjl) {
            return (zzjl)queryLocalInterface;
        }
        return new zzjl$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
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
                final zzjk zzas = zzjk$zza.zzas(parcel.readStrongBinder());
                ProxyRequest proxyRequest;
                if (parcel.readInt() != 0) {
                    proxyRequest = (ProxyRequest)ProxyRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    proxyRequest = null;
                }
                this.zza(zzas, proxyRequest);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
