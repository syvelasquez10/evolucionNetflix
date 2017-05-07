// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;

public final class CommandHandler
{
    private static final int DEFAULT_INTEGER = -1;
    private static final String TAG = "nf_mdx";
    private CommandInterface mTarget;
    
    CommandHandler(final CommandInterface mTarget) {
        this.mTarget = mTarget;
    }
    
    void handleCommandIntent(final Intent intent) {
        if (!intent.hasCategory("com.netflix.mediaclient.intent.category.MDX")) {
            Log.e("nf_mdx", "handleCommandIntent:intent is not for mdx, " + intent.getCategories());
            return;
        }
        final String action = intent.getAction();
        if (StringUtils.isEmpty(action)) {
            Log.e("nf_mdx", "handleCommandIntent:intent doesn't have action, " + intent.toString());
            return;
        }
        final String stringExtra = intent.getStringExtra("uuid");
        if (action.equals("com.netflix.mediaclient.intent.action.MDX_GETCAPABILITY")) {
            this.mTarget.playerGetCapability(stringExtra);
            return;
        }
        if (action.equals("com.netflix.mediaclient.intent.action.MDX_GETSTATE")) {
            this.mTarget.playerGetCurrentState(stringExtra);
            return;
        }
        if (action.equals("com.netflix.mediaclient.intent.action.MDX_PLAY")) {
            final String stringExtra2 = intent.getStringExtra("catalogId");
            final int intExtra = intent.getIntExtra("trackId", -1);
            final String stringExtra3 = intent.getStringExtra("episodeId");
            final int intExtra2 = intent.getIntExtra("time", -1);
            if (intExtra == -1) {
                Log.e("nf_mdx", "handleCommandIntent: " + action + ", invalid parameters");
                return;
            }
            this.mTarget.playerPlay(stringExtra, stringExtra2, intExtra, stringExtra3, intExtra2);
        }
        else {
            if (action.equals("com.netflix.mediaclient.intent.action.MDX_PAUSE")) {
                this.mTarget.playerPause(stringExtra);
                return;
            }
            if (action.equals("com.netflix.mediaclient.intent.action.MDX_RESUME")) {
                this.mTarget.playerResume(stringExtra);
                return;
            }
            if (action.equals("com.netflix.mediaclient.intent.action.MDX_SEEK")) {
                final int intExtra3 = intent.getIntExtra("time", -1);
                if (intExtra3 == -1) {
                    Log.e("nf_mdx", "handleCommandIntent: " + action + ", invalid parameters");
                    return;
                }
                this.mTarget.playerSeek(stringExtra, intExtra3);
            }
            else if (action.equals("com.netflix.mediaclient.intent.action.MDX_SKIP")) {
                final int intExtra4 = intent.getIntExtra("time", -1);
                if (intExtra4 == -1) {
                    Log.e("nf_mdx", "handleCommandIntent: " + action + ", invalid parameters");
                    return;
                }
                this.mTarget.playerSkip(stringExtra, intExtra4);
            }
            else {
                if (action.equals("com.netflix.mediaclient.intent.action.MDX_STOP")) {
                    this.mTarget.playerStop(stringExtra);
                    return;
                }
                if (action.equals("com.netflix.mediaclient.intent.action.MDX_SETAUDIOSUB")) {
                    final String stringExtra4 = intent.getStringExtra("audioTrackId");
                    final String stringExtra5 = intent.getStringExtra("subtitleTrackId");
                    if (StringUtils.isEmpty(stringExtra4) || StringUtils.isEmpty(stringExtra5)) {
                        Log.e("nf_mdx", "handleCommandIntent: " + action + ", invalid parameters");
                        return;
                    }
                    this.mTarget.playerSetAudioSubtitle(stringExtra, stringExtra4, stringExtra5);
                }
                else if (action.equals("com.netflix.mediaclient.intent.action.MDX_SETVOLUME")) {
                    final int intExtra5 = intent.getIntExtra("volume", -1);
                    if (intExtra5 == -1) {
                        Log.e("nf_mdx", "handleCommandIntent: " + action + ", invalid parameters");
                        return;
                    }
                    this.mTarget.playerSetVolume(stringExtra, intExtra5);
                }
                else if (action.equals("com.netflix.mediaclient.intent.action.MDX_AUTOADV")) {
                    final int intExtra6 = intent.getIntExtra("speed", -1);
                    if (intExtra6 == -1) {
                        Log.e("nf_mdx", "handleCommandIntent: " + action + ", invalid parameters");
                        return;
                    }
                    this.mTarget.playerAutoAdvance(stringExtra, intExtra6);
                }
                else if (action.equals("com.netflix.mediaclient.intent.action.MDX_METADATA")) {
                    final String stringExtra6 = intent.getStringExtra("catalogId");
                    final String stringExtra7 = intent.getStringExtra("episodeId");
                    final String stringExtra8 = intent.getStringExtra("type");
                    if (StringUtils.isEmpty(stringExtra6) || StringUtils.isEmpty(stringExtra7)) {
                        Log.e("nf_mdx", "handleCommandIntent: " + action + ", invalid parameters");
                        return;
                    }
                    this.mTarget.playerChangeMetaData(stringExtra, stringExtra6, stringExtra7, stringExtra8);
                }
                else if (action.equals("com.netflix.mediaclient.intent.action.MDX_DIALOGRESP")) {
                    final String stringExtra9 = intent.getStringExtra("uid");
                    final String stringExtra10 = intent.getStringExtra("data");
                    if (StringUtils.isEmpty(stringExtra9) || StringUtils.isEmpty(stringExtra10)) {
                        Log.e("nf_mdx", "handleCommandIntent: " + action + ", invalid parameters");
                        return;
                    }
                    this.mTarget.playerDialogReponse(stringExtra, stringExtra9, stringExtra10);
                }
                else {
                    if (action.equals("com.netflix.mediaclient.intent.action.MDX_GETAUDIOSUB")) {
                        this.mTarget.playerGetAudioSubtitle(stringExtra);
                        return;
                    }
                    Log.e("nf_mdx", "handleCommandIntent: " + action + ", unknown command");
                }
            }
        }
    }
}
