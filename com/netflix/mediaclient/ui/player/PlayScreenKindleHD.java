// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.annotation.SuppressLint;

public class PlayScreenKindleHD extends PlayScreen
{
    public static final int AMAZON_FLAGS_HIDE_DRAG_HANDLE = 2;
    @SuppressLint({ "InlinedApi" })
    private static final int BASE_FLAGS = 4;
    private static final int HIDE_FLAGS = 1;
    private static final int SHOW_FLAGS = 0;
    
    PlayScreenKindleHD(final PlayerFragment playerFragment, final PlayScreen$Listeners playScreen$Listeners, final PostPlayFactory$PostPlayType postPlayFactory$PostPlayType) {
        super(playerFragment, playScreen$Listeners, postPlayFactory$PostPlayType);
    }
    
    @Override
    void hideNavigationBar() {
        this.mController.getWindow().getDecorView().setSystemUiVisibility(7);
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
        this.mController.getWindow().getDecorView().setSystemUiVisibility(4);
    }
}
