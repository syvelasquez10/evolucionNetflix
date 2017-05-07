// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.os.Parcelable;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.content.res.Resources;
import android.view.ViewGroup$LayoutParams;
import com.netflix.model.leafs.originals.BillboardLogo;
import com.netflix.model.leafs.originals.BillboardSummary;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.net.Uri;
import android.text.TextUtils;
import com.netflix.model.leafs.originals.BillboardBackground;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.util.AttributeSet;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.content.Context;
import android.widget.FrameLayout;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.TextureView;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
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
            this.val$serviceMan.getBrowse().logBillboardActivity(this.val$video, BillboardInteractionType.ACTION);
        }
        PlaybackLauncher.startPlaybackAfterPIN((NetflixActivity)this.this$0.getContext(), this.val$video, this.this$0.playContext);
    }
}
