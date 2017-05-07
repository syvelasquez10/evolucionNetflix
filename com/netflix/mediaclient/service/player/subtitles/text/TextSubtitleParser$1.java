// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.text;

import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import com.netflix.mediaclient.util.XmlDomUtils;
import com.netflix.mediaclient.util.StringUtils;
import java.io.File;
import com.netflix.mediaclient.util.FileUtils;
import java.util.ArrayList;
import java.util.HashMap;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import java.util.List;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import java.util.Map;
import com.netflix.mediaclient.service.player.subtitles.BaseSubtitleParser;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;

class TextSubtitleParser$1 extends LoggingResourceFetcherCallback
{
    final /* synthetic */ TextSubtitleParser this$0;
    final /* synthetic */ String val$url;
    
    TextSubtitleParser$1(final TextSubtitleParser this$0, final String val$url) {
        this.this$0 = this$0;
        this.val$url = val$url;
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Subtitles onResourceFetched for expected URL " + this.val$url + ", FOUND URL " + s + ", status: " + status + ", local URL: " + s2);
        }
        if (status.isSucces()) {
            this.this$0.handleDownloadedSubtitleData(s2, this.val$url);
            return;
        }
        if (Log.isLoggable()) {
            Log.e("nf_subtitles", "Failed to download subtitle metadata, status " + status);
        }
        this.this$0.mPlayer.reportFailedToDownloadSubtitleMetadata();
    }
}
