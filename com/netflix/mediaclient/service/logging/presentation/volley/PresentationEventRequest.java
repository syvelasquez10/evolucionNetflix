// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.presentation.volley;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.Log;
import java.util.Map;
import com.netflix.mediaclient.service.logging.presentation.PresentationRequest;
import com.netflix.mediaclient.service.logging.presentation.PresentationWebCallback;

public class PresentationEventRequest extends PresentationVolleyWebClientRequest<String>
{
    private static final String TAG = "nf_presentation";
    private final PresentationWebCallback mCallback;
    private final String mDeliveryRequestId;
    private final PresentationRequest mRequestObj;
    
    public PresentationEventRequest(final String mDeliveryRequestId, final PresentationRequest mRequestObj, final PresentationWebCallback mCallback) {
        this.mCallback = mCallback;
        this.mDeliveryRequestId = mDeliveryRequestId;
        this.mRequestObj = mRequestObj;
    }
    
    public Map<String, String> getParams() {
        final Map<String, String> requestParams = this.mRequestObj.toRequestParams();
        if (Log.isLoggable()) {
            Log.d("nf_presentation", "params size " + requestParams.toString().length());
        }
        return requestParams;
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (Log.isLoggable()) {
            Log.v("nf_presentation", "presentationEvent FAIL : " + status);
        }
        if (this.mCallback != null) {
            this.mCallback.onEventsDeliveryFailed(this.mDeliveryRequestId);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        Log.v("nf_presentation", "presentationEvent OK : ");
        if (this.mCallback != null) {
            this.mCallback.onEventsDelivered(this.mDeliveryRequestId);
        }
    }
    
    @Override
    protected String parseResponse(final String s) {
        return "OK";
    }
}
