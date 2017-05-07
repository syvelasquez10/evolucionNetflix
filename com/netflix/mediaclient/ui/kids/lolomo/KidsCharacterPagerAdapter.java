// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;
import android.content.Context;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.ui.lomo.RowAdapterCallbacks;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.lomo.StandardLoMoPagerAdapter;

public class KidsCharacterPagerAdapter extends StandardLoMoPagerAdapter
{
    public KidsCharacterPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ViewRecycler viewRecycler) {
        super(serviceManager, rowAdapterCallbacks, viewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<Video> createPaginatedAdapter(final Context context) {
        return new KidsPaginatedCharacterAdapter(context);
    }
}
