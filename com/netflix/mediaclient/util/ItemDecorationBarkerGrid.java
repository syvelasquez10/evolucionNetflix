// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.Context;

public class ItemDecorationBarkerGrid extends ItemDecorationUniformPadding
{
    Context context;
    
    public ItemDecorationBarkerGrid(final Context context, final int n) {
        super(context.getResources().getDimensionPixelSize(2131362013), n);
        this.context = context;
    }
    
    public int getAllSpaceWidth() {
        return (this.numColumns + 1) * this.padding;
    }
}
