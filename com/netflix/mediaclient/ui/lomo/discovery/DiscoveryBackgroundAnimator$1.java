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
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.widget.ImageView;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;

class DiscoveryBackgroundAnimator$1 implements ErrorWrapper$Callback
{
    final /* synthetic */ DiscoveryBackgroundAnimator this$0;
    
    DiscoveryBackgroundAnimator$1(final DiscoveryBackgroundAnimator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onRetryRequested() {
        if (this.this$0.context instanceof ErrorWrapper$Callback) {
            ((ErrorWrapper$Callback)this.this$0.context).onRetryRequested();
            return;
        }
        Log.w("DiscoveryBackgroundAnimator", "SPY-8068 - DiscoveryBackgroundAnimator - conxect has not a valid type");
        ErrorLoggingManager.logHandledException("SPY-8068 - DiscoveryBackgroundAnimator - conxect has not a valid type");
    }
}
