// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.Closeable;
import java.io.IOException;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.facebook.Settings;
import com.facebook.SessionState;
import android.os.Looper;
import com.facebook.widget.FacebookDialog$PendingCall;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog$Callback;
import com.facebook.RequestBatch$Callback;
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import android.support.v4.content.LocalBroadcastManager;
import android.app.Activity;
import android.content.Intent;
import com.facebook.Session;
import java.util.UUID;
import android.os.Bundle;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import com.facebook.Request$Callback;
import com.facebook.Response;
import com.facebook.LoggingBehavior;
import com.facebook.RequestBatch;
import com.facebook.Request;
import com.facebook.FacebookRequestError;

abstract class LikeActionController$AbstractRequestWrapper
{
    FacebookRequestError error;
    protected String objectId;
    private Request request;
    final /* synthetic */ LikeActionController this$0;
    
    protected LikeActionController$AbstractRequestWrapper(final LikeActionController this$0, final String objectId) {
        this.this$0 = this$0;
        this.objectId = objectId;
    }
    
    void addToBatch(final RequestBatch requestBatch) {
        requestBatch.add(this.request);
    }
    
    protected void processError(final FacebookRequestError facebookRequestError) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error running request for object '%s' : %s", this.objectId, facebookRequestError);
    }
    
    protected abstract void processSuccess(final Response p0);
    
    protected void setRequest(final Request request) {
        (this.request = request).setVersion("v2.2");
        request.setCallback(new LikeActionController$AbstractRequestWrapper$1(this));
    }
}
