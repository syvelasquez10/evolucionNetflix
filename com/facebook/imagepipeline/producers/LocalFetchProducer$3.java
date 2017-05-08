// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import com.facebook.common.internal.Supplier;

class LocalFetchProducer$3 implements Supplier<FileInputStream>
{
    final /* synthetic */ LocalFetchProducer this$0;
    final /* synthetic */ File val$file;
    
    LocalFetchProducer$3(final LocalFetchProducer this$0, final File val$file) {
        this.this$0 = this$0;
        this.val$file = val$file;
    }
    
    @Override
    public FileInputStream get() {
        try {
            return new FileInputStream(this.val$file);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
