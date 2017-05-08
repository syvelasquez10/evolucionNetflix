// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.imagepipeline.producers.ThrottlingProducer;
import com.facebook.imagepipeline.producers.ThumbnailBranchProducer;
import com.facebook.imagepipeline.producers.ThumbnailProducer;
import com.facebook.imagepipeline.producers.AddImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.DataFetchProducer;
import android.os.Build$VERSION;
import android.net.Uri;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.util.UriUtil;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashMap;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import java.util.Map;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.Producer;

public class ProducerSequenceFactory
{
    Producer<EncodedImage> mBackgroundNetworkFetchToEncodedMemorySequence;
    Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> mCloseableImagePrefetchSequences;
    private Producer<EncodedImage> mCommonNetworkFetchToEncodedMemorySequence;
    Producer<CloseableReference<CloseableImage>> mDataFetchSequence;
    private final boolean mDownsampleEnabled;
    Producer<CloseableReference<CloseableImage>> mLocalAssetFetchSequence;
    Producer<CloseableReference<CloseableImage>> mLocalContentUriFetchSequence;
    Producer<CloseableReference<CloseableImage>> mLocalImageFileFetchSequence;
    Producer<CloseableReference<CloseableImage>> mLocalResourceFetchSequence;
    Producer<CloseableReference<CloseableImage>> mLocalVideoFileFetchSequence;
    Producer<CloseableReference<CloseableImage>> mNetworkFetchSequence;
    Producer<Void> mNetworkFetchToEncodedMemoryPrefetchSequence;
    private final NetworkFetcher mNetworkFetcher;
    Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> mPostprocessorSequences;
    private final ProducerFactory mProducerFactory;
    private final boolean mResizeAndRotateEnabledForNetwork;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;
    private final boolean mWebpSupportEnabled;
    
    public ProducerSequenceFactory(final ProducerFactory mProducerFactory, final NetworkFetcher mNetworkFetcher, final boolean mResizeAndRotateEnabledForNetwork, final boolean mDownsampleEnabled, final boolean mWebpSupportEnabled, final ThreadHandoffProducerQueue mThreadHandoffProducerQueue) {
        this.mProducerFactory = mProducerFactory;
        this.mNetworkFetcher = mNetworkFetcher;
        this.mResizeAndRotateEnabledForNetwork = mResizeAndRotateEnabledForNetwork;
        this.mDownsampleEnabled = mDownsampleEnabled;
        this.mWebpSupportEnabled = mWebpSupportEnabled;
        this.mPostprocessorSequences = new HashMap<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>>();
        this.mCloseableImagePrefetchSequences = new HashMap<Producer<CloseableReference<CloseableImage>>, Producer<Void>>();
        this.mThreadHandoffProducerQueue = mThreadHandoffProducerQueue;
    }
    
    private Producer<EncodedImage> getBackgroundNetworkFetchToEncodedMemorySequence() {
        synchronized (this) {
            if (this.mBackgroundNetworkFetchToEncodedMemorySequence == null) {
                this.mBackgroundNetworkFetchToEncodedMemorySequence = this.mProducerFactory.newBackgroundThreadHandoffProducer(this.getCommonNetworkFetchToEncodedMemorySequence(), this.mThreadHandoffProducerQueue);
            }
            return this.mBackgroundNetworkFetchToEncodedMemorySequence;
        }
    }
    
    private Producer<CloseableReference<CloseableImage>> getBasicDecodedImageSequence(final ImageRequest imageRequest) {
        Preconditions.checkNotNull(imageRequest);
        final Uri sourceUri = imageRequest.getSourceUri();
        Preconditions.checkNotNull(sourceUri, "Uri is null.");
        if (UriUtil.isNetworkUri(sourceUri)) {
            return this.getNetworkFetchSequence();
        }
        if (UriUtil.isLocalFileUri(sourceUri)) {
            if (MediaUtils.isVideo(MediaUtils.extractMime(sourceUri.getPath()))) {
                return this.getLocalVideoFileFetchSequence();
            }
            return this.getLocalImageFileFetchSequence();
        }
        else {
            if (UriUtil.isLocalContentUri(sourceUri)) {
                return this.getLocalContentUriFetchSequence();
            }
            if (UriUtil.isLocalAssetUri(sourceUri)) {
                return this.getLocalAssetFetchSequence();
            }
            if (UriUtil.isLocalResourceUri(sourceUri)) {
                return this.getLocalResourceFetchSequence();
            }
            if (UriUtil.isDataUri(sourceUri)) {
                return this.getDataFetchSequence();
            }
            String s2;
            final String s = s2 = sourceUri.toString();
            if (s.length() > 30) {
                s2 = s.substring(0, 30) + "...";
            }
            throw new RuntimeException("Unsupported uri scheme! Uri is: " + s2);
        }
    }
    
