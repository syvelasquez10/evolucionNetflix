// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient;

import android.content.Context;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService
{
    private static final String TAG = "nf_push_service";
    
    public GCMIntentService() {
        super(new String[] { "484286080282" });
    }
    
    private Intent createIntent(final String s) {
        final Intent intent = new Intent(s);
        intent.setClass((Context)this, (Class)NetflixService.class);
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        return intent;
    }
    
    @Override
    protected void onDeletedMessages(final Context context, final int n) {
        Log.d("nf_push_service", "Received deleted messages notification");
    }
    
    public void onError(final Context context, final String s) {
        Log.w("nf_push_service", "Received error: " + s);
    }
    
    @Override
    protected void onMessage(final Context context, final Intent intent) {
        Log.i("nf_push_service", "Received message");
        if (intent == null) {
            Log.e("nf_push_service", "Error, intent can not be null!");
            return;
        }
        Log.d("nf_push_service", intent);
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        intent.setClass((Context)this, (Class)NetflixService.class);
        intent.setAction("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONMESSAGE");
        intent.putExtra("isRunning", NetflixService.isInstanceCreated());
        Log.d("nf_push_service", "Sending command to NetflixService started...isNetflixServiceRunning: " + NetflixService.isInstanceCreated());
        this.startService(intent);
        Log.d("nf_push_service", "Sending command to NetflixService done.");
    }
    
    @Override
    protected boolean onRecoverableError(final Context context, final String s) {
        Log.w("nf_push_service", "Received recoverable error: " + s);
        return super.onRecoverableError(context, s);
    }
    
    @Override
    protected void onRegistered(final Context context, final String s) {
        synchronized (this) {
            if (Log.isLoggable("nf_push_service", 3)) {
                Log.d("nf_push_service", "onRegistered:: Device registered: regId = " + s);
            }
            final Intent intent = this.createIntent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONREGISTERED");
            intent.putExtra("reg_id", s);
            Log.d("nf_push_service", "Sending command to NetflixService started...");
            this.startService(intent);
            Log.d("nf_push_service", "Sending command to NetflixService done.");
        }
    }
    
    @Override
    protected void onUnregistered(final Context context, final String s) {
        synchronized (this) {
            Log.d("nf_push_service", "Device unregistered");
            final Intent intent = this.createIntent("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONUNREGISTERED");
            intent.putExtra("reg_id", s);
            Log.d("nf_push_service", "Sending command to NetflixService started...");
            this.startService(intent);
            Log.d("nf_push_service", "Sending command to NetflixService done.");
        }
    }
}
