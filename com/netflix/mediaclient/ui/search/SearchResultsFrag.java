// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.widget.AdapterView;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.ui.kids.search.KidsSearchResultView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersBaseAdapter;
import android.widget.BaseAdapter;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.SearchResults;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import android.util.SparseIntArray;
import android.util.SparseArray;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class SearchResultsFrag extends NetflixFrag
{
    private static final String TAG = "SearchResultsFrag";
    private static final SparseArray<SparseIntArray> numColsTable;
    private SearchResultsAdapter adapter;
    private StickyGridHeadersGridView gridView;
    private SearchResults results;
    
    static {
        numColsTable = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1, 1);
        sparseIntArray.put(2, 1);
        sparseIntArray.put(3, 2);
        sparseIntArray.put(4, 2);
        SearchResultsFrag.numColsTable.put(1, (Object)sparseIntArray);
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1, 2);
        sparseIntArray2.put(2, 2);
        sparseIntArray2.put(3, 3);
        sparseIntArray2.put(4, 3);
        SearchResultsFrag.numColsTable.put(2, (Object)sparseIntArray2);
    }
    
    public static SearchResultsFrag create() {
        return new SearchResultsFrag();
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130903153, (ViewGroup)null);
        this.adapter = new SearchResultsAdapter();
        (this.gridView = (StickyGridHeadersGridView)inflate.findViewById(2131165558)).setAdapter((ListAdapter)this.adapter);
        this.gridView.setOnItemClickListener((AdapterView$OnItemClickListener)this.adapter);
        this.gridView.setAreHeadersSticky(false);
        this.gridView.setNumColumns(((SparseIntArray)SearchResultsFrag.numColsTable.get(DeviceUtils.getBasicScreenOrientation((Context)this.getActivity()))).get(DeviceUtils.getScreenSizeCategory((Context)this.getActivity())));
        return inflate;
    }
    
    public void update(final SearchResults results) {
        Log.v("SearchResultsFrag", "Updating...");
        this.results = results;
        this.adapter.notifyDataSetChanged();
        this.gridView.smoothScrollToPosition(0);
    }
    
    private class SearchResultsAdapter extends BaseAdapter implements AdapterView$OnItemClickListener, StickyGridHeadersBaseAdapter
    {
        private final View DUMMY_VIEW;
        
        public SearchResultsAdapter() {
            this.DUMMY_VIEW = (View)new TextView((Context)SearchResultsFrag.this.getActivity());
        }
        
        public int getCount() {
            if (SearchResultsFrag.this.results == null) {
                return 0;
            }
            return SearchResultsFrag.this.results.getNumResults();
        }
        
        public int getCountForHeader(final int n) {
            if (SearchResultsFrag.this.results == null) {
                return 0;
            }
            return SearchResultsFrag.this.results.getNumResultsForSection(n);
        }
        
        public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
            if (SearchResultsFrag.this.results == null) {
                return this.DUMMY_VIEW;
            }
            View inflate;
            if ((inflate = view) == null) {
                inflate = SearchResultsFrag.this.getActivity().getLayoutInflater().inflate(2130903154, (ViewGroup)null);
            }
            ((TextView)inflate.findViewById(2131165559)).setText(SearchResultsFrag.this.results.getSectionTitle((Context)SearchResultsFrag.this.getActivity(), n));
            return inflate;
        }
        
        public Object getItem(final int n) {
            if (SearchResultsFrag.this.results == null) {
                return null;
            }
            return SearchResultsFrag.this.results.getResult(n);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public int getNumHeaders() {
            if (SearchResultsFrag.this.results == null) {
                return 0;
            }
            return SearchResultsFrag.this.results.getNumSections();
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            Object o = null;
            Label_0040: {
                if (view != null) {
                    o = view;
                    if (view instanceof SearchResultView) {
                        break Label_0040;
                    }
                }
                final NetflixActivity netflixActivity = (NetflixActivity)SearchResultsFrag.this.getActivity();
                if (netflixActivity.isForKids()) {
                    o = new KidsSearchResultView((Context)netflixActivity);
                }
                else {
                    o = new SearchResultView((Context)netflixActivity);
                }
            }
            ((SearchResultView)o).update(this.getItem(n), new PlayContextImp(SearchResultsFrag.this.results, n));
            return (View)o;
        }
        
        public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
            view.performClick();
        }
    }
}
