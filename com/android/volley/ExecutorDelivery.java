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
        this.mResponsePoster = new Executor() {
            @Override
            public void execute(final Runnable runnable) {
                handler.post(runnable);
            }
        };
    }
    
    public ExecutorDelivery(final Executor mResponsePoster) {
        this.mResponsePoster = mResponsePoster;
    }
    
    @Override
    public void postError(final Request<?> request, final VolleyError volleyError) {
        request.addMarker("post-error");
        this.mResponsePoster.execute(new ResponseDeliveryRunnable(request, Response.error(volleyError), null));
    }
    
    @Override
    public void postResponse(final Request<?> request, final Response<?> response) {
        this.postResponse(request, response, null);
    }
    
    @Override
    public void postResponse(final Request<?> request, final Response<?> response, final Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        this.mResponsePoster.execute(new ResponseDeliveryRunnable(request, response, runnable));
    }
    
    private class ResponseDeliveryRunnable implements Runnable
    {
        private final Request mRequest;
        private final Response mResponse;
        private final Runnable mRunnable;
        
        public ResponseDeliveryRunnable(final Request mRequest, final Response mResponse, final Runnable mRunnable) {
            this.mRequest = mRequest;
            this.mResponse = mResponse;
            this.mRunnable = mRunnable;
        }
        
        @Override
        public void run() {
            if (this.mRequest.isCanceled()) {
                this.mRequest.finish("canceled-at-delivery");
            }
            else {
                if (this.mResponse.isSuccess()) {
                    this.mRequest.deliverResponse(this.mResponse.result);
                }
                else {
                    this.mRequest.deliverError(this.mResponse.error);
                }
                if (this.mResponse.intermediate) {
                    this.mRequest.addMarker("intermediate-response");
                }
                else {
                    this.mRequest.finish("done");
                }
                if (this.mRunnable != null) {
                    this.mRunnable.run();
                }
            }
        }
    }
}
