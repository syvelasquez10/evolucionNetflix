// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.model.HdEnabledProvider;
import com.netflix.mediaclient.servicemgr.model.Ratable;
import com.netflix.mediaclient.util.StringUtils;
import android.view.View$OnClickListener;
import java.util.List;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewGroup$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.FriendProfilesProvider;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.LayoutInflater;
import android.graphics.Rect;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;
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
        if (this.this$0.hdDrawable == null) {
            this.this$0.hdDrawable = this.this$0.buildHdDrawable();
        }
        this.this$0.setHdIcon();
    }
}
