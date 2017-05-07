// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import java.util.Iterator;
import java.util.Map;
import android.view.View$OnClickListener;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import java.util.List;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewGroup$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.service.webclient.model.leafs.FriendProfile;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.servicemgr.interface_.FriendProfilesProvider;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.util.AttributeSet;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.view.View;
import java.util.EnumMap;
import android.widget.LinearLayout;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class VideoDetailsViewGroup$4 extends BroadcastReceiver
{
    final /* synthetic */ VideoDetailsViewGroup this$0;
    
    VideoDetailsViewGroup$4(final VideoDetailsViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            Log.v("VideoDetailsViewGroup", "Received null intent - ignoring");
        }
        else {
            final String stringExtra = intent.getStringExtra("extra_video_id");
            final float floatExtra = intent.getFloatExtra("extra_user_rating", -1.0f);
            if (StringUtils.safeEquals(this.this$0.videoId, stringExtra)) {
                this.this$0.ratingBar.setRating(floatExtra);
                if (Log.isLoggable()) {
                    Log.v("VideoDetailsViewGroup", "Updated video rating to: " + floatExtra + ", hash: " + this.this$0.hashCode());
                }
            }
        }
    }
}
