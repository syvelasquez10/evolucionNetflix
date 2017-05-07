// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import android.database.DataSetObserver;
import android.widget.BaseAdapter;

public class StickyGridHeadersSimpleAdapterWrapper extends BaseAdapter implements StickyGridHeadersBaseAdapter
{
    private StickyGridHeadersSimpleAdapter mDelegate;
    private StickyGridHeadersSimpleAdapterWrapper$HeaderData[] mHeaders;
    
    public StickyGridHeadersSimpleAdapterWrapper(final StickyGridHeadersSimpleAdapter mDelegate) {
        (this.mDelegate = mDelegate).registerDataSetObserver((DataSetObserver)new StickyGridHeadersSimpleAdapterWrapper$DataSetObserverExtension(this, null));
        this.mHeaders = this.generateHeaderList(mDelegate);
    }
    
    protected StickyGridHeadersSimpleAdapterWrapper$HeaderData[] generateHeaderList(final StickyGridHeadersSimpleAdapter stickyGridHeadersSimpleAdapter) {
        final HashMap<Long, StickyGridHeadersSimpleAdapterWrapper$HeaderData> hashMap = (HashMap<Long, StickyGridHeadersSimpleAdapterWrapper$HeaderData>)new HashMap<Object, StickyGridHeadersSimpleAdapterWrapper$HeaderData>();
        final ArrayList<StickyGridHeadersSimpleAdapterWrapper$HeaderData> list = new ArrayList<StickyGridHeadersSimpleAdapterWrapper$HeaderData>();
        for (int i = 0; i < stickyGridHeadersSimpleAdapter.getCount(); ++i) {
            final long headerId = stickyGridHeadersSimpleAdapter.getHeaderId(i);
            StickyGridHeadersSimpleAdapterWrapper$HeaderData stickyGridHeadersSimpleAdapterWrapper$HeaderData;
            if ((stickyGridHeadersSimpleAdapterWrapper$HeaderData = hashMap.get(headerId)) == null) {
                stickyGridHeadersSimpleAdapterWrapper$HeaderData = new StickyGridHeadersSimpleAdapterWrapper$HeaderData(this, i);
                list.add(stickyGridHeadersSimpleAdapterWrapper$HeaderData);
            }
            stickyGridHeadersSimpleAdapterWrapper$HeaderData.incrementCount();
            hashMap.put(headerId, stickyGridHeadersSimpleAdapterWrapper$HeaderData);
        }
        return list.toArray(new StickyGridHeadersSimpleAdapterWrapper$HeaderData[list.size()]);
    }
    
    public int getCount() {
        return this.mDelegate.getCount();
    }
    
    public int getCountForHeader(final int n) {
        return this.mHeaders[n].getCount();
    }
    
    public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        return this.mDelegate.getHeaderView(this.mHeaders[n].getRefPosition(), view, viewGroup);
    }
    
    public Object getItem(final int n) {
        return this.mDelegate.getItem(n);
    }
    
    public long getItemId(final int n) {
        return this.mDelegate.getItemId(n);
    }
    
    public int getItemViewType(final int n) {
        return this.mDelegate.getItemViewType(n);
    }
    
    public int getNumHeaders() {
        return this.mHeaders.length;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.mDelegate.getView(n, view, viewGroup);
    }
    
    public int getViewTypeCount() {
        return this.mDelegate.getViewTypeCount();
    }
    
    public boolean hasStableIds() {
        return this.mDelegate.hasStableIds();
    }
}
