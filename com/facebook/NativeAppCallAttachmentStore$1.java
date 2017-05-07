// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.io.Closeable;
import com.facebook.internal.Utility;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.FileOutputStream;
import java.io.File;
import android.graphics.Bitmap;

class NativeAppCallAttachmentStore$1 implements NativeAppCallAttachmentStore$ProcessAttachment<Bitmap>
{
    final /* synthetic */ NativeAppCallAttachmentStore this$0;
    
    NativeAppCallAttachmentStore$1(final NativeAppCallAttachmentStore this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void processAttachment(final Bitmap bitmap, File file) {
        file = (File)new FileOutputStream(file);
        try {
            bitmap.compress(Bitmap$CompressFormat.JPEG, 100, (OutputStream)file);
        }
        finally {
            Utility.closeQuietly((Closeable)file);
        }
    }
}
