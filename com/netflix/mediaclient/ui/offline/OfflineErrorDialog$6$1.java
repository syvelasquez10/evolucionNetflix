// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import android.net.Uri;
import android.content.Intent;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class OfflineErrorDialog$6$1 implements Runnable
{
    final /* synthetic */ OfflineErrorDialog$6 this$1;
    final /* synthetic */ NetflixActivity val$netflixActivity;
    
    OfflineErrorDialog$6$1(final OfflineErrorDialog$6 this$1, final NetflixActivity val$netflixActivity) {
        this.this$1 = this$1;
        this.val$netflixActivity = val$netflixActivity;
    }
    
    @Override
    public void run() {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.val$netflixActivity) && ConnectivityUtils.isConnected((Context)this.val$netflixActivity)) {
            this.val$netflixActivity.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://www.netflix.com/changeplan")));
        }
    }
}
