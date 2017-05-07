// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.AuthAccountRequest;
import android.accounts.Account;
import android.os.IInterface;

public interface zzf extends IInterface
{
    void zza(final int p0, final Account p1, final zze p2);
    
    void zza(final AuthAccountRequest p0, final zze p1);
    
    void zza(final ResolveAccountRequest p0, final zzt p1);
    
    void zza(final zzp p0, final int p1, final boolean p2);
    
    void zza(final CheckServerAuthResult p0);
    
    void zza(final RecordConsentRequest p0, final zze p1);
    
    void zza(final zze p0);
    
    void zzaq(final boolean p0);
    
    void zzjq(final int p0);
}
