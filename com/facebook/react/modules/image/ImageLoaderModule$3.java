// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.image;

import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.react.bridge.WritableMap;
import android.net.Uri;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.GuardedAsyncTask;

class ImageLoaderModule$3 extends GuardedAsyncTask<Void, Void>
{
    final /* synthetic */ ImageLoaderModule this$0;
    final /* synthetic */ Promise val$promise;
    final /* synthetic */ ReadableArray val$uris;
    
    ImageLoaderModule$3(final ImageLoaderModule this$0, final ReactContext reactContext, final ReadableArray val$uris, final Promise val$promise) {
        this.this$0 = this$0;
        this.val$uris = val$uris;
        this.val$promise = val$promise;
        super(reactContext);
    }
    
    @Override
    protected void doInBackgroundGuarded(final Void... array) {
        final WritableMap map = Arguments.createMap();
        final ImagePipeline imagePipeline = Fresco.getImagePipeline();
        for (int i = 0; i < this.val$uris.size(); ++i) {
            final String string = this.val$uris.getString(i);
            final Uri parse = Uri.parse(string);
            if (imagePipeline.isInBitmapMemoryCache(parse)) {
                map.putString(string, "memory");
            }
            else if (imagePipeline.isInDiskCacheSync(parse)) {
                map.putString(string, "disk");
            }
        }
        this.val$promise.resolve(map);
    }
}
