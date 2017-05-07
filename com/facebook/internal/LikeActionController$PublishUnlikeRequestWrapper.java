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
import com.facebook.RequestBatch;
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import android.support.v4.content.LocalBroadcastManager;
import android.app.Activity;
import android.content.Intent;
import com.facebook.Session;
import java.util.UUID;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import com.facebook.Response;
import com.facebook.LoggingBehavior;
import com.facebook.FacebookRequestError;
import android.os.Bundle;
import com.facebook.Request;
import com.facebook.HttpMethod;

class LikeActionController$PublishUnlikeRequestWrapper extends LikeActionController$AbstractRequestWrapper
{
    final /* synthetic */ LikeActionController this$0;
    private String unlikeToken;
    
    LikeActionController$PublishUnlikeRequestWrapper(final LikeActionController this$0, final String unlikeToken) {
        this.this$0 = this$0;
        super(this$0, null);
        this.unlikeToken = unlikeToken;
        this.setRequest(new Request(this$0.session, unlikeToken, null, HttpMethod.DELETE));
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error unliking object with unlike token '%s' : %s", this.unlikeToken, facebookRequestError);
        this.this$0.logAppEventForError("publish_unlike", facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final Response response) {
    }
}
