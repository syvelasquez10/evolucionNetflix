// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.AuthAccountRequest;
import android.accounts.Account;
import android.os.IInterface;

public interface zzf extends IInterface
{
    void zza(final int p0, final Account p1, final zze p2);
    
    void zza(final AuthAccountRequest p0, final zze p1);
    
    void zza(final IAccountAccessor p0, final int p1, final boolean p2);
    
    void zza(final ResolveAccountRequest p0, final zzq p1);
    
    void zza(final CheckServerAuthResult p0);
    
    void zzal(final boolean p0);
    
    void zziQ(final int p0);
}
