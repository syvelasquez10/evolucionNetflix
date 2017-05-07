// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.internal.dt;
import android.content.Context;
import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;

public final class Api
{
    private final b<?> mS;
    private final ArrayList<Scope> mT;
    
    public Api(final b<?> ms, final Scope... array) {
        this.mS = ms;
        this.mT = new ArrayList<Scope>(Arrays.asList(array));
    }
    
    public b<?> bj() {
        return this.mS;
    }
    
    public List<Scope> bk() {
        return this.mT;
    }
    
    public interface a
    {
        void connect();
        
        void disconnect();
        
        boolean isConnected();
    }
    
    public interface b<T extends a>
    {
        T b(final Context p0, final dt p1, final GoogleApiClient.ApiOptions p2, final GoogleApiClient.ConnectionCallbacks p3, final GoogleApiClient.OnConnectionFailedListener p4);
        
        int getPriority();
    }
}
