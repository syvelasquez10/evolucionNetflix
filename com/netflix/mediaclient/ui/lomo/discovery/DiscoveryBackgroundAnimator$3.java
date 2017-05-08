// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery;

import android.graphics.drawable.Drawable;
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
import android.widget.ImageView;
import android.graphics.Bitmap;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class DiscoveryBackgroundAnimator$3 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ DiscoveryBackgroundAnimator this$0;
    
    DiscoveryBackgroundAnimator$3(final DiscoveryBackgroundAnimator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onErrorResponse(final String s) {
    }
    
    @Override
    public void onResponse(final Bitmap bitmap, final String s) {
        if (bitmap == null) {
            return;
        }
        this.this$0.setBlurredImage(this.this$0.nextPage, bitmap, this.this$0.blurredTopImageView);
    }
}
