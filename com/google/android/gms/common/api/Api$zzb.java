// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.Set;
import com.google.android.gms.common.internal.zzp;
import java.io.PrintWriter;
import java.io.FileDescriptor;

public interface Api$zzb
{
    void disconnect();
    
    void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
    
    boolean isConnected();
    
    void zza(final GoogleApiClient$zza p0);
    
    void zza(final zzp p0);
    
    void zza(final zzp p0, final Set<Scope> p1);
    
    boolean zzlm();
}
