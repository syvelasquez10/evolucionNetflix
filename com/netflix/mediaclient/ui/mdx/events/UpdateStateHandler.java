// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.ui.mdx.RemotePlaybackListener;

public final class UpdateStateHandler extends EventHandler
{
    UpdateStateHandler() {
        super("com.netflix.mediaclient.intent.action.MDXUPDATE_STATE");
    }
    
    @Override
    public void handle(final RemotePlaybackListener remotePlaybackListener, final Intent intent) {
        Log.d("mdxui", "Remote state is changed");
        remotePlaybackListener.updateState(intent.getStringExtra("currentState"), intent.getIntExtra("time", 0), intent.getIntExtra("volume", 0));
    }
}
