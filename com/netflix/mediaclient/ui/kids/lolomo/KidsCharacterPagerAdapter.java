// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.RowAdapterCallbacks;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.lomo.StandardLoMoPagerAdapter;

public class KidsCharacterPagerAdapter extends StandardLoMoPagerAdapter
{
    public KidsCharacterPagerAdapter(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler) {
        super(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
    }
    
    @Override
    protected BasePaginatedAdapter<Video> createPaginatedAdapter(final NetflixActivity netflixActivity) {
        return new KidsPaginatedCharacterAdapter((Context)netflixActivity);
    }
}
