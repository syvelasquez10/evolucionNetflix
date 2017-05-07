// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.Trackable;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.BasicLoMo;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.servicemgr.Video;

public abstract class BaseProgressivePagerAdapter<T extends Video> implements ProgressiveAdapterProvider, FetchCallback<T>
{
    protected static final String TAG = "BaseProgressivePagerAdapter";
    private final PagerAdapterCallbacks adapterCallbacks;
    private int currDataIndex;
    private boolean hasMoreData;
    private BasicLoMo lomo;
    private final ServiceManager manager;
    private final BasePaginatedAdapter<T> paginatedAdapter;
    private long requestId;
    private final ViewRecycler viewRecycler;
    
    public BaseProgressivePagerAdapter(final ServiceManager manager, final PagerAdapterCallbacks adapterCallbacks, final ViewRecycler viewRecycler) {
        this.paginatedAdapter = this.createPaginatedAdapter(manager.getContext());
        this.adapterCallbacks = adapterCallbacks;
        this.manager = manager;
        this.viewRecycler = viewRecycler;
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
        return this.paginatedAdapter.computeNumVideosToFetchPerBatch(this.manager.getContext());
    }
    
    @Override
    public long getRequestId() {
        return this.requestId;
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
    public void restoreFromMemento(final Memento memento) {
        this.invalidateRequestId();
        this.lomo = memento.lomo;
        this.hasMoreData = memento.hasMoreData;
        this.currDataIndex = memento.currDataIndex;
        this.paginatedAdapter.restoreFromMemento(memento.adapterMemento);
    }
    
    @Override
    public Memento saveToMemento() {
        return new Memento(this.lomo, this.hasMoreData, this.currDataIndex, this.paginatedAdapter);
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
    
    static class Memento
    {
        final BasePaginatedAdapter.Memento adapterMemento;
        final int currDataIndex;
        final boolean hasMoreData;
        final BasicLoMo lomo;
        
        protected Memento(final BasicLoMo lomo, final boolean hasMoreData, final int currDataIndex, final BasePaginatedAdapter<?> basePaginatedAdapter) {
            this.lomo = lomo;
            this.hasMoreData = hasMoreData;
            this.currDataIndex = currDataIndex;
            this.adapterMemento = (BasePaginatedAdapter.Memento)basePaginatedAdapter.saveToMemento();
        }
        
        @Override
        public String toString() {
            final StringBuilder append = new StringBuilder().append("lomo: ");
            String title;
            if (this.lomo == null) {
                title = "no lomo";
            }
            else {
                title = this.lomo.getTitle();
            }
            return append.append(title).append(", hasMoreData: ").append(this.hasMoreData).append(", currDataIndex: ").append(this.currDataIndex).append(", adapter: ").append(this.adapterMemento).toString();
        }
    }
}
