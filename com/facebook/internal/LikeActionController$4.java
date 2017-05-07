// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.Closeable;
import java.io.IOException;
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
import com.facebook.Session;
import java.util.UUID;
import android.os.Bundle;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

final class LikeActionController$4 extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        if (LikeActionController.isPendingBroadcastReset) {
            return;
        }
        final String action = intent.getAction();
        final boolean b = Utility.areObjectsEqual("com.facebook.sdk.ACTIVE_SESSION_UNSET", action) || Utility.areObjectsEqual("com.facebook.sdk.ACTIVE_SESSION_CLOSED", action);
        LikeActionController.isPendingBroadcastReset = true;
        LikeActionController.handler.postDelayed((Runnable)new LikeActionController$4$1(this, b, context), 100L);
    }
}
