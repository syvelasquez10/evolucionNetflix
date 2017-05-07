// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.BasicLoMo;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.android.fragment.LoadingView;
import android.view.View;
import com.netflix.mediaclient.android.widget.ViewRecycler;

public class LoadingFragmentPagerAdapter implements ProgressiveAdapterProvider
{
    private static final String TAG = "LoadingFragmentPagerAdapter";
    private final PagerAdapterCallbacks callbacks;
    private final ViewRecycler viewRecycler;
    
    public LoadingFragmentPagerAdapter(final PagerAdapterCallbacks callbacks, final ViewRecycler viewRecycler) {
        this.callbacks = callbacks;
        this.viewRecycler = viewRecycler;
    }
    
    @Override
    public int getCount() {
        return 1;
    }
    
    @Override
    public View getView(final int n) {
        final View pop = this.viewRecycler.pop(LoadingView.class);
        if (pop != null) {
            return pop;
        }
        final LoadingView loadingView = new LoadingView((Context)this.callbacks.getActivity());
        Log.v("LoadingFragmentPagerAdapter", "Creating view: " + loadingView);
        return (View)loadingView;
    }
    
    @Override
    public boolean hasMoreData() {
        return false;
    }
    
    @Override
    public void invalidateRequestId() {
    }
    
    @Override
    public void refreshData(final BasicLoMo basicLoMo, final int n) {
    }
    
    @Override
    public void restoreFromMemento(final BaseProgressivePagerAdapter.Memento memento) {
    }
    
    @Override
    public BaseProgressivePagerAdapter.Memento saveToMemento() {
        return null;
    }
    
    @Override
    public void trackPresentation(final int n) {
    }
}
