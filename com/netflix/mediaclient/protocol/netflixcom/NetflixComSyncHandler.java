// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.List;

public class NetflixComSyncHandler implements NetflixComHandler
{
    private static final String TAG = "NetflixComSyncHandler";
    
    @Override
    public boolean canHandle(final List<String> list) {
        return list.size() > 1;
    }
    
    @Override
    public NflxHandler$Response tryHandle(final NetflixActivity netflixActivity, final List<String> list, final String s) {
        final String dialUuidAsCurrentTarget = list.get(1);
        final IMdx mdx = netflixActivity.getServiceManager().getMdx();
        if (mdx == null) {
            Log.e("NetflixComSyncHandler", "Sync action is required, MDX agent is null. This should NOT happen!");
        }
        if (MdxUtils.isMdxTargetAvailable(netflixActivity.getServiceManager(), dialUuidAsCurrentTarget)) {
            Log.d("NetflixComSyncHandler", "Sync action is required, target is available, sync");
            final boolean setDialUuidAsCurrentTarget = mdx.setDialUuidAsCurrentTarget(dialUuidAsCurrentTarget);
            if (Log.isLoggable()) {
                Log.d("NetflixComSyncHandler", "Set dial uuid as current target got status: " + setDialUuidAsCurrentTarget);
            }
        }
        else {
            Log.w("NetflixComSyncHandler", "Sync action is required, target not available, do nothing!");
        }
        NetflixComUtils.startHomeActivity(netflixActivity);
        return NflxHandler$Response.HANDLING;
    }
}
