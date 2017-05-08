// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.io.File;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imageutils.JfifUtil;
import android.net.Uri;
import android.util.Pair;
import com.facebook.imageformat.ImageFormat;
import com.facebook.common.references.CloseableReference;
import java.io.InputStream;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
import android.content.ContentResolver;
import android.media.ExifInterface;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.image.EncodedImage;

class LocalExifThumbnailProducer$1 extends StatefulProducerRunnable<EncodedImage>
{
    final /* synthetic */ LocalExifThumbnailProducer this$0;
    final /* synthetic */ ImageRequest val$imageRequest;
    
    LocalExifThumbnailProducer$1(final LocalExifThumbnailProducer this$0, final Consumer consumer, final ProducerListener producerListener, final String s, final String s2, final ImageRequest val$imageRequest) {
        this.this$0 = this$0;
        this.val$imageRequest = val$imageRequest;
        super(consumer, producerListener, s, s2);
    }
    
    @Override
    protected void disposeResult(final EncodedImage encodedImage) {
        EncodedImage.closeSafely(encodedImage);
    }
    
    @Override
    protected Map<String, String> getExtraMapOnSuccess(final EncodedImage encodedImage) {
        return ImmutableMap.of("createdThumbnail", Boolean.toString(encodedImage != null));
    }
    
    @Override
    protected EncodedImage getResult() {
        final ExifInterface exifInterface = this.this$0.getExifInterface(this.val$imageRequest.getSourceUri());
        if (exifInterface == null || !exifInterface.hasThumbnail()) {
            return null;
        }
        return this.this$0.buildEncodedImage(this.this$0.mPooledByteBufferFactory.newByteBuffer(exifInterface.getThumbnail()), exifInterface);
    }
}
