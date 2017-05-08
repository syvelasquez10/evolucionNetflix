// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.backends.pipeline;

import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import android.net.Uri;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.controller.ControllerListener;
import java.util.Set;
import android.content.Context;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;

public class PipelineDraweeControllerBuilder extends AbstractDraweeControllerBuilder<PipelineDraweeControllerBuilder, ImageRequest, CloseableReference<CloseableImage>, ImageInfo>
{
    private final ImagePipeline mImagePipeline;
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;
    
    public PipelineDraweeControllerBuilder(final Context context, final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory, final ImagePipeline mImagePipeline, final Set<ControllerListener> set) {
        super(context, set);
        this.mImagePipeline = mImagePipeline;
        this.mPipelineDraweeControllerFactory = mPipelineDraweeControllerFactory;
    }
    
    @Override
    protected DataSource<CloseableReference<CloseableImage>> getDataSourceForRequest(final ImageRequest imageRequest, final Object o, final boolean b) {
        if (b) {
            return this.mImagePipeline.fetchImageFromBitmapCache(imageRequest, o);
        }
        return this.mImagePipeline.fetchDecodedImage(imageRequest, o);
    }
    
    @Override
    protected PipelineDraweeControllerBuilder getThis() {
        return this;
    }
    
    @Override
    protected PipelineDraweeController obtainController() {
        final DraweeController oldController = this.getOldController();
        if (oldController instanceof PipelineDraweeController) {
            final PipelineDraweeController pipelineDraweeController = (PipelineDraweeController)oldController;
            pipelineDraweeController.initialize(((AbstractDraweeControllerBuilder<BUILDER, REQUEST, CloseableReference<CloseableImage>, INFO>)this).obtainDataSourceSupplier(), AbstractDraweeControllerBuilder.generateUniqueControllerId(), this.getCallerContext());
            return pipelineDraweeController;
        }
        return this.mPipelineDraweeControllerFactory.newController(((AbstractDraweeControllerBuilder<BUILDER, REQUEST, CloseableReference<CloseableImage>, INFO>)this).obtainDataSourceSupplier(), AbstractDraweeControllerBuilder.generateUniqueControllerId(), this.getCallerContext());
    }
    
    @Override
    public PipelineDraweeControllerBuilder setUri(final Uri uri) {
        return super.setImageRequest(ImageRequest.fromUri(uri));
    }
}
