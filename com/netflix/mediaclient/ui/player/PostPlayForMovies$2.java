// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class PostPlayForMovies$2 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ PostPlayForMovies this$0;
    
    PostPlayForMovies$2(final PostPlayForMovies this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onErrorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.w("nf_postplay", "Image failed to be retrieved " + s);
        }
    }
    
    @Override
    public void onResponse(final Bitmap bitmap, final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_postplay", "Image retrieved " + s);
        }
    }
}
