// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.references.ResourceReleaser;

class FlexByteArrayPool$1 implements ResourceReleaser<byte[]>
{
    final /* synthetic */ FlexByteArrayPool this$0;
    
    FlexByteArrayPool$1(final FlexByteArrayPool this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void release(final byte[] array) {
        this.this$0.release(array);
    }
}
