// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.Response;
import com.android.volley.Cache;
import com.android.volley.Request;

public class ClearCacheRequest extends Request<Object>
{
    private final Cache mCache;
    private final Runnable mCallback;
    
    public ClearCacheRequest(final Cache mCache, final Runnable mCallback) {
        super(0, null, null);
        this.mCache = mCache;
        this.mCallback = mCallback;
    }
    
    @Override
    protected void deliverResponse(final Object o) {
    }
    
    @Override
    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }
    
    @Override
    public boolean isCanceled() {
        this.mCache.clear();
        if (this.mCallback != null) {
            new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.mCallback);
        }
        return true;
    }
    
    @Override
    protected Response<Object> parseNetworkResponse(final NetworkResponse networkResponse) {
        return null;
    }
}
