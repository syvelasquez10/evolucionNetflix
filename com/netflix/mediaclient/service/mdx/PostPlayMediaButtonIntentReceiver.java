// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.view.KeyEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class PostPlayMediaButtonIntentReceiver extends BroadcastReceiver
{
    private static final String TAG = "nf_mdx_RemoteClient";
    
    private void playNextVideo(final Context context, final Intent intent) {
        final Intent addCategory = new Intent("com.netflix.mediaclient.intent.action.MDX_PLAY_VIDEOIDS").setClass(context, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").addCategory("com.netflix.mediaclient.intent.category.MDXRCC");
        if (intent.hasExtra("episodeId")) {
            addCategory.putExtra("episodeId", intent.getExtras().getInt("episodeId"));
        }
        if (intent.hasExtra("catalogId")) {
            addCategory.putExtra("catalogId", intent.getExtras().getInt("catalogId"));
        }
        if (intent.hasExtra("uuid")) {
            addCategory.putExtra("uuid", intent.getExtras().getString("uuid"));
        }
        context.startService(addCategory);
    }
    
    private void stopMDXTarget(final Context context, final Intent intent) {
        final Intent addCategory = new Intent("com.netflix.mediaclient.intent.action.MDX_STOP").setClass(context, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").addCategory("com.netflix.mediaclient.intent.category.MDXRCC");
        if (intent.hasExtra("uuid")) {
            addCategory.putExtra("uuid", intent.getExtras().getString("uuid"));
        }
        context.startService(addCategory);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction().equals("android.intent.action.MEDIA_BUTTON")) {
            Log.d("nf_mdx_RemoteClient", "received ACTION_MEDIA_BUTTON");
            final KeyEvent keyEvent = (KeyEvent)intent.getExtras().get("android.intent.extra.KEY_EVENT");
            if (keyEvent.getAction() == 0) {
                switch (keyEvent.getKeyCode()) {
                    default: {
                        Log.d("nf_mdx_RemoteClient", "received unhandled key");
                    }
                    case 86: {
                        Log.d("nf_mdx_RemoteClient", "received play_stop");
                        this.stopMDXTarget(context, intent);
                    }
                    case 85: {
                        Log.d("nf_mdx_RemoteClient", "received play_pause");
                    }
                    case 126: {
                        Log.d("nf_mdx_RemoteClient", "received play");
                        this.playNextVideo(context, intent);
                    }
                }
            }
        }
    }
}
