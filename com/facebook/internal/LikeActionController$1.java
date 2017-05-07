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
import com.facebook.Session;
import android.os.Bundle;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import android.content.Intent;
import java.util.UUID;

final class LikeActionController$1 implements LikeActionController$CreationCallback
{
    final /* synthetic */ UUID val$callId;
    final /* synthetic */ Intent val$data;
    final /* synthetic */ int val$requestCode;
    final /* synthetic */ int val$resultCode;
    
    LikeActionController$1(final int val$requestCode, final int val$resultCode, final Intent val$data, final UUID val$callId) {
        this.val$requestCode = val$requestCode;
        this.val$resultCode = val$resultCode;
        this.val$data = val$data;
        this.val$callId = val$callId;
    }
    
    @Override
    public void onComplete(final LikeActionController likeActionController) {
        likeActionController.onActivityResult(this.val$requestCode, this.val$resultCode, this.val$data, this.val$callId);
    }
}
