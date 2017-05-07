// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public abstract class ResourceHelper
{
    public int pause;
    public int play;
    public int timelineDent;
    public int timelineHeightInDip;
    public int timelineHeightPaddingInDip;
    public int timelineThumbOffsetInDip;
    public int topBackground;
    public int zoomIn;
    public int zoomOut;
    
    public static ResourceHelper newInstance(final boolean b) {
        if (b) {
            return new ResourceHelperTablet();
        }
        return new ResourceHelperPhone();
    }
}
