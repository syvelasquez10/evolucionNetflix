// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import android.widget.ImageView;
import android.view.View;
import com.android.volley.Request$Priority;
import java.util.Iterator;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker;
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
    
    public String getImgUrl() {
        return this.imgUrl;
    }
    
    @Override
    public void onErrorResponse(final VolleyError volleyError) {
        if (this.hasRegistered) {
            final Iterator<InteractiveTracker> iterator = this.this$0.mInteractiveTrackers.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().onImageLoaded(this, ImageLoader$Type.NETWORK);
            }
        }
    }
    
    @Override
    public void onResponse(final ImageLoader$ImageContainer imageLoader$ImageContainer, final ImageLoader$Type imageLoader$Type) {
        if (this.hasRegistered) {
            final Iterator<InteractiveTracker> iterator = this.this$0.mInteractiveTrackers.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().onImageLoaded(this, imageLoader$Type);
            }
        }
    }
    
    public void register(final Request$Priority request$Priority) {
        for (final InteractiveTracker interactiveTracker : this.this$0.mInteractiveTrackers.values()) {
            if (interactiveTracker.shouldTrack((View)this.view, request$Priority)) {
                interactiveTracker.registerListener(this, this.view);
            }
        }
        this.hasRegistered = true;
    }
}
