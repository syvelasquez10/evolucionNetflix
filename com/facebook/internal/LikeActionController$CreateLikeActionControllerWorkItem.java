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

class LikeActionController$CreateLikeActionControllerWorkItem implements Runnable
{
    private LikeActionController$CreationCallback callback;
    private Context context;
    private String objectId;
    
    LikeActionController$CreateLikeActionControllerWorkItem(final Context context, final String objectId, final LikeActionController$CreationCallback callback) {
        this.context = context;
        this.objectId = objectId;
        this.callback = callback;
    }
    
    @Override
    public void run() {
        createControllerForObjectId(this.context, this.objectId, this.callback);
    }
}
