// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.view.ViewGroup;
import android.view.View;
import android.widget.ListAdapter;

public interface StickyGridHeadersSimpleAdapter extends ListAdapter
{
    long getHeaderId(final int p0);
    
    View getHeaderView(final int p0, final View p1, final ViewGroup p2);
}
