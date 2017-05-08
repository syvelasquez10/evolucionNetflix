// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.backends.pipeline;

import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.common.internal.Supplier;
import java.util.concurrent.Executor;
import android.content.res.Resources;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.imagepipeline.animated.factory.AnimatedDrawableFactory;

public class PipelineDraweeControllerFactory
{
    private AnimatedDrawableFactory mAnimatedDrawableFactory;
    private DeferredReleaser mDeferredReleaser;
    private Resources mResources;
    private Executor mUiThreadExecutor;
    
    public PipelineDraweeControllerFactory(final Resources mResources, final DeferredReleaser mDeferredReleaser, final AnimatedDrawableFactory mAnimatedDrawableFactory, final Executor mUiThreadExecutor) {
        this.mResources = mResources;
        this.mDeferredReleaser = mDeferredReleaser;
        this.mAnimatedDrawableFactory = mAnimatedDrawableFactory;
        this.mUiThreadExecutor = mUiThreadExecutor;
    }
    
    public PipelineDraweeController newController(final Supplier<DataSource<CloseableReference<CloseableImage>>> supplier, final String s, final Object o) {
        return new PipelineDraweeController(this.mResources, this.mDeferredReleaser, this.mAnimatedDrawableFactory, this.mUiThreadExecutor, supplier, s, o);
    }
}
