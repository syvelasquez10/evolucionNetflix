// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging;
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
    private final MdxImageLoaderInterface mCallback;
    private final Context mContext;
    private final ResourceFetcher mResourceFetcher;
    private final Handler mWorkerHandler;
    
    public MdxImageLoader(final Context mContext, final ResourceFetcher mResourceFetcher, final MdxImageLoaderInterface mCallback, final Handler mWorkerHandler) {
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
        this.mResourceFetcher.fetchResource(s, IClientLogging.AssetType.boxArt, new ResourceFetcherCallback() {
            @Override
            public void onResourceFetched(final String s, final String s2, final Status status) {
                if (status.isSucces()) {
                    if (Log.isLoggable("nf_mdxImageLoader", 3)) {
                        Log.d("nf_mdxImageLoader", "resource fetched to " + s2);
                    }
                    if (MdxImageLoader.this.mWorkerHandler != null && StringUtils.isNotEmpty(s2)) {
                        MdxImageLoader.this.mWorkerHandler.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                final String substring = s2.substring("file://".length());
                                while (true) {
                                    try {
                                        final FileInputStream fileInputStream = new FileInputStream(substring);
                                        final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                                        MdxImageLoader.this.mBitmap = BitmapFactory.decodeStream((InputStream)bufferedInputStream);
                                        if (fileInputStream != null) {
                                            fileInputStream.close();
                                        }
                                        if (bufferedInputStream != null) {
                                            bufferedInputStream.close();
                                        }
                                        if (MdxImageLoader.this.mBitmap != null) {
                                            if (Log.isLoggable("nf_mdxImageLoader", 3)) {
                                                Log.d("nf_mdxImageLoader", "mBitmap decoded, " + MdxImageLoader.this.mBitmap.getWidth() + " X " + MdxImageLoader.this.mBitmap.getHeight());
                                            }
                                            MdxImageLoader.this.mCallback.onBitmapReady(MdxImageLoader.this.mBitmap);
                                        }
                                    }
                                    catch (Exception ex) {
                                        Log.e("decode bitmap failed", ex.toString());
                                        continue;
                                    }
                                    break;
                                }
                            }
                        });
                    }
                }
            }
            
            @Override
            public void onResourcePrefetched(final String s, final int n, final Status status) {
                if (Log.isLoggable("nf_mdxImageLoader", 3)) {
                    Log.d("nf_mdxImageLoader", "ERROR resource prefetched from " + s);
                }
            }
        });
    }
    
    public void fetchImage(final String s) {
        this.fetchImageWithLoader(s);
    }
    
    public void fetchImageWithLoader(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.e("nf_mdxImageLoader", "Loader url empty");
            return;
        }
        final com.netflix.mediaclient.service.resfetcher.volley.ImageLoader imageLoader = this.mResourceFetcher.getImageLoader(this.mContext);
        if (imageLoader != null) {
            imageLoader.getImg(s, IClientLogging.AssetType.boxArt, 0, 0, (ImageLoader.ImageLoaderListener)new ImageLoader.ImageLoaderListener() {
                @Override
                public void onErrorResponse(final String s) {
                    Log.e("nf_mdxImageLoader", "failed to downlod " + s);
                }
                
                @Override
                public void onResponse(final Bitmap bitmap, final String s) {
                    if (bitmap != null && !bitmap.isRecycled()) {
                        MdxImageLoader.this.mBitmap = bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
                    }
                    else {
                        Log.e("nf_mdxImageLoader", "bitmap is not valid " + bitmap);
                    }
                    if (Log.isLoggable("nf_mdxImageLoader", 3)) {
                        Log.d("nf_mdxImageLoader", "downloaded image from " + s);
                    }
                    MdxImageLoader.this.mCallback.onBitmapReady(MdxImageLoader.this.mBitmap);
                }
            });
            return;
        }
        Log.e("nf_mdxImageLoader", "ImageLoader is null!");
    }
    
    public interface MdxImageLoaderInterface
    {
        void onBitmapReady(final Bitmap p0);
    }
}
