// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.StringUtils;
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
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.app.DialogFragment;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class MdxMiniPlayerViews$8 implements View$OnClickListener
{
    final /* synthetic */ MdxMiniPlayerViews this$0;
    
    MdxMiniPlayerViews$8(final MdxMiniPlayerViews this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.activity.destroyed()) {
            return;
        }
        final VideoDetails currentVideo = this.this$0.callbacks.getCurrentVideo();
        if (currentVideo == null) {
            Log.w("MdxMiniPlayerViews", "currentVideo is null - can't show episodes");
            return;
        }
        if (!(currentVideo instanceof EpisodeDetails)) {
            Log.w("MdxMiniPlayerViews", "currentVideo is not an episode detail");
            return;
        }
        Log.v("MdxMiniPlayerViews", "Showing episodes dialog");
        final NetflixDialogFrag episodes = EpisodesFrag.createEpisodes(currentVideo.getPlayable().getParentId(), null, false);
        episodes.onManagerReady(this.this$0.callbacks.getManager(), CommonStatus.OK);
        episodes.setCancelable(true);
        this.this$0.activity.showDialog(episodes);
    }
}
