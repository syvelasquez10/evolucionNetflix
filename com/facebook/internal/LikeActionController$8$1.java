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
import com.facebook.LoggingBehavior;
import com.facebook.RequestBatch;
import com.facebook.RequestBatch$Callback;

class LikeActionController$8$1 implements RequestBatch$Callback
{
    final /* synthetic */ LikeActionController$8 this$1;
    final /* synthetic */ LikeActionController$GetEngagementRequestWrapper val$engagementRequest;
    final /* synthetic */ LikeActionController$GetOGObjectLikesRequestWrapper val$objectLikesRequest;
    
    LikeActionController$8$1(final LikeActionController$8 this$1, final LikeActionController$GetOGObjectLikesRequestWrapper val$objectLikesRequest, final LikeActionController$GetEngagementRequestWrapper val$engagementRequest) {
        this.this$1 = this$1;
        this.val$objectLikesRequest = val$objectLikesRequest;
        this.val$engagementRequest = val$engagementRequest;
    }
    
    @Override
    public void onBatchCompleted(final RequestBatch requestBatch) {
        if (this.val$objectLikesRequest.error != null || this.val$engagementRequest.error != null) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Unable to refresh like state for id: '%s'", this.this$1.this$0.objectId);
            return;
        }
        this.this$1.this$0.updateState(this.val$objectLikesRequest.objectIsLiked, this.val$engagementRequest.likeCountStringWithLike, this.val$engagementRequest.likeCountStringWithoutLike, this.val$engagementRequest.socialSentenceStringWithLike, this.val$engagementRequest.socialSentenceStringWithoutLike, this.val$objectLikesRequest.unlikeToken);
    }
}
