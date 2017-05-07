// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public class CwPagerAdapter extends ProgressiveCwPagerAdapter
{
    public static final String CW_CACHE_TAG = "CW";
    
    public CwPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ViewRecycler viewRecycler) {
        super(serviceManager, rowAdapterCallbacks, viewRecycler);
    }
    
    @Override
    protected void fetchMoreData(final int n, final int n2) {
        int n3 = n2;
        if (this.getManager() != null) {
            n3 = n2;
            if (this.getManager().getActivity() != null) {
                n3 = n2;
                if (this.getManager().getActivity().isForKids()) {
                    Log.d("BaseProgressivePagerAdapter", "limiting index for Kids CW row, max: " + 2);
                    if ((n3 = n2) > 2) {
                        n3 = 2;
                    }
                }
            }
        }
        Log.v("BaseProgressivePagerAdapter", "fetching for continue watching, start: " + n + ", end: " + n3);
        this.getManager().getBrowse().fetchCWVideos(n, n3, new FetchVideosHandler<Object>("BaseProgressivePagerAdapter", (FetchVideosHandler.FetchCallback<Object>)this, "CW", n, n3));
    }
}
