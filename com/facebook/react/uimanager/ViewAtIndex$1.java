// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.util.Comparator;

final class ViewAtIndex$1 implements Comparator<ViewAtIndex>
{
    @Override
    public int compare(final ViewAtIndex viewAtIndex, final ViewAtIndex viewAtIndex2) {
        return viewAtIndex.mIndex - viewAtIndex2.mIndex;
    }
}
