// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bif;

import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.TrickplayUrl;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import android.content.Context;
import java.io.FileNotFoundException;
import com.netflix.mediaclient.Log;
import java.io.RandomAccessFile;
import java.io.InputStream;
import java.io.FileInputStream;

class StreamingBifManager$1 implements Runnable
{
    final /* synthetic */ StreamingBifManager this$0;
    
    StreamingBifManager$1(final StreamingBifManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        try {
            final String string = this.this$0.mAppContext.getFilesDir() + "/" + this.this$0.mSavedFileName;
            if (this.this$0.parseIndexFromInputStream(new FileInputStream(string))) {
                this.this$0.mFile = new RandomAccessFile(string, "r");
                this.this$0.mIsBifLoaded.set(true);
            }
        }
        catch (FileNotFoundException ex) {
            if (Log.isLoggable()) {
                Log.d("BifManager", "loadBifIndexAsync has exception " + ex);
            }
        }
    }
}
