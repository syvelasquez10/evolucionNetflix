// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.List;

public class NetflixComProfilesHandler implements NetflixComHandler
{
    private static final String TAG = "NetflixComProfilesHandler";
    
    @Override
    public boolean canHandle(final List<String> list) {
        return true;
    }
    
    @Override
    public NflxHandler$Response tryHandle(final NetflixActivity netflixActivity, final List<String> list, final String s) {
        Log.v("NetflixComProfilesHandler", "Starting Profiles activity");
        NetflixComUtils.startProfilesActivity(netflixActivity);
        return NflxHandler$Response.HANDLING;
    }
}
