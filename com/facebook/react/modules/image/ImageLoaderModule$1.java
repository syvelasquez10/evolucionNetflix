// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.image;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.datasource.DataSource;
import com.facebook.react.bridge.Promise;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;

class ImageLoaderModule$1 extends BaseDataSubscriber<CloseableReference<CloseableImage>>
{
    final /* synthetic */ ImageLoaderModule this$0;
    final /* synthetic */ Promise val$promise;
    
    ImageLoaderModule$1(final ImageLoaderModule this$0, final Promise val$promise) {
        this.this$0 = this$0;
        this.val$promise = val$promise;
    }
    
    @Override
    protected void onFailureImpl(final DataSource<CloseableReference<CloseableImage>> dataSource) {
        this.val$promise.reject("E_GET_SIZE_FAILURE", dataSource.getFailureCause());
    }
    
    @Override
    protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> closeableReference) {
        if (!((DataSource)closeableReference).isFinished()) {
            return;
        }
        closeableReference = ((DataSource<CloseableReference<CloseableImage>>)closeableReference).getResult();
        if (closeableReference != null) {
            try {
                final CloseableImage closeableImage = closeableReference.get();
                final WritableMap map = Arguments.createMap();
                map.putInt("width", closeableImage.getWidth());
                map.putInt("height", closeableImage.getHeight());
                this.val$promise.resolve(map);
                return;
            }
            catch (Exception ex) {
                this.val$promise.reject("E_GET_SIZE_FAILURE", ex);
                return;
            }
            finally {
                CloseableReference.closeSafely(closeableReference);
            }
        }
        this.val$promise.reject("E_GET_SIZE_FAILURE");
    }
}
