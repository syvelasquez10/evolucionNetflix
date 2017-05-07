// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import android.app.Activity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.Log;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class ProfileGateActionHandler extends BaseNflxHandlerWithoutDelayedActionSupport
{
    private long mStarted;
    
    public ProfileGateActionHandler(final NetflixActivity netflixActivity, final Map<String, String> map, final long mStarted) {
        super(netflixActivity, map);
        this.mStarted = mStarted;
    }
    
    @Override
    public Response handle() {
        Log.d("NflxHandler", "Profile gate is required.");
        NflxProtocolUtils.reportOnProfileGate(this.mActivity, this.mParamsMap, this.mStarted);
        this.mActivity.startActivity(ProfileSelectionActivity.createSwitchFromDeepLinking(this.mActivity, IClientLogging.ModalView.homeScreen));
        return Response.HANDLING;
    }
}
