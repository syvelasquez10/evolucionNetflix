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
import com.facebook.Request;
import com.facebook.HttpMethod;
import android.os.Bundle;

class LikeActionController$PublishLikeRequestWrapper extends LikeActionController$AbstractRequestWrapper
{
    final /* synthetic */ LikeActionController this$0;
    String unlikeToken;
    
    LikeActionController$PublishLikeRequestWrapper(final LikeActionController this$0, final String s) {
        this.this$0 = this$0;
        super(this$0, s);
        final Bundle bundle = new Bundle();
        bundle.putString("object", s);
        this.setRequest(new Request(this$0.session, "me/og.likes", bundle, HttpMethod.POST));
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        if (facebookRequestError.getErrorCode() == 3501) {
            this.error = null;
            return;
        }
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error liking object '%s' : %s", this.objectId, facebookRequestError);
        this.this$0.logAppEventForError("publish_like", facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final Response response) {
        this.unlikeToken = Utility.safeGetStringFromResponse(response.getGraphObject(), "id");
    }
}
