// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import android.content.Context;
import com.netflix.mediaclient.ui.voip.ContactUsActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity;

final class StandardSlidingMenu$14 implements Runnable
{
    final /* synthetic */ NetflixActivity val$context;
    
    StandardSlidingMenu$14(final NetflixActivity val$context) {
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        final Intent startIntent = ContactUsActivity.createStartIntent((Context)this.val$context);
        final IClientLogging$ModalView uiScreen = this.val$context.getUiScreen();
        if (uiScreen != null) {
            startIntent.putExtra("source", uiScreen.name());
        }
        startIntent.putExtra("entry", CustomerServiceLogging$EntryPoint.appMenu.name());
        this.val$context.startActivity(startIntent);
    }
}
