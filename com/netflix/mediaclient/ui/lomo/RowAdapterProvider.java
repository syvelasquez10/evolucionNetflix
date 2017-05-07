// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.servicemgr.ServiceManager;

class RowAdapterProvider
{
    static RowAdapterProvider$IRowAdapterProvider create(final ServiceManager serviceManager, final RowAdapterCallbacks rowAdapterCallbacks, final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final boolean b) {
        if (BrowseExperience.isKubrickKids()) {
            return new RowAdapterProvider$KubrickKidsRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        if (BrowseExperience.isKubrick()) {
            return new RowAdapterProvider$KubrickRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
        }
        return new RowAdapterProvider$StandardRowAdapterProvider(serviceManager, rowAdapterCallbacks, objectRecycler$ViewRecycler, b);
    }
}
