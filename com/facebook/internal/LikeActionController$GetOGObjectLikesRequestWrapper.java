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
import org.json.JSONArray;
import com.facebook.Response;
import com.facebook.LoggingBehavior;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.HttpMethod;
import android.os.Bundle;

class LikeActionController$GetOGObjectLikesRequestWrapper extends LikeActionController$AbstractRequestWrapper
{
    boolean objectIsLiked;
    final /* synthetic */ LikeActionController this$0;
    String unlikeToken;
    
    LikeActionController$GetOGObjectLikesRequestWrapper(final LikeActionController this$0, final String s) {
        this.this$0 = this$0;
        super(this$0, s);
        this.objectIsLiked = this.this$0.isObjectLiked;
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id,application");
        bundle.putString("object", s);
        this.setRequest(new Request(this$0.session, "me/og.likes", bundle, HttpMethod.GET));
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error fetching like status for object '%s' : %s", this.objectId, facebookRequestError);
        this.this$0.logAppEventForError("get_og_object_like", facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final Response response) {
        final JSONArray tryGetJSONArrayFromResponse = Utility.tryGetJSONArrayFromResponse(response.getGraphObject(), "data");
        if (tryGetJSONArrayFromResponse != null) {
            for (int i = 0; i < tryGetJSONArrayFromResponse.length(); ++i) {
                final JSONObject optJSONObject = tryGetJSONArrayFromResponse.optJSONObject(i);
                if (optJSONObject != null) {
                    this.objectIsLiked = true;
                    final JSONObject optJSONObject2 = optJSONObject.optJSONObject("application");
                    if (optJSONObject2 != null && Utility.areObjectsEqual(this.this$0.session.getApplicationId(), optJSONObject2.optString("id"))) {
                        this.unlikeToken = optJSONObject.optString("id");
                    }
                }
            }
        }
    }
}