    private Producer<EncodedImage> getCommonNetworkFetchToEncodedMemorySequence() {
        synchronized (this) {
            if (this.mCommonNetworkFetchToEncodedMemorySequence == null) {
                this.mCommonNetworkFetchToEncodedMemorySequence = ProducerFactory.newAddImageTransformMetaDataProducer(this.newEncodedCacheMultiplexToTranscodeSequence(this.mProducerFactory.newNetworkFetchProducer(this.mNetworkFetcher)));
                if (this.mResizeAndRotateEnabledForNetwork && !this.mDownsampleEnabled) {
                    this.mCommonNetworkFetchToEncodedMemorySequence = this.mProducerFactory.newResizeAndRotateProducer(this.mCommonNetworkFetchToEncodedMemorySequence);
                }
            }
            return this.mCommonNetworkFetchToEncodedMemorySequence;
        }
    }
    
    private Producer<CloseableReference<CloseableImage>> getDataFetchSequence() {
        synchronized (this) {
            if (this.mDataFetchSequence == null) {
                Producer<EncodedImage> producer;
                final DataFetchProducer dataFetchProducer = (DataFetchProducer)(producer = this.mProducerFactory.newDataFetchProducer());
                if (Build$VERSION.SDK_INT < 18) {
                    producer = dataFetchProducer;
                    if (!this.mWebpSupportEnabled) {
                        producer = this.mProducerFactory.newWebpTranscodeProducer(dataFetchProducer);
                    }
                }
                final ProducerFactory mProducerFactory = this.mProducerFactory;
                Producer<EncodedImage> producer2;
                final AddImageTransformMetaDataProducer addImageTransformMetaDataProducer = (AddImageTransformMetaDataProducer)(producer2 = ProducerFactory.newAddImageTransformMetaDataProducer(producer));
                if (!this.mDownsampleEnabled) {
                    producer2 = this.mProducerFactory.newResizeAndRotateProducer(addImageTransformMetaDataProducer);
                }
                this.mDataFetchSequence = this.newBitmapCacheGetToDecodeSequence(producer2);
            }
            return this.mDataFetchSequence;
        }
    }
    
