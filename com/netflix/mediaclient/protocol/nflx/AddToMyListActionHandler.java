// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class AddToMyListActionHandler extends ViewDetailsActionHandler
{
    public AddToMyListActionHandler(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    @Override
    protected DetailsActivity.Action getAction() {
        return DetailsActivity.Action.AddToMyList;
    }
    
    @Override
    public Response handle() {
        final String s = this.mParamsMap.get("msg_token");
        String profileId = null;
        if (this.mActivity == null || this.mActivity.getServiceManager() == null || this.mActivity.getServiceManager().getCurrentProfile() == null) {
            Log.w("NflxHandler", "Current user profile not available!");
        }
        else {
            profileId = this.mActivity.getServiceManager().getCurrentProfile().getProfileId();
        }
        if (Log.isLoggable("NflxHandler", 3)) {
            Log.d("NflxHandler", "Add to my list action is for profile " + s + ", when current profile is: " + profileId);
        }
        if (s != null && s.equals(profileId)) {
            Log.d("NflxHandler", "Same profile, go to add to my list");
            return super.handle();
        }
        Log.w("NflxHandler", "Not same profile, go to MDP/SDP AND ADD (test)");
        return new ViewDetailsActionHandler(this.mActivity, this.mParamsMap).handle();
    }
}
