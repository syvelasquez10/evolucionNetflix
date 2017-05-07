// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.content.Intent;
import android.content.Context;

public final class NotificationFactory
{
    public static final String DATA = "data";
    private static final String TAG = "nf_service";
    
    public static void createNotification(final Context context, final Intent intent, final ImageLoader imageLoader, final int n, final ErrorLogging errorLogging) {
        if (intent == null) {
            Log.e("nf_service", "NotificationFactory.createNotification:: Intent is null!");
            return;
        }
        if (context == null) {
            Log.e("nf_service", "NotificationFactory.createNotification:: context is null!");
            return;
        }
        Payload payload;
        int androidVersion;
        try {
            payload = new Payload(intent);
            androidVersion = AndroidUtils.getAndroidVersion();
            if (androidVersion >= 16) {
                NotificationBuilderJellyBean.createNotification(context, payload, imageLoader, n, errorLogging);
                return;
            }
        }
        catch (Throwable t) {
            Log.e("nf_service", "NotificationFactory.createNotification:: Failed to create payload object!", t);
            return;
        }
        if (androidVersion >= 11) {
            NotificationBuilderHoneycomb.createNotification(context, payload, imageLoader, n, errorLogging);
            return;
        }
        throw new IllegalStateException("Invalid api level");
    }
}
