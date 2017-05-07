// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public interface VideoWindowForPostplay
{
    void animateIn();
    
    void animateOut(final Runnable p0);
    
    boolean canVideoVindowResize();
    
    void setVisible(final boolean p0);
}
