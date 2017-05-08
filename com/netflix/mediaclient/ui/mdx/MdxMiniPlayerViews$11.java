// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
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
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.common.VolumeDialogFrag;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View;
import android.view.View$OnClickListener;

class MdxMiniPlayerViews$11 implements View$OnClickListener
{
    final /* synthetic */ MdxMiniPlayerViews this$0;
    
    MdxMiniPlayerViews$11(final MdxMiniPlayerViews this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.activity)) {
            this.this$0.log("Activity destroyed, can't show volume frag");
            return;
        }
        if (!this.this$0.callbacks.isRemotePlayerReady()) {
            Log.w("MdxMiniPlayerViews", "Remote player is not ready - can't get/set volume");
            return;
        }
        final VolumeDialogFrag instance = VolumeDialogFrag.newInstance();
        instance.setCancelable(true);
        this.this$0.activity.showDialog(instance);
    }
}
