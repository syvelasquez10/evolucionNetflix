// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.service.webclient.model.leafs.ThumbMessaging;
import com.netflix.mediaclient.ui.rating.Ratings;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.thumb_rating.ThumbRatingTutorialDialogFrag;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.ui.push_notify.SocialOptInDialogFrag;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.offline.OfflineTutorialDialogFrag;
import android.app.Activity;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.push_notify.SocialOptInDialogFrag$OptInResponseHandler;

class DialogManager implements SocialOptInDialogFrag$OptInResponseHandler
{
    private static final String TAG = "DialogManager";
    private final HomeActivity mOwner;
    
    DialogManager(final HomeActivity mOwner) {
        this.mOwner = mOwner;
    }
    
    private boolean canDialogBeDisplayedSafely() {
        if (this.mOwner.isInstanceStateSaved()) {
            Log.d("DialogManager", "Activity has saved instance state - can't display dialog");
            return false;
        }
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.mOwner)) {
            Log.d("DialogManager", "Activity is destroyed - can't display dialog");
            return false;
        }
        return true;
    }
    
    private boolean displayGooglePlayServicesDialogIfNeeded() {
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)this.mOwner);
        if (Log.isLoggable()) {
            Log.d("DialogManager", "Google Play status: " + googlePlayServicesAvailable);
        }
        if (googlePlayServicesAvailable == 0) {
            Log.d("DialogManager", "Success!");
        }
        else {
            if (googlePlayServicesAvailable != 0) {
                Log.d("DialogManager", "Device is not Google certified, skip");
                return false;
            }
            Log.d("DialogManager", "Device is Google certified, problem with Google Play Services");
            if (GooglePlayServicesUtil.isUserRecoverableError(googlePlayServicesAvailable)) {
                Log.d("DialogManager", "Error is recoverable, display dialog");
                if (!this.canDialogBeDisplayedSafely()) {
                    return false;
                }
                try {
                    GooglePlayServicesUtil.showErrorDialogFragment(googlePlayServicesAvailable, this.mOwner, 1001);
                    return false;
                }
                catch (Throwable t) {
                    Log.e("DialogManager", "Failed to display Google play services error dialog!", t);
                    return false;
                }
            }
            Log.e("DialogManager", "Error is NOT recoverable, skip");
            return false;
        }
        return false;
    }
    
    private boolean displayOfflineTutorialIfNeeded() {
        if (this.shouldDisplayOfflineTutorialDialog()) {
            this.mOwner.showDialog((DialogFragment)new OfflineTutorialDialogFrag());
            return true;
        }
        return false;
    }
    
    private boolean displayOptInDialogIfNeeded() {
        boolean b = false;
        if (this.shouldDisplayOptInDialog()) {
            Log.d("DialogManager", "Displaying opt-in dialog");
            final SocialOptInDialogFrag instance = SocialOptInDialogFrag.newInstance();
            ((NetflixDialogFrag)instance).setCancelable(false);
            if (this.canDialogBeDisplayedSafely() && this.mOwner.showDialog((DialogFragment)instance)) {
                ApmLogUtils.reportUiModalViewChanged((Context)this.mOwner, IClientLogging$ModalView.optInDialog);
            }
            b = true;
        }
        return b;
    }
    
    private boolean displayThumbRatingTutorialIfNeeded() {
        if (this.shouldDisplayThumbRatingTutorialDialog()) {
            this.mOwner.showDialog((DialogFragment)new ThumbRatingTutorialDialogFrag());
            return true;
        }
        return false;
    }
    
    private boolean shouldDisplayOfflineTutorialDialog() {
        return !this.mOwner.isDialogFragmentVisible() && this.canDialogBeDisplayedSafely() && this.mOwner.getTutorialHelper().shouldDisplayFullscreenTutorial(this.mOwner.getServiceManager());
    }
    
    private boolean shouldDisplayOptInDialog() {
        if (this.mOwner.getServiceManager().getPushNotification().wasNotificationOptInDisplayed()) {
            Log.d("DialogManager", "User was already prompted for opt-in to social");
            return false;
        }
        if (this.mOwner.isDialogFragmentVisible()) {
            Log.w("DialogManager", "Dialog fragment is already displayed. There should only be one visible at time. Do NOT display opt-in to social.");
            return false;
        }
        Log.d("DialogManager", "User was NOT prompted for opt-in to social and no dialogs are visible.");
        return true;
    }
    
    private boolean shouldDisplayThumbRatingTutorialDialog() {
        if (this.mOwner.isDialogFragmentVisible()) {
            Log.w("DialogManager", "Dialog fragment is already displayed, so don't display thumb rating tutorial.");
        }
        else if (NetflixActivity.isTutorialOn() && Ratings.isAndroidThumbActive()) {
            final ThumbMessaging thumbMessaging = this.mOwner.getServiceManager().getThumbMessaging();
            if (thumbMessaging != null && thumbMessaging.shouldShowOneTimeProfileThumbsMessage()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean displayDialogsIfNeeded() {
        if (this.displayOfflineTutorialIfNeeded()) {
            Log.d("DialogManager", "Offline tutorial dialog displayed");
            return true;
        }
        if (this.displayThumbRatingTutorialIfNeeded()) {
            Log.d("DialogManager", "ThumbRating dialog displayed");
            return true;
        }
        if (this.displayOptInDialogIfNeeded()) {
            Log.d("DialogManager", "OptIn dialog displayed");
            return true;
        }
        Log.d("DialogManager", "OptIn Dialog does not need to be displayed.");
        return this.displayGooglePlayServicesDialogIfNeeded();
    }
    
    public void onAccept() {
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.mOwner)) {
            return;
        }
        Log.v("DialogManager", "Sending PUSH_OPTIN...");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTIN");
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        LocalBroadcastManager.getInstance((Context)this.mOwner).sendBroadcast(intent);
        Log.v("DialogManager", "Sending PUSH_OPTIN done.");
        this.displayGooglePlayServicesDialogIfNeeded();
    }
    
    public void onDecline() {
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.mOwner)) {
            return;
        }
        Log.v("DialogManager", "Sending PUSH_OPTOUT...");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTOUT");
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        LocalBroadcastManager.getInstance((Context)this.mOwner).sendBroadcast(intent);
        Log.v("DialogManager", "Sending PUSH_OPTOUT done.");
        this.displayGooglePlayServicesDialogIfNeeded();
    }
}
