// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.ui.mdx.RemotePlaybackListener;

public class UpdateVideoMetadataAvailableHandler extends EventHandler
{
    public UpdateVideoMetadataAvailableHandler() {
        super("com.netflix.mediaclient.intent.action.MDXUPDATE_MOVIEMETADATA_AVAILABLE");
    }
    
    @Override
    public void handle(final RemotePlaybackListener remotePlaybackListener, final Intent intent) {
        Log.d("mdxui", "Video metadata update");
        remotePlaybackListener.updateVideoMetadata();
    }
}
