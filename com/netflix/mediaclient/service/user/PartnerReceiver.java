// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class PartnerReceiver extends BroadcastReceiver
{
    private static final String EXTRA_LOGGEDIN = "loggedIn";
    private static final String TAG = "nf_receiver";
    private static final String USER_STATUS_REQUEST = "com.netflix.mediaclient.intent.action.USER_STATUS";
    private static final String USER_STATUS_RESPONSE = "com.netflix.mediaclient.intent.action.USER_STATUS_RESPONSE";
    
    static void broadcastUserStatus(final Context context, final boolean b) {
        Log.d("nf_receiver", "broadcastUserStatus");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.USER_STATUS_RESPONSE");
        intent.putExtra("loggedIn", b);
        context.sendBroadcast(intent);
    }
    
    private void handleUsertatus(final Context context, final Intent intent) {
        Log.d("nf_receiver", "Received user status request");
        final boolean booleanPref = PreferenceUtils.getBooleanPref(context, "nf_user_status_loggedin", false);
        if (Log.isLoggable()) {
            Log.d("nf_receiver", "User is logged in: " + booleanPref);
        }
        broadcastUserStatus(context, booleanPref);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable()) {
            Log.d("nf_receiver", "Received an action: " + intent.getAction());
        }
        if ("com.netflix.mediaclient.intent.action.USER_STATUS".equals(intent.getAction())) {
            this.handleUsertatus(context, intent);
            return;
        }
        Log.w("nf_receiver", "Received Unintented action : " + intent.getAction());
    }
}
