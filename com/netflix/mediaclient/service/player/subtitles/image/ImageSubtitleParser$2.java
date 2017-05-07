// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image;

import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import java.util.List;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.io.File;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser$DownloadFailedCallback;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.player.subtitles.BaseSubtitleParser;
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
        final int access$1100 = this.this$0.getCurrentSegmentIndex();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Download segment " + access$1100 + " on start");
        }
        this.this$0.downloadNextRange(access$1100);
    }
}
