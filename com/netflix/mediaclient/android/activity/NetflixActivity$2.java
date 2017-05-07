// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import android.content.Intent;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$ReturnToDialScreenFrom;
import android.content.Context;
import com.netflix.mediaclient.ui.voip.ContactUsActivity;
import android.view.View;
import android.view.View$OnClickListener;

class NetflixActivity$2 implements View$OnClickListener
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$2(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final Intent startIntent = ContactUsActivity.createStartIntent((Context)this.this$0);
        if (this.this$0.getUiScreen() != null) {
            startIntent.putExtra("source", this.this$0.getUiScreen().name());
        }
        startIntent.putExtra("from", CustomerServiceLogging$ReturnToDialScreenFrom.fab.name());
        this.this$0.startActivity(startIntent);
    }
}
