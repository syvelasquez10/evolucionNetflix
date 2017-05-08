// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.view.TextureView;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.io.IOException;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.util.ThreadUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;
import android.content.Context;
import com.facebook.device.yearclass.YearClass;
import java.util.Collections;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.ui.player.PostPlay;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.graphics.BitmapFactory$Options;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.view.View;
import android.os.Handler;
import android.animation.Animator;
import android.widget.ImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.iko.InteractivePostPlayManager;
import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class KongInteractivePostPlayManager$2 extends SimpleManagerCallback
{
    final /* synthetic */ KongInteractivePostPlayManager this$0;
    
    KongInteractivePostPlayManager$2(final KongInteractivePostPlayManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onResourceCached(final String s, final String s2, final long n, final long n2, final Status status) {
        super.onResourceCached(s, s2, n, n2, status);
        this.this$0.resourceResponseCounter++;
        if (status.isError()) {
            Log.e("KongInteractivePostPlayManager", "Failed to retrieve resource: " + s + " Status = " + status.getStatusCode() + ": " + status.getMessage());
            this.this$0.failureCount++;
        }
        else {
            if (Log.isLoggable()) {
                Log.d("KongInteractivePostPlayManager", "Downloaded resource - : " + this.this$0.resourceResponseCounter + ") - " + s);
            }
            this.this$0.resourceToLocalCacheFileMap.put(s, new LocalCachedFileMetadata(s2, n, n2));
        }
        if (this.this$0.resourceResponseCounter >= this.this$0.preCacheableResources.size()) {
            this.this$0.checkFailuresAndRetry();
            if (Log.isLoggable()) {
                Log.d("KongInteractivePostPlayManager", "Caching post play resources request complete! Failures - " + this.this$0.failureCount);
            }
        }
    }
}
