// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.service.NetflixService;
import android.view.KeyEvent;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class MediaButtonIntentReceiver extends BroadcastReceiver
{
    private static final String TAG = "nf_mdx_RemoteClient";
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction().equals("android.intent.action.MEDIA_BUTTON")) {
            Log.d("nf_mdx_RemoteClient", "received ACTION_MEDIA_BUTTON");
            final KeyEvent keyEvent = (KeyEvent)intent.getExtras().get("android.intent.extra.KEY_EVENT");
            if (keyEvent.getAction() == 0) {
                switch (keyEvent.getKeyCode()) {
                    default: {
                        Log.d("nf_mdx_RemoteClient", "received unhandled key");
                    }
                    case 85: {
                        Log.d("nf_mdx_RemoteClient", "received play_pause");
                        context.startService(new Intent("com.netflix.mediaclient.intent.action.MDX_TOGGLE_PAUSE").setClass(context, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").addCategory("com.netflix.mediaclient.intent.category.MDXRCC"));
                    }
                    case 127: {
                        Log.d("nf_mdx_RemoteClient", "received pause");
                        context.startService(new Intent("com.netflix.mediaclient.intent.action.MDX_PAUSE").setClass(context, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").addCategory("com.netflix.mediaclient.intent.category.MDXRCC"));
                    }
                    case 126: {
                        Log.d("nf_mdx_RemoteClient", "received play");
                        context.startService(new Intent("com.netflix.mediaclient.intent.action.MDX_RESUME").setClass(context, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").addCategory("com.netflix.mediaclient.intent.category.MDXRCC"));
                    }
                }
            }
        }
    }
}
