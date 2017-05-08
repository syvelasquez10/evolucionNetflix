// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.content.res.Configuration;
import com.netflix.mediaclient.servicemgr.Asset;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.service.net.LogMobileType;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.content.res.Resources;
import android.text.Html;
import com.netflix.mediaclient.util.MdxUtils;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.CoppolaUtils;
import com.netflix.mediaclient.util.ViewUtils;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;
import android.os.Build$VERSION;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.os.Handler;
import android.view.View;

class CoppolaLoadingDecorator$UpdateLoadingTextRunnable implements Runnable
{
    private final boolean bShow;
    private final int episodeNumber;
    private final boolean isNSRE;
    private final String seasonNumberLabel;
    final /* synthetic */ CoppolaLoadingDecorator this$0;
    private final String title;
    
    CoppolaLoadingDecorator$UpdateLoadingTextRunnable(final CoppolaLoadingDecorator this$0, final String title, final boolean bShow, final String seasonNumberLabel, final int episodeNumber, final boolean isNSRE) {
        this.this$0 = this$0;
        this.title = title;
        this.bShow = bShow;
        this.seasonNumberLabel = seasonNumberLabel;
        this.episodeNumber = episodeNumber;
        this.isNSRE = isNSRE;
    }
    
    @Override
    public void run() {
        this.this$0.updateLoadingText(this.title, this.bShow, this.seasonNumberLabel, this.episodeNumber, this.isNSRE);
    }
}
