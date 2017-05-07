// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.accounts.Account;
import android.os.IBinder;

class IAccountAccessor$zza$zza implements IAccountAccessor
{
    private IBinder zznF;
    
    IAccountAccessor$zza$zza(final IBinder zznF) {
        this.zznF = zznF;
    }
    
    public IBinder asBinder() {
        return this.zznF;
    }
    
    @Override
    public Account getAccount() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IAccountAccessor");
            this.zznF.transact(2, obtain, obtain2, 0);
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
