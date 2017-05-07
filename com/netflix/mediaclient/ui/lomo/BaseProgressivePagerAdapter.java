// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import java.util.List;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler$FetchCallback;
import com.netflix.mediaclient.servicemgr.model.Video;

public abstract class BaseProgressivePagerAdapter<T extends Video> implements FetchVideosHandler$FetchCallback<T>, RowAdapter
{
    protected static final String TAG = "BaseProgressivePagerAdapter";
    private final RowAdapterCallbacks adapterCallbacks;
    private int currDataIndex;
    private boolean hasMoreData;
    private BasicLoMo lomo;
    private final ServiceManager manager;
    private final BasePaginatedAdapter<T> paginatedAdapter;
    private long requestId;
    private final ObjectRecycler$ViewRecycler viewRecycler;
    
    public BaseProgressivePagerAdapter(final ServiceManager manager, final RowAdapterCallbacks adapterCallbacks, final ObjectRecycler$ViewRecycler viewRecycler) {
        this.adapterCallbacks = adapterCallbacks;
        this.manager = manager;
        this.viewRecycler = viewRecycler;
        this.paginatedAdapter = this.createPaginatedAdapter((Context)manager.getActivity());
    }
    
    private void fetchMoreData() {
        this.requestId = System.nanoTime();
        this.fetchMoreData(this.currDataIndex, this.currDataIndex + this.getNumVideosToFetchPerBatch() - 1);
    }
    
    protected abstract BasePaginatedAdapter<T> createPaginatedAdapter(final Context p0);
    
    protected abstract void fetchMoreData(final int p0, final int p1);
    
    @Override
    public int getCount() {
        return this.paginatedAdapter.getCount();
    }
    
    protected BasicLoMo getLoMo() {
        return this.lomo;
    }
    
    protected ServiceManager getManager() {
        return this.manager;
    }
    
    protected int getNumVideosToFetchPerBatch() {
        return this.paginatedAdapter.computeNumVideosToFetchPerBatch((Context)this.manager.getActivity());
    }
    
    @Override
    public long getRequestId() {
        return this.requestId;
    }
    
    @Override
    public int getRowHeightInPx() {
        return this.paginatedAdapter.getRowHeightInPx();
    }
    
    @Override
    public View getView(final int n) {
        if (this.hasMoreData() && this.paginatedAdapter.isLastItem(n)) {
            this.fetchMoreData();
        }
        return this.paginatedAdapter.getView(this.viewRecycler, this.lomo, n);
    }
    
    @Override
    public boolean hasMoreData() {
        return this.hasMoreData;
    }
    
    @Override
    public void invalidateRequestId() {
        this.requestId = -1L;
    }
    
    @Override
    public void onErrorResponse() {
        this.hasMoreData = false;
        this.adapterCallbacks.notifyParentOfError();
    }
    
    @Override
    public void onNoVideosInResponse() {
        this.hasMoreData = false;
        this.adapterCallbacks.notifyParentOfDataSetChange();
    }
    
    @Override
    public final void refreshData(final BasicLoMo lomo, final int listViewPos) {
        if (Log.isLoggable("BaseProgressivePagerAdapter", 2)) {
            final StringBuilder append = new StringBuilder().append("Refreshing data for: ");
            String title;
            if (lomo == null) {
                title = "n/a";
            }
            else {
                title = lomo.getTitle();
            }
            Log.v("BaseProgressivePagerAdapter", append.append(title).append(", ").append(this.hashCode()).toString());
        }
        this.lomo = lomo;
        this.paginatedAdapter.clearData();
        this.paginatedAdapter.setListViewPos(listViewPos);
        this.currDataIndex = 0;
        this.hasMoreData = false;
        this.fetchMoreData();
    }
    
    @Override
    public void restoreFromMemento(final Object o) {
        final BaseProgressivePagerAdapter$Memento baseProgressivePagerAdapter$Memento = (BaseProgressivePagerAdapter$Memento)o;
        this.invalidateRequestId();
        this.lomo = baseProgressivePagerAdapter$Memento.lomo;
        this.hasMoreData = baseProgressivePagerAdapter$Memento.hasMoreData;
        this.currDataIndex = baseProgressivePagerAdapter$Memento.currDataIndex;
        this.paginatedAdapter.restoreFromMemento(baseProgressivePagerAdapter$Memento.adapterMemento);
    }
    
    @Override
    public BaseProgressivePagerAdapter$Memento saveToMemento() {
        return new BaseProgressivePagerAdapter$Memento(this.lomo, this.hasMoreData, this.currDataIndex, this.paginatedAdapter);
    }
    
    @Override
    public void trackPresentation(final int n) {
        this.paginatedAdapter.trackPresentation(this.manager, this.lomo, n, this instanceof GenreLoMoPagerAdapter);
    }
    
    @Override
    public void updateDataSet(final List<T> list, final String s, final int n, final int n2) {
        this.currDataIndex = n2 + 1;
        if (Log.isLoggable("BaseProgressivePagerAdapter", 2)) {
            Log.v("BaseProgressivePagerAdapter", s + ": updated start index to: " + this.currDataIndex);
        }
        this.hasMoreData = (list.size() == this.getNumVideosToFetchPerBatch());
        this.paginatedAdapter.appendData(list, s, n, n2);
        this.adapterCallbacks.notifyParentOfDataSetChange();
    }
}
