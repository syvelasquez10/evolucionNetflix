// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.ui.kids.lolomo.KidsCharacterPagerAdapter;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;

class LoMoViewPagerAdapter$RowAdapterSet
{
    public final RowAdapter billboard;
    public final RowAdapter character;
    public final RowAdapter cw;
    public final RowAdapter error;
    public final RowAdapter iq;
    public final RowAdapter loading;
    public final RowAdapter standard;
    
    public LoMoViewPagerAdapter$RowAdapterSet(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
        this.character = new KidsCharacterPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.billboard = new BillboardPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.cw = new CwPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.iq = new IqPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        ProgressiveLoMoPagerAdapter standard;
        if (b) {
            standard = new GenreLoMoPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        }
        else {
            standard = new StandardLoMoPagerAdapter(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler);
        }
        this.standard = standard;
        this.loading = new LoadingViewAdapter(rowAdapterCallbacks, objectRecycler$ViewRecycler);
        this.error = new ErrorViewAdapter(rowAdapterCallbacks);
    }
}
