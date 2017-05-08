// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.camera;

import java.io.FilenameFilter;
import java.io.File;
import com.facebook.react.bridge.ReactContext;
import android.content.Context;
import com.facebook.react.bridge.GuardedAsyncTask;

class ImageEditingManager$CleanTask extends GuardedAsyncTask<Void, Void>
{
    private final Context mContext;
    
    private ImageEditingManager$CleanTask(final ReactContext mContext) {
        super(mContext);
        this.mContext = (Context)mContext;
    }
    
    private void cleanDirectory(final File file) {
        final File[] listFiles = file.listFiles(new ImageEditingManager$CleanTask$1(this));
        if (listFiles != null) {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                listFiles[i].delete();
            }
        }
    }
    
    @Override
    protected void doInBackgroundGuarded(final Void... array) {
        this.cleanDirectory(this.mContext.getCacheDir());
        final File externalCacheDir = this.mContext.getExternalCacheDir();
        if (externalCacheDir != null) {
            this.cleanDirectory(externalCacheDir);
        }
    }
}
