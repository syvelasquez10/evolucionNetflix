// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.internal.fc;
import android.content.Context;
import android.os.Looper;
import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;

public final class Api<O extends ApiOptions>
{
    private final ArrayList<Scope> AA;
    private final b<?, O> Ay;
    private final c<?> Az;
    
    public Api(final b<C, O> ay, final c<C> az, final Scope... array) {
        this.Ay = ay;
        this.Az = az;
        this.AA = new ArrayList<Scope>(Arrays.asList(array));
    }
    
    public b<?, O> dY() {
        return this.Ay;
    }
    
    public List<Scope> dZ() {
        return this.AA;
    }
    
    public c<?> ea() {
        return this.Az;
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
        T a(final Context p0, final Looper p1, final fc p2, final O p3, final GoogleApiClient.ConnectionCallbacks p4, final GoogleApiClient.OnConnectionFailedListener p5);
        
        int getPriority();
    }
    
    public static final class c<C extends a>
    {
    }
}
