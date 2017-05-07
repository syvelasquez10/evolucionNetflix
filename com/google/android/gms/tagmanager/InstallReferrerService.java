// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.Intent;
import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingService;
import android.app.IntentService;

public final class InstallReferrerService extends IntentService
{
    CampaignTrackingService apl;
    Context apm;
    
    public InstallReferrerService() {
        super("InstallReferrerService");
    }
    
    public InstallReferrerService(final String s) {
        super(s);
    }
    
    private void a(final Context context, final Intent intent) {
        if (this.apl == null) {
            this.apl = new CampaignTrackingService();
        }
        this.apl.processIntent(context, intent);
    }
    
    protected void onHandleIntent(final Intent intent) {
        final String stringExtra = intent.getStringExtra("referrer");
        Context context;
        if (this.apm != null) {
            context = this.apm;
        }
        else {
            context = this.getApplicationContext();
        }
        ay.d(context, stringExtra);
        this.a(context, intent);
    }
}
