// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.servicemgr.IBrowseManager;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class DataDumper$1 extends SimpleManagerCallback
{
    final /* synthetic */ DataDumper this$0;
    
    DataDumper$1(final DataDumper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        super.onLoMosFetched(list, status);
        Log.v("DataDumper", "lomos fetched, count: " + list.size());
        final ArrayList<LoMo> list2 = new ArrayList<LoMo>(list);
        for (final LoMo loMo : list) {
            Log.v("DataDumper", "Fetching videos for lomo: " + loMo.getTitle());
            this.this$0.lomoVideos.put(loMo, null);
            this.this$0.browseClient.fetchVideos(loMo, 0, 250, false, false, new DataDumper$VideosCallback(loMo, list2));
        }
    }
}
