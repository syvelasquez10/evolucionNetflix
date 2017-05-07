// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.servicemgr.IBrowseManager;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.genre.Genre;
import java.util.List;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class DataDumper$2 extends SimpleManagerCallback
{
    final /* synthetic */ DataDumper this$0;
    
    DataDumper$2(final DataDumper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final Status status) {
        super.onGenresFetched(list, status);
        Log.v("DataDumper", "genres fetched, count: " + list.size());
        final ArrayList<LoMo> list2 = new ArrayList<LoMo>(list);
        for (final Genre genre : list) {
            Log.v("DataDumper", "Fetching videos for lomo: " + genre.getTitle());
            this.this$0.lomoVideos.put(genre, null);
            this.this$0.browseClient.fetchGenreVideos(genre, 0, 250, false, new DataDumper$VideosCallback(genre, list2));
        }
    }
}
