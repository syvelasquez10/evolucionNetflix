// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import android.content.Context;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.RowAdapterCallbacks;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.StandardLoMoPagerAdapter;

public class KubrickGalleryPagerAdapter extends StandardLoMoPagerAdapter<KubrickVideo>
{
    public KubrickGalleryPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<KubrickVideo> createPaginatedAdapter(final NetflixActivity netflixActivity) {
        return new KubrickPaginatedGalleryAdapter((Context)netflixActivity);
    }
}
