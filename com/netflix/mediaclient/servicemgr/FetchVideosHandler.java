// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;

public class FetchVideosHandler<T> extends LoggingManagerCallback
{
    private final FetchCallback<T> callback;
    private final int end;
    private final long requestId;
    private final int start;
    private final String title;
    
    public FetchVideosHandler(final String s, final FetchCallback<T> callback, final String title, final int start, final int end) {
        super(s);
        this.callback = callback;
        this.requestId = callback.getRequestId();
        this.title = title;
        this.start = start;
        this.end = end;
    }
    
    private void handleResponse(final List<? extends Video> list, final Status status) {
        if (this.requestId != this.callback.getRequestId()) {
            Log.v(this.tag, "Ignoring stale onVideosFetched callback");
            return;
        }
        if (status.isError()) {
            Log.w(this.tag, "Invalid status code");
            this.callback.onErrorResponse();
            return;
        }
        if (list == null || list.size() <= 0) {
            Log.d(this.tag, "No videos in response");
            this.callback.onNoVideosInResponse();
            return;
        }
        this.callback.updateDataSet((List<T>)list, this.title, this.start, this.end);
    }
    
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
        super.onBBVideosFetched(list, status);
        this.handleResponse(list, status);
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
        super.onCWVideosFetched(list, status);
        this.handleResponse(list, status);
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        super.onVideosFetched(list, status);
        this.handleResponse(list, status);
    }
    
    public interface FetchCallback<T>
    {
        long getRequestId();
        
        void onErrorResponse();
        
        void onNoVideosInResponse();
        
        void updateDataSet(final List<T> p0, final String p1, final int p2, final int p3);
    }
}
