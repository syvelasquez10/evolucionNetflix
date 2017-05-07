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
    private final Callback callback;
    
    public DialogMessageReceiver(final Callback callback) {
        this.callback = callback;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable("mdxui", 2)) {
            Log.v("mdxui", "Received intent " + intent);
        }
        final String action = intent.getAction();
        if ("ui_rating".equals(action)) {
            this.callback.handleUserRatingChange(intent.getExtras().getString("videoId"), intent.getExtras().getFloat("rating"));
        }
        else {
            if ("nflx_button_selected".equals(action)) {
                this.callback.handleDialogButton(intent.getExtras().getString("nflx_dialog_id"), intent.getExtras().getString("nflx_action_selected"));
                return;
            }
            if ("nflx_button_canceled".equals(action)) {
                this.callback.handleDialogCancel(intent.getExtras().getString("nflx_dialog_id"));
                return;
            }
            if (Log.isLoggable("mdxui", 3)) {
                Log.d("mdxui", "We do not support action " + action);
            }
        }
    }
    
    public interface Callback
    {
        void handleDialogButton(final String p0, final String p1);
        
        void handleDialogCancel(final String p0);
        
        void handleUserRatingChange(final String p0, final float p1);
    }
}
