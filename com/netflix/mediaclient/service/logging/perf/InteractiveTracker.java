// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import com.android.volley.Request$Priority;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.widget.ImageView;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$Type;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$ImageInteractionTrackingListener;
import com.netflix.mediaclient.Log;
import java.util.HashSet;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$ImageListener;
import java.util.Set;

public abstract class InteractiveTracker
{
    public static final String TAG = "InteractiveTracker";
    private boolean hasCompleted;
    private InteractiveTracker$InteractiveListener interactiveListener;
    private Set<ImageLoader$ImageListener> onscreenListeners;
    
    public InteractiveTracker() {
        this.onscreenListeners = new HashSet<ImageLoader$ImageListener>();
    }
    
    private void clearImageListeners() {
        this.onscreenListeners.clear();
    }
    
    private void isNowInteractive() {
        Log.d("InteractiveTracker", "isNowInteractive() -> " + this.getId());
        if (this.interactiveListener == null) {
            Log.d("InteractiveTracker", "... but there was no listener attached so tracking has not completed");
            return;
        }
        this.hasCompleted = true;
        this.interactiveListener.onInteractive();
    }
    
    public abstract String getId();
    
    public boolean isComplete() {
        return this.hasCompleted;
    }
    
    public void onImageLoaded(final ImageLoader$ImageInteractionTrackingListener imageLoader$ImageInteractionTrackingListener, final ImageLoader$Type imageLoader$Type) {
        Log.v("InteractiveTracker", "onImageLoaded -- " + imageLoader$ImageInteractionTrackingListener.getImgUrl() + " ... type? " + imageLoader$Type);
        if (imageLoader$Type != ImageLoader$Type.PLACEHOLDER) {
            if (!this.onscreenListeners.remove(imageLoader$ImageInteractionTrackingListener)) {
                Log.d("InteractiveTracker", ".... wasn't in onscreenListeners");
                return;
            }
            if (this.onscreenListeners.isEmpty() && imageLoader$Type != ImageLoader$Type.CACHE) {
                Log.d("InteractiveTracker", "onInteractive");
                this.isNowInteractive();
            }
        }
    }
    
    public ImageLoader$ImageListener registerListener(final ImageLoader$ImageInteractionTrackingListener imageLoader$ImageInteractionTrackingListener, final ImageView imageView) {
        Log.d("InteractiveTracker", "registerListener -- " + imageLoader$ImageInteractionTrackingListener.getImgUrl());
        this.onscreenListeners.add(imageLoader$ImageInteractionTrackingListener);
        imageView.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new InteractiveTracker$1(this, imageView));
        return imageLoader$ImageInteractionTrackingListener;
    }
    
    public void setListener(final InteractiveTracker$InteractiveListener interactiveListener) {
        this.clearImageListeners();
        this.interactiveListener = interactiveListener;
        String s;
        if (interactiveListener == null) {
            s = " CLEARED";
        }
        else {
            s = "SET";
        }
        Log.d("InteractiveTracker", "-------------- Listener %s -> %s --------------", s, this.getId());
    }
    
    public boolean shouldTrack(final View view, final Request$Priority request$Priority) {
        return view != null && !this.isComplete();
    }
}
