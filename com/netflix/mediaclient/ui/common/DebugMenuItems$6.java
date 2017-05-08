// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.servicemgr.IBrowseManager;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.view.MenuItem;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$6 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    final /* synthetic */ HomeActivity val$homeActivity;
    
    DebugMenuItems$6(final DebugMenuItems this$0, final HomeActivity val$homeActivity) {
        this.this$0 = this$0;
        this.val$homeActivity = val$homeActivity;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        final GenreList genre = this.val$homeActivity.getGenre();
        final IBrowseManager browse = this.val$homeActivity.getServiceManager().getBrowse();
        final String genreId = this.val$homeActivity.getGenreId();
        String title;
        if (genre == null) {
            title = "Home Lolomo";
        }
        else {
            title = genre.getTitle();
        }
        browse.dumpHomeLoLoMosAndVideos(genreId, title);
        return true;
    }
}
