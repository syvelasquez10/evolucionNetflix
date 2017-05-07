// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social.model;

import org.json.JSONException;
import org.json.JSONArray;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.SocialLogging;

public final class RecommendFriendSelectedEvent extends BaseSocialDiscreteEvent
{
    private static final String FRIENDS_POSITIONS = "friendsPositions";
    protected static final String NAME = "recommendFriendSelected";
    private SocialLogging.FriendPosition[] mFriendPositions;
    private int mTrackId;
    private IClientLogging.ModalView mView;
    
    public RecommendFriendSelectedEvent(final IClientLogging.ModalView mView, final SocialLogging.FriendPosition[] mFriendPositions, final int mTrackId) {
        super("recommendFriendSelected");
        this.mView = mView;
        this.mFriendPositions = mFriendPositions;
        this.mTrackId = mTrackId;
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mView != null) {
            data.put("view", (Object)this.mView.name());
        }
        data.put("trackId", this.mTrackId);
        data.put("originatingRequestGuid", (Object)ConsolidatedLoggingUtils.createGUID());
        if (this.mFriendPositions != null) {
            final JSONArray jsonArray = new JSONArray();
            data.put("friendsPositions", (Object)jsonArray);
            final SocialLogging.FriendPosition[] mFriendPositions = this.mFriendPositions;
            for (int length = mFriendPositions.length, i = 0; i < length; ++i) {
                jsonArray.put((Object)mFriendPositions[i].toJson());
            }
        }
        return data;
    }
}
