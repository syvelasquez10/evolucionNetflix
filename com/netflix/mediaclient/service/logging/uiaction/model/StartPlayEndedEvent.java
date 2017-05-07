// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.media.PlayerType;

public final class StartPlayEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String PLAYER_TYPE = "playerType";
    public static final String RANK_TITLE = "rankTitle";
    private static final String UIA_NAME = "startPlay";
    private PlayerType mPlayerType;
    private Integer mRankTitle;
    
    public StartPlayEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging.ModalView modalView, final UserActionLogging.CommandName commandName, final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer mRankTitle, final PlayerType mPlayerType) {
        super("startPlay", deviceUniqueId, n, modalView, commandName, completionReason, uiError);
        this.mRankTitle = mRankTitle;
        this.mPlayerType = mPlayerType;
    }
    
    public StartPlayEndedEvent(final JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.mRankTitle = JsonUtils.getIntegerObject(jsonObject, "rankTitle", null);
    }
    
    @Override
    protected JSONObject getData() throws JSONException {
        final JSONObject data = super.getData();
        if (this.mRankTitle != null) {
            data.put("rankTitle", (int)this.mRankTitle);
        }
        if (this.mPlayerType != null) {
            data.put("playerType", (Object)PlayerType.mapPlayerTypeForLogging(this.mPlayerType));
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
