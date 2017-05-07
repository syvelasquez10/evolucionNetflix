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

class LikeActionController$GetEngagementRequestWrapper extends LikeActionController$AbstractRequestWrapper
{
    String likeCountStringWithLike;
    String likeCountStringWithoutLike;
    String socialSentenceStringWithLike;
    String socialSentenceStringWithoutLike;
    final /* synthetic */ LikeActionController this$0;
    
    LikeActionController$GetEngagementRequestWrapper(final LikeActionController this$0, final String s) {
        this.this$0 = this$0;
        super(this$0, s);
        this.likeCountStringWithLike = this.this$0.likeCountStringWithLike;
        this.likeCountStringWithoutLike = this.this$0.likeCountStringWithoutLike;
        this.socialSentenceStringWithLike = this.this$0.socialSentenceWithLike;
        this.socialSentenceStringWithoutLike = this.this$0.socialSentenceWithoutLike;
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "engagement.fields(count_string_with_like,count_string_without_like,social_sentence_with_like,social_sentence_without_like)");
        this.setRequest(new Request(this$0.session, s, bundle, HttpMethod.GET));
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error fetching engagement for object '%s' : %s", this.objectId, facebookRequestError);
        this.this$0.logAppEventForError("get_engagement", facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final Response response) {
        final JSONObject tryGetJSONObjectFromResponse = Utility.tryGetJSONObjectFromResponse(response.getGraphObject(), "engagement");
        if (tryGetJSONObjectFromResponse != null) {
            this.likeCountStringWithLike = tryGetJSONObjectFromResponse.optString("count_string_with_like", this.likeCountStringWithLike);
            this.likeCountStringWithoutLike = tryGetJSONObjectFromResponse.optString("count_string_without_like", this.likeCountStringWithoutLike);
            this.socialSentenceStringWithLike = tryGetJSONObjectFromResponse.optString("social_sentence_with_like", this.socialSentenceStringWithLike);
            this.socialSentenceStringWithoutLike = tryGetJSONObjectFromResponse.optString("social_sentence_without_like", this.socialSentenceStringWithoutLike);
        }
    }
}
