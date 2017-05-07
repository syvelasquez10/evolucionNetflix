// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public class PlayScreenKindleFire extends PlayScreen
{
    public static final int AMAZON_FLAG_NOSOFTKEYS = Integer.MIN_VALUE;
    private static final int FLAG_SUPER_FULLSCREEN = -2147482624;
    
    PlayScreenKindleFire(final PlayerActivity playerActivity, final Listeners listeners) {
        super(playerActivity, listeners);
    }
    
    @Override
    protected void playerOverlayVisibility(final boolean b) {
        super.playerOverlayVisibility(b);
        if (b) {
            this.mController.getWindow().clearFlags(-2147482624);
            return;
        }
        this.mController.getWindow().addFlags(-2147482624);
    }
}
