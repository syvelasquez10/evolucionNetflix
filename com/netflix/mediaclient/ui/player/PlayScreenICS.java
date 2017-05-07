// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public class PlayScreenICS extends PlayScreen
{
    private static final int BASE_FLAGS = 4;
    private static final int HIDE_FLAGS = 1;
    private static final int SHOW_FLAGS = 0;
    
    PlayScreenICS(final PlayerActivity playerActivity, final Listeners listeners) {
        super(playerActivity, listeners);
    }
    
    @Override
    protected void playerOverlayVisibility(final boolean b) {
        super.playerOverlayVisibility(b);
        if (b) {
            if (this.mSurface != null) {
                this.mSurface.setSystemUiVisibility(4);
            }
        }
        else if (this.mSurface != null) {
            this.mSurface.setSystemUiVisibility(5);
        }
    }
}
