// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko;

import com.facebook.device.yearclass.YearClass;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.view.TextureView;
import android.content.Context;
import java.io.IOException;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.util.ThreadUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.Map;
import android.graphics.BitmapFactory$Options;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import java.util.HashSet;
import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class BaseInteractiveMomentsManager$1 extends SimpleManagerCallback
{
    final /* synthetic */ BaseInteractiveMomentsManager this$0;
    
    BaseInteractiveMomentsManager$1(final BaseInteractiveMomentsManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onResourceCached(final String s, final String s2, final long n, final long n2, final Status status) {
        super.onResourceCached(s, s2, n, n2, status);
        this.this$0.resourceResponseCounter++;
        this.this$0.assetsResponseSet.add(s);
        if (status.isError()) {
            if (Log.isLoggable()) {
                Log.d("BaseInteractiveMomentsManager", "Failed to retrieve resource: " + s + " Status = " + status.getStatusCode() + ": " + status.getMessage());
            }
            this.this$0.failureCount++;
        }
        else {
            if (Log.isLoggable()) {
                Log.d("BaseInteractiveMomentsManager", "Downloaded resource - : " + s);
            }
            this.this$0.resourceToLocalCacheFileMap.put(s, new LocalCachedFileMetadata(s2, n, n2));
        }
        if (this.this$0.assetsResponseSet.size() >= this.this$0.assetsRequestSet.size()) {
            this.this$0.cachingResourcesComplete();
        }
    }
}
