// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

public class ExecutorDelivery implements ResponseDelivery
{
    private final Executor mResponsePoster;
    
    public ExecutorDelivery(final Handler handler) {
        this.mResponsePoster = new ExecutorDelivery$1(this, handler);
    }
    
    @Override
    public void postError(final Request<?> request, final VolleyError volleyError) {
        request.addMarker("post-error");
        this.mResponsePoster.execute(new ExecutorDelivery$ResponseDeliveryRunnable(this, request, Response.error(volleyError), null));
    }
    
    @Override
    public void postResponse(final Request<?> request, final Response<?> response) {
        this.postResponse(request, response, null);
    }
    
    @Override
    public void postResponse(final Request<?> request, final Response<?> response, final Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        this.mResponsePoster.execute(new ExecutorDelivery$ResponseDeliveryRunnable(this, request, response, runnable));
    }
}
