// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public abstract class PlayerSection extends Section
{
    protected final PlayerFragment playerFragment;
    protected final boolean tablet;
    
    public PlayerSection(final PlayerFragment playerFragment) {
        super(playerFragment.getActivity());
        this.playerFragment = playerFragment;
        this.tablet = playerFragment.getNetflixActivity().isTablet();
    }
    
    @Override
    public void destroy() {
    }
}
