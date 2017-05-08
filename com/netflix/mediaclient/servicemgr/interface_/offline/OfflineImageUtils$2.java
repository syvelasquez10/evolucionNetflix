// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;

final class OfflineImageUtils$2 implements ResourceFetcherCallback
{
    final /* synthetic */ String val$localFilePath;
    
    OfflineImageUtils$2(final String val$localFilePath) {
        this.val$localFilePath = val$localFilePath;
    }
    
    @Override
    public void onResourceCached(final String s, final String s2, final long n, final long n2, final Status status) {
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        if (status.isSucces() && StringUtils.isNotEmpty(s2)) {
            if (Log.isLoggable()) {
                Log.i("offlineImageUtils", "moving localUrl=" + s2 + " localFilePath=" + this.val$localFilePath);
            }
            Log.i("offlineImageUtils", "move result=%b", FileUtils.moveFile(s2.replaceFirst("file://", ""), this.val$localFilePath));
        }
    }
    
    @Override
    public void onResourcePrefetched(final String s, final int n, final Status status) {
    }
    
    @Override
    public void onResourceRawFetched(final String s, final byte[] array, final Status status) {
    }
}
