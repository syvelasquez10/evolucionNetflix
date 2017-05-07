// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.pin.PinVerifier;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.content.BroadcastReceiver;

class NetflixActivity$8 extends BroadcastReceiver
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$8(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    private void logWithClassName(final String s) {
        if (Log.isLoggable("NetflixActivity", 3)) {
            Log.d("NetflixActivity", this.this$0.getClass().getSimpleName() + ": " + s);
        }
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            this.logWithClassName("Null intent");
            return;
        }
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_ACTIVE".equals(action)) {
            this.logWithClassName("User profile activated - restarting app");
            PinVerifier.getInstance().clearState();
            this.this$0.handleProfileActivated();
            return;
        }
        if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_ACCOUNT_DEACTIVE".equals(action)) {
            this.logWithClassName("Account deactivated - restarting app");
            this.this$0.handleAccountDeactivated();
            return;
        }
        if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_READY_TO_SELECT".equals(action)) {
            this.logWithClassName("Ready to select profile - calling children");
            this.this$0.handleProfileReadyToSelect();
            return;
        }
        if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_SELECTION_RESULT".equals(action)) {
            final int intExtra = intent.getIntExtra("com.netflix.mediaclient.intent.action.EXTRA_USER_PROFILE_SELECTION_RESULT_INT", StatusCode.OK.getValue());
            final String stringExtra = intent.getStringExtra("com.netflix.mediaclient.intent.action.EXTRA_USER_PROFILE_SELECTION_RESULT_STRING");
            this.logWithClassName("Profile selection status: " + intExtra);
            this.this$0.handleProfileSelectionResult(intExtra, stringExtra);
            return;
        }
        if ("com.netflix.mediaclient.intent.action.NOTIFY_PROFILES_LIST_UPDATED".equals(action)) {
            this.logWithClassName("Profiles list updated!");
            this.this$0.handleProfilesListUpdated();
            return;
        }
        if ("com.netflix.mediaclient.intent.action.NOTIFY_CURRENT_PROFILE_INVALID".equals(action)) {
            this.logWithClassName("current profile is invalid");
            this.this$0.handleInvalidCurrentProfile();
            return;
        }
        this.logWithClassName("No action taken for intent: " + action);
    }
}
