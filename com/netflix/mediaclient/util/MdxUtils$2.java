// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.service.mdx.cast.CastAgent;
import android.content.DialogInterface;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.DialogInterface$OnClickListener;

final class MdxUtils$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ NetflixActivity val$activity;
    
    MdxUtils$2(final NetflixActivity val$activity) {
        this.val$activity = val$activity;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        final MdxMiniPlayerFrag mdxMiniPlayerFrag = this.val$activity.getMdxMiniPlayerFrag();
        final IMdx mdx = this.val$activity.getServiceManager().getMdx();
        if (mdxMiniPlayerFrag != null && mdx != null) {
            if (mdxMiniPlayerFrag.getPlayer() != null && mdxMiniPlayerFrag.getPlayer().isPlaying()) {
                mdxMiniPlayerFrag.getPlayer().stop(false);
                mdx.switchPlaybackFromTarget(null, 0);
                mdxMiniPlayerFrag.notifyPlayingBackLocal();
            }
            mdx.setCurrentTarget(null);
            final CastAgent castAgent = (CastAgent)mdx;
            if (castAgent != null) {
                castAgent.disconnectFromCast();
            }
            LocalBroadcastManager.getInstance((Context)this.val$activity).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.UPDATE_CAPABILITIES_BADGES"));
        }
    }
}
