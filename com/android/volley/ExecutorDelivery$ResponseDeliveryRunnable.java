// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

class ExecutorDelivery$ResponseDeliveryRunnable implements Runnable
{
    private final Request mRequest;
    private final Response mResponse;
    private final Runnable mRunnable;
    final /* synthetic */ ExecutorDelivery this$0;
    
    public ExecutorDelivery$ResponseDeliveryRunnable(final ExecutorDelivery this$0, final Request mRequest, final Response mResponse, final Runnable mRunnable) {
        this.this$0 = this$0;
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
