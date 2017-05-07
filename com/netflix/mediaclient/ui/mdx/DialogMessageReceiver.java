// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class DialogMessageReceiver extends BroadcastReceiver
{
    private static final String TAG = "DialogMessageReceiver";
    private final DialogMessageReceiver$Callback callback;
    
    public DialogMessageReceiver(final DialogMessageReceiver$Callback callback) {
        this.callback = callback;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable("DialogMessageReceiver", 2)) {
            Log.v("DialogMessageReceiver", "Received intent " + intent);
        }
        final String action = intent.getAction();
        if ("ui_rating".equals(action)) {
            this.callback.handleUserRatingChange(intent.getExtras().getString("videoId"), intent.getExtras().getFloat("rating"));
        }
        else if (Log.isLoggable("DialogMessageReceiver", 3)) {
            Log.d("DialogMessageReceiver", "We do not support action " + action);
        }
    }
}
