// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public class PlayScreenJB extends PlayScreen
{
    private static final int BASE_FLAGS = 1792;
    private static final int HIDE_FLAGS = 3;
    private static final int SHOW_FLAGS = 0;
    
    PlayScreenJB(final PlayerActivity playerActivity, final Listeners listeners) {
        super(playerActivity, listeners);
        this.mSurface.setSystemUiVisibility(1792);
    }
    
    @Override
    protected void playerOverlayVisibility(final boolean b) {
        super.playerOverlayVisibility(b);
        if (b) {
            if (this.mSurface != null) {
                this.mSurface.setSystemUiVisibility(1792);
            }
        }
        else if (this.mSurface != null) {
            this.mSurface.setSystemUiVisibility(1795);
        }
    }
}
