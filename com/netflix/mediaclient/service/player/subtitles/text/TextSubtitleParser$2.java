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
import java.util.ArrayList;
import java.util.HashMap;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import java.util.List;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import java.util.Map;
import com.netflix.mediaclient.service.player.subtitles.BaseSubtitleParser;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.Log;

class TextSubtitleParser$2 implements Runnable
{
    final /* synthetic */ TextSubtitleParser this$0;
    final /* synthetic */ String val$localUrl;
    
    TextSubtitleParser$2(final TextSubtitleParser this$0, final String val$localUrl) {
        this.this$0 = this$0;
        this.val$localUrl = val$localUrl;
    }
    
    @Override
    public void run() {
        Log.d("nf_subtitles", "Subtitles metadata update started.");
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Resource fetched to " + this.val$localUrl);
        }
        final String fileName = FileUtils.extractFileName(this.val$localUrl);
        while (true) {
            try {
                final String fileWithUTF8Encoding = FileUtils.readFileWithUTF8Encoding(fileName);
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "Downloaded subtitles metadata:\n" + fileWithUTF8Encoding);
                }
                this.this$0.parse(fileWithUTF8Encoding);
                if (this.this$0.mPlayer.getPlayerFileCache().moveFile(this.this$0.mCacheName, fileName, "manifest_ttml.xml")) {
                    Log.d("nf_subtitles", "Text subtitle xml moved to cache.");
                }
                else {
                    Log.e("nf_subtitles", "Failed to move text subtitle xml to cache!");
                    this.this$0.mPlayer.getResourceFetcher().deleteLocalResource(this.val$localUrl);
                }
                Log.d("nf_subtitles", "Subtitles metadata updated.");
                return;
            }
            catch (Throwable t) {
                Log.e("nf_subtitles", "We failed to parse subtitle metadata", t);
                this.this$0.mPlayer.reportHandledException(new RuntimeException("We failed to parse subtitle metadata", t));
                continue;
            }
            continue;
        }
    }
}
