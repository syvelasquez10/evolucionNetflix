// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler$FetchCallback;
import com.netflix.mediaclient.servicemgr.FetchVideosHandler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public class CwPagerAdapter extends ProgressiveCwPagerAdapter
{
    public static final String CW_CACHE_TAG = "CW";
    
    public CwPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected void fetchMoreData(final int n, int n2) {
        if (this.getManager() != null && this.getManager().getActivity() != null && this.getManager().getActivity().isForKids()) {
            final int n3 = 2;
            Log.d("BaseProgressivePagerAdapter", "limiting index for Kids CW row, max: " + 2);
            if (n2 > 2) {
                n2 = n3;
            }
        }
        while (true) {
            Log.v("BaseProgressivePagerAdapter", "fetching for continue watching, start: " + n + ", end: " + n2);
            this.getManager().getBrowse().fetchCWVideos(n, n2, new FetchVideosHandler<Object>("BaseProgressivePagerAdapter", this, "CW", n, n2));
            return;
            continue;
        }
    }
}
