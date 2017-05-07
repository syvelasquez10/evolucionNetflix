// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.launch;

import android.os.Bundle;
import android.content.Context;
import android.content.ComponentName;
import android.net.Uri;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.protocol.netflixcom.NetflixComHandlerFactory;
import android.content.Intent;
import android.app.Activity;

public class NetflixComLaunchActivity extends Activity
{
    private static final String TAG = "NetflixComLaunchActivity";
    
    private void handleIntent(final Intent intent) {
        if (NetflixComHandlerFactory.canHandle(intent) && NetflixComHandlerFactory.finishMeAndLaunchBrowserIfNeeded(this, intent)) {
            Log.i("NetflixComLaunchActivity", "This deeplink should be handled by external browser - launching it");
            intent.setData((Uri)null);
            return;
        }
        intent.setComponent(new ComponentName((Context)this, (Class)LaunchActivity.class));
        this.startActivity(intent);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        Log.d("NetflixComLaunchActivity", "onCreate()");
        this.handleIntent(this.getIntent());
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        Log.d("NetflixComLaunchActivity", "onNewIntent()");
        this.handleIntent(intent);
    }
}
