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
import android.support.v4.content.LocalBroadcastManager;
import android.app.Activity;
import android.content.Intent;
import com.facebook.Session;
import java.util.UUID;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
import com.facebook.Response;
import com.facebook.LoggingBehavior;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.HttpMethod;
import android.os.Bundle;

class LikeActionController$GetPageIdRequestWrapper extends LikeActionController$AbstractRequestWrapper
{
    boolean objectIsPage;
    final /* synthetic */ LikeActionController this$0;
    String verifiedObjectId;
    
    LikeActionController$GetPageIdRequestWrapper(final LikeActionController this$0, final String s) {
        this.this$0 = this$0;
        super(this$0, s);
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id");
        bundle.putString("ids", s);
        this.setRequest(new Request(this$0.session, "", bundle, HttpMethod.GET));
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error getting the FB id for object '%s' : %s", this.objectId, facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final Response response) {
        final JSONObject tryGetJSONObjectFromResponse = Utility.tryGetJSONObjectFromResponse(response.getGraphObject(), this.objectId);
        if (tryGetJSONObjectFromResponse != null) {
            this.verifiedObjectId = tryGetJSONObjectFromResponse.optString("id");
            this.objectIsPage = !Utility.isNullOrEmpty(this.verifiedObjectId);
        }
    }
}
