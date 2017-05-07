// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.app.DialogFragment;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;

class DialogManager implements OptInResponseHandler
{
    private static final String TAG = "HomeActivityDialogManager";
    private HomeActivity mOwner;
    
    DialogManager(final HomeActivity mOwner) {
        this.mOwner = mOwner;
    }
    
    private boolean displayGooglePlayServicesDialogIfNeeded() {
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)this.mOwner);
        if (Log.isLoggable("HomeActivityDialogManager", 3)) {
            Log.d("HomeActivityDialogManager", "Google Play status: " + googlePlayServicesAvailable);
        }
        if (googlePlayServicesAvailable == 0) {
            Log.d("HomeActivityDialogManager", "Success!");
            return false;
        }
        if (googlePlayServicesAvailable != 0) {
            Log.d("HomeActivityDialogManager", "Device is not Google certified, skip");
            return false;
        }
        Log.d("HomeActivityDialogManager", "Device is Google certified, problem with Google Play Services");
        if (GooglePlayServicesUtil.isUserRecoverableError(googlePlayServicesAvailable)) {
            Log.d("HomeActivityDialogManager", "Error is recoverable, display dialog");
            GooglePlayServicesUtil.showErrorDialogFragment(googlePlayServicesAvailable, this.mOwner, 1001);
            return false;
        }
        Log.e("HomeActivityDialogManager", "Error is NOT recoverable, skip");
        return false;
    }
    
    private boolean displayOptInDialogIfNeeded() {
        boolean b = false;
        if (this.shouldDisplayOptInDialog()) {
            Log.d("HomeActivityDialogManager", "Displaying opt-in dialog");
            final SocialOptInDialogFrag instance = SocialOptInDialogFrag.newInstance();
            instance.setCancelable(false);
            this.mOwner.showDialog(instance);
            b = true;
        }
        return b;
    }
    
    private boolean shouldDisplayOptInDialog() {
        if (this.mOwner.getServiceManager().getPushNotification().wasNotificationOptInDisplayed()) {
            Log.d("HomeActivityDialogManager", "User was already prompted for opt-in to social");
            return false;
        }
        if (this.mOwner.isDialogFragmentVisible()) {
            Log.w("HomeActivityDialogManager", "Dialog fragment is already displayed. There should only be one visible at time. Do NOT display opt-in to social.");
            return false;
        }
        Log.d("HomeActivityDialogManager", "User was NOT prompted for opt-in to social and no dialogs are visible.");
        return true;
    }
    
    public void displayDialogsIfNeeded() {
        if (this.displayOptInDialogIfNeeded()) {
            Log.d("HomeActivityDialogManager", "OptIn dialog displayed");
            return;
        }
        Log.d("HomeActivityDialogManager", "OptIn Dialog does not need to be displayed.");
        this.displayGooglePlayServicesDialogIfNeeded();
    }
    
    @Override
    public void onAccept() {
        if (this.mOwner.destroyed()) {
            return;
        }
        Log.v("HomeActivityDialogManager", "Sending PUSH_OPTIN...");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTIN");
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        LocalBroadcastManager.getInstance((Context)this.mOwner).sendBroadcast(intent);
        Log.v("HomeActivityDialogManager", "Sending PUSH_OPTIN done.");
        this.displayGooglePlayServicesDialogIfNeeded();
    }
    
    @Override
    public void onDecline() {
        if (this.mOwner.destroyed()) {
            return;
        }
        Log.v("HomeActivityDialogManager", "Sending PUSH_OPTOUT...");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTOUT");
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        LocalBroadcastManager.getInstance((Context)this.mOwner).sendBroadcast(intent);
        Log.v("HomeActivityDialogManager", "Sending PUSH_OPTOUT done.");
        this.displayGooglePlayServicesDialogIfNeeded();
    }
}
