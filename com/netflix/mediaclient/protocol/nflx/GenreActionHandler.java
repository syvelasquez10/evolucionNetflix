// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class GenreActionHandler extends BaseNflxHandlerWithoutDelayedActionSupport
{
    public GenreActionHandler(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    @Override
    public NflxHandler$Response handle() {
        final String s = this.mParamsMap.get("genreid");
        if (s == null) {
            Log.v("NflxHandler", "Could not find genre ID");
            return NflxHandler$Response.NOT_HANDLING;
        }
        this.mActivity.getServiceManager().getBrowse().fetchLoLoMoSummary(s, new GenreActionHandler$1FetchLoLoMoSummaryCallback(this, this.mActivity, s));
        return NflxHandler$Response.HANDLING_WITH_DELAY;
    }
}
