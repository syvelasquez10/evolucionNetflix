// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.signin.internal.zze;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.api.Api$Client;

public interface zzps extends Api$Client
{
    void connect();
    
    void zza(final IAccountAccessor p0, final Set<Scope> p1, final zze p2);
    
    void zza(final IAccountAccessor p0, final boolean p1);
    
    void zza(final zzq p0);
    
    void zzxW();
}
