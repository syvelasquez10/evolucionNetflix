// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import android.view.View;

interface RowAdapter
{
    int getCount();
    
    int getRowHeightInPx();
    
    View getView(final int p0);
    
    boolean hasMoreData();
    
    void invalidateRequestId();
    
    void refreshData(final BasicLoMo p0, final int p1);
    
    void restoreFromMemento(final Object p0);
    
    Object saveToMemento();
    
    boolean shouldOverlapPages();
    
    void trackPresentation(final int p0);
}
