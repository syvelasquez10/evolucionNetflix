// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import com.netflix.android.widgetry.widget.UserRatingButton$OnRateListener;
import android.support.design.widget.CoordinatorLayout;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import java.util.List;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import java.util.Iterator;
import java.util.Map;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.util.Coppola1Utils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.FrameLayout;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import android.text.TextUtils$TruncateAt;
import android.view.ViewGroup$MarginLayoutParams;
import android.support.v4.content.ContextCompat;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.rating.Ratings;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.util.AttributeSet;
import android.view.View$OnClickListener;
import android.view.ViewStub;
import com.netflix.android.widgetry.widget.ThumbsToMatchPercentageAnimator;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.android.widget.TopCropImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import java.util.EnumMap;
import android.widget.LinearLayout;
import com.netflix.android.widgetry.widget.UserRatingButton;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class VideoDetailsViewGroup$5 extends BroadcastReceiver
{
    final /* synthetic */ VideoDetailsViewGroup this$0;
    
    VideoDetailsViewGroup$5(final VideoDetailsViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            Log.v("VideoDetailsViewGroup", "Received null intent - ignoring");
        }
        else if (StringUtils.safeEquals(this.this$0.videoId, intent.getStringExtra("extra_video_id"))) {
            if (this.this$0.ratingBar != null) {
                final float floatExtra = intent.getFloatExtra("extra_user_rating", -1.0f);
                this.this$0.ratingBar.setRating(floatExtra);
                if (Log.isLoggable()) {
                    Log.v("VideoDetailsViewGroup", "Updated video rating to: " + floatExtra + ", hash: " + this.this$0.hashCode());
                }
            }
            if (this.this$0.userRatingButton != null) {
                final int thumbsRating = UserRatingButton.asThumbsRating(intent.getIntExtra("extra_user_thumb_rating", 0));
                this.this$0.userRatingButton.setRating(thumbsRating);
                this.this$0.updateMatchPercentage(thumbsRating, true);
            }
        }
    }
}
