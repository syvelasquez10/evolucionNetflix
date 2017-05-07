// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

public interface SlidingMenuAdapter extends ManagerStatusListener
{
    boolean canLoadNotifications();
    
    void onActivityResume();
    
    void refresh();
    
    void setSelectedGenre(final GenreList p0);
}
