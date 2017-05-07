// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.util.Set;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import java.util.Iterator;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import java.util.List;

class DataDumper$2 extends SimpleBrowseAgentCallback
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
            this.this$0.lomoVideos.put(genre, null);
            this.this$0.mBrowseWebClient.fetchGenreVideos(genre, 0, 250, new DataDumper$VideosCallback(genre, list2));
        }
    }
}
