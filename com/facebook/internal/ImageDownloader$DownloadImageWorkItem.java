// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.InputStream;
import java.net.URI;
import java.io.Closeable;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;
import android.content.Context;

class ImageDownloader$DownloadImageWorkItem implements Runnable
{
    private Context context;
    private ImageDownloader$RequestKey key;
    
    ImageDownloader$DownloadImageWorkItem(final Context context, final ImageDownloader$RequestKey key) {
        this.context = context;
        this.key = key;
    }
    
    @Override
    public void run() {
        download(this.key, this.context);
    }
}
