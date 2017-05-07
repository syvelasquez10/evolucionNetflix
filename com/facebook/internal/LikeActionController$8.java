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
import com.facebook.RequestBatch$Callback;
import com.facebook.RequestBatch;

class LikeActionController$8 implements LikeActionController$RequestCompletionCallback
{
    final /* synthetic */ LikeActionController this$0;
    
    LikeActionController$8(final LikeActionController this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onComplete() {
        final LikeActionController$GetOGObjectLikesRequestWrapper likeActionController$GetOGObjectLikesRequestWrapper = new LikeActionController$GetOGObjectLikesRequestWrapper(this.this$0, this.this$0.verifiedObjectId);
        final LikeActionController$GetEngagementRequestWrapper likeActionController$GetEngagementRequestWrapper = new LikeActionController$GetEngagementRequestWrapper(this.this$0, this.this$0.verifiedObjectId);
        final RequestBatch requestBatch = new RequestBatch();
        likeActionController$GetOGObjectLikesRequestWrapper.addToBatch(requestBatch);
        likeActionController$GetEngagementRequestWrapper.addToBatch(requestBatch);
        requestBatch.addCallback(new LikeActionController$8$1(this, likeActionController$GetOGObjectLikesRequestWrapper, likeActionController$GetEngagementRequestWrapper));
        requestBatch.executeAsync();
    }
}
