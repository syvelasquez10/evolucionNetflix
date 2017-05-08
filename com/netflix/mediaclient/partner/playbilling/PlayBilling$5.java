// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.playbilling;

import com.netflix.mediaclient.android.app.BackgroundTask;
import android.app.PendingIntent;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.Bundle;
import com.netflix.mediaclient.util.Base64;
import android.content.Intent;
import java.util.ArrayList;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.content.ServiceConnection;
import com.android.vending.billing.IInAppBillingService;
import android.os.Handler;
import android.content.Context;
import java.util.List;
import org.json.JSONObject;

class PlayBilling$5 implements Runnable
{
    final /* synthetic */ PlayBilling this$0;
    final /* synthetic */ PlayBillingCallback val$callback;
    final /* synthetic */ String val$continuationToken;
    
    PlayBilling$5(final PlayBilling this$0, final String val$continuationToken, final PlayBillingCallback val$callback) {
        this.this$0 = this$0;
        this.val$continuationToken = val$continuationToken;
        this.val$callback = val$callback;
    }
    
    @Override
    public void run() {
        final JSONObject access$200 = this.this$0.getPurchaseHistoryFromPlayBilling(this.val$continuationToken);
        this.this$0.flagEndAsync();
        this.this$0.sendToCallback(this.val$callback, access$200);
    }
}
