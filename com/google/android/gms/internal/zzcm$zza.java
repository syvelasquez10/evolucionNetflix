// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.net.Uri;
import com.google.android.gms.dynamic.zzd;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzcm$zza extends Binder implements zzcm
{
    public static zzcm zzt(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzcm) {
            return (zzcm)queryLocalInterface;
        }
        return new zzcm$zza$zza(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                final zzd zzdv = this.zzdv();
                parcel2.writeNoException();
                IBinder binder;
                if (zzdv != null) {
                    binder = zzdv.asBinder();
                }
                else {
                    binder = null;
                }
                parcel2.writeStrongBinder(binder);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                final Uri uri = this.getUri();
                parcel2.writeNoException();
                if (uri != null) {
                    parcel2.writeInt(1);
                    uri.writeToParcel(parcel2, 1);
                }
                else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                final double scale = this.getScale();
                parcel2.writeNoException();
                parcel2.writeDouble(scale);
                return true;
            }
        }
    }
}
