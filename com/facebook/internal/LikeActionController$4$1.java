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
import android.os.Bundle;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import android.content.Context;

class LikeActionController$4$1 implements Runnable
{
    final /* synthetic */ LikeActionController$4 this$0;
    final /* synthetic */ Context val$broadcastContext;
    final /* synthetic */ boolean val$shouldClearDisk;
    
    LikeActionController$4$1(final LikeActionController$4 this$0, final boolean val$shouldClearDisk, final Context val$broadcastContext) {
        this.this$0 = this$0;
        this.val$shouldClearDisk = val$shouldClearDisk;
        this.val$broadcastContext = val$broadcastContext;
    }
    
    @Override
    public void run() {
        if (this.val$shouldClearDisk) {
            LikeActionController.objectSuffix = (LikeActionController.objectSuffix + 1) % 1000;
            this.val$broadcastContext.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putInt("OBJECT_SUFFIX", LikeActionController.objectSuffix).apply();
            LikeActionController.cache.clear();
            LikeActionController.controllerDiskCache.clearCache();
        }
        broadcastAction(this.val$broadcastContext, null, "com.facebook.sdk.LikeActionController.DID_RESET");
        LikeActionController.isPendingBroadcastReset = false;
    }
}
