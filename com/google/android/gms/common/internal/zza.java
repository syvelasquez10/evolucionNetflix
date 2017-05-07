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
    private Account zzQd;
    int zzaeG;
    
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
        return this == o || (o instanceof zza && this.zzQd.equals((Object)((zza)o).zzQd));
    }
    
    public Account getAccount() {
        final int callingUid = Binder.getCallingUid();
        if (callingUid == this.zzaeG) {
            return this.zzQd;
        }
        if (GooglePlayServicesUtil.zze(this.mContext, callingUid)) {
            this.zzaeG = callingUid;
            return this.zzQd;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }
}
