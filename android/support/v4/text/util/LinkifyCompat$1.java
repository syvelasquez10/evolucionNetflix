// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text.util;

import java.util.Comparator;

final class LinkifyCompat$1 implements Comparator<LinkifyCompat$LinkSpec>
{
    @Override
    public final int compare(final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec, final LinkifyCompat$LinkSpec linkifyCompat$LinkSpec2) {
        if (linkifyCompat$LinkSpec.start >= linkifyCompat$LinkSpec2.start) {
            if (linkifyCompat$LinkSpec.start > linkifyCompat$LinkSpec2.start) {
                return 1;
            }
            if (linkifyCompat$LinkSpec.end < linkifyCompat$LinkSpec2.end) {
                return 1;
            }
            if (linkifyCompat$LinkSpec.end <= linkifyCompat$LinkSpec2.end) {
                return 0;
            }
        }
        return -1;
    }
}
