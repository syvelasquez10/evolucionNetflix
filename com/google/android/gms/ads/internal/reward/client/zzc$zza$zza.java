// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.client;

import android.os.Parcel;
import com.google.android.gms.internal.zzem;
import com.google.android.gms.dynamic.zzd;
import android.os.IBinder;

class zzc$zza$zza implements zzc
{
    private IBinder zznJ;
    
    zzc$zza$zza(final IBinder zznJ) {
        this.zznJ = zznJ;
    }
    
    public IBinder asBinder() {
        return this.zznJ;
    }
    
    @Override
    public IBinder zza(final zzd zzd, final zzem zzem, final int n) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
            IBinder binder2;
            if (zzd != null) {
                binder2 = zzd.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            IBinder binder3 = binder;
            if (zzem != null) {
                binder3 = zzem.asBinder();
            }
            obtain.writeStrongBinder(binder3);
            obtain.writeInt(n);
            this.zznJ.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readStrongBinder();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
