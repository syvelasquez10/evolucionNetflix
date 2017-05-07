// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import android.support.v7.media.MediaRouter$ProviderInfo;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.google.android.gms.cast.CastDevice;
import java.util.HashMap;
import org.json.JSONArray;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouteSelector;
import com.netflix.mediaclient.service.mdx.MdxNrdpLogger;
import android.support.v7.media.MediaRouter$RouteInfo;
import java.util.Map;
import android.os.Handler;
import android.content.Context;
import android.support.v7.media.MediaRouter$Callback;

class CastManager$5 implements Runnable
{
    final /* synthetic */ CastManager this$0;
    final /* synthetic */ String val$friendlyName;
    final /* synthetic */ String val$location;
    final /* synthetic */ String val$uuid;
    
    CastManager$5(final CastManager this$0, final String val$uuid, final String val$location, final String val$friendlyName) {
        this.this$0 = this$0;
        this.val$uuid = val$uuid;
        this.val$location = val$location;
        this.val$friendlyName = val$friendlyName;
    }
    
    @Override
    public void run() {
        this.this$0.nativeDeviceFound(this.val$uuid, this.val$location, this.val$friendlyName);
    }
}
