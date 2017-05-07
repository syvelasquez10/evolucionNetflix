// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import android.app.Activity;
import com.netflix.mediaclient.ui.search.SearchActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class SearchActionHandler extends BaseNflxHandlerWithoutDelayedActionSupport
{
    SearchActionHandler(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    @Override
    public NflxHandler$Response handle() {
        final String s = this.mParamsMap.get("query");
        if (StringUtils.isEmpty(s)) {
            Log.v("NflxHandler", "Could not find query param");
            return NflxHandler$Response.NOT_HANDLING;
        }
        SearchActivity.search(this.mActivity, s);
        return NflxHandler$Response.HANDLING;
    }
}
