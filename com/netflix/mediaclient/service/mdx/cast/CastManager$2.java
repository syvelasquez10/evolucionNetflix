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
import com.google.android.gms.cast.CastDevice;
import java.util.HashMap;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouteSelector;
import com.netflix.mediaclient.service.mdx.MdxNrdpLogger;
import android.support.v7.media.MediaRouter$RouteInfo;
import java.util.Map;
import android.os.Handler;
import android.content.Context;
import android.support.v7.media.MediaRouter$Callback;
import com.netflix.mediaclient.Log;

class CastManager$2 implements Runnable
{
    final /* synthetic */ CastManager this$0;
    
    CastManager$2(final CastManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d(CastManager.TAG, "stop ApiClient");
        if (this.this$0.mSelectedMdxCastApp != null) {
            this.this$0.mSelectedMdxCastApp.stop();
            this.this$0.mSelectedMdxCastApp = null;
        }
        Log.d(CastManager.TAG, "stop ApiClient done");
    }
}
