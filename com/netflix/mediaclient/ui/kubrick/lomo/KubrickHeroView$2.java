// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.graphics.Bitmap$Config;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.content.res.Resources;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.RelativeLayout$LayoutParams;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.ViewUtils;
import android.util.AttributeSet;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.TopCropImageView;
import android.view.View;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class KubrickHeroView$2 extends BroadcastReceiver
{
    final /* synthetic */ KubrickHeroView this$0;
    
    KubrickHeroView$2(final KubrickHeroView this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            Log.v("KubrickHeroView", "Received null intent - ignoring");
        }
        else {
            final String stringExtra = intent.getStringExtra("extra_video_id");
            final float floatExtra = intent.getFloatExtra("extra_user_rating", -1.0f);
            if (StringUtils.safeEquals(this.this$0.videoId, stringExtra)) {
                this.this$0.rating.setRating(floatExtra);
                if (Log.isLoggable()) {
                    Log.v("KubrickHeroView", "Updated video rating to: " + floatExtra);
                }
            }
        }
    }
}
