// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter$Callback;
import android.support.v4.app.Fragment;

public class MediaRouteDiscoveryFragment extends Fragment
{
    private final String ARGUMENT_SELECTOR;
    private MediaRouter$Callback mCallback;
    private MediaRouter mRouter;
    private MediaRouteSelector mSelector;
    
    public MediaRouteDiscoveryFragment() {
        this.ARGUMENT_SELECTOR = "selector";
    }
    
    private void ensureRouteSelector() {
        if (this.mSelector == null) {
            final Bundle arguments = this.getArguments();
            if (arguments != null) {
                this.mSelector = MediaRouteSelector.fromBundle(arguments.getBundle("selector"));
            }
            if (this.mSelector == null) {
                this.mSelector = MediaRouteSelector.EMPTY;
            }
        }
    }
    
    private void ensureRouter() {
        if (this.mRouter == null) {
            this.mRouter = MediaRouter.getInstance((Context)this.getActivity());
        }
    }
    
    public MediaRouter getMediaRouter() {
        this.ensureRouter();
        return this.mRouter;
    }
    
    public MediaRouteSelector getRouteSelector() {
        this.ensureRouteSelector();
        return this.mSelector;
    }
    
    public MediaRouter$Callback onCreateCallback() {
        return new MediaRouteDiscoveryFragment$1(this);
    }
    
    public int onPrepareCallbackFlags() {
        return 4;
    }
    
    @Override
    public void onStart() {
        super.onStart();
        this.ensureRouteSelector();
        this.ensureRouter();
        this.mCallback = this.onCreateCallback();
        if (this.mCallback != null) {
            this.mRouter.addCallback(this.mSelector, this.mCallback, this.onPrepareCallbackFlags());
        }
    }
    
    @Override
    public void onStop() {
        if (this.mCallback != null) {
            this.mRouter.removeCallback(this.mCallback);
            this.mCallback = null;
        }
        super.onStop();
    }
    
    public void setRouteSelector(final MediaRouteSelector mSelector) {
        if (mSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        this.ensureRouteSelector();
        if (!this.mSelector.equals(mSelector)) {
            this.mSelector = mSelector;
            Bundle arguments;
            if ((arguments = this.getArguments()) == null) {
                arguments = new Bundle();
            }
            arguments.putBundle("selector", mSelector.asBundle());
            this.setArguments(arguments);
            if (this.mCallback != null) {
                this.mRouter.removeCallback(this.mCallback);
                this.mRouter.addCallback(this.mSelector, this.mCallback, this.onPrepareCallbackFlags());
            }
        }
    }
}
