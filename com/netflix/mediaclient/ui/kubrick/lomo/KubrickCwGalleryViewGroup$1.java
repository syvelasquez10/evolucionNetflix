// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

class KubrickCwGalleryViewGroup$1 extends GridLayoutManager
{
    final /* synthetic */ KubrickCwGalleryViewGroup this$0;
    
    KubrickCwGalleryViewGroup$1(final KubrickCwGalleryViewGroup this$0, final Context context, final int n, final int n2, final boolean b) {
        this.this$0 = this$0;
        super(context, n, n2, b);
    }
    
    @Override
    public boolean canScrollHorizontally() {
        return false;
    }
}
