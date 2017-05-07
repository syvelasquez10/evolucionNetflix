// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.view.ViewGroup;
import android.view.View;
import android.widget.ListAdapter;

public interface StickyListHeadersAdapter extends ListAdapter
{
    long getHeaderId(final int p0);
    
    View getHeaderView(final int p0, final View p1, final ViewGroup p2);
}
