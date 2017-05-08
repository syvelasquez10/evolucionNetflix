// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.res.Configuration;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayContext;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import android.view.ViewGroup$MarginLayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ViewUtils$Visibility;
import android.app.Activity;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.Log;

class PostPlayForEpisodes$1 implements Runnable
{
    final /* synthetic */ PostPlayForEpisodes this$0;
    
    PostPlayForEpisodes$1(final PostPlayForEpisodes this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (!this.this$0.mInPostPlay || this.this$0.mNetflixActivity.isFinishing()) {
            Log.d("nf_postplay", "post play timer exit or activity is destroyed");
            return;
        }
        this.this$0.mTimer--;
        this.this$0.refreshTimerText();
        if (this.this$0.mTimer <= 0) {
            Log.d("nf_postplay", "Timer counter to 0, play next episode");
            this.this$0.onTimerEnd();
            return;
        }
        this.this$0.mNetflixActivity.getHandler().postDelayed((Runnable)this, 1000L);
    }
}
