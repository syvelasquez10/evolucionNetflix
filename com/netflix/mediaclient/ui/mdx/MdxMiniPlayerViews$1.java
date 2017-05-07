// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
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
import com.netflix.mediaclient.android.widget.IconFontTextView;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import android.view.ViewGroup;
import java.util.List;
import android.widget.TextView;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.animation.Interpolator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.view.View;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.media.BifManager$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.util.TimeUtils;
import android.widget.SeekBar;
import com.netflix.mediaclient.android.widget.SnappableSeekBar$OnSnappableSeekBarChangeListener;

class MdxMiniPlayerViews$1 implements SnappableSeekBar$OnSnappableSeekBarChangeListener
{
    private long lastProgressChangeTime;
    final /* synthetic */ MdxMiniPlayerViews this$0;
    
    MdxMiniPlayerViews$1(final MdxMiniPlayerViews this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
        this.this$0.callbacks.onProgressChanged(seekBar, n, b);
        if (b && TimeUtils.convertNsToMs(System.nanoTime() - this.lastProgressChangeTime) >= -1L) {
            this.lastProgressChangeTime = System.nanoTime();
            if (ServiceManagerUtils.isMdxAgentAvailable(this.this$0.callbacks.getManager())) {
                BifManager$Utils.showBifInView(this.this$0.callbacks.getMdx().getBifFrame(n * 1000), this.this$0.bifImage);
            }
            this.this$0.updateTimeViews(n);
        }
    }
    
    @Override
    public void onStartTrackingTouch(final SeekBar seekBar) {
        Log.v("MdxMiniPlayerViews", "onStartTrackingTouch");
        this.this$0.callbacks.onStartTrackingTouch(seekBar);
        this.this$0.currentTime.setVisibility(4);
        this.this$0.fadeOutAndHide(this.this$0.artwork);
        this.this$0.fadeInAndShow(this.this$0.bifImage, this.this$0.bifSeekTime);
        this.this$0.updateViewsForSeekBarUsage(true);
    }
    
    @Override
    public void onStopTrackingTouch(final SeekBar seekBar, final boolean b) {
        Log.v("MdxMiniPlayerViews", "onStopTrackingTouch, isInSnapRegion: " + b);
        this.this$0.callbacks.onStopTrackingTouch(seekBar, b);
        this.lastProgressChangeTime = 0L;
        if (b) {
            this.this$0.updateTimeViews(seekBar.getProgress());
        }
        else {
            this.onProgressChanged(seekBar, seekBar.getProgress(), true);
        }
        this.this$0.showArtworkAndHideBif();
        this.this$0.updateViewsForSeekBarUsage(false);
        if (!b) {
            this.this$0.setControlsEnabled(false);
        }
    }
}
