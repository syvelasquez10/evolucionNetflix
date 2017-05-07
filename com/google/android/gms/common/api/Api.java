// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.ClientSettings;
import android.content.Context;
import android.os.Looper;
import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;

public final class Api<O extends ApiOptions>
{
    private final b<?, O> Ij;
    private final c<?> Ik;
    private final ArrayList<Scope> Il;
    
    public Api(final b<C, O> ij, final c<C> ik, final Scope... array) {
        this.Ij = ij;
        this.Ik = ik;
        this.Il = new ArrayList<Scope>(Arrays.asList(array));
    }
    
    public b<?, O> gd() {
        return this.Ij;
    }
    
    public List<Scope> ge() {
        return this.Il;
    }
    
    public c<?> gf() {
        return this.Ik;
    }
    
    public interface ApiOptions
    {
        public interface HasOptions extends ApiOptions
        {
        }
        
        public static final class NoOptions implements NotRequiredOptions
        {
        }
        
        public interface NotRequiredOptions extends ApiOptions
        {
        }
        
        public interface Optional extends HasOptions, NotRequiredOptions
        {
        }
    }
    
    public interface a
    {
        void connect();
        
        void disconnect();
        
        Looper getLooper();
        
        boolean isConnected();
    }
    
    public interface b<T extends a, O>
    {
        T a(final Context p0, final Looper p1, final ClientSettings p2, final O p3, final GoogleApiClient.ConnectionCallbacks p4, final GoogleApiClient.OnConnectionFailedListener p5);
        
        int getPriority();
    }
    
    public static final class c<C extends a>
    {
    }
}
