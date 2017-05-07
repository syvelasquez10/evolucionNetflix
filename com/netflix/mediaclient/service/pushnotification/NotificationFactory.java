// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.Log;
import android.content.Intent;

public final class NotificationFactory
{
    public static final String DATA = "data";
    private static final String TAG = "nf_service";
    
    public static Intent createDummyGcmInfoIntent(final Payload.ActionInfoType actionInfoType) {
        final Intent intent = new Intent();
        intent.putExtra("profileId", "W6HQ56YURRBVHKDXMUT6FBHWRY");
        intent.putExtra("type", actionInfoType.getValue());
        intent.putExtra("when", "1408065095164");
        intent.putExtra("defaultActionKey", "INFO");
        Log.d("nf_service", String.format("Built gcmInfoEvent for type:%s, intent: %s", actionInfoType, intent));
        return intent;
    }
    
    public static void createDummyNotification(final NetflixService netflixService) {
    }
    
    public static void createNotification(final NetflixService netflixService, final Intent intent, final ImageLoader imageLoader, final int n, final ErrorLogging errorLogging) {
        if (intent == null) {
            Log.e("nf_service", "NotificationFactory.createNotification:: Intent is null!");
            return;
        }
        final Context applicationContext = netflixService.getApplicationContext();
        if (applicationContext == null) {
            Log.e("nf_service", "NotificationFactory.createNotification:: context is null!");
            return;
        }
        Payload payload = null;
        Label_0106: {
            try {
                payload = new Payload(intent);
                if (!"INFO".equals(payload.defaultActionKey)) {
                    break Label_0106;
                }
                if (netflixService.getCurrentProfile() == null) {
                    Log.d("nf_service", String.format("currentProfile null dropping gcm event payload:%s", payload));
                    return;
                }
            }
            catch (Throwable t) {
                Log.e("nf_service", "NotificationFactory.createNotification:: Failed to create payload object!", t);
                return;
            }
            InfoEventHandler.getInstance().handleEvent(netflixService, payload, intent);
            return;
        }
        final int androidVersion = AndroidUtils.getAndroidVersion();
        if (androidVersion >= 16) {
            NotificationBuilderJellyBean.createNotification(applicationContext, payload, imageLoader, n, errorLogging);
            return;
        }
        if (androidVersion >= 11) {
            NotificationBuilderHoneycomb.createNotification(applicationContext, payload, imageLoader, n, errorLogging);
            return;
        }
        throw new IllegalStateException("Invalid api level");
    }
}
