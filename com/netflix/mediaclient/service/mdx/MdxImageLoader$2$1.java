// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Handler;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import android.content.Context;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

class MdxImageLoader$2$1 implements Runnable
{
    final /* synthetic */ MdxImageLoader$2 this$1;
    final /* synthetic */ String val$localFileUrl;
    
    MdxImageLoader$2$1(final MdxImageLoader$2 this$1, final String val$localFileUrl) {
        this.this$1 = this$1;
        this.val$localFileUrl = val$localFileUrl;
    }
    
    @Override
    public void run() {
        final String substring = this.val$localFileUrl.substring("file://".length());
        while (true) {
            try {
                final FileInputStream fileInputStream = new FileInputStream(substring);
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                this.this$1.this$0.mBitmap = BitmapFactory.decodeStream((InputStream)bufferedInputStream);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (this.this$1.this$0.mBitmap != null) {
                    if (Log.isLoggable("nf_mdxImageLoader", 3)) {
                        Log.d("nf_mdxImageLoader", "mBitmap decoded, " + this.this$1.this$0.mBitmap.getWidth() + " X " + this.this$1.this$0.mBitmap.getHeight());
                    }
                    this.this$1.this$0.mCallback.onBitmapReady(this.this$1.this$0.mBitmap);
                }
            }
            catch (Exception ex) {
                Log.e("decode bitmap failed", ex.toString());
                continue;
            }
            break;
        }
    }
}
