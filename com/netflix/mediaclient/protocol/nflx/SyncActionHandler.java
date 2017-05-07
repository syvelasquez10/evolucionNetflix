// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class SyncActionHandler extends BaseNflxHandlerWithoutDelayedActionSupport
{
    public SyncActionHandler(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    @Override
    public NflxHandler$Response handle() {
        final String justUuid = NflxProtocolUtils.extractJustUuid(this.mParamsMap.get("targetid"));
        final IMdx mdx = this.mActivity.getServiceManager().getMdx();
        if (mdx == null) {
            Log.e("NflxHandler", "Sync action is required, MDX agent is null. This should NOT happen!");
            return NflxHandler$Response.NOT_HANDLING;
        }
        if (MdxUtils.isMdxTargetAvailable(this.mActivity.getServiceManager(), justUuid)) {
            Log.d("NflxHandler", "Sync action is required, target is available, sync");
            final boolean setDialUuidAsCurrentTarget = mdx.setDialUuidAsCurrentTarget(justUuid);
            if (Log.isLoggable()) {
                Log.d("NflxHandler", "Set dial uuid as current target was success " + setDialUuidAsCurrentTarget);
            }
            return NflxHandler$Response.NOT_HANDLING;
        }
        Log.d("NflxHandler", "Sync action is required, target not available, do nothing!");
        return NflxHandler$Response.NOT_HANDLING;
    }
}
