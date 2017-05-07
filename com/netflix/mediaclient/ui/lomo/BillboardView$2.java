// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.text.Html;
import com.netflix.mediaclient.util.api.Api17Util;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.util.AttributeSet;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.service.browse.BrowseAgent$BillboardActivityType;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View$OnClickListener;

class BillboardView$2 implements View$OnClickListener
{
    final /* synthetic */ BillboardView this$0;
    final /* synthetic */ ServiceManager val$serviceMan;
    final /* synthetic */ Billboard val$video;
    
    BillboardView$2(final BillboardView this$0, final ServiceManager val$serviceMan, final Billboard val$video) {
        this.this$0 = this$0;
        this.val$serviceMan = val$serviceMan;
        this.val$video = val$video;
    }
    
    public void onClick(final View view) {
        if (this.val$serviceMan != null && this.val$serviceMan.isReady()) {
            this.val$serviceMan.getBrowse().logBillboardActivity(this.val$video, BrowseAgent$BillboardActivityType.ACTION);
        }
        PlaybackLauncher.startPlaybackAfterPIN((NetflixActivity)this.this$0.getContext(), this.val$video, this.this$0.playContext);
    }
}
