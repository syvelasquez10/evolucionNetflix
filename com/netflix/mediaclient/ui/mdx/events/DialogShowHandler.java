// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import org.json.JSONException;
import com.netflix.mediaclient.ui.mdx.RemoteDialog;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.ui.mdx.RemotePlaybackListener;

public final class DialogShowHandler extends EventHandler
{
    DialogShowHandler() {
        super("com.netflix.mediaclient.intent.action.MDXUPDATE_DIALOGSHOW");
    }
    
    @Override
    public void handle(final RemotePlaybackListener remotePlaybackListener, Intent stringExtra) {
        Log.d("mdxui", "Show dialog");
        stringExtra = (Intent)stringExtra.getStringExtra("stringBlob");
        try {
            remotePlaybackListener.showDialog(new RemoteDialog((String)stringExtra));
        }
        catch (JSONException ex) {
            Log.e("mdxui", "Unable to display dialog, failed to process " + (String)stringExtra, (Throwable)ex);
        }
    }
}
