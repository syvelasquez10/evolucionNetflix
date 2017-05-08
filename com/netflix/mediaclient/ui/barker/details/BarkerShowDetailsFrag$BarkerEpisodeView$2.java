// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import com.netflix.mediaclient.util.ViewUtils;
import android.widget.TextView;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import com.netflix.mediaclient.ui.barker.BarkerUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.content.res.Resources;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.details.EpisodesFrag$EpisodeView;
import android.view.View;
import android.view.View$OnClickListener;

class BarkerShowDetailsFrag$BarkerEpisodeView$2 implements View$OnClickListener
{
    final /* synthetic */ BarkerShowDetailsFrag$BarkerEpisodeView this$1;
    
    BarkerShowDetailsFrag$BarkerEpisodeView$2(final BarkerShowDetailsFrag$BarkerEpisodeView this$1) {
        this.this$1 = this$1;
    }
    
    public void onClick(final View view) {
        this.this$1.expandSynopsis();
    }
}
