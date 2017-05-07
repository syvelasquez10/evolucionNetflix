// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

import android.support.v7.media.MediaRouter$ProviderInfo;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.cast.CastDevice;
import org.json.JSONArray;
import android.support.v7.media.MediaRouteSelector;
import com.netflix.mediaclient.service.mdx.MdxNrdpLogger;
import android.os.Handler;
import android.support.v7.media.MediaRouter$RouteInfo;
import java.util.List;
import android.content.Context;
import android.support.v7.media.MediaRouter$Callback;
import android.widget.Toast;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import com.netflix.mediaclient.Log;
import com.google.android.gms.cast.CastMediaControlIntent;
import android.support.v7.media.MediaRouteSelector$Builder;
import android.support.v7.media.MediaRouter;
import java.util.ArrayList;

class CastManager$1 implements Runnable
{
    final /* synthetic */ CastManager this$0;
    
    CastManager$1(final CastManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        new ArrayList<String>().add("urn:x-cast:mdx-netflix-com:service:target:2");
        this.this$0.mMediaRouter = MediaRouter.getInstance(this.this$0.mContext);
        try {
            this.this$0.mMediaRouteSelector = new MediaRouteSelector$Builder().addControlCategory(CastMediaControlIntent.categoryForCast(this.this$0.mApplicationId)).build();
            this.this$0.startDiscovery();
        }
        catch (IllegalArgumentException ex) {
            Log.e(CastManager.TAG, "MediaRouteSelector: " + ex);
            SettingsConfiguration.setCastApplicationId(this.this$0.mContext, "==invalid ApplicationId==");
            Toast.makeText(this.this$0.mContext, (CharSequence)"Invalid ApplicationId, Enter New One", 1).show();
        }
    }
}
