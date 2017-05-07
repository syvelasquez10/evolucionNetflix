// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class IAccountAccessor$zza extends Binder implements IAccountAccessor
{
    public static IAccountAccessor zzaD(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
        if (queryLocalInterface != null && queryLocalInterface instanceof IAccountAccessor) {
            return (IAccountAccessor)queryLocalInterface;
        }
        return new IAccountAccessor$zza$zza(binder);
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
                parcel2.writeString("com.google.android.gms.common.internal.IAccountAccessor");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IAccountAccessor");
                final Account account = this.getAccount();
                parcel2.writeNoException();
                if (account != null) {
                    parcel2.writeInt(1);
                    account.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
        }
    }
}
