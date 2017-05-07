// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.widget.SectionIndexer;

class SectionIndexerAdapterWrapper extends AdapterWrapper implements SectionIndexer
{
    final SectionIndexer mSectionIndexerDelegate;
    
    SectionIndexerAdapterWrapper(final Context context, final StickyListHeadersAdapter stickyListHeadersAdapter) {
        super(context, stickyListHeadersAdapter);
        this.mSectionIndexerDelegate = (SectionIndexer)stickyListHeadersAdapter;
    }
    
    public int getPositionForSection(final int n) {
        return this.mSectionIndexerDelegate.getPositionForSection(n);
    }
    
    public int getSectionForPosition(final int n) {
        return this.mSectionIndexerDelegate.getSectionForPosition(n);
    }
    
    public Object[] getSections() {
        return this.mSectionIndexerDelegate.getSections();
    }
}
