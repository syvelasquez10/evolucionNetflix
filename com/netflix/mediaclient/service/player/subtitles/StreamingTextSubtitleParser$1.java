// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleFailure;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;

class StreamingTextSubtitleParser$1 extends LoggingResourceFetcherCallback
{
    final /* synthetic */ StreamingTextSubtitleParser this$0;
    final /* synthetic */ String[] val$nameServers;
    final /* synthetic */ String val$url;
    
    StreamingTextSubtitleParser$1(final StreamingTextSubtitleParser this$0, final String val$url, final String[] val$nameServers) {
        this.this$0 = this$0;
        this.val$url = val$url;
        this.val$nameServers = val$nameServers;
    }
    
    @Override
    public void onResourceRawFetched(final String s, final byte[] array, final Status status) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Subtitles onResourceFetched for expected URL " + this.val$url + ", FOUND URL " + s + ", status: " + status);
        }
        if (status.isSucces()) {
            this.this$0.handleDownloadedSubtitleData(array, this.val$url, this.val$nameServers);
            return;
        }
        if (Log.isLoggable()) {
            Log.e("nf_subtitles", "Failed to download subtitle metadata, status " + status);
        }
        this.this$0.onError(s, this.val$nameServers, ISubtitleDef$SubtitleFailure.download, status);
    }
}
