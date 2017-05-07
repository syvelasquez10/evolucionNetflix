// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.ui.mdx.RemotePlaybackListener;

public final class UpdateVideoMetadataHandler extends EventHandler
{
    UpdateVideoMetadataHandler() {
        super("com.netflix.mediaclient.intent.action.MDXUPDATE_MOVIEMETADA");
    }
    
    @Override
    public void handle(final RemotePlaybackListener remotePlaybackListener, final Intent intent) {
        Log.d("mdxui", "Video metadata update");
        remotePlaybackListener.updateDuration(intent.getIntExtra("duration", 0));
    }
}
