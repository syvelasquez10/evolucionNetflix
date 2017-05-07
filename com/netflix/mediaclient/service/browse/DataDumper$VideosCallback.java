// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.util.Set;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMo;

class DataDumper$VideosCallback extends SimpleBrowseAgentCallback
{
    private final LoMo lomo;
    final /* synthetic */ DataDumper this$0;
    private final List<LoMo> todo;
    
    public DataDumper$VideosCallback(final DataDumper this$0, final LoMo lomo, final List<LoMo> todo) {
        this.this$0 = this$0;
        this.lomo = lomo;
        this.todo = todo;
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        if (this.this$0.dumpErrorOccurred) {
            return;
        }
        super.onVideosFetched(list, status);
        if (status.isError()) {
            Log.e("DataDumper", "Bummer!  Invalid status code during data dump :(");
            this.this$0.dumpErrorOccurred = true;
            this.todo.clear();
        }
        else {
            this.this$0.lomoVideos.put(this.lomo, list);
            this.todo.remove(this.lomo);
        }
        if (this.todo.size() == 0) {
            Log.v("DataDumper", "--LoMo data collection complete--");
            this.this$0.handleDataLoadCompleted(this.this$0.lomoVideos);
            return;
        }
        Log.v("DataDumper", "Remaining request count: " + this.todo.size());
    }
}
