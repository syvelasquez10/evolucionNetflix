// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;

class zzav$zza$zza implements zzav
{
    private IBinder zznJ;
    
    zzav$zza$zza(final IBinder zznJ) {
        this.zznJ = zznJ;
    }
    
    public IBinder asBinder() {
        return this.zznJ;
    }
    
    @Override
    public String getId() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            this.zznJ.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void zzb(final String s, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            obtain.writeString(s);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.zznJ.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public boolean zzc(final boolean b) {
        final boolean b2 = true;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            int n;
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.zznJ.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 && b2;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String zzn(String string) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            obtain.writeString(string);
            this.zznJ.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            string = obtain2.readString();
            return string;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
