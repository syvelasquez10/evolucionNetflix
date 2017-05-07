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
import java.util.List;
import android.content.Context;
import android.support.v7.media.MediaRouter$Callback;
import java.util.Iterator;
import android.support.v7.media.MediaRouter$RouteInfo;

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
        this.this$0.mSelectedRoute = null;
        for (final MediaRouter$RouteInfo mediaRouter$RouteInfo : this.this$0.mListOfRoutes) {
            if (this.val$id.equalsIgnoreCase(this.this$0.getUuid(mediaRouter$RouteInfo.getId()))) {
                this.this$0.mSelectedRoute = mediaRouter$RouteInfo;
                this.this$0.mTargetId = this.val$id;
            }
        }
        if (this.this$0.mSelectedRoute == null) {
            return;
        }
        this.this$0.mForceLaunch = true;
        if (!this.this$0.mMediaRouter.getSelectedRoute().equals(this.this$0.mSelectedRoute)) {
            this.this$0.mMediaRouter.selectRoute(this.this$0.mSelectedRoute);
            return;
        }
        this.this$0.castLaunchApplication(this.this$0.mSelectedRoute);
    }
}
