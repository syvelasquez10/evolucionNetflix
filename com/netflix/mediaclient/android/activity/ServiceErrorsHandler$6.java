// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.ui.launch.RelaunchActivity;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserLocaleRepository;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

final class ServiceErrorsHandler$6 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Activity val$activity;
    
    ServiceErrorsHandler$6(final Activity val$activity) {
        this.val$activity = val$activity;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (((NetflixActivity)this.val$activity).getServiceManager() != null) {
            UserLocaleRepository.setAlertedLanguage((Context)this.val$activity);
            this.val$activity.startActivity(RelaunchActivity.createStartIntent(this.val$activity, "ServiceErrorsHandler"));
            this.val$activity.finish();
        }
    }
}
