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
import com.facebook.RequestBatch$Callback;
import com.facebook.RequestBatch;
import android.os.Bundle;
import android.app.Activity;

class LikeActionController$6 implements LikeActionController$RequestCompletionCallback
{
    final /* synthetic */ LikeActionController this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ Bundle val$analyticsParameters;
    
    LikeActionController$6(final LikeActionController this$0, final Activity val$activity, final Bundle val$analyticsParameters) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
        this.val$analyticsParameters = val$analyticsParameters;
    }
    
    @Override
    public void onComplete() {
        if (Utility.isNullOrEmpty(this.this$0.verifiedObjectId)) {
            final Bundle bundle = new Bundle();
            bundle.putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Invalid Object Id");
            broadcastAction(this.this$0.context, this.this$0, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
            return;
        }
        final RequestBatch requestBatch = new RequestBatch();
        final LikeActionController$PublishLikeRequestWrapper likeActionController$PublishLikeRequestWrapper = new LikeActionController$PublishLikeRequestWrapper(this.this$0, this.this$0.verifiedObjectId);
        likeActionController$PublishLikeRequestWrapper.addToBatch(requestBatch);
        requestBatch.addCallback(new LikeActionController$6$1(this, likeActionController$PublishLikeRequestWrapper));
        requestBatch.executeAsync();
    }
}
