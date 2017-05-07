// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import com.netflix.mediaclient.Log;
import android.graphics.Bitmap;
import com.netflix.mediaclient.android.app.LoggingLruCache;

public class BitmapLruCache extends LoggingLruCache<String, Bitmap>
{
    private static final boolean LOG_VERBOSE = false;
    private static final String TAG = "BitmapLruCache";
    
    public BitmapLruCache(final int n) {
        super("BitmapLruCache", n);
        Log.v("BitmapLruCache", "Max size: " + n);
    }
    
    @Override
    protected void entryRemoved(final boolean b, final String s, final Bitmap bitmap, final Bitmap bitmap2) {
        super.entryRemoved(b, s, bitmap, bitmap2);
    }
    
    public Bitmap getBitmap(final String s) {
        return (Bitmap)super.get((Object)s);
    }
    
    public void putBitmap(final String s, final Bitmap bitmap) {
        this.put((Object)s, (Object)bitmap);
    }
    
    protected int sizeOf(final String s, final Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
