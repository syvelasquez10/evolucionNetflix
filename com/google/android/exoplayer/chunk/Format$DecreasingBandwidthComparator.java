// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import java.util.Comparator;

public final class Format$DecreasingBandwidthComparator implements Comparator<Format>
{
    @Override
    public int compare(final Format format, final Format format2) {
        return format2.bitrate - format.bitrate;
    }
}
