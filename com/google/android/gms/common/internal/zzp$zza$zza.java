// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.accounts.Account;
import android.os.IBinder;

class zzp$zza$zza implements zzp
{
    private IBinder zznJ;
    
    zzp$zza$zza(final IBinder zznJ) {
        this.zznJ = zznJ;
    }
    
    public IBinder asBinder() {
        return this.zznJ;
    }
    
    @Override
    public Account getAccount() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IAccountAccessor");
            this.zznJ.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            Account account;
            if (obtain2.readInt() != 0) {
                account = (Account)Account.CREATOR.createFromParcel(obtain2);
            }
            else {
                account = null;
            }
            return account;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
