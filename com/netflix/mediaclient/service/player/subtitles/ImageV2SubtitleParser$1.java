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
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.net.DnsManager;
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
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;

class ImageV2SubtitleParser$1 extends LoggingResourceFetcherCallback
{
    final /* synthetic */ ImageV2SubtitleParser this$0;
    final /* synthetic */ String[] val$nameServers;
    
    ImageV2SubtitleParser$1(final ImageV2SubtitleParser this$0, final String[] val$nameServers) {
        this.this$0 = this$0;
        this.val$nameServers = val$nameServers;
    }
    
    @Override
    public void onResourceRawFetched(final String s, final byte[] array, final Status status) {
        super.onResourceRawFetched(s, array, status);
        if (status.isError()) {
            if (Log.isLoggable()) {
                Log.e("nf_subtitles_imv2", "Failed to download master index " + status);
            }
            this.this$0.onError(s, this.val$nameServers, IMedia$SubtitleFailure.download, status);
            return;
        }
        if (this.this$0.parseMasterIndexContainer(array)) {
            this.this$0.handleDownloadSegmentIndexes();
            return;
        }
        this.this$0.onError(s, this.val$nameServers, IMedia$SubtitleFailure.parsing, null);
    }
}
