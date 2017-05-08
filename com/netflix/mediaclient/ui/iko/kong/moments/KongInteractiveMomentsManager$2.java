// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.moments;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;
import java.util.Comparator;
import java.util.Collections;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.mediaclient.util.AudioUtils;
import android.content.Context;
import android.animation.ObjectAnimator;
import android.view.View;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;
import java.io.IOException;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Map;
import android.media.SoundPool;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractiveMomentsModel$KongInteractiveMoment;
import java.util.List;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractiveMomentsModel;
import com.netflix.mediaclient.ui.iko.InteractiveMomentsManager;
import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class KongInteractiveMomentsManager$2 extends SimpleManagerCallback
{
    final /* synthetic */ KongInteractiveMomentsManager this$0;
    
    KongInteractiveMomentsManager$2(final KongInteractiveMomentsManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onResourceCached(final String s, final String s2, final long n, final long n2, final Status status) {
        super.onResourceCached(s, s2, n, n2, status);
        this.this$0.resourceResponseCounter++;
        if (status.isError()) {
            Log.e("KongInteractiveMomentsManager", "Failed to retrieve resource: " + s + " Status = " + status.getStatusCode() + ": " + status.getMessage());
            this.this$0.failureCount++;
        }
        else {
            if (Log.isLoggable()) {
                Log.d("KongInteractiveMomentsManager", "Downloaded resource - : " + s);
            }
            this.this$0.resourceToLocalCacheFileMap.put(s, new LocalCachedFileMetadata(s2, n, n2));
        }
        if (this.this$0.resourceResponseCounter >= this.this$0.resourceRequestCounter) {
            this.this$0.loadViewResources();
        }
    }
}
