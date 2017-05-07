// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
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
import android.content.Context;
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
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.details.DetailsActivity$Action;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.view.View;
import android.view.View$OnClickListener;

class MdxMiniPlayerViews$4 implements View$OnClickListener
{
    final /* synthetic */ MdxMiniPlayerViews this$0;
    
    MdxMiniPlayerViews$4(final MdxMiniPlayerViews this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final VideoDetails currentVideo = this.this$0.callbacks.getCurrentVideo();
        if (currentVideo == null) {
            return;
        }
        if (this.this$0.activity instanceof DetailsActivity) {
            final DetailsActivity detailsActivity = (DetailsActivity)this.this$0.activity;
            if (StringUtils.safeEquals(currentVideo.getId(), detailsActivity.getVideoId()) || StringUtils.safeEquals(currentVideo.getPlayable().getParentId(), detailsActivity.getVideoId())) {
                Log.d("MdxMiniPlayerViews", "Current details are already being shown - not showing details activity");
                this.this$0.activity.notifyMdxShowDetailsRequest();
                return;
            }
        }
        if (currentVideo.getType() == VideoType.EPISODE) {
            Log.v("MdxMiniPlayerViews", "showing details activity from episode for: " + currentVideo);
            DetailsActivityLauncher.showEpisodeDetails(this.this$0.activity, currentVideo.getPlayable().getParentId(), currentVideo.getId(), PlayContext.EMPTY_CONTEXT, null, null);
            return;
        }
        Log.v("MdxMiniPlayerViews", "showing details activity for: " + currentVideo);
        DetailsActivityLauncher.show(this.this$0.activity, currentVideo, PlayContext.EMPTY_CONTEXT, "MdxMiniPlayer");
    }
}
