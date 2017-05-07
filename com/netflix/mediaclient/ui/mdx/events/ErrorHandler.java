// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.ui.mdx.RemotePlaybackListener;

public final class ErrorHandler extends EventHandler
{
    ErrorHandler() {
        super("com.netflix.mediaclient.intent.action.MDXUPDATE_ERROR");
    }
    
    @Override
    public void handle(final RemotePlaybackListener remotePlaybackListener, final Intent intent) {
        Log.d("mdxui", "Error");
        remotePlaybackListener.error(intent.getIntExtra("errorCode", 0), intent.getStringExtra("errorDesc"));
    }
}
