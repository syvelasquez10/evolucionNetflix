// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.OutputStream;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.AbstractQueue;
import java.util.PriorityQueue;
import com.facebook.LoggingBehavior;
import com.facebook.Settings;
import android.content.Context;
import java.util.concurrent.atomic.AtomicLong;
import java.io.File;

class FileLruCache$1 implements FileLruCache$StreamCloseCallback
{
    final /* synthetic */ FileLruCache this$0;
    final /* synthetic */ File val$buffer;
    final /* synthetic */ String val$key;
    
    FileLruCache$1(final FileLruCache this$0, final String val$key, final File val$buffer) {
        this.this$0 = this$0;
        this.val$key = val$key;
        this.val$buffer = val$buffer;
    }
    
    @Override
    public void onClose() {
        this.this$0.renameToTargetAndTrim(this.val$key, this.val$buffer);
    }
}
