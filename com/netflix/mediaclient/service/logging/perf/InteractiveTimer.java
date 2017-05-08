// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import android.widget.ImageView;
import com.netflix.mediaclient.Log;
import java.util.HashSet;
import com.netflix.mediaclient.service.resfetcher.volley.ImageLoader$ImageListener;
import java.util.Set;

public class InteractiveTimer
{
    private static final String TAG = "InteractiveTimer";
    private boolean hasCompleted;
    private InteractiveTimer$InteractiveListener interactiveListener;
    Set<ImageLoader$ImageListener> listeners;
    
    public InteractiveTimer(final InteractiveTimer$InteractiveListener interactiveListener) {
        this.listeners = new HashSet<ImageLoader$ImageListener>();
        this.interactiveListener = interactiveListener;
    }
    
    private void onImageLoaded(final ImageLoader$ImageListener imageLoader$ImageListener, final boolean b) {
        Log.i("InteractiveTimer", "onImageLoaded -- " + imageLoader$ImageListener);
        this.listeners.remove(imageLoader$ImageListener);
        if (this.listeners.size() == 0 && this.interactiveListener != null) {
            this.hasCompleted = true;
            Log.i("InteractiveTimer", "onInteractive -- " + imageLoader$ImageListener);
            this.interactiveListener.onInteractive();
        }
    }
    
    public ImageLoader$ImageListener registerListener(final ImageLoader$ImageListener imageLoader$ImageListener) {
        Log.i("InteractiveTimer", "registerListener -- " + imageLoader$ImageListener);
        final InteractiveTimer$1 interactiveTimer$1 = new InteractiveTimer$1(this, imageLoader$ImageListener);
        this.listeners.add(interactiveTimer$1);
        return interactiveTimer$1;
    }
    
    public boolean shouldTrack(final ImageView imageView) {
        return imageView != null && !this.hasCompleted;
    }
}
