// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.Log;
import com.netflix.falkor.PQL;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.UserRating;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;

public class Falkor$Utils
{
    public static UserRating getUserRating(final String s, final ServiceManager serviceManager, final VideoDetails videoDetails) {
        PQL pql;
        if (videoDetails.getType() == VideoType.EPISODE) {
            pql = PQL.create("shows", videoDetails.getPlayable().getTopLevelId(), "rating");
        }
        else {
            pql = PQL.create("movies", videoDetails.getId(), "rating");
        }
        if (Log.isLoggable()) {
            Log.v(s, "Getting user rating with pql: " + pql);
        }
        return (UserRating)serviceManager.getBrowse().getModelProxy().getValue(pql);
    }
}
