// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import java.util.Map;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.Log;

class BaseNflxHandler$1 implements Runnable
{
    final /* synthetic */ BaseNflxHandler this$0;
    final /* synthetic */ String val$tinyUrl;
    
    BaseNflxHandler$1(final BaseNflxHandler this$0, final String val$tinyUrl) {
        this.this$0 = this$0;
        this.val$tinyUrl = val$tinyUrl;
    }
    
    @Override
    public void run() {
        Log.d("NflxHandler", "Resolving tiny URL in background");
        this.this$0.handleTinyUrl(this.val$tinyUrl, NflxProtocolUtils.extractJustUuid((String)this.this$0.mParamsMap.get("targetid")), NflxProtocolUtils.getTrackId((Map)this.this$0.mParamsMap));
    }
}
