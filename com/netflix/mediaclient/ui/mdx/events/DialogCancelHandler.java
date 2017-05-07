// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.ui.mdx.RemotePlaybackListener;

public final class DialogCancelHandler extends EventHandler
{
    DialogCancelHandler() {
        super("com.netflix.mediaclient.intent.action.MDXUPDATE_DIALOGCANCEL");
    }
    
    @Override
    public void handle(final RemotePlaybackListener remotePlaybackListener, final Intent intent) {
        Log.d("mdxui", "Cancel dialog");
        remotePlaybackListener.cancelDialog();
    }
}
