// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.model.branches.FalkorVideo;
import java.util.Iterator;
import java.util.Map;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.AttributeSet;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.NetflixTextButton;
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
            if (StringUtils.safeEquals(this.this$0.videoId, stringExtra) && this.this$0.ratingBar != null) {
                this.this$0.ratingBar.setRating(floatExtra);
                if (Log.isLoggable()) {
                    Log.v("VideoDetailsViewGroup", "Updated video rating to: " + floatExtra + ", hash: " + this.this$0.hashCode());
                }
            }
        }
    }
}
