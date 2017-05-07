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
    public static final String RANK_TITLE = " rankTitle";
    public static final String RATING = " rating";
    private static final String UIA_NAME = "rateTitle";
    private Integer mRankTitle;
    private int mRating;
    
    public RateTitleEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer mRankTitle, final int mRating) {
        super("rateTitle", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mRating = mRating;
        this.mRankTitle = mRankTitle;
    }
    
    public RateTitleEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mRankTitle = JsonUtils.getIntegerObject(jsonObject, " rankTitle", null);
        this.mRating = JsonUtils.getInt(jsonObject, " rating", 0);
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (this.mRankTitle != null) {
            data.put(" rankTitle", (int)this.mRankTitle);
        }
        data.put(" rating", this.mRating);
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
