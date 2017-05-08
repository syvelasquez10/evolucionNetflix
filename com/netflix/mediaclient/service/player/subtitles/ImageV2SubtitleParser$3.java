// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.subtitles.image.v2.SegmentIndex;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;

class ImageV2SubtitleParser$3 extends LoggingResourceFetcherCallback
{
    final /* synthetic */ ImageV2SubtitleParser this$0;
    final /* synthetic */ String val$key;
    final /* synthetic */ SegmentIndex val$si;
    final /* synthetic */ String val$url;
    
    ImageV2SubtitleParser$3(final ImageV2SubtitleParser this$0, final SegmentIndex val$si, final String val$url, final String val$key) {
        this.this$0 = this$0;
        this.val$si = val$si;
        this.val$url = val$url;
        this.val$key = val$key;
    }
    
    @Override
    public void onResourceRawFetched(final String s, final byte[] array, final Status status) {
        super.onResourceRawFetched(s, array, status);
        if (status.isError()) {
            if (Log.isLoggable()) {
                Log.e("nf_subtitles_imv2", "Failed to download segment" + this.val$si);
            }
            return;
        }
        this.this$0.parseSegment(array, this.val$si, this.val$url, this.val$key);
    }
}
