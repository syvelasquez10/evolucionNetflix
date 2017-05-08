// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import android.content.Context;
import com.netflix.mediaclient.ui.voip.ContactUsActivity;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

final class ServiceErrorsHandler$4 implements DialogInterface$OnClickListener
{
    final /* synthetic */ Activity val$activity;
    
    ServiceErrorsHandler$4(final Activity val$activity) {
        this.val$activity = val$activity;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final Intent startIntent = ContactUsActivity.createStartIntent((Context)this.val$activity);
        final IClientLogging$ModalView uiScreen = ((NetflixActivity)this.val$activity).getUiScreen();
        if (uiScreen != null) {
            startIntent.putExtra("source", uiScreen.name());
        }
        startIntent.putExtra("entry", CustomerServiceLogging$EntryPoint.errorDialog.name());
        this.val$activity.startActivity(startIntent);
    }
}
