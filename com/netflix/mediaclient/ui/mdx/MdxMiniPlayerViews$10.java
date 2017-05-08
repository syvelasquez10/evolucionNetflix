// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.animation.TimeInterpolator;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Arrays;
import android.content.res.Resources;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import java.util.ArrayList;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.gfx.AnimationUtils$HideViewOnAnimatorEnd;
import java.util.Iterator;
import android.animation.Animator$AnimatorListener;
import java.util.Collection;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.android.widget.SnappableSeekBar;
import com.netflix.mediaclient.android.widget.SnappableSeekBar$OnSnappableSeekBarChangeListener;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import android.view.ViewGroup;
import java.util.List;
import android.widget.TextView;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.animation.Interpolator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import com.netflix.mediaclient.servicemgr.interface_.UserRating;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.common.RatingDialogFrag;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.falkor.Falkor$Utils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View;
import android.view.View$OnClickListener;

class MdxMiniPlayerViews$10 implements View$OnClickListener
{
    final /* synthetic */ MdxMiniPlayerViews this$0;
    
    MdxMiniPlayerViews$10(final MdxMiniPlayerViews this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.activity)) {
            this.this$0.log("Activity destroyed, can't show rating");
        }
        else {
            final VideoDetails currentVideo = this.this$0.callbacks.getCurrentVideo();
            if (currentVideo == null) {
                Log.e("MdxMiniPlayerViews", "Video is NULL. This should NOT happen!");
                return;
            }
            final UserRating userRating = Falkor$Utils.getUserRating("MdxMiniPlayerViews", this.this$0.callbacks.getManager(), currentVideo);
            float userRating2;
            if (userRating == null) {
                userRating2 = -1.0f;
            }
            else {
                userRating2 = userRating.getUserRating();
            }
            if (Log.isLoggable()) {
                this.this$0.log("Curr user rating: " + userRating2);
            }
            Log.d("MdxMiniPlayerViews", "Report rate action started");
            UserActionLogUtils.reportRateActionStarted((Context)this.this$0.activity, null, this.this$0.activity.getUiScreen());
            String s;
            if (StringUtils.isEmpty(s = currentVideo.getPlayable().getParentTitle())) {
                s = currentVideo.getTitle();
            }
            final String playableVideoId = MdxUtils.getPlayableVideoId(currentVideo);
            VideoType videoType;
            if (currentVideo.getType() == VideoType.EPISODE) {
                videoType = VideoType.SHOW;
            }
            else {
                videoType = currentVideo.getType();
            }
            final RatingDialogFrag create = RatingDialogFrag.create(playableVideoId, videoType, s, null, 2130903286, true);
            create.setCancelable(true);
            this.this$0.activity.showDialog(create);
            if (this.this$0.activity != null && this.this$0.activity.getServiceManager() != null) {
                create.onManagerReady(this.this$0.activity.getServiceManager(), CommonStatus.OK);
            }
        }
    }
}
