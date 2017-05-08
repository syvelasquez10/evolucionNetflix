// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class PostPlayForPlayer$1 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ PostPlayForPlayer this$0;
    
    PostPlayForPlayer$1(final PostPlayForPlayer this$0) {
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
