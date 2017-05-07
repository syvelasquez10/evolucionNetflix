// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.ui.details.DetailsActivity$Action;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class AddToMyListActionHandler extends ViewDetailsActionHandler
{
    public AddToMyListActionHandler(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    @Override
    protected DetailsActivity$Action getAction() {
        return DetailsActivity$Action.AddToMyList;
    }
    
    @Override
    protected String getActionToken() {
        return this.mParamsMap.get("msg_token");
    }
}
