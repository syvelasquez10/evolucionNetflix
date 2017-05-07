// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.animation.TimeInterpolator;
import com.netflix.mediaclient.util.gfx.AnimationUtils$HideViewOnAnimatorEnd;
import java.util.Arrays;
import java.util.Iterator;
import android.animation.Animator$AnimatorListener;
import android.view.LayoutInflater;
import android.content.res.Resources;
import java.util.ArrayList;
import java.util.Collection;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.AndroidUtils;
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
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.common.RatingDialogFrag;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class MdxMiniPlayerViews$10 implements View$OnClickListener
{
    final /* synthetic */ MdxMiniPlayerViews this$0;
    
    MdxMiniPlayerViews$10(final MdxMiniPlayerViews this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.activity.destroyed()) {
            this.this$0.log("Activity destroyed, can't show rating");
            return;
        }
        final VideoDetails currentVideo = this.this$0.callbacks.getCurrentVideo();
        if (currentVideo == null) {
            Log.e("MdxMiniPlayerViews", "Video is NULL. This should NOT happen!");
            return;
        }
        final float currentRating = this.this$0.callbacks.getCurrentRating();
        if (Log.isLoggable("MdxMiniPlayerViews", 2)) {
            this.this$0.log("User set rating " + currentRating);
            this.this$0.log("User rating from VideoDetails " + currentVideo.getUserRating());
        }
        Log.d("MdxMiniPlayerViews", "Report rate action started");
        UserActionLogUtils.reportRateActionStarted((Context)this.this$0.activity, null, this.this$0.activity.getUiScreen());
        String s;
        if (StringUtils.isEmpty(s = currentVideo.getPlayable().getParentTitle())) {
            s = currentVideo.getTitle();
        }
        final RatingDialogFrag instance = RatingDialogFrag.newInstance(MdxUtils.getRating(currentVideo, currentRating), MdxUtils.getVideoId(currentVideo), s);
        instance.setCancelable(true);
        this.this$0.activity.showDialog(instance);
    }
}
