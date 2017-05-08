// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery;

import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.Coppola2Utils;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import android.view.View$OnClickListener;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.lomo.CwDiscoveryView;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.View;
import android.widget.LinearLayout;
import com.netflix.mediaclient.Log;
import android.view.ViewTreeObserver$OnPreDrawListener;

class DiscoveryPage$2 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ DiscoveryPage this$0;
    final /* synthetic */ int val$page;
    
    DiscoveryPage$2(final DiscoveryPage this$0, final int val$page) {
        this.this$0 = this$0;
        this.val$page = val$page;
    }
    
    public boolean onPreDraw() {
        if (Log.isLoggable()) {
            Log.i("DiscoveryPage", "CwDiscoveryView height (" + this.val$page + ") -> " + this.this$0.playableView.getLayoutParams().height);
        }
        this.this$0.playableView.getLayoutParams().height = (int)(this.this$0.playableView.getMeasuredWidth() * 0.5625f);
        this.this$0.playableView.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        return true;
    }
}
