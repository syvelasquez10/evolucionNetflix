// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.annotation.TargetApi;

@TargetApi(16)
public class PlayScreenJB extends PlayScreen
{
    private static final int BASE_FLAGS = 1792;
    private static final int HIDE_FLAGS = 3;
    private static final int SHOW_FLAGS = 0;
    
    PlayScreenJB(final PlayerActivity playerActivity, final Listeners listeners, final PostPlay.PostPlayType postPlayType) {
        super(playerActivity, listeners, postPlayType);
        playerActivity.getWindow().getDecorView().setSystemUiVisibility(1792);
    }
    
    @Override
    void hideNavigationBar() {
        Log.d("screen", "hide nav JB");
        this.mController.getWindow().getDecorView().setSystemUiVisibility(1795);
    }
    
    @Override
    protected void playerOverlayVisibility(final boolean b) {
        super.playerOverlayVisibility(b);
        if (b) {
            this.showNavigationBar();
            return;
        }
        this.hideNavigationBar();
    }
    
    @Override
    void showNavigationBar() {
        Log.d("screen", "show nav JB");
        this.mController.getWindow().getDecorView().setSystemUiVisibility(1792);
    }
}
