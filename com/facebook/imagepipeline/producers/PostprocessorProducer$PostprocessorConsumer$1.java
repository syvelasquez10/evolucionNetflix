// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

class PostprocessorProducer$PostprocessorConsumer$1 extends BaseProducerContextCallbacks
{
    final /* synthetic */ PostprocessorProducer$PostprocessorConsumer this$1;
    final /* synthetic */ PostprocessorProducer val$this$0;
    
    PostprocessorProducer$PostprocessorConsumer$1(final PostprocessorProducer$PostprocessorConsumer this$1, final PostprocessorProducer val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void onCancellationRequested() {
        this.this$1.maybeNotifyOnCancellation();
    }
}
