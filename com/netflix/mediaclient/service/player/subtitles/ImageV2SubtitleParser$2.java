// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import java.util.List;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.subtitles.image.v2.BoxHeader;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import com.netflix.mediaclient.service.player.subtitles.image.ImageDescriptor;
import java.io.File;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.player.subtitles.image.v2.SegmentEncryptionInfo$ImageEncryptionInfo;
import com.netflix.mediaclient.service.player.subtitles.image.v2.ImageDecryptorFactory;
import com.netflix.mediaclient.service.player.subtitles.image.v2.SegmentIndex;
import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.player.subtitles.image.v2.ISCSegment;
import com.netflix.mediaclient.service.player.subtitles.image.v2.ISCTrack;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleMetadata;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;

class ImageV2SubtitleParser$2 extends LoggingResourceFetcherCallback
{
    final /* synthetic */ ImageV2SubtitleParser this$0;
    final /* synthetic */ int val$size;
    
    ImageV2SubtitleParser$2(final ImageV2SubtitleParser this$0, final int val$size) {
        this.this$0 = this$0;
        this.val$size = val$size;
    }
    
    @Override
    public void onResourceRawFetched(final String s, final byte[] array, final Status status) {
        super.onResourceRawFetched(s, array, status);
        if (status.isError()) {
            if (Log.isLoggable()) {
                Log.e("nf_subtitles_imv2", "Failed to download segment indexes " + status);
            }
            return;
        }
        Log.d("nf_subtitles_imv2", "Segment indexes received, parse it...");
        this.this$0.parseSegmentIndexes(array, this.val$size);
        Log.d("nf_subtitles_imv2", "Ready to serve subtitles...");
        this.this$0.mReady = true;
        this.this$0.saveFileSafelyToCache("segment.idx", array);
        final int access$300 = this.this$0.getCurrentSegmentIndex();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_imv2", "Download segment " + access$300 + " on start");
        }
        this.this$0.downloadNextRange(access$300);
    }
}
