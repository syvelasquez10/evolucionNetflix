// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.Log;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class HomeActionHandler extends BaseNflxHandlerWithoutDelayedActionSupport
{
    HomeActionHandler(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    @Override
    public Response handle() {
        Log.v("NflxHandler", "Starting home activity");
        this.mActivity.startActivity(HomeActivity.createStartIntent(this.mActivity));
        this.mActivity.overridePendingTransition(0, 0);
        return Response.HANDLING;
    }
}
