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
import com.facebook.widget.FacebookDialog;
import com.facebook.RequestBatch$Callback;
import com.facebook.RequestBatch;
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.FacebookRequestError;
import android.app.Activity;
import android.content.Intent;
import com.facebook.Session;
import java.util.UUID;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import com.facebook.LoggingBehavior;
import com.facebook.widget.FacebookDialog$PendingCall;
import android.os.Bundle;
import com.facebook.widget.FacebookDialog$Callback;

class LikeActionController$5 implements FacebookDialog$Callback
{
    final /* synthetic */ LikeActionController this$0;
    final /* synthetic */ Bundle val$analyticsParameters;
    
    LikeActionController$5(final LikeActionController this$0, final Bundle val$analyticsParameters) {
        this.this$0 = this$0;
        this.val$analyticsParameters = val$analyticsParameters;
    }
    
    @Override
    public void onComplete(final FacebookDialog$PendingCall facebookDialog$PendingCall, final Bundle bundle) {
        if (bundle == null || !bundle.containsKey("object_is_liked")) {
            return;
        }
        final boolean boolean1 = bundle.getBoolean("object_is_liked");
        String s = this.this$0.likeCountStringWithLike;
        String access$900 = this.this$0.likeCountStringWithoutLike;
        if (bundle.containsKey("like_count_string")) {
            access$900 = (s = bundle.getString("like_count_string"));
        }
        String s2 = this.this$0.socialSentenceWithLike;
        String access$901 = this.this$0.socialSentenceWithoutLike;
        if (bundle.containsKey("social_sentence")) {
            access$901 = (s2 = bundle.getString("social_sentence"));
        }
        String s3;
        if (bundle.containsKey("object_is_liked")) {
            s3 = bundle.getString("unlike_token");
        }
        else {
            s3 = this.this$0.unlikeToken;
        }
        Bundle val$analyticsParameters;
        if (this.val$analyticsParameters == null) {
            val$analyticsParameters = new Bundle();
        }
        else {
            val$analyticsParameters = this.val$analyticsParameters;
        }
        val$analyticsParameters.putString("call_id", facebookDialog$PendingCall.getCallId().toString());
        this.this$0.appEventsLogger.logSdkEvent("fb_like_control_dialog_did_succeed", null, val$analyticsParameters);
        this.this$0.updateState(boolean1, s, access$900, s2, access$901, s3);
    }
    
    @Override
    public void onError(final FacebookDialog$PendingCall facebookDialog$PendingCall, final Exception ex, final Bundle bundle) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Like Dialog failed with error : %s", ex);
        Bundle val$analyticsParameters;
        if (this.val$analyticsParameters == null) {
            val$analyticsParameters = new Bundle();
        }
        else {
            val$analyticsParameters = this.val$analyticsParameters;
        }
        val$analyticsParameters.putString("call_id", facebookDialog$PendingCall.getCallId().toString());
        this.this$0.logAppEventForError("present_dialog", val$analyticsParameters);
        broadcastAction(this.this$0.context, this.this$0, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
    }
}
