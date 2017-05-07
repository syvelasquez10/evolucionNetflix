// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.widget.ListAdapter;

public interface LoLoMoFrag$ILoLoMoAdapter extends ListAdapter, LoadingStatus, ManagerStatusListener, StickyListHeadersAdapter
{
    void onDestroyView();
    
    void onPause();
    
    void onResume();
    
    void refreshData();
}
