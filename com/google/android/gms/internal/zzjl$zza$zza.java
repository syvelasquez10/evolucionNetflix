// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import android.os.IBinder;

class zzjl$zza$zza implements zzjl
{
    private IBinder zznF;
    
    zzjl$zza$zza(final IBinder zznF) {
        this.zznF = zznF;
    }
    
    public IBinder asBinder() {
        return this.zznF;
    }
    
    @Override
    public void zza(final zzjk zzjk, final ProxyRequest proxyRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.internal.IAuthService");
            IBinder binder;
            if (zzjk != null) {
                binder = zzjk.asBinder();
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
            this.zznF.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
