// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.auth.api.proxy.ProxyResponse;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzjk$zza extends Binder implements zzjk
{
    public static zzjk zzas(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthCallbacks");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzjk) {
            return (zzjk)queryLocalInterface;
        }
        return new zzjk$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.auth.api.internal.IAuthCallbacks");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.auth.api.internal.IAuthCallbacks");
                ProxyResponse proxyResponse;
                if (parcel.readInt() != 0) {
                    proxyResponse = (ProxyResponse)ProxyResponse.CREATOR.createFromParcel(parcel);
                }
                else {
                    proxyResponse = null;
                }
                this.zza(proxyResponse);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
