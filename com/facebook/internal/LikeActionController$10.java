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
import android.app.Activity;
import android.content.Intent;
import com.facebook.Session;
import java.util.UUID;
import android.os.Bundle;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import com.facebook.FacebookRequestError;
import com.facebook.LoggingBehavior;
import com.facebook.RequestBatch;
import com.facebook.RequestBatch$Callback;

class LikeActionController$10 implements RequestBatch$Callback
{
    final /* synthetic */ LikeActionController this$0;
    final /* synthetic */ LikeActionController$RequestCompletionCallback val$completionHandler;
    final /* synthetic */ LikeActionController$GetOGObjectIdRequestWrapper val$objectIdRequest;
    final /* synthetic */ LikeActionController$GetPageIdRequestWrapper val$pageIdRequest;
    
    LikeActionController$10(final LikeActionController this$0, final LikeActionController$GetOGObjectIdRequestWrapper val$objectIdRequest, final LikeActionController$GetPageIdRequestWrapper val$pageIdRequest, final LikeActionController$RequestCompletionCallback val$completionHandler) {
        this.this$0 = this$0;
        this.val$objectIdRequest = val$objectIdRequest;
        this.val$pageIdRequest = val$pageIdRequest;
        this.val$completionHandler = val$completionHandler;
    }
    
    @Override
    public void onBatchCompleted(final RequestBatch requestBatch) {
        this.this$0.verifiedObjectId = this.val$objectIdRequest.verifiedObjectId;
        if (Utility.isNullOrEmpty(this.this$0.verifiedObjectId)) {
            this.this$0.verifiedObjectId = this.val$pageIdRequest.verifiedObjectId;
            this.this$0.objectIsPage = this.val$pageIdRequest.objectIsPage;
        }
        if (Utility.isNullOrEmpty(this.this$0.verifiedObjectId)) {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, LikeActionController.TAG, "Unable to verify the FB id for '%s'. Verify that it is a valid FB object or page", this.this$0.objectId);
            final LikeActionController this$0 = this.this$0;
            FacebookRequestError facebookRequestError;
            if (this.val$pageIdRequest.error != null) {
                facebookRequestError = this.val$pageIdRequest.error;
            }
            else {
                facebookRequestError = this.val$objectIdRequest.error;
            }
            this$0.logAppEventForError("get_verified_id", facebookRequestError);
        }
        if (this.val$completionHandler != null) {
            this.val$completionHandler.onComplete();
        }
    }
}
