// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import android.os.IBinder;

class zzjk$zza$zza implements zzjk
{
    private IBinder zznF;
    
    zzjk$zza$zza(final IBinder zznF) {
        this.zznF = zznF;
    }
    
    public IBinder asBinder() {
        return this.zznF;
    }
    
    @Override
    public void zza(final ProxyResponse proxyResponse) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.auth.api.internal.IAuthCallbacks");
            if (proxyResponse != null) {
                obtain.writeInt(1);
                proxyResponse.writeToParcel(obtain, 0);
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
