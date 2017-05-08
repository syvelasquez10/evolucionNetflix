// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import android.widget.ImageView;
import com.netflix.mediaclient.Log;
import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$ImageContainer;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$ImageListener;

class InteractiveTimer$1 implements ImageLoader$ImageListener
{
    final /* synthetic */ InteractiveTimer this$0;
    final /* synthetic */ ImageLoader$ImageListener val$listener;
    
    InteractiveTimer$1(final InteractiveTimer this$0, final ImageLoader$ImageListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        this.this$0.onImageLoaded(this, false);
        if (this.val$listener != null) {
            this.val$listener.onErrorResponse(volleyError);
        }
    }
    
    @Override
    public void onResponse(final ImageLoader$ImageContainer imageLoader$ImageContainer, final boolean b) {
        this.this$0.onImageLoaded(this, b);
        if (this.val$listener != null) {
            this.val$listener.onResponse(imageLoader$ImageContainer, b);
        }
    }
}
