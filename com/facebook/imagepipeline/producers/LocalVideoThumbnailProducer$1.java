// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.concurrent.Executor;
import android.graphics.Bitmap;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import android.media.ThumbnailUtils;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

class LocalVideoThumbnailProducer$1 extends StatefulProducerRunnable<CloseableReference<CloseableImage>>
{
    final /* synthetic */ LocalVideoThumbnailProducer this$0;
    final /* synthetic */ ImageRequest val$imageRequest;
    
    LocalVideoThumbnailProducer$1(final LocalVideoThumbnailProducer this$0, final Consumer consumer, final ProducerListener producerListener, final String s, final String s2, final ImageRequest val$imageRequest) {
        this.this$0 = this$0;
        this.val$imageRequest = val$imageRequest;
        super(consumer, producerListener, s, s2);
    }
    
    @Override
    protected void disposeResult(final CloseableReference<CloseableImage> closeableReference) {
        CloseableReference.closeSafely(closeableReference);
    }
    
    @Override
    protected Map<String, String> getExtraMapOnSuccess(final CloseableReference<CloseableImage> closeableReference) {
        return ImmutableMap.of("createdThumbnail", String.valueOf(closeableReference != null));
    }
    
    @Override
    protected CloseableReference<CloseableImage> getResult() {
        final Bitmap videoThumbnail = ThumbnailUtils.createVideoThumbnail(this.val$imageRequest.getSourceFile().getPath(), calculateKind(this.val$imageRequest));
        if (videoThumbnail == null) {
            return null;
        }
        return (CloseableReference<CloseableImage>)CloseableReference.of(new CloseableStaticBitmap(videoThumbnail, SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0));
    }
}
