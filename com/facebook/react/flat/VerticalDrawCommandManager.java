// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import java.util.Arrays;

final class VerticalDrawCommandManager extends ClippingDrawCommandManager
{
    VerticalDrawCommandManager(final FlatViewGroup flatViewGroup, final DrawCommand[] array) {
        super(flatViewGroup, array);
    }
    
    @Override
    int commandStartIndex() {
        int binarySearch;
        final int n = binarySearch = Arrays.binarySearch(this.mCommandMaxBottom, this.mClippingRect.top);
        if (n < 0) {
            binarySearch = ~n;
        }
        return binarySearch;
    }
    
    @Override
    int commandStopIndex(int binarySearch) {
        final int n = binarySearch = Arrays.binarySearch(this.mCommandMinTop, binarySearch, this.mCommandMinTop.length, this.mClippingRect.bottom);
        if (n < 0) {
            binarySearch = ~n;
        }
        return binarySearch;
    }
    
    @Override
    boolean regionAboveTouch(final int n, final float n2, final float n3) {
        return this.mRegionMaxBottom[n] < n3;
    }
    
    @Override
    int regionStopIndex(final float n, final float n2) {
        int binarySearch;
        final int n3 = binarySearch = Arrays.binarySearch(this.mRegionMinTop, 1.0E-4f + n2);
        if (n3 < 0) {
            binarySearch = ~n3;
        }
        return binarySearch;
    }
}
