// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
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
import com.netflix.mediaclient.Log;
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
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.util.AttributeSet;
import android.content.BroadcastReceiver;
import android.view.View$OnClickListener;
import android.view.ViewStub;
import com.netflix.android.widgetry.widget.ThumbsToMatchPercentageAnimator;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.android.widget.TopCropImageView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import java.util.EnumMap;
import android.widget.LinearLayout;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.thumb_rating.ThumbRatingFirstRatingThanksDialogFrag;
import com.netflix.mediaclient.ui.rating.Ratings$CL;
import com.netflix.android.widgetry.widget.UserRatingButton;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.android.widgetry.widget.UserRatingButton$OnRateListener;

class VideoDetailsViewGroup$4 implements UserRatingButton$OnRateListener
{
    private boolean mUserRated;
    private boolean mUsingDrag;
    final /* synthetic */ VideoDetailsViewGroup this$0;
    final /* synthetic */ VideoDetails val$details;
    final /* synthetic */ NetflixActivity val$netflixActivity;
    final /* synthetic */ NetflixRatingBar$RatingBarDataProvider val$provider;
    
    VideoDetailsViewGroup$4(final VideoDetailsViewGroup this$0, final NetflixActivity val$netflixActivity, final NetflixRatingBar$RatingBarDataProvider val$provider, final VideoDetails val$details) {
        this.this$0 = this$0;
        this.val$netflixActivity = val$netflixActivity;
        this.val$provider = val$provider;
        this.val$details = val$details;
        this.mUsingDrag = false;
        this.mUserRated = false;
    }
    
    @Override
    public void onAlphaAnimate(final float n) {
        if (this.this$0.videoActionsContainer != null) {
            this.this$0.videoActionsContainer.setAlpha(1.0f - n);
        }
    }
    
    @Override
    public void onDismissed(final UserRatingButton userRatingButton) {
        Ratings$CL.reportDialogDismissed(userRatingButton.getContext(), this.mUserRated, this.mUsingDrag);
        if (this.mUserRated && ThumbRatingFirstRatingThanksDialogFrag.shouldDisplayDialog(this.val$netflixActivity)) {
            final ThumbRatingFirstRatingThanksDialogFrag thumbRatingFirstRatingThanksDialogFrag = new ThumbRatingFirstRatingThanksDialogFrag();
            thumbRatingFirstRatingThanksDialogFrag.setUserName(this.val$netflixActivity.getServiceManager().getCurrentProfile().getProfileName());
            thumbRatingFirstRatingThanksDialogFrag.setThumbRating(this.val$details.getUserThumbRating());
            this.val$netflixActivity.showDialog((DialogFragment)thumbRatingFirstRatingThanksDialogFrag);
        }
        this.mUsingDrag = false;
        this.mUserRated = false;
    }
    
    @Override
    public void onOpened(final UserRatingButton userRatingButton, final boolean mUsingDrag) {
        this.mUsingDrag = mUsingDrag;
    }
    
    @Override
    public void onRate(final UserRatingButton userRatingButton, final int n) {
        UserActionLogUtils.reportRateActionStarted((Context)this.val$netflixActivity, (UserActionLogging$CommandName)null, this.val$netflixActivity.getUiScreen());
        int trackId;
        if (this.val$provider.getPlayContext() != null) {
            trackId = this.val$provider.getPlayContext().getTrackId();
        }
        else {
            trackId = -1;
        }
        this.val$netflixActivity.getServiceManager().getBrowse().setVideoRating(this.this$0.videoId, this.val$details.getType(), n, trackId, (ManagerCallback)new VideoDetailsViewGroup$4$1(this, "VideoDetailsViewGroup", this.val$details));
        this.mUserRated = true;
    }
}
