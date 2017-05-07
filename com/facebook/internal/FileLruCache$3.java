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
import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

class FileLruCache$3 implements Runnable
{
    final /* synthetic */ FileLruCache this$0;
    
    FileLruCache$3(final FileLruCache this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.trim();
    }
}
