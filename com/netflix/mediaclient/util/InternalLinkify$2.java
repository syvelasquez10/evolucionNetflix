// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Comparator;

final class InternalLinkify$2 implements Comparator<LinkSpec>
{
    @Override
    public final int compare(final LinkSpec linkSpec, final LinkSpec linkSpec2) {
        if (linkSpec.start >= linkSpec2.start) {
            if (linkSpec.start > linkSpec2.start) {
                return 1;
            }
            if (linkSpec.end < linkSpec2.end) {
                return 1;
            }
            if (linkSpec.end <= linkSpec2.end) {
                return 0;
            }
        }
        return -1;
    }
    
    @Override
    public final boolean equals(final Object o) {
        return false;
    }
}
