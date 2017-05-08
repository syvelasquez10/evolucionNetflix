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
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactApplicationContext;
import android.util.SparseArray;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.datasource.DataSource;
import com.facebook.react.bridge.Promise;
import com.facebook.datasource.BaseDataSubscriber;

class ImageLoaderModule$2 extends BaseDataSubscriber<Void>
{
    final /* synthetic */ ImageLoaderModule this$0;
    final /* synthetic */ Promise val$promise;
    final /* synthetic */ int val$requestId;
    
    ImageLoaderModule$2(final ImageLoaderModule this$0, final int val$requestId, final Promise val$promise) {
        this.this$0 = this$0;
        this.val$requestId = val$requestId;
        this.val$promise = val$promise;
    }
    
    @Override
    protected void onFailureImpl(final DataSource<Void> dataSource) {
        try {
            this.this$0.removeRequest(this.val$requestId);
            this.val$promise.reject("E_PREFETCH_FAILURE", dataSource.getFailureCause());
        }
        finally {
            dataSource.close();
        }
    }
    
    @Override
    protected void onNewResultImpl(final DataSource<Void> dataSource) {
        if (!dataSource.isFinished()) {
            return;
        }
        try {
            this.this$0.removeRequest(this.val$requestId);
            this.val$promise.resolve(true);
        }
        finally {
            dataSource.close();
        }
    }
}
