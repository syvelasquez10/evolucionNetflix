// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.util.Iterator;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import java.util.LinkedHashMap;
import com.netflix.mediaclient.servicemgr.IBrowseManager;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class DataDumper$VideosCallback extends SimpleManagerCallback
{
    private final LoMo lomo;
    final /* synthetic */ DataDumper this$0;
    private final List<LoMo> todo;
    
    public DataDumper$VideosCallback(final DataDumper this$0, final LoMo lomo, final List<LoMo> todo) {
        this.this$0 = this$0;
        this.lomo = lomo;
        this.todo = todo;
    }
    
    private void handleResponse(final List<? extends Video> list, final Status status) {
        Log.v("DataDumper", "Got videos for lomo: " + this.lomo.getTitle());
        if (status.isError()) {
            Log.e("DataDumper", "Bummer!  Invalid status code during data dump :(");
            Log.e("DataDumper", status.getMessage() + ", code: " + status.getStatusCode());
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
    
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
        if (this.this$0.dumpErrorOccurred) {
            return;
        }
        super.onBBVideosFetched(list, status);
        this.handleResponse(list, status);
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        if (this.this$0.dumpErrorOccurred) {
            return;
        }
        super.onVideosFetched(list, status);
        this.handleResponse(list, status);
    }
}
