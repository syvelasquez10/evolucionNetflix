// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiaction.model;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;

public class PostPlayEndedEvent extends BaseUIActionSessionEndedEvent
{
    public static final String CHOSEN_INDEX = "chosenIndex";
    public static final String CHOSEN_VIDEOID = "chosenVideoId";
    public static final String DID_USER_CONTINUE_WATCHING = "didUserContinueWatching";
    public static final String IS_AUTOPLAY_COUNTDOWN_ENABLED = "isAutoPlayCountdownEnabled";
    public static final String LENGTH_OF_AUTOPLAY_COUNTDOWN = "lengthOfAutoPlayCountdown";
    public static final String POSTPLAY_EXPERIENCE = "postPlayExperience";
    public static final String TRACK_ID = "trackId";
    private static final String UIA_NAME = "postPlay";
    public static final String WAS_AUTOPLAY_COUNTDOWN_INTERRUPTED = "wasAutoPlayCountdownInterrupted";
    private boolean mAutoPlayCountdownEnabled;
    private Integer mChosenIndex;
    private Integer mChosenVideoId;
    private boolean mDidUserContinueWatching;
    private int mLengthOfAutoPlayCountdown;
    private UserActionLogging$PostPlayExperience mPostPlayExperience;
    private int mTrackId;
    private boolean mWasAutoPlayCountdownInterrupted;
    
    public PostPlayEndedEvent(final DeviceUniqueId deviceUniqueId, final long n, final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final boolean mAutoPlayCountdownEnabled, final int mLengthOfAutoPlayCountdown, final UserActionLogging$PostPlayExperience mPostPlayExperience, final boolean mWasAutoPlayCountdownInterrupted, final boolean mDidUserContinueWatching, final Integer mChosenVideoId, final Integer mChosenIndex, final int mTrackId) {
        super("postPlay", deviceUniqueId, n, clientLogging$ModalView, null, clientLogging$CompletionReason, uiError);
        this.mAutoPlayCountdownEnabled = mAutoPlayCountdownEnabled;
        this.mLengthOfAutoPlayCountdown = mLengthOfAutoPlayCountdown;
        this.mPostPlayExperience = mPostPlayExperience;
        this.mWasAutoPlayCountdownInterrupted = mWasAutoPlayCountdownInterrupted;
        this.mDidUserContinueWatching = mDidUserContinueWatching;
        this.mChosenVideoId = mChosenVideoId;
        this.mChosenIndex = mChosenIndex;
        this.mTrackId = mTrackId;
    }
    
    public PostPlayEndedEvent(final JSONObject jsonObject) {
        super(jsonObject);
        this.mAutoPlayCountdownEnabled = JsonUtils.getBoolean(jsonObject, "isAutoPlayCountdownEnabled", false);
        this.mLengthOfAutoPlayCountdown = JsonUtils.getInt(jsonObject, "lengthOfAutoPlayCountdown", 0);
        final String string = JsonUtils.getString(jsonObject, "postPlayExperience", (String)null);
        if (StringUtils.isNotEmpty(string)) {
            this.mPostPlayExperience = UserActionLogging$PostPlayExperience.valueOf(string);
        }
        this.mWasAutoPlayCountdownInterrupted = JsonUtils.getBoolean(jsonObject, "wasAutoPlayCountdownInterrupted", false);
        this.mDidUserContinueWatching = JsonUtils.getBoolean(jsonObject, "didUserContinueWatching", false);
        this.mChosenVideoId = JsonUtils.getIntegerObject(jsonObject, "chosenVideoId", (Integer)null);
        this.mChosenIndex = JsonUtils.getIntegerObject(jsonObject, "chosenIndex", (Integer)null);
        this.mTrackId = JsonUtils.getInt(jsonObject, "trackId", 0);
    }
    
    @Override
    protected JSONObject getData() {
        final JSONObject data = super.getData();
        data.put("isAutoPlayCountdownEnabled", this.mAutoPlayCountdownEnabled);
        data.put("lengthOfAutoPlayCountdown", this.mLengthOfAutoPlayCountdown);
        if (this.mPostPlayExperience != null) {
            data.put("postPlayExperience", (Object)this.mPostPlayExperience.toString());
        }
        data.put("wasAutoPlayCountdownInterrupted", this.mWasAutoPlayCountdownInterrupted);
        data.put("didUserContinueWatching", this.mDidUserContinueWatching);
        if (this.mChosenVideoId != null) {
            data.put("chosenVideoId", (int)this.mChosenVideoId);
        }
        if (this.mChosenIndex != null) {
            data.put("chosenIndex", (int)this.mChosenIndex);
        }
        data.put("trackId", this.mTrackId);
        return data;
    }
    
    @Override
    public boolean isMemberEvent() {
        return true;
    }
}
