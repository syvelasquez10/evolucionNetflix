// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import java.io.File;
import com.netflix.mediaclient.Log;

class FalkorAgent$15 implements Runnable
{
    final /* synthetic */ FalkorAgent this$0;
    
    FalkorAgent$15(final FalkorAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (Log.isLoggable()) {
            Log.d("FalkorAgent", "deletePrefetchMetadataFile: start");
        }
        final File file = new File(this.this$0.getService().getFilesDir(), "prefetch.json");
        while (true) {
            try {
                final boolean delete = file.delete();
                if (Log.isLoggable()) {
                    Log.d("FalkorAgent", "deletePrefetchMetadataFile: prefetch metadata file deleted = " + delete);
                }
                if (Log.isLoggable()) {
                    Log.d("FalkorAgent", "deletePrefetchMetadataFile: end");
                }
            }
            catch (SecurityException ex) {
                if (Log.isLoggable()) {
                    Log.d("FalkorAgent", "deletePrefetchMetadataFile: cannot delete prefetch metadata file - " + ex.getMessage());
                }
                continue;
            }
            break;
        }
    }
}
