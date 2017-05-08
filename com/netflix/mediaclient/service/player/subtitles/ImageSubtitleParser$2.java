// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;

class ImageSubtitleParser$2 extends LoggingResourceFetcherCallback
{
    final /* synthetic */ ImageSubtitleParser this$0;
    final /* synthetic */ int val$size;
    
    ImageSubtitleParser$2(final ImageSubtitleParser this$0, final int val$size) {
        this.this$0 = this$0;
        this.val$size = val$size;
    }
    
    @Override
    public void onResourceRawFetched(final String s, final byte[] array, final Status status) {
        super.onResourceRawFetched(s, array, status);
        if (status.isError()) {
            if (Log.isLoggable()) {
                Log.e("nf_subtitles", "Failed to download segment indexes " + status);
            }
            return;
        }
        Log.d("nf_subtitles", "Segment indexes received, parse it...");
        this.this$0.parseSegmentIndexes(array, this.val$size);
        Log.d("nf_subtitles", "Ready to serve subtitles...");
        this.this$0.mReady = true;
        this.this$0.saveFileSafelyToCache("segment.idx", array);
        final int currentSegmentIndex = this.this$0.getCurrentSegmentIndex();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Download segment " + currentSegmentIndex + " on start");
        }
        this.this$0.downloadNextRange(currentSegmentIndex);
    }
}
