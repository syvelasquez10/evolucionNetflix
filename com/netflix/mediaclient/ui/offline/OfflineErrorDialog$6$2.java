// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.net.Uri;
import com.netflix.mediaclient.ui.home.AccountHandler;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class OfflineErrorDialog$6$2 extends SimpleManagerCallback
{
    final /* synthetic */ OfflineErrorDialog$6 this$1;
    final /* synthetic */ NetflixActivity val$netflixActivity;
    final /* synthetic */ Runnable val$tokenTimeOutRunnable;
    
    OfflineErrorDialog$6$2(final OfflineErrorDialog$6 this$1, final NetflixActivity val$netflixActivity, final Runnable val$tokenTimeOutRunnable) {
        this.this$1 = this$1;
        this.val$netflixActivity = val$netflixActivity;
        this.val$tokenTimeOutRunnable = val$tokenTimeOutRunnable;
    }
    
    @Override
    public void onAutoLoginTokenCreated(final String s, final Status status) {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.val$netflixActivity) && status.isSucces()) {
            this.val$netflixActivity.getHandler().removeCallbacks(this.val$tokenTimeOutRunnable);
            this.val$netflixActivity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(AccountHandler.createLink("https://www.netflix.com/changeplan", s))));
        }
    }
}
