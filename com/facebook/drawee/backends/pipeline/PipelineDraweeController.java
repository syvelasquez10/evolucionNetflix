// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.backends.pipeline;

import com.facebook.common.internal.Objects;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.drawable.OrientedDrawable;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.common.internal.Preconditions;
import android.graphics.drawable.Drawable;
import java.util.concurrent.Executor;
import com.facebook.drawee.components.DeferredReleaser;
import android.content.res.Resources;
import com.facebook.drawee.drawable.LightBitmapDrawable;
import com.facebook.datasource.DataSource;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.animated.factory.AnimatedDrawableFactory;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.controller.AbstractDraweeController;

public class PipelineDraweeController extends AbstractDraweeController<CloseableReference<CloseableImage>, ImageInfo>
{
    private static final Class<?> TAG;
    private static boolean sIsLightEnabled;
    private static boolean sIsReuseEnabled;
    private final AnimatedDrawableFactory mAnimatedDrawableFactory;
    private Supplier<DataSource<CloseableReference<CloseableImage>>> mDataSourceSupplier;
    private LightBitmapDrawable mLightBitmapDrawable;
    private final Resources mResources;
    
    static {
        TAG = PipelineDraweeController.class;
    }
    
    public PipelineDraweeController(final Resources mResources, final DeferredReleaser deferredReleaser, final AnimatedDrawableFactory mAnimatedDrawableFactory, final Executor executor, final Supplier<DataSource<CloseableReference<CloseableImage>>> supplier, final String s, final Object o) {
        super(deferredReleaser, executor, s, o);
        this.mResources = mResources;
        this.mAnimatedDrawableFactory = mAnimatedDrawableFactory;
        this.init(supplier);
    }
    
    private void init(final Supplier<DataSource<CloseableReference<CloseableImage>>> mDataSourceSupplier) {
        this.mDataSourceSupplier = mDataSourceSupplier;
    }
    
    @Override
    protected Drawable createDrawable(final CloseableReference<CloseableImage> closeableReference) {
        Preconditions.checkState(CloseableReference.isValid(closeableReference));
        final CloseableImage closeableImage = closeableReference.get();
        if (closeableImage instanceof CloseableStaticBitmap) {
            final CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap)closeableImage;
            Object mLightBitmapDrawable;
            if (PipelineDraweeController.sIsLightEnabled) {
                if (PipelineDraweeController.sIsReuseEnabled && this.mLightBitmapDrawable != null) {
                    this.mLightBitmapDrawable.setBitmap(closeableStaticBitmap.getUnderlyingBitmap());
                }
                else {
                    this.mLightBitmapDrawable = new LightBitmapDrawable(this.mResources, closeableStaticBitmap.getUnderlyingBitmap());
                }
                mLightBitmapDrawable = this.mLightBitmapDrawable;
            }
            else {
                mLightBitmapDrawable = new BitmapDrawable(this.mResources, closeableStaticBitmap.getUnderlyingBitmap());
            }
            if (closeableStaticBitmap.getRotationAngle() == 0 || closeableStaticBitmap.getRotationAngle() == -1) {
                return (Drawable)mLightBitmapDrawable;
            }
            return new OrientedDrawable((Drawable)mLightBitmapDrawable, closeableStaticBitmap.getRotationAngle());
        }
        else {
            if (this.mAnimatedDrawableFactory != null) {
                return this.mAnimatedDrawableFactory.create(closeableImage);
            }
            throw new UnsupportedOperationException("Unrecognized image class: " + closeableImage);
        }
    }
    
    @Override
    protected DataSource<CloseableReference<CloseableImage>> getDataSource() {
        if (FLog.isLoggable(2)) {
            FLog.v(PipelineDraweeController.TAG, "controller %x: getDataSource", (Object)System.identityHashCode(this));
        }
        return this.mDataSourceSupplier.get();
    }
    
    @Override
    protected int getImageHash(final CloseableReference<CloseableImage> closeableReference) {
        if (closeableReference != null) {
            return closeableReference.getValueHash();
        }
        return 0;
    }
    
    @Override
    protected ImageInfo getImageInfo(final CloseableReference<CloseableImage> closeableReference) {
        Preconditions.checkState(CloseableReference.isValid(closeableReference));
        return closeableReference.get();
    }
    
    public void initialize(final Supplier<DataSource<CloseableReference<CloseableImage>>> supplier, final String s, final Object o) {
        super.initialize(s, o);
        this.init(supplier);
    }
    
    @Override
    protected void releaseDrawable(final Drawable drawable) {
        if (drawable instanceof DrawableWithCaches) {
            ((DrawableWithCaches)drawable).dropCaches();
        }
    }
    
    @Override
    protected void releaseImage(final CloseableReference<CloseableImage> closeableReference) {
        CloseableReference.closeSafely(closeableReference);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("super", super.toString()).add("dataSourceSupplier", this.mDataSourceSupplier).toString();
    }
}
