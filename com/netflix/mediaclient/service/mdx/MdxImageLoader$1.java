// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Handler;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import android.content.Context;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class MdxImageLoader$1 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ MdxImageLoader this$0;
    
    MdxImageLoader$1(final MdxImageLoader this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onErrorResponse(final String s) {
        Log.e("nf_mdxImageLoader", "failed to downlod " + s);
    }
    
    @Override
    public void onResponse(final Bitmap bitmap, final String s) {
        if (bitmap != null && !bitmap.isRecycled()) {
            this.this$0.mBitmap = bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
        }
        else {
            Log.e("nf_mdxImageLoader", "bitmap is not valid " + bitmap);
        }
        if (Log.isLoggable("nf_mdxImageLoader", 3)) {
            Log.d("nf_mdxImageLoader", "downloaded image from " + s);
        }
        this.this$0.mCallback.onBitmapReady(this.this$0.mBitmap);
    }
}
