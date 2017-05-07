// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zzd$zza;
import com.google.android.gms.dynamic.zzd;
import android.net.Uri;
import android.os.Parcel;
import android.os.IBinder;

class zzcm$zza$zza implements zzcm
{
    private IBinder zznJ;
    
    zzcm$zza$zza(final IBinder zznJ) {
        this.zznJ = zznJ;
    }
    
    public IBinder asBinder() {
        return this.zznJ;
    }
    
    @Override
    public double getScale() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
            this.zznJ.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readDouble();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Uri getUri() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
            this.zznJ.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            Uri uri;
            if (obtain2.readInt() != 0) {
                uri = (Uri)Uri.CREATOR.createFromParcel(obtain2);
            }
            else {
                uri = null;
            }
            return uri;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public zzd zzdv() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
            this.zznJ.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return zzd$zza.zzbk(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
