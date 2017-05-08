// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import com.netflix.mediaclient.service.webclient.model.leafs.VoipConfiguration;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.content.Context;
import com.netflix.mediaclient.util.log.CustomerServiceLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$Action;
import android.net.Uri;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ViewUtils$Visibility;
import android.view.View;

class LandingPageScreen
{
    private static final String TAG = "VoipActivity";
    private View mCancelMyAccount;
    private View mChangePassword;
    private View mFab;
    private ContactUsActivity mOwner;
    
    LandingPageScreen(final ContactUsActivity mOwner) {
        this.mOwner = mOwner;
        this.mFab = mOwner.findViewById(2131689740);
        this.mCancelMyAccount = mOwner.findViewById(2131689744);
        this.mChangePassword = mOwner.findViewById(2131689742);
        if (this.shouldHidePersonalizedLinks()) {
            ViewUtils.setVisibility(this.mCancelMyAccount, ViewUtils$Visibility.GONE);
            ViewUtils.setVisibility(this.mChangePassword, ViewUtils$Visibility.GONE);
        }
        if (this.shouldHideCallButton()) {
            ViewUtils.setVisibility(this.mFab, ViewUtils$Visibility.GONE);
        }
    }
    
    private void openUrl(final String s) {
        if (Log.isLoggable()) {
            Log.d("VoipActivity", "Opening external URL: " + s);
        }
        final Intent setData = new Intent("android.intent.action.VIEW").setData(Uri.parse(s));
        setData.addFlags(268435456);
        if (setData.resolveActivity(this.mOwner.getPackageManager()) != null) {
            this.mOwner.startActivity(setData);
            CustomerServiceLogUtils.reportHelpRequestSessionEnded((Context)this.mOwner, CustomerServiceLogging$Action.url, s, IClientLogging$CompletionReason.success, null);
            return;
        }
        Log.e("VoipActivity", "Unable to launchHelp");
    }
    
    private boolean shouldHideCallButton() {
        try {
            if (this.mOwner.getServiceManager() != null && this.mOwner.getServiceManager().getConfiguration() != null) {
                final VoipConfiguration voipConfiguration = this.mOwner.getServiceManager().getConfiguration().getVoipConfiguration();
                if (voipConfiguration == null) {
                    Log.d("VoipActivity", "VOIP config does not exist, by default it is enabled");
                    return false;
                }
                final boolean wiFiConnected = ConnectivityUtils.isWiFiConnected((Context)this.mOwner);
                if (ConnectivityUtils.isNetworkTypeCellular((Context)this.mOwner)) {
                    if (Log.isLoggable()) {
                        Log.d("VoipActivity", "On data, VOIP call is enabled " + voipConfiguration.isEnableVoipOverData());
                    }
                    if (voipConfiguration.isEnableVoipOverData()) {
                        return false;
                    }
                    return true;
                }
                else {
                    if (!wiFiConnected) {
                        Log.w("VoipActivity", "Not on data or WiFi? We most likely do not have network Hide by default");
                        return true;
                    }
                    Log.d("VoipActivity", "On WiFi, VOIP call is enabled " + voipConfiguration.isEnableVoipOverWiFi());
                    if (voipConfiguration.isEnableVoipOverWiFi()) {
                        return false;
                    }
                    return true;
                }
            }
        }
        catch (Throwable t) {
            Log.e("VoipActivity", "Something is very wrong, go with default .");
            return true;
        }
        Log.w("VoipActivity", "Service manager or configuration not found, hide VOIP");
        return true;
    }
    
    private boolean shouldHidePersonalizedLinks() {
        try {
            if (this.mOwner.getServiceManager() != null && this.mOwner.getServiceManager().isUserLoggedIn()) {
                Log.d("VoipActivity", "User is logged in, leave links...");
                return false;
            }
            Log.w("VoipActivity", "User is NOT logged in, remove personal links...");
            return true;
        }
        catch (Throwable t) {
            Log.e("VoipActivity", "User is NOT logged in, remove personal links...");
            return true;
        }
    }
    
    public View getFab() {
        return this.mFab;
    }
    
    public boolean performAction(final View view) {
        if (view == null) {
            Log.e("VoipActivity", "launchBrowser:: null view? This should never happen!");
            return true;
        }
        switch (view.getId()) {
            default: {
                if (Log.isLoggable()) {
                    Log.w("VoipActivity", "Uknown view, unable to handle: " + view.getId());
                }
                return false;
            }
            case 2131689741: {
                this.openUrl(this.mOwner.getString(2131231539));
                return true;
            }
            case 2131689742: {
                this.openUrl(this.mOwner.getString(2131231540));
                return true;
            }
            case 2131689744: {
                this.openUrl(this.mOwner.getString(2131231537));
                return true;
            }
            case 2131689743: {
                this.openUrl(this.mOwner.getString(2131231538));
                return true;
            }
            case 2131689740: {
                this.mOwner.startDial();
                return true;
            }
            case 2131689691: {
                Log.d("VoipActivity", "Perform up action");
                this.mOwner.performUpAction();
                return true;
            }
        }
    }
    
    public void update() {
        if (this.shouldHideCallButton()) {
            ViewUtils.setVisibility(this.mFab, ViewUtils$Visibility.GONE);
            return;
        }
        ViewUtils.setVisibility(this.mFab, ViewUtils$Visibility.VISIBLE);
    }
}
