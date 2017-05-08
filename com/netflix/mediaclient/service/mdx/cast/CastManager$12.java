// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

import android.widget.Toast;
import com.google.android.gms.cast.CastMediaControlIntent;
import android.support.v7.media.MediaRouteSelector$Builder;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import org.json.JSONArray;
import java.util.Iterator;
import android.support.v7.media.MediaRouter$ProviderInfo;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouteSelector;
import com.netflix.mediaclient.service.mdx.MdxNrdpLogger;
import android.support.v7.media.MediaRouter$RouteInfo;
import java.util.Map;
import android.os.Handler;
import android.content.Context;
import android.support.v7.media.MediaRouter$Callback;
import com.google.chromecast.background.Channel;
import com.netflix.mediaclient.Log;
import com.google.android.gms.cast.CastDevice;

class CastManager$12 implements Runnable
{
    final /* synthetic */ CastManager this$0;
    final /* synthetic */ CastDevice val$castDevice;
    final /* synthetic */ String val$msg;
    
    CastManager$12(final CastManager this$0, final CastDevice val$castDevice, final String val$msg) {
        this.this$0 = this$0;
        this.val$castDevice = val$castDevice;
        this.val$msg = val$msg;
    }
    
    @Override
    public void run() {
        try {
            Log.d(CastManager.TAG, "send pre-fetch message to device, id=%s, hostAddress=%s", this.val$castDevice.getDeviceId(), this.val$castDevice.getIpAddress().getHostAddress());
            Log.d(CastManager.TAG, "returns " + Channel.sendMessage(this.val$castDevice, this.val$msg));
        }
        catch (Throwable t) {
            Log.i(CastManager.TAG, "background channle throw exception %s", t);
        }
    }
}
