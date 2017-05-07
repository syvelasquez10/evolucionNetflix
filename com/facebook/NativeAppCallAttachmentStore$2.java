// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.io.Closeable;
import com.facebook.internal.Utility;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;

class NativeAppCallAttachmentStore$2 implements NativeAppCallAttachmentStore$ProcessAttachment<File>
{
    final /* synthetic */ NativeAppCallAttachmentStore this$0;
    
    NativeAppCallAttachmentStore$2(final NativeAppCallAttachmentStore this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void processAttachment(final File file, File file2) {
        final FileOutputStream fileOutputStream = new FileOutputStream(file2);
        while (true) {
            try {
                file2 = (File)new FileInputStream(file);
                Label_0058: {
                    try {
                        final byte[] array = new byte[1024];
                        while (true) {
                            final int read = ((FileInputStream)file2).read(array);
                            if (read <= 0) {
                                break Label_0058;
                            }
                            fileOutputStream.write(array, 0, read);
                        }
                    }
                    finally {}
                    Utility.closeQuietly(fileOutputStream);
                    Utility.closeQuietly((Closeable)file2);
                    throw;
                }
                Utility.closeQuietly(fileOutputStream);
                Utility.closeQuietly((Closeable)file2);
            }
            finally {
                file2 = null;
                continue;
            }
            break;
        }
    }
}
