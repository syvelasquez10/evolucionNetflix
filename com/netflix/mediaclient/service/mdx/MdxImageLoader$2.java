// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.os.Handler;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import android.content.Context;
import android.graphics.Bitmap;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;

class MdxImageLoader$2 implements ResourceFetcherCallback
{
    final /* synthetic */ MdxImageLoader this$0;
    
    MdxImageLoader$2(final MdxImageLoader this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        if (status.isSucces()) {
            if (Log.isLoggable()) {
                Log.d("nf_mdxImageLoader", "resource fetched to " + s2);
            }
            if (this.this$0.mWorkerHandler != null && StringUtils.isNotEmpty(s2)) {
                this.this$0.mWorkerHandler.post((Runnable)new MdxImageLoader$2$1(this, s2));
            }
        }
    }
    
    @Override
    public void onResourcePrefetched(final String s, final int n, final Status status) {
        if (Log.isLoggable()) {
            Log.d("nf_mdxImageLoader", "ERROR resource prefetched from " + s);
        }
    }
}
