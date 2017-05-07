// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.Comparator;

final class FalkorKidsCharacter$1 implements Comparator<FalkorVideo>
{
    @Override
    public int compare(final FalkorVideo falkorVideo, final FalkorVideo falkorVideo2) {
        if (falkorVideo.getYear() < falkorVideo2.getYear()) {
            return 1;
        }
        if (falkorVideo.getYear() > falkorVideo2.getYear()) {
            return -1;
        }
        return 0;
    }
}
