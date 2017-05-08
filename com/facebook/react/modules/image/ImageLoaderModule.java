// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.image;

import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSubscriber;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import android.net.Uri;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.datasource.DataSource;
import android.util.SparseArray;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class ImageLoaderModule extends ReactContextBaseJavaModule implements LifecycleEventListener
{
    private static final String ERROR_GET_SIZE_FAILURE = "E_GET_SIZE_FAILURE";
    private static final String ERROR_INVALID_URI = "E_INVALID_URI";
    private static final String ERROR_PREFETCH_FAILURE = "E_PREFETCH_FAILURE";
    private final Object mCallerContext;
    private final Object mEnqueuedRequestMonitor;
    private final SparseArray<DataSource<Void>> mEnqueuedRequests;
    
    public ImageLoaderModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = (SparseArray<DataSource<Void>>)new SparseArray();
        this.mCallerContext = this;
    }
    
    public ImageLoaderModule(final ReactApplicationContext reactApplicationContext, final Object mCallerContext) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = (SparseArray<DataSource<Void>>)new SparseArray();
        this.mCallerContext = mCallerContext;
    }
    
    private void registerRequest(final int n, final DataSource<Void> dataSource) {
        synchronized (this.mEnqueuedRequestMonitor) {
            this.mEnqueuedRequests.put(n, (Object)dataSource);
        }
    }
    
    private DataSource<Void> removeRequest(final int n) {
        synchronized (this.mEnqueuedRequestMonitor) {
            final DataSource dataSource = (DataSource)this.mEnqueuedRequests.get(n);
            this.mEnqueuedRequests.remove(n);
            return (DataSource<Void>)dataSource;
        }
    }
    
    @ReactMethod
    public void abortRequest(final int n) {
        final DataSource<Void> removeRequest = this.removeRequest(n);
        if (removeRequest != null) {
            removeRequest.close();
        }
    }
    
    @Override
    public String getName() {
        return "ImageLoader";
    }
    
    @ReactMethod
    public void getSize(final String s, final Promise promise) {
        if (s == null || s.isEmpty()) {
            promise.reject("E_INVALID_URI", "Cannot get the size of an image for an empty URI");
            return;
        }
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(s)).build(), this.mCallerContext).subscribe(new ImageLoaderModule$1(this, promise), CallerThreadExecutor.getInstance());
    }
    
    public void onHostDestroy() {
        while (true) {
            while (true) {
                int n;
                synchronized (this.mEnqueuedRequestMonitor) {
                    final int size = this.mEnqueuedRequests.size();
                    n = 0;
                    if (n >= size) {
                        this.mEnqueuedRequests.clear();
                        return;
                    }
                    final DataSource dataSource = (DataSource)this.mEnqueuedRequests.valueAt(n);
                    if (dataSource != null) {
                        dataSource.close();
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    @Override
    public void onHostPause() {
    }
    
    @Override
    public void onHostResume() {
    }
    
    @ReactMethod
    public void prefetchImage(final String s, final int n, final Promise promise) {
        if (s == null || s.isEmpty()) {
            promise.reject("E_INVALID_URI", "Cannot prefetch an image for an empty URI");
            return;
        }
        final DataSource<Void> prefetchToDiskCache = Fresco.getImagePipeline().prefetchToDiskCache(ImageRequestBuilder.newBuilderWithSource(Uri.parse(s)).build(), this.mCallerContext);
        final ImageLoaderModule$2 imageLoaderModule$2 = new ImageLoaderModule$2(this, n, promise);
        this.registerRequest(n, prefetchToDiskCache);
        prefetchToDiskCache.subscribe(imageLoaderModule$2, CallerThreadExecutor.getInstance());
    }
    
    @ReactMethod
    public void queryCache(final ReadableArray readableArray, final Promise promise) {
        new ImageLoaderModule$3(this, this.getReactApplicationContext(), readableArray, promise).executeOnExecutor(GuardedAsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    }
}
