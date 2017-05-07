// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.media.MediaRouter$Callback;
import android.view.ViewGroup$LayoutParams;
import android.util.Log;
import android.view.View;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.content.Context;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v4.view.ActionProvider;

public class MediaRouteActionProvider extends ActionProvider
{
    private static final String TAG = "MediaRouteActionProvider";
    private MediaRouteButton mButton;
    private final MediaRouteActionProvider$MediaRouterCallback mCallback;
    private MediaRouteDialogFactory mDialogFactory;
    private final MediaRouter mRouter;
    private MediaRouteSelector mSelector;
    
    public MediaRouteActionProvider(final Context context) {
        super(context);
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mDialogFactory = MediaRouteDialogFactory.getDefault();
        this.mRouter = MediaRouter.getInstance(context);
        this.mCallback = new MediaRouteActionProvider$MediaRouterCallback(this);
    }
    
    private void refreshRoute() {
        this.refreshVisibility();
    }
    
    @NonNull
    public MediaRouteDialogFactory getDialogFactory() {
        return this.mDialogFactory;
    }
    
    @Nullable
    public MediaRouteButton getMediaRouteButton() {
        return this.mButton;
    }
    
    @NonNull
    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }
    
    @Override
    public boolean isVisible() {
        return this.mRouter.isRouteAvailable(this.mSelector, 1);
    }
    
    @Override
    public View onCreateActionView() {
        if (this.mButton != null) {
            Log.e("MediaRouteActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old menu item...");
        }
        (this.mButton = this.onCreateMediaRouteButton()).setCheatSheetEnabled(true);
        this.mButton.setRouteSelector(this.mSelector);
        this.mButton.setDialogFactory(this.mDialogFactory);
        this.mButton.setLayoutParams(new ViewGroup$LayoutParams(-2, -1));
        return this.mButton;
    }
    
    public MediaRouteButton onCreateMediaRouteButton() {
        return new MediaRouteButton(this.getContext());
    }
    
    @Override
    public boolean onPerformDefaultAction() {
        return this.mButton != null && this.mButton.showDialog();
    }
    
    @Override
    public boolean overridesItemVisibility() {
        return true;
    }
    
    public void setDialogFactory(@NonNull final MediaRouteDialogFactory mediaRouteDialogFactory) {
        if (mediaRouteDialogFactory == null) {
            throw new IllegalArgumentException("factory must not be null");
        }
        if (this.mDialogFactory != mediaRouteDialogFactory) {
            this.mDialogFactory = mediaRouteDialogFactory;
            if (this.mButton != null) {
                this.mButton.setDialogFactory(mediaRouteDialogFactory);
            }
        }
    }
    
    public void setRouteSelector(@NonNull final MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (!this.mSelector.equals(mediaRouteSelector)) {
            if (!this.mSelector.isEmpty()) {
                this.mRouter.removeCallback(this.mCallback);
            }
            if (!mediaRouteSelector.isEmpty()) {
                this.mRouter.addCallback(mediaRouteSelector, this.mCallback);
            }
            this.mSelector = mediaRouteSelector;
            this.refreshRoute();
            if (this.mButton != null) {
                this.mButton.setRouteSelector(mediaRouteSelector);
            }
        }
    }
}
