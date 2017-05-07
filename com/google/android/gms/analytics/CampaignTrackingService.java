// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.io.FileOutputStream;
import java.io.IOException;
import android.content.Context;
import android.content.Intent;
import android.app.IntentService;

public class CampaignTrackingService extends IntentService
{
    public CampaignTrackingService() {
        super("CampaignIntentService");
    }
    
    public CampaignTrackingService(final String s) {
        super(s);
    }
    
    public void onHandleIntent(final Intent intent) {
        this.processIntent((Context)this, intent);
    }
    
    public void processIntent(final Context context, final Intent intent) {
        final String stringExtra = intent.getStringExtra("referrer");
        try {
            final FileOutputStream openFileOutput = context.openFileOutput("gaInstallData", 0);
            openFileOutput.write(stringExtra.getBytes());
            openFileOutput.close();
            aa.y("Stored campaign information.");
        }
        catch (IOException ex) {
            aa.w("Error storing install campaign.");
        }
    }
}
