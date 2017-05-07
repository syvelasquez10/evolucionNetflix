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

public class zza extends zzp$zza
{
    private Context mContext;
    private Account zzOY;
    int zzacB;
    
    public static Account zzb(final zzp zzp) {
        Account account = null;
        if (zzp == null) {
            return account;
        }
        final long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            account = zzp.getAccount();
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
        return this == o || (o instanceof zza && this.zzOY.equals((Object)((zza)o).zzOY));
    }
    
    public Account getAccount() {
        final int callingUid = Binder.getCallingUid();
        if (callingUid == this.zzacB) {
            return this.zzOY;
        }
        if (GooglePlayServicesUtil.zze(this.mContext, callingUid)) {
            this.zzacB = callingUid;
            return this.zzOY;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }
}
