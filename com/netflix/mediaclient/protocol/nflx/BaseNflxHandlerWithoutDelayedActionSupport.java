// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

abstract class BaseNflxHandlerWithoutDelayedActionSupport extends BaseNflxHandler
{
    public BaseNflxHandlerWithoutDelayedActionSupport(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    @Override
    protected NflxHandler$Response handleEpisodeFromTinyUrl(final JSONObject jsonObject, final String s, final String s2) {
        Log.d("NflxHandler", "handleEpisodeFromTinyUrl");
        return NflxHandler$Response.NOT_HANDLING;
    }
    
    @Override
    protected NflxHandler$Response handleMovieFromTinyUrl(final JSONObject jsonObject, final String s, final String s2) {
        Log.d("NflxHandler", "handleMovieFromTinyUrl NOOP");
        return NflxHandler$Response.NOT_HANDLING;
    }
}
