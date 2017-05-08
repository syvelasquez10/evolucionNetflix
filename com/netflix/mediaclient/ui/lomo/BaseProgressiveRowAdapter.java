// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.ui.details.DPPrefetchABTestUtils;
import java.util.List;
import android.view.View;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler$FetchCallback;
import com.netflix.mediaclient.servicemgr.interface_.Video;

public abstract class BaseProgressiveRowAdapter<T extends Video> implements FetchVideosHandler$FetchCallback<T>, RowAdapter
{
    protected static final String TAG = "BaseProgressiveRowAdapter";
    private final RowAdapterCallbacks adapterCallbacks;
    private int currDataIndex;
    private boolean hasMoreData;
    private BasicLoMo lomo;
    private final ServiceManager manager;
    protected final BasePaginatedAdapter<T> paginatedAdapter;
    private long requestId;
    private final ObjectRecycler$ViewRecycler viewRecycler;
    
    public BaseProgressiveRowAdapter(final BasePaginatedAdapter<T> paginatedAdapter, final ServiceManager manager, final RowAdapterCallbacks adapterCallbacks, final ObjectRecycler$ViewRecycler viewRecycler) {
        this.adapterCallbacks = adapterCallbacks;
        this.manager = manager;
        this.viewRecycler = viewRecycler;
        this.paginatedAdapter = paginatedAdapter;
        if (Log.isLoggable()) {
            Log.v("BaseProgressiveRowAdapter", "Created progressive adapter of type: " + this.getClass().getSimpleName() + ", paginated adapter type: " + paginatedAdapter.getClass().getSimpleName());
        }
    }
    
    private void fetchMoreData() {
        this.requestId = System.nanoTime();
        this.fetchMoreData(this.currDataIndex, this.currDataIndex + this.getNumVideosToFetchPerBatch(this.currDataIndex) - 1);
    }
    
    private int getNumVideosToFetchPerBatch(final int n) {
        return this.paginatedAdapter.computeNumVideosToFetchPerBatch(n);
    }
    
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
    
    protected void handlePrefetchDPData(final List<T> list, final int n) {
        if (n > 0) {
            Log.d("BaseProgressiveRowAdapter", "prefetch DP only for videos in first page.");
            return;
        }
        DPPrefetchABTestUtils.prefetchDPForLomoRow(this.manager, this.lomo, list, this.paginatedAdapter.computeNumItemsPerPage());
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
        Log.v("BaseProgressiveRowAdapter", "onErrorResponse(), hasMoreData now false");
        this.hasMoreData = false;
        this.adapterCallbacks.notifyParentOfError();
    }
    
    @Override
    public void onNoVideosInResponse() {
        Log.v("BaseProgressiveRowAdapter", "onNoVideosInResponse(), hasMoreData now false");
        this.hasMoreData = false;
        this.adapterCallbacks.notifyParentOfDataSetChange();
    }
    
    @Override
    public final void refreshData(final BasicLoMo lomo, final int listViewPos) {
        if (Log.isLoggable()) {
            final StringBuilder append = new StringBuilder().append(this.getClass().getSimpleName()).append(" refreshing data for: ");
            String title;
            if (lomo == null) {
                title = "n/a";
            }
            else {
                title = lomo.getTitle();
            }
            Log.v("BaseProgressiveRowAdapter", append.append(title).append(", paginated adapter: ").append(this.paginatedAdapter.getClass().getSimpleName()).toString());
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
        final BaseProgressiveRowAdapter$Memento baseProgressiveRowAdapter$Memento = (BaseProgressiveRowAdapter$Memento)o;
        this.invalidateRequestId();
        this.lomo = baseProgressiveRowAdapter$Memento.lomo;
        this.hasMoreData = baseProgressiveRowAdapter$Memento.hasMoreData;
        this.currDataIndex = baseProgressiveRowAdapter$Memento.currDataIndex;
        this.paginatedAdapter.restoreFromMemento(baseProgressiveRowAdapter$Memento.adapterMemento);
        if (Log.isLoggable()) {
            Log.v("BaseProgressiveRowAdapter", "Restored state from memento, lomo: " + this.lomo.getTitle() + ", hasMoreData: " + this.hasMoreData);
        }
    }
    
    @Override
    public BaseProgressiveRowAdapter$Memento saveToMemento() {
        return new BaseProgressiveRowAdapter$Memento(this.lomo, this.hasMoreData, this.currDataIndex, this.paginatedAdapter);
    }
    
    @Override
    public boolean shouldOverlapPages() {
        return true;
    }
    
    @Override
    public void trackPresentation(final int n) {
        this.paginatedAdapter.trackPresentation(this.manager, this.lomo, n, this instanceof ProgressiveGenreVideoAdapter);
    }
    
    @Override
    public void updateDataSet(final List<T> list, final String s, final int n, final int n2) {
        this.hasMoreData = (list.size() == this.getNumVideosToFetchPerBatch(this.currDataIndex));
        if (Log.isLoggable()) {
            Log.v("BaseProgressiveRowAdapter", "Updating data set, videos size: " + list.size() + ", videos per batch: " + this.getNumVideosToFetchPerBatch(this.currDataIndex) + ", hasMoreData: " + this.hasMoreData);
        }
        this.currDataIndex = n2 + 1;
        if (Log.isLoggable()) {
            Log.v("BaseProgressiveRowAdapter", s + ": updated start index to: " + this.currDataIndex);
        }
        this.paginatedAdapter.appendData(list, s, n, n2, this.hasMoreData);
        this.adapterCallbacks.notifyParentOfDataSetChange();
        this.handlePrefetchDPData(list, n);
    }
}