    private Producer<CloseableReference<CloseableImage>> getLocalAssetFetchSequence() {
        synchronized (this) {
            if (this.mLocalAssetFetchSequence == null) {
                this.mLocalAssetFetchSequence = this.newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalAssetFetchProducer());
            }
            return this.mLocalAssetFetchSequence;
        }
    }
    
    private Producer<CloseableReference<CloseableImage>> getLocalContentUriFetchSequence() {
        synchronized (this) {
            if (this.mLocalContentUriFetchSequence == null) {
                this.mLocalContentUriFetchSequence = this.newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalContentUriFetchProducer(), new ThumbnailProducer[] { this.mProducerFactory.newLocalContentUriThumbnailFetchProducer(), this.mProducerFactory.newLocalExifThumbnailProducer() });
            }
            return this.mLocalContentUriFetchSequence;
        }
    }
    
    private Producer<CloseableReference<CloseableImage>> getLocalImageFileFetchSequence() {
        synchronized (this) {
            if (this.mLocalImageFileFetchSequence == null) {
                this.mLocalImageFileFetchSequence = this.newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalFileFetchProducer());
            }
            return this.mLocalImageFileFetchSequence;
        }
    }
    
    private Producer<CloseableReference<CloseableImage>> getLocalResourceFetchSequence() {
        synchronized (this) {
            if (this.mLocalResourceFetchSequence == null) {
                this.mLocalResourceFetchSequence = this.newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalResourceFetchProducer());
            }
            return this.mLocalResourceFetchSequence;
        }
    }
    
    private Producer<CloseableReference<CloseableImage>> getLocalVideoFileFetchSequence() {
        synchronized (this) {
            if (this.mLocalVideoFileFetchSequence == null) {
                this.mLocalVideoFileFetchSequence = this.newBitmapCacheGetToBitmapCacheSequence(this.mProducerFactory.newLocalVideoThumbnailProducer());
            }
            return this.mLocalVideoFileFetchSequence;
        }
    }
    
    private Producer<CloseableReference<CloseableImage>> getNetworkFetchSequence() {
        synchronized (this) {
            if (this.mNetworkFetchSequence == null) {
                this.mNetworkFetchSequence = this.newBitmapCacheGetToDecodeSequence(this.getCommonNetworkFetchToEncodedMemorySequence());
            }
            return this.mNetworkFetchSequence;
        }
    }
    
    private Producer<Void> getNetworkFetchToEncodedMemoryPrefetchSequence() {
        synchronized (this) {
            if (this.mNetworkFetchToEncodedMemoryPrefetchSequence == null) {
                final ProducerFactory mProducerFactory = this.mProducerFactory;
                this.mNetworkFetchToEncodedMemoryPrefetchSequence = ProducerFactory.newSwallowResultProducer(this.getBackgroundNetworkFetchToEncodedMemorySequence());
            }
            return this.mNetworkFetchToEncodedMemoryPrefetchSequence;
        }
    }
    
    private Producer<CloseableReference<CloseableImage>> getPostprocessorSequence(final Producer<CloseableReference<CloseableImage>> producer) {
        synchronized (this) {
            if (!this.mPostprocessorSequences.containsKey(producer)) {
                this.mPostprocessorSequences.put(producer, this.mProducerFactory.newPostprocessorBitmapMemoryCacheProducer(this.mProducerFactory.newPostprocessorProducer(producer)));
            }
            return this.mPostprocessorSequences.get(producer);
        }
    }
    
    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence(final Producer<CloseableReference<CloseableImage>> producer) {
        return this.mProducerFactory.newBitmapMemoryCacheGetProducer((Producer<CloseableReference<CloseableImage>>)this.mProducerFactory.newBackgroundThreadHandoffProducer((Producer<Object>)this.mProducerFactory.newBitmapMemoryCacheKeyMultiplexProducer(this.mProducerFactory.newBitmapMemoryCacheProducer(producer)), this.mThreadHandoffProducerQueue));
    }
    
    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToDecodeSequence(final Producer<EncodedImage> producer) {
        return this.newBitmapCacheGetToBitmapCacheSequence(this.mProducerFactory.newDecodeProducer(producer));
    }
    
    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(final Producer<EncodedImage> producer) {
        return this.newBitmapCacheGetToLocalTransformSequence(producer, new ThumbnailProducer[] { this.mProducerFactory.newLocalExifThumbnailProducer() });
    }
    
    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(final Producer<EncodedImage> producer, final ThumbnailProducer<EncodedImage>[] array) {
        return this.newBitmapCacheGetToDecodeSequence(this.newLocalTransformationsSequence(this.newEncodedCacheMultiplexToTranscodeSequence(producer), array));
    }
    
    private Producer<EncodedImage> newEncodedCacheMultiplexToTranscodeSequence(final Producer<EncodedImage> producer) {
        Producer<EncodedImage> webpTranscodeProducer = producer;
        if (Build$VERSION.SDK_INT < 18) {
            webpTranscodeProducer = producer;
            if (!this.mWebpSupportEnabled) {
                webpTranscodeProducer = this.mProducerFactory.newWebpTranscodeProducer(producer);
            }
        }
        return this.mProducerFactory.newEncodedCacheKeyMultiplexProducer(this.mProducerFactory.newEncodedMemoryCacheProducer(this.mProducerFactory.newDiskCacheProducer(webpTranscodeProducer)));
    }
    
    private Producer<EncodedImage> newLocalThumbnailProducer(final ThumbnailProducer<EncodedImage>[] array) {
        final ThumbnailBranchProducer thumbnailBranchProducer = this.mProducerFactory.newThumbnailBranchProducer(array);
        if (this.mDownsampleEnabled) {
            return thumbnailBranchProducer;
        }
        return this.mProducerFactory.newResizeAndRotateProducer(thumbnailBranchProducer);
    }
    
    private Producer<EncodedImage> newLocalTransformationsSequence(final Producer<EncodedImage> producer, final ThumbnailProducer<EncodedImage>[] array) {
        Producer<EncodedImage> producer2;
        final AddImageTransformMetaDataProducer addImageTransformMetaDataProducer = (AddImageTransformMetaDataProducer)(producer2 = ProducerFactory.newAddImageTransformMetaDataProducer(producer));
        if (!this.mDownsampleEnabled) {
            producer2 = this.mProducerFactory.newResizeAndRotateProducer(addImageTransformMetaDataProducer);
        }
        final ThrottlingProducer<EncodedImage> throttlingProducer = this.mProducerFactory.newThrottlingProducer(5, producer2);
        final ProducerFactory mProducerFactory = this.mProducerFactory;
        return ProducerFactory.newBranchOnSeparateImagesProducer(this.newLocalThumbnailProducer(array), throttlingProducer);
    }
    
    private static void validateEncodedImageRequest(final ImageRequest imageRequest) {
        Preconditions.checkNotNull(imageRequest);
        Preconditions.checkArgument(UriUtil.isNetworkUri(imageRequest.getSourceUri()));
        Preconditions.checkArgument(imageRequest.getLowestPermittedRequestLevel().getValue() <= ImageRequest$RequestLevel.ENCODED_MEMORY_CACHE.getValue());
    }
    
    public Producer<CloseableReference<CloseableImage>> getDecodedImageProducerSequence(final ImageRequest imageRequest) {
        Producer<CloseableReference<CloseableImage>> producer = this.getBasicDecodedImageSequence(imageRequest);
        if (imageRequest.getPostprocessor() != null) {
            producer = this.getPostprocessorSequence(producer);
        }
        return producer;
    }
    
    public Producer<Void> getEncodedImagePrefetchProducerSequence(final ImageRequest imageRequest) {
        validateEncodedImageRequest(imageRequest);
        return this.getNetworkFetchToEncodedMemoryPrefetchSequence();
    }
}
