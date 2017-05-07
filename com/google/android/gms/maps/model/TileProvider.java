// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

public interface TileProvider
{
    public static final Tile NO_TILE = new Tile(-1, -1, null);
    
    Tile getTile(final int p0, final int p1, final int p2);
}
