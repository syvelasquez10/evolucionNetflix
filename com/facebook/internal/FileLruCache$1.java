// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.OutputStream;
import org.json.JSONObject;
import java.io.IOException;
import com.facebook.LoggingBehavior;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import com.facebook.Settings;
import android.content.Context;
import java.util.concurrent.atomic.AtomicLong;
import java.io.File;

class FileLruCache$1 implements FileLruCache$StreamCloseCallback
{
    final /* synthetic */ FileLruCache this$0;
    final /* synthetic */ File val$buffer;
    final /* synthetic */ long val$bufferFileCreateTime;
    final /* synthetic */ String val$key;
    
    FileLruCache$1(final FileLruCache this$0, final long val$bufferFileCreateTime, final File val$buffer, final String val$key) {
        this.this$0 = this$0;
        this.val$bufferFileCreateTime = val$bufferFileCreateTime;
        this.val$buffer = val$buffer;
        this.val$key = val$key;
    }
    
    @Override
    public void onClose() {
        if (this.val$bufferFileCreateTime < this.this$0.lastClearCacheTime.get()) {
            this.val$buffer.delete();
            return;
        }
        this.this$0.renameToTargetAndTrim(this.val$key, this.val$buffer);
    }
}
