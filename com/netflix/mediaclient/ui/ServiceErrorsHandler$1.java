// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

final class ServiceErrorsHandler$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Activity val$activity;
    
    ServiceErrorsHandler$1(final Activity val$activity) {
        this.val$activity = val$activity;
    }
    
    public void onClick(final DialogInterface dialogInterface, int intPref) {
        intPref = PreferenceUtils.getIntPref((Context)this.val$activity, "config_recommended_version", -1);
        if (Log.isLoggable("ServiceErrorsHandler", 4)) {
            Log.i("ServiceErrorsHandler", "User clicked cancel on prompt to update. Saving minRecommendedVersion = " + intPref);
        }
        PreferenceUtils.putIntPref((Context)this.val$activity, "nflx_update_skipped", intPref);
        this.val$activity.startActivity(RelaunchActivity.createStartIntent(this.val$activity, "ServiceErrorsHandler"));
        this.val$activity.finish();
    }
}
