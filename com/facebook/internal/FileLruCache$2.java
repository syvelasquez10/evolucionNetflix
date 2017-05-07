// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.File;

class FileLruCache$2 implements Runnable
{
    final /* synthetic */ FileLruCache this$0;
    final /* synthetic */ File[] val$filesToDelete;
    
    FileLruCache$2(final FileLruCache this$0, final File[] val$filesToDelete) {
        this.this$0 = this$0;
        this.val$filesToDelete = val$filesToDelete;
    }
    
    @Override
    public void run() {
        final File[] val$filesToDelete = this.val$filesToDelete;
        for (int length = val$filesToDelete.length, i = 0; i < length; ++i) {
            val$filesToDelete[i].delete();
        }
    }
}
