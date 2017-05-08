// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import com.netflix.mediaclient.servicemgr.MdxPostplayState;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.util.Pair;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public class MdxAgent$Utils
{
    public static void attemptMdxPrefetch(final ServiceManager serviceManager, final List<Pair<String, Integer>> list) {
        if (Log.isLoggable()) {
            Log.d("nf_mdx_MdxAgent", "attempt to prefetch a movie over MDX " + list.toString());
        }
        if (serviceManager.getMdx() != null) {
            serviceManager.getMdx().prefetchVideo(list);
            return;
        }
        Log.d("nf_mdx_MdxAgent", "MDX is not initialized, ignore prefetch");
    }
    
    public static Intent createIntent(final Context context, final String s, final String s2) {
        final Intent intent = new Intent(s);
        intent.setClass(context, (Class)NetflixService.class);
        intent.addCategory("com.netflix.mediaclient.intent.category.MDX");
        intent.putExtra("uuid", s2);
        return intent;
    }
    
    public static boolean isInPostPlay(final Intent intent) {
        if (intent.hasExtra("postplayState")) {
            final String string = intent.getExtras().getString("postplayState");
            if (!StringUtils.isEmpty(string)) {
                final MdxPostplayState mdxPostplayState = new MdxPostplayState(string);
                if (mdxPostplayState.isInCountdown() || mdxPostplayState.isInPrompt()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean isSameAsCurrentlyPlaying(final String s, final String s2, final WebApiUtils$VideoIds webApiUtils$VideoIds) {
        if (StringUtils.isEmpty(s2) && StringUtils.isNotEmpty(s) && StringUtils.isNumeric(s) && webApiUtils$VideoIds.catalogId == Integer.valueOf(s)) {
            Log.v("nf_mdx_MdxAgent", "same movie");
            return true;
        }
        if (StringUtils.isNotEmpty(s2) && StringUtils.isNumeric(s2) && webApiUtils$VideoIds.episodeId == Integer.valueOf(s2)) {
            Log.v("nf_mdx_MdxAgent", "same show");
            return true;
        }
        return false;
    }
    
    public static boolean playVideo(final NetflixActivity netflixActivity, final Asset asset, final int n, final boolean b) {
        if (asset.isEpisode()) {
            Log.d("nf_mdx_MdxAgent", "Playing episode");
            return playVideo(netflixActivity, asset.getParentId(), asset.getPlayableId(), asset.getTrackId(), n, b);
        }
        Log.d("nf_mdx_MdxAgent", "Playing movie");
        return playVideo(netflixActivity, asset.getPlayableId(), null, asset.getTrackId(), n, b);
    }
    
    public static boolean playVideo(final NetflixActivity netflixActivity, final Asset asset, final boolean b) {
        return playVideo(netflixActivity, asset, asset.getPlaybackBookmark(), b);
    }
    
    private static boolean playVideo(final NetflixActivity netflixActivity, final String s, final String s2, final int n, final int n2, final boolean b) {
        if (Log.isLoggable()) {
            Log.v("nf_mdx_MdxAgent", "Starting playback movieId " + s + ", epId " + s2 + ", trackId " + n + ", bookmark " + n2);
        }
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        if (!ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
            Log.w("nf_mdx_MdxAgent", "MDX agent not available - can't play video");
        }
        else {
            final WebApiUtils$VideoIds videoIds = serviceManager.getMdx().getVideoIds();
            if (b || videoIds == null || !isSameAsCurrentlyPlaying(s, s2, videoIds)) {
                final String currentTarget = serviceManager.getMdx().getCurrentTarget();
                final Intent intent = createIntent((Context)netflixActivity, "com.netflix.mediaclient.intent.action.MDX_PLAY_VIDEOIDS", currentTarget);
                if (s != null) {
                    intent.putExtra("catalogId", Integer.parseInt(s));
                }
                if (s2 != null) {
                    intent.putExtra("episodeId", Integer.parseInt(s2));
                }
                intent.putExtra("trackId", n);
                intent.putExtra("time", n2);
                netflixActivity.startService(intent);
                Log.v("nf_mdx_MdxAgent", "play done");
                netflixActivity.startService(createIntent((Context)netflixActivity, "com.netflix.mediaclient.intent.action.MDX_GETCAPABILITY", currentTarget));
                return true;
            }
        }
        return false;
    }
}
