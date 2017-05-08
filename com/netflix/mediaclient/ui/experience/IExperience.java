// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.home.SlidingMenuAdapter;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.ui.lomo.RowAdapterProvider$IRowAdapterProvider;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.RowAdapterCallbacks;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag$ILoLoMoAdapter;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFrag;

public interface IExperience
{
    LoLoMoFrag$ILoLoMoAdapter createLolomoAdapter(final LoLoMoFrag p0, final boolean p1, final String p2);
    
    RowAdapterProvider$IRowAdapterProvider createRowAdapterProvider(final NetflixActivity p0, final RowAdapterCallbacks p1, final ObjectRecycler$ViewRecycler p2, final boolean p3);
    
    SlidingMenuAdapter createSlidingMenuAdapter(final NetflixActivity p0, final DrawerLayout p1);
    
    Class<? extends DetailsActivity> getDetailsClassTypeForVideo(final VideoType p0);
    
    int getLomoRowTitleVisibility(final NetflixActivity p0, final BasicLoMo p1);
}
