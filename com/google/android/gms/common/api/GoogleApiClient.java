// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.internal.zzlb$zza;
import android.os.Looper;
import java.io.PrintWriter;
import java.io.FileDescriptor;

public abstract class GoogleApiClient
{
    public abstract void connect();
    
    public abstract void disconnect();
    
    public abstract void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
    
    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }
    
    public int getSessionId() {
        throw new UnsupportedOperationException();
    }
    
    public abstract boolean isConnected();
    
    public abstract boolean isConnecting();
    
    public abstract void reconnect();
    
    public abstract void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener p0);
    
    public abstract void unregisterConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener p0);
    
    public <C extends Api$zzb> C zza(final Api$zzc<C> api$zzc) {
        throw new UnsupportedOperationException();
    }
    
    public <A extends Api$zzb, R extends Result, T extends zzlb$zza<R, A>> T zza(final T t) {
        throw new UnsupportedOperationException();
    }
    
    public <A extends Api$zzb, T extends zzlb$zza<? extends Result, A>> T zzb(final T t) {
        throw new UnsupportedOperationException();
    }
}
