// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.view.KeyEvent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.content.Context;

public final class PostPlayMediaButtonHandler
{
    private static final String TAG = "PostPlayMediaButtonHandler";
    
    private void playNextVideo(final Context context, final int n, final int n2, final String s) {
        if (Log.isLoggable("PostPlayMediaButtonHandler", 3)) {
            Log.d("PostPlayMediaButtonHandler", "Sending intent to play next video, uuid: " + s + ", catalogId: " + n + ", episodeId: " + n2);
        }
        final Intent addCategory = new Intent("com.netflix.mediaclient.intent.action.MDX_PLAY_VIDEOIDS").setClass(context, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").addCategory("com.netflix.mediaclient.intent.category.MDXRCC");
        if (n > 0) {
            addCategory.putExtra("catalogId", n);
        }
        if (n2 > 0) {
            addCategory.putExtra("episodeId", n2);
        }
        if (StringUtils.isNotEmpty(s)) {
            addCategory.putExtra("uuid", s);
        }
        context.startService(addCategory);
    }
    
    private void stopMDXTarget(final Context context, final String s) {
        if (Log.isLoggable("PostPlayMediaButtonHandler", 3)) {
            Log.d("PostPlayMediaButtonHandler", "Sending intent to stop MDX target, uuid: " + s);
        }
        final Intent addCategory = new Intent("com.netflix.mediaclient.intent.action.MDX_STOP").setClass(context, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").addCategory("com.netflix.mediaclient.intent.category.MDXRCC");
        if (StringUtils.isNotEmpty(s)) {
            addCategory.putExtra("uuid", s);
        }
        context.startService(addCategory);
    }
    
    public void handleButtonDown(final Context context, final KeyEvent keyEvent, final int n, final int n2, final String s) {
        switch (keyEvent.getKeyCode()) {
            default: {
                Log.d("PostPlayMediaButtonHandler", "received unhandled key");
            }
            case 86: {
                Log.d("PostPlayMediaButtonHandler", "received play_stop");
                this.stopMDXTarget(context, s);
            }
            case 85: {
                Log.d("PostPlayMediaButtonHandler", "received play_pause");
            }
            case 126: {
                Log.d("PostPlayMediaButtonHandler", "received play");
                this.playNextVideo(context, n, n2, s);
            }
        }
    }
}
