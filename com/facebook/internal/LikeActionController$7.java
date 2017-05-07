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
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.FacebookRequestError;
import android.content.Intent;
import com.facebook.Session;
import java.util.UUID;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import com.facebook.RequestBatch;
import android.os.Bundle;
import android.app.Activity;
import com.facebook.RequestBatch$Callback;

class LikeActionController$7 implements RequestBatch$Callback
{
    final /* synthetic */ LikeActionController this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ Bundle val$analyticsParameters;
    final /* synthetic */ LikeActionController$PublishUnlikeRequestWrapper val$unlikeRequest;
    
    LikeActionController$7(final LikeActionController this$0, final LikeActionController$PublishUnlikeRequestWrapper val$unlikeRequest, final Activity val$activity, final Bundle val$analyticsParameters) {
        this.this$0 = this$0;
        this.val$unlikeRequest = val$unlikeRequest;
        this.val$activity = val$activity;
        this.val$analyticsParameters = val$analyticsParameters;
    }
    
    @Override
    public void onBatchCompleted(final RequestBatch requestBatch) {
        this.this$0.isPendingLikeOrUnlike = false;
        if (this.val$unlikeRequest.error != null) {
            this.this$0.updateState(true, this.this$0.likeCountStringWithLike, this.this$0.likeCountStringWithoutLike, this.this$0.socialSentenceWithLike, this.this$0.socialSentenceWithoutLike, this.this$0.unlikeToken);
            this.this$0.presentLikeDialog(this.val$activity, this.val$analyticsParameters);
            return;
        }
        this.this$0.unlikeToken = null;
        this.this$0.isObjectLikedOnServer = false;
        this.this$0.appEventsLogger.logSdkEvent("fb_like_control_did_unlike", null, this.val$analyticsParameters);
        this.this$0.toggleAgainIfNeeded(this.val$activity, this.val$analyticsParameters);
    }
}
