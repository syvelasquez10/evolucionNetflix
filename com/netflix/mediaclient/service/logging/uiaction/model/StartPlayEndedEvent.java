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
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.ui.common.PlayLocationType;

public final class StartPlayEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String PLAYER_TYPE = "playerType";
    public static final String PLAY_LOCATION = "playLocation";
    public static final String RANK_TITLE = "rankTitle";
    private static final String UIA_NAME = "startPlay";
    private PlayLocationType mPlayLocation;
    private PlayerType mPlayerType;
    private Integer mRankTitle;
    
    public StartPlayEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer mRankTitle, final PlayerType mPlayerType, final PlayLocationType mPlayLocation) {
        super("startPlay", deviceUniqueId, n, clientLogging$ModalView, userActionLogging$CommandName, clientLogging$CompletionReason, uiError);
        this.mRankTitle = mRankTitle;
        this.mPlayerType = mPlayerType;
        this.mPlayLocation = mPlayLocation;
    }
    
    public StartPlayEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mRankTitle = JsonUtils.getIntegerObject(jsonObject, "rankTitle", null);
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        if (this.mRankTitle != null) {
            data.put("rankTitle", (int)this.mRankTitle);
        }
        if (this.mPlayerType != null) {
            data.put("playerType", (Object)PlayerType.mapPlayerTypeForLogging(this.mPlayerType));
        }
        if (this.mPlayLocation != null) {
            data.put("playLocation", (Object)this.mPlayLocation.getValue());
        }
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
