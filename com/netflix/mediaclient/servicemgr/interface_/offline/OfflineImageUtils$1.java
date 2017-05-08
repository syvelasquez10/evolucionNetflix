// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import java.io.File;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.FileUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.graphics.Bitmap;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

final class OfflineImageUtils$1 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ String val$localFilePath;
    final /* synthetic */ NetflixService val$service;
    final /* synthetic */ String val$src;
    
    OfflineImageUtils$1(final NetflixService val$service, final String val$src, final String val$localFilePath) {
        this.val$service = val$service;
        this.val$src = val$src;
        this.val$localFilePath = val$localFilePath;
    }
    
    public void onErrorResponse(final String s) {
    }
    
    public void onResponse(final Bitmap bitmap, final String s) {
        if (bitmap != null) {
            copyResourceToFilesDir(this.val$service, this.val$src, this.val$localFilePath);
        }
    }
}
