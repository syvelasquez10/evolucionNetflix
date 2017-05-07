// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.NflxProtocolUtils;
import java.util.List;
import java.util.ArrayList;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.IntentUtils;
import android.net.Uri;
import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.google.android.gcm.GCMRegistrar;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.service.logging.UserData;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IPushNotification;
import com.netflix.mediaclient.service.ServiceAgent;
import java.util.Map;

class PushNotificationAgent$1 implements Runnable
{
    final /* synthetic */ PushNotificationAgent this$0;
    final /* synthetic */ Map val$settings;
    
    PushNotificationAgent$1(final PushNotificationAgent this$0, final Map val$settings) {
        this.this$0 = this$0;
        this.val$settings = val$settings;
    }
    
    @Override
    public void run() {
        NotificationUserSettings.saveSettings(this.this$0.getContext(), this.val$settings);
    }
}
