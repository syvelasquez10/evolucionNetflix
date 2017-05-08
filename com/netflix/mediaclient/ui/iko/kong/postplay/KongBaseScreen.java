// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.os.Handler;

public abstract class KongBaseScreen
{
    protected Handler handler;
    protected PlayerFragment playerFragment;
    protected KongInteractivePostPlayManager postPlayManager;
    
    KongBaseScreen(final KongInteractivePostPlayManager postPlayManager) {
        this.postPlayManager = postPlayManager;
        this.playerFragment = this.getPlayerFragment();
        this.handler = this.getHandler();
    }
    
    protected NetflixActivity getActivity() {
        return this.postPlayManager.getActivity();
    }
    
    protected Handler getHandler() {
        return this.postPlayManager.getHandler();
    }
    
    protected PlayerFragment getPlayerFragment() {
        return this.postPlayManager.getPlayerFragment();
    }
    
    abstract void hide();
    
    abstract void initViews(final View p0);
    
    abstract void loadPostPlayData(final KongInteractivePostPlayModel p0);
    
    abstract void loadResources();
    
    abstract void onResourcesLoaded();
    
    public abstract void releaseBitmapResources();
    
    abstract void start();
}
