// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.ErrorView;
import android.view.View;

public class ErrorViewAdapter implements RowAdapter
{
    private final RowAdapterCallbacks callbacks;
    
    public ErrorViewAdapter(final RowAdapterCallbacks callbacks) {
        this.callbacks = callbacks;
    }
    
    @Override
    public int getCount() {
        return 1;
    }
    
    @Override
    public int getRowHeightInPx() {
        return -2;
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
    public void restoreFromMemento(final Object o) {
    }
    
    @Override
    public Object saveToMemento() {
        return null;
    }
    
    @Override
    public boolean shouldOverlapPages() {
        return true;
    }
    
    @Override
    public void trackPresentation(final int n) {
    }
}
