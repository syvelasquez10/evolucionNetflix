// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.util.Comparator;

class ViewAtIndex
{
    public static Comparator<ViewAtIndex> COMPARATOR;
    public final int mIndex;
    public final int mTag;
    
    static {
        ViewAtIndex.COMPARATOR = new ViewAtIndex$1();
    }
    
    public ViewAtIndex(final int mTag, final int mIndex) {
        this.mTag = mTag;
        this.mIndex = mIndex;
    }
}
