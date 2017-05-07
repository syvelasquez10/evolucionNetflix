// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import com.netflix.mediaclient.service.logging.client.model.Error;
import android.content.Context;
import com.netflix.mediaclient.util.log.CustomerServiceLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$Action;
import android.net.Uri;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.view.View;

class LandingPageScreen
{
    private static final String TAG = "VoipActivity";
    private View mFab;
    private ContactUsActivity mOwner;
    
    LandingPageScreen(final ContactUsActivity mOwner) {
        this.mOwner = mOwner;
        this.mFab = mOwner.findViewById(2131624136);
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
            case 2131624132: {
                this.openUrl(this.mOwner.getString(2131165789));
                return true;
            }
            case 2131624133: {
                this.openUrl(this.mOwner.getString(2131165790));
                return true;
            }
            case 2131624135: {
                this.openUrl(this.mOwner.getString(2131165787));
                return true;
            }
            case 2131624134: {
                this.openUrl(this.mOwner.getString(2131165788));
                return true;
            }
            case 2131624136: {
                this.mOwner.startDial();
                return true;
            }
            case 2131624128:
            case 2131624129: {
                Log.d("VoipActivity", "Perform up action");
                this.mOwner.performUpAction();
                return true;
            }
        }
    }
    
    public void update() {
    }
}
