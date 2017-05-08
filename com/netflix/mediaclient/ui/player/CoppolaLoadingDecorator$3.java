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
import com.netflix.mediaclient.service.net.LogMobileType;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.content.res.Resources;
import android.text.Html;
import com.netflix.mediaclient.util.MdxUtils;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewPropertyAnimator;
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
import android.view.View$OnClickListener;

class CoppolaLoadingDecorator$3 implements View$OnClickListener
{
    final /* synthetic */ CoppolaLoadingDecorator this$0;
    
    CoppolaLoadingDecorator$3(final CoppolaLoadingDecorator this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.launchPlayback();
    }
}
