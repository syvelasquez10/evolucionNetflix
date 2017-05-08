// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

public interface SlidingMenuAdapter extends ManagerStatusListener
{
    boolean canLoadNotifications();
    
    void onActivityPause(final NetflixActivity p0);
    
    void onActivityResume(final NetflixActivity p0);
    
    void refresh();
    
    void setSelectedGenre(final GenreList p0);
}
