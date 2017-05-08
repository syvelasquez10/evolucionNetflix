// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import android.app.Activity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
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
    public NflxHandler$Response handle() {
        Log.d("NflxHandler", "Profile gate is required.");
        NflxProtocolUtils.reportOnProfileGate(this.mActivity, (Map)this.mParamsMap, this.mStarted);
        this.mActivity.startActivity(ProfileSelectionActivity.createSwitchFromDeepLinking((Activity)this.mActivity, IClientLogging$ModalView.homeScreen));
        return NflxHandler$Response.HANDLING;
    }
}
