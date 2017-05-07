// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.accounts.Account;
import android.os.IBinder;

class zzkd$zza$zza implements zzkd
{
    private IBinder zznJ;
    
    zzkd$zza$zza(final IBinder zznJ) {
        this.zznJ = zznJ;
    }
    
    public IBinder asBinder() {
        return this.zznJ;
    }
    
    @Override
    public void zza(final Account account, final int n, final zzkc zzkc) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService");
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(n);
                    if (zzkc != null) {
                        final IBinder binder = zzkc.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.zznJ.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                final IBinder binder = null;
                continue;
            }
        }
    }
}
