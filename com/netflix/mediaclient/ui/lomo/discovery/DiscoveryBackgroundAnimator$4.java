// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery;

import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.Log;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import android.os.Handler;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.util.SparseArray;
import com.netflix.mediaclient.util.Coppola2Utils;
import android.widget.ImageView;
import android.graphics.Bitmap;

class DiscoveryBackgroundAnimator$4 implements Runnable
{
    final /* synthetic */ DiscoveryBackgroundAnimator this$0;
    final /* synthetic */ Bitmap val$originalBmp;
    final /* synthetic */ int val$pageIndex;
    final /* synthetic */ ImageView val$view;
    
    DiscoveryBackgroundAnimator$4(final DiscoveryBackgroundAnimator this$0, final Bitmap val$originalBmp, final int val$pageIndex, final ImageView val$view) {
        this.this$0 = this$0;
        this.val$originalBmp = val$originalBmp;
        this.val$pageIndex = val$pageIndex;
        this.val$view = val$view;
    }
    
    @Override
    public void run() {
        this.this$0.mainHandler.post((Runnable)new DiscoveryBackgroundAnimator$4$1(this, Coppola2Utils.getBlurredBitmap(this.this$0.context, this.val$originalBmp, 25)));
    }
}
