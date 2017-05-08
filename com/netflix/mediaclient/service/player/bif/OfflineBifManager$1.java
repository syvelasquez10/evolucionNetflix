// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bif;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.FileNotFoundException;
import com.netflix.mediaclient.Log;
import java.io.RandomAccessFile;
import java.io.InputStream;
import java.io.FileInputStream;

class OfflineBifManager$1 implements Runnable
{
    final /* synthetic */ OfflineBifManager this$0;
    final /* synthetic */ String val$fileUrl;
    
    OfflineBifManager$1(final OfflineBifManager this$0, final String val$fileUrl) {
        this.this$0 = this$0;
        this.val$fileUrl = val$fileUrl;
    }
    
    @Override
    public void run() {
        try {
            if (this.this$0.parseIndexFromInputStream(new FileInputStream(this.val$fileUrl))) {
                this.this$0.mFile = new RandomAccessFile(this.val$fileUrl, "r");
                this.this$0.mIsBifLoaded.set(true);
            }
        }
        catch (FileNotFoundException ex) {
            if (Log.isLoggable()) {
                Log.d("OfflineBifManager", "loadBifIndexAsync has exception " + ex);
            }
        }
    }
}
