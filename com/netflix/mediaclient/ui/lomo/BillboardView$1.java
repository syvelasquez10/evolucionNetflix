// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.content.res.Resources;
import android.view.ViewGroup$LayoutParams;
import com.netflix.model.leafs.originals.BillboardDateBadge;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.model.leafs.originals.BillboardLogo;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import android.net.Uri;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.text.TextUtils;
import com.netflix.model.leafs.originals.BillboardBackground;
import com.netflix.model.leafs.originals.BillboardSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.model.leafs.originals.BillboardCTA;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.util.AttributeSet;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.content.Context;
import android.widget.FrameLayout;
import com.netflix.mediaclient.android.widget.TopCropImageView;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.TextureView;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import java.util.Map;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.Button;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import android.widget.RelativeLayout;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class BillboardView$1 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ BillboardView this$0;
    
    BillboardView$1(final BillboardView this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        if (!((NetflixActivity)this.this$0.getContext()).destroyed()) {
            if (Log.isLoggable()) {
                Log.v("BillboardView", "vg height: " + this.this$0.infoViewGroup.getHeight() + ", h: " + this.this$0.getHeight());
            }
            if (this.this$0.getHeight() > 0 && this.this$0.infoViewGroup.getHeight() >= this.this$0.getHeight()) {
                Log.d("BillboardView", "Info view group is larger than view height - hiding some text");
                this.this$0.info.setVisibility(8);
            }
            if (this.this$0.getHeight() > 0) {
                ViewUtils.removeGlobalLayoutListener((View)this.this$0, (ViewTreeObserver$OnGlobalLayoutListener)this);
            }
        }
    }
}
