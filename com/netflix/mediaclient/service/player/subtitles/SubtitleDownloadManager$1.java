// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleFailure;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitleDownloadRetryPolicy;
import com.netflix.mediaclient.util.net.ExponentialBackOff;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.event.nrdp.media.SubtitleData;
import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.util.net.BackOff;
import com.netflix.mediaclient.Log;

class SubtitleDownloadManager$1 implements Runnable
{
    final /* synthetic */ SubtitleDownloadManager this$0;
    
    SubtitleDownloadManager$1(final SubtitleDownloadManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("nf_subtitles", "Execute retry...");
        this.this$0.createParserAndStartDownload();
    }
}
