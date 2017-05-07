// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.Log;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

abstract class BaseNflxHandlerWithoutDelayedActionSupport extends BaseNflxHandler
{
    public BaseNflxHandlerWithoutDelayedActionSupport(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    @Override
    protected NflxHandler$Response handleEpisodeFromTinyUrl(final String s, final String s2, final String s3) {
        Log.d("NflxHandler", "handleEpisodeFromTinyUrl");
        return NflxHandler$Response.NOT_HANDLING;
    }
    
    @Override
    protected NflxHandler$Response handleMovieFromTinyUrl(final String s, final String s2, final String s3) {
        Log.d("NflxHandler", "handleMovieFromTinyUrl");
        return NflxHandler$Response.NOT_HANDLING;
    }
}
