// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import java.util.HashMap;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.content.res.Resources;
import android.view.ViewGroup$LayoutParams;
import com.netflix.model.leafs.originals.BillboardDateBadge;
import com.netflix.model.leafs.originals.BillboardAwardsHeadline;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import android.net.Uri;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import com.netflix.model.leafs.originals.BillboardBackground;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.model.leafs.originals.BillboardSummary;
import android.view.View$OnClickListener;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.model.leafs.originals.BillboardCTA;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.AttributeSet;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.content.Context;
import android.widget.FrameLayout;
import com.netflix.mediaclient.android.widget.TopCropImageView;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.TextureView;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.view.View;
import java.util.Map;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import android.widget.Button;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import android.widget.RelativeLayout;
import android.text.TextUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class BillboardView$5 extends SimpleManagerCallback
{
    final /* synthetic */ BillboardView this$0;
    
    BillboardView$5(final BillboardView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onResourceCached(final String s, final String s2, final long n, final long n2, final Status status) {
        super.onResourceCached(s, s2, n, n2, status);
        if (status.isError()) {
            if (Log.isLoggable()) {
                Log.e("BillboardView", "Failed to retrieve video: " + s);
            }
        }
        else if (this.this$0.mediaPlayerWrapper != null && !TextUtils.isEmpty((CharSequence)s2)) {
            if (Log.isLoggable()) {
                Log.v("BillboardView", "Downloaded video - localUrl: " + s2);
            }
            this.this$0.mediaPlayerWrapper.setDataSource(s2, n, n2);
            this.this$0.showMotionBB();
        }
    }
}
