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
    private HeaderData[] mHeaders;
    
    public StickyGridHeadersSimpleAdapterWrapper(final StickyGridHeadersSimpleAdapter mDelegate) {
        (this.mDelegate = mDelegate).registerDataSetObserver((DataSetObserver)new DataSetObserverExtension());
        this.mHeaders = this.generateHeaderList(mDelegate);
    }
    
    protected HeaderData[] generateHeaderList(final StickyGridHeadersSimpleAdapter stickyGridHeadersSimpleAdapter) {
        final HashMap<Long, HeaderData> hashMap = (HashMap<Long, HeaderData>)new HashMap<Object, HeaderData>();
        final ArrayList<HeaderData> list = new ArrayList<HeaderData>();
        for (int i = 0; i < stickyGridHeadersSimpleAdapter.getCount(); ++i) {
            final long headerId = stickyGridHeadersSimpleAdapter.getHeaderId(i);
            HeaderData headerData;
            if ((headerData = hashMap.get(headerId)) == null) {
                headerData = new HeaderData(i);
                list.add(headerData);
            }
            headerData.incrementCount();
            hashMap.put(headerId, headerData);
        }
        return list.toArray(new HeaderData[list.size()]);
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
    
    private final class DataSetObserverExtension extends DataSetObserver
    {
        public void onChanged() {
            StickyGridHeadersSimpleAdapterWrapper.this.mHeaders = StickyGridHeadersSimpleAdapterWrapper.this.generateHeaderList(StickyGridHeadersSimpleAdapterWrapper.this.mDelegate);
            StickyGridHeadersSimpleAdapterWrapper.this.notifyDataSetChanged();
        }
        
        public void onInvalidated() {
            StickyGridHeadersSimpleAdapterWrapper.this.mHeaders = StickyGridHeadersSimpleAdapterWrapper.this.generateHeaderList(StickyGridHeadersSimpleAdapterWrapper.this.mDelegate);
            StickyGridHeadersSimpleAdapterWrapper.this.notifyDataSetInvalidated();
        }
    }
    
    private class HeaderData
    {
        private int mCount;
        private int mRefPosition;
        
        public HeaderData(final int mRefPosition) {
            this.mRefPosition = mRefPosition;
            this.mCount = 0;
        }
        
        public int getCount() {
            return this.mCount;
        }
        
        public int getRefPosition() {
            return this.mRefPosition;
        }
        
        public void incrementCount() {
            ++this.mCount;
        }
    }
}
