// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin;

import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.signin.internal.zze;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.api.Api$zzb;

public interface zzd extends Api$zzb
{
    void connect();
    
    void zza(final zzp p0, final Set<Scope> p1, final zze p2);
    
    void zza(final zzp p0, final boolean p1);
    
    void zza(final zzt p0);
    
    void zzzn();
}
