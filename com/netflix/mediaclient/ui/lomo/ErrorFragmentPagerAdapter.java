// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.BasicLoMo;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.android.fragment.ErrorView;
import android.view.View;

public class ErrorFragmentPagerAdapter implements ProgressiveAdapterProvider
{
    private final PagerAdapterCallbacks callbacks;
    
    public ErrorFragmentPagerAdapter(final PagerAdapterCallbacks callbacks) {
        this.callbacks = callbacks;
    }
    
    @Override
    public int getCount() {
        return 1;
    }
    
    @Override
    public View getView(final int n) {
        return ErrorView.create(this.callbacks.getActivity().getLayoutInflater(), null);
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
