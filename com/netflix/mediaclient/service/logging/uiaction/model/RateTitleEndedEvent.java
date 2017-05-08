// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;

public final class RateTitleEndedEvent extends BaseUIActionSessionEndedEvent
{
    private static final String IS_NEW_MS = "isNewMS";
    private static final String MATCH_SCORE = "matchScore";
    private static final String RANK_TITLE = "rankTitle";
    private static final String RATING = "rating";
    private static final String RATING_TYPE = "ratingType";
    private static final String UIA_NAME = "rateTitle";
    private int mMatchScore;
    private boolean mNewMS;
    private Integer mRankTitle;
    private int mRating;
    private String mRatingType;
    
    public RateTitleEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer mRankTitle, final int mRating, final String mRatingType, final int mMatchScore, final boolean mNewMS) {
        super("rateTitle", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mRating = mRating;
        this.mRankTitle = mRankTitle;
        this.mRatingType = mRatingType;
        this.mMatchScore = mMatchScore;
        this.mNewMS = mNewMS;
    }
    
    public RateTitleEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mRankTitle = JsonUtils.getIntegerObject(jsonObject, "rankTitle", (Integer)null);
        this.mRating = JsonUtils.getInt(jsonObject, "rating", 0);
        this.mRatingType = JsonUtils.getString(jsonObject, "ratingType", (String)null);
        this.mMatchScore = JsonUtils.getInt(jsonObject, "matchScore", -1);
        this.mNewMS = JsonUtils.getBoolean(jsonObject, "isNewMS", false);
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (this.mRankTitle != null) {
            data.put("rankTitle", (int)this.mRankTitle);
        }
        if (this.mRatingType != null) {
            data.put("ratingType", (Object)this.mRatingType);
        }
        if (this.mMatchScore != -1) {
            data.put("matchScore", this.mMatchScore);
        }
        if (this.mNewMS) {
            data.put("isNewMS", 1);
        }
        data.put("rating", this.mRating);
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
