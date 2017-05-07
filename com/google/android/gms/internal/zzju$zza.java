// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzju$zza extends Binder implements zzju
{
    public static zzju zzaq(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzju) {
            return (zzju)queryLocalInterface;
        }
        return new zzju$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService");
                Account account;
                if (parcel.readInt() != 0) {
                    account = (Account)Account.CREATOR.createFromParcel(parcel);
                }
                else {
                    account = null;
                }
                this.zza(account, parcel.readInt(), zzjt$zza.zzap(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
