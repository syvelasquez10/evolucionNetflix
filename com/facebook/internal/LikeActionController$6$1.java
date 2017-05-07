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
import android.app.Activity;
import android.content.Intent;
import com.facebook.Session;
import java.util.UUID;
import android.os.Bundle;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import com.facebook.RequestBatch;
import com.facebook.RequestBatch$Callback;

class LikeActionController$6$1 implements RequestBatch$Callback
{
    final /* synthetic */ LikeActionController$6 this$1;
    final /* synthetic */ LikeActionController$PublishLikeRequestWrapper val$likeRequest;
    
    LikeActionController$6$1(final LikeActionController$6 this$1, final LikeActionController$PublishLikeRequestWrapper val$likeRequest) {
        this.this$1 = this$1;
        this.val$likeRequest = val$likeRequest;
    }
    
    @Override
    public void onBatchCompleted(final RequestBatch requestBatch) {
        this.this$1.this$0.isPendingLikeOrUnlike = false;
        if (this.val$likeRequest.error != null) {
            this.this$1.this$0.updateState(false, this.this$1.this$0.likeCountStringWithLike, this.this$1.this$0.likeCountStringWithoutLike, this.this$1.this$0.socialSentenceWithLike, this.this$1.this$0.socialSentenceWithoutLike, this.this$1.this$0.unlikeToken);
            this.this$1.this$0.presentLikeDialog(this.this$1.val$activity, this.this$1.val$analyticsParameters);
            return;
        }
        this.this$1.this$0.unlikeToken = Utility.coerceValueIfNullOrEmpty(this.val$likeRequest.unlikeToken, null);
        this.this$1.this$0.isObjectLikedOnServer = true;
        this.this$1.this$0.appEventsLogger.logSdkEvent("fb_like_control_did_like", null, this.this$1.val$analyticsParameters);
        this.this$1.this$0.toggleAgainIfNeeded(this.this$1.val$activity, this.this$1.val$analyticsParameters);
    }
}
