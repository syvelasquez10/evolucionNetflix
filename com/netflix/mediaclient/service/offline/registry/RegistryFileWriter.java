// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.registry;

import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.Log;
import java.io.File;

class RegistryFileWriter
{
    private static final String TAG = "nf_offline_registry";
    
    static void recoverRegistryDataFileAtStart(final File file) {
        final File file2 = new File(file.getAbsolutePath() + ".new");
        if (file2.exists() && file2.length() > 0L) {
            Log.i("nf_offline_registry", "recovering result=%b newSize=%d oldSize=%d", file.delete(), file2.length(), file.length());
            FileUtils.moveFile(file2.getAbsolutePath(), file.getAbsolutePath());
        }
    }
    
    static boolean writeRegistryFileRecoverable(final File file, final String s) {
        final File file2 = new File(file.getAbsolutePath() + ".new");
        boolean b;
        if (b = FileUtils.writeBytesToFile(file2.getAbsolutePath(), s.getBytes())) {
            Log.i("nf_offline_registry", "deleteOldResult=%b moving %s to %s", file.delete(), file2.getAbsolutePath(), file.getAbsolutePath());
            b = FileUtils.moveFile(file2.getAbsolutePath(), file.getAbsolutePath());
        }
        Log.i("nf_offline_registry", "writeRegistryFileRecoverable isSuccess=%b", b);
        return b;
    }
}
