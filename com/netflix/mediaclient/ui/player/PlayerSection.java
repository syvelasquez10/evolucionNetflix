// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.app.Activity;

public abstract class PlayerSection extends Section
{
    protected final PlayerActivity context;
    protected final boolean tablet;
    
    public PlayerSection(final PlayerActivity context) {
        super(context);
        this.context = context;
        this.tablet = context.isTablet();
    }
    
    @Override
    public void destroy() {
    }
}
