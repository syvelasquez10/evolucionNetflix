// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.launch.RelaunchActivity;
import android.content.DialogInterface;
import com.netflix.mediaclient.repository.UserLocale;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

final class ServiceErrorsHandler$4 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ UserLocale val$userLocale;
    
    ServiceErrorsHandler$4(final Activity val$activity, final UserLocale val$userLocale) {
        this.val$activity = val$activity;
        this.val$userLocale = val$userLocale;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final ServiceManager serviceManager = ((NetflixActivity)this.val$activity).getServiceManager();
        if (serviceManager != null) {
            serviceManager.getConfiguration().setAlertLocale(this.val$userLocale);
            this.val$activity.startActivity(RelaunchActivity.createStartIntent(this.val$activity, "ServiceErrorsHandler"));
            this.val$activity.finish();
        }
    }
}
