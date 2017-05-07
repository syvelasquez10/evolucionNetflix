// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import android.os.IBinder;

class zzcm$zza$zza implements zzcm
{
    private IBinder zznI;
    
    zzcm$zza$zza(final IBinder zznI) {
        this.zznI = zznI;
    }
    
    public IBinder asBinder() {
        return this.zznI;
    }
    
    @Override
    public IBinder zza(final zzd zzd, final zzd zzd2, final zzd zzd3, final int n) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
            IBinder binder2;
            if (zzd != null) {
                binder2 = zzd.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            IBinder binder3;
            if (zzd2 != null) {
                binder3 = zzd2.asBinder();
            }
            else {
                binder3 = null;
            }
            obtain.writeStrongBinder(binder3);
            IBinder binder4 = binder;
            if (zzd3 != null) {
                binder4 = zzd3.asBinder();
            }
            obtain.writeStrongBinder(binder4);
            obtain.writeInt(n);
            this.zznI.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readStrongBinder();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
