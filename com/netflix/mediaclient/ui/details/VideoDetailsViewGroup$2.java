// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import java.util.Iterator;
import java.util.Map;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import java.util.EnumMap;
import android.widget.LinearLayout;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class VideoDetailsViewGroup$2 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ VideoDetailsViewGroup this$0;
    
    VideoDetailsViewGroup$2(final VideoDetailsViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        ViewUtils.removeGlobalLayoutListener((View)this.this$0, (ViewTreeObserver$OnGlobalLayoutListener)this);
        if (Log.isLoggable() && this.this$0.imgGroup != null) {
            Log.v("VideoDetailsViewGroup", "img group width: " + this.this$0.imgGroup.getWidth() + ", height: " + this.this$0.imgGroup.getHeight());
        }
        this.this$0.alignViews();
    }
}
