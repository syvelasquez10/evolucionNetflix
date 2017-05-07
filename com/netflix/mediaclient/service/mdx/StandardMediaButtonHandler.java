// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.view.KeyEvent;
import android.content.Context;

public final class StandardMediaButtonHandler
{
    private static final String TAG = "StandardMediaButtonHandler";
    
    public void handleButtonDown(final Context context, final KeyEvent keyEvent) {
        Log.d("StandardMediaButtonHandler", "handling media key event...");
        switch (keyEvent.getKeyCode()) {
            default: {
                Log.d("StandardMediaButtonHandler", "received unhandled key");
            }
            case 85: {
                Log.d("StandardMediaButtonHandler", "received play_pause");
                context.startService(new Intent("com.netflix.mediaclient.intent.action.MDX_TOGGLE_PAUSE").setClass(context, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").addCategory("com.netflix.mediaclient.intent.category.MDXRCC"));
            }
            case 127: {
                Log.d("StandardMediaButtonHandler", "received pause");
                context.startService(new Intent("com.netflix.mediaclient.intent.action.MDX_PAUSE").setClass(context, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").addCategory("com.netflix.mediaclient.intent.category.MDXRCC"));
            }
            case 126: {
                Log.d("StandardMediaButtonHandler", "received play");
                context.startService(new Intent("com.netflix.mediaclient.intent.action.MDX_RESUME").setClass(context, (Class)NetflixService.class).addCategory("com.netflix.mediaclient.intent.category.MDX").addCategory("com.netflix.mediaclient.intent.category.MDXRCC"));
            }
        }
    }
}
