// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.playbilling;

import com.android.vending.billing.IInAppBillingService$Stub;
import com.netflix.mediaclient.Log;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

class PlayBilling$3 implements ServiceConnection
{
    final /* synthetic */ PlayBilling this$0;
    final /* synthetic */ PlayBilling$OnSetupFinishedListener val$listener;
    
    PlayBilling$3(final PlayBilling this$0, final PlayBilling$OnSetupFinishedListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        if (!this.this$0.mDisposed) {
            Log.d("playBilling", "Billing service connected.");
            this.this$0.mService = IInAppBillingService$Stub.asInterface(binder);
            final String packageName = this.this$0.mContext.getPackageName();
            try {
                Log.d("playBilling", "Checking for play billing 3 support.");
                if (this.this$0.mService.isBillingSupported(3, packageName, "inapp") != 0) {
                    this.this$0.mSubscriptionsSupported = false;
                    this.this$0.mSubscriptionUpdateSupported = false;
                    Log.d("playBilling", "Error checking for billing v3 support.");
                    if (this.val$listener != null) {
                        this.val$listener.onSetupFinished(false);
                    }
                    return;
                }
            }
            catch (Exception ex) {
                Log.e("playBilling", "Exception while setting up in-app billing.", ex);
                if (this.val$listener != null) {
                    this.val$listener.onSetupFinished(false);
                }
                return;
            }
            Log.d("playBilling", "play billing version 3 supported for " + packageName);
            if (this.this$0.mService.isBillingSupported(5, packageName, "subs") == 0) {
                Log.d("playBilling", "Subscription re-signup AVAILABLE.");
                this.this$0.mSubscriptionUpdateSupported = true;
            }
            else {
                Log.d("playBilling", "Subscription re-signup not available.");
                this.this$0.mSubscriptionUpdateSupported = false;
            }
            if (this.this$0.mSubscriptionUpdateSupported) {
                this.this$0.mSubscriptionsSupported = true;
            }
            else {
                final int billingSupported = this.this$0.mService.isBillingSupported(3, packageName, "subs");
                if (billingSupported == 0) {
                    Log.d("playBilling", "Subscriptions AVAILABLE.");
                    this.this$0.mSubscriptionsSupported = true;
                }
                else {
                    Log.d("playBilling", "Subscriptions NOT AVAILABLE. Response: " + billingSupported);
                    this.this$0.mSubscriptionsSupported = false;
                    this.this$0.mSubscriptionUpdateSupported = false;
                }
            }
            if (this.this$0.mService.isBillingSupported(6, packageName, "subs") != 0) {
                Log.e("playBilling", "api 6 not supported. Check if PlayStore version > 6.2");
                this.this$0.mSubscriptionsSupported = false;
                this.this$0.mSubscriptionUpdateSupported = false;
            }
            else {
                Log.d("playBilling", "api 6 supported... ");
            }
            this.this$0.mSetupDone = true;
            Log.d("playBilling", "Setup successful");
            if (this.val$listener != null) {
                this.val$listener.onSetupFinished(true);
            }
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        Log.d("playBilling", "Billing service disconnected.");
        this.this$0.mService = null;
    }
}
