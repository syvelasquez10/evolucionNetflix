// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.backends.pipeline;

import com.facebook.imagepipeline.animated.factory.AnimatedDrawableFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import java.util.concurrent.Executor;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.core.ImagePipeline;
import android.content.Context;
import com.facebook.drawee.controller.ControllerListener;
import java.util.Set;
import com.facebook.common.internal.Supplier;

public class PipelineDraweeControllerBuilderSupplier implements Supplier<PipelineDraweeControllerBuilder>
{
    private final Set<ControllerListener> mBoundControllerListeners;
    private final Context mContext;
    private final ImagePipeline mImagePipeline;
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;
    
    public PipelineDraweeControllerBuilderSupplier(final Context context) {
        this(context, ImagePipelineFactory.getInstance());
    }
    
    public PipelineDraweeControllerBuilderSupplier(final Context context, final ImagePipelineFactory imagePipelineFactory) {
        this(context, imagePipelineFactory, null);
    }
    
    public PipelineDraweeControllerBuilderSupplier(final Context mContext, final ImagePipelineFactory imagePipelineFactory, final Set<ControllerListener> mBoundControllerListeners) {
        this.mContext = mContext;
        this.mImagePipeline = imagePipelineFactory.getImagePipeline();
        final AnimatedFactory animatedFactory = imagePipelineFactory.getAnimatedFactory();
        AnimatedDrawableFactory animatedDrawableFactory = null;
        if (animatedFactory != null) {
            animatedDrawableFactory = animatedFactory.getAnimatedDrawableFactory(mContext);
        }
        this.mPipelineDraweeControllerFactory = new PipelineDraweeControllerFactory(mContext.getResources(), DeferredReleaser.getInstance(), animatedDrawableFactory, UiThreadImmediateExecutorService.getInstance());
        this.mBoundControllerListeners = mBoundControllerListeners;
    }
    
    @Override
    public PipelineDraweeControllerBuilder get() {
        return new PipelineDraweeControllerBuilder(this.mContext, this.mPipelineDraweeControllerFactory, this.mImagePipeline, this.mBoundControllerListeners);
    }
}
