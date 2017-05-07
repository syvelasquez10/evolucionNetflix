// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.servicemgr.INetflixServiceCallback;
import android.util.SparseArray;

public class NetflixService$ClientCallbacks extends SparseArray<INetflixServiceCallback>
{
    public int put(final INetflixServiceCallback netflixServiceCallback) {
        ThreadUtils.assertOnMain();
        final int hashCode = netflixServiceCallback.hashCode();
        super.put(hashCode, (Object)netflixServiceCallback);
        return hashCode;
    }
    
    public INetflixServiceCallback remove(INetflixServiceCallback netflixServiceCallback) {
        ThreadUtils.assertOnMain();
        final int hashCode = netflixServiceCallback.hashCode();
        netflixServiceCallback = (INetflixServiceCallback)this.get(hashCode);
        super.remove(hashCode);
        return netflixServiceCallback;
    }
}
