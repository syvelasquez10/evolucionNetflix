// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.backends.pipeline;

import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import android.content.Context;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.core.ImagePipeline;

public class Fresco
{
    private static PipelineDraweeControllerBuilderSupplier sDraweeControllerBuilderSupplier;
    
    public static ImagePipeline getImagePipeline() {
        return getImagePipelineFactory().getImagePipeline();
    }
    
    public static ImagePipelineFactory getImagePipelineFactory() {
        return ImagePipelineFactory.getInstance();
    }
    
    public static void initialize(final Context context, final ImagePipelineConfig imagePipelineConfig) {
        ImagePipelineFactory.initialize(imagePipelineConfig);
        initializeDrawee(context);
    }
    
    private static void initializeDrawee(final Context context) {
        SimpleDraweeView.initialize(Fresco.sDraweeControllerBuilderSupplier = new PipelineDraweeControllerBuilderSupplier(context));
    }
    
    public static PipelineDraweeControllerBuilder newDraweeControllerBuilder() {
        return Fresco.sDraweeControllerBuilderSupplier.get();
    }
}
