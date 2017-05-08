// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleFailure;
import com.netflix.mediaclient.Log;

class StreamingTextSubtitleParser$2 implements Runnable
{
    final /* synthetic */ StreamingTextSubtitleParser this$0;
    final /* synthetic */ String[] val$nameServers;
    final /* synthetic */ String val$requestedUrl;
    final /* synthetic */ byte[] val$responseData;
    
    StreamingTextSubtitleParser$2(final StreamingTextSubtitleParser this$0, final byte[] val$responseData, final String val$requestedUrl, final String[] val$nameServers) {
        this.this$0 = this$0;
        this.val$responseData = val$responseData;
        this.val$requestedUrl = val$requestedUrl;
        this.val$nameServers = val$nameServers;
    }
    
    @Override
    public void run() {
        Log.d("nf_subtitles", "Subtitles metadata update started.");
        Label_0054: {
            if (!Log.isLoggable()) {
                break Label_0054;
            }
            final StringBuilder append = new StringBuilder().append("Resource fetched as ");
            Label_0074: {
                if (this.val$responseData == null) {
                    break Label_0074;
                }
                int length = this.val$responseData.length;
            Label_0065_Outer:
                while (true) {
                    Log.d("nf_subtitles", append.append(length).toString());
                    while (true) {
                        try {
                            this.this$0.injectContent(this.val$responseData);
                            Log.d("nf_subtitles", "Subtitles metadata updated.");
                            return;
                            length = -1;
                            continue Label_0065_Outer;
                        }
                        catch (Throwable t) {
                            Log.e("nf_subtitles", "We failed to parse subtitle metadata", t);
                            this.this$0.onError(this.val$requestedUrl, this.val$nameServers, ISubtitleDef$SubtitleFailure.parsing, null);
                            this.this$0.reportHandledException(new RuntimeException("We failed to parse subtitle metadata", t));
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
        }
    }
}
