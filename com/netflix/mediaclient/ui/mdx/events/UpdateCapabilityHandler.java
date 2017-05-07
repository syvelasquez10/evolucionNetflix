// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.ui.mdx.RemotePlaybackListener;

public final class UpdateCapabilityHandler extends EventHandler
{
    UpdateCapabilityHandler() {
        super("com.netflix.mediaclient.intent.action.MDXUPDATE_CAPABILITY");
    }
    
    @Override
    public void handle(final RemotePlaybackListener remotePlaybackListener, final Intent intent) {
        Log.d("mdxui", "Update capability");
        final String stringExtra = intent.getStringExtra("stringBlob");
        try {
            remotePlaybackListener.updateTargetCapabilities(new MdxTargetCapabilities(stringExtra));
        }
        catch (Exception ex) {
            Log.e("mdxui", "Failed to extract capability data ", ex);
        }
    }
}
