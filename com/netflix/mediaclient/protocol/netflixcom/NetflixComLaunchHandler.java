// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.List;

class NetflixComLaunchHandler implements NetflixComHandler
{
    private static final String TAG = "NetflixComLaunchHandler";
    
    @Override
    public boolean canHandle(final List<String> list) {
        return true;
    }
    
    @Override
    public NflxHandler$Response tryHandle(final NetflixActivity netflixActivity, final List<String> list, final String s) {
        Log.v("NetflixComLaunchHandler", "Starting launch activity");
        NetflixComUtils.startLaunchActivity(netflixActivity);
        return NflxHandler$Response.HANDLING;
    }
}
