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
import android.widget.ImageView;
import com.netflix.mediaclient.Log;
import android.graphics.Bitmap;

class DiscoveryBackgroundAnimator$4$1 implements Runnable
{
    final /* synthetic */ DiscoveryBackgroundAnimator$4 this$1;
    final /* synthetic */ Bitmap val$delayedBlurredBmp;
    
    DiscoveryBackgroundAnimator$4$1(final DiscoveryBackgroundAnimator$4 this$1, final Bitmap val$delayedBlurredBmp) {
        this.this$1 = this$1;
        this.val$delayedBlurredBmp = val$delayedBlurredBmp;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.blurredImages.put(this.this$1.val$pageIndex, (Object)this.val$delayedBlurredBmp);
        this.this$1.val$view.setImageBitmap(this.val$delayedBlurredBmp);
        this.this$1.this$0.updateBackgrounds(this.this$1.this$0.currentPage);
        Log.i("DiscoveryBackgroundAnimator", "Blurring was completed for blur image: " + this.val$delayedBlurredBmp + " to view: " + this.this$1.val$view);
    }
}
