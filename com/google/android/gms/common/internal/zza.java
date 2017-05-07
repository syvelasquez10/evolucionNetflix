// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.RemoteException;
import android.util.Log;
import android.os.Binder;
import android.accounts.Account;
import android.content.Context;

public class zza extends IAccountAccessor$zza
{
    private Context mContext;
    private Account zzMY;
    int zzZM;
    
    public static Account zza(final IAccountAccessor accountAccessor) {
        Account account = null;
        if (accountAccessor == null) {
            return account;
        }
        final long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            account = accountAccessor.getAccount();
            return account;
        }
        catch (RemoteException ex) {
            Log.w("AccountAccessor", "Remote account accessor probably died");
            return null;
        }
        finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof zza && this.zzMY.equals((Object)((zza)o).zzMY));
    }
    
    public Account getAccount() {
        final int callingUid = Binder.getCallingUid();
        if (callingUid == this.zzZM) {
            return this.zzMY;
        }
        if (GooglePlayServicesUtil.zzd(this.mContext, callingUid)) {
            this.zzZM = callingUid;
            return this.zzMY;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }
}
