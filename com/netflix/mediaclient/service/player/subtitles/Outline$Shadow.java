// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

public class Outline$Shadow
{
    public static final Outline$Shadow DEPRESSED;
    public static final Outline$Shadow DROP_SHADOW;
    public static final Outline$Shadow RAISED;
    public static final Outline$Shadow UNIFORM;
    public final int dx;
    public final int dy;
    public final float radius;
    
    static {
        RAISED = new Outline$Shadow(1.0f, 0, -2);
        DEPRESSED = new Outline$Shadow(1.0f, 0, 2);
        DROP_SHADOW = new Outline$Shadow(1.0f, 2, 2);
        UNIFORM = new Outline$Shadow(0.0f, 0, 0);
    }
    
    private Outline$Shadow(final float radius, final int dx, final int dy) {
        this.radius = radius;
        this.dx = dx;
        this.dy = dy;
    }
}
