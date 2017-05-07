// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import android.support.v7.media.MediaRouter$ProviderInfo;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.cast.CastDevice;
import java.util.HashMap;
import org.json.JSONArray;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouteSelector;
import com.netflix.mediaclient.service.mdx.MdxNrdpLogger;
import java.util.Map;
import android.os.Handler;
import android.content.Context;
import android.support.v7.media.MediaRouter$Callback;
import android.support.v7.media.MediaRouter$RouteInfo;
import com.netflix.mediaclient.Log;

class CastManager$4 implements Runnable
{
    final /* synthetic */ CastManager this$0;
    final /* synthetic */ String val$id;
    
    CastManager$4(final CastManager this$0, final String val$id) {
        this.this$0 = this$0;
        this.val$id = val$id;
    }
    
    @Override
    public void run() {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "about to launchNetflix " + this.val$id);
        }
        this.this$0.mSelectedRoute = null;
        final MediaRouter$RouteInfo mediaRouter$RouteInfo = this.this$0.mMapOfRoutes.get(this.val$id);
        if (mediaRouter$RouteInfo != null) {
            this.this$0.mSelectedRoute = mediaRouter$RouteInfo;
            this.this$0.mTargetId = this.val$id;
        }
        if (this.this$0.mSelectedRoute == null) {
            Log.e(CastManager.TAG, "launchNetflix failed, there is no route for" + this.val$id);
            return;
        }
        this.this$0.mForceLaunch = true;
        if (!this.this$0.mMediaRouter.getSelectedRoute().equals(this.this$0.mSelectedRoute)) {
            if (Log.isLoggable(CastManager.TAG, 3)) {
                Log.d(CastManager.TAG, "about to select the route before launch");
            }
            this.this$0.mMediaRouter.selectRoute(this.this$0.mSelectedRoute);
            return;
        }
        this.this$0.castLaunchApplication(this.this$0.mSelectedRoute);
    }
}
