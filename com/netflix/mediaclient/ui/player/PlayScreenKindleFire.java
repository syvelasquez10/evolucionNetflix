// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public class PlayScreenKindleFire extends PlayScreen
{
    public static final int AMAZON_FLAG_NOSOFTKEYS = Integer.MIN_VALUE;
    private static final int FLAG_SUPER_FULLSCREEN = -2147482624;
    
    PlayScreenKindleFire(final PlayerActivity playerActivity, final Listeners listeners, final PostPlayFactory.PostPlayType postPlayType) {
        super(playerActivity, listeners, postPlayType);
    }
    
    @Override
    void hideNavigationBar() {
        this.mController.getWindow().addFlags(-2147482624);
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
        this.mController.getWindow().clearFlags(-2147482624);
    }
}
