// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import android.widget.ImageView;
import com.android.volley.Request$Priority;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

public abstract class ImageLoader$ImageInteractionTrackingListener implements ImageLoader$ImageListener
{
    private boolean hasRegistered;
    protected final String imgUrl;
    final /* synthetic */ ImageLoader this$0;
    protected final AdvancedImageView view;
    
    public ImageLoader$ImageInteractionTrackingListener(final ImageLoader this$0, final AdvancedImageView view, final String imgUrl) {
        this.this$0 = this$0;
        this.view = view;
        this.imgUrl = imgUrl;
    }
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (this.hasRegistered) {
            this.this$0.mTTRTracker.onImageLoaded(this, ImageLoader$Type.NETWORK);
        }
    }
    
    @Override
    public void onResponse(final ImageLoader$ImageContainer imageLoader$ImageContainer, final ImageLoader$Type imageLoader$Type) {
        if (this.hasRegistered) {
            this.this$0.mTTRTracker.onImageLoaded(this, imageLoader$Type);
        }
    }
    
    public void registerForTTR(final Request$Priority request$Priority) {
        if (this.this$0.mTTRTracker.shouldTrack(this.view, request$Priority)) {
            this.this$0.mTTRTracker.registerListener(this, this.view);
            this.hasRegistered = true;
        }
    }
}
