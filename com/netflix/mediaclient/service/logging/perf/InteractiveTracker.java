// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import android.view.ViewTreeObserver$OnPreDrawListener;
import android.widget.ImageView;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$Type;
import com.netflix.mediaclient.Log;
import java.util.HashSet;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$ImageListener;
import java.util.Set;

public class InteractiveTracker
{
    public static final String TAG = "InteractiveTracker";
    private boolean hasCompleted;
    private InteractiveTracker$InteractiveListener interactiveListener;
    Set<ImageLoader$ImageListener> onscreenListeners;
    
    public InteractiveTracker() {
        this.onscreenListeners = new HashSet<ImageLoader$ImageListener>();
    }
    
    public void clearImageListeners() {
        this.onscreenListeners.clear();
    }
    
    public boolean isComplete() {
        return this.hasCompleted;
    }
    
    public void isNowInteractive() {
        Log.d("InteractiveTracker", "isNowInteractive()");
        if (this.interactiveListener == null) {
            Log.d("InteractiveTracker", "... but there was no listener attached so tracking has not completed");
            return;
        }
        this.hasCompleted = true;
        this.interactiveListener.onInteractive();
    }
    
    public void onImageLoaded(final ImageLoader$ImageListener imageLoader$ImageListener, final ImageLoader$Type imageLoader$Type) {
        Log.d("InteractiveTracker", "onImageLoaded -- " + imageLoader$ImageListener + " ... type? " + imageLoader$Type);
        if (imageLoader$Type != ImageLoader$Type.PLACEHOLDER) {
            if (!this.onscreenListeners.remove(imageLoader$ImageListener)) {
                Log.d("InteractiveTracker", ".... wasn't in onscreenListeners");
                return;
            }
            if (this.onscreenListeners.isEmpty() && imageLoader$Type != ImageLoader$Type.CACHE) {
                Log.d("InteractiveTracker", "onInteractive");
                this.isNowInteractive();
            }
        }
    }
    
    public ImageLoader$ImageListener registerListener(final ImageLoader$ImageListener imageLoader$ImageListener, final ImageView imageView) {
        Log.d("InteractiveTracker", "registerListener -- " + imageLoader$ImageListener);
        this.onscreenListeners.add(imageLoader$ImageListener);
        imageView.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new InteractiveTracker$1(this, imageView));
        return imageLoader$ImageListener;
    }
    
    public void setListener(final InteractiveTracker$InteractiveListener interactiveListener) {
        this.interactiveListener = interactiveListener;
        Log.d("InteractiveTracker", "-------------- Listener set --------------");
    }
    
    public boolean shouldTrack(final ImageView imageView) {
        return imageView != null && !this.isComplete();
    }
}
