// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Handler;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import android.content.Context;
import android.graphics.Bitmap;

public final class MdxImageLoader
{
    private static final String TAG = "nf_mdxImageLoader";
    private Bitmap mBitmap;
    private final MdxImageLoader$MdxImageLoaderInterface mCallback;
    private final Context mContext;
    private final ResourceFetcher mResourceFetcher;
    private final Handler mWorkerHandler;
    
    public MdxImageLoader(final Context mContext, final ResourceFetcher mResourceFetcher, final MdxImageLoader$MdxImageLoaderInterface mCallback, final Handler mWorkerHandler) {
        this.mContext = mContext;
        this.mResourceFetcher = mResourceFetcher;
        this.mCallback = mCallback;
        this.mWorkerHandler = mWorkerHandler;
    }
    
    private void fetchImageWithResourceFetcher(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.e("nf_mdxImageLoader", "Res fetcher url empty");
            return;
        }
        if (this.mResourceFetcher == null) {
            Log.e("nf_mdxImageLoader", "ResourceFetcher is null");
            return;
        }
        this.mResourceFetcher.fetchResource(s, IClientLogging$AssetType.boxArt, new MdxImageLoader$2(this));
    }
    
    public void fetchImage(final String s) {
        this.fetchImageWithLoader(s);
    }
    
    public void fetchImageWithLoader(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.e("nf_mdxImageLoader", "Loader url empty");
            return;
        }
        final ImageLoader imageLoader = this.mResourceFetcher.getImageLoader(this.mContext);
        if (imageLoader != null) {
            ((com.netflix.mediaclient.util.gfx.ImageLoader)imageLoader).getImg(s, IClientLogging$AssetType.boxArt, 0, 0, (ImageLoader$ImageLoaderListener)new MdxImageLoader$1(this));
            return;
        }
        Log.e("nf_mdxImageLoader", "ImageLoader is null!");
    }
}
