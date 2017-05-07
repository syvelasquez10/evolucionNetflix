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
import com.facebook.FacebookRequestError;
import android.app.Activity;
import android.content.Intent;
import com.facebook.Session;
import java.util.UUID;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import android.os.Bundle;

class LikeActionController$9 implements PlatformServiceClient$CompletedListener
{
    final /* synthetic */ LikeActionController this$0;
    
    LikeActionController$9(final LikeActionController this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void completed(final Bundle bundle) {
        if (bundle == null || !bundle.containsKey("com.facebook.platform.extra.OBJECT_IS_LIKED")) {
            return;
        }
        final boolean boolean1 = bundle.getBoolean("com.facebook.platform.extra.OBJECT_IS_LIKED");
        String s;
        if (bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE")) {
            s = bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE");
        }
        else {
            s = this.this$0.likeCountStringWithLike;
        }
        String s2;
        if (bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE")) {
            s2 = bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE");
        }
        else {
            s2 = this.this$0.likeCountStringWithoutLike;
        }
        String s3;
        if (bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE")) {
            s3 = bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE");
        }
        else {
            s3 = this.this$0.socialSentenceWithLike;
        }
        String s4;
        if (bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE")) {
            s4 = bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE");
        }
        else {
            s4 = this.this$0.socialSentenceWithoutLike;
        }
        String s5;
        if (bundle.containsKey("com.facebook.platform.extra.UNLIKE_TOKEN")) {
            s5 = bundle.getString("com.facebook.platform.extra.UNLIKE_TOKEN");
        }
        else {
            s5 = this.this$0.unlikeToken;
        }
        this.this$0.updateState(boolean1, s, s2, s3, s4, s5);
    }
}
