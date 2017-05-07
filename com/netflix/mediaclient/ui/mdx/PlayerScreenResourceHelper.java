// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

public abstract class PlayerScreenResourceHelper
{
    public int pause;
    public int play;
    public int timelineDent;
    public int timelineHeightInDip;
    public int timelineHeightPaddingInDip;
    public int timelineThumbOffsetInDip;
    public int topBackground;
    
    public static PlayerScreenResourceHelper newInstance(final boolean b) {
        if (b) {
            return new PlayerScreenResourceHelperTablet();
        }
        return new PlayerScreenResourceHelperPhone();
    }
}
