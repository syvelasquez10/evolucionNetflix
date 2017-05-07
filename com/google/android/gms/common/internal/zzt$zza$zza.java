// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.IBinder;

class zzt$zza$zza implements zzt
{
    private IBinder zznJ;
    
    zzt$zza$zza(final IBinder zznJ) {
        this.zznJ = zznJ;
    }
    
    public IBinder asBinder() {
        return this.zznJ;
    }
    
    @Override
    public void zzb(final ResolveAccountResponse resolveAccountResponse) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IResolveAccountCallbacks");
            if (resolveAccountResponse != null) {
                obtain.writeInt(1);
                resolveAccountResponse.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.zznJ.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
