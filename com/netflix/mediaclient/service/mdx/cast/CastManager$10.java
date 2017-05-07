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
import java.util.ArrayList;
import org.json.JSONArray;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouteSelector;
import com.netflix.mediaclient.service.mdx.MdxNrdpLogger;
import android.os.Handler;
import android.support.v7.media.MediaRouter$RouteInfo;
import java.util.List;
import android.content.Context;
import android.support.v7.media.MediaRouter$Callback;

class CastManager$10 implements Runnable
{
    final /* synthetic */ CastManager this$0;
    final /* synthetic */ String val$id;
    
    CastManager$10(final CastManager this$0, final String val$id) {
        this.this$0 = this$0;
        this.val$id = val$id;
    }
    
    @Override
    public void run() {
        this.this$0.nativeLaunchResult(false, this.val$id);
    }
}
