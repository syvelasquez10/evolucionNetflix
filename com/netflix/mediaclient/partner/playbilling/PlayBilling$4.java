// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.playbilling;

import com.netflix.mediaclient.android.app.BackgroundTask;
import android.app.PendingIntent;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import org.json.JSONException;
import java.util.Collection;
import org.json.JSONArray;
import android.os.Bundle;
import android.content.Intent;
import java.util.ArrayList;
import android.content.ServiceConnection;
import com.android.vending.billing.IInAppBillingService;
import android.os.Handler;
import android.content.Context;
import java.util.List;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class PlayBilling$4 implements Runnable
{
    final /* synthetic */ PlayBilling this$0;
    final /* synthetic */ String val$accountId;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ PlayBillingCallback val$callback;
    final /* synthetic */ String val$payload;
    final /* synthetic */ int val$requestCode;
    final /* synthetic */ String val$sku;
    final /* synthetic */ int val$trialPeriodInDays;
    
    PlayBilling$4(final PlayBilling this$0, final PlayBillingCallback val$callback, final NetflixActivity val$activity, final String val$sku, final String val$payload, final int val$trialPeriodInDays, final String val$accountId, final int val$requestCode) {
        this.this$0 = this$0;
        this.val$callback = val$callback;
        this.val$activity = val$activity;
        this.val$sku = val$sku;
        this.val$payload = val$payload;
        this.val$trialPeriodInDays = val$trialPeriodInDays;
        this.val$accountId = val$accountId;
        this.val$requestCode = val$requestCode;
    }
    
    @Override
    public void run() {
        this.this$0.mPurchaseListener = this.val$callback;
        final JSONObject access$000 = this.this$0.initiatePurchasePlayBilling(this.val$activity, this.val$sku, this.val$payload, this.val$trialPeriodInDays, this.val$accountId, this.val$requestCode);
        this.this$0.flagEndAsync();
        if (access$000 != null) {
            if (Log.isLoggable()) {
                Log.d("playBilling", String.format("error with purchase %s", access$000));
            }
            this.this$0.mPurchaseListener = null;
            this.this$0.sendToCallback(this.val$callback, access$000);
        }
    }
}
