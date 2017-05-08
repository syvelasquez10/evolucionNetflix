// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import java.util.Iterator;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

final class OfflineUiHelper$1 extends LoggingManagerCallback
{
    final /* synthetic */ NetflixActivity val$activity;
    
    OfflineUiHelper$1(final String s, final NetflixActivity val$activity) {
        this.val$activity = val$activity;
        super(s);
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final Status status) {
        super.onGenreListsFetched(list, status);
        if (status.isError()) {
            Log.w("offlineUiHelper", "Invalid status code for genres fetch");
        }
        else if (list != null && list.size() > 1) {
            for (final GenreList list2 : list) {
                if (list2.getId().compareToIgnoreCase("1647397") == 0) {
                    HomeActivity.showGenreList(this.val$activity, list2);
                }
            }
        }
    }
}
